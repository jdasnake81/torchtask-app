package com.example.project_task_manager.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_task_manager.R;
import com.example.project_task_manager.viewmodels.ProjectTaskViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NewEditTaskFragment extends Fragment {
    public NewEditTaskFragment() { super(R.layout.task_edit_fragment);}
    ProjectTaskViewModel viewModel;
    private boolean saving = false;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
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

        viewModel.getSaving().observe(getViewLifecycleOwner(), saving -> {
            if (this.saving && !saving){
                getActivity().runOnUiThread(() -> {
                    getActivity().getSupportFragmentManager().popBackStack();
                });
            } else if (!this.saving && saving){
                MaterialButton button = view.findViewById(R.id.save_task_button);
                button.setEnabled(false);
            }
            this.saving = saving;
        });

        TextInputEditText title = view.findViewById(R.id.edit_task_title);
        TextInputEditText assignee = view.findViewById(R.id.edit_task_assignee);
        TextInputEditText status = view.findViewById(R.id.edit_task_status);
        TextInputEditText dueDate = view.findViewById(R.id.edit_task_due_date);
        TextInputEditText project = view.findViewById(R.id.edit_task_project);
        TextInputEditText description  = view.findViewById(R.id.edit_task_description);

        view.findViewById(R.id.save_task_button).setOnClickListener(button -> {
            viewModel.saveTask(
                    title.getText().toString(),
                    description.getText().toString(),
                    project.getText().toString(),
                    status.getText().toString(),
                    dueDate.getText().toString(),
                    assignee.getText().toString()
            );
        });
    }
}
