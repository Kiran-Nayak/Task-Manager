package com.kiran.softwaredevelopers.taskmanager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.datamodels.UserDetails;
import com.kiran.softwaredevelopers.taskmanager.listener.RecyclerClickedListener;
import com.kiran.softwaredevelopers.taskmanager.view.MainActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserDetails> userDetails;
    private MainActivity mainActivity;

    public RecyclerViewAdapter(Context context, ArrayList<UserDetails> userDetails, MainActivity mainActivity){
        this.context = context;
        this.userDetails = userDetails;
        this.mainActivity = mainActivity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_design,parent,false);
        return new ViewHolder(view,mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvListName.setText(userDetails.get(position).getName());
        holder.tvListEmail.setText(userDetails.get(position).getEmail());
        holder.tvListAge.setText(userDetails.get(position).getAge());
        holder.tvListDOB.setText(userDetails.get(position).getDob());
    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvListName;
        TextView tvListEmail;
        TextView tvListAge;
        TextView tvListDOB;
        RecyclerClickedListener recyclerClickedListener;

        public ViewHolder(@NonNull View itemView, MainActivity mainActivity) {
            super(itemView);
            tvListName = itemView.findViewById(R.id.tvListName);
            tvListEmail = itemView.findViewById(R.id.tvListEmail);
            tvListAge = itemView.findViewById(R.id.tvListAge);
            tvListDOB = itemView.findViewById(R.id.tvListDOB);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email",tvListEmail.getText().toString());
                    mainActivity.onClicked(bundle);
                    Log.d("TAG", "onClick: Packet Added");
                }
            });
        }


    }


}
