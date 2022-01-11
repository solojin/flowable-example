package com.example.controller;

import com.example.service.StartService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/start")
@RestController
public class StartController {

    @Autowired
    private StartService myService;

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance() {
        myService.startProcess();
    }

    @RequestMapping(value="/getStartTasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName(), task.getExecutionId()));
        }
        return dtos;
    }

    @RequestMapping(value="/getVar", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object getVar(@RequestParam String executionId) {
        return myService.getVar(executionId);
    }

    static class TaskRepresentation {

        private String id;
        private String name;
        private String executionId;

        public TaskRepresentation(String id, String name, String executionId) {
            this.id = id;
            this.name = name;
            this.executionId = executionId;
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

    }
}
