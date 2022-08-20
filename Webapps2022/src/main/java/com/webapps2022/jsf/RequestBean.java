package com.webapps2022.jsf;

import com.webapps2022.ejb.RequestHandler;
import com.webapps2022.entity.SystemUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "requestBean")
@RequestScoped
public class RequestBean {

    @EJB
    RequestHandler request; //calls RequestHandler
    Long id; //creates varaible for request id
    double amount; //crsates variable to hold amount
    String username; //creates variable for username
    String result; //creates variable for  result string display

    FacesContext context = FacesContext.getCurrentInstance();
    SystemUser currentUser = (SystemUser) context.getExternalContext().getSessionMap().get("currentUser");

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
     */
    public RequestBean() {
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return result string
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

    /**
     *
     * @return request
     */
//CREATES A REQUEST
    public String request() {
        result = request.makeRequest(username, amount);
        amount = 0.0;
        username = "";
        return "requestPayment.xhtml";
    }

//REMOVES THE REQUEST 
    /**
     *
     * @param id
     * @return request removal
     */
    public String remove(Long id) {
        result = request.removeRequest(id);
        return "viewRequests.xhtml";
    }
//ACCEPTS THE REQUEST

    /**
     *
     * @param id
     * @return request acception
     */
    public String accept(Long id) {
        result = request.completeRequest(id);
        return "viewRequests.xhtml";
    }
}
