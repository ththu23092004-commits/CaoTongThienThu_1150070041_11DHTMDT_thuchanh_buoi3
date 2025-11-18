package com.example.thuchanh_buoi3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CustomButton2Activity extends AppCompatActivity {

    Button btnToast, btnDialog;
    ImageButton imgEmoji;
    boolean isHappy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_button2);

        btnToast = findViewById(R.id.btnToast);
        btnDialog = findViewById(R.id.btnDialog);
        imgEmoji = findViewById(R.id.imgEmoji);

        // NHẤN EMOJI → ĐỔI HÌNH
        imgEmoji.setOnClickListener(v -> {
            if (isHappy) {
                imgEmoji.setImageResource(R.drawable.emoji_sad);
                isHappy = false;
            } else {
                imgEmoji.setImageResource(R.drawable.emoji_happy);
                isHappy = true;
            }
        });

        // CUSTOM TOAST
        btnToast.setOnClickListener(v -> showCustomToast());

        // CUSTOM DIALOG
        btnDialog.setOnClickListener(v -> showCustomDialog());
    }

    private void showCustomToast() {
        View view = LayoutInflater.from(this).inflate(R.layout.toast_custom, null);
        Toast t = new Toast(this);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    private void showCustomDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        Button ok = view.findViewById(R.id.btnOK);
        Button cancel = view.findViewById(R.id.btnCancel);

        ok.setOnClickListener(v -> {
            showCustomToast();
            dialog.dismiss();
        });

        cancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
