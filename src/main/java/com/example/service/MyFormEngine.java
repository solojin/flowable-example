package com.example.service;

import org.flowable.engine.form.StartFormData;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.impl.form.FormEngine;


//@process customFormTypes
public class MyFormEngine implements FormEngine {
    // 表单引擎的名称
    @Override
    public String getName() {
        return "myFormEngine";
    }

    @Override
    public Object renderStartForm(StartFormData startFormData) {
        return startFormData.getFormProperties().get(0).getName();
    }

    @Override
    public Object renderTaskForm(TaskFormData taskFormData) {
        return taskFormData.getFormProperties().get(0).getName();
    }

}
