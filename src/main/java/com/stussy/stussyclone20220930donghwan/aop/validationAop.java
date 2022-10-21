package com.stussy.stussyclone20220930donghwan.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class validationAop {

//    @Pointcut("execution(* com.stussy.stussyclone20220930donghwan..*Api.*(..))")
//    private void executionPointCut() {}
    @Pointcut("@annotation(com.stussy.stussyclone20220930donghwanghwan.aop.annotation.ValidAspect)")
    private void annotaionPointCut() {}

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for (Object arg : args) {
            System.out.println(arg);
            if (arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
            if (arg.getClass() == BindingResult.class) {

            }
        }

            if (bindingResult.hasErrors()) {
                Map<String, String> errorMap = new HashMap<String, String>();

                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
           for (FieldError fieldError : fieldErrors) {
               System.out.println("필드명:" + fieldError.getField());
               System.out.println("에러 메세지:" + fieldError.getDefaultMessage());
               errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new CustomValidationException("Validation Error", errorMap);

        }
        Object result = null;
        result = joinPoint.proceed();

        return result;

    }

}
