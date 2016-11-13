package com.example.y1247.workdemo.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.y1247.workdemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

/**
 * Created by y1247 on 2016/10/9 0009.
 */

public class StatisticsFragment extends Fragment {

    View view;
    PieChart pieChart;
    LineChart mChart;


    PieData mPieData;
    private int flag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stat_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mChart = (LineChart) view.findViewById(R.id.lineChart);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        mPieData = getPieData(4,100);
        Toast.makeText(getContext(),"3Created",Toast.LENGTH_SHORT).show();
        initLineChart();
        initPieChart();
        flag = 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("TestView", "onDestroyView: ");
        pieChart = null;
        mChart = null;
    }

    /**
     * 扇形图初始化
     */
    private void initPieChart() {
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(30f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        pieChart.setDescription("收支比例");
        pieChart.setDrawCenterText(false);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转
        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比

        //设置数据
        pieChart.setData(mPieData);

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

//        pieChart.animateXY(1000, 1000);

    }

    private PieData getPieData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容


        /**
         * 比例详细
         */
        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 23;
        float quarterly2 = 14;
        float quarterly3 = 34;
        float quarterly4 = 38;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));


        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);


        return pieData;
    }


    /**
     * 折线表初始化
     */
    private void initLineChart() {
        LineData line = null;



        mChart.setDrawGridBackground(false);
        mChart.setDrawBorders(false);
        // 设置右下角描述
        mChart.setDescription("");
        //设置透明度
        mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        mChart.setBorderColor(Color.rgb(213, 216, 214));

        //设置是否可以触摸，如为false，则不能拖动，缩放等
        mChart.setTouchEnabled(false);
        //设置是否可以拖拽
        mChart.setDragEnabled(false);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        mChart.setPinchZoom(false);

        XAxis x1 = mChart.getXAxis();
        x1.setDrawGridLines(true);
        x1.setAvoidFirstLastClipping(true);
        x1.setSpaceBetweenLabels(0);
        x1.setEnabled(true);
        x1.setPosition(XAxis.XAxisPosition.BOTTOM);


        line = initExpendLineData();
        line.addDataSet(initIncomeLineData());

        mChart.setData(line);

//        mChart.animateX(4000);
//        mChart.animateY(3000);   //从Y轴进入的动画
//        mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        mChart.setScaleMinima(0.5f, 1f);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
    }

    /**
     * 支出折线数据初始化
     */

    private LineData initExpendLineData() {
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < 12; i++) {
            xVals.add(String.valueOf(i+1));
        }

        String[] yy = {"31", "70", "63", "85", "95", "24", "77", "79", "139","90","61","52"};

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "支出");
        set1.setDrawCubic(false);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setValueTextSize(10f);
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(3f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(117, 224, 224));
        set1.setColor(Color.rgb(231, 107, 175));
        return new LineData(xVals,set1);

    }

    /**
     * 收入折线数据初始化
     */
    private LineDataSet initIncomeLineData() {
        ArrayList<String> xVals = new ArrayList<String>();

        String[] yy = {"61", "20", "23", "45", "75", "64", "37", "89", "109","50","101","102"};
        for (int i = 0; i < 12; i++) {
            xVals.add(String.valueOf(i+1));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }
        LineDataSet set1 = new LineDataSet(yVals, "收入");
        set1.setDrawCubic(false);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setValueTextSize(10f);
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(3f);   //设置小圆的大小
        set1.setCircleColor(Color.rgb(213,241,10));
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));
        Log.i("123123123123", "initIncomeLineData: ");
        return set1;

    }


}
