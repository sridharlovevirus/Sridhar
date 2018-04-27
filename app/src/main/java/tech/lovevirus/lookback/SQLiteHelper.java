package tech.lovevirus.lookback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sridhar on 20/1/18.
 */

public class SQLiteHelper extends SQLiteOpenHelper
{
    public final static String dbname ="user.db";
public final static int version=1;
    public static final String TABLE_NAME = "USER123";
    public static final String NAME = "NAME";
    public static final String DP = "DP";
    public static  final  String ROLLNO="ROLLNO";

    public SQLiteHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ( " +NAME+ " VARCHAR,"+DP+" VARCHAR,"+ROLLNO+" VARCHAR );");
    System.out.println("db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public void updateRecord(String name, String dp,String rollno)
    {

        SQLiteDatabase database1;
        database1 = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(DP,dp);
        contentValues.put(ROLLNO,rollno);
        database1.update(TABLE_NAME,contentValues," ROLLNO = ? ",new String[] {rollno});
        database1.close();

    }
    public void insertRecord(String name, String dp,String rollno)
    {
        SQLiteDatabase database;
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,name);//2

        contentValues.put(DP,dp);//5
        contentValues.put(ROLLNO,rollno);//6
database.insert(TABLE_NAME,"null",contentValues);

        System.out.println("NAME"+name);

        System.out.println("DP"+dp);
        System.out.println("Rollno"+rollno);
        database.close();
        System.out.println("Insert done!");
    }
    public String delete()
    {
        SQLiteDatabase db1=this.getReadableDatabase();
        db1.delete(TABLE_NAME,null,null);
        return "ok";
    }
    public String[] viewdata() {
        String[] ls = new String[10];
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from USER123";
        Cursor cr = db.rawQuery(sql, null);
        if (cr.getCount() != 0) {
            cr.moveToFirst();
            do {
                for (int j = 0; j < 3; j++) {
                    ls[j] = cr.getString(j);
                }
                i++;
            } while (cr.moveToNext());
            return ls;
        }
        else {
            ls[0]="null";
return ls;
        }
    }

}
