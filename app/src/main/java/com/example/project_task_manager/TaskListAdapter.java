package com.example.project_task_manager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_task_manager.models.ProjectTask;
import com.google.android.material.button.MaterialButton;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    ObservableArrayList<ProjectTask> tasks;
    OnTaskClicked listener;
    OnTaskClicked deleteListener;

    public interface OnTaskClicked {
    public void onCLick(ProjectTask Task);
    }

    public TaskListAdapter(ObservableArrayList<ProjectTask> tasks, OnTaskClicked listener, OnTaskClicked deleteListener){
        this.tasks = tasks;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_fragment,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectTask task = tasks.get(position);
        TextView title = holder.itemView.findViewById(R.id.task_title);
        TextView assignee = holder.itemView.findViewById(R.id.task_assignee);
        TextView dueDate = holder.itemView.findViewById(R.id.task_due_date  );
        MaterialButton editButton = holder.itemView.findViewById(R.id.view_edit_task_button);
        editButton.setOnClickListener(v -> {
            if (listener == null) return;
            listener.onCLick(task);
        });
        holder.itemView.findViewById(R.id.delete_task_button).setOnClickListener(v -> {
            if (deleteListener == null) return;
            deleteListener.onCLick(task);
        });
        title.setText(task.title);
        assignee.setText(task.assignee);
        dueDate.setText(task.dueDate);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {super(itemView);}
    }
}
