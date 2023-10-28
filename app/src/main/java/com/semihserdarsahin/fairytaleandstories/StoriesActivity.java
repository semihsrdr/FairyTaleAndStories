package com.semihserdarsahin.fairytaleandstories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.semihserdarsahin.fairytaleandstories.databinding.ActivityStoriesBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StoriesActivity extends AppCompatActivity {
    private ActivityStoriesBinding binding;
    StoryDatabase db;
    Dao dao;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStoriesBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        db= Room.databaseBuilder(StoriesActivity.this,StoryDatabase.class,"Story").build();
        dao=db.dao();

        compositeDisposable.add(dao.getAll().observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(StoriesActivity.this::handleResponse));





    }
    public void handleResponse(List<Story> storyArrayList){
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter=new Adapter(new ArrayList<>(storyArrayList));
        binding.recyclerview.setAdapter(adapter);
    }

    protected void onDestroy(){
        super.onDestroy();
        compositeDisposable.clear();
    }
}