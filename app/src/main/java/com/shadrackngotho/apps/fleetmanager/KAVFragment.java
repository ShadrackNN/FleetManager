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
public class KAVFragment extends Fragment implements View.OnClickListener {


    public KAVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_kcg, container, false);

        View v2 =inflater.inflate(R.layout.fragment_kav, container , false);
        Button kav = (Button) v2.findViewById(R.id.buttonKAV);
        kav.setOnClickListener(this);

        return v2;
    }


    @Override
    public void onClick(View v2) {
        startActivity(new Intent(getActivity(), KAVEditActivity.class));
    }
}
