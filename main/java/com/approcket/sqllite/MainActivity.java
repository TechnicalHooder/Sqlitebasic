package com.approcket.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText edt, edt1, edt2, edt3;
    private Button btn, btn1, btn2,btn3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt = findViewById(R.id.edt);
        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = databaseHelper.inserted(edt.getText().toString(),
                        edt1.getText().toString(),
                        edt2.getText().toString(),
                        edt3.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = databaseHelper.getAllData();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    //showMessage("Error", "no data found");
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("Name: " + res.getString(1) + "\n");
                        buffer.append("Sirname: " + res.getString(2) + "\n");
                        buffer.append("Marks: " + res.getString(3) + "\n\n");
                          Toast.makeText(MainActivity.this, "data:\n"+buffer, Toast.LENGTH_SHORT).show();
                      //  showMessage("Data", buffer.toString());
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = databaseHelper.updateData(edt.getText().toString(),
                        edt1.getText().toString(),
                        edt2.getText().toString(),
                        edt3.getText().toString());
                if (isUpdated = true) {

                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted=databaseHelper.deleteData(edt.getText().toString());
                if(isDeleted=true)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}