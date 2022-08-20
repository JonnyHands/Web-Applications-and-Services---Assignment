package com.webapps2022.jsf;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.webapps2022.ejb.UserService;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.SystemUserGroup;

@Named
@Singleton
public class defaultUser {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    UserService userService;


//method to create default admin

    public void defaultAdmin1() {

        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser user = userService.getUser("admin1");

        if (user == null) {
            String username = "admin1";
            String userpassword = "admin1";
            String name = "Default";
            String surname = "Admin";
            String currency = "GBP";
            double balance = 1000;

            String usergroup = "admins";

            try {
                SystemUser sys_user;
                SystemUserGroup sys_user_group;

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                String passwd = userpassword;
                md.update(passwd.getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }
                String paswdToStoreInDB = sb.toString();

                sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currency, balance);
                sys_user_group = new SystemUserGroup(username, usergroup);

                entityManager.persist(sys_user);
                entityManager.persist(sys_user_group);

                context.addMessage(null, new FacesMessage("init admin1: username = admin1, password = admin1"));
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException exception) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, exception);
            }
        }

    }
}
