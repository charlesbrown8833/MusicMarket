/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the splash that displays when application
 * is loading to the MainActivity. Also displays animated imageView while loading.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    //Instantiate variables
    Animation animFadeIn;
    ImageView welcomeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Load animations method
        loadAnimations();
        //Load controls method
        loadUI();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Create intent to move into MainActivity and set duration to 5 seconds.
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    //Method to load animation and set duration of animation to 4 seconds(1 less than splash duration).
    private void loadAnimations() {
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeIn.setDuration(4000);
    }

    //Method to assign image reference to view and start animation on the image view.
    private void loadUI() {
        welcomeImage = findViewById(R.id.welcomeImage);
        welcomeImage.startAnimation(animFadeIn);
    }
}
