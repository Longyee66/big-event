package com.bigevent.annotation;

import com.bigevent.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { StateValidation.class})//指定校验规则的类
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //校验失败后提示信息
    String message() default "{state参数值只能是已发布或草稿}";
    //提示分组
    Class<?>[] groups() default {};
    //负载，获取到state 注解的其他信息
    Class<? extends Payload>[] payload() default {};
}
