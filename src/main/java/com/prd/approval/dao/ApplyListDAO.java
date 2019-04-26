/**
 * Author: lin
 * Date: 2019/4/25 16:40
 */
package com.prd.approval.dao;

import com.prd.approval.entity.ApplyList;

import java.util.List;

/**
 *<p></p>
 *
 */
public interface ApplyListDAO {
    List<ApplyList> selectApplyListByHID(String hid);
}
