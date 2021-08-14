package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelpher extends SQLiteOpenHelper {

    private static final String DataBase_Name = "db2";
    private static final String user = "user";
    private static final String col_1 = "ID";
    private static final String CardNumber = "CardNumber";
    private static final String PinNumber = "PinNumber";
    private static final String Email = "Email";
    private static final String Amount = "Amount";
    private static final String AccountNo = "AccountNo";
    private static final String UserName = "UserName";
    private static final String WaterBill = "waterBill";
    private static final String SubscriptionNo = "SubscriptionNo";
    private static final String BillAmount = "Amount";
    private static final String  Save = "save";
    private static final String Accounts = "Accounts";


    public DataBaseHelpher(@Nullable Context context) {
        super(context, DataBase_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + user + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,CardNumber TEXT,PinNumber TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + WaterBill + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,SubscriptionNo TEXT,Amount Number,AccountNo TEXT,Save TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Accounts + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,UserName text,CardNumber TEXT,AccountNo TEXT ,Amount Text,Email TEXT)");

    }

    public boolean registerUser(String cardnumber, String pinnumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CardNumber, cardnumber);
        values.put(PinNumber, pinnumber);
        long result = db.insert(user, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean addAccounts(String userName,String cardno,String accountno ,int amount,String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserName, userName);
        values.put(CardNumber, cardno);
        values.put(AccountNo, accountno);
        values.put(Amount, amount);
        values.put(Email, email);


        long result = db.insert(Accounts, null, values);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean utilitybillWater(String subscriptionno,int amount,String accountno,String save) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SubscriptionNo, subscriptionno);
        values.put(BillAmount, amount);
        values.put(AccountNo, accountno);
        values.put(Save, save);
        long result = db.insert(WaterBill, null, values);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + user);
        db.execSQL("DROP TABLE IF EXISTS " + WaterBill);
        db.execSQL("DROP TABLE IF EXISTS " + Accounts);
        onCreate(db);
    }




    public boolean checkUser(String cardnumber, String pinnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {col_1};
        String selection = CardNumber + "=?" + " and " + PinNumber + "=?";
        String[] selectionargs = {cardnumber, pinnumber};
        Cursor cursor = db.query(user, columns, selection, selectionargs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {

            return true;
        } else {
            return false;
        }
    }
    public String getAccountData(String cardnumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + user + " WHERE cardNumber='" + cardnumber + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Amount));
            String accno = cursor.getString(cursor.getColumnIndex(AccountNo));

            users.setAmount(cid);
            users.setAccountNo(accno);

            buffer.append(cid);


        }
        return buffer.toString();
    }

    public String FindUserName(String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * From " + Accounts + " WHERE AccountNo='" + accountNo + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String UserNNAME = cursor.getString(cursor.getColumnIndex(UserName));
            users.setUSERNAME(UserNNAME);
            buffer.append(UserNNAME);
        }
        return buffer.toString();
    }


    public String getData(String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + Accounts + " WHERE AccountNo='" + accountNo + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Amount));
            String accno = cursor.getString(cursor.getColumnIndex(AccountNo));

            users.setAmount(cid);
            users.setAccountNo(accno);
            buffer.append(cid);

        }
        return buffer.toString();
    }


    public String getaccountsData(String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + Accounts + " WHERE AccountNo='" + accountNo + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Amount));
            String accno = cursor.getString(cursor.getColumnIndex(AccountNo));
            String uName = cursor.getString(cursor.getColumnIndex(UserName));
            String email = cursor.getString(cursor.getColumnIndex(Email));

            users.setAmount(cid);
            users.setAccountNo(accno);
            users.setUSERNAME(uName);
            buffer.append(cid);

        }
        return buffer.toString();
    }

    public String getAccountMoney(String accoutnum) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + Accounts + " WHERE AccountNo='" + accoutnum + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Amount));
            String username1 = cursor.getString(cursor.getColumnIndex("UserName"));
            users.setAmount(cid);
            users.setUSERNAME(username1);

            buffer.append(cid);

        }
        return buffer.toString();
    }
    public String getTransferMoneyAccountEmail(String accoutnum) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + Accounts + " WHERE AccountNo='" + accoutnum + "'", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(Email));
            users.setTransferAccountEmail(email);
            buffer.append(email);
        }
        return buffer.toString();
    }




    public boolean updatedata(String CardNumber,String accountno,int amount){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("CardNumber",CardNumber);
       contentValues.put("AccountNo",accountno);
       contentValues.put("Amount",amount);
       db.update(Accounts,contentValues,"AccountNo =?",new String[] {accountno});
       return true;
   }



    public boolean sendmoney(String AccountNo,int Amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccountNo",AccountNo);
        contentValues.put("Amount",Amount);
        db.update(Accounts,contentValues,"AccountNo =?",new String[] {AccountNo});
    return true;

    }


    public boolean checkAccountnoUser(String username ,String accountno ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {col_1};
        String selection = UserName + "=?" + " and " + AccountNo + "=?";
        String[] selectionargs = {username,accountno};
        Cursor cursor = db.query(Accounts, columns, selection, selectionargs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {

            return true;
        } else {
            return false;
        }
    }


    public Cursor  viewdata(String cardnumber){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From "+ Accounts +" where CardNumber ="+ cardnumber ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor  viewdataaterbill(String accountno){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From "+ WaterBill +" Where AccountNo ="+ accountno +" AND save='true'" ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor  viewallaccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From "+ Accounts ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor  viewallaccountsName(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From "+ Accounts ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public List<String> getAllLabels(String cardNumber){
        List<String> list = new ArrayList<String>();


        String selectQuery = "SELECT  * FROM " + Accounts + " where CardNumber="+cardNumber;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


}























