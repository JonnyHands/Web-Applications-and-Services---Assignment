/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.UserService;
import com.webapps2022.entity.SystemUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@Stateless
public class LoginBean implements Serializable {
    private String username; //variable for username
    private String password; //variable for password
    @EJB
    private UserService userService;

    /**
     *
     * @return user
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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     *
     * @return correct url for role
     */
//method to login

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.username, this.password); //checks user and password match
        } catch (ServletException exception) {
            context.addMessage(null, new FacesMessage("Login failed:" + exception));
            return "alternative_error";
        }
        boolean isAdmin = context.getExternalContext().isUserInRole("admins"); //initiates admin boolean
        SystemUser currentUser = userService.getUser(username);
        context.getExternalContext().getSessionMap().put("currentUser", currentUser);
        if (!isAdmin) { //admin check
            return "/users/user.xhtml?faces-redirect=true";

        } else {
            return "/admins/admin.xhtml?faces-redirect=true";
        }
    }



    /**
     * method to log out
     */
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }
}
