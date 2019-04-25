/**
 * Author: lin
 * Date: 2019/4/6 17:29
 */
package com.prd.approval.service.impl;

import com.prd.approval.dao.*;
import com.prd.approval.entity.*;
import com.prd.approval.entity.Process;
import com.prd.approval.service.TemplateService;
import com.prd.approval.utils.IDNOUtil;
import com.prd.approval.utils.LogUtil;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 */

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private EventCreatorDAO eventCreatorDAO;

    @Autowired
    private AuditorDAO auditorDAO;

    @Autowired
    private ProcessDAO processDAO;

    @Autowired
    private EventBillDAO eventBillDAO;

    @Autowired
    private EventStepRelationDAO eventStepRelationDAO;

    @Transactional
    @Override
    public ResponseUtil<Event> addTemplate(Event event,
                                           List<String> processIdList,
                                           List<String> originatorIdList) {


        if (processIdList == null || processIdList.size() == 0) {
            return new ResponseUtil<>(0, "审批阶段不能为空");
        }
        if (originatorIdList == null || originatorIdList.size() == 0) {
            return new ResponseUtil<>(0, "发起人不能为空");
        }
        if (event.getBillCode() == null || event.getBillCode().trim().isEmpty()) {
            return new ResponseUtil<>(0, "单据类型不能为空");
        }


        //防止重复插入
        if (templateDAO.selectTemplateByName(event.getEventName()) != null) {
            return new ResponseUtil<>(0, "重复的审批模板名");

        }

        //服务器端自动填入的数据
        event.setId(IDNOUtil.getIDNO());
        event.setIsModel(1);
        event.setCreateDate(new Timestamp(System.currentTimeMillis()));
        //单据类型 ap_event_bill
        /* 若审批模板 isActive == 1 为启用状态，
                先检查数据库中该模板的单据类型是否已被其他模板占用（一个单据类型只能由一个模板），
                如果已被占用，报错返回；
                否则插入。
           若审批模板 isActive == 0  为不启用状态，则直接插入模板 */
        ResponseUtil responseUtil = isEventBillUsed(event);
        if (responseUtil.getStatus() == 0) return responseUtil;

        EventBill eventBill;
        eventBill = new EventBill();
        eventBill.setId(IDNOUtil.getIDNO());
        eventBill.setHid(event.getId());
        eventBill.setIsModel(0);
        eventBill.setBillCode(event.getBillCode());
        eventBill.setBillName("采购申请立项");
        eventBillDAO.insertEventBill(eventBill);
        //添加对应的发起人到ap_event_creator 发起人表
        List<EventCreator> eventCreatorList = new ArrayList<>();
        EventCreator eventCreator;
        Auditor originator;
        for (int i = 0; i < originatorIdList.size(); i++) {
            originator = auditorDAO.selectAuditorById(originatorIdList.get(i));
            if (originator == null) {
                return new ResponseUtil<>(0, "发起人 " + originatorIdList.get(i) + " 不存在");
            }
            eventCreator = new EventCreator();
            eventCreator.setId(IDNOUtil.getIDNO());
            eventCreator.setHid(event.getId());
            eventCreator.setSortNo(i + 1);
            eventCreator.setCreatorNo(originator.getId());
            eventCreator.setCreatorName(originator.getName());
            eventCreator.setCreatorDepartment(originator.getDepartment());
            System.out.println(eventCreator.toString());
            eventCreatorList.add(eventCreator);
        }
        eventCreatorDAO.insertEventCreator(eventCreatorList);
        //添加对应阶段到ap_event_ap_step_relation
        /*当ap_event为模板时(is_model = 1)，它与ap_step通过ap_event_ap_step_relation表连接，
        即ap_event.idno = ap_event_ap_step_relation.event_idno = ap_step.idno*/
        List<EventStepRelation> eventStepRelationList = new ArrayList<>();
        EventStepRelation eventStepRelation;
        for (int i = 0; i < processIdList.size(); i++) {
            Process process = processDAO.selectProcessById(processIdList.get(i));
            if(process == null){
                return new ResponseUtil<>(0,"审批阶段 "+processIdList.get(i)+" 不存在");
            }
            eventStepRelation = new EventStepRelation();
            eventStepRelation.setId(IDNOUtil.getIDNO());
            eventStepRelation.setEventId(event.getId());
            eventStepRelation.setStepId(processIdList.get(i));
            //对应step次序
            eventStepRelation.setSortNo(i + 1);
            // 总次数
            eventStepRelation.setStepCount(processIdList.size());
            eventStepRelationList.add(eventStepRelation);
        }
        eventStepRelationDAO.insertRelationList(eventStepRelationList);


        int result = templateDAO.insertTemplate(event);
        if (result != 1) {
            return new ResponseUtil<>(0, "审批模板创建失败");
        }
        return new ResponseUtil<>(1, "审批模板创建成功");

    }

    @Override
    public ResponseUtil<List<Event>> findAllActiveTemplate() {

        List<Event> eventList = templateDAO.selectActiveTemplates();

        return new ResponseUtil<>(0, "查询所有激活状态的模板成功", eventList);
    }

    /**
     * 返回处于激活状态的所有模板
     *
     * @return
     */
    @Override
    public ResponseUtil<List<Event>> findAllTemplate() {

        List<Event> eventList = templateDAO.selectAllTemplates();

        return new ResponseUtil<>(1, "获取所有审批模板成功", eventList);
    }


    @Override
    public ResponseUtil<Map<String,Object>> findTemplate(String templateId) {
        if (templateId == null || templateId.trim().isEmpty()) {
            return new ResponseUtil<>(0, "审批模板编码不能为空");
        }
        Event event = templateDAO.selectTemplateById(templateId);
        List<EventCreator> eventCreatorList = eventCreatorDAO.selectEventCreatorByTemplateId(templateId);
        List<Process> processList = processDAO.selectProcessBelongTemplate(templateId);
        Map<String,Object> map = new HashMap<>();
        map.put("template",event);
        map.put("originatorList",eventCreatorList);
        map.put("processList",processList);

        if (event == null) {
            return new ResponseUtil<>(1, "该审批模板不存在");
        }
        return new ResponseUtil<>(1, "审批模板获取成功", map);
    }

    @Transactional
    @Override
    public ResponseUtil<Event> modifyTemplate(Event event,
                                              List<String> originatorIdList,
                                              List<String> processIdList) {
        if (originatorIdList == null || originatorIdList.size() < 1) {
            return new ResponseUtil<>(0, "发起人数量不正确");
        }
        if (event.getId() == null || event.getId().trim().isEmpty()) {
            return new ResponseUtil<>(0, "审批模板编码不能为空");
        }
        //模板名不能一样
        //防止重复插入
        if (templateDAO.selectTemplateByNameAndID(event.getId(),event.getEventName()) != null) {
            return new ResponseUtil<>(0, "重复的审批模板名");
        }

        int result = templateDAO.updateTemplate(event);
        if (result == 0) {
            return new ResponseUtil<>(0, "不存在编码为 " + event.getId() + " 的审批模板");
        }

        // 修改发起人:先删除，后插入，不使用UPDATE语句是因为修改前后的发起人数可能不一样
        List<EventCreator> eventCreatorList = new ArrayList<>();
        EventCreator eventCreator;
        Auditor originator;
        for (int i = 0; i < originatorIdList.size(); i++) {
            System.out.println("out "+originatorIdList.get(i));
            originator = auditorDAO.selectAuditorById(originatorIdList.get(i));
            if (originator == null) {
                return new ResponseUtil<>(0, "发起人 " + originatorIdList.get(i) + " 不存在");
            }
            eventCreator = new EventCreator();
            eventCreator.setId(IDNOUtil.getIDNO());
            eventCreator.setHid(event.getId());
            eventCreator.setSortNo(i + 1);
            eventCreator.setCreatorNo(originator.getId());
            eventCreator.setCreatorName(originator.getName());
            eventCreator.setCreatorDepartment(originator.getDepartment());
            System.out.println(eventCreator.toString());
            eventCreatorList.add(eventCreator);
        }
        eventCreatorDAO.deleteEventCreatorByEventId(event.getId());
        eventCreatorDAO.insertEventCreator(eventCreatorList);
        //修改模板的阶段：先删除后插入
        List<EventStepRelation> eventStepRelationList = new ArrayList<>();
        EventStepRelation eventStepRelation;
        for (int i = 0; i < processIdList.size(); i++) {
            eventStepRelation = new EventStepRelation();
            eventStepRelation.setId(IDNOUtil.getIDNO());
            eventStepRelation.setEventId(event.getId());
            eventStepRelation.setStepId(processIdList.get(i));
            //对应step次序
            eventStepRelation.setSortNo(i + 1);
            // 总次数
            eventStepRelation.setStepCount(processIdList.size());
            eventStepRelationList.add(eventStepRelation);
        }
        eventStepRelationDAO.deleteRelationByEventId(event.getId());
        eventStepRelationDAO.insertRelationList(eventStepRelationList);


        /*
        修改单据类型
        因为目前只有 "采购申请立项" 这一单据类型，
        所以如果该单据类型已被处于激活状态的模板占用，则直接抛出错误
        实际上不需要修改任何数据
         */
        ResponseUtil responseUtil = isEventBillUsed(event);
        if (responseUtil.getStatus() == 0) return responseUtil;

        return new ResponseUtil<>(1, "审批模板修改成功");
    }

    @Transactional
    @Override
    public ResponseUtil<Event> removeTemplate(String templateId) {
        if (templateId == null || templateId.trim().isEmpty()) {
            return new ResponseUtil<>(0, "审批模板编码不能为空");
        }
        //删除模板基本信息
        int result = templateDAO.deleteTemplateById(templateId);
        if (result == 0) {
            return new ResponseUtil<>(0, "不存在编码为 " + templateId + " 的审批模板");
        }
        //删除审批模板-审批阶段
        eventStepRelationDAO.deleteRelationByEventId(templateId);
        //删除单据类型
        eventBillDAO.deleteEventBillByEventId(templateId);
        //删除发起人
        eventCreatorDAO.deleteEventCreatorByEventId(templateId);
        return new ResponseUtil<>(1, "审批模板 " + templateId + " 删除成功");
    }

    /**
     * <p>
     *     如果 event 的 isActive == 1
     *     如果 pur_apply_pre 此单据类型已被占用，则返回占用此单据类型的 模板，否则null
     *
     * </p>
     * @param event
     * @return the Event which already use pur_apply_pre,null if pur_apply_pre
     */
    private ResponseUtil<Event> isEventBillUsed(Event event) {
        if (event.getIsActive() == 1) {
            // 现在 系统中只存在 billCode 为 pur_apply_pre 的单据类型，因此可以做如下判断
            if (!event.getBillCode().equals("pur_apply_pre")) {
                return new ResponseUtil<>(0, "单据类型错误");
            }
            event = templateDAO.selectActiveTemplateByBillCode("pur_apply_pre");
            if (event != null) {
                return new ResponseUtil<>(0, "该单据类型已审批模板"+event.getId()+"占用",event);
            }
        }
        return new ResponseUtil<>(1, "单据类型正确");
    }
}
