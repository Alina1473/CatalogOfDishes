package com.alina.catalogofdishes.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.alina.catalogofdishes.Database.Constants;
import com.alina.catalogofdishes.Dishes;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.List;

public class DBCreate extends SQLiteAssetHelper {

    public DBCreate(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    public List<Dishes> getDishes()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {Constants.KEY_ID, Constants.KEY_NAME, Constants.KEY_COUNTRY, Constants.KEY_COMPONENTS};
        String tableName = Constants.TABLE_CONTACTS;

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null,null,null,null,null);
        List<Dishes> result = new ArrayList<>();

        if(cursor.moveToNext())
        {
            do{
                Dishes dishes = new Dishes();
                dishes.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                dishes.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                dishes.setCountry(cursor.getString(cursor.getColumnIndex(Constants.KEY_COUNTRY)));
                dishes.setComponents(cursor.getString(cursor.getColumnIndex(Constants.KEY_COMPONENTS)));

                result.add(dishes);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getName()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {Constants.KEY_NAME};
        String tableName = Constants.TABLE_CONTACTS;

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToNext())
        {
            do{
                result.add(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<Dishes> getDishesByName (String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {Constants.KEY_ID, Constants.KEY_NAME, Constants.KEY_COUNTRY, Constants.KEY_COMPONENTS};
        String tableName = Constants.TABLE_CONTACTS;

        qb.setTables(tableName);

        Cursor cursor = qb.query(db, sqlSelect, "Name LIKE ?",new String[]{"%"+name+"%"} ,null,null,null);
        String search = "SELECT * FROM " + Constants.TABLE_CONTACTS + " WHERE ( " + Constants.KEY_NAME + " LIKE " + name + " )";
        String ser = Constants.KEY_NAME + " LIKE '%"+name+"%' OR " + Constants.KEY_COUNTRY + " LIKE '%"+name+"%' OR " +Constants.KEY_COMPONENTS+ " LIKE '%"+name+"%'";

        Cursor cur = qb.query(db, sqlSelect, ser, null,null, null,null);
        List<Dishes> result = new ArrayList<>();
        if(cur.moveToNext())
        {
            do{
                Dishes dishes = new Dishes();
                dishes.setId(cur.getInt(cur.getColumnIndex(Constants.KEY_ID)));
                dishes.setName(cur.getString(cur.getColumnIndex(Constants.KEY_NAME)));
                dishes.setCountry(cur.getString(cur.getColumnIndex(Constants.KEY_COUNTRY)));
                dishes.setComponents(cur.getString(cur.getColumnIndex(Constants.KEY_COMPONENTS)));

                result.add(dishes);
            }while (cur.moveToNext());
        }
        return result;
    }

    public void InfoToDb(SQLiteDatabase db){
        Log.d(Constants.nameLog, "InfoToDb(SQLiteDatabase db)");
        db.execSQL("drop table if exists "+ Constants.TABLE_CONTACTS);
        db.execSQL(Constants.CREATE_TABLE);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+ Constants.TABLE_CONTACTS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt (0) == 0) {
                String query_insert=Constants.INSERT_INTO+Constants.INSERT_VALUES;
                Log.d(Constants.nameLog,"110-"+query_insert);
                db.execSQL(query_insert);
            }
        }

        if((cursor.getCount()!=cursor.getInt (0))) {
            db.execSQL("drop table if exists "+ Constants.TABLE_CONTACTS);
            db.execSQL(Constants.CREATE_TABLE);
            String query_insert=Constants.INSERT_INTO+Constants.INSERT_VALUES;
            db.execSQL(query_insert);
        }
        cursor.close();

        Cursor cursorLog = db.query(Constants.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursorLog.moveToFirst()) {
            Constants.idIndex = cursorLog.getColumnIndex(Constants.KEY_ID);
            Constants.nameIndex = cursorLog.getColumnIndex(Constants.KEY_NAME);
            Constants.countryIndex = cursorLog.getColumnIndex(Constants.KEY_COUNTRY);
            Constants.componentsIndex = cursorLog.getColumnIndex(Constants.KEY_COMPONENTS);
            do {
                Log.d(Constants.nameLog, "ID = " + cursorLog.getInt(Constants.idIndex) +
                        ", name = " + cursorLog.getString(Constants.nameIndex) +
                        ", country = " + cursorLog.getString(Constants.countryIndex)+
                        ", components = " + cursorLog.getString(Constants.componentsIndex));
            } while (cursorLog.moveToNext());
        } else
            Log.d("mLog","0 rows");
        cursorLog.close();
}
}
