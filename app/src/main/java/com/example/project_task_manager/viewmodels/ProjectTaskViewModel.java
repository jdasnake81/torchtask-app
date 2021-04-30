package com.example.project_task_manager.viewmodels;

import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.project_task_manager.database.AppDatabase;
import com.example.project_task_manager.models.ProjectTask;

public class ProjectTaskViewModel extends AndroidViewModel {
    ObservableArrayList<ProjectTask> tasks = new ObservableArrayList<>();
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    MutableLiveData<ProjectTask> currentTask = new MutableLiveData<>();
    AppDatabase database;

    public ProjectTaskViewModel(Application application) {
        super(application);
        saving.setValue(false);
        database = Room.databaseBuilder(application, AppDatabase.class, "task-db").build();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            tasks.addAll(database.getProjectTaskDao().getAllTasks());
        }).start();
    }

    public void setCurrentTask(ProjectTask task){currentTask.postValue(task);}

    public MutableLiveData<ProjectTask> getCurrentTask(){ return currentTask;}

    public MutableLiveData<Boolean> getSaving(){ return saving;}

    public ObservableArrayList<ProjectTask> getTasks(){ return tasks;}

    public void saveTask(String title, String description, String assignee, String dueDate, String project, String status){
        saving.setValue(true);
        new Thread(() -> {
            if (currentTask.getValue() != null){
                ProjectTask task = currentTask.getValue();
                task.title = title;
                task.description = description;
                task.project = project;
                task.status = status;
                task.dueDate = dueDate;
                task.assignee = assignee;
                database.getProjectTaskDao().update(task);
                int index = tasks.indexOf(task);
                tasks.set(index, task);
            } else {
                ProjectTask task = new ProjectTask();
                task.title = title;
                task.description = description;
                task.project = project;
                task.status = status;
                task.dueDate = dueDate;
                task.assignee = assignee;
                task.id = database.getProjectTaskDao().insert(task);
                tasks.add(task);
            }
             saving.postValue(false);
        }).start();

    }
    public void deleteTask(ProjectTask task){
        new Thread(() -> {
            database.getProjectTaskDao().delete(task);
            tasks.remove(task);
        }).start();
    }

}
