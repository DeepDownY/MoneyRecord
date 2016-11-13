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


import com.example.y1247.workdemo.Adapter.TimeLineAdapter;
import com.example.y1247.workdemo.Entity.TimeEntity;
import com.example.y1247.workdemo.R;
import com.example.y1247.workdemo.SQL.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y1247 on 2016/10/9 0009.
 */

public class TimeLinetFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<TimeEntity> ls = new ArrayList<TimeEntity>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DBManager dbManager = new DBManager(getContext());
//        dbManager.add(TimeEntity.TYPE_EXPEND,20160122,235);
//        dbManager.add(TimeEntity.TYPE_EXPEND,20160122,232);
//        dbManager.add(TimeEntity.TYPE_INCOME,20160222,234);
//        dbManager.add(TimeEntity.TYPE_EXPEND,20160322,235);
//        dbManager.add(TimeEntity.TYPE_INCOME,20160422,235);
//        dbManager.add(TimeEntity.TYPE_EXPEND,20160522,235);
        ls = dbManager.getTimeLineData();

        TimeLineAdapter timeLineAdapter = new TimeLineAdapter(this.getContext(),ls);
        recyclerView = (RecyclerView) view .findViewById(R.id.rv_timeLine);
        recyclerView.setAdapter(timeLineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        Toast.makeText(getContext(),"2Created",Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.timeline_fargment,container,false);
        return view;
    }


}
