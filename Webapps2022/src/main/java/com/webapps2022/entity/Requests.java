package com.webapps2022.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "userRequests", query = "SELECT r FROM Requests r WHERE r.sentFrom.username = :username")
@Entity
public class Requests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //creates id variable
    @ManyToOne
    SystemUser requestedBy; //allocates variable for requester
    @ManyToOne
    SystemUser sentFrom; //allocates variable for sender

    double amount;

    /**
     *
     * @param sentFrom
     * @param requestedBy
     * @param amount
     */
    public Requests(SystemUser sentFrom, SystemUser requestedBy, double amount) {
        this.sentFrom = sentFrom;
        this.requestedBy = requestedBy;
        this.amount = amount;
    }

    /**
     *
     */
    public Requests() {
    }

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return who sent
     */
    public SystemUser getRequestSentFrom() {
        return sentFrom;
    }

    /**
     *
     * @param sentFrom
     */
    public void setRequestSentFrom(SystemUser sentFrom) {
        this.sentFrom = sentFrom;
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
     * @return who requested
     */
    public SystemUser getRequestedBy() {
        return requestedBy;
    }

    /**
     *
     * @param requestedBy
     */
    public void setRequestedBy(SystemUser requestedBy) {
        this.requestedBy = requestedBy;
    }
}
