package com.project.arzun.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.project.arzun.navigator.R.id.buttonHome;

/**
 * Created by arzun on 3/28/17.
 */
public class HomeFragment extends Fragment {

    Button btnHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.homefragment_layout,container,false);
        btnHome=(Button)v.findViewById(R.id.buttonHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Cicked",Snackbar.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
