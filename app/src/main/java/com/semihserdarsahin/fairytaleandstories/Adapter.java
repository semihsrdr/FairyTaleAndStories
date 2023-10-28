package com.semihserdarsahin.fairytaleandstories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.semihserdarsahin.fairytaleandstories.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.StoryHolder> {
    ArrayList<Story> myArrayList;

    public Adapter(ArrayList<Story> myArrayList) {
        this.myArrayList = myArrayList;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StoryHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recyclerRowBinding.imageView.setImageResource(myArrayList.get(position).image);
        holder.recyclerRowBinding.title.setText(myArrayList.get(position).header);
        holder.recyclerRowBinding.intro.setText(myArrayList.get(position).intro);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("selectedStory",myArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myArrayList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;
        public StoryHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;
        }
    }
}
