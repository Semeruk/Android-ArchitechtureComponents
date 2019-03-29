package com.semeruk.architechturecomponents.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.semeruk.architechturecomponents.R;
import com.semeruk.architechturecomponents.data.entity.User;
import com.semeruk.architechturecomponents.viewmodel.UserViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends Fragment {

    /**
     * For data
     */

    public static final String UID_KEY = "uid";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel mViewModel;

    /**
     * For design
     */

    @BindView(R.id.fragment_user_profile_image)
    ImageView imageView;
    @BindView(R.id.fragment_user_profile_username)
    TextView username;
    @BindView(R.id.fragment_user_profile_company)
    TextView company;
    @BindView(R.id.fragment_user_profile_website)
    TextView website;

    public UserProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Configure Dagger
        AndroidSupportInjection.inject(this);

        // Configure ViewModel
        String userLogin = getArguments().getString(UID_KEY);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        mViewModel.init(userLogin);
        mViewModel.getUserLiveData().observe(this, user -> updateUI(user));
    }

    /**
     * Update UI
     */

    private void updateUI(@Nullable User user){
        if (user != null){
            Glide.with(this).load(user.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);

            this.username.setText(user.getName());
            this.company.setText(user.getCompany());
            this.website.setText(user.getBlog());
        }
    }
}