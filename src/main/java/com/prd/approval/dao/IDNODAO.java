package com.prd.approval.dao;

/*
*
* 用于获取各个表自增的IDNO
*
* */
public interface IDNODAO {
    int selectCurrentIDNO();
    int updateIDNOAfterSelect();
}
