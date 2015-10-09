package com.rssfeed.myapp.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by venkateanr on 25-05-2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    /**
     * Database Name
     */
    public static String DATABASENAME = "hindu.db";
    public static int DATABASEVERSION = 1;

    /**
     * Table Definition for Section Home
     */
    public static final String DONE_HINDU_SECTION = "hindusection";
    public static final String COLUMN_SECTIONID = "_id";
    public static final String COLUMN_SECTIONSID = "sid";
    public static final String COLUMN_SECTIONTITLE = "title";
    public static final String COLUMN_SECTIONDESCRIPTION = "description";
    public static final String COLUMN_SECTIONLINK = "link";
    public static final String COLUMN_SECTIONAUTHOR = "author";
    public static final String COLUMN_SECTIONDATE = "pubdate";

    /**
     * Table Definition
     */
    public static final String DONE_HINDU_SECTION_NEWS = "hindunews";
    public static final String DONE_HINDU_SECTION_OPINION = "hinduopinion";
    public static final String DONE_HINDU_SECTION_BUSINESS = "hindubusiness";
    public static final String DONE_HINDU_SECTION_SPORT = "hindusport";
    public static final String DONE_HINDU_SECTION_ENTERTAINMENT = "hinduentertainment";

    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTION = "CREATE TABLE "
            + DONE_HINDU_SECTION + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";

    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTIONNEWS = "CREATE TABLE "
            + DONE_HINDU_SECTION_NEWS + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";


    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTIONOPINION= "CREATE TABLE "
            + DONE_HINDU_SECTION_OPINION + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";


    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTIONBUSINESS = "CREATE TABLE "
            + DONE_HINDU_SECTION_BUSINESS + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";


    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTIONSPORT = "CREATE TABLE "
            + DONE_HINDU_SECTION_SPORT + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";


    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTIONENTERTAINMENT = "CREATE TABLE "
            + DONE_HINDU_SECTION_ENTERTAINMENT + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";

    public DatabaseHelper(Context context){
        super(context , DATABASENAME , null , DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ddddddddddd","ddddddddddddddd");
        db.execSQL(TABLE_CREATESECTION);
        db.execSQL(TABLE_CREATESECTIONNEWS);
        db.execSQL(TABLE_CREATESECTIONOPINION);
        db.execSQL(TABLE_CREATESECTIONBUSINESS);
        db.execSQL(TABLE_CREATESECTIONSPORT);
        db.execSQL(TABLE_CREATESECTIONENTERTAINMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ddddddddddd","ddddddddddddddd");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTION);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTIONNEWS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTIONOPINION);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTIONBUSINESS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTIONSPORT);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTIONENTERTAINMENT);
        onCreate(db);
    }
}
