package com.semeruk.architechturecomponents.data;

import android.arch.lifecycle.LiveData;
import android.widget.Toast;

import com.semeruk.architechturecomponents.api.github.rest.ApiService;
import com.semeruk.architechturecomponents.data.dao.UserDao;
import com.semeruk.architechturecomponents.data.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 3;

    private final ApiService apiService;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(ApiService apiService, UserDao userDao, Executor executor) {
        this.apiService = apiService;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userLogin) {
        refreshUser(userLogin); // try to refresh data if possible from Github Api
        return userDao.load(userLogin); // return a LiveData directly from the database
    }

    private void refreshUser(String userLogin) {
        executor.execute(() -> {

            // Check if the user was fetched recently
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime()) != null);

            // If the user has to be updated
            if (!userExists) {

                apiService.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        Toast.makeText(CustomApplication.getContext, "Data refreshed from the network!", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {

                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.save(user);

                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) { }
                });
            }
        });
    }

    private Date getMaxRefreshTime(){
        Calendar cal = Calendar.getInstance();
        // Get current date
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
