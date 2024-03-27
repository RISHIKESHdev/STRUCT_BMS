package com.lvbank.actions.card;

import com.lvbank.CommonConstant;
import com.lvbank.model.transaction.Card.Card;
import com.lvbank.model.transaction.Card.CoBrandedCreditCard;
import com.lvbank.model.transaction.Card.CreditCard;
import com.lvbank.model.transaction.Card.DebitCard;
import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDateTime;

public class CardMasterAction extends ActionSupport {
    private String cardName,cardPaymentGateway,cardType;
    private double withdrawalLimit;
    private int interestFreeCreditDays;
    private String merchantName;
    private double merchantOfferPercentage,rateOfInterest;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPaymentGateway() {
        return cardPaymentGateway;
    }

    public void setCardPaymentGateway(String cardPaymentGateway) {
        this.cardPaymentGateway = cardPaymentGateway;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    public int getInterestFreeCreditDays() {
        return interestFreeCreditDays;
    }

    public void setInterestFreeCreditDays(int interestFreeCreditDays) {
        this.interestFreeCreditDays = interestFreeCreditDays;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getMerchantOfferPercentage() {
        return merchantOfferPercentage;
    }

    public void setMerchantOfferPercentage(double merchantOfferPercentage) {
        this.merchantOfferPercentage = merchantOfferPercentage;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String execute(){
        String retVal;

        LocalDateTime inceptionDate;
        boolean isActive=true,isCardMasterInserted=false;
        Card card = null;

        CardService service = new CardService();

        inceptionDate= CommonConstant.currentDateTime;
        isActive=true;

        if(cardType.compareTo("debitCard")==0){
            withdrawalLimit=Double.parseDouble(String.valueOf(withdrawalLimit));
            card = new DebitCard(cardName,inceptionDate,isActive,cardPaymentGateway,withdrawalLimit);
        }else if(cardType.compareTo("coBrandedCreditCard")==0){
            merchantOfferPercentage=Double.parseDouble(String.valueOf(merchantOfferPercentage));
            rateOfInterest=Double.parseDouble(String.valueOf(rateOfInterest));
            interestFreeCreditDays=Integer.parseInt(String.valueOf(interestFreeCreditDays));

            card = new CoBrandedCreditCard(cardName,inceptionDate,isActive,cardPaymentGateway,interestFreeCreditDays,rateOfInterest,merchantName,merchantOfferPercentage);
        }else if(cardType.compareTo("creditCard")==0){
            interestFreeCreditDays=Integer.parseInt(String.valueOf(interestFreeCreditDays));
            rateOfInterest=Double.parseDouble(String.valueOf(rateOfInterest));

            card = new CreditCard(cardName,inceptionDate,isActive,cardPaymentGateway,interestFreeCreditDays,rateOfInterest);
        }

        isCardMasterInserted=service.insertNewCardMaster(card);

        if(isCardMasterInserted){
            retVal="success";
        }else{
            retVal="error";
        }

        return retVal;
    }
}
