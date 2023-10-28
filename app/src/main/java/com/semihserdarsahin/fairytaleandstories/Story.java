package com.semihserdarsahin.fairytaleandstories;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Story implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "header")
    public String header;

    @ColumnInfo(name = "story")
    public String story;

    @ColumnInfo(name = "image")
    public int image;

    @ColumnInfo(name = "intro")
    public String intro;

    public Story(String header, String story, int image,String intro) {
        this.header = header;
        this.story = story;
        this.image = image;
        this.intro = intro;
    }

}
