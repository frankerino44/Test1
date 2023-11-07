package com.example.test1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class FunkoPopProvider extends ContentProvider {
    public FunkoPopProvider() {
    }

    public final static String DBFUNKOPOP = "FunkoPopDatabase";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBFUNKOPOP, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };

    public final static String TABLE_FUNKOPOPSTABLE = "Names";

    public static final String AUTHORITY = "FunkoPops";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_FUNKOPOPSTABLE);

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_FUNKOPOPSTABLE +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            "POP_NAME String, " +
            "POP_NUMBER int, " +
            "POP_TYPE String, " +
            "FANDOM String, " +
            "On boolean, " +
            "ULTIMATE String, " +
            "PRICE double)";

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return mOpenHelper.getWritableDatabase().delete(TABLE_FUNKOPOPSTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        
        long id = mOpenHelper.getWritableDatabase().insert(TABLE_FUNKOPOPSTABLE, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + "");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        return mOpenHelper.getReadableDatabase().query(TABLE_FUNKOPOPSTABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        return mOpenHelper.getWritableDatabase().update(TABLE_FUNKOPOPSTABLE, values, selection, selectionArgs);
    }
}