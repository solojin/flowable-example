package com.example.controller;

import com.example.service.MyFormService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/form")
@RestController
public class FormController {

    @Autowired
    private MyFormService myService;

    @RequestMapping(value="/process", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public void startProcessInstance(@RequestParam String proDefId) {
        myService.startProcess(proDefId);
    }

    @RequestMapping(value="/getVar", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object getVar(@RequestParam String taskId) {
        return myService.getVar(taskId);
    }

    @RequestMapping(value="/getVarByPro", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object getVarByPro(@RequestParam String proDefId, @RequestParam String proInsId) {
        return myService.getVarByPro(proDefId, proInsId);
    }

    @RequestMapping(value="/getTasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName(), task.getExecutionId(), sd.format(task.getCreateTime())));
        }
        return dtos;
    }

    static class TaskRepresentation {

        private String id;
        private String name;
        private String executionId;
        private String time;

        public TaskRepresentation(String id, String name, String executionId, String time) {
            this.id = id;
            this.name = name;
            this.executionId = executionId;
            this.time = time;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getExecutionId() {
            return executionId;
        }
        public void setExecutionId(String executionId) {
            this.executionId = executionId;
        }
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

    }
}
