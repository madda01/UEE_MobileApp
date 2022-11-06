package com.example.panthera.undergrad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.panthera.R;
import com.example.panthera.database.AnimalDBHandler;
import com.example.panthera.models.Animal;

public class AnimalEach extends AppCompatActivity {

    //variable declaration
    Context context;
    EditText name,sciname,desc;
    AnimalDBHandler dbConnection;
    Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_single_view_undergrad);

        //mapping the elements
        name=findViewById(R.id.editTitle);
        sciname = findViewById(R.id.addscientific);
        desc=findViewById(R.id.adddesc);

        context = this;
        dbConnection = new AnimalDBHandler(context); //creating db connection
        animal = new Animal();
        Intent intent = getIntent();
        int i = intent.getIntExtra("id", 0);

        animal=dbConnection.getSingleAnimal(i); //getting single animal

        name.setText(animal.getName());
        sciname.setText(animal.getScientificName());
        desc.setText(animal.getDescription());

        //showing animal data to the user
        name.setKeyListener(null);
        sciname.setKeyListener(null);
        desc.setKeyListener(null);
    }
}
