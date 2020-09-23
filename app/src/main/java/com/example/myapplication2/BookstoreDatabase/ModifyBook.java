package com.example.myapplication2.BookstoreDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.BookstoreDatabase.BookStoreDatabase;
import com.example.myapplication2.BookstoreDatabase.MyDatabaseHelper;
import com.example.myapplication2.R;

public class ModifyBook extends AppCompatActivity {
    EditText addedName;
    EditText addedPrice;
    EditText addedQuantity;

    MyDatabaseHelper myHelper = new MyDatabaseHelper(this);
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_book);
        addedName = (EditText) findViewById(R.id.added_name);
        addedPrice = (EditText) findViewById(R.id.added_price);
        addedQuantity = (EditText) findViewById(R.id.added_quantity);
        myHelper.open();
        setIntentData();
    }

    public void setIntentData(){
        Intent intent = getIntent();
        String _id= intent.getStringExtra("id");
        String _name= intent.getStringExtra("name");
        String _price= intent.getStringExtra("price");
        String _quantity= intent.getStringExtra("quantity");
        id = Long.parseLong(_id);
        addedPrice.setText(_price);
        addedName.setText(_name);
        addedQuantity.setText(_quantity);
    }

    public void returnToMainActivity(){
        Intent main = new Intent(this, BookStoreDatabase.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);

    }
    public void deleteProduct(View view) {
        myHelper.delete(id);
        returnToMainActivity();
    }

    public void updateProduct(View view) {
        String _name= addedName.getText().toString();
        String _price=  addedPrice.getText().toString();
        String _quantity=  addedQuantity.getText().toString();

        myHelper.update(id,_name,_price,_quantity);
        returnToMainActivity();
    }
}
