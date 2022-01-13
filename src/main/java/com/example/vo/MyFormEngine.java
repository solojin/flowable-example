package com.example.vo;


import org.flowable.engine.form.StartFormData;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.impl.form.FormEngine;

public class MyFormEngine implements FormEngine {
    @Override
    public String getName() {
        return "MyFormEngine";
    }

    // 实际处理逻辑根据具体业务而定
    @Override
    public Object renderStartForm(StartFormData startFormData) {
        return "MyStartData";
    }

    // 实际处理逻辑根据具体业务而定
    @Override
    public Object renderTaskForm(TaskFormData taskFormData) {
        return "MyTaskData";
    }
}
