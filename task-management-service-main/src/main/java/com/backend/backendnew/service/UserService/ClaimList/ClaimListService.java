package com.backend.backendnew.service.UserService.ClaimList;

import com.backend.backendnew.model.ClaimedTask;
import com.backend.backendnew.model.Task;
import com.backend.backendnew.repository.ClaimedTasksRepository;
import com.backend.backendnew.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimListService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private ClaimedTasksRepository claimedTasksRepository;



    public List<Task> getAvailableTasks() {
        return tasksRepository.getAvailableTasks();
    }
    public ClaimedTask getClaimedTask(String userName) {
        return claimedTasksRepository.getClaimedTask(userName);

    }
    public Task getClaimedTaskDetail(String userName) {
        ClaimedTask claimedTask = getClaimedTask(userName);
        if(claimedTask == null){
            return null;
        }
        return tasksRepository.getTaskById(claimedTask.getTasksID());

    }
    public Task getTaskById(int taskId) {
        return tasksRepository.getTaskById(taskId);
    }
    public ClaimedTask createClaimTask(ClaimedTask claimedTask) {
        ClaimedTask savedTask = null;
        List<ClaimedTask> AlreadyClaimedTask= claimedTasksRepository.getAlreadyClaimedTask(claimedTask.getUserName());
        List<ClaimedTask> AlreadyAssignedTask= claimedTasksRepository.getAlreadyAssignedTask(claimedTask.getTasksID());
        if(!AlreadyClaimedTask.isEmpty() || !AlreadyAssignedTask.isEmpty()){
            return savedTask;
        }
        try {
            savedTask = claimedTasksRepository.save(claimedTask);
            tasksRepository.updateTask(claimedTask.getTasksID(), 2);
        } catch (DataIntegrityViolationException e) {
            savedTask = null;
        }
        return savedTask;
    }

    public int updateClaimTask(ClaimedTask unclaimedTask, int statusId) {
        int success = 0;
        try{
            success = claimedTasksRepository.updateClaimedTask(unclaimedTask.getUserName(), unclaimedTask.getTasksID());
            tasksRepository.updateTask(unclaimedTask.getTasksID(), statusId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }
}