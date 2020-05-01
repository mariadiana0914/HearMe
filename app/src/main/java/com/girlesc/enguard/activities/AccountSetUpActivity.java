package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.girlesc.enguard.R;
import com.girlesc.enguard.adapters.SectionsPagerAdapter;
import com.girlesc.enguard.fragments.AccountDetailsSetUpFragment;
import com.girlesc.enguard.fragments.CodeVerificationFragment;
import com.girlesc.enguard.fragments.SecurityLevelSettingsFragment;
import com.girlesc.enguard.views.CustomTabLayout;

public class AccountSetUpActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SectionsPagerAdapter pagerAdapter;
    private LinearLayout nextBtn;
    private CustomTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_set_up);

        viewPager = findViewById(R.id.viewPager);
        nextBtn = findViewById(R.id.nextBtn);
        tabLayout = findViewById(R.id.tabLayout);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new AccountDetailsSetUpFragment());
        pagerAdapter.addFragment(new SecurityLevelSettingsFragment());
        pagerAdapter.addFragment(new CodeVerificationFragment());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewPager.getCurrentItem() + 1;

                if (position <= viewPager.getChildCount() - 1) {
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
