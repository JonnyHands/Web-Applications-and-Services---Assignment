package com.webapps2022.ejb;

import com.webapps2022.entity.Requests;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.Transactions;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DatabaseBean implements DatabaseInterface {

    @PersistenceContext
    EntityManager entityManager;

    public DatabaseBean() {

    }

    /**
     *
     * @return method to get all users
     */
    @Override
    public synchronized List<SystemUser> getAllUsers() {
        return entityManager.createNamedQuery("getAllUsers").getResultList();
    }

    /**
     *
     * @return method to get all usernames
     */
    @Override
    public synchronized List<SystemUser> getUsernames() {
        return entityManager.createNamedQuery("getUsernames").getResultList();
    }

    /**
     *
     * @return method to get all transactions
     */
    @Override
    public synchronized List<Transactions> getAllTransactions() {
        return entityManager.createNamedQuery("getAllTransactions").getResultList();
    }

    /**
     *
     * @return method to get all requests for current user
     */
    @Override
    public synchronized List<Requests> userRequests() {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser currentUser = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
        return entityManager.createNamedQuery("userRequests").setParameter("username", currentUser.getUsername()).getResultList();
    }

    /**
     *
     * @return method to get all transactions received by current user
     */
    @Override
    public List<Transactions> userTransactionsReceived() {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser currentUser = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
        return entityManager.createNamedQuery("userTransactionsReceived").setParameter("username", currentUser.getUsername()).getResultList();
    }

    /**
     *
     * @return method to get all transactions sent by current user
     */
    @Override
    public List<Transactions> userTransactionsSent() {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser currentUser = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
        return entityManager.createNamedQuery("userTransactionsSent").setParameter("username", currentUser.getUsername()).getResultList();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("UserStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("UserStore: PreDestroy");
    }
}
