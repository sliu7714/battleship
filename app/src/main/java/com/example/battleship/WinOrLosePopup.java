package com.example.battleship;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class WinOrLosePopup extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //boolean to store if player won or not
        boolean win = getArguments().getBoolean("win");
        //string to store the win or lose message
        String msg;
        if (win)
            msg = "You win! :)";
        else
            msg = "You lose :(";

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(msg)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //when play again is clicked it changes activity back to place ships
                        startActivity(new Intent(getContext(), SelectionActivity.class));
                    }
                })
                .create();

        return dialog;
    }

}
