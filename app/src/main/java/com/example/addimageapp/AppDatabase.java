package com.example.addimageapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Image.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
