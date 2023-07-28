package com.example.recyclerviewbyanshul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemClicked {
    private EditText etName,etGender,etNumber;
    private Button btnAdd,btnSave;
    private RecyclerView myView;
    RecyclerView.LayoutManager layoutManager;
    ContactAdapter myAdapter;
    ArrayList<Contact> newContact;
    private String name,number,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etGender = findViewById(R.id.etGender);
        etNumber = findViewById(R.id.etNumber);
        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
        myView = findViewById(R.id.myView);
        myView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        myView.setLayoutManager(layoutManager);
        newContact = new ArrayList<>();
        loadData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                gender = etGender.getText().toString().trim();
                number = etNumber.getText().toString().trim();
                if(name.isEmpty()||gender.isEmpty()||number.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    newContact.add(new Contact(name,gender,number));
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        myAdapter = new ContactAdapter(MainActivity.this,newContact);
        myView.setAdapter(myAdapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream file = openFileOutput("MyContact.txt",MODE_PRIVATE);
                    OutputStreamWriter outputFile = new OutputStreamWriter(file);
                    for(int i=0;i<newContact.size();i++){
                        outputFile.write(newContact.get(i).getName()+","+newContact.get(i).getGender()+","+newContact.get(i).getNumber()+"\n");
                    }
                    outputFile.flush();
                    outputFile.close();
                    Toast.makeText(MainActivity.this, "Successfully Saved!", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void loadData(){
        newContact.clear();
        File file = getApplicationContext().getFileStreamPath("MyContact.txt");
        String lineFromFile;
        if(file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("MyContact.txt")));
                while((lineFromFile=reader.readLine())!=null){
                    StringTokenizer tokens = new StringTokenizer(lineFromFile,",");
                    newContact.add(new Contact(tokens.nextToken(),tokens.nextToken(),tokens.nextToken()));
                }
                reader.close();

            }
            catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onItemClicked(int index){
        Toast.makeText(this, newContact.get(index).getName()+" : "+newContact.get(index).getNumber(), Toast.LENGTH_SHORT).show();
    }
}