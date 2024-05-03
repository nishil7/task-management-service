package com.backend.backendnew.service.AdminService.ResourceManagementService;


import com.backend.backendnew.clientPackage.UserClient;
import com.backend.backendnew.model.*;
import com.backend.backendnew.repository.ClaimedTasksRepository;
import com.backend.backendnew.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceMS {

    //UserClient userClient=new UserClient();

    private UserClient userClient=new UserClient();
    ////////////////////////////////////////////////////
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private ClaimedTasksRepository claimedTasksRepository;

    //functions
    public List<Task> getAllUnclaimedTasks() {
        return tasksRepository.getAllUnclaimedTasks();
    }
    public List<Task> getAllDoneTasks() {return tasksRepository.getAllDoneTasks();}
    public List<Task> getAllPendingTasks(){return tasksRepository.getAllPendingTasks();}

    public List<Task> getInProgressTasks(){return tasksRepository.getInProgressTasks();}
    public List<Piechartdata>numberOfDiffrentUsers(){
        List<Piechartdata>m=new ArrayList<>();
        Integer nf=FreeUsers().size();
        Integer nb=BusyUsers().size();
        m.add(new Piechartdata("FreeUsers",nf));
        m.add(new Piechartdata("BusyUsers",nb));
        return m;
    }
    public List<Piechartdata>numberOfDiffrentTasks(){
        List<Piechartdata>m=new ArrayList<>();
        Integer unclaimed=getAllUnclaimedTasks().size();
        Integer done=getAllDoneTasks().size();
        Integer pending=getAllPendingTasks().size();
        Integer inProgress= getInProgressTasks().size();
        m.add(new Piechartdata("Unclaimed Tasks",unclaimed));
        m.add(new Piechartdata("Completed Tasks",done));
        m.add(new Piechartdata("Pending Tasks",pending));
        m.add(new Piechartdata("In Progress Tasks",inProgress));
        return m;
    }

    public List<UserDetail> BusyUsers(){
        List<User> AllUsers=userClient.getUsers();

        List<ClaimedTask>AllClaimedTask=claimedTasksRepository.findAll();
        List<Task>AllTasks=tasksRepository.findAll();

        //Both lists above
        Map<String,String>myMap=new HashMap<>();
        // Populate the userMap
        for(User user :AllUsers){
            myMap.put(user.getUserName(), user.getName());
        }
        //task id and task name mapping
        Map<Integer,String>myMap2=new HashMap<>();
        for(Task task:AllTasks){
            myMap2.put(task.getTasksID(), task.getTaskName());
        }

        /////////////////

        List<UserDetail>answer=new ArrayList<>();
        for(ClaimedTask claimedTask:AllClaimedTask){
            if(claimedTask.getIsActive()==1) {
                UserDetail temp = new UserDetail();
                temp.setUserName(claimedTask.getUserName());
                temp.setTasksID(claimedTask.getTasksID());
                temp.setName(myMap.get(temp.getUserName()));
                temp.setTaskName(myMap2.get(temp.getTasksID()));
                answer.add(temp);
            }
        }
        return answer;
    }

    public List<UserDetail> FreeUsers(){
        List<User> AllUsers=userClient.getUsers();
        List<ClaimedTask>AllClaimedTask=claimedTasksRepository.findAll();
        List<Task>AllTasks=tasksRepository.findAll();

        //task id and task name mapping
//        Map<Integer,String>myMap2=new HashMap<>();
//        for(Task task:AllTasks){
//            myMap2.put(task.getTasksID(), task.getTaskName());
//        }
        Map<String,Integer>myMap3=new HashMap<>();
        for(ClaimedTask claimedTask:AllClaimedTask){
            if(claimedTask.getIsActive()==1) myMap3.put(claimedTask.getUserName(),claimedTask.getTasksID());
        }

        /////////////////

        List<UserDetail>answer=new ArrayList<>();
        for(User user :AllUsers){
            UserDetail temp=new UserDetail();
            temp.setUserName(user.getUserName());
            temp.setName(user.getName());
            if(myMap3.containsKey(user.getUserName())) {
//                temp.setTasksID(myMap3.get(user.getUserName()));
//                temp.setTaskName(myMap2.get(temp.getTasksID()));
            }
            else {
                temp.setTasksID(-1);
                temp.setTaskName("");
                answer.add(temp);
            }
        }
        return answer;
    }

}

