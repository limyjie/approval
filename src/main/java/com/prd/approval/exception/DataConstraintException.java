/**
 * Author: lin
 * Date: 2019/5/24 11:22
 */
package com.prd.approval.exception;

/**
 *<p></p>
 *
 */
public class DataConstraintException extends RuntimeException {


    private static final long serialVersionUID = -7059334351821482610L;

    public DataConstraintException() {
        super();
    }

    public DataConstraintException(String s){
        super(s);
    }
}
