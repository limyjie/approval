/**
 * Author: lin
 * Date: 2019/4/4 14:43
 */
package com.prd.approval.dao;

import com.prd.approval.entity.Auditor;
import com.prd.approval.entity.StepStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StepStaffDAO {

    int insertStaffStaff(StepStaff stepStaff);

    int deleteStepStaffByProcessId(String processId);

    List<StepStaff> selectStepStaffsByProcessId(String processId);

    //StepStaff whetherHaveNewEventToDo(String staffId);

    int updateStepStaffByStepIdAndStaffId(StepStaff stepStaff);

    StepStaff selectByStepIdAndStaffId(@Param("stepId")String processId,
                                                @Param("staffId")String auditorId);


    StepStaff selectByStepStaffId(String stepStaffId);

    int selectStepUnApprovalPersonNumber(String stepId);
}
