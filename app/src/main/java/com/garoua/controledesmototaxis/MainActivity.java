package com.garoua.controledesmototaxis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText idEdit, matriculeEdit, marqueEdit, assuranceEdit, numcarteEdit;
    private  String carteGrise, assurance, marque, ID;
    private int matricule;
    private DBHelper dbHelper;
    TextView addButton, delButton, updateButton, showButton, searchButton, deleteAll;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);

        idEdit=findViewById(R.id.NumberV);
        matriculeEdit=findViewById(R.id.immatV);
        marqueEdit=findViewById(R.id.MarqueMotoV);
        assuranceEdit=findViewById(R.id.assuranceMotoV);
        numcarteEdit=findViewById(R.id.numCarteV);


        addButton=findViewById(R.id.add);
        delButton=findViewById(R.id.delete);
        updateButton=findViewById(R.id.update);
        showButton=findViewById(R.id.show);
        searchButton=findViewById(R.id.search);
        deleteAll=findViewById(R.id.deletteAll);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assurance=assuranceEdit.getText().toString();
                carteGrise=numcarteEdit.getText().toString();
                marque=marqueEdit.getText().toString();
                ID=idEdit.getText().toString();

                String numMatricule=matriculeEdit.getText().toString();

                if (assurance.isEmpty()||carteGrise.isEmpty()||marque.isEmpty()){
                    Toast.makeText(MainActivity.this, "cannot insert empty moto", Toast.LENGTH_SHORT).show();
                }else {
                    matricule=Integer.parseInt(numMatricule);
                    try {
                        dbHelper.insertMoto(matricule, marque, assurance, carteGrise);
                        // clear data field

                        matriculeEdit.getText().clear();
                        idEdit.getText().clear();
                        assuranceEdit.getText().clear();
                        marqueEdit.getText().clear();
                        numcarteEdit.getText().clear();

                    }catch (Exception e){
                        //  e.printStackTrace();
                    }
                }

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assurance=assuranceEdit.getText().toString();
                carteGrise=numcarteEdit.getText().toString();
                marque=marqueEdit.getText().toString();
                ID=idEdit.getText().toString();
                String numMatricule=matriculeEdit.getText().toString();

                if (assurance.isEmpty()||carteGrise.isEmpty()||marque.isEmpty()){
                    Toast.makeText(MainActivity.this, "cannot submit empty moto", Toast.LENGTH_SHORT).show();
                }else {
                    matricule=Integer.parseInt(numMatricule);
                    try {
                        dbHelper.UpdateMoto(ID, String.valueOf(matricule), marque, assurance, carteGrise);
                        // clear data field
                        matriculeEdit.getText().clear();
                        idEdit.getText().clear();
                        assuranceEdit.getText().clear();
                        marqueEdit.getText().clear();
                        numcarteEdit.getText().clear();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });



        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID=idEdit.getText().toString();
                if (ID.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter the ID", Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        dbHelper.DeleteMoto(ID);

                        // clear data field
                        matriculeEdit.getText().clear();
                        idEdit.getText().clear();
                        assuranceEdit.getText().clear();
                        marqueEdit.getText().clear();
                        numcarteEdit.getText().clear();

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }

            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this, R.layout.activity_main);
                String carteGrise, assurance, marque, ID;
                String matricule;

                try {
                    Cursor cursor=dbHelper.ListMoto();
                    if (cursor !=null && cursor.getCount()>0){
                        while (cursor.moveToNext()){
                            ID=cursor.getString(0);
                            matricule=cursor.getString(1);
                            marque=cursor.getString(2);
                            assurance=cursor.getString(3);
                            carteGrise=cursor.getString(4);

                            arrayAdapter.add("ID :-"+ID);
                            arrayAdapter.add("Immatriculation :-"+matricule);
                            arrayAdapter.add("Marque :-"+marque);
                            arrayAdapter.add("Assurence :-"+assurance);
                            arrayAdapter.add("ID :-"+ID);


                        }
                    }else{
                        arrayAdapter.add("no Motor here");
                    }
                    cursor.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("SQlite saved Moto");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID=idEdit.getText().toString();
                if (ID.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter the ID", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Cursor cursor=dbHelper.searchMoto(ID);
                        if (cursor.moveToFirst()){
                            assuranceEdit.getText().toString();
                            numcarteEdit.getText().toString();
                            marqueEdit.getText().toString();
                            idEdit.getText().toString();
                            matriculeEdit.getText().toString();

                            Toast.makeText(MainActivity.this, "Moto Successfully searched", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "ID not found", Toast.LENGTH_SHORT).show();
                            matriculeEdit.setText("ID not found");
                            assuranceEdit.setText("ID not found");
                            numcarteEdit.setText("ID not found");
                            marqueEdit.setText("ID not found");

                        }
                        cursor.close();

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            }
        });


        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("Delete All Moto");builder.setCancelable(false);builder.setMessage("Do you realy want to delete all the Moto ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteAllMoto();

                        // clear data field
                        matriculeEdit.getText().clear();
                        idEdit.getText().clear();
                        assuranceEdit.getText().clear();
                        marqueEdit.getText().clear();
                        numcarteEdit.getText().clear();


                    }

                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

    }
}