package com.semihserdarsahin.fairytaleandstories;


import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM Story")
    public Flowable<List<Story>> getAll();

    @Insert
    public Completable insert(Story story);
}
