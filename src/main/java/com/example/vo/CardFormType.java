package com.example.vo;

import org.flowable.engine.form.AbstractFormType;

public class CardFormType extends AbstractFormType {

    // 定义表单类型的标识符
    @Override
    public String getName() {
        return "card";
    }

    // 把表单中的值转换为实际的对象
    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return propertyValue;
    }

    // 把实际对象的值转换为表单中的值
    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return (String) modelValue;
    }

}
