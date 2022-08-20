package com.webapps2022.ejb;

import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager entityManager; //initates entitymanager

    /**
     *
     */
    public UserService() {
    }


    /**
     *
     * @param username
     * @param userpassword
     * @param name
     * @param surname
     * @param currency
     */
//registers the user
    public void registerUser(String username, String userpassword, String name, String surname, String currency) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String password = userpassword;
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currency, 1000);
            sys_user_group = new SystemUserGroup(username, "users");
            entityManager.persist(sys_user);
            entityManager.persist(sys_user_group);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    /**
     *
     * @param username
     * @param userpassword
     * @param name
     * @param surname
     * @param currency
     */
//registers the admin
    public void registerAdmin(String username, String userpassword, String name, String surname, String currency) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String password = userpassword;
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currency, 1000);
            sys_user_group = new SystemUserGroup(username, "admins");
            entityManager.persist(sys_user);
            entityManager.persist(sys_user_group);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param username
     * @return user
     */
    public SystemUser getUser(String username) {
        try {
            SystemUser user = (SystemUser) entityManager.createNamedQuery("getUser").setParameter("username", username).getSingleResult();
            return user;
        } catch (Exception exception) {
            System.out.println(username + " : Not Found - " + exception);
            return null;
        }
    }
}
