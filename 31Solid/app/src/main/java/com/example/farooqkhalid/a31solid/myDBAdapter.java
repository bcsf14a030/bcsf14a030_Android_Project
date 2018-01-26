package com.example.farooqkhalid.a31solid;

/**
 * Created by Farooq Khalid on 1/26/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class myDBAdapter {
    myDBHelper myHelper;
    public myDBAdapter(Context context){
        myHelper = new myDBHelper(context);
    }


    public long insertData(String name, String lname)
    {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myHelper.FNAME , name);
        contentValues.put(myHelper.LNAME, lname);
        long id = db.insert(myHelper.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public long insertExpense(String name, Integer amount, Integer pid)
    {

        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myHelper.EXPENSE , name);
        contentValues.put(myHelper.AMOUNT, amount);
        contentValues.put(myHelper.PERSONID, pid);
        long id = db.insert(myHelper.TABLE_NAME1, null, contentValues);
        db.close();
        return id;
    }

    public Cursor getExpenses(Integer id){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String [] columns = {myHelper.EXPENSE, myHelper.AMOUNT};
        Cursor cursor = db.query(myHelper.TABLE_NAME1, columns, myHelper.PERSONID+"=?", new String[] { id.toString() },null,null,null);
        return cursor;
    }

    public myDBHelper getHelper(){
        return myHelper;
    }

    public Cursor getData(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String [] columns = {myHelper.UID, myHelper.FNAME, myHelper.LNAME};
        Cursor cursor = db.query(myHelper.TABLE_NAME, columns,null,null,null,null,null);
        return cursor;
    }

    public Cursor read(long id){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor c = db.query(myHelper.TABLE_NAME, new String[]{myHelper.UID, myHelper.FNAME, myHelper.LNAME}, null,null,null, null, null);
        return c;
    }


    static class myDBHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "NameDB";    // Database Name
        private static final String TABLE_NAME = "Names";   // Table Name
        private static final String TABLE_NAME1 = "Expenses";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        public static final String UID="_id";     // Column I (Primary Key)
        public static final String FNAME = "Fname";    //Column II
        public static final String LNAME = "Lname";    // Column III
        public static final String EXPENSE = "Expense";
        public static final String AMOUNT = "Amount";
        public static final String PERSONID = "Pid";

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FNAME+" VARCHAR(255) ,"+ LNAME +" VARCHAR(255));";
        private static final String EXPENSE_TABLE = "CREATE TABLE "+TABLE_NAME1+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EXPENSE+" VARCHAR(255) ,"+ AMOUNT +" INTEGER,"+ PERSONID +" INTEGER );";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;



        public myDBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_Version);

        }

        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE);
            db.execSQL(EXPENSE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }


    }

}
