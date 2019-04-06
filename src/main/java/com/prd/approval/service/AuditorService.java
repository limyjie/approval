package com.prd.approval.service;


import com.prd.approval.entity.Auditor;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;

public interface AuditorService {

    ResponseUtil<List<Auditor>> findAllAuditor();
}
