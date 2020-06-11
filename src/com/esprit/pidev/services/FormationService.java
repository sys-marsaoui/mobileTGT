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

import com.esprit.pidev.models.Formation;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author islem
 */
public class FormationService {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Formation> formations;

    public FormationService() {
        request = DataSource.getInstance().getRequest();
    }
    
/**********************************Show Organisations*******************************************/
     public ArrayList<Formation> getAllFormations() {
        String url = Statics.BASE_URL + "/formation/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formations = parseFormations(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return formations;
    }

    public ArrayList<Formation> parseFormations(String jsonText) {
        try {
            formations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> formationsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) formationsListJson.get("root");
            for (Map<String, Object> formationsJson : list) {
                int formation_id =formationsJson.get("formationId")==null?0:(int)Float.parseFloat(formationsJson.get("formationId").toString());
                int id =formationsJson.get("id")==null?0:(int)Float.parseFloat(formationsJson.get("id").toString());
                String titre_formation=formationsJson.get("titreFormation")==null?null:formationsJson.get("titreFormation").toString();
                String description_formation=formationsJson.get("descriptionFormation")==null?null:formationsJson.get("descriptionFormation").toString();
                String image=formationsJson.get("image")==null?null:formationsJson.get("image").toString();
                int duree_formation =formationsJson.get("dureeFormation")==null?0:(int)Float.parseFloat(formationsJson.get("dureeFormation").toString());

                formations.add(new Formation(formation_id,id,titre_formation,description_formation,image,duree_formation));
            }

        } catch (IOException ex) {
        }

        return formations;
    }
    
/**********************************add Organisations*******************************************/
        public boolean addFormation (Formation formation){
            System.out.println(formation);
            String url = Statics.BASE_URL + "/formation/newF"+"?titreFormation="+formation.getTitre_formation()+"&descriptionFormation="+formation.getDescription_formation()+"&image="+formation.getImage()+"&dureeFormation="+formation.getDuree_formation()+"&id="+formation.getId();
            request.setUrl(url);
            request.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                     responseResult = request.getResponseCode()==200;
                     request.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);
            return responseResult;
        }

/**********************************Supprimer Organisations*******************************************/

        public boolean supFormation (Formation formation){
            String url = Statics.BASE_URL + "/formation/deleteF/" + formation.getFormation_id();
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

/**********************************Modifier Organisations*******************************************/

        public boolean modifFormation(Formation formation){
            System.out.println(formation);
            String url = Statics.BASE_URL + "/formation/"+formation.getFormation_id()+"/editF"+"?titreFormation="+formation.getTitre_formation()+"&descriptionFormation="+formation.getDescription_formation()+"&image="+formation.getImage()+"&dureeFormation="+formation.getDuree_formation()+"&id="+formation.getId();
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
