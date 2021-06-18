package com.example.android.chickenkilla;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayContacts extends AppCompatActivity {

    Button btn_search;
    EditText et_searchName;
    ListView lv_displayList;

    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(DisplayContacts.this);

        Button button = (Button) findViewById(R.id.btn_search);
        lv_displayList = findViewById(R.id.lv_displayList);
        et_searchName = findViewById(R.id.et_searchName);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String searchTerms = et_searchName.getText().toString();
                showCustomersOnListView(v, searchTerms);
            }
        });
    }

    public void showCustomersOnListView(View v, String searchTerms) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(DisplayContacts.this);
        List list = dataBaseHelper.getSearch("");
        if(list.isEmpty()){
            System.out.println("The list is empty");
        }else {
            customerArrayAdapter = new ArrayAdapter<CustomerModel>(DisplayContacts.this, android.R.layout.simple_expandable_list_item_1, dataBaseHelper.getSearch(searchTerms));
            lv_displayList.setAdapter(customerArrayAdapter);
        }
    }
}

