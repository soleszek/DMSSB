package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    private ModelAndView displayAllTasks(){
        ModelAndView mov = new ModelAndView();

        List<Task> allTasks = taskService.findAll();

        mov.addObject("allTasks", allTasks);
        mov.setViewName("tasks");

        return mov;
    }

    @GetMapping("/task/{taskId}")
    private ModelAndView displayTask(@PathVariable("taskId") Long taskId){
        ModelAndView mov = new ModelAndView();

        Task task = taskService.findById(taskId);

        mov.addObject("task", task);
        mov.setViewName("task");

        return mov;
    }

    @GetMapping("/task/completed/{taskId}")
    private ModelAndView completeTask(@PathVariable("taskId") Long taskId){
        ModelAndView mov = new ModelAndView();

        Task task = taskService.findById(taskId);

        mov.addObject("task", task);
        mov.setViewName("task");

        return mov;
    }
}
