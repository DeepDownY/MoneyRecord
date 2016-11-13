package com.example.y1247.workdemo.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.y1247.workdemo.Adapter.AccountAdapter;
import com.example.y1247.workdemo.Entity.TimeEntity;
import com.example.y1247.workdemo.R;
import com.example.y1247.workdemo.SQL.DBManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by y1247 on 2016/10/9 0009.
 */

public class MainFragment extends Fragment {

    View view;
    RecyclerView recyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> ls = new ArrayList<>();
        ls.add("John");
        ls.add("Bob");
        ls.add("Mary");
        ls.add("test");
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_accountInfo);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //效果不佳，暂时注释

        AccountAdapter adapter = new AccountAdapter(ls,this.getContext());
        recyclerView.setAdapter(adapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.account_header,recyclerView,false);
        adapter.setHeaderView(header);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment,container,false);

        return view;
    }
}
