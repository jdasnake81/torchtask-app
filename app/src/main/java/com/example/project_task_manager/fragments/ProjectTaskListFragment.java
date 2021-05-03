package com.example.project_task_manager.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_task_manager.R;
import com.example.project_task_manager.TaskListAdapter;
import com.example.project_task_manager.models.ProjectTask;
import com.example.project_task_manager.viewmodels.ProjectTaskViewModel;

public class ProjectTaskListFragment extends Fragment {
    public ProjectTaskListFragment() { super(R.layout.task_list_fragment);}
    ProjectTaskViewModel viewModel;
    String status = "Not Started";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ProjectTaskViewModel.class);

        TaskListAdapter adapter = new TaskListAdapter(
                viewModel.getTasks(),
                (task) -> {
                    Log.d("Task ", task.id + "");
                    viewModel.setCurrentTask(task);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, NewEditTaskFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                },
                task -> {
                    viewModel.deleteTask(task);
                }
        );
        viewModel.getTasks().addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<ProjectTask>>() {
            @Override
            public void onChanged(ObservableList<ProjectTask> sender) {
                getActivity().runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onItemRangeChanged(ObservableList<ProjectTask> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(()->{
                   adapter.notifyItemRangeChanged(positionStart, itemCount);
                });

            }

            @Override
            public void onItemRangeInserted(ObservableList<ProjectTask> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(()->{
                    adapter.notifyItemRangeInserted(positionStart, itemCount);
                });

            }

            @Override
            public void onItemRangeMoved(ObservableList<ProjectTask> sender, int fromPosition, int toPosition, int itemCount) {
                getActivity().runOnUiThread(()->{
                    adapter.notifyItemMoved(fromPosition, itemCount);
                });

            }

            @Override
            public void onItemRangeRemoved(ObservableList<ProjectTask> sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(()->{
                    adapter.notifyItemRangeRemoved(positionStart, itemCount);
                });

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.task_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        view.findViewById(R.id.floating_action_button).setOnClickListener((fab) -> {
            viewModel.setCurrentTask(null);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frag_container, NewEditTaskFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

    }
}
