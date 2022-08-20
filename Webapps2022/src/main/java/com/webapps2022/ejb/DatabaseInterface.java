package com.webapps2022.ejb;

import com.webapps2022.entity.Requests;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.Transactions;

import java.util.List;
import javax.annotation.security.RolesAllowed;

public interface DatabaseInterface {

    /**
     * @return creates a List of type SystemUser for usernames
     */
    public List<SystemUser> getUsernames();

    /**
     * @return creates a List of type Transactionsfor transactions received by user
     */
    @RolesAllowed("users")
    public List<Transactions> userTransactionsReceived();

    /**
     * @return creates a List of type Transactions for transactions sent by user
     */
    @RolesAllowed("users")
    public List<Transactions> userTransactionsSent();

    /**
     * 
     * @return creates a List of type Requestsfor requests for a user
     */
    @RolesAllowed("users")
    public List<Requests> userRequests();

    /**
     * 
     * @return creates a List of type Admins for transactions sent 
     */
    @RolesAllowed("admins")
    public List<SystemUser> getAllUsers();

    /**
     *  
     * @return creates a List of type Transactions for all transactions sent 
     */
    @RolesAllowed("admins")
    public List<Transactions> getAllTransactions();

}
