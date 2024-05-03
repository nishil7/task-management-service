package com.backend.backendnew.repository;

import com.backend.backendnew.model.ClaimedTask;
import com.backend.backendnew.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClaimedTasksRepository extends JpaRepository<ClaimedTask, Integer> {
    @Query("SELECT c FROM ClaimedTask c WHERE c.UserName = :userName AND c.IsActive = 1")
    List<ClaimedTask>getAlreadyClaimedTask(@Param("userName") String userName);
    @Query("SELECT c FROM ClaimedTask c WHERE c.TasksID = :taskId AND c.IsActive = 1")
    List<ClaimedTask>getAlreadyAssignedTask(@Param("taskId") int taskId);
    @Query("SELECT c FROM ClaimedTask c WHERE c.UserName = :userName AND c.IsActive = 1")
    ClaimedTask getClaimedTask(@Param("userName") String userName);
    @Transactional
    @Modifying
    @Query("UPDATE ClaimedTask SET IsActive = 0 WHERE UserName = :userName AND TasksID = :taskId AND IsActive = 1")
    int updateClaimedTask(@Param("userName") String userName, @Param("taskId") int taskId);
}
