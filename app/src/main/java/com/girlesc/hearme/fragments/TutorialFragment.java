package com.girlesc.hearme.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.girlesc.hearme.R;

import org.w3c.dom.Text;

public class TutorialFragment extends Fragment {

    private String title;
    private String description;
    private int imageId;
    private TextView titleTV;
    private TextView descriptionTV;
    private ImageView imageIV;

    public TutorialFragment(String title, String description, int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public TutorialFragment(int contentLayoutId, String title, String description, int imageId) {
        super(contentLayoutId);
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        titleTV = view.findViewById(R.id.titleTV);
        descriptionTV = view.findViewById(R.id.descriptionTV);
        imageIV = view.findViewById(R.id.tutorialImage);

        titleTV.setText(this.title);
        descriptionTV.setText(this.description);
        imageIV.setImageResource(this.imageId);

        return view;
    }

}
