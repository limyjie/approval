package com.prd.approval.dao;

import com.prd.approval.entity.ApplyHeader;

import java.util.Map;

public interface ApplyHeaderDAO {
    Map<String,Object> selectApplyHeaderByApplyNo(String applyNo);
}
