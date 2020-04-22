package com.girlesc.hearme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.girlesc.hearme.R;

public class AccountSetUpActivity extends AppCompatActivity {
    LinearLayout prefixBtn;
    TextView prefixTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_set_up);

        prefixBtn=findViewById(R.id.phonePrefixBtn);
        prefixTV=findViewById(R.id.phonePrefixTV);
        prefixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu;
                menu=new PopupMenu(AccountSetUpActivity.this, v);
                menu.inflate(R.menu.menu_phone_number_prefixes);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        prefixTV.setText(item.getTitle());
                        return true;
                    }
                });
                menu.show();
            }
        });
    }
}
