package com.webapps2022.jsf;

import com.webapps2022.ejb.UserService;
import com.webapps2022.entity.SystemUser;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named(value = "RegistrationBean")
@RequestScoped
public class RegistrationBean {
    @EJB
    UserService userService; //calls userservice class
    SystemUser systemUser; //calls system suer class
    EntityManager entityManager; //intiiates entitymanager
    String currency; //creates variable for currency
    String username; //creates variable for username
    String userpassword; //creates variable for user password
    String name; //creates variable for name
    String surname; //creates vraible for surname
    
    String usernameinuse = "Username in use"; //result for username in use

    /**
     *
     * @return username in use string result
     */
    public String getusernameinuse() {
        return usernameinuse;
    }

    /**
     *
     * @param result
     */
    public void setusernameinuse(String result) {
        this.usernameinuse = result;
    }
    FacesContext context = FacesContext.getCurrentInstance();
    Double balance;

    /**
     *
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     *
     */
    public RegistrationBean() {
    }


    /**
     *
     * @return correct url for role and creates user
     */
//METHOD TO REGISTER USER, CHECKS NAME ISNT IN USE IF NOT CREATES NEW USER
    public String registerUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        SystemUser currentUser = userService.getUser(username);
        if (currentUser == null) { //checks user isnt in use
            userService.registerUser(username, userpassword, name, surname, currency);
            return "index";// 
        } else { //throws error
            context.addMessage(null, new FacesMessage("Username in use"));
            return "registration.xhtml";
        }
    }
//METHOD TO REGISTER ADMIN, CHECKS NAME ISNT IN USE IF NOT CREATES NEW ADMIN

    /**
     *
     * @return correct url for role and creates admin
     */
public String registerAdmin() {
        SystemUser currentUser = userService.getUser(username);
        if (currentUser == null) { //checks username isnt in use
            userService.registerAdmin(username, userpassword, name, surname, currency);
            return "admin";
        } else { //throws error
            context.addMessage(null, new FacesMessage("Username in use"));
            return "registerAdmin.xhtml";
        }
    }

    /**
     *
     * @return userservice
     */
    public UserService getUsrSrv() {
        return userService;
    }

    /**
     *
     * @param usrSrv
     */
    public void setUsrSrv(UserService usrSrv) {
        this.userService = usrSrv;
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
     * @return password
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     *
     * @param userpassword
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
