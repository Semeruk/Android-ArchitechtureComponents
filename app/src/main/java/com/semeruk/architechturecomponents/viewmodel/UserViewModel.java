package com.semeruk.architechturecomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.semeruk.architechturecomponents.data.UserRepository;
import com.semeruk.architechturecomponents.data.entity.User;

import javax.inject.Inject;

/**
 * A ViewModel provides the data for a specific UI component,
 * such as a fragment or activity, and handles the communication.
 * The ViewModel does not know about the View and is not affected
 * by configuration changes such as recreating an activity due to rotation.
 */
public class UserViewModel extends ViewModel {

    private LiveData<User> mUserLiveData;
    private UserRepository mUserRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public void init(String userId) {
        if (mUserLiveData != null) {
            return;
        }

        mUserLiveData = mUserRepository.getUser(userId);
    }

    public LiveData<User> getUserLiveData() {
        return mUserLiveData;
    }
}