package com.prd.approval.dao;

import com.prd.approval.entity.Event;
import org.apache.ibatis.annotations.Param;

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

    /**
     * <p>
     *  用于修改模板
     *  检查是否除 ID 为 templateId 外 有重复的模板名
     * </p>
     * @param templateName
     * @return
     */
    String selectTemplateByNameAndID(@Param("templateId") String templateId,@Param("templateName") String templateName);


    String selectTemplateByName(String name);

    Event selectActiveTemplateByBillCode(String billCode);

    List<Event> selectTodoEventListByUserId(String userId);

}
