package com.example.thuchanh_buoi3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

public class LengthActivity extends AppCompatActivity {

    EditText edtInput;
    Spinner spnUnit;
    ListView lvKetQua;

    String[] units = {"Hải lý", "Dặm", "Kilometer", "Lý", "Met", "Yard", "Foot", "Inches"};

    double[] rate = {
            1852,
            1609.34,
            1000,
            500,
            1,
            0.9144,
            0.3048,
            0.0254
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        edtInput = findViewById(R.id.edtInput);
        spnUnit = findViewById(R.id.spnUnit);
        lvKetQua = findViewById(R.id.lvKetQua);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, units);
        spnUnit.setAdapter(spAdapter);

        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                convert(pos);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    void convert(int selected) {
        String s = edtInput.getText().toString().trim();
        if (s.isEmpty()) return;

        double input = Double.parseDouble(s);

        double[] values = new double[units.length];

        for (int i = 0; i < units.length; i++) {

            values[i] = input * (rate[selected] / rate[i]);
        }

        LengthAdapter adapter = new LengthAdapter(this, values, units);
        lvKetQua.setAdapter(adapter);
    }
}
