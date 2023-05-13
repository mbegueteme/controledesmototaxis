package com.garoua.controledesmototaxis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname="motoDB";
    public static final int  Version=1;
    public static final String ColumnID="ID";
    public static final String columnMatricule="IMMATRICULATION";
    public static final String columnMarque="MARQUE";
    public static final String columnAssurance="ASSURANCE";
    public static final String columnCarte="CARTE_GRISE";

    Context context;

    public DBHelper(@Nullable Context context) {
        super(context,DBname, null, Version);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableCreate= "CREATE TABLE Moto(idMoto INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT, matriculMoto INTEGER, marqueMoto TEXT, assuranceMoto TEXT,carteGrise TEXT )";
        db.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {

        db.execSQL("DROP TABLE IF EXISTS Moto");
        onCreate(db);
    }


    /* insertion de la moto dans la base de donnee*/
    public void insertMoto( int matriculeMoto, String marqueMoto, String assuranceMoto, String numCarte){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(columnMatricule, matriculeMoto);
        cv.put(columnMarque, marqueMoto);
        cv.put(columnAssurance, assuranceMoto);
        cv.put(columnCarte, numCarte);
        long result=DB.insert("Moto", null, cv);

        if (result==-1){
            Toast.makeText(context, "Moto not saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "new Moto saved", Toast.LENGTH_SHORT).show();
        }
    }

    /* mettre a jour  la moto dans la base de donnee*/
    public void UpdateMoto(String idmoto, String matriculeMoto, String marqueMoto, String assuranceMoto, String numCarte){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(columnMatricule, matriculeMoto);
        cv.put(columnMarque, marqueMoto);
        cv.put(columnAssurance, assuranceMoto);
        cv.put(columnCarte, numCarte);

        long result = DB.update("Moto", cv, "ID = ?", new String[]{idmoto});
        if (result==-1){
            Toast.makeText(context, "Moto not updated ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Moto updated successfully", Toast.LENGTH_SHORT).show();
        }

        Cursor cursor=DB.rawQuery("select * from Moto where idmoto= ?", new String[]{idmoto});
    }


    /* a suppression de la moto dans la base de donnee*/
    public void DeleteMoto(String idmoto){
        SQLiteDatabase DB=this.getWritableDatabase();
        long result = DB.delete("Moto",  "ID = ?", new String[]{idmoto});
        if (result==-1){
            Toast.makeText(context, "Motor not deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Motor  deleted", Toast.LENGTH_SHORT).show();
        }
    }


    /* a suppression de la moto dans la base de donnee*/
    public Cursor ListMoto(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("SELECT * FROM Moto",null);
        return cursor;
    }

    /* a suppression de la liste entiere des  motos dans la base de donnee*/
    public void DeleteAllMoto(String idmoto){
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("DELETE FROM Moto");

    }

    /* a suppression de la liste entiere des  motos dans la base de donnee*/
    public  Cursor searchMoto(String id){
        SQLiteDatabase DB=this.getWritableDatabase();
        String[] columns=new String[]{ColumnID, columnMatricule, columnMarque, columnAssurance, columnCarte};
        Cursor cursor=DB.query("Moto", columns, "ID= ? ", new String[]{id}, null, null, null, null);
        return cursor;
    }


    public void deleteAllMoto(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM Moto");
    }
    /*===================================================================================================================*/

}
