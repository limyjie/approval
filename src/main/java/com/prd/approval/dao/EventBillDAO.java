/**
 * Author: lin
 * Date: 2019/4/15 15:24
 */
package com.prd.approval.dao;

import com.prd.approval.entity.Event;
import com.prd.approval.entity.EventBill;

/**
 *<p></p>
 *
 */
public interface EventBillDAO {

    int insertEventBill(EventBill eventBill);

    EventBill selectEventBillByBillCode(String billCode);

    int deleteEventBillByEventId(String eventId);

    int updateEventBill(EventBill eventBill);


}
