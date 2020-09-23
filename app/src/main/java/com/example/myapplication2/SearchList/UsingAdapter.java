package com.example.myapplication2.SearchList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.myapplication2.R;

public class UsingAdapter extends AppCompatActivity {
   // Global to access it in all functions
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_adapter);
        // simple array adapter
        // Accessing the resource File of the array string
        String[] stringArray = getResources().getStringArray(R.array.custom_string_array);
        // Create the adapter taking three arguments ( context , layout containg textview, data)
        // What is a context ? getting the current state to handle resources or database
        adapter = new ArrayAdapter<String>(this,R.layout.my_string_array, stringArray);
        // Configure set The adapter ( connection with the UI
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);


        // custom adapter
        // create an arrayList
//        ArrayList<User> users = new ArrayList<User>();
        // Create useradapter
//        UserAdapter userAdapter = new UserAdapter(this,users);
        // set adapter
//        ListView listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(userAdapter);

//        User user1= new User("Maryam1","Nabil1");
//        User user2= new User("Maryam2","Nabil2");
        // adding them to adapter
//        userAdapter.add(user1);
//        userAdapter.add(user2);

    }

    // To Modify The Acrion bar and add a search bar we should override onCreateOptionsMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // MenuInflatter is an object that is able to create Menu from xml resources
        MenuInflater Inflater= getMenuInflater();
        Inflater.inflate(R.menu.search_options, menu);
        MenuItem item = menu.findItem(R.id.search);
        //importing search widget which is a shortcut to a piece of contained code
        SearchView searchView = (SearchView) item.getActionView();
        // Get actionView return the modifier for this item
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
