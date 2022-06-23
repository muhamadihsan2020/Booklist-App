package com.group4.booklistapp.booklist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group4.booklistapp.booklist.API.ApiRequestData;
import com.group4.booklistapp.booklist.API.RetroServer;
import com.group4.booklistapp.booklist.Model.ResponseModel;
import com.group4.booklistapp.booklist.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private EditText title_input, author_input, pages_input;
    private Button add_button;
    private String title, author;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = title_input.getText().toString();
                author = author_input.getText().toString();
                pages = Integer.valueOf(pages_input.getText().toString());

                if(title.trim().equals("")) {
                    title_input.setError("Title is Required");
                }
                else if(author.trim().equals("")) {
                    author_input.setError("Author is Required");
                }
                else {
                    createData();
                }

                //Refresh Activity
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createData() {
        ApiRequestData ardData = RetroServer.con().create(ApiRequestData.class);
        Call<ResponseModel> addData = ardData.ardCreateData(title, author, pages);

        addData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int code = response.body().getCode();
                String status = response.body().getStatus();
                Toast.makeText(AddActivity.this, "Code: " + code + " | Status: " + status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Error Add Data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
