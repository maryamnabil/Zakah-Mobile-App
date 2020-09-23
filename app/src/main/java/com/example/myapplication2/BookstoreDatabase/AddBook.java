package com.example.myapplication2.BookstoreDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication2.R;

public class AddBook extends AppCompatActivity {
    // Creating an instance from helper
    MyDatabaseHelper myHelper = new MyDatabaseHelper(this);
    EditText added_name;
    EditText added_price;
    EditText added_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        myHelper.open();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Values of EditTexts
        added_name = (EditText) findViewById(R.id.added_name);
        added_price = (EditText) findViewById(R.id.added_price);
        added_quantity = (EditText) findViewById(R.id.added_quantity);
    }

    public void addProduct(View view) {
        // Convert to strings
        String name =  added_name.getText().toString();
        String price =  added_price.getText().toString();
        String quantity = added_quantity.getText().toString();
        myHelper.insert(name,price,quantity);
        Intent main = new Intent(this, BookStoreDatabase.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
