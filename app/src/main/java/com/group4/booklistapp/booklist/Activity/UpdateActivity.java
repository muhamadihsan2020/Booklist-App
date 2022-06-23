package com.group4.booklistapp.booklist.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group4.booklistapp.booklist.API.ApiRequestData;
import com.group4.booklistapp.booklist.API.RetroServer;
import com.group4.booklistapp.booklist.Model.DataModel;
import com.group4.booklistapp.booklist.Model.ResponseModel;
import com.group4.booklistapp.booklist.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button, delete_button;

    private int id, pages;
    private String title, author;
    private List<DataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().hide();

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        title = title_input.getText().toString();
        author = author_input.getText().toString();
        pages = Integer.valueOf(pages_input.getText().toString());

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Refresh Activity
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        ApiRequestData ardData = RetroServer.con().create(ApiRequestData.class);
        Call<ResponseModel> GetData = ardData.ardGetData(id);

        GetData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int code = response.body().getCode();
                String status = response.body().getStatus();
                dataList = response.body().getData();

                int book_id = dataList.get(0).getId();
                String book_title = dataList.get(0).getTitle();
                String book_author = dataList.get(0).getAuthor();
                int book_pages = dataList.get(0).getPages();

                title_input.setText(book_title);
                author_input.setText(book_author);
                pages_input.setText(String.valueOf(book_pages));

                Toast.makeText(UpdateActivity.this, "Code: " + code + " | Status: " + status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error Get Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ApiRequestData ardData = RetroServer.con().create(ApiRequestData.class);
                Call<ResponseModel> DropData = ardData.ardDeleteData(id);

                DropData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int code = response.body().getCode();
                        String status = response.body().getStatus();
                        Toast.makeText(UpdateActivity.this, "Code: " + code + " | Status: " + status, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UpdateActivity.this, "Error Deleted Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
