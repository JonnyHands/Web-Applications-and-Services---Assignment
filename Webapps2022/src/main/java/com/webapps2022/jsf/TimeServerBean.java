/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.thrift.TimeClient;
import com.webapps2022.thrift.Server;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "TimeServerBean")
@RequestScoped
public class TimeServerBean implements Serializable {
TimeClient client = new TimeClient();
Server server = new Server();

    /**
     *
     */
    public TimeServerBean() {

    }

    /**
     *
     * @return
     */
    public String getCurrentTime() {

    long result = (long) TimeClient.getTime();
    System.out.println(result);
    java.util.Date time=new java.util.Date((result)); 
    return time.toString();

    }


}
