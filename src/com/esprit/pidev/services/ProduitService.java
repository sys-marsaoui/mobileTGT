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
import com.esprit.pidev.models.Produit;

/**
 *
 * @author aissa
 */
public class ProduitService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Produit> produit;

    public ProduitService() {
        request = DataSource.getInstance().getRequest();
    }


    public ArrayList<Produit> getAllProduit() {
        String url = Statics.BASE_URL + "/produit/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduit(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return produit;
    }

   public ArrayList<Produit> parseProduit(String jsonText) {
        try {
            produit = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> candidatListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidatListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
int id = result.getAsInteger("id");
int idcategorie = result.getAsInteger("categorie/id");
String nomcategorie = result.getAsString("categorie/nomcategorie");
String nom = result.getAsString("nom");
String description = result.getAsString("description");
int prix = result.getAsInteger("prix");
int quantite = result.getAsInteger("quantite");
String image = result.getAsString("image");

CategorieProduit categorieProduit = new CategorieProduit(idcategorie,nomcategorie);          
                produit.add(new Produit(id, categorieProduit, nom, description, prix, quantite, image ));
            }

        } catch (IOException ex) {
        }

        return produit;
    }

    public boolean addProduit(Produit produit) {
        
        
        System.out.println(produit);
        
         String url = Statics.BASE_URL + "/produit/new" + "?nom=" + produit.getNom() + "&description=" +produit.getDescription()
                + "&prix=" +produit.getPrix()+"&quantite=" +produit.getQuantite()+
                 "&image=" +produit.getImage()+"&categorie_id="+produit.getCategorie_id().getId();
         
         

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
    
   public boolean supProduit(Produit produit) {
        
        
         String url = Statics.BASE_URL + "/produit/sup/" +produit.getId();
         
         

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
    
     public boolean modifProduit(Produit produit) {
        
        
        System.out.println(produit);
        
         String url = Statics.BASE_URL + "/produit/"+produit.getId()+ "/edit?" + "nom="  + produit.getNom() + "&description=" +produit.getDescription()
                + "&prix=" +produit.getPrix() + "&quantite=" +produit.getQuantite() + "&image=" +produit.getImage();
         
         

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

