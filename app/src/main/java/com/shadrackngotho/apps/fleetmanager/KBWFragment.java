package com.shadrackngotho.apps.fleetmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class KBWFragment extends Fragment implements View.OnClickListener {


    public KBWFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_kcg, container, false);

        View v1 =inflater.inflate(R.layout.fragment_kbw, container , false);
        Button kbw = (Button) v1.findViewById(R.id.buttonKBW);
        kbw.setOnClickListener(this);

        return v1;
    }


    @Override
    public void onClick(View v1) {
        startActivity(new Intent(getContext(), KBWEditActivity.class));
    }
}
