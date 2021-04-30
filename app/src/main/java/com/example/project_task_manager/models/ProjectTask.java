package com.example.project_task_manager.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ProjectTask implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "assignee")
    public String assignee;

    @ColumnInfo(name = "due_date")
    public String dueDate;

    @ColumnInfo(name = "project")
    public String project;

    @ColumnInfo(name = "status")
    public String status;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ProjectTask){
            ProjectTask other = (ProjectTask) obj;
            return other.id == this.id;
        }
        return false;
    }
}
