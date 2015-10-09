package com.rssfeed.myapp.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by venkatesanr on 25-05-2015.
 */
public class HinduContentProvider extends ContentProvider {

    /**
     * Database Helper Instance Class
     */
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase dbDatabase;
    private SQLiteQueryBuilder queryBuilder = null;
    /**
     * DATABASE AUTHORITY
     */
    public static final String AUTHORITY = "com.rssfeed";

    /**
     * TO BUILD BASE URI
     */
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * To add URL CODE to identify the path
     */
    public static final int SECTIONINSERT = 100;
    public static final int FEEDSECTION = 101;
    public static final int SECTIONDELETE = 102;

    public static final int SECTIONNEWSINSERT = 103;
    public static final int FEEDNEWSSECTION = 104;
    public static final int SECTIONNEWSDELETE = 105;

    public static final int SECTIONOPINIONINSERT = 106;
    public static final int FEEDOPINIONSECTION = 107;
    public static final int SECTIONOPINIONDELETE = 108;

    public static final int SECTIONBUSINESSINSERT = 109;
    public static final int FEEDBUSINESSSECTION = 110;
    public static final int SECTIONBUSINESSDELETE = 111;

    public static final int SECTIONSPORTINSERT = 112;
    public static final int FEEDSPORTSECTION = 113;
    public static final int SECTIONSPORTDELETE = 114;

    public static final int SECTIONENTERTAINMENTINSERT = 115;
    public static final int FEEDENTERTAINMENTSECTION = 116;
    public static final int SECTIONENTERTAINMENTDELETE = 117;


    static {
        final UriMatcher matcher = uriMatcher;
        matcher.addURI(AUTHORITY, "hindu/section/get", FEEDSECTION);
        matcher.addURI(AUTHORITY, "hindu/section/add", SECTIONINSERT);
        matcher.addURI(AUTHORITY, "hindu/section/delete", SECTIONDELETE);

        matcher.addURI(AUTHORITY, "hindu/news/add", SECTIONNEWSINSERT);
        matcher.addURI(AUTHORITY, "hindu/news/get", FEEDNEWSSECTION);
        matcher.addURI(AUTHORITY, "hindu/news/delete", SECTIONNEWSDELETE);

        matcher.addURI(AUTHORITY, "hindu/opinion/add", SECTIONOPINIONINSERT);
        matcher.addURI(AUTHORITY, "hindu/opinion/get", FEEDOPINIONSECTION);
        matcher.addURI(AUTHORITY, "hindu/opinion/delete", SECTIONOPINIONDELETE);

        matcher.addURI(AUTHORITY, "hindu/business/add", SECTIONBUSINESSINSERT);
        matcher.addURI(AUTHORITY, "hindu/business/get", FEEDBUSINESSSECTION);
        matcher.addURI(AUTHORITY, "hindu/business/delete", SECTIONBUSINESSDELETE);

        matcher.addURI(AUTHORITY, "hindu/sport/add", SECTIONSPORTINSERT);
        matcher.addURI(AUTHORITY, "hindu/sport/get", FEEDSPORTSECTION);
        matcher.addURI(AUTHORITY, "hindu/sport/delete", SECTIONSPORTDELETE);

        matcher.addURI(AUTHORITY, "hindu/entertainment/add", SECTIONENTERTAINMENTINSERT);
        matcher.addURI(AUTHORITY, "hindu/entertainment/get", FEEDENTERTAINMENTSECTION);
        matcher.addURI(AUTHORITY, "hindu/entertainment/delete", SECTIONENTERTAINMENTDELETE);
    }

    @Override
    public boolean onCreate() {
        this.dbHelper = new DatabaseHelper(getContext());
        this.dbDatabase = this.dbHelper.getWritableDatabase();
        return (this.dbHelper != null);
    }

    /**
     * To return db object
     */
    public synchronized SQLiteDatabase getDb() {
        return dbDatabase;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = uriMatcher.match(uri);


        Cursor cursor = null;
        queryBuilder = new SQLiteQueryBuilder();
        switch (uriType) {
            case FEEDSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION);
            break;
            case FEEDNEWSSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION_NEWS);
            break;
            case FEEDOPINIONSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION_OPINION);
            break;
            case FEEDBUSINESSSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION_BUSINESS);
            break;
            case FEEDSPORTSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION_SPORT);
            break;
            case FEEDENTERTAINMENTSECTION:
                queryBuilder.setTables(DatabaseHelper.DONE_HINDU_SECTION_ENTERTAINMENT);
            break;
        }
        cursor = queryBuilder.query(this.getDb(), projection, selection,
                selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues cv) {
        long id = 0L;
            switch (uriMatcher.match(uri)) {
                case SECTIONINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION, null, cv);
                break;
                case SECTIONNEWSINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION_NEWS, null, cv);
                break;
                case SECTIONOPINIONINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION_OPINION, null, cv);
                break;
                case SECTIONBUSINESSINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION_BUSINESS, null, cv);
                break;
                case SECTIONSPORTINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION_SPORT, null, cv);
                break;
                case SECTIONENTERTAINMENTINSERT:
                    id = this.getDb().insert(DatabaseHelper.DONE_HINDU_SECTION_ENTERTAINMENT, null, cv);
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int row = 0;
        int matched = uriMatcher.match(uri);
        try {
            switch (uriMatcher.match(uri)) {
                case SECTIONDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION);
                break;
                case SECTIONNEWSDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION_NEWS);
                break;
                case SECTIONOPINIONDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION_OPINION);
                break;
                case SECTIONBUSINESSDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION_BUSINESS);
                break;
                case SECTIONSPORTDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION_SPORT);
                break;
                case SECTIONENTERTAINMENTDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_HINDU_SECTION_ENTERTAINMENT);
                break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
