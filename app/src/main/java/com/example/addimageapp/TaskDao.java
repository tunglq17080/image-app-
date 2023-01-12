package com.example.addimageapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    // GET ALL IMAGE
    @Query("SELECT * FROM image")
    List<Image> getAll();

    // ADD IMAGE
    @Insert
    void insert(Image task);

}
