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
public class KCGFragment extends Fragment implements View.OnClickListener {
    public KCGFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_kcg, container , false);
        Button bkcg = (Button) v.findViewById(R.id.buttonKCG);
        bkcg.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), KCGEditActivity.class));
    }
}