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
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity {

    TextView tvResult;
    String playerNameX;
    String playerNameO;
    Button btnPlayAgain;
    Button btnBack;

    //Array to store the game board
    ImageView[] arrImgViews = new ImageView[9];

    //Boolean to check if it is player X turn
    boolean playerXTurn = true;

    //Blocking the board after the end game
    boolean isGameEnded = false;

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

        if (playerNameX.isEmpty())
        {
            playerNameX = "Player X";
        }
        if (playerNameO.isEmpty())
        {
            playerNameO = "Player O";
        }

        tvResult = findViewById(R.id.tvResult);

        //Fill the array with the buttons
        for (int i = 0; i < 9; i++)
        {
            String imageViewID = "iv" + i;

            //Getting the real ID
            int realID = getResources().getIdentifier(imageViewID, "id", getPackageName());

            //print the real ID
            System.out.println("RealID: " + realID);

            arrImgViews[i] = findViewById(realID);

            arrImgViews[i].setOnClickListener(this::onButtonPressed);

        }

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NewGame();
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                onBackPressed();
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }


    public void onButtonPressed(View v)
    {
        System.out.println("Button pressed");
        ImageView imageView = (ImageView) v;

        //Check if the imageview has an image and if the game ended
        if (imageView.getDrawable() != null || isGameEnded)
        {
            return;
        }

        if (playerXTurn)
        {
            imageView.setImageResource(R.drawable.image_x);
            imageView.setScaleX(0f);
            imageView.setScaleY(0f);
            imageView.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
            //Adding the tag to compare later
            imageView.setTag("X");
        }
        else
        {
            imageView.setImageResource(R.drawable.image_o);
            imageView.setScaleX(0f);
            imageView.setScaleY(0f);
            imageView.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
            imageView.setTag("O");
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
                isGameEnded = true;
                btnPlayAgain.setVisibility(View.VISIBLE);
            }
            else
            {
                tvResult.setText("Congrats " + playerNameO + "! You won!");
                isGameEnded = true;
                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        }
        else if (IsTie())
        {
            tvResult.setText("It is a Tie!");
            isGameEnded = true;
            btnPlayAgain.setVisibility(View.VISIBLE);
        }
    }

    public boolean IsWinner()
    {
        //Rows (0|1|2 - 3|4|5 - 6|7|8)
        for (int row = 0; row < 3; row++)
        {
            String a = (String) arrImgViews[row * 3].getTag();
            String b = (String) arrImgViews[row * 3 + 1].getTag();
            String c = (String) arrImgViews[row * 3 + 2].getTag();

            //Check if the a is note epmty, then check if a is equal b and b is equal c
            if (a != null && b != null && c != null && a.equals(b) && b.equals(c))
            {
                return true;
            }
        }

        //Cols (0|3|6 - 1|4|7 - 2|5|8)
        for (int col = 0; col < 3; col++)
        {
            String a = (String) arrImgViews[col].getTag();
            String b = (String) arrImgViews[col + 3].getTag();
            String c = (String) arrImgViews[col + 6].getTag();
            if (a != null && b != null && c != null && a.equals(b) && b.equals(c))
            {
                return true;
            }
        }

        //Diagonals
        String a = (String) arrImgViews[0].getTag();
        String b = (String) arrImgViews[4].getTag();
        String c = (String) arrImgViews[8].getTag();
        if (a != null && b != null && c != null && a.equals(b) && b.equals(c))
        {
            return true;
        }

        a = (String) arrImgViews[2].getTag();
        b = (String) arrImgViews[4].getTag();
        c = (String) arrImgViews[6].getTag();
        if (a != null && b != null && c != null && a.equals(b) && b.equals(c))
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
        for (ImageView iv : arrImgViews)
        {
            //Remove the image
            iv.setImageDrawable(null);

            //Remove the tag
            iv.setTag(null);

            isGameEnded = false;

            btnPlayAgain.setVisibility(View.INVISIBLE);

            tvResult.setText(null);

        }

        //Reseting the variables
        movesCounter = 0;
        playerXTurn = true;
    }


}