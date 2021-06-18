package com.example.android.chickenkilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {


    //references to buttons and other controls on layout
    Button btn_add, btn_searchPage;
    EditText et_name, et_address, et_violation, et_date, et_surveyor;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_searchPage = findViewById(R.id.btn_searchPage);
        et_address = findViewById(R.id.et_address);
        et_name = findViewById(R.id.et_name);
        et_violation = findViewById(R.id.et_violation);
        lv_customerList = findViewById(R.id.lv_displayList);
        et_date = findViewById(R.id.et_date);
        et_surveyor = findViewById(R.id.et_surveyor);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowCustomersOnListView(dataBaseHelper);

        //buttons listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), et_address.getText().toString(), et_violation.getText().toString(), et_date.getText().toString(), Integer.parseInt(et_surveyor.getText().toString()));
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creating customer ", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", "", "", "", -1);

                    ShowCustomersOnListView(dataBaseHelper);


                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "Violation added = " + success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateSearchPage();
            }
        });

        /*
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);


                ShowCustomersOnListView(dataBaseHelper);

                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        */

    }

    public void activateSearchPage(){
        Intent intent = new Intent(this, DisplayContacts.class);
        Button button = (Button) findViewById(R.id.btn_searchPage);
        startActivity(intent);
    }

    public void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        //customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
       // lv_customerList.setAdapter(customerArrayAdapter);
    }



}

