package com.semeruk.architechturecomponents.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.semeruk.architechturecomponents.data.entity.User;

import java.util.Date;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE login = :userLogin")
    LiveData<User> load(String userLogin);

    // ----------------------------------------------------------- assume 17:11 is current time
    // ------------------------------------------------------------------ (we subtract 3 min from now)
    // ---------------------------------------------------------- 17:09 > 17:08 will return 1 user
    // ---------------------------------------------------------- 17:06 > 17:08 will return null because last update was more than 3 min ago
    @Query("SELECT * FROM user WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    User hasUser(String userLogin, Date lastRefreshMax);
}