package com.project.arzun.navigator;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arzun on 3/31/17.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyHolder> {
    Context c;
    ArrayList<Module> saveData;
    LayoutInflater inflater;

    public MyCustomAdapter(FragmentActivity activity, ArrayList<Module> mydata) {
        c=activity;
        saveData=mydata;
        inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyCustomAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.list_item,parent,false);

        return new MyHolder(v);
    }
    @Override
    public void onBindViewHolder(MyCustomAdapter.MyHolder holder, int position) {
            holder.title.setText(saveData.get(position).title);
            holder.date.setText(saveData.get(position).date);

    }
    @Override
    public int getItemCount() {
        return saveData.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imv;
        TextView title,date;
        public MyHolder(View itemView) {
            super(itemView);
            imv=(ImageView)itemView.findViewById(R.id.imageView);
            title=(TextView)itemView.findViewById(R.id.titleText);
            date=(TextView)itemView.findViewById(R.id.dateText);
        }
    }
}
