package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void SaveOrUpdate(Task user){
        taskRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no task with this id"));
    }

    @Transactional(readOnly = true)
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    @Transactional
    public void delete(Task task){
        taskRepository.delete(task);
    }
}
