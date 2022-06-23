package com.group4.booklistapp.booklist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;

import com.group4.booklistapp.booklist.R;

public class SplashActivity extends AppCompatActivity {

    ImageView square, book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        square = findViewById(R.id.square_splash);
        book = findViewById(R.id.book_splash);

        square.animate().translationY(-400).setDuration(3000).setStartDelay(600);
        book.animate().translationY(50).setDuration(3000).setStartDelay(800);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },3500);

    }
}
