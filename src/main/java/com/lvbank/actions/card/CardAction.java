package com.lvbank.actions.card;

import com.lvbank.model.transaction.Card.Card;
import com.opensymphony.xwork2.ActionSupport;

public class CardAction extends ActionSupport {
    private int masterCardId;
    private String cardType;
    private double accountNumber;

    public String execute(){
        String retVal = null;
        Card card = null;
        boolean isCardRegistered;

        CardService service = new CardService();

        if(cardType.compareTo("creditCard")==0){
            card = service.getCardInfo(2,masterCardId);
        } else if (cardType.compareTo("debitCard")==0) {
            card = service.getCardInfo(1,masterCardId);
        } else if (cardType.compareTo("coBrandedCreditCard")==0) {
            card = service.getCardInfo(3,masterCardId);
        }

        isCardRegistered = service.registerCard(card,accountNumber);
        if(isCardRegistered) retVal="success";

        return retVal;
    }
}
