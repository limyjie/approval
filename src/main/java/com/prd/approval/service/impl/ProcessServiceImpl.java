/**
 * Author: lin
 * Date: 2019/3/27 10:21
 */
package com.prd.approval.service.impl;

import com.prd.approval.dao.AuditorDAO;
import com.prd.approval.dao.IDNODAO;
import com.prd.approval.dao.ProcessDAO;
import com.prd.approval.dao.StepStaffDAO;
import com.prd.approval.entity.Auditor;
import com.prd.approval.entity.Process;
import com.prd.approval.entity.StepStaff;
import com.prd.approval.service.ProcessService;
import com.prd.approval.utils.IDNOUtil;
import com.prd.approval.utils.LogUtil;
import com.prd.approval.utils.ResponseUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.StringValueExp;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessDAO processDAO;

    @Autowired
    private StepStaffDAO stepStaffDAO;

    @Autowired
    private AuditorDAO auditorDAO;


    /**
     * <p>添加审批阶段，可用builder 改进</p>
     *
     * @param process
     * @param auditorIdList
     * @return
     */
    public ResponseUtil<Process> addProcess(Process process, List<Integer> auditorIdList) {
        if (auditorIdList.size() < process.getTimesCount()) {
            return new ResponseUtil<>(0, "审核人数应大于等于通过次数");
        }
        process.setId(IDNOUtil.getIDNO());
        /*status 1=新建，2=正在执行(开启),3=通过，4 = 被拒绝*/
        process.setStatus("1");
        process.setStepCount(process.getStepCount());
        process.setStepName(process.getStepName());
        process.setStepType(2);
        process.setStepDescription(process.getStepDescription());
        process.setTimesCount(process.getTimesCount());
        process.setTimesRemain(process.getTimesCount());
        process.setCreateDate(new Timestamp(System.currentTimeMillis()));
        process.setCreateBy(process.getCreateBy());

        ResponseUtil<Process> errorResponse
                = addStepStaffListByAuditorId(process.getId(), auditorIdList);

        if (errorResponse != null) return errorResponse;

        processDAO.insertProcess(process);

        return new ResponseUtil<>(1, "添加阶段成功");
    }

    @Override
    public ResponseUtil<Map<String,Object>> getProcess(String processId) {

        Process process = processDAO.selectProcessById(processId);
        if (process == null) {
            return new ResponseUtil<>(0, "审批阶段 " + processId + " 不存在");
        }
        //该审批阶段的审核人
        List<StepStaff> stepStaffList = stepStaffDAO.selectStepStaffsByProcessId(processId);

        Map<String,Object> map = new HashMap<>();
        map.put("process",process);
        map.put("auditorList",stepStaffList);

        return new ResponseUtil<>(1, "审批阶段 " + processId + " 查询成功", map);
    }

    @Override
    public ResponseUtil<List<Process>> getAll() {

        List<Process> processList = processDAO.selectAllProcess();
        if (processList == null) {
            return new ResponseUtil<>(0, "查询阶段失败");
        }
        return new ResponseUtil<>(1, "查询阶段成功", processList);
    }


    /**
     * <p>
     * 删除审批阶段
     * 1 删除ap_step
     * 2 删除ap_step_staff
     * </p>
     *
     * @param processId
     * @return
     */
    @Transactional
    @Override
    public ResponseUtil<List<Process>> removeProcess(String processId) {

        int result = 0;
        result = processDAO.deleteProcessById(processId);
        if (result != 1) {
            return new ResponseUtil<>(0, "阶段 " + processId + " 不存在");
        }
        result = stepStaffDAO.deleteStepStaffByProcessId(processId);

        return new ResponseUtil<>(1, "删除阶段成功");
    }

    /**
     * <p>
     * 修改审批阶段:
     * 1.修改 ap_step   阶段信息
     * 2.修改 ap_step_staff  阶段审批人
     * 2.1 根据 ap_step.idno 找到对应的ap_step_staff,将其删除
     * 2.2 根据 auditorIdList 重新添加到ap_step_staff
     * 缺点：耗时长，stepStaff idno 改变
     * <p>
     * 改进：（不可行）
     * 1.修改 ap_step   阶段信息
     * 2.用 auditorIdList.size 条 update 语句修改 ap_step_staff  阶段审批人
     * 缺点：修改前后的审批人数可能不一样，不能用update语句
     * </p>
     *
     * @param process
     * @param auditorIdList
     * @return
     */
    @Transactional
    @Override
    public ResponseUtil<Process> modifyProcess(Process process, List<Integer> auditorIdList) {
        if (process.getId() == null) {
            return new ResponseUtil<>(0, "审批阶段编码不能为空");
        }
        if(auditorIdList.size()<process.getTimesCount()){
            return new ResponseUtil<>(0, "审核人数应大于等于通过次数");
        }
        int result = 0;
        // 1
        result = processDAO.updateProcess(process);
        if (result == 0) {
            return new ResponseUtil<>(0, "审批阶段编码不存在");
        }
        // 2.1

        stepStaffDAO.deleteStepStaffByProcessId(process.getId());
        //2.2
        ResponseUtil<Process> errorResponse
                = addStepStaffListByAuditorId(process.getId(), auditorIdList);

        if (errorResponse != null) return errorResponse;

        return new ResponseUtil<Process>(1, "审批阶段修改成功");
    }

    private ResponseUtil<Process> addStepStaffListByAuditorId(String processId, List<Integer> auditorIdList) {
        StepStaff stepStaff = new StepStaff();
        for (Integer id : auditorIdList) {
            Auditor auditor = auditorDAO.selectAuditorById(String.valueOf(id));
            if (auditor == null) {
                return new ResponseUtil<>(1, "找不到编号为" + id + "的审核人");
            }
            stepStaff.setId(IDNOUtil.getIDNO());
            stepStaff.sethId(processId);
            stepStaff.setStatus("1");
            //sort_no number(5),排序号
            stepStaff.setStaffNo(String.valueOf(id));
            stepStaff.setStaffName(auditor.getName());
            stepStaff.setApResult("1");
            stepStaff.setStaffDepa(auditor.getDepartment());
            stepStaff.setStaffDepaName(auditorDAO.selectDepartmentNameById(auditor.getDepartment()));
            System.out.println("out " + stepStaff.getStaffDepaName());
            stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));

            stepStaffDAO.insertStaffStaff(stepStaff);
        }
        return null;
    }
}
