package com.example.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    public void deleteDeploy() {
        String deploymentId = "";
        repositoryService.deleteDeployment(deploymentId, true);
    }
    public void getXML() {
        String processDefinitionId = "";
        String resourceName = "";
        // 根据processDefinitionId查询
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        // 读取资源流
        InputStream stream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);
        // 读取到的资源流再返回到前端
    }

    public void getImage() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("singleTask")
                .singleResult();

        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), diagramResourceName);
    }

    public List<ProcessDefinition> getDeployList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        return list;
    }


    public long deploy() {
        // 从前端接收到的XML字符串
        // 此处省去xml具体内容，可参考1.1的single-task.bpmn20.xml示例
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"\n" +
                "        xmlns:flowable=\"http://flowable.org/bpmn\"\n" +
                "        targetNamespace=\"Examples\">\n" +
                "\n" +
                "    <process id=\"singleTask2\" name=\"The One Task Process\">\n" +
                "        <startEvent id=\"theStart\" />\n" +
                "        <sequenceFlow id=\"flow1\" sourceRef=\"theStart\" targetRef=\"theTask\" />\n" +
                "        <userTask id=\"theTask\" name=\"my task\" flowable:assignee=\"zhangsan\" />\n" +
                "        <sequenceFlow id=\"flow2\" sourceRef=\"theTask\" targetRef=\"theEnd\" />\n" +
                "        <endEvent id=\"theEnd\" />\n" +
                "    </process></definitions>";
        // 创建部署构建器
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        // 执行部署
        deploymentBuilder.addString("single-task2.bpmn20.xml", text).deploy();
        // 验证部署
        long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("singleTask2").count();
        // count等于1，则说明部署成功
        return count;
    }

    @Transactional
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }


}