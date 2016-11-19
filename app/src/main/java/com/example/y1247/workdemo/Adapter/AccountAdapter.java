package com.example.y1247.workdemo.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y1247.workdemo.R;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by y1247 on 2016/10/22 0022.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyHolder>{


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    private Context context;
    private List<String> ls = new ArrayList<>();

    private View mHeaderView;


    /**
     *
     * @param ls 传入数据
     * @param context 传入上下文
     */
    public AccountAdapter(List<String> ls,Context context) {
        super();
        this.ls = ls;
        this.context = context;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }
    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(MyHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new MyHolder(mHeaderView);
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.account_detal,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        final String data = ls.get(pos);

        if(holder instanceof MyHolder) {
            ((MyHolder) holder).tt_accountName.setText(data);

        }

    }


    @Override
    public int getItemCount() {
        return mHeaderView == null ? ls.size() : ls.size() + 1;
    }

    class MyHolder extends RecyclerView.ViewHolder{
            TextView tt_accountType,tt_accountName,tt_accountRest,tt_accountUse;
            ImageView iv_account;


        public MyHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            tt_accountType = (TextView) itemView.findViewById(R.id.tt_accountType);
            tt_accountName = (TextView) itemView.findViewById(R.id.tt_accountName);
            tt_accountRest = (TextView) itemView.findViewById(R.id.tt_accountRest);
            tt_accountUse = (TextView) itemView.findViewById(R.id.tt_accountUse);
            iv_account = (ImageView) itemView.findViewById(R.id.iv_account);
        }
    }
}
