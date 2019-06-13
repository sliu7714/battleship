package com.example.battleship;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SetShipsActivity extends AppCompatActivity {

    private int playerBoard[][] = new int [6][6]; // array to store player's board
    //0 = water - blue
    //1 = ship - grey
    //2 = attacked (no ship) - dark blue
    //3 = attacked (ship) - red

    private String grey = "#8B8F91"; //colour for the ships
    private Button continueBtn; //when current ship = 6 (all ships have been placed) it will appear

    //gridlayouts for ships
    private android.support.v7.widget.GridLayout ship0;
    private android.support.v7.widget.GridLayout ship1;
    private android.support.v7.widget.GridLayout ship2;
    private android.support.v7.widget.GridLayout ship3;
    private android.support.v7.widget.GridLayout ship4;
    private android.support.v7.widget.GridLayout ship5;


    private int currentShip = 0; //count of ship that currently needs to be placed

    //button that leads to popup with instructions
    private ImageButton info;
    private Button ok; //button part of info layout to close info layout
    private ConstraintLayout infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ships);

        //if info button clicked popup appears
        infoLayout = findViewById(R.id.info_layout);
        info = findViewById(R.id.info_btn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        //populating player board array with 0 (water)
        //x is row y is column
        for(int x = 0; x< 6; x++)
            for (int y = 0; y< 6; y++)
                playerBoard[x][y]= 0;

        //setting continue button
        continueBtn = findViewById(R.id.continue_btn);
        //if the continue button is clicked, then the main game activity starts
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to convert playerBoard array from int array to string array to pass it to the other activity
                String playerBoardStr[][] = new String[6][6];
                for (int length = 0; length< 6; length++)
                    for (int width = 0; width<6; width++)
                        playerBoardStr[length][width] = String.valueOf(playerBoard[length][width]);

                //passing the playerBoard array to main game activity
                Bundle bundle = new Bundle();
                Intent passIntent = new Intent(SetShipsActivity.this, GameActivity2.class);
                bundle.putSerializable("playerBoard2",playerBoardStr);
                passIntent.putExtras(bundle);
                startActivity(passIntent); //starts intent and switches activity
            }
        });


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

        //array to store buttons on player's board
        final Button[] playerButtons = {a1,a2,a3,a4,a5,a6,
                                        b1,b2,b3,b4,b5,b6,
                                        c1,c2,c3,c4,c5,c6,
                                        d1,d2,d3,d4,d5,d6,
                                        e1,e2,e3,e4,e5,e6,
                                        f1,f2,f3,f4,f5,f6};

        //setting all the ships
        ship0 = findViewById(R.id.ship0);
        ship1 = findViewById(R.id.ship1);
        ship2 = findViewById(R.id.ship2);
        ship3 = findViewById(R.id.ship3);
        ship4 = findViewById(R.id.ship4);
        ship5 = findViewById(R.id.ship5);

        //setting OnClickListeners for each button
        for (int x = 0;x< playerButtons.length; x++){
            final int w = x;
            playerButtons[w].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //changing the array to show that there is a ship there
                //tags holds row(0) column(1)
                int row = Character.getNumericValue(playerButtons[w].getTag().toString().charAt(0));
                int column = Character.getNumericValue(playerButtons[w].getTag().toString().charAt(1));

                //if place on board doesn't have ship on it
                if (currentShip == 0 && (row +1) < 6){ //to make sure that ship is placed within the array
                    if (playerBoard[row][column] != 1 && playerBoard[row+1][column]!= 1 ) {
                        playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                        playerButtons[w + 6].setBackgroundColor(Color.parseColor((grey))); //square below top left square
                        //updating playerboard array
                        playerBoard[row][column] = 1;
                        playerBoard[row+1][column] = 1;
                        //setting next ship to be visible
                        ship0.setVisibility(View.GONE);
                        ship1.setVisibility(View.VISIBLE);
                        currentShip++; //moving on to the next ship
                    }
                    else // if a ship is already placed there msg is displayed
                        Toast.makeText(SetShipsActivity.this,"You can't place a ship there!",Toast.LENGTH_SHORT).show();
                }
                else if (currentShip == 1 && (column +1) < 6){
                    if (playerBoard[row][column] != 1 && playerBoard[row][column+1]!= 1) {
                        playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                        playerButtons[w + 1].setBackgroundColor(Color.parseColor((grey))); //square to the right of top left square
                        //updating playerboard array
                        playerBoard[row][column] = 1;
                        playerBoard[row][column + 1] = 1;
                        //setting next ship to be visible
                        ship1.setVisibility(View.GONE);
                        ship2.setVisibility(View.VISIBLE);
                        currentShip++; //moving on to the next ship
                    }
                    else // if a ship is already placed there msg is displayed
                        Toast.makeText(SetShipsActivity.this,"You can't place a ship there!",Toast.LENGTH_SHORT).show();
                }
                else if (currentShip == 2 && playerBoard[row][column] != 1) { // since only one square it can be within one else if loop
                    playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                    //updating playerboard array
                    playerBoard[row][column] = 1;
                    //setting next ship to be visible
                    ship2.setVisibility(View.GONE);
                    ship3.setVisibility(View.VISIBLE);
                    currentShip++; //moving on to the next ship
                }
                else if (currentShip == 3 && ((row +1) < 6) && ((column +1) < 6)){
                    if (playerBoard[row][column] != 1 && playerBoard[row][column+1]!= 1 && playerBoard[row+1][column] != 1) {
                        playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                        playerButtons[w + 1].setBackgroundColor(Color.parseColor((grey))); //square to the right of top left square
                        playerButtons[w + 6].setBackgroundColor(Color.parseColor((grey))); //square below top left square

                        // updating playerboard array
                        playerBoard[row][column] = 1;
                        playerBoard[row][column + 1] = 1;
                        playerBoard[row+1][column] = 1;
                        //setting next ship to be visible
                        ship3.setVisibility(View.GONE);
                        ship4.setVisibility(View.VISIBLE);
                        currentShip++; //moving on to the next ship
                    }
                    else // if a ship is already placed there msg is displayed
                        Toast.makeText(SetShipsActivity.this,"You can't place a ship there!",Toast.LENGTH_SHORT).show();
                }

                else if (currentShip == 4 && playerBoard[row][column] != 1) {
                    playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                    //updating playerboard array
                    playerBoard[row][column] = 1;
                    //setting next ship to be visible
                    ship4.setVisibility(View.GONE);
                    ship5.setVisibility(View.VISIBLE);
                    currentShip++; //moving on to the next ship
                }

                else if (currentShip == 5 && ((column +1) < 6)&& ((column +1) < 6)){
                    if (playerBoard[row][column] != 1 && playerBoard[row][column+1]!= 1 && playerBoard[row][column+2] != 1) {
                        playerButtons[w].setBackgroundColor(Color.parseColor(grey));
                        playerButtons[w + 1].setBackgroundColor(Color.parseColor((grey))); //square to the right of top left square
                        playerButtons[w + 2].setBackgroundColor(Color.parseColor((grey))); //square to the right of middle square
                        //updating playerboard array
                        playerBoard[row][column] = 1;
                        playerBoard[row][column + 1] = 1;
                        playerBoard[row][column + 2] = 1;
                        //setting next ship to be visible
                        ship5.setVisibility(View.GONE);
                        continueBtn.setVisibility(View.VISIBLE);
                    }
                    else // if a ship is already placed there msg is displayed
                        Toast.makeText(SetShipsActivity.this,"You can't place a ship there!",Toast.LENGTH_SHORT).show();
                }
                else if (currentShip == 6 )//if player placed all of their
                    Toast.makeText(SetShipsActivity.this,"You placed all your ships already! Please press continue",Toast.LENGTH_SHORT).show();
                else // if a ship is already placed there or cant be placed msg is displayed
                    Toast.makeText(SetShipsActivity.this,"You can't place a ship there!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
