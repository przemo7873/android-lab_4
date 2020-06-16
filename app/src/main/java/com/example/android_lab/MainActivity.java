package com.example.android_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] values = new String[] { "Pies", "Kot", "Koń", "Gołąb",
        "Kruk", "Dzik", "Karp", "Osioł", "Chomik", "Mysz", "Jeż"};
        this.target = new ArrayList<String>();
        this.target.addAll(Arrays.asList(values));
        this.adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,db.lista(),
                new String[]{"_id","gatunek"},
                new int[] {android.R.id.text1,android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(this.adapter);
    }
    private MySQLite db;
    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    public void nowyWpis(MenuItem mi)
    {
        Intent intencja = new Intent(this,DodajWpis.class);
        startActivityForResult(intencja,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String nowy = (String) extras.get("wpis");
            target.add(nowy);
            adapter.notifyDataSetChanged();
        }
    }


}
