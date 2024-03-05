package com.example.mytodoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mytodoapp.R;
import com.example.mytodoapp.lists.TaskList;

import java.util.List;

public class TaskRecycler extends RecyclerView.Adapter<TaskRecycler.TaskViewHolder> {
    private List<TaskList> tasklist;
    private Context context;

    public TaskRecycler(List<TaskList> tasklist, Context context) {
        this.tasklist = tasklist;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.task_recycler, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(inflate);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskList tl =tasklist.get(position);
        holder.task.setText(tl.getTask());
        holder.delbutton.setOnClickListener(view -> {
            tasklist.remove(holder.getAdapterPosition());

            notifyItemRemoved(holder.getAdapterPosition());
        });
    }



    @Override
    public int getItemCount() {
        return tasklist.size();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder{
     private TextView task;
     private Button delbutton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task =itemView.findViewById(R.id.checkbox);
            delbutton=itemView.findViewById(R.id.task_delete_button);
        }
    }
}
