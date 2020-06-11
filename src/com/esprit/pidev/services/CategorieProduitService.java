/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.processing.Result;
import com.esprit.pidev.models.CategorieProduit;

/**
 *
 * @author aissa
 */
public class CategorieProduitService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<CategorieProduit> categorieProduitList;

    public CategorieProduitService() {
        request = DataSource.getInstance().getRequest();
    }


    public ArrayList<CategorieProduit> getAllCaegorie() {
        String url = Statics.BASE_URL + "/categorieprod/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorieProduitList = parseCategorie(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return categorieProduitList;
    }

   public ArrayList<CategorieProduit> parseCategorie(String jsonText) {
        try {
            categorieProduitList = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> candidatListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidatListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
int id = result.getAsInteger("id");
String nomcategorie = result.getAsString("nomcategorie");

          
                categorieProduitList.add(new CategorieProduit(id,nomcategorie));
            }

        } catch (IOException ex) {
        }

        return categorieProduitList;
    }

    public boolean addCandidat(CategorieProduit categorieProduit) {
        
        
        System.out.println(categorieProduit);
        
         String url = Statics.BASE_URL + "/categorie/new" + "?nomcategorie=" + categorieProduit.getNomcategorie();
         
         

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
   public boolean supCategorie(CategorieProduit categorieProduit) {
        
        
         String url = Statics.BASE_URL + "/categorie/sup/" +categorieProduit.getId() ;
         
         

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
     public boolean modifCategorie(CategorieProduit categorieProduit) {
        
        
        System.out.println(categorieProduit);
        
         String url = Statics.BASE_URL + "/categorie/"+categorieProduit.getId()+ "/edit?" + "nomcategorie="  + categorieProduit.getNomcategorie();
         
         

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
}

