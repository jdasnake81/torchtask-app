package com.example.project_task_manager.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_task_manager.R;
import com.example.project_task_manager.viewmodels.ProjectTaskViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class NotStartedFragment extends Fragment {
    public NotStartedFragment() { super(R.layout.task_list_fragment);}
    ProjectTaskViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ProjectTaskViewModel.class);

        viewModel.getCurrentTask().observe(getViewLifecycleOwner(), task -> {
            if (task != null){
                TextInputEditText title = view.findViewById(R.id.edit_task_title);
                TextInputEditText assignee = view.findViewById(R.id.edit_task_assignee);
                TextInputEditText status = view.findViewById(R.id.edit_task_status);
                TextInputEditText dueDate = view.findViewById(R.id.edit_task_due_date);
                TextInputEditText project = view.findViewById(R.id.edit_task_project);
                TextInputEditText description  = view.findViewById(R.id.edit_task_description);

                title.setText(task.title);
                assignee.setText(task.assignee);
                status.setText(task.status);
                dueDate.setText(task.dueDate);
                project.setText(task.project);
                description.setText(task.description);
            }
        });
    }
}
