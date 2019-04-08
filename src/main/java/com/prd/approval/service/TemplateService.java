/**
 * Author: lin
 * Date: 2019/4/6 17:25
 */
package com.prd.approval.service;

import com.prd.approval.entity.Event;
import com.prd.approval.utils.ResponseUtil;

/**
 *<p>
 *     审批模板
 *
 *</p>
 *
 */
public interface TemplateService {
    ResponseUtil<Event> addTemplate(Event event);
}
