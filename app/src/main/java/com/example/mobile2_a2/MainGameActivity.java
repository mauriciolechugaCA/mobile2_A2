package com.example.mobile2_a2;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;

public class MainGameActivity extends AppCompatActivity {

    //Array to store the game board
    Button[] arrButtons = new Button[9];

    //Boolean to check if it is player X turn
    boolean playerXTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Fill the array with the buttons
        for (int i = 0; i < 9; i++)
        {
            String buttonID = "btn" + i;

            //Getting the real ID
            int realID = getResources().getIdentifier(buttonID, "id", getPackageName());

            arrButtons[i] = findViewById(realID);

            arrButtons[i].setOnClickListener(this::onButtonPressed);

        }
    }


    public void onButtonPressed(View v)
    {
        Button button = (Button) v;


        if (!button.getText().toString().equals(""))
        {
            return;
        }


        if (playerXTurn)
        {
            button.setText("X");
        }
        else
        {
            button.setText("O");
        }

        //Change the turn
        playerXTurn = !playerXTurn;
    }
}