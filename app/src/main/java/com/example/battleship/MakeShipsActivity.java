package com.example.battleship;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MakeShipsActivity extends AppCompatActivity {

    private int playerBoard[][] = new int [6][6]; // array to store player's board
    //0 = water - blue
    //1 = ship - grey
    //2 = attacked (no ship) - dark blue
    //3 = attacked (ship) - red

    private int shipCount = 10; // the number of ship squares that the player can
    private TextView shipCounter;
    private String grey = "#8B8F91"; //colour for the ships
    private Button continueBtn;

    //button that leads to popup with instructions
    private ImageButton info;
    private Button ok; //button part of info layout to close info layout
    private ConstraintLayout infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_ships);

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

        shipCounter = findViewById(R.id.ship_count_tv);
        //setting textview to show how many ships are left
        shipCounter.setText("You have " + shipCount + " ships left");

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
                Intent passIntent = new Intent(MakeShipsActivity.this, GameActivity1.class);
                bundle.putSerializable("playerBoard1",playerBoardStr);
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
        Button[] playerButtons ={a1,a2,a3,a4,a5,a6,
                                b1,b2,b3,b4,b5,b6,
                                c1,c2,c3,c4,c5,c6,
                                d1,d2,d3,d4,d5,d6,
                                e1,e2,e3,e4,e5,e6,
                                f1,f2,f3,f4,f5,f6};

        //setting OnClickListeners for each button
        for (int x = 0;x< playerButtons.length; x++){
            final Button currentButton = playerButtons[x];
            currentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //changing the array to show that there is a ship there
                    //tags holds row(0) column(1)
                    int row = Character.getNumericValue(currentButton.getTag().toString().charAt(0));
                    int column = Character.getNumericValue(currentButton.getTag().toString().charAt(1));

                    //if place on board doesn't have ship on it and shipcount is not zero;
                    if (playerBoard[row][column] != 1 && shipCount !=0){
                        playerBoard[row][column] = 1;
                        shipCount --; //one ship has been placed
                        currentButton.setBackgroundColor(Color.parseColor(grey)); //setting background colour to grey(ship)
                        //Toast.makeText(this, " button clicked", Toast.LENGTH_SHORT).show(); //TEST
                        shipCounter.setText("You have " + shipCount + " ships left"); //updating ship count text view

                        //if the last ship has been placed , the continue button will become visible
                        if (shipCount == 0)
                            continueBtn.setVisibility(v.VISIBLE);
                    }
                    else if (shipCount == 0)//if player ran out of ships
                        Toast.makeText(MakeShipsActivity.this,"You placed all your ships already! Please press continue",Toast.LENGTH_SHORT).show();
                    else// if a ship is already placed there (and there are still ships left to place) msg is displayed
                        Toast.makeText(MakeShipsActivity.this,"You already placed a ship there!",Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}


//    //need to define every button
//    private Button a1;
//    private Button a2;
//    private Button a3;
//    private Button a4;
//    private Button a5;
//    private Button a6;
//    private Button b1;
//    private Button b2;
//    private Button b3;
//    private Button b4;
//    private Button b5;
//    private Button b6;
//    private Button c1;
//    private Button c2;
//    private Button c3;
//    private Button c4;
//    private Button c5;
//    private Button c6;
//    private Button d1;
//    private Button d2;
//    private Button d3;
//    private Button d4;
//    private Button d5;
//    private Button d6;
//    private Button e1;
//    private Button e2;
//    private Button e3;
//    private Button e4;
//    private Button e5;
//    private Button e6;
//    private Button f1;
//    private Button f2;
//    private Button f3;
//    private Button f4;
//    private Button f5;
//    private Button f6;
