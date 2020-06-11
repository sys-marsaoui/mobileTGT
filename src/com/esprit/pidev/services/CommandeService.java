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
import com.esprit.pidev.models.Commande;

/**
 *
 * @author aissa
 */
public class CommandeService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Commande> commande;

    public CommandeService() {
        request = DataSource.getInstance().getRequest();
    }


    public ArrayList<Commande> getAllCommandes() {
        String url = Statics.BASE_URL + "/candidat/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commande = parseCommande(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commande;
    }

   public ArrayList<Commande> parseCommande(String jsonText) {
        try {
            commande = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> candidatListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidatListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
int id = result.getAsInteger("id");       
                commande.add(new Commande(id));
            }

        } catch (IOException ex) {
        }

        return commande;
    }

    public boolean addCommande() {
        
        
         String url = Statics.BASE_URL + "/commande/new";
         
         

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
    
//   public boolean supCandidat(Candidat candidat) {
//        
//        
//         String url = Statics.BASE_URL + "/candidat/sup/" +candidat.getId() ;
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
//    
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

