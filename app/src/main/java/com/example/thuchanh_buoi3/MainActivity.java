package com.example.thuchanh_buoi3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText edtInput;
    Spinner spnCurrency;
    ListView lvResult;

    String[] moneyNames = {
            "USD", "EUR", "GBP", "INR", "AUD",
            "CAD", "ZAR", "NZD", "JPY", "VND"
    };

    int[] flags = {
            R.drawable.flag_us,
            R.drawable.flag_eu,
            R.drawable.flag_gb,
            R.drawable.flag_in,
            R.drawable.flag_au,
            R.drawable.flag_ca,
            R.drawable.flag_za,
            R.drawable.flag_nz,
            R.drawable.flag_jp,
            R.drawable.flag_vn
    };

    double[][] rate = {
            {1,      0.80518, 0.6407,  63.3318, 1.21288, 1.16236, 11.7129, 1.2931, 118.337, 21385.7},
            {1.24172, 1,      0.79575, 78.6048, 1.51266, 1.44314, 14.5371, 1.60576, 146.927, 26561.8},
            {1.56044, 1.25667, 1,      98.784,  1.90091, 1.85135, 18.2683, 2.01791, 184.638, 33374.9},
            {0.01578, 0.01272, 0.01012, 1,      0.01924, 0.01836, 0.18493, 0.02043, 1.8691, 337.811},
            {0.82414, 0.66119, 0.52622, 52.0618, 1,      0.95416, 9.61148, 1.06158, 97.1128, 17567.9},
            {0.860599,0.69296, 0.55148, 54.5885, 1.04804, 1,      10.0742, 1.11258, 101.777, 18401.7},
            {0.085392,0.06877, 0.05437, 4.65148, 0.1039,  0.099174,1,      0.11089, 10.0996, 1825.87},
            {0.77402, 0.62319, 0.49597, 49.0031, 0.94215, 0.89931, 9.06754, 1,      91.5139, 16552.1},
            {0.00845, 0.00684, 0.00551, 0.53477, 0.0103,  0.00927, 0.09908, 0.01093, 1,      180.837},
            {0.000047,0.000038,0.000030,0.00296, 0.00006, 0.000054,0.00055, 0.000060,0.00553, 1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = findViewById(R.id.edtInput);
        spnCurrency = findViewById(R.id.spnCurrency);
        lvResult = findViewById(R.id.lvResult);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, moneyNames);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCurrency.setAdapter(spAdapter);

        spnCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                convertAll(pos);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Khi nhập xong bấm Enter sẽ đổi tiền liền
        edtInput.setOnEditorActionListener((v, actionId, event) -> {
            convertAll(spnCurrency.getSelectedItemPosition());
            return true;
        });
    }

    void convertAll(int selectedTo) {
        String s = edtInput.getText().toString().trim();
        if (s.isEmpty()) return;

        double input = Double.parseDouble(s);
        double[] values = new double[moneyNames.length];

        for (int i = 0; i < moneyNames.length; i++) {
            values[i] = input * rate[i][selectedTo];
        }

        MoneyAdapter adapter = new MoneyAdapter(
                this, moneyNames, values, flags
        );
        lvResult.setAdapter(adapter);
    }
}
