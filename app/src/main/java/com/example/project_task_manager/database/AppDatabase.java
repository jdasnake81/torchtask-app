package com.example.project_task_manager.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project_task_manager.models.ProjectTask;


@Database(entities = {ProjectTask.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProjectTaskDao getProjectTaskDao();
}
