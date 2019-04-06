/**
 * Author: lin
 * Date: 2019/4/3 13:11
 */
package com.prd.approval.utils;

import com.prd.approval.dao.IDNODAO;
import com.prd.approval.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* 从数据库中获取IDNO，获取后将FFNO的值+1
* */
@Component
public class IDNOUtil {

    private static IDNODAO idnodao;

    @Autowired
    private IDNOUtil(IDNODAO idnodao){
        this.idnodao = idnodao;
    }

    public static String getIDNO(){

        int idno = idnodao.selectCurrentIDNO();
        idnodao.updateIDNOAfterSelect();
        return String.valueOf(idno);
    }
}
