package com.webapps2022.jsf;

import com.webapps2022.ejb.DatabaseInterface;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.Requests;
import com.webapps2022.entity.Transactions;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("GetLists")
@RequestScoped
public class GetLists{
    @EJB
    DatabaseInterface makeList;

    /**
     *
     */
    public GetLists() {
    }

    /**
     *
     * @return list of transaction received
     */
    public List<Transactions> getUserTransactionsReceived() {
        return makeList.userTransactionsReceived();
    }

    /**
     *
     * @return list of transactions sent
     */
    public List<Transactions> getUserTransactionsSent() {
        return makeList.userTransactionsSent();
    }

    /**
     *
     * @return list of user requests
     */
    public List<Requests> getUserRequests() {
        return makeList.userRequests();
    }

    /**
     *
     * @return list of all user roles
     */
    @RolesAllowed("admins")
    public List<SystemUser> getAllUsers() {
        return makeList.getAllUsers();
    }

    /**
     *
     * @return list of all transactions
     */
    @RolesAllowed("admins")
    public List<Transactions> getAllTransactions() {
        return makeList.getAllTransactions();
    }
}
