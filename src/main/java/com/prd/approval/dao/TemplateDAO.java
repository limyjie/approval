package com.prd.approval.dao;

import com.prd.approval.entity.Event;

import java.util.List;

public interface TemplateDAO {
    int insertTemplate(Event event);

    List<Event> selectAllTemplates();
    List<Event> selectActiveTemplates();

    /**
     * <p>用户登陆时检查是否有需要审核的事件</p>
     * @return
     */
    List<Event> selectEventByUserId();

    Event selectTemplateById(String templateId);

    int deleteTemplateById(String templateId);

    int updateTemplate(Event event);

    String selectTemplateByName(String templateName);

    Event selectActiveTemplateByBillCode(String billCode);
}
