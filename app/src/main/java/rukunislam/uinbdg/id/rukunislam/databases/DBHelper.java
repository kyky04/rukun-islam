package rukunislam.uinbdg.id.rukunislam.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rukunislam.uinbdg.id.rukunislam.R;
import rukunislam.uinbdg.id.rukunislam.model.SumberHadits;
import rukunislam.uinbdg.id.rukunislam.model.SumberQuran;

/**
 * Created by Comp on 11/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static Context mContext;
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "rukun_islam";

    // Contacts table name
    private static final String SUMBER_QURAN = "sumber_quran";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SURAT_ID = "surat_id";
    private static final String KEY_AYAT_ID = "ayat_id";
    private static final String KEY_ISI_AYAT = "terjemah";
    private static final String KEY_ISI_AYAT_QURAN = "ayat";

    private static final String SUMBER_HADITS = "sumber_hadits";

    // Contacts Table Columns names
    private static final String KEY_KATEGORI_HADITS = "kategori";
    private static final String KEY_HADITS = "isi_hadits";
    private static final String KEY_TERJEMAH_HADITS = "terjemah_hadits";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String CREATE_TABLE_SURAT_QURAN ="CREATE TABLE " + SUMBER_QURAN + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SURAT_ID + " INTEGER,"
                + KEY_AYAT_ID + " INTEGER,"
                + KEY_ISI_AYAT + " TEXT,"
                + KEY_ISI_AYAT_QURAN + " TEXT" +
                ")";

        String CREATE_TABLE_SURAT_HADITS ="CREATE TABLE " + SUMBER_HADITS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_KATEGORI_HADITS + " TEXT,"
                + KEY_HADITS + " TEXT,"
                + KEY_TERJEMAH_HADITS + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_SURAT_QURAN);
        sqLiteDatabase.execSQL(CREATE_TABLE_SURAT_HADITS);
        importSumberQuran(sqLiteDatabase);
//        importArabQuran(sqLiteDatabase);
        importHadits(sqLiteDatabase);
    }

    public List<SumberQuran> getAllSumber(){
        List<SumberQuran> quranArrayList = new ArrayList<SumberQuran>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SUMBER_QURAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SumberQuran sumberQuran = new SumberQuran();
                sumberQuran.setId(Integer.parseInt(cursor.getString(0)));
                sumberQuran.setSuratId(cursor.getInt(1));
                sumberQuran.setAyatId(cursor.getInt(2));
                sumberQuran.setTerjemah(cursor.getString(3));
                sumberQuran.setIsiArab(cursor.getString(4));
                quranArrayList.add(sumberQuran);
            } while (cursor.moveToNext());
        }
        return quranArrayList;
    }

    public List<SumberHadits> getAllHadits(){
        List<SumberHadits> quranArrayList = new ArrayList<SumberHadits>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SUMBER_HADITS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SumberHadits sumberQuran = new SumberHadits();
                sumberQuran.setId(Integer.parseInt(cursor.getString(0)));
                sumberQuran.setKategori(cursor.getString(1));
                sumberQuran.setIsi_hadits(cursor.getString(2));
                sumberQuran.setTerjemah_hadits(cursor.getString(3));
                quranArrayList.add(sumberQuran);
            } while (cursor.moveToNext());
        }
        return quranArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SUMBER_QURAN);
        onCreate(sqLiteDatabase);
    }

    private void importSumberQuran(SQLiteDatabase db) {
        List<String[]> records = readDataFromFileResource(R.raw.indonesia);

        List<String[]> recordsQuran = readDataFromFileResource(R.raw.arab);
        String[] recordData;
        String[] recordDataQuran;

        ContentValues values = new ContentValues();
        for (int i = 0; i < records.size(); i++) {
            recordData = records.get(i);
            values.put(KEY_SURAT_ID, recordData[0]);
            values.put(KEY_AYAT_ID, recordData[1]);
            values.put(KEY_ISI_AYAT, recordData[2]);
//            values.put(KEY_ID, recordData[0]);
            recordDataQuran = recordsQuran.get(i);
            values.put(KEY_ISI_AYAT_QURAN, recordDataQuran[3]);
            db.insert(SUMBER_QURAN, null, values);
        }

    }

    private void importArabQuran(SQLiteDatabase db) {
        List<String[]> records = readDataFromFileResource(R.raw.arab);
        String[] recordData;
        for (int i = 0; i < records.size(); i++) {
            recordData = records.get(i);
            ContentValues values = new ContentValues();
            values.put(KEY_ISI_AYAT_QURAN, recordData[3]);
            db.insert(SUMBER_QURAN, null, values);
        }
    }

    private void importHadits(SQLiteDatabase db) {
        List<String[]> records = readDataFromFileResource(R.raw.bukhari);
        String[] recordData;

        for (int i = 0; i < records.size(); i++) {
            recordData = records.get(i);
            ContentValues values = new ContentValues();

//            values.put(KEY_ID, recordData[0]);
            values.put(KEY_ID, recordData[0]);
            values.put(KEY_KATEGORI_HADITS, recordData[1]);
            values.put(KEY_HADITS, recordData[2]);
            values.put(KEY_TERJEMAH_HADITS, recordData[3]);

            db.insert(SUMBER_HADITS, null, values);
        }
    }




    private List<String[]> readDataFromFileResource(int resourceId) {
        List<String[]> records = null;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(mContext.getResources().openRawResource(resourceId)));
            records = reader.readAll();
            reader.close();
        } catch (IOException e) {
            Log.e("error", "Failed to read data from file: " + e.getMessage());
            e.printStackTrace();
        }
        return records;
    }
}
