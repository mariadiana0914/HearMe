package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.enguard.R;
import com.girlesc.enguard.adapters.SectionsPagerAdapter;
import com.girlesc.enguard.fragments.TutorialFragment;
import com.girlesc.enguard.views.CustomTabLayout;

public class TutorialActivity extends AppCompatActivity {

    ViewPager viewPager;
    SectionsPagerAdapter pagerAdapter;
    LinearLayout nextBtn;
    TextView skipBtn;
    CustomTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        viewPager = findViewById(R.id.viewPager);
        nextBtn = findViewById(R.id.nextBtn);
        skipBtn = findViewById(R.id.skipBtn);
        tabLayout = findViewById(R.id.tabLayout);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new TutorialFragment(getResources().getString(R.string.tutorial_activity_fragment_1_title),getResources().getString(R.string.tutorial_activity_fragment_1_description), R.drawable.ic_launcher_background));
        pagerAdapter.addFragment(new TutorialFragment(getResources().getString(R.string.tutorial_activity_fragment_2_title),getResources().getString(R.string.tutorial_activity_fragment_2_description), R.drawable.ic_launcher_background));
        pagerAdapter.addFragment(new TutorialFragment(getResources().getString(R.string.tutorial_activity_fragment_3_title),getResources().getString(R.string.tutorial_activity_fragment_3_description), R.drawable.ic_launcher_background));
        pagerAdapter.addFragment(new TutorialFragment(getResources().getString(R.string.tutorial_activity_fragment_4_title),getResources().getString(R.string.tutorial_activity_fragment_4_description), R.drawable.ic_launcher_background));

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewPager.getCurrentItem() + 1;

                if (position <= viewPager.getChildCount() - 1) {
                    viewPager.setCurrentItem(position);
                } else {
                    Intent intent = new Intent(TutorialActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
