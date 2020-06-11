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
import com.esprit.pidev.models.Panier;
import com.esprit.pidev.models.Produit;

/**
 *
 * @author aissa
 */
public class PanierService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Panier> panier;

    public PanierService() {
        request = DataSource.getInstance().getRequest();
    }


    public ArrayList<Panier> getAllPanier() {
        String url = Statics.BASE_URL + "/panier/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                panier = parsePanier(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return panier;
    }

   public ArrayList<Panier> parsePanier(String jsonText) {
        try {
            panier = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> produitListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) produitListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
int id = result.getAsInteger("id");
int idprod = result.getAsInteger("produit/id");
String nomprod = result.getAsString("produit/nom");
String descprod = result.getAsString("produit/description");
int prixprod = result.getAsInteger("produit/prix");
int quantiteprod = result.getAsInteger("produit/quantite");
String imageprod = result.getAsString("produit/image");

            Produit produit = new Produit(idprod,nomprod,descprod,prixprod,quantiteprod,imageprod);           
                panier.add(new Panier(id,produit));
            }

        } catch (IOException ex) {
        }

        return panier;
    }

    public boolean addPanier(Panier panier) {
        
        
        System.out.println(panier);
        
         String url = Statics.BASE_URL + "/panier/new" + "?produit_id="+ panier.getProduit_id().getId();
         
         

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
    
   public boolean supPanier(Panier panier) {
        
        
         String url = Statics.BASE_URL + "/panier/sup/" +panier.getId() ;
         
         

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
    
//     public boolean modifCandidat(Candidat candidat) {
//        
//        
//        System.out.println(candidat);
//        
//         String url = Statics.BASE_URL + "/candidat/"+candidat.getId()+ "/edit?" + "nomC="  + candidat.getNomC() + "&cinCondidat=" +candidat.getCinCondidat()
//                + "&cv=" +candidat.getCv()+"&motivation=" +candidat.getMotivation()+
//                 "&mailaddress=" +candidat.getMailaddress()+"&tel=" +candidat.getTel();
//         
//         
//
//        request.setUrl(url);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
//                request.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//
//        return responseResult;
//    }
    
}

