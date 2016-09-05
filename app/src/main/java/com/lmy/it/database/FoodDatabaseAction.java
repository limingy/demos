package com.lmy.it.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lmy.it.bean.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limingyang on 2016/8/14.
 */
public class FoodDatabaseAction {
    private static final String DB_NAME = "demo";
    private static final int DB_VERSION = 1;

    private static DevSqliteOpenHelper openHelper;
    private static FoodDatabaseAction databaseAction = null;

    public static synchronized FoodDatabaseAction getDatabaseActionIntance(Context context){
        if(databaseAction == null){
            databaseAction = new FoodDatabaseAction();
            openHelper = new DevSqliteOpenHelper(context);
        }

        return databaseAction;
    }

    public static class DevSqliteOpenHelper extends SQLiteOpenHelper {

        public DevSqliteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create TABLE " + Food.TableColumn.TABLE_NAME
                + "(" +Food.TableColumn.COLUMN_ID + " Integer primary key autoincrement,"
                + Food.TableColumn.COLUMN_NAME +" text,"
                + Food.TableColumn.COLUMN_PRICE + " text"
                + " )"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public long save(Food food) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.TableColumn.COLUMN_NAME, food.getName());
        contentValues.put(Food.TableColumn.COLUMN_PRICE, food.getPrice());

        SQLiteDatabase database = openHelper.getWritableDatabase();
        return database.insert(Food.TableColumn.TABLE_NAME, "", contentValues);
    }

    public long delete(String whereClause, String[] whereArgs){
        SQLiteDatabase database = openHelper.getWritableDatabase();
        return database.delete(Food.TableColumn.TABLE_NAME, whereClause, whereArgs);
    }

    public long delete(long id){
        return delete(Food.TableColumn.COLUMN_ID + "= ?", new String[]{id +""});
    }

    public long update(ContentValues contentValues, String whereClause, String[] whereArgs){
        SQLiteDatabase database = openHelper.getWritableDatabase();
        return database.update(Food.TableColumn.TABLE_NAME,contentValues, whereClause, whereArgs);
    }

    public Food query(long id){
        Food food = null;
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Food.TableColumn.TABLE_NAME, null, Food.TableColumn.COLUMN_ID + " = ?", new String[]{id +""}, null, null, null);
        if(cursor.moveToFirst()){
            int _id = cursor.getInt(cursor.getColumnIndex(Food.TableColumn.COLUMN_ID));
            String _name = cursor.getString(cursor.getColumnIndex(Food.TableColumn.COLUMN_NAME));
            double _price = cursor.getDouble(cursor.getColumnIndex(Food.TableColumn.COLUMN_PRICE));

            food = new Food();
            food.setId(_id);
            food.setName(_name);
            food.setPrice(_price);
        }
        cursor.close();

        return food;
    }

    public List<Food> query(String selection, String[] selectionArgs){
        List<Food> foods = new ArrayList<>();
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Food.TableColumn.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        while(cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex(Food.TableColumn.COLUMN_ID));
            String _name = cursor.getString(cursor.getColumnIndex(Food.TableColumn.COLUMN_NAME));
            double _price = cursor.getDouble(cursor.getColumnIndex(Food.TableColumn.COLUMN_PRICE));

            Food food = new Food();
            food.setId(_id);
            food.setName(_name);
            food.setPrice(_price);

            foods.add(food);
        }
        cursor.close();

        return foods;
    }
}
