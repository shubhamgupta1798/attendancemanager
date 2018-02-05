package com.my.new2pma;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ajay on 2/3/2018.
 */

public class TodoLister extends RecyclerView.Adapter<TodoLister.MyViewHolder> {


    private List<String> dataList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView deadline,descri;

        public MyViewHolder(View view) {
            super(view);
            this.deadline= (TextView) view.findViewById(R.id.deadlineDate);
            this.descri=(TextView) view.findViewById(R.id.todoshow);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext,"you opted for options",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public TodoLister.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_lister, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String tempobj=dataList.get(position);
        holder.descri.setText(tempobj);
    }


    public TodoLister(List<String> categories, Context context) {
        this.mContext = context;

        this.dataList = categories;

    }
    @Override
    public int getItemCount() {

        return dataList.size();
    }
}





