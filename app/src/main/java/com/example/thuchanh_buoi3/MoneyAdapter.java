package com.example.thuchanh_buoi3;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class MoneyAdapter extends ArrayAdapter<String> {

    Context context;
    String[] names;
    double[] values;
    int[] flags;

    public MoneyAdapter(Context context, String[] names, double[] values, int[] flags) {
        super(context, R.layout.item_money, names);
        this.context = context;
        this.names = names;
        this.values = values;
        this.flags = flags;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_money, parent, false);

        ImageView img = v.findViewById(R.id.imgFlag);
        TextView tv = v.findViewById(R.id.tvMoney);

        img.setImageResource(flags[pos]);
        tv.setText(names[pos] + "     " + String.format("%.6f", values[pos]));

        return v;
    }
}
