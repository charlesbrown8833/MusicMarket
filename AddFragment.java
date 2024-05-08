/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the dialog fragment that allows the user to
 * a new listing to the recycler view for display.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class AddFragment extends DialogFragment {
    //Instantiate controls and Product class to create object
    private RadioGroup mRadioGroup;
    private ImageView mImageView;
    Product newProduct = new Product();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Build the AlertDialog to inflate the fragment layout and create View for dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_add, null);

        //Instantiate and assign controls for the dialog
        final EditText editBrand = dialogView.findViewById(R.id.editBrand);
        final EditText editModel = dialogView.findViewById(R.id.editModel);
        final EditText editPrice = dialogView.findViewById(R.id.editPrice);
        final EditText editCity = dialogView.findViewById(R.id.editCity);
        final EditText editState = dialogView.findViewById(R.id.editState);
        Button addButton = dialogView.findViewById(R.id.addButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        mImageView = dialogView.findViewById(R.id.myImageView);
        mRadioGroup = dialogView.findViewById(R.id.radioGroup);

        //Pass in dialog View created above and pass into builder
        builder.setView(dialogView);

        //Button to cancel listing and return to Main Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //Button to set data of Product object for user to add listing and return to Main Activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newProduct.setBrand(editBrand.getText().toString());
                newProduct.setModel(editModel.getText().toString());
                newProduct.setPrice(Double.parseDouble(editPrice.getText().toString()));
                newProduct.setCity(editCity.getText().toString());
                newProduct.setState(editState.getText().toString());

                MainActivity calling = (MainActivity) getActivity();
                calling.createNewListing(newProduct);

                dismiss();
            }
        });

        // Clears radio buttons so that user can choose form the 3.
        mRadioGroup.clearCheck();

        //OnCheckedChangedListener that sets Product type based on selected radio button.
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Instantiate radiobutton and assign to View while checking the selection and setting type.
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                switch (rb.getId()) {
                    case R.id.guitarRadioButton:
                        newProduct.setType("Guitar");
                        mImageView.setImageResource(R.drawable.guitar);
                        break;
                    case R.id.drumRadioButton:
                        newProduct.setType("Drum");
                        mImageView.setImageResource(R.drawable.drums);
                        break;
                    case R.id.micRadioButton:
                        newProduct.setType("Mic");
                        mImageView.setImageResource(R.drawable.mic);
                        break;
                }
            }
        });

        //return the builder object
        return builder.create();
    }

}
