/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.user;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author islem
 */
public class UserService {
    private ConnectionRequest request;

    private boolean responseResult;
    
    public ArrayList<User> userlist;



    public ArrayList<User> getAllUsers() {
          
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://localhost/pidev/TGTMobileWeb/web/app_dev.php/users/");
        

       
        r.setPost(false);
        r.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userlist = parseUsers(new String(r.getResponseData()));
                r.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
         
        return userlist;
    }

   public ArrayList<User> parseUsers(String jsonText) {
        try {
            userlist = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> userListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> userJson : list) {
              Result result = Result.fromContent(userJson);

             String username = result.getAsString("username");//==null?null:userListJson.get("username").toString();
             String email = result.getAsString("email");//==null?null:userListJson.get("email").toString();
           //  String roles = result.getAsString("roles");//==null?null:userListJson.get("roles").toString();
              // String mailaddress = result.getAsString("mailaddress");
            
                userlist.add(new User(username, email));
            }

        } catch (IOException ex) {
        }

        return userlist;
   }
    
}
