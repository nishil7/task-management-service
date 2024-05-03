package com.backend.backendnew.service.AdminService.TaskManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.backendnew.repository.TasksRepository;
import com.backend.backendnew.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskMS {
    @Autowired
    private TasksRepository tasksRepository;

    //functions
    public List<Task> getAllTasks(long status, long priority) {
        List<Task> allTasks = tasksRepository.getAllTasks();
        List<Task> finalList = new ArrayList<>();

        for (Task t : allTasks) {
            // Skip tasks that don't meet the criteria
            if ((status != 0 && status != t.getStatusID()) || (priority != 0 && priority != t.getPriorityID())) {
                continue;
            }
            // Add filtered the task to the finalList
            finalList.add(t);
        }
        return finalList;
    }

    public Task getTaskbyId(int id) {
        return tasksRepository.getTaskById(id);
    }

    public Task addnewTask(Task newTask){
        return tasksRepository.save(newTask);
    }


    public Task editTask(long id, Task changedTask) {
        Optional<Task> optionalTask = tasksRepository.findById((int) id);

        Task currTask = optionalTask.orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));

        currTask.setTaskName(changedTask.getTaskName());
        currTask.setDescription(changedTask.getDescription());
        currTask.setCompletedBy(changedTask.getCompletedBy());
        currTask.setStatusID(changedTask.getStatusID());
        currTask.setPriorityID(changedTask.getPriorityID());

        tasksRepository.save(currTask);
        return currTask;
    }


}
