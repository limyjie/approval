package com.prd.approval.dao;

import com.prd.approval.entity.ApplyHeader;

public interface ApplyHeaderDAO {
    ApplyHeader selectApplyHeaderByApplyNo(String applyNo);
}
