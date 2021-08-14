package com.example.bankingapp;

public class users {
    public static String id ;
    public static String CARDNUMBER ;
    public static int Amount ;
    public static String USERNAME ;
    public static String AccountNo;
    public static String Password;

    public static String getTransferAccountEmail() {
        return TransferAccountEmail;
    }

    public static void setTransferAccountEmail(String transferAccountEmail) {
        TransferAccountEmail = transferAccountEmail;
    }

    public static String TransferAccountEmail;

    public static String FromAccountNo;
    public static String ToAccountNo;
    public static String FromAccountNoBalance;

    public static String getToAccountNoBalance() {
        return ToAccountNoBalance;
    }

    public static void setToAccountNoBalance(String toAccountNoBalance) {
        ToAccountNoBalance = toAccountNoBalance;
    }

    public static String ToAccountNoBalance;

    public static String getFromAccountNoBalance() {
        return FromAccountNoBalance;
    }

    public static void setFromAccountNoBalance(String fromAccountNoBalance) {
        FromAccountNoBalance = fromAccountNoBalance;
    }



    public static String getFromAccountNo() {
        return FromAccountNo;
    }

    public static void setFromAccountNo(String fromAccountNo) {
        FromAccountNo = fromAccountNo;
    }

    public static String getToAccountNo() {
        return ToAccountNo;
    }

    public static void setToAccountNo(String toAccountNo) {
        ToAccountNo = toAccountNo;
    }




    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }




    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        users.USERNAME = USERNAME;
    }



    public static String getAccountNo() {
        return AccountNo;
    }

    public static void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }


    public static int getAmount() {
        return Amount;
    }

    public static void setAmount(int amount) {
        Amount = amount;
    }

    public static int Deposit(int prevamount,int addamount) {
        int result=0;
        Amount = prevamount;
        result = Amount + addamount;
        return  result;
    }

    public static int currentbalance(int CurrentAmout,int transferamount) {
        int result=0;

        result = CurrentAmout - transferamount;
        return  result;
    }

    public static int transferaccountcurrentbalance(int prevbalance,int transferamount) {
        int result=0;

        result = prevbalance + transferamount;
        return  result;
    }




    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        id = id;
    }



    public static String getCARDNUMBER() {
        return CARDNUMBER;
    }

    public static void setCARDNUMBER(String CARDNUMBER) {
        users.CARDNUMBER = CARDNUMBER;
    }



}
