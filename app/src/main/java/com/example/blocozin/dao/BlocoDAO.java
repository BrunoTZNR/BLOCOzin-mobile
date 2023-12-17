package com.example.blocozin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.blocozin.model.Bloco;

import java.util.ArrayList;
import java.util.List;

public class BlocoDAO extends SQLiteOpenHelper {
    public BlocoDAO(@Nullable Context context) { super(context, "db_blocozin", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tb_bloco(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " title TEXT NOT NULL, " +
                " 'desc' TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void onInsert(Bloco bloco) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("title", bloco.getTitle());
        data.put("desc", bloco.getDesc());

        db.insert("tb_bloco", null, data);
    }

    public Bloco onSelectOne(String title) {
        Bloco bloco =  new Bloco();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM tb_bloco WHERE title = ?";
        String[] args = {title};

        Cursor cursor = db.rawQuery(sql, args);

        try {
            if(cursor.moveToFirst()) {
                bloco.setId(cursor.getInt(0));
                bloco.setTitle(cursor.getString(1));
                bloco.setDesc(cursor.getString(2));
            }
        } finally {
            cursor.close();
        }

        return bloco;
    }

    public List<Bloco> onSelectAll() {
        List<Bloco> blocoList = new ArrayList<Bloco>();

        String sql = "SELECT * FROM tb_bloco";

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        try {
            if(cursor!=null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String titulo = cursor.getString(1);
                        String desc = cursor.getString(2);

                        blocoList.add(new Bloco(id, titulo, desc));
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            cursor.close();
        }

        return blocoList;
    }

    public void onUpdate(Bloco bloco) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("title", bloco.getTitle());
        data.put("desc", bloco.getDesc());

        db.update("tb_bloco", data, "id = ?", new String[]{String.valueOf(bloco.getId())});
    }

    public void onDelete(Integer id) {
        SQLiteDatabase db = getWritableDatabase();

        String[] a = {id.toString()};

        db.delete("tb_bloco", "id = ?", a);
    }
}
