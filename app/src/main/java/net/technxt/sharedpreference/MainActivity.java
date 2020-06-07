package net.technxt.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*instantiate an instance of the SharedPreferences*/
    SharedPreferences sharedPref;
    //Initializing the Editor
    SharedPreferences.Editor editor;
    EditText name;
    EditText address;
    EditText email;
    EditText password;
    Button save, view, clear;
    TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);/*instantiate an instance of the SharedPreferences*/
        editor = sharedPref.edit();//Initializing the Editor

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        save = findViewById(R.id.saveBtn);
        view = findViewById(R.id.viewBtn);
        clear = findViewById(R.id.clearBtn);
        value = findViewById(R.id.value);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllData();
            }
        });

    }

    private void clearAllData() {
        //Clear all data from SharedPreferences
        editor.clear();
        // Save the changes in SharedPreferences
        boolean status = editor.commit();
        if (status){
            Toast.makeText(this,"Data cleared successfully.",Toast.LENGTH_LONG).show();
        }
        //get data and Update the UI
        getData();
    }

    private void saveData() {
        // Check if editText is empty
        if (name.getText().toString().isEmpty()) {
            name.setError("Oops! No Name");
            name.requestFocus();
            return;
        }
        if (address.getText().toString().isEmpty()) {
            address.setError("Oops! No address");
            address.requestFocus();
            return;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Oops! No email");
            email.requestFocus();
            return;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Oops! No password");
            password.requestFocus();
            return;
        }

        editor.putString("name", name.getText().toString());
        editor.putString("address", address.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.putString("pass", password.getText().toString());
        // Save the changes in SharedPreferences
        boolean status = editor.commit();
        if (status){
            Toast.makeText(this,"Data insert successfully.",Toast.LENGTH_LONG).show();
        }
    }

    private void getData() {
        String valueData = "";
        // If value for key_name not exist then return second param value - In this case null
        if (sharedPref.contains("name")) {
            valueData += sharedPref.getString("name", null) + "\n";
        }
        if (sharedPref.contains("address")){
            valueData += sharedPref.getString("address", null) + "\n";
        }
        if (sharedPref.contains("email")){
            valueData += sharedPref.getString("email", null) + "\n";
        }
        if (sharedPref.contains("pass")){
            valueData += sharedPref.getString("pass", null);
        }

        updateUI(valueData);
    }

    private void updateUI(String valueData) {
        value.setText(valueData);
    }

}
