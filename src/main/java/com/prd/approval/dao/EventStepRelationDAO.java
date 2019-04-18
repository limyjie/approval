/**
 * Author: lin
 * Date: 2019/4/15 12:56
 */
package com.prd.approval.dao;

import com.prd.approval.entity.EventStepRelation;

import java.util.List;

/**
 *<p></p>
 *
 */
public interface EventStepRelationDAO {

    int insertRelationList(List<EventStepRelation> eventStepRelationList);

    int deleteRelationByEventId(String eventId);
}
