package com.prd.approval.dao;

import com.prd.approval.entity.Event;
import org.apache.ibatis.annotations.Param;
import sun.rmi.transport.ObjectTable;

import java.util.List;
import java.util.Map;

public interface TemplateDAO {
    int insertTemplate(Event event);

    List<Event> selectAllTemplates();

    List<Event> selectActiveTemplates();


    Map<String, Object> selectEventAndCreatorByEventId(String eventId);

    Event selectTemplateById(String templateId);

    int deleteTemplateById(String templateId);

    int updateTemplate(Event event);

    /**
     * <p>
     * 用于修改模板
     * 检查是否除 ID 为 templateId 外 有重复的模板名
     * </p>
     *
     * @param templateName
     * @return
     */
    String selectTemplateByNameAndID(@Param("templateId") String templateId, @Param("templateName") String templateName);


    String selectTemplateByName(String name);

    Event selectActiveTemplateByBillCode(String billCode);

    List<Event> selectTodoEventListByUserId(String userId);

    List<Event> selectEventByStatusAndUser(@Param("status") String status,
                                           @Param("userId") String userId);

    List<Map<String, Object>> selectEventAndOriginatorByCase(@Param("billNo") String billNo,
                                                             @Param("creator") String creator,
                                                             @Param("status") String eventStatus);

    Map<String,Object> selectEventAndCreatorAndCurrentProcess(String eventId);

    Map<String,Object> selectEventAndCreatorAndAllProcessAndAuditor(String eventId);
}
