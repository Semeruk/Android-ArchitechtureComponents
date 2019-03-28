package com.semeruk.architechturecomponents.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.semeruk.architechturecomponents.data.dao.UserDao;
import com.semeruk.architechturecomponents.data.entity.User;
import com.semeruk.architechturecomponents.data.util.DateConverter;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    // Singleton
    private static volatile AppDatabase INSTANCE;

    // Dao
    public abstract UserDao userDao();
}