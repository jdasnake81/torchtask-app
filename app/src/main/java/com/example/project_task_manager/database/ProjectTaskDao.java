package com.example.project_task_manager.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_task_manager.models.ProjectTask;

import java.util.List;

@Dao
public interface ProjectTaskDao {

    @Query("SELECT * FROM projecttask")
    List<ProjectTask> getAllTasks();

    @Query("SELECT * FROM projecttask WHERE id = :id LIMIT 1")
    ProjectTask findById(long id);

    @Query("SELECT * FROM projecttask WHERE project = :project")
    List<ProjectTask> findTasksInProject(String project);

    @Query("SELECT * FROM projecttask WHERE status = :status")
    List<ProjectTask> findTasksWithStatus(String status);

    @Query("SELECT DISTINCT project FROM projecttask")
    List<String> getProjectNames();

    @Insert
    long insert(ProjectTask task);

    @Update
    void update(ProjectTask task);

    @Delete
    void delete(ProjectTask task);

}