package com.lvbank.actions.card;

import com.adventnet.ds.query.*;

import java.util.ArrayList;
import java.util.List;

public class CardSQLQuery {
    static final String INSERT_CARD_MASTER_QUERY="INSERT INTO Card_Master(card_Name,inception_Date,payment_Gateway,is_Active) VALUE (?,CURRENT_TIMESTAMP,?,TRUE)";
    static final String INSERT_CO_BRAND_CARD_MASTER_QUERY="INSERT INTO Co_Branded_Credit_Card_Master(card_Id,merchant_Name,merchant_Offer_Percentage,interest_Free_Credit_Days,rate_Of_Interest) VALUE (?,?,?,?,?)";
    static final String INSERT_CREDIT_CARD_MASTER_QUERY="INSERT INTO Credit_Card_Master(card_Id,interest_Free_Credit_Days,rate_Of_Interest) VALUE (?,?,?)";
    static final String INSERT_DEBIT_CARD_MASTER_QUERY="INSERT INTO Debit_Card_Master(card_Id,withdrawal_Limit) VALUE(?,?)";
    static final String SELECT_ACTIVE_DEBIT_CARD = "SELECT Id,card_Name FROM Card_Master JOIN DEBIT_CARD_MASTER ON card_Id=Id WHERE is_Active=TRUE";
    static final String SELECT_ACTIVE_CREDIT_CARD = "SELECT Id,card_Name FROM Card_Master JOIN CREDIT_CARD_MASTER ON card_Id=Id WHERE is_Active=TRUE";
    static final String SELECT_ACTIVE_CO_BRAND_CREDIT_CARD = "SELECT Id,card_Name FROM Card_Master JOIN CO_BRANDED_CREDIT_CARD_MASTER ON card_Id=Id WHERE is_Active=TRUE";
    static final String SELECT_CREDIT_CARD_MASTER_BY_ID="SELECT card_Name,inception_Date,payment_Gateway,interest_Free_Credit_Days,rate_Of_Interest FROM Card_MASTER JOIN CREDIT_CARD_MASTER ON Id=card_Id WHERE Id=?";
    static final String SELECT_DEBIT_CARD_MASTER_BY_ID="SELECT card_Name,inception_Date,payment_Gateway,withdrawal_Limit FROM Card_MASTER JOIN DEBIT_CARD_MASTER ON Id=card_Id WHERE Id=?";
    static final String SELECT_CO_BRAND_CREDIT_CARD_MASTER_BY_ID="SELECT card_Name,inception_Date,payment_Gateway,interest_Free_Credit_Days,rate_Of_Interest,merchant_Name,merchant_Offer_Percentage FROM Card_MASTER JOIN CO_BRANDED_CREDIT_CARD_MASTER ON Id=card_Id WHERE Id=?";
    static final String INSERT_CARD="INSERT INTO Card(account_Number,card_Holder_Name,inception_Date,valid_from,expiry_Date,payment_Gateway,CVV,pin_Number,is_Active) VALUE(?,?,?,CURRENT_TIMESTAMP,?,?,?,?,TRUE)";
    static final String SELECT_CVV="SELECT FLOOR(rand() * 900 + 100)";
    static final String SELECT_PIN="SELECT FLOOR(rand() * 9000 + 1000)";
    static final String INSERT_CREDIT_CARD="INSERT INTO Credit_Card(card_Id,interest_Free_Credit_Days,rate_Of_Interest) value(?,?,?)";
    static final String INSERT_DEBIT_CARD="INSERT INTO Debit_Card(card_Id,withdrawal_Limit) VALUE(?,?)";
    static final String INSERT_CO_BRAND_CREDIT_CARD="INSERT INTO CO_BRANDED_CREDIT_CARD(card_Id,merchant_Name,merchant_Offer_Percentage,interest_Free_Credit_Days,rate_Of_Interest) VALUE(?,?,?,?,?)";
    final SelectQuery getActiveCardTypeMasterQuery(String tableName){
        Table cardMaster = new Table("CardMaster","one");
        Table cardTypeMaster = new Table(tableName,"two");
        SelectQuery activeCardTypeMasterQuery = new SelectQueryImpl(cardMaster);

        Column id = new Column("one","ID");
        Column cardName = new Column("one","CARD_NAME");
        activeCardTypeMasterQuery.addSelectColumn(id);
        activeCardTypeMasterQuery.addSelectColumn(cardName);

        Join cardTypeMasterCardJoin = new Join("CardMaster",tableName,new String[]{"ID"},new String[]{"CARD_ID"},Join.INNER_JOIN);
        activeCardTypeMasterQuery.addJoin(cardTypeMasterCardJoin);

        Criteria criteria = new Criteria(new Column("one","IS_ACTIVE"),true,QueryConstants.EQUAL);
        activeCardTypeMasterQuery.setCriteria(criteria);

        return activeCardTypeMasterQuery;
    }
    final SelectQuery getCardTypeMasterByIdQuery(String tableName,int id){
        Table cardMaster = new Table("CardMaster","one");
        Table CardTypeMaster = new Table(tableName,"two");
        SelectQuery cardTypeMasterQuery = new SelectQueryImpl(cardMaster);

        List<Column> colsList= new ArrayList<>();
        colsList.add(new Column("one","CARD_NAME"));
        colsList.add(new Column("one","INCEPTION_DATE"));
        colsList.add(new Column("one","PAYMENT_GATEWAY"));
        if(tableName.compareToIgnoreCase("CreditCardMaster")==0){
            colsList.add(new Column("two","INTEREST_FREE_CREDIT_DAYS"));
            colsList.add(new Column("two","RATE_OF_INTEREST"));
        } else if (tableName.compareToIgnoreCase("CoBrandedCreditCardMaster")==0) {
            colsList.add(new Column("two","INTEREST_FREE_CREDIT_DAYS"));
            colsList.add(new Column("two","RATE_OF_INTEREST"));
            colsList.add(new Column("two","MERCHANT_NAME"));
            colsList.add(new Column("two","MERCHANT_OFFER_PERCENTAGE"));
        }else{
            colsList.add(new Column("two","WITHDRAWAL_LIMIT"));
        }
        cardTypeMasterQuery.addSelectColumns(colsList);

        Join cardTypeMasterJoin = new Join("one","two",new String[]{"ID"},new String[]{"CARD_ID"},Join.INNER_JOIN);
        cardTypeMasterQuery.addJoin(cardTypeMasterJoin);

        Criteria criteria = new Criteria(new Column("CardMaster","ID"),id,QueryConstants.EQUAL);
        cardTypeMasterQuery.setCriteria(criteria);

        return cardTypeMasterQuery;
    }
}
