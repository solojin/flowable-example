package com.example.service;

import org.flowable.engine.FormService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.StartFormData;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormModel;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyFormService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @Transactional
    public void startProcess(String proDefId) {
        String outcome = "outStr";
        Map<String, Object> formProp = new HashMap();
        formProp.put("reason", "家里有事");
        formProp.put("startTime", "12345");
        formProp.put("endTime", new Date());
//        formProp.put("validScript", "123456");
        runtimeService.startProcessInstanceWithForm(proDefId, outcome, formProp, "表单任务");
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().orderByTaskCreateTime().desc().list();
    }

    public Object getVarByPro(String proDefId, String proInsId) {
//        StartFormData startForm = formService.getStartFormData(proDefId);
//
//        Map<String, Object> res = new HashMap();
//        for(FormProperty fp : startForm.getFormProperties()) {
//            res.put(fp.getId(), fp.getValue());
//        }
//
//        return res;

        Object re = formService.getRenderedStartForm(proDefId, "myFormEngine");


         FormInfo startForm = runtimeService.getStartFormModel(proDefId, proInsId);

         return startForm;

    }


    public Object getVar(String taskId) {
        formService.getRenderedTaskForm(taskId);


        // task.getId()
        Object res = taskService.getVariables(taskId);
        return res;
    }

}
