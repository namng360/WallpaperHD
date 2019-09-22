package vn.mac.gnam.wallpaperhd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.mac.gnam.wallpaperhd.model.Favorite;

public class FavoriteDAO {
    private SQLiteDatabase db;
    private Database databasemanager;
    public static final String TABLE_NAME = "Favorite";

    public static final String URL = "url";


    public static final String SQL_FAVORITE = "CREATE TABLE " + TABLE_NAME + " (" + URL + " text primary key);";
    public static final String TAG = "FavoriteDAO";

    public FavoriteDAO(Context context) {
        databasemanager = new Database(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertURL(Favorite favorite) {
        ContentValues values = new ContentValues();
        values.put(URL, favorite.getUrl());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateURL(String url) {
        ContentValues values = new ContentValues();
        values.put(URL, URL);
        int result = db.update(TABLE_NAME, values, URL + "=?", new String[]{URL});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteURL(String url) {
        int result = db.delete(TABLE_NAME, URL + "=?", new String[]{url});
        if (result == 0)
            return -1;
        return 1;
    }

    public List<Favorite> getAllFavorite() {
        List<Favorite> dsFavorite = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Favorite ee = new Favorite();
            ee.setUrl(c.getString(0));
            dsFavorite.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsFavorite;
    }
}
