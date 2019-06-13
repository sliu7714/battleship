package com.example.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    private Button makeShips; //allows the player and opponent to "make" ships square by square.
    private Button setShips; // has preset ships that player and opponent can set on board.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_);

        //if make ships button is clicked, then it changes to make ships activity
        makeShips = findViewById(R.id.make_ships_button);
        makeShips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionActivity.this, MakeShipsActivity.class));
            }
        });

        //if set ships button is clicked, then it changes to set ships activity
        setShips = findViewById(R.id.set_ships_btn);
        setShips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionActivity.this,SetShipsActivity.class));
            }
        });
    }
}
