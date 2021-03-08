package com.example.testhexalitics.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testhexalitics.DetailsActivity;
import com.example.testhexalitics.R;
import com.example.testhexalitics.model.DataModel;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterAsList extends RecyclerView.Adapter<AdapterAsList.ViewHolder> {

    private List<DataModel> list;
    private Context context;

    public AdapterAsList(Context context, List<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_list_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DataModel response = list.get(position);

        holder.textView.setText(response.getHelpLineName());

        Glide.with(holder.imageView.getContext()).load(list.get(position).getImage()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title",list.get(position).getHelpLineName());
                intent.putExtra("image",list.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.text);

            this.imageView = (ImageView)itemView.findViewById(R.id.image);


        }


    }
}
