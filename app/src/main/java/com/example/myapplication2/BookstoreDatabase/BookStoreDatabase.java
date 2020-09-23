package com.example.myapplication2.BookstoreDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.myapplication2.R;

public class BookStoreDatabase extends AppCompatActivity {
    MyDatabaseHelper myHelper = new MyDatabaseHelper(this);
    private ListView listView ;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[]{myHelper._ID,myHelper.NAME,myHelper.PRICE,myHelper.QUANTITY};
    final int[] to = new int[] {R.id.record_id,R.id.record_name,R.id.record_price, R.id.record_quantity};
    String id;
    String name;
    String price;
    String quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store_database);

        listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        myHelper.open();
        Cursor c = myHelper.getProducts();
        adapter = new SimpleCursorAdapter(this,R.layout.book_record,c,from , to , 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long _id) {

                id = ((TextView)view.findViewById(R.id.record_id)).getText().toString();
                name =  ((TextView)view.findViewById(R.id.record_name)).getText().toString();
                price =  ((TextView)view.findViewById(R.id.record_price)).getText().toString();
                quantity = ((TextView)view.findViewById(R.id.record_quantity)).getText().toString();
                Intent modify = new Intent(getApplicationContext(), ModifyBook.class);
                modify.putExtra("id",id);
                modify.putExtra("name",name);
                modify.putExtra("price",price);
                modify.putExtra("quantity",quantity);
                startActivity(modify);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.add:
            {
                Intent addActivity = new Intent(this, AddBook.class);
                this.startActivity(addActivity);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
