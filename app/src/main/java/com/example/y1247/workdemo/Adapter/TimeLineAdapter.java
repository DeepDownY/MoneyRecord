package com.example.y1247.workdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y1247.workdemo.Entity.Expend;
import com.example.y1247.workdemo.Entity.TimeEntity;
import com.example.y1247.workdemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by y1247 on 2016/10/23 0023.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 类型变量
     */
    private final static int ITEM_DATE = 1;
    private final static int ITEM_INCOME = 2;
    private final static int ITEM_EXPEND = 3;

    private List<TimeEntity> ls = new ArrayList<TimeEntity>();

    Context context;

    LayoutInflater inflater;

    SimpleDateFormat ymd = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
    SimpleDateFormat md = new SimpleDateFormat("MM月dd日", Locale.getDefault());
    SimpleDateFormat day = new SimpleDateFormat("dd日", Locale.getDefault());


    TextView tt_date_day,tt_date_imcome,tt_imcome_type,
            tt_imcome_number,tt_expend_type,tt_expend_number;
    ImageView iv_expend_type,iv_income_type;


    /**
     *
     * @param context 上下文
     * @param ls 数据list
     */
    public TimeLineAdapter(Context context,List<TimeEntity> ls){
        this.context = context;
        this.ls = ls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==ITEM_DATE){
            return new DateHolder(inflater.inflate(R.layout.timeline_date_item,parent,false));
        }
        if(viewType==ITEM_EXPEND){
            return new ExpendHolder(inflater.inflate(R.layout.timeline_expend_item,parent,false));
        }
        if(viewType==ITEM_INCOME){
            return new IncomeHolder(inflater.inflate(R.layout.timeline_income_item,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        String temp = sf.format(ls.get(position).date);
        String temp = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ls.get(position).date);
        Calendar nowTime = Calendar.getInstance();
        if(calendar.get(Calendar.MONTH)==nowTime.get(Calendar.MONTH)
                &&calendar.get(Calendar.YEAR)==nowTime.get(Calendar.YEAR)){
            temp = day.format(ls.get(position).date);
        }else if(calendar.get(Calendar.YEAR)==nowTime.get(Calendar.YEAR)){
            temp = md.format(ls.get(position).date);
        }else {
            temp = ymd.format(ls.get(position).date);
        }



        if(holder instanceof DateHolder){
            ((DateHolder) holder).tv_number.
                    setText(String.valueOf(ls.get(position).use));
            ((DateHolder) holder).tv_date.
                    setText(temp);
        }
        if(holder instanceof ExpendHolder){
            ((ExpendHolder) holder).tv_number.
                    setText(String.valueOf(ls.get(position).use));
            ((ExpendHolder) holder).tv_type.
                    setText(ls.get(position).type_name);
        }
        if(holder instanceof IncomeHolder){
            ((IncomeHolder) holder).tv_number.
                    setText(String.valueOf(ls.get(position).use));
            ((IncomeHolder) holder).tv_type.
                    setText(ls.get(position).type_name);
        }

    }

    @Override
    public int getItemViewType(int position) {
//        Log.i("TimeLineAdapter", "getItemViewType: "+ls.get(position).type);
        switch (ls.get(position).type){
            case TimeEntity.TYPE_DATEREC:
                return ITEM_DATE;
            case TimeEntity.TYPE_INCOME:
                return ITEM_INCOME;
            case TimeEntity.TYPE_EXPEND:
                return  ITEM_EXPEND;
        }
        return super.getItemViewType(position);
    }



    @Override
    public int getItemCount() {
        return ls.size();
    }

    private class DateHolder extends RecyclerView.ViewHolder{

        TextView tv_date,tv_number;

        private DateHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_number = (TextView) itemView.findViewById(R.id.tv_date_income);

        }
    }

    private class IncomeHolder extends RecyclerView.ViewHolder {

        ImageView iv_type;
        TextView tv_type, tv_number;

        private IncomeHolder(View itemView) {
            super(itemView);
            iv_type = (ImageView) itemView.findViewById(R.id.iv_income_type);
            tv_type = (TextView) itemView.findViewById(R.id.tv_income_type);
            tv_number = (TextView) itemView.findViewById(R.id.tv_income_number);
        }
    }
    private class ExpendHolder extends RecyclerView.ViewHolder{

        ImageView iv_type;
        TextView tv_type,tv_number;

        private ExpendHolder(View itemView) {
            super(itemView);
            iv_type = (ImageView) itemView.findViewById(R.id.iv_expend_type);
            tv_type = (TextView) itemView.findViewById(R.id.tv_expend_type);
            tv_number = (TextView) itemView.findViewById(R.id.tv_expend_number);
        }
    }
}
