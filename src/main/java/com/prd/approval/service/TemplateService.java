/**
 * Author: lin
 * Date: 2019/4/6 17:25
 */
package com.prd.approval.service;

import com.prd.approval.entity.Event;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;
import java.util.Map;

/**
 *<p>
 *     审批模板
 *
 *</p>
 *
 */
public interface TemplateService {
    ResponseUtil<Event> addTemplate(Event event,
                                    List<String> processIdList,
                                    List<String> originatorIdList);

    ResponseUtil<List<Event>> findAllActiveTemplate();

    ResponseUtil<List<Event>> findAllTemplate();

    ResponseUtil<Map<String,Object>> findTemplate(String templateId);

    ResponseUtil<Event> modifyTemplate(Event event,List<String> originatorIdList,List<String> processIdList);

    ResponseUtil<Event> removeTemplate(String templateId);

    ResponseUtil<List<Map<String,Object>>> getEventByStatusAndUser(String status,String userId);
}
