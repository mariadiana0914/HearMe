package com.girlesc.hearme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.hearme.R;
import com.girlesc.hearme.adapters.SectionsPagerAdapter;
import com.girlesc.hearme.fragments.TutorialFragment;

public class TutorialActivity extends AppCompatActivity {

    ViewPager viewPager;
    SectionsPagerAdapter pagerAdapter;
    LinearLayout nextBtn;
    TextView skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        viewPager = findViewById(R.id.viewPager);
        nextBtn = findViewById(R.id.nextBtn);
        skipBtn = findViewById(R.id.skipBtn);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new TutorialFragment("Lorem ipsum amet consectetuer", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy.", R.drawable.ic_launcher_background));
        pagerAdapter.addFragment(new TutorialFragment("Lorem ipsum amet consectetuer", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy.", R.drawable.ic_launcher_background));
        pagerAdapter.addFragment(new TutorialFragment("Lorem ipsum amet consectetuer", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy.", R.drawable.ic_launcher_background));
        viewPager.setAdapter(pagerAdapter);

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
