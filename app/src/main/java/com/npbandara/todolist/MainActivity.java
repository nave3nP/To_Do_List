package com.npbandara.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper appDb;
    EditText inputTask,inputDate,inputId,inputSId;
    Button btnAddData,btnUpdateData,btnDeleteData,btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        appDb = new DatabaseHelper(this);

        //Define Edit Texts and Buttons

        inputTask = findViewById(R.id.taskInput);
        inputSId = findViewById(R.id.sId);
        inputDate = findViewById(R.id.dateInput);
        inputId = findViewById(R.id.updateTask);
        btnAddData = findViewById(R.id.addBtn);
        btnViewData = findViewById(R.id.viewTasks);
        btnDeleteData = findViewById(R.id.deleteTask);
        btnUpdateData = findViewById(R.id.taskUpdateBtn);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    //Implement Add Data Method
    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = appDb.insertData(inputSId.getText().toString(),inputTask.getText().toString(),inputDate.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Added", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Implement viewAll method
    public void viewAll(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor results = appDb.getAllData();
                if(results.getCount()==0){
                    showMessage("Error Message","No Data Found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("ID :" +results.getString(0)+ "\n");
                    buffer.append("Student ID :" +results.getString(1)+ "\n");
                    buffer.append("Task Info :" +results.getString(2)+ "\n");
                    buffer.append("Due Date :" +results.getString(3)+ "\n\n");

                    showMessage("List of Data :", buffer.toString());
                }
            }

        });
    }

    //Implement Data Viewer
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    //Implement Data Update Method
    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = appDb.updateData(inputId.getText().toString(),inputSId.getText().toString(),inputTask.getText().toString(),inputDate.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Updated!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    //Implement Data delete method
    public void deleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedatarows = appDb.deleteData(inputId.getText().toString());
                if(deletedatarows>0)
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}