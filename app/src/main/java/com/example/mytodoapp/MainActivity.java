package com.example.mytodoapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytodoapp.adapter.TaskRecycler;
import com.example.mytodoapp.lists.TaskList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TaskList> tasks;
    RecyclerView recyclerView;
    FloatingActionButton button;
    TaskRecycler taskRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


            recyclerView=findViewById(R.id.tasks_recycler_view);
            this.tasks=new ArrayList<>();
            tasks.add(new TaskList(1,"You can add task by clicking on button at botton end"));

        taskRecycler = new TaskRecycler(tasks, this);
        recyclerView.setAdapter(taskRecycler);
        button = findViewById(R.id.add_task_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
    }

    private void showPopup() {
        final View popupView = getLayoutInflater().inflate(R.layout.add_task, null);
        final EditText taskEditText = popupView.findViewById(R.id.add_task);

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(findViewById(R.id.tasks_recycler_view), Gravity.CENTER, 0, 0);

        final Button addTaskButton = popupView.findViewById(R.id.save_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = String.valueOf(taskEditText.getText());
                if (!taskText.isEmpty()) {
                    tasks.add(new TaskList(tasks.size() + 1, taskText));

                    taskRecycler.notifyItemInserted(tasks.size() - 1);
                    popupWindow.dismiss();
                }
            }
        });
    }

}



