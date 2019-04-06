/**
 * Author: lin
 * Date: 2019/4/3 11:21
 */
package com.prd.approval.validation;

import com.prd.approval.entity.Process;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProcessValid implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Process.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o == null) {
            errors.rejectValue("", null, "ProcessValid阶段不能为空");
            return;
        }
        Process process = (Process) o;
        if (StringUtils.isEmpty(process.getStepName())) {
            errors.reject("stepName", null, "ProcessValid阶段名不能为空");
        }
    }
}

/*
*
*  List<ObjectError> objectErrors = errors.getAllErrors();
        for(ObjectError objectError:objectErrors){
            String key = null,msg = null;
            if(objectError instanceof FieldError){
                FieldError fieldError = (FieldError)objectError;
                key = ((FieldError) objectError).getField();
            }else{
                key = objectError.getObjectName();
            }
            msg = objectError.getDefaultMessage();
            LogUtil.log(key+ " "+msg);
        }

        */
