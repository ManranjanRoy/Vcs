package com.example.vcs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vcs.Mannu.Data;

import java.util.ArrayList;
import java.util.List;

public class TestAdaptor extends RecyclerView.Adapter<TestAdaptor.DataviewHolder> {
    Context mcontext;
    List<Data> dataList;

    public TestAdaptor(Context mcontext, List<Data> dataList) {
        this.mcontext = mcontext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(mcontext).inflate(R.layout.data_item,viewGroup,false);
        return new DataviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataviewHolder dataviewHolder, int i) {
        Data user=dataList.get(i);
        dataviewHolder.id.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataviewHolder extends  RecyclerView.ViewHolder{
        TextView id;
        public DataviewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.data);
        }
    }
}