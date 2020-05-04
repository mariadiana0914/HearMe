package com.girlesc.enguard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.girlesc.enguard.R;
import com.girlesc.enguard.adapters.SectionsPagerAdapter;
import com.girlesc.enguard.fragments.AccountDetailsSetUpFragment;
import com.girlesc.enguard.fragments.CodeVerificationFragment;
import com.girlesc.enguard.fragments.SecurityLevelSettingsFragment;
import com.girlesc.enguard.views.CustomTabLayout;
import com.girlesc.enguard.views.CustomViewPager;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AccountSetUpActivity extends AppCompatActivity {

    private CustomViewPager viewPager;
    private SectionsPagerAdapter pagerAdapter;
    private LinearLayout nextBtn;
    private CustomTabLayout tabLayout;
    private LinearLayout backBtn;

    private AccountDetailsSetUpFragment mAccountDetailsSetUpFragment;
    private SecurityLevelSettingsFragment mSecurityLevelSettingsFragment;
    private CodeVerificationFragment mCodeVerificationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_set_up);

        viewPager = findViewById(R.id.viewPager);
        nextBtn = findViewById(R.id.nextBtn);
        tabLayout = findViewById(R.id.tabLayout);
        backBtn = findViewById(R.id.backBtn);

        mAccountDetailsSetUpFragment = new AccountDetailsSetUpFragment();
        mSecurityLevelSettingsFragment = new SecurityLevelSettingsFragment();
        mCodeVerificationFragment = new CodeVerificationFragment();

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(mAccountDetailsSetUpFragment);
        pagerAdapter.addFragment(mCodeVerificationFragment);
        pagerAdapter.addFragment(mSecurityLevelSettingsFragment);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager.getCurrentItem() - 1;
                if(position == 0)
                    backBtn.setVisibility(View.INVISIBLE);
                viewPager.setCurrentItem(position);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtn.setVisibility(View.VISIBLE);
                int position = viewPager.getCurrentItem() + 1;
                if (position <= viewPager.getChildCount()) {
                    viewPager.setCurrentItem(position);
                } else {
                    Intent intent = new Intent(AccountSetUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
