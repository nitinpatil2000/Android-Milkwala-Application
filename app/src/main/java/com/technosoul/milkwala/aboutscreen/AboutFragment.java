package com.technosoul.milkwala.aboutscreen;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;

public class AboutFragment extends Fragment {
    TextView hyperlink;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        //TODO SET THE TITLE
        if(getActivity()!= null){
            ((AboutAppActivity)getActivity()).setActionBarTitle(getString(R.string.about_screen_title));
        }

        hyperlink = view.findViewById(R.id.hyperlink);
        hyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperlink.setLinkTextColor(getResources().getColor(R.color.black));
        return view;
    }
}