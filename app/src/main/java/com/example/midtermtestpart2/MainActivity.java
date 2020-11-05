package com.example.midtermtestpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    MySQLiteHelper mydb=null;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new MySQLiteHelper(this,null,null,1);

        list = (ListView) findViewById(R.id.lstBreed);
        ArrayList<String> arrayDogs = mydb.retrieveRecords();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayDogs);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String temp = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(MainActivity.this,temp ,Toast.LENGTH_LONG).show();

    }


    //Part1: implement this method
    public void showBreedImage(View v){

    }
}
