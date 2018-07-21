package com.jediupc.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rank_Fragment extends Fragment {

    View rootView;
    RecyclerView rV;

    public Rank_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_rank, container, false);

        setUpRecyclerView(rootView);
        return rootView;
    }

    void setUpRecyclerView(View view){
        rV = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getActivity().getApplicationContext());
        rV.setLayoutManager(linearLayout);

        Adapter adapter = new Adapter();
        ArrayList<User> dataSet = new ArrayList<>();
        //dataSet.add();
        adapter.dataSet = dataSet;

        rV.setAdapter(adapter);
    }
}
