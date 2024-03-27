package com.lvbank.actions.card;

import com.lvbank.CommonConstant;
import com.lvbank.MySQLConnection;
import com.lvbank.model.transaction.Card.Card;
import com.lvbank.model.transaction.Card.CoBrandedCreditCard;
import com.lvbank.model.transaction.Card.CreditCard;
import com.lvbank.model.transaction.Card.DebitCard;

import java.sql.*;
import java.util.ArrayList;

public class CardDataLogic {
    boolean insertNewDebitCard(DebitCard debitCard){
        boolean isNewDebitCardInserted=false;
        int cardId = 0;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CARD_MASTER_QUERY, Statement.RETURN_GENERATED_KEYS)){
                    ps.setString(1, debitCard.getCardName());
                    ps.setString(2, debitCard.getPaymentGateway());
                    int rs = ps.executeUpdate();
                    if(rs>0){
                        ResultSet rSet = ps.getGeneratedKeys();
                        rSet.next();
                        cardId = rSet.getInt(1);
                    }
                }
                if(cardId>0){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_DEBIT_CARD_MASTER_QUERY)){
                        ps.setInt(1,cardId);
                        ps.setDouble(2,debitCard.getWithdrawalLimit());
                        int rs = ps.executeUpdate();
                        if(rs>0){
                            isNewDebitCardInserted=true;
                            connection.commit();
                        }else{
                            connection.rollback();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return isNewDebitCardInserted;
    }
    boolean insertNewCreditCard(CreditCard creditCard){
        boolean isNewCreditCardInserted=false;
        int cardId = 0;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CARD_MASTER_QUERY, Statement.RETURN_GENERATED_KEYS)){
                    ps.setString(1, creditCard.getCardName());
                    ps.setString(2, creditCard.getPaymentGateway());
                    int rs = ps.executeUpdate();
                    if(rs>0){
                        ResultSet rSet = ps.getGeneratedKeys();
                        rSet.next();
                        cardId = rSet.getInt(1);
                    }
                }
                if(cardId>0){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CREDIT_CARD_MASTER_QUERY)){
                        ps.setInt(1,cardId);
                        ps.setInt(2,creditCard.getInterestFreeCreditDays());
                        ps.setDouble(3,creditCard.getRateOfInterest());
                        int rs = ps.executeUpdate();
                        if(rs>0){
                            isNewCreditCardInserted=true;
                            connection.commit();
                        }else{
                            connection.rollback();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isNewCreditCardInserted;
    }
    boolean insertNewCoBrandedCreditCard(CoBrandedCreditCard coBrandCreditCard){
        boolean isNewCoBrandCreditCardInserted=false;
        int cardId = 0;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CARD_MASTER_QUERY, Statement.RETURN_GENERATED_KEYS)){
                    ps.setString(1, coBrandCreditCard.getCardName());
                    ps.setString(2, coBrandCreditCard.getPaymentGateway());
                    int rs = ps.executeUpdate();
                    if(rs>0){
                        ResultSet rSet = ps.getGeneratedKeys();
                        rSet.next();
                        cardId = rSet.getInt(1);
                    }
                }
                if(cardId>0){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CO_BRAND_CARD_MASTER_QUERY)){
                        ps.setInt(1,cardId);
                        ps.setString(2,coBrandCreditCard.getMerchantName());
                        ps.setDouble(3,coBrandCreditCard.getMerchantOfferPercentage());
                        ps.setInt(4,coBrandCreditCard.getInterestFreeCreditDays());
                        ps.setDouble(5,coBrandCreditCard.getRateOfInterest());
                        int rs = ps.executeUpdate();
                        if(rs>0){
                            isNewCoBrandCreditCardInserted=true;
                            connection.commit();
                        }else{
                            connection.rollback();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isNewCoBrandCreditCardInserted;
    }

    ArrayList<Integer> fetchAvailableDebitCard(){
        ArrayList<Integer> cardMasterId=new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_ACTIVE_DEBIT_CARD)){
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        cardMasterId.add(rs.getInt(1));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return cardMasterId;
    }
    ArrayList<Integer> fetchAvailableCreditCard(){
        ArrayList<Integer> cardMasterId = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_ACTIVE_CREDIT_CARD)){
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        cardMasterId.add(rs.getInt(1));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return cardMasterId;
    }
    ArrayList<Integer> fetchAvailableCoBrandedCreditCard(){
        ArrayList<Integer> cardMasterId = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_ACTIVE_CO_BRAND_CREDIT_CARD)){
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        cardMasterId.add(rs.getInt(1));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return cardMasterId;
    }
    CreditCard getCreditCardMasterById(int cardId){
        CreditCard creditCard = null;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_CREDIT_CARD_MASTER_BY_ID)){
                    ps.setInt(1,cardId);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        creditCard=new CreditCard(rs.getString(1), rs.getDate(2).toLocalDate().atStartOfDay(), true, rs.getString(3), rs.getInt(4),rs.getDouble(5));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return creditCard;
    }
    DebitCard getDebitCardMasterById(int cardId){
        DebitCard debitCard = null;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_DEBIT_CARD_MASTER_BY_ID)){
                    ps.setInt(1,cardId);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        debitCard=new DebitCard(rs.getString(1), rs.getDate(2).toLocalDate().atStartOfDay(), true, rs.getString(3),rs.getDouble(4));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return debitCard;
    }
    CoBrandedCreditCard getCoBrandCreditCardMasterById(int cardId){
        CoBrandedCreditCard coBrandCreditCard=null;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_CO_BRAND_CREDIT_CARD_MASTER_BY_ID)){
                    ps.setInt(1,cardId);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        coBrandCreditCard=new CoBrandedCreditCard(rs.getString(1), rs.getDate(2).toLocalDate().atStartOfDay(), true, rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getString(6),rs.getDouble(7));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return coBrandCreditCard;
    }


    private int generateRandomCVV(Connection connection){
        int cvv = 0;

        try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_CVV)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cvv=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return cvv;
    }
    private int generateRandomPinNumber(Connection connection){
        int pinNumber = 0;

        try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.SELECT_PIN)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pinNumber=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return pinNumber;
    }
    private boolean insertCard(Connection connection,Card card,double accountNumber){
        boolean isCardInserted=false;
        int cvv,pinNumber;
        double cardNumber;

        cvv=generateRandomCVV(connection);
        pinNumber=generateRandomPinNumber(connection);

        if(accountNumber!=0 && pinNumber!=0 && cvv!=0){
            try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CARD, Statement.RETURN_GENERATED_KEYS)){
                ps.setDouble(1,accountNumber);
                ps.setString(2,card.getCardHolderName());
                ps.setString(3, CommonConstant.currentDateTime.format(CommonConstant.dbDateTimeFormat));
                ps.setString(4, CommonConstant.currentDateTime.plusYears(8).format(CommonConstant.dbDateTimeFormat));
                ps.setString(5,card.getPaymentGateway());
                ps.setInt(6,cvv);
                ps.setInt(7,pinNumber);
                int rs=ps.executeUpdate();
                if(rs>0){
                    ResultSet rSet = ps.getGeneratedKeys();
                    rSet.next();
                    cardNumber=rSet.getDouble(1);
                    card.setCardNumber(cardNumber);
                    isCardInserted=true;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        return isCardInserted;
    }
    boolean insertCreditCard(CreditCard creditCard,double accountNumber){
        boolean isCreditCardInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                if(insertCard(connection,creditCard,accountNumber)){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CREDIT_CARD)){
                        ps.setDouble(1,creditCard.getCardNumber());
                        ps.setInt(2,creditCard.getInterestFreeCreditDays());
                        ps.setDouble(3,creditCard.getRateOfInterest());
                        int rs = ps.executeUpdate();
                        isCreditCardInserted = rs>0;
                    }
                }
                if(isCreditCardInserted){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isCreditCardInserted;
    }
    boolean insertDebitCard(DebitCard debitCard,double accountNumber){
        boolean isDebitCardInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                if(insertCard(connection,debitCard,accountNumber)){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_DEBIT_CARD)){
                        ps.setDouble(1,debitCard.getCardNumber());
                        ps.setDouble(2,debitCard.getWithdrawalLimit());
                        int rs = ps.executeUpdate();
                        isDebitCardInserted = rs>0;
                    }
                }
                if(isDebitCardInserted){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isDebitCardInserted;
    }
    boolean insertCoBrandCreditCard(CoBrandedCreditCard coBrandCreditCard,double accountNumber){
        boolean isCoBrandCreditCardInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                if(insertCard(connection,coBrandCreditCard,accountNumber)){
                    try(PreparedStatement ps = connection.prepareStatement(CardSQLQuery.INSERT_CO_BRAND_CREDIT_CARD)){
                        ps.setDouble(1,coBrandCreditCard.getCardNumber());
                        ps.setString(2,coBrandCreditCard.getMerchantName());
                        ps.setDouble(3,coBrandCreditCard.getMerchantOfferPercentage());
                        ps.setInt(4,coBrandCreditCard.getInterestFreeCreditDays());
                        ps.setDouble(5,coBrandCreditCard.getRateOfInterest());
                        int rs = ps.executeUpdate();
                        isCoBrandCreditCardInserted = rs>0;
                    }
                }
                if(isCoBrandCreditCardInserted){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isCoBrandCreditCardInserted;
    }
}
