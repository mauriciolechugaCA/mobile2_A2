package com.example.mobile2_a2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    Button btn_Start;
    EditText et_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_Start = findViewById(R.id.btn_Start);
        et_Name = findViewById(R.id.et_Name);

        btn_Start.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.putExtra("name", et_Name.getText().toString());
            startActivity(intent);
        });
    }

}
