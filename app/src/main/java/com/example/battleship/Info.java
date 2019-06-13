package com.example.battleship;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class Info extends DialogFragment {

    ///NOT USED

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Info\n" +
                        "Click on your opponent's board to to attack.\n" +
                        "Your opponent will then attack your board.\n" +
                        "Blue squares represent water\n" +
                        "Grey squares represent ships\n" +
                        "Red squares represent ships that have been attacked" +
                        "Dark Blue squares represent water that have been attacked\n" +
                        "Both you and the opponent have 10 ships\n")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        return dialog;
    }
}
