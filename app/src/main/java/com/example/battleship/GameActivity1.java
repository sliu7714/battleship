package com.example.battleship;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GameActivity1 extends AppCompatActivity {

    private int playerBoard[][] = new int[6][6]; // array to store player's board
    private int opBoard[][] = new int[6][6];// array to store opponent's board
    //0 = water - blue
    //1 = ship - grey
    //2 = attacked (no ship) - darkBlue
    //3 = attacked (ship) - red

    //colours
    private String grey = "#8B8F91"; //ship
    private String darkBlue = "#166DA8"; //attacked ship
    private String red = "#DF0C00";//attacked noship

    //scores
    private int playerShips = 10;
    private int opShips = 10;

    //button that leads to popup with instructions
    private ImageButton info;
    private Button ok; //button part of info layout to close info layout
    private ConstraintLayout infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        infoLayout = findViewById(R.id.info_layout);

        //if info button clicked popup appears
        info = findViewById(R.id.info_btn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //originally wanted to make a popup with dialog but it doesn't display images for the squares
//                Info infoPopup = new Info();
//                infoPopup.show(getSupportFragmentManager(),"comms");
                infoLayout.setVisibility(v.VISIBLE);
            }
        });


        //if ok button in info layout is clicked then the layout disappears
        ok = findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoLayout.setVisibility(v.GONE);
            }
        });

        //populating opponent board array with 0 (water)
        //x is row y is column
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++)
                opBoard[x][y] = 0;

        int length, width; //for the place of ships
        int counter = opShips;
        while (counter > 0) {
            //placing a ship in a random place in the array if there isn't already a ship there
            length = (int) (Math.random() * 6);
            width = (int) (Math.random() * 6);

            if (opBoard[length][width] != 1) {
                opBoard[length][width] = 1;
                counter--;
            }
        }

        //getting playerBoard array from place ships activity
        Bundle bundle = getIntent().getExtras();
        final String[][] playerBoardStr = (String[][]) bundle.getSerializable("playerBoard1");

        //converting playerBoard array from string to int
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++)
                playerBoard[x][y] = Integer.parseInt(playerBoardStr[x][y]);

        //setting buttons to be the buttons on playergrid
        //ROW A
        Button a1_op = findViewById(R.id.a1_op);
        Button a2_op = findViewById(R.id.a2_op);
        Button a3_op = findViewById(R.id.a3_op);
        Button a4_op = findViewById(R.id.a4_op);
        Button a5_op = findViewById(R.id.a5_op);
        Button a6_op = findViewById(R.id.a6_op);
        //ROW B
        Button b1_op = findViewById(R.id.b1_op);
        Button b2_op = findViewById(R.id.b2_op);
        Button b3_op = findViewById(R.id.b3_op);
        Button b4_op = findViewById(R.id.b4_op);
        Button b5_op = findViewById(R.id.b5_op);
        Button b6_op = findViewById(R.id.b6_op);
        //ROW C
        Button c1_op = findViewById(R.id.c1_op);
        Button c2_op = findViewById(R.id.c2_op);
        Button c3_op = findViewById(R.id.c3_op);
        Button c4_op = findViewById(R.id.c4_op);
        Button c5_op = findViewById(R.id.c5_op);
        Button c6_op = findViewById(R.id.c6_op);
        //ROW D
        Button d1_op = findViewById(R.id.d1_op);
        Button d2_op = findViewById(R.id.d2_op);
        Button d3_op = findViewById(R.id.d3_op);
        Button d4_op = findViewById(R.id.d4_op);
        Button d5_op = findViewById(R.id.d5_op);
        Button d6_op = findViewById(R.id.d6_op);
        //ROW E
        Button e1_op = findViewById(R.id.e1_op);
        Button e2_op = findViewById(R.id.e2_op);
        Button e3_op = findViewById(R.id.e3_op);
        Button e4_op = findViewById(R.id.e4_op);
        Button e5_op = findViewById(R.id.e5_op);
        Button e6_op = findViewById(R.id.e6_op);
        //Row F
        Button f1_op = findViewById(R.id.f1_op);
        Button f2_op = findViewById(R.id.f2_op);
        Button f3_op = findViewById(R.id.f3_op);
        Button f4_op = findViewById(R.id.f4_op);
        Button f5_op = findViewById(R.id.f5_op);
        Button f6_op = findViewById(R.id.f6_op);

        //array to store buttons on opponent's board
        final Button[] opButtons = {a1_op, a2_op, a3_op, a4_op, a5_op, a6_op,
                b1_op, b2_op, b3_op, b4_op, b5_op, b6_op,
                c1_op, c2_op, c3_op, c4_op, c5_op, c6_op,
                d1_op, d2_op, d3_op, d4_op, d5_op, d6_op,
                e1_op, e2_op, e3_op, e4_op, e5_op, e6_op,
                f1_op, f2_op, f3_op, f4_op, f5_op, f6_op};

//        //TEST -- showing opponent's board
//        //changing colours of buttons on opponent grid to match array
//        for (int x = 0; x < 6; x++)
//            for (int y = 0; y < 6; y++) {
//                // if there is a ship there the button at that place will be grey
//                if (opBoard[x][y] == 1)
//                    opButtons[6 * x + y].setBackgroundColor(Color.parseColor(grey));
//            }
//        //END TEST

        //setting buttons to be the buttons on playergrid
        //ROW A
        Button a1 = findViewById(R.id.a1);
        Button a2 = findViewById(R.id.a2);
        Button a3 = findViewById(R.id.a3);
        Button a4 = findViewById(R.id.a4);
        Button a5 = findViewById(R.id.a5);
        Button a6 = findViewById(R.id.a6);
        //ROW B
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        //ROW C
        Button c1 = findViewById(R.id.c1);
        Button c2 = findViewById(R.id.c2);
        Button c3 = findViewById(R.id.c3);
        Button c4 = findViewById(R.id.c4);
        Button c5 = findViewById(R.id.c5);
        Button c6 = findViewById(R.id.c6);
        //ROW D
        Button d1 = findViewById(R.id.d1);
        Button d2 = findViewById(R.id.d2);
        Button d3 = findViewById(R.id.d3);
        Button d4 = findViewById(R.id.d4);
        Button d5 = findViewById(R.id.d5);
        Button d6 = findViewById(R.id.d6);
        //ROW E
        Button e1 = findViewById(R.id.e1);
        Button e2 = findViewById(R.id.e2);
        Button e3 = findViewById(R.id.e3);
        Button e4 = findViewById(R.id.e4);
        Button e5 = findViewById(R.id.e5);
        Button e6 = findViewById(R.id.e6);
        //Row F
        Button f1 = findViewById(R.id.f1);
        Button f2 = findViewById(R.id.f2);
        Button f3 = findViewById(R.id.f3);
        Button f4 = findViewById(R.id.f4);
        Button f5 = findViewById(R.id.f5);
        Button f6 = findViewById(R.id.f6);


        //array to store buttons on player's board (need to be defined after buttons are found)
        final Button[] playerButtons = {a1, a2, a3, a4, a5, a6,
                b1, b2, b3, b4, b5, b6,
                c1, c2, c3, c4, c5, c6,
                d1, d2, d3, d4, d5, d6,
                e1, e2, e3, e4, e5, e6,
                f1, f2, f3, f4, f5, f6};

        //changing colours of buttons on player grid to match array
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++) {
                // if there is a ship there the button at that place will be grey
                if (playerBoard[x][y] == 1)
                    playerButtons[6 * x + y].setBackgroundColor(Color.parseColor(grey));
            }

        //allowing player to click on opponent's board to attack
        //setting OnClickListeners for each button
        for (int x = 0; x < opButtons.length; x++) {
            final Button currentButton = opButtons[x];
            currentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //changing the array to show that there is a ship there
                //tags holds row(0) column(1)
                int row = Character.getNumericValue(currentButton.getTag().toString().charAt(0));
                int column = Character.getNumericValue(currentButton.getTag().toString().charAt(1));
                Boolean validTurnPlayer = false; // if player's turn is valid

                //if player already selected (attacked) the spot
                if (opBoard[row][column] == 2 || opBoard[row][column] == 3)
                    Toast.makeText(GameActivity1.this,"You already attacked there!",Toast.LENGTH_SHORT).show();
                //if the button that player clicked has a ship there on opBoard
                else if (opBoard[row][column] == 1) {
                    opBoard[row][column] = 3;
                    opButtons[6 * row + column].setBackgroundColor(Color.parseColor(red));
                    opShips--; // the number of valid opponent ships decreases
                    validTurnPlayer = true;
                }
                //if the button that player clicked has water there on opBoard
                else if (opBoard[row][column] == 0){
                    opBoard[row][column] = 2;
                    opButtons[6*row+ column].setBackgroundColor(Color.parseColor(darkBlue));
                    validTurnPlayer = true;
                }

                //if player wins and all of opponent's ships have sunk
                if (opShips == 0){
                    //making a popup that allows player to play again
                    WinOrLosePopup popup = new WinOrLosePopup();
                    Bundle result = new Bundle();
                    result.putBoolean("win", true);
                    popup.setArguments(result);

                    popup.show(getSupportFragmentManager(),"comms");

                    //Toast.makeText(GameActivity1.this,"You Win! :)", Toast.LENGTH_SHORT).show(); //TEST
                }
                //if the player had a valid turn(made a selection)
                int length,width;
                boolean validTurn; //if the opponent's turn is valid
                if (validTurnPlayer){
                    validTurn = false;
                    do{
                        length = (int) (Math.random() * 6);
                        width = (int) (Math.random() * 6);

                        //if there is a ship at playboard the color changes to ht
                        if (playerBoard[length][width] == 1){
                            playerBoard[length][width] = 3; //attacked (ship)
                            playerButtons[6*length+ width].setBackgroundColor(Color.parseColor(red));
                            validTurn = true;
                            playerShips--;
                        }
                        //if there is water at player board
                        else if (playerBoard[length][width] == 0){
                            playerBoard[length][width] = 2; // attacked (no  ship)
                            playerButtons[6*length+ width].setBackgroundColor(Color.parseColor(darkBlue));
                            validTurn = true;
                        }
                        if (playerShips == 0){
                            //making a popup that allows player to play again
                            WinOrLosePopup popup = new WinOrLosePopup();

                            Bundle result = new Bundle();
                            result.putBoolean("win", false);
                            popup.setArguments(result);

                            popup.show(getSupportFragmentManager(),"comms");

                            //Toast.makeText(GameActivity1.this,"You Lose :(" + playerShips, Toast.LENGTH_SHORT).show(); //TEST
                        }
                    }while (!validTurn);
                }
                }

            });
        }
    }
}
