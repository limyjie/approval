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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.StringValueExp;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


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
     * @param process
     * @param auditorIdList
     * @return
     */
    public ResponseUtil<Process> addProcess(Process process,List<Integer> auditorIdList) {

        process.setId(IDNOUtil.getIDNO());
        /*status 1=新建，2=正在执行(开启),3=通过，4 = 被拒绝*/
        process.setStatus("1");
        process.setStepCount(process.getStepCount());
        process.setStepName(process.getStepName());
        process.setStepType(2);
        process.setStepDescription(process.getStepDescription());
        process.setTimesCount(process.getTimesCount());
        process.setTimesRemain(process.getTimesRemain());
        process.setCreateDate(new Timestamp(System.currentTimeMillis()));
        process.setCreateBy(process.getCreateBy());

        StepStaff stepStaff = new StepStaff();

        for(Integer id :auditorIdList){
            Auditor auditor =  auditorDAO.selectAuditorById(String.valueOf(id));
            if(auditor == null){
                return new ResponseUtil<>(1,"找不到编号为"+id+"的审核人");
            }
            stepStaff.setId(IDNOUtil.getIDNO());
            stepStaff.sethId(process.getId());
            stepStaff.setStatus("1");
            //sort_no number(5),排序号
            stepStaff.setStaffNo(String.valueOf(id));
            stepStaff.setStaffName(auditor.getName());
            stepStaff.setApResult("1");
           // stepStaff.setStaffDepaName();
            stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));

            stepStaffDAO.insertStaffStaff(stepStaff);
        }


        processDAO.insertProcess(process);

        return new ResponseUtil<>(1,"添加阶段成功");
    }

    @Override
    public ResponseUtil<List<Process>> getAll() {

        List<Process> processList = processDAO.selectAllProcess();
        if(processList==null) {
            return new ResponseUtil<>(0,"查询阶段失败");
        }
        return new ResponseUtil<>(1,"查询阶段成功",processList);
    }
}
