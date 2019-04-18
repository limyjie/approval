/**
 * Author: lin
 * Date: 2019/4/6 17:25
 */
package com.prd.approval.service;

import com.prd.approval.entity.Event;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;

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

    ResponseUtil<Event> findTemplate(String templateId);

    ResponseUtil<Event> modifyTemplate(Event event,List<String> originatorIdList,List<String> processIdList);

    ResponseUtil<Event> removeTemplate(String templateId);
}
