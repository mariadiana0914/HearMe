package com.girlesc.hearme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.girlesc.hearme.R;
import com.girlesc.hearme.adapters.SectionsPagerAdapter;
import com.girlesc.hearme.fragments.AccountDetailsSetUpFragment;
import com.girlesc.hearme.fragments.CodeVerificationFragment;
import com.girlesc.hearme.fragments.SecurityLevelSettingsFragment;
import com.girlesc.hearme.views.CustomTabLayout;

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
