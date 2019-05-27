package com.prd.approval.dao;

import com.prd.approval.entity.Event;
import com.prd.approval.entity.EventCreator;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
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

    Map<String, Object> selectEventDetailByMessageId(String messageId);

    Map<String, Object> selectEventDetailByStepStaffId(String stepStaffId);

    Map<String, Object> selectEventAndCreatorAndAllProcessAndAuditor(String eventId);

    List<Event> selectRejectEventByOriginator(String originatorId);

    default List<Map<String, Object>> selectEventByCase(String billNo, String creator, String status) {
        List<Map<String, Object>> mapList = this.selectEventAndOriginatorByCase(billNo, creator, status);
        System.out.println(mapList);
        List<Map<String, Object>> finalList = new LinkedList<>();
        List<EventCreator> eventCreatorList;
        if (creator != null) {
            for (Map<String, Object> map : mapList) {
                boolean containCreator = false;
                eventCreatorList = (List<EventCreator>) map.get("creatorList");
                for (EventCreator eventCreator : eventCreatorList) {
                    if (eventCreator.getCreatorNo().equals(creator)) {
                        containCreator = true;
                        break;
                    }
                }
                if (containCreator) {
                    System.out.println("add: " + map);
                    finalList.add(map);
                }
            }
        } else {
            finalList = mapList;
        }
        return finalList;
    }

    Event selectEventById(String eventId);

    List<Map<String,Object>> selectTemp(@Param("userId")String userId,
                                  @Param("status") String status);
}
