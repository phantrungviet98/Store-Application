package com.example.storeapplication.View.Home.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.storeapplication.Adapter.HotAdapter;
import com.example.storeapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HotFragment extends Fragment {
    RecyclerView recyclerView;
    HotAdapter hotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hot,container,false);
        recyclerView = view.findViewById(R.id.recycleHot);
        List<String> data = new ArrayList<>();

        for(int i = 0; i<50; i++){
            String name = "Noi bat " + i;
            data.add(name);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        hotAdapter = new HotAdapter(getActivity(),data);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hotAdapter);

        hotAdapter.notifyDataSetChanged();
        return view;
    }
}
