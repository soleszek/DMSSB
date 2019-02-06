package com.oleszeksylwester.dmssb.DMSSB.factory;

import com.oleszeksylwester.dmssb.DMSSB.enums.RouteStates;
import com.oleszeksylwester.dmssb.DMSSB.enums.TaskStates;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory {

    /*private TaskDao taskDao = DaoProvider.getInstance().getTaskDao();

    public void createTask(Route route){

        String routeState = route.getState();

        if(routeState.equals(RouteStates.NOT_STARTED.getState())){

            Task task = new Task.Builder()
                    .owner(route.getOwner())
                    .assignedTo(route.getResponsibleForChecking())
                    .documentBeingApprovedId(route.getDocumentBeingApproved())
                    .documentBeingApprovedName(route.getDocumentBeingApprovedName())
                    .state(TaskStates.ACTIVE.getState())
                    .dueDate(route.getCheckingDueDate())
                    .completionDate(null)
                    .comments("Please check")
                    .parentId(String.valueOf(route.getId()))
                    .build();

            taskDao.SaveOrUpdate(task);

            Long taskId = taskDao.findBy(task.getId()).getId();
            NameFactory nameFactory = new NameFactory();
            String name = nameFactory.createName(taskId, "task");

            task.setName(name);

            taskDao.SaveOrUpdate(task);

            assignTaskName(task);

        } else if (routeState.equals(RouteStates.CHECKING.getState())){

            Task task = new Task.Builder()
                    .owner(route.getOwner())
                    .assignedTo(route.getResponsibleForApproving())
                    .documentBeingApprovedId(route.getDocumentBeingApproved())
                    .documentBeingApprovedName(route.getDocumentBeingApprovedName())
                    .state(TaskStates.ACTIVE.getState())
                    .dueDate(route.getCheckingDueDate())
                    .completionDate(null)
                    .comments("Please approve")
                    .parentId(String.valueOf(route.getId()))
                    .build();

            taskDao.SaveOrUpdate(task);

            assignTaskName(task);

        }
    }


    private void assignTaskName(Task task) {
        Long taskId = taskDao.findBy(task.getId()).getId();
        NameFactory nameFactory = new NameFactory();
        String name = nameFactory.createName(taskId, "task");

        task.setName(name);

        taskDao.SaveOrUpdate(task);
    }*/

}
