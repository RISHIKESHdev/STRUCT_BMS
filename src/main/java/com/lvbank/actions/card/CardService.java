package com.lvbank.actions.card;

import com.lvbank.model.transaction.Card.Card;
import com.lvbank.model.transaction.Card.CoBrandedCreditCard;
import com.lvbank.model.transaction.Card.CreditCard;
import com.lvbank.model.transaction.Card.DebitCard;

import java.util.ArrayList;

public class CardService {

    boolean insertNewCardMaster(Card card){
        boolean isCardInserted=false;

        CardDataLogic dataLogic = new CardDataLogic();

        if(card instanceof DebitCard){
            isCardInserted=dataLogic.insertNewDebitCard((DebitCard) card);
        } else if (card instanceof CoBrandedCreditCard) {
            isCardInserted=dataLogic.insertNewCoBrandedCreditCard((CoBrandedCreditCard) card);
        }else if(card instanceof CreditCard){
            isCardInserted=dataLogic.insertNewCreditCard((CreditCard) card);
        }

        return isCardInserted;
    }

    ArrayList<Integer> getCardMasterIds(int cardType){
        ArrayList<Integer> cardMasterIdNames = null;

        CardDataLogic dataLogic = new CardDataLogic();
        switch(cardType){
            case 1:{
                cardMasterIdNames=dataLogic.fetchAvailableCreditCard();
                break;
            }
            case 2:{
                cardMasterIdNames=dataLogic.fetchAvailableCoBrandedCreditCard();
                break;
            }
            case 3:{
                cardMasterIdNames=dataLogic.fetchAvailableDebitCard();
                break;
            }
        }

        return cardMasterIdNames;
    }
    Card getCardInfo(int cardCode,int cardId){
        Card card = null;

        CardDataLogic dataLogic = new CardDataLogic();


        switch(cardCode){
            case 1:{
                card=dataLogic.getDebitCardMasterById(cardId);
                break;
            }
            case 2:{
                card=dataLogic.getCreditCardMasterById(cardId);
                break;
            }
            case 3:{
                card=dataLogic.getCoBrandCreditCardMasterById(cardId);
                break;
            }
        }

        return card;
    }

    boolean registerCard(Card card,double accountNumber) {
        boolean isCardInserted;

        CardDataLogic dataLogic = new CardDataLogic();

        if(card instanceof CoBrandedCreditCard) isCardInserted=dataLogic.insertCoBrandCreditCard((CoBrandedCreditCard) card,accountNumber);
        else if(card instanceof CreditCard) isCardInserted= dataLogic.insertCreditCard((CreditCard) card, accountNumber);
        else isCardInserted= dataLogic.insertDebitCard((DebitCard) card, accountNumber);

        return isCardInserted;
    }
}
