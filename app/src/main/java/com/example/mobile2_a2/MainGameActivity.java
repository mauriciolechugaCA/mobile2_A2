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
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity {

    TextView tvResult;
    String playerNameX;
    String playerNameO;

    //Array to store the game board
    Button[] arrButtons = new Button[9];

    //Boolean to check if it is player X turn
    boolean playerXTurn = true;

    //Moves counter
    int movesCounter = 0;

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

        //Get the player name from the main
        playerNameX = getIntent().getStringExtra("playerNameX");
        playerNameO = getIntent().getStringExtra("playerNameO");

        tvResult = findViewById(R.id.tvResult);

        //Fill the array with the buttons
        for (int i = 0; i < 9; i++)
        {
            String buttonID = "btn" + i;

            //Getting the real ID
            int realID = getResources().getIdentifier(buttonID, "id", getPackageName());

            //print the real ID
            System.out.println("RealID: " + realID);

            arrButtons[i] = findViewById(realID);

            arrButtons[i].setOnClickListener(this::onButtonPressed);

        }
    }


    public void onButtonPressed(View v)
    {
        Button button = (Button) v;

        //Button already has a value
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

        movesCounter++;

        //Check the board for a winner or draw
        CheckBoard();

        if (!IsWinner() && !IsTie())
        {
            //Change the turn
            playerXTurn = !playerXTurn;
        }

    }

    public void CheckBoard()
    {
        if (IsWinner())
        {
            if (playerXTurn)
            {
                tvResult.setText("Congrats " + playerNameX + "! You won!");

            }
            else
            {
                tvResult.setText("Congrats " + playerNameO + "! You won!");
            }
        }
        else if (IsTie())
        {
            tvResult.setText("It is a Tie!");
        }
    }

    public boolean IsWinner()
    {
        //Rows (0|1|2 - 3|4|5 - 6|7|8)
        for (int row = 0; row < 3; row++)
        {
            String a = arrButtons[row * 3].getText().toString();
            String b = arrButtons[row * 3 + 1].getText().toString();
            String c = arrButtons[row * 3 + 2].getText().toString();

            //Check if the a is note epmty, then check if a is equal b and b is equal c
            if (!a.equals("") && a.equals(b) && b.equals(c))
            {
                return true;
            }
        }

        //Cols (0|3|6 - 1|4|7 - 2|5|8)
        for (int col = 0; col < 3; col++)
        {
            String a = arrButtons[col].getText().toString();
            String b = arrButtons[col + 3].getText().toString();
            String c = arrButtons[col + 6].getText().toString();
            if (!a.equals("") && a.equals(b) && b.equals(c))
            {
                return true;
            }
        }

        //Diagonals
        String a = arrButtons[0].getText().toString();
        String b = arrButtons[4].getText().toString();
        String c = arrButtons[8].getText().toString();
        if (!a.equals("") && a.equals(b) && b.equals(c))
        {
            return true;
        }

        a = arrButtons[2].getText().toString();
        b = arrButtons[4].getText().toString();
        c = arrButtons[6].getText().toString();
        if (!a.equals("") && a.equals(b) && b.equals(c))
        {
            return true;
        }

        return false;
    }

    public boolean IsTie()
    {
        return movesCounter == 9;
    }

    public void NewGame()
    {
        //Clean the board
        for (Button b : arrButtons)
        {
            b.setText("");
        }

        //Reseting the variables
        movesCounter = 0;
        playerXTurn = true;
    }


}