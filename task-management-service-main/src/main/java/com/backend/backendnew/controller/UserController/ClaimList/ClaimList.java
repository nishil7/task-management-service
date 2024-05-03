package com.backend.backendnew.controller.UserController.ClaimList;

import com.backend.backendnew.AuthTokenVerifyPackage.AuthTokenService;
import com.backend.backendnew.model.ClaimedTask;
import com.backend.backendnew.model.Task;

import com.backend.backendnew.service.UserService.ClaimList.ClaimListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user/taskManagement/claimList")
public class ClaimList {
    private final ClaimListService userService;
    AuthTokenService authTokenService=new AuthTokenService();


    public ClaimList(ClaimListService userService) {
        this.userService = userService;
    }

    @PostMapping("/claimTask")
    public ResponseEntity<ClaimedTask> createClaimTask(@RequestBody ClaimedTask claimedTask,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        claimedTask.setUserName(authTokenService.getUsername(token));
        ClaimedTask claimTask = userService.createClaimTask(claimedTask);
        if(claimTask != null){
            return ResponseEntity.ok(claimTask);
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
        }
    }

    @PatchMapping("/unclaimTask")
    public ResponseEntity<String> updateClaimTask(@RequestBody ClaimedTask unclaimedTask,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        unclaimedTask.setUserName(authTokenService.getUsername(token));
        int unclaimTask = userService.updateClaimTask(unclaimedTask, 1);
        if(unclaimTask > 0){
            return new ResponseEntity<>("Success message", HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
        }
    }
    @PatchMapping("/askForApprovalTask")
    public ResponseEntity<String> askForApproval(@RequestBody ClaimedTask unclaimedTask,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        unclaimedTask.setUserName(authTokenService.getUsername(token));
        int askForApproval = userService.updateClaimTask(unclaimedTask, 3);
        if(askForApproval > 0){
            return new ResponseEntity<>("Success message", HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
        }
    }

    @GetMapping("/getAvailableTasks")
    public ResponseEntity<List<Task>> getAvailableTasks(@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        return ResponseEntity.ok(userService.getAvailableTasks());
    }

    @GetMapping("/getTaskById/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable int taskId,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        Task task = userService.getTaskById(taskId);
        if(task != null){
            return ResponseEntity.ok(task);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
    @GetMapping("/getClaimedTask")
    public ResponseEntity<ClaimedTask> getClaimedTasks(@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        String userName = authTokenService.getUsername(token);
        return ResponseEntity.ok(userService.getClaimedTask(userName));
    }
    @GetMapping("/getClaimedTaskDetail")
    public ResponseEntity<Task> getClaimedTaskDetail(@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,0)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        String userName = authTokenService.getUsername(token);
        return ResponseEntity.ok(userService.getClaimedTaskDetail(userName));
    }

}