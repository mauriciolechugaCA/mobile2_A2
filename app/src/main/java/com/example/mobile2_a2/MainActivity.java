package com.example.mobile2_a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String playerNameX;
    String playerNameO;
    EditText etInputNameX;
    EditText etInputNameO;
    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Creating the Start Game Button
        btnStartGame = (Button) findViewById(R.id.btn_StartGame);

        //Creating the Edit Text for the Player Name
        etInputNameX = (EditText) findViewById(R.id.et_InputNameX);
        etInputNameO = (EditText) findViewById(R.id.et_InputNameO);


    }

    //Method to open the main game activity
    public void openMainGameActivity(View v) {
        Intent it_mainGame = new Intent(this, MainGameActivity.class);

        //Get the name of the player
        playerNameX = etInputNameX.getText().toString();
        playerNameO = etInputNameO.getText().toString();

        //Send the name of the player to the main game activity
        it_mainGame.putExtra("playerNameX", playerNameX);
        it_mainGame.putExtra("playerNameO", playerNameO);

        startActivity(it_mainGame);
    }

}