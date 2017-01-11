package edu.gatech.seclass.glm;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 10/14/16.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "grocerylist";

    // table name
    private static final String TABLE_GLISTS = "gLists";
    private static final String TABLE_ITEMS = "items";
    private static final String TABLE_MASTER_ITEMS = "masterItems";
    private static final String TABLE_TYPES = "types";

    // gLists Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STORE_NAME = "storeName";

    // items Table Columns names
    private static final String ITEM_ID = "itemID";
    private static final String ITEM_QTY = "itemQty";
    private static final String ITEM_ISCHECKED = "itemIsChecked";
    private static final String GLIST_NO = "gListNo";

    // AllItems Table Columns name
    private static final String MASTER_ITEM_ID = "masterItemID";
    private static final String ITEM_NAME = "itemName";
    private static final String TYPE_NO = "typeNo";
    private static final String TYPE_NAME = "typeName";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GLIST_TABLE = "CREATE TABLE " + TABLE_GLISTS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_STORE_NAME + " TEXT" + ")";

        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + ITEM_ID + " INTEGER PRIMARY KEY," + ITEM_NAME + " TEXT," + TYPE_NAME + " TEXT," +
                ITEM_QTY + " INTEGER," + ITEM_ISCHECKED + " TEXT," + GLIST_NO + " INTEGER" + ")";

        String CREATE_MASTER_ITEMS_TABLE = "CREATE TABLE " + TABLE_MASTER_ITEMS +
                "(" + MASTER_ITEM_ID + " INTEGER PRIMARY KEY,"
                + ITEM_NAME + " TEXT, " + TYPE_NAME + " TEXT" + ")";

        db.execSQL(CREATE_GLIST_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_MASTER_ITEMS_TABLE);
        this.populateDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASTER_ITEMS);

        // Creating tables again
        onCreate(db);
    }

    public void add_gList(GList glist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, glist.getListID()); // List ID
        values.put(KEY_NAME, glist.getListName()); // List Name
        values.put(KEY_STORE_NAME, glist.getStoreName()); // Store Name
        // Inserting Row
        db.insert(TABLE_GLISTS, null, values);
        db.close(); // Closing database connection
    }

    public void update_gList(int gListID, String listName, String storeName){
//        System.out.println("Update " + listName + storeName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_GLISTS + " SET " + KEY_NAME + "= '" + listName + "', "
                + KEY_STORE_NAME + "= '" + storeName + "' " + "WHERE " + KEY_ID + "=" + gListID);
        db.close();
    }

    public ArrayList<MasterItem> getItemsByTypeName(String typeName) {
        ArrayList<MasterItem> masterItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * "
                + "FROM masterItems WHERE masterItems.typeName = \""
                + typeName + "\"", null);
        if (cursor != null) {
            cursor.moveToFirst();
            masterItems.add(new MasterItem(cursor.getString(1), cursor.getString(2)));
        }
        while (cursor.moveToNext()) {
            masterItems.add(new MasterItem(cursor.getString(1), cursor.getString(2)));
        }
        cursor.close();
        db.close();
        return masterItems;
    }

    public GList getGListByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GLISTS + " WHERE id=" + id, null);
        GList gList = new GList();
        if (cursor != null) {
            cursor.moveToFirst();
            gList.setListID(Integer.parseInt(cursor.getString(0)));
            gList.setListName(cursor.getString(1));
            gList.setStoreName(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return gList;
    }

    public void delete_glist(GList gList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GLISTS, KEY_ID + "=" + gList.getListID(), null);
        db.delete(TABLE_ITEMS, GLIST_NO + "=" + gList.getListID(), null);
        db.close();
    }

    public ArrayList<String> getTypeList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT typeName FROM masterItems ORDER BY "
                + "typeName ASC",null);

        ArrayList<String> typeList = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            typeList.add(cursor.getString(0));
        }
        while (cursor.moveToNext()) {
                typeList.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return typeList;
    }

    public ArrayList<Item> getItemsByGListNo(int gListNo){
        ArrayList<Item> items = new ArrayList<Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ITEM_QTY + ", " + ITEM_NAME
                + ", "  + TYPE_NAME + ", " + ITEM_ISCHECKED + " FROM " + TABLE_ITEMS
                + " WHERE " + GLIST_NO + " = " + gListNo + " ORDER BY " + TYPE_NAME
                + " ASC", null);
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String itemQty = cursor.getString(0);
            String itemName = cursor.getString(1);
            String itemType = cursor.getString(2);
            String itemIsChecked = cursor.getString(3);
//            String gListNo = cursor.getString(4);
            Item i = new Item(itemName, itemType, Integer.parseInt(itemQty),
                    Boolean.parseBoolean(itemIsChecked), gListNo);
            items.add(i);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return items;
    }

    public void addItemsByGListNo(Item item){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName());
        values.put(TYPE_NAME, item.getType());
        values.put(ITEM_QTY, item.getQty());
        values.put(ITEM_ISCHECKED, item.isChecked());
        values.put(GLIST_NO, item.getgListNo());
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void updateItemsByGListNo(Item item){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_ITEMS + " SET " + ITEM_QTY + "= '" + item.getQty() + "', "
                + ITEM_ISCHECKED + "= '" + item.isChecked() + "' " + "WHERE " + GLIST_NO + "="
                + item.getgListNo()
                + " AND " + ITEM_NAME + "= '" + item.getName() + "'");
        db.close();
    }

    public void deleteItemByGListNo(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, GLIST_NO + "=" + item.getgListNo() + " AND " + ITEM_NAME + "= '"
                + item.getName() +"'", null);
        db.close();
    }


    // Getting GList
    public List<GList> getGLists() {
        List<GList> allLists = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GLISTS, null);
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GList gList = new GList();
            gList.setListID(Integer.parseInt(cursor.getString(0)));
            gList.setListName(cursor.getString(1));
            gList.setStoreName(cursor.getString(2));
            allLists.add(gList);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return allLists;
    }

    public void newMasterItem(MasterItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName());
        values.put(TYPE_NAME, item.getType());

        db.insert(TABLE_MASTER_ITEMS, null, values);
        db.close();
    }

    public List<MasterItem> searchItems(String name){

        List<MasterItem> items = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();

        if (name.length() == 0){
            cursor = db.rawQuery("SELECT * FROM " + TABLE_MASTER_ITEMS + " ORDER BY "
                    + TYPE_NAME +" ASC", null);
        } else {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_MASTER_ITEMS + " WHERE " + ITEM_NAME
                    + " LIKE '%" + name +"%' COLLATE NOCASE", null);
        }
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MasterItem item = new MasterItem(cursor.getString(1), cursor.getString(2));
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return items;
    }

    public void populateDB(SQLiteDatabase db) {
        ArrayList<MasterItem> mI = new ArrayList<>();
        mI.add(new MasterItem("Chips","Snack"));
        mI.add(new MasterItem("Cucumber","Produce"));
        mI.add(new MasterItem("Green Pepper","Produce"));
        mI.add(new MasterItem("Carrots, Baby","Produce"));
        mI.add(new MasterItem("Potatos","Produce"));
        mI.add(new MasterItem("Dog Food","Pet"));
        mI.add(new MasterItem("Cat Food","Pet"));
        mI.add(new MasterItem("Steak","Meat"));
        mI.add(new MasterItem("Chicken","Meat"));
        mI.add(new MasterItem("Hamburger","Meat"));
        mI.add(new MasterItem("Banana","Produce"));
        mI.add(new MasterItem("Ice Crea","Frozen"));
        mI.add(new MasterItem("Ice","Frozen"));
        mI.add(new MasterItem("Coffee","Dry Goods"));
        mI.add(new MasterItem("Flour","Dry Goods"));
        mI.add(new MasterItem("Tea","Dry Goods"));
        mI.add(new MasterItem("Cream Cheese","Dairy"));
        mI.add(new MasterItem("Eggs","Dairy"));
        mI.add(new MasterItem("Dawn","Cleaning"));
        mI.add(new MasterItem("Mints","Candy"));
        mI.add(new MasterItem("Oatmeal","Breakfast"));
        mI.add(new MasterItem("Captain Crunch","Breakfast"));
        mI.add(new MasterItem("Loaf Bread","Bread"));
        mI.add(new MasterItem("Hamburger Buns","Bread"));
        mI.add(new MasterItem("Coca-Cola","Beverage"));
        mI.add(new MasterItem("Orange Juice","Beverage"));
        mI.add(new MasterItem("Grape Juice","Beverage"));

        ContentValues values = new ContentValues();
        for(int i = 0; i < mI.size(); i++) {
            values.put(ITEM_NAME, mI.get(i).getName());
            values.put(TYPE_NAME, mI.get(i).getType());
            db.insert(TABLE_MASTER_ITEMS, null, values);
        }
    }

}
