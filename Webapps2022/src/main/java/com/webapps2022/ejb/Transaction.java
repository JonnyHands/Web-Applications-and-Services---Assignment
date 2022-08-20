package com.webapps2022.ejb;

import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.Transactions;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.faces.context.FacesContext;
import javax.jms.TransactionRolledBackException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(REQUIRED)
@RolesAllowed("users")
public class Transaction {

    @PersistenceContext
    EntityManager entityManager; //initiates entity manager
    @EJB
    private UserService userService; //calls UserService class

    public String transact(String receiver, double amount) {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser senderCookie = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
        if (senderCookie.getUsername().equals(receiver)) { //checks username is valid
            return "Select a valid user to send to";
        } else if (amount < 0 || (senderCookie.getBalance() < amount)) { //checks amount is valid
            return "Invalid amount";
        }
        try {
            SystemUser currentUser = userService.getUser(receiver);
            SystemUser recipient = entityManager.find(SystemUser.class, currentUser.getId());
            if ("Admin".equals(recipient.getName())) { //checks for admin
                return "Admins cannot receive payments";
            }
            SystemUser sender = entityManager.find(SystemUser.class, senderCookie.getId()); //finds id
            recipient.setBalance(recipient.getBalance() + amount); //adds to balance of recipient
            sender.setBalance(sender.getBalance() - amount); //takes away from balance of sender
            Transactions transaction = new Transactions(sender, recipient, amount); //initiates new transaction
            entityManager.persist(recipient);
            entityManager.persist(sender);
            entityManager.persist(transaction);
            context.getExternalContext().getSessionMap().remove("currentUser");
//looks for user
            context.getExternalContext().getSessionMap().put("currentUser", sender);
        } catch (EJBTransactionRolledbackException f) {
            return "User not found";
        }
        return "Payment sent";
    }
}
