package com.bigevent.validation;

import com.bigevent.annotation.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class StateValidation implements ConstraintValidator<State,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //校验规则
        if(s==null){
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿")){
            return true;
        }
        return false;
    }
}
