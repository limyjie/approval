/**
 * Author: lin
 * Date: 2019/4/15 12:22
 */
package com.prd.approval.entity;

/**
 *<p>
 *
 *   当ap_event为模板时(is_model = 1)，它与ap_step通过ap_event_ap_step_relation表连接
 *   即ap_event.idno = ap_event_ap_step_relation.event_idno = ap_step.idno
 *</p>
 *
 */
public class EventStepRelation {
    private String id;
    private String stepId;
    private String eventId;
    //对应step的次序
    private Integer sortNo;
    //step的总数
    private Integer stepCount;

    public EventStepRelation() {
    }

    @Override
    public String toString() {
        return "EventStepRelation{" +
                "id='" + id + '\'' +
                ", stepId='" + stepId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", sortNo=" + sortNo +
                ", stepCount=" + stepCount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }
}

