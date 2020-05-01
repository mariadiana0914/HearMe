package com.girlesc.enguard.fragments;

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

import com.girlesc.enguard.R;

public class AccountDetailsSetUpFragment extends Fragment {

    private LinearLayout prefixBtn;
    private TextView prefixTV;
    private PopupMenu phonePrefixMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_account_detail_set_up, container, false);

        prefixBtn = view.findViewById(R.id.phonePrefixBtn);
        prefixTV = view.findViewById(R.id.phonePrefixTV);

        phonePrefixMenu = new PopupMenu(getContext(), prefixBtn);
        phonePrefixMenu.inflate(R.menu.menu_phone_number_prefixes);
        phonePrefixMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                prefixTV.setText(menuItem.getTitle());
                return true;
            }
        });

        prefixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonePrefixMenu.show();
            }
        });
        return view;
    }
}

