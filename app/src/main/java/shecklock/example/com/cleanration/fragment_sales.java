package shecklock.example.com.cleanration;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sherlock on 4/11/16.
 */
public class fragment_sales extends Fragment implements OnSeekBarChangeListener,
        OnChartValueSelectedListener{

    private PieChart mChart,mChart2,mChart3,mChart4;
    private String[] mParties = new String[] {
            "Sold", "Left"
    };

    String url;
    SharedPreferences pref;
    JSONObject userDetails;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sales, container, false);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll);


        url = "http://192.168.43.92:8080/ration-shop/shopkeeper-data";
        pref = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        userDetails = new JSONObject();

        try
        {
            userDetails.put("username", pref.getString("username", ""));
            userDetails.put("password", pref.getString("password", ""));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        mChart = (PieChart) rootView.findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterText(generateCenterSpannableText("Rice"));

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(51f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        //setData(2, 100,35,65,mChart);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(12f);

        mChart2 = (PieChart) rootView.findViewById(R.id.chart2);
        mChart2.setUsePercentValues(true);
        mChart2.getDescription().setEnabled(false);
        mChart2.setExtraOffsets(5, 10, 5, 5);

        mChart2.setDragDecelerationFrictionCoef(0.95f);

        mChart2.setCenterText(generateCenterSpannableText("Wheat"));

        mChart2.setDrawHoleEnabled(true);
        mChart2.setHoleColor(Color.WHITE);

        mChart2.setTransparentCircleColor(Color.WHITE);
        mChart2.setTransparentCircleAlpha(110);

        mChart2.setHoleRadius(45f);
        mChart2.setTransparentCircleRadius(51f);

        mChart2.setDrawCenterText(true);

        mChart2.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart2.setRotationEnabled(true);
        mChart2.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart2.setOnChartValueSelectedListener(this);

        //setData(2, 100,30,70,mChart2);

        mChart2.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // entry label styling
        mChart2.setEntryLabelColor(Color.WHITE);
        mChart2.setEntryLabelTextSize(12f);

        mChart3 = (PieChart) rootView.findViewById(R.id.chart3);
        mChart3.setUsePercentValues(true);
        mChart3.getDescription().setEnabled(false);
        mChart3.setExtraOffsets(5, 10, 5, 5);

        mChart3.setDragDecelerationFrictionCoef(0.95f);

        mChart3.setCenterText(generateCenterSpannableText("Sugar"));

        mChart3.setDrawHoleEnabled(true);
        mChart3.setHoleColor(Color.WHITE);

        mChart3.setTransparentCircleColor(Color.WHITE);
        mChart3.setTransparentCircleAlpha(110);

        mChart3.setHoleRadius(45f);
        mChart3.setTransparentCircleRadius(51f);

        mChart3.setDrawCenterText(true);

        mChart3.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart3.setRotationEnabled(true);
        mChart3.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart3.setOnChartValueSelectedListener(this);

        //setData(2, 100,90,10,mChart3);

        mChart3.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // entry label styling
        mChart3.setEntryLabelColor(Color.WHITE);
        mChart3.setEntryLabelTextSize(12f);

        mChart4 = (PieChart) rootView.findViewById(R.id.chart4);
        mChart4.setUsePercentValues(true);
        mChart4.getDescription().setEnabled(false);
        mChart4.setExtraOffsets(5, 10, 5, 5);

        mChart4.setDragDecelerationFrictionCoef(0.95f);

        mChart4.setCenterText(generateCenterSpannableText("Kerosene"));

        mChart4.setDrawHoleEnabled(true);
        mChart4.setHoleColor(Color.WHITE);

        mChart4.setTransparentCircleColor(Color.WHITE);
        mChart4.setTransparentCircleAlpha(110);

        mChart4.setHoleRadius(45f);
        mChart4.setTransparentCircleRadius(51f);

        mChart4.setDrawCenterText(true);

        mChart4.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart4.setRotationEnabled(true);
        mChart4.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart4.setOnChartValueSelectedListener(this);

        //setData(2, 100,42,58,mChart4);

        mChart4.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // entry label styling
        mChart4.setEntryLabelColor(Color.WHITE);
        mChart4.setEntryLabelTextSize(10f);

        populateGraph();

        return rootView;
    }

    private void populateGraph()
    {
        JsonObjectRequest getGraphRequest = new JsonObjectRequest(Request.Method.POST, url, userDetails, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success"))
                    {
                        JSONObject items = response.getJSONObject("chart-data");
                        setData(2, 100, 100-(float) items.getDouble("Rice"), (float) items.getDouble("Rice"), mChart);
                        setData(2, 100, 100-(float) items.getDouble("Wheat"), (float) items.getDouble("Wheat"), mChart2);
                        setData(2, 100, 100-(float) items.getDouble("Sugar"), (float) items.getDouble("Sugar"), mChart3);
                        setData(2, 100, 100-(float) items.getDouble("Kerosene"), (float) items.getDouble("Kerosene"), mChart4);

                    }
                    else
                    {
                        Log.d("Error", "error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(getGraphRequest);

    }

    private void setData(int count, float range, float x, float y, PieChart pc) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));
//        }
        entries.add(new PieEntry((float) (x),mParties[0]));

        entries.add(new PieEntry((float) (y),mParties[1]));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

       int i=(int)Math.floor(Math.random()*4);
        switch(i) {
            case 0: for (int c : ColorTemplate.VORDIPLOM_COLORS)
                    colors.add(c);
                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);
                    break;
            case 1:for (int c : ColorTemplate.JOYFUL_COLORS)
                    colors.add(c);
                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);
                    break;

            case 2:for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);
                for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);
                    break;

            case 3:for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);
                    break;
            default:for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        pc.setData(data);
        // undo all highlights
        pc.highlightValues(null);
        pc.invalidate();
    }
    private SpannableString generateCenterSpannableText(String str)
    {

        SpannableString s = new SpannableString("\n"+str+"\n                          ");
        s.setSpan(new RelativeSizeSpan(0.8f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }



    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
