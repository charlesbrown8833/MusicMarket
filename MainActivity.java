/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the MainActivity that creates main page
 * of application and shows recycler view.
 *
 * Class: CITC 2376, Spring 2024
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Instantiate Class objects and Controls
    private JSONSerializer mSerializer;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private ListingAdapter mAdapter;
    private SharedPreferences mPrefs;
    private Button addButton;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set button references and on click listener
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment add = new AddFragment();
                add.show(getSupportFragmentManager(), "");

            }
        });

        //Assign Json serializer object to new Json serializer and pass in filename and context for
        //constructor. Use try/catch to load serialized data from Json serializer class. Create new
        //list if try fails and print message to Logcat.
        mSerializer = new JSONSerializer("ProductListings.json", getApplicationContext());
        try {
            productList = mSerializer.load();
        }
        catch (Exception e) {
            productList = new ArrayList<Product>();
            Log.e("Error Loading Products: ", "", e);
        }

        //Method to build and show the recycler
        buildAndShowRecycler();

    }

    //Recycler view build and show method
    public void buildAndShowRecycler() {
        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ListingAdapter(this, productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    //Method to save listings by passing in List to save method of Json serializer class
    public void saveListings() {
        try {
            mSerializer.save(productList);
        }
        catch (Exception e) {
            Log.e("Error Saving Notes", "", e);
        }
    }

    //Create overflow options menu and inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Method to return boolean value based on action chosen condition
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            AboutDialog about = new AboutDialog();
            about.show(getSupportFragmentManager(), "123");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Method to create new listing that is used inside Adapter class
    @SuppressLint("NotifyDataSetChanged")
    public void createNewListing(Product product) {
        productList.add(product);
        mAdapter.notifyDataSetChanged();
    }

    //Method to get Shared Preferences in the event application is paused and then resumed.
    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("Product Listings", MODE_PRIVATE);
    }

    //Method to save listings in the event application is paused.
    @Override
    protected void onPause() {
        super.onPause();
        saveListings();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("parcelable", product);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.product = savedInstanceState.getParcelable("parcelable");
    }
}