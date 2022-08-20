
package com.webapps2022.jsf;

import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import com.webapps2022.ejb.Transaction;
import com.webapps2022.entity.SystemUser;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "TransactionBean")
@ConversationScoped
public class TransactionBean implements Serializable{
  
    @EJB
    Transaction transaction; //calls Transaction entitybean
    double amount; //creates variable to store amount
    String sentTo; //creates variable to store user being sent transaction
    String result; //creates varaible to store string result to display
    FacesContext context = FacesContext.getCurrentInstance();
    SystemUser currentUser = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
    public TransactionBean() {
    }

    /**
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return transaction sent to
     */
    public String getRecipient() {
        return sentTo;
    }

    /**
     *
     * @param sentTo
     */
    public void setRecipient(String sentTo) {
        this.sentTo = sentTo;
    }

    /**
     *
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }





    public String sendPayment(){ //METHOD TO SEND PAYMENT, CALLS THE TRNSACT METHOD
        result = transaction.transact(sentTo, amount);
        amount = 0.0;
        sentTo = "";
        return "sendPayment.xhtml";
    }
}
