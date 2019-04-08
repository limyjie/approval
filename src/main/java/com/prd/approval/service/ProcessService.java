/**
 * Author: lin
 * Date: 2019/3/25 17:37
 */
package com.prd.approval.service;

import com.prd.approval.entity.Process;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;

public interface ProcessService {
    ResponseUtil<Process> addProcess(Process process,List<Integer> auditorIdList);

    ResponseUtil<Process> getProcess(String processId);

    ResponseUtil<List<Process>> getAll();

    ResponseUtil<List<Process>> removeProcess(String processId);

    ResponseUtil<Process> modifyProcess(Process process,List<Integer> auditorIdList);
}
