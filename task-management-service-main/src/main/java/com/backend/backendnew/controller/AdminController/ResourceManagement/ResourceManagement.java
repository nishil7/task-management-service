package com.backend.backendnew.controller.AdminController.ResourceManagement;


import com.backend.backendnew.AuthTokenVerifyPackage.AuthTokenService;
import com.backend.backendnew.model.Piechartdata;
import com.backend.backendnew.model.Task;
import com.backend.backendnew.model.User;
import com.backend.backendnew.model.UserDetail;
import com.backend.backendnew.service.AdminService.ResourceManagementService.ResourceMS;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/taskManagement/resourceManagement")
public class ResourceManagement {

    private final ResourceMS resourceMS;
    public ResourceManagement(ResourceMS resourceMS) {
        this.resourceMS = resourceMS;
    }
    AuthTokenService authTokenService=new AuthTokenService();

    @GetMapping("/getAllUnclaimedTasks/{id}")
    public ResponseEntity<List<Task>> getAllUnclaimedTasks(@PathVariable int id,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(401)
                    .body(null);

        }
        if(id==1)
        return ResponseEntity.ok(resourceMS.getAllUnclaimedTasks());
        else if (id==2) {
            return ResponseEntity.ok(resourceMS.getAllDoneTasks());
        }
        else return ResponseEntity.ok(resourceMS.getAllPendingTasks());
    }

    @GetMapping("/freeUsersBusyUsers/{id}")
    public ResponseEntity<List<UserDetail>> usersAndTaskAssigned(@PathVariable int id,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        List<UserDetail> result;
        System.out.println("usercontroller wortking");
        if (id == 1) {
            result = resourceMS.BusyUsers();
        } else {

            result = resourceMS.FreeUsers();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/number/{id}")
    public ResponseEntity<List<Piechartdata>> piechartdata(@PathVariable String id,@RequestHeader(name = "Authorization") String token) {
        if(!authTokenService.verify(token,1)) {
            return ResponseEntity
                    .status(401)
                    .body(null);
        }
        List<Piechartdata> data;
        if (Objects.equals(id, "users")) {
            data = resourceMS.numberOfDiffrentUsers();
        } else {
            data = resourceMS.numberOfDiffrentTasks();
        }
        return ResponseEntity.ok(data);
    }

}
