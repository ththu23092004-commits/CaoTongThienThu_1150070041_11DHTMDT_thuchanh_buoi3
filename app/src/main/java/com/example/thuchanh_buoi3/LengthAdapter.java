package com.example.thuchanh_buoi3;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class LengthAdapter extends ArrayAdapter<String> {

    Context context;
    double[] values;
    String[] units;

    public LengthAdapter(Context context, double[] values, String[] units) {
        super(context, R.layout.item_length, units);
        this.context = context;
        this.values = values;
        this.units = units;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_length, parent, false);

        TextView tvValue = v.findViewById(R.id.tvValue);
        TextView tvUnit = v.findViewById(R.id.tvUnit);

        tvValue.setText(String.format("%,.4f", values[pos]));
        tvUnit.setText(units[pos]);

        return v;
    }
}
