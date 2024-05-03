package com.backend.backendnew.repository;


import com.backend.backendnew.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TasksRepository extends JpaRepository<Task,Integer> {

    @Query("select t FROM Task t WHERE t.StatusID = 1")
    List<Task> getAllUnclaimedTasks();

    @Query("select t FROM Task t WHERE t.StatusID = 4")
    List<Task> getAllDoneTasks();

    @Query("select t FROM Task t WHERE t.StatusID = 3")
    List<Task> getAllPendingTasks();

    @Query("select t FROM Task t WHERE t.StatusID = 2")
    List<Task> getInProgressTasks();

    //Get All Tasks Query
    @Query("select new com.backend.backendnew.model.Task(t.TasksID, t.TaskName, t.StatusID, t.PriorityID, t.Description, t.CompletedBy) from Task t")
    List<Task> getAllTasks();

    @Query("select t from Task t where t.StatusID=1")
    List<Task> getAvailableTasks();

    @Query("select t from Task t where t.TasksID= :taskId")
    Task getTaskById(@Param("taskId") int taskId);
    @Transactional
    @Modifying
    @Query("UPDATE Task SET StatusID = :statusId WHERE TasksID = :taskId")
    void updateTask(@Param("taskId") int taskId, @Param("statusId") int statusId);
}
