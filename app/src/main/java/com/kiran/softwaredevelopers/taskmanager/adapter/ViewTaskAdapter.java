package com.kiran.softwaredevelopers.taskmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.datamodels.Tasks;

import java.util.ArrayList;

public class ViewTaskAdapter extends RecyclerView.Adapter<ViewTaskAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Tasks> tasks;

    public ViewTaskAdapter(Context context, ArrayList<Tasks> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view,parent,false);
        return new ViewTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTask.setText(tasks.get(position).getTask());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTask = itemView.findViewById(R.id.tvTask);
        }


    }
}
