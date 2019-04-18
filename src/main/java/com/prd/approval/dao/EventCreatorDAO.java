/**
 * Author: lin
 * Date: 2019/4/15 12:30
 */
package com.prd.approval.dao;

import com.prd.approval.entity.EventCreator;

import java.util.List;

/**
 *<p>发起人</p>
 *
 */
public interface EventCreatorDAO {

    int insertEventCreator(List<EventCreator> eventCreatorList);

    int deleteEventCreatorByEventId(String eventId);


}
