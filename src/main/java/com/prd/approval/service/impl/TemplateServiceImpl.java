/**
 * Author: lin
 * Date: 2019/4/6 17:29
 */
package com.prd.approval.service.impl;

import com.prd.approval.dao.TemplateDAO;
import com.prd.approval.entity.Event;
import com.prd.approval.service.TemplateService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *<p></p>
 *
 */

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDAO templateDAO;

    @Override
    public ResponseUtil<Event> addTemplate(Event event) {

        

        templateDAO.insertTemplate(event);
        return null;
    }
}
