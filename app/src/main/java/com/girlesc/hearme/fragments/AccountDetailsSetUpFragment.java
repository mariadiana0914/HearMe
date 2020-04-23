package com.girlesc.hearme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.girlesc.hearme.R;
import com.girlesc.hearme.activities.AccountSetUpActivity;

public class AccountDetailsSetUpFragment extends Fragment {

    private LinearLayout prefixBtn;
    private TextView prefixTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_tutorial, container, false );
        prefixBtn=view.findViewById(R.id.phonePrefixBtn);
        prefixTV=view.findViewById(R.id.phonePrefixTV);
        prefixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu;
                menu=new PopupMenu(getContext(), v);
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
        return view;
    }
}

