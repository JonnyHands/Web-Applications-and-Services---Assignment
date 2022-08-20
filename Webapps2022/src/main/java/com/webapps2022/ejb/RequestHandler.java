package com.webapps2022.ejb;

import com.webapps2022.entity.Requests;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.jsf.RequestBean;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@RolesAllowed("users")
public class RequestHandler {

    @PersistenceContext
    EntityManager entityManager; //initiates entity manager
    @EJB
    private Transaction transaction; //calls Transaction class
    @EJB
    private UserService userService; //calls UserService class

    /**
     * @param username
     * @param amount
     * @return the request for money from the user
     */
    public String makeRequest(String username, double amount) {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser cookie = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");
        if (cookie.getUsername().equals(username)) { //checks if user is valid
            return "Select a valid user to request from";
        } else if (amount < 0) { //checks negative amounr isnt being given
            return "Cannot request a negative amount";
        }
        try {
            SystemUser from = userService.getUser(username);
            if ("Admin".equals(from.getName())) { //checks if is an admin
                return "Admins cannot receive requests";
            }
            Requests request = new Requests(from, cookie, amount);
            entityManager.persist(request);
            return "Request accepted";
        } catch (EJBTransactionRolledbackException f) {
            return "User invalid";
        }
    }

//removes the request from the user
    /**
     *
     * @param id
     * @return
     */
    public String removeRequest(Long id) {
        Requests request = entityManager.find(Requests.class, id);
        entityManager.remove(request);
        return "Request Removed";
    }

//completes the request 
    /**
     *
     * @param id
     * @return
     */
    public String completeRequest(Long id) {
        Requests request = entityManager.find(Requests.class, id);
        if (transaction.transact(request.getRequestedBy().getUsername(), request.getAmount()) == "Payment sent") {
            entityManager.remove(request);
        }
        return "Request Completed";
    }
}
