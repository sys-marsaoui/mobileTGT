/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.user;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghazi
 */
public class Auth {

    public void Login(String Login, String Password) {
        try {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://localhost/pidev/TGTMobileWeb/web/app_dev.php/login/");

            r.addArgument("username", Login);
            r.addArgument("password", Password);

            NetworkManager.getInstance().addToQueueAndWait(r);

            if (r.getResponseCode() == 200) {
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) result.get("root");
                String access = (String) content.get(0).get("Access");
                List<String> roles =  (List<String>) content.get(0).get("Roles");

                System.out.println(access);
                if(access.equalsIgnoreCase("granted")){
                    System.out.println("Access Garanted ! ");
                    System.out.println("Roles" + roles);
                }
                

            } else if (r.getResponseCode() == 400) {
                System.out.println("Access Denied");;
            } else {
                System.out.println("Access Denied");

            }
        } catch (Exception err) {
            Log.e(err);
        }
    }

     public void Register(String Username, String Password , String Email) {
        try {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://localhost/pidev/TGTMobileWeb/web/app_dev.php/register/");

            r.addArgument("username", Username);
            r.addArgument("email", Email);
            r.addArgument("password", Password);
 
            NetworkManager.getInstance().addToQueueAndWait(r);

            if (r.getResponseCode() == 200) {
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) result.get("root");
                String message = (String) content.get(0).get("Message");
 
                System.out.println(message);
                System.out.println("Access Granted");
            } else if (r.getResponseCode() == 400) {
                System.out.println("Access Denied");;
            } else {
                System.out.println("Access Denied");

            }
        } catch (Exception err) {
            Log.e(err);
        }
    } 
}
