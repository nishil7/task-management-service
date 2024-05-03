package com.backend.backendnew.controller.AdminController.TaskManagement;

import com.backend.backendnew.AuthTokenVerifyPackage.AuthTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.backendnew.service.AdminService.TaskManagementService.TaskMS;
import com.backend.backendnew.model.Task;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/taskManagement")
public class TaskManagement {
    private final TaskMS taskMS;

    AuthTokenService authTokenService=new AuthTokenService();

    public TaskManagement(TaskMS taskMS) {
        this.taskMS = taskMS;
    }
    //    GetAllTasks API
    @GetMapping("/{status}/{priority}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable long status, @PathVariable long priority, @RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        return ResponseEntity.ok(taskMS.getAllTasks(status,priority));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskbyId(@PathVariable int id,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
        return ResponseEntity.ok(taskMS.getTaskbyId(id));
    }


    @PostMapping("/addTask")
    public ResponseEntity<Task> addnewTask(@RequestBody Task newTask,@RequestHeader(name = "Authorization") String token){
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
        return ResponseEntity.ok(taskMS.addnewTask(newTask));
    }

    @PutMapping("/editTask/{id}")
    public ResponseEntity<Task> editTask(@PathVariable long id,@RequestBody Task changedTask,@RequestHeader(name = "Authorization") String token){
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
        return ResponseEntity.ok(taskMS.editTask(id,changedTask));

        //return ResponseEntity.ok(taskMS.editTask(id,changedTask));
    }
}
