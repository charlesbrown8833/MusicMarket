/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the Dialog that shows the about info
 * for the application.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

//Creates Dialog Fragment and AlertDialog builder to inflate the settings layout and pass in the
//View to the builder and return the created builder.
public class AboutDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.settings_about, null);

        builder.setView(dialogView);

        return builder.create();
    }
}
