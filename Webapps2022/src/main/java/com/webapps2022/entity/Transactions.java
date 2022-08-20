package com.webapps2022.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "getAllTransactions", query = "SELECT t FROM Transactions t")
@NamedQuery(name = "userTransactionsReceived", query = "SELECT t FROM Transactions t WHERE t.sentTo.username = :username")
@NamedQuery(name = "userTransactionsSent", query = "SELECT t FROM Transactions t WHERE t.sentFrom.username = :username")
@Entity
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //creates id variable
    @ManyToOne
    SystemUser sentFrom; //creates variable for person sending transaciton
    @ManyToOne
    SystemUser sentTo; //creates variable from who is being sent the transaction
    double amount; //creates variable for the amount

    /**
     *
     */
    public Transactions() {
    }

    /**
     *
     * @param sentFrom
     * @param sentTo
     * @param amount
     */
    public Transactions(SystemUser sentFrom, SystemUser sentTo, double amount) {
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.amount = amount;
    }

    /**
     *
     * @return who sent
     */
    public SystemUser getSentFrom() {
        return sentFrom;
    }

    /**
     *
     * @param sentFrom
     */
    public void setSentFrom(SystemUser sentFrom) {
        this.sentFrom = sentFrom;
    }

    /**
     *
     * @return who was sent to
     */
    public SystemUser getSentTo() {
        return sentTo;
    }

    /**
     *
     * @param sentTo
     */
    public void setSentTo(SystemUser sentTo) {
        this.sentTo = sentTo;
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
}
