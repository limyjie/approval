package com.prd.approval.dao;

import com.prd.approval.entity.ApplyHeader;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ApplyHeaderDAO {
    Map<String,Object> selectApplyHeaderByApplyNo(String applyNo);

    int updateStatusByApplyNo(@Param("status")String status,
                              @Param("applyNo")String applyNo);


}
