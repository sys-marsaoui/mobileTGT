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

import com.esprit.pidev.models.Cour;
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
public class CourService {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Cour> cours;

    public CourService() {
        request = DataSource.getInstance().getRequest();
    }
    
/**********************************Show Organisations*******************************************/
     public ArrayList<Cour> getAllCour() {
        String url = Statics.BASE_URL + "/cour/allC";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseCours(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return cours;
    }

    public ArrayList<Cour> parseCours(String jsonText) {
        try {
            cours = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> coursListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) coursListJson.get("root");
            for (Map<String, Object> coursJson : list) {
                int cour_id =coursJson.get("courId")==null?0:(int)Float.parseFloat(coursJson.get("courId").toString());
                int formations_id =coursJson.get("formationId")==null?0:(int)Float.parseFloat(coursJson.get("formationId").toString());
                String titre_cour=coursJson.get("titreCour")==null?null:coursJson.get("titreCour").toString();
                String description_cour=coursJson.get("descriptionCour")==null?null:coursJson.get("descriptionCour").toString();
                int duree_cour =coursJson.get("dureeCour")==null?0:(int)Float.parseFloat(coursJson.get("dureeCour").toString());
                String text_cour=coursJson.get("textCour")==null?null:coursJson.get("textCour").toString();
                String image=coursJson.get("image")==null?null:coursJson.get("image").toString();
                int note=coursJson.get("noteCour")==null?0:(int)Float.parseFloat(coursJson.get("noteCour").toString());


                cours.add(new Cour(cour_id,formations_id,titre_cour,description_cour,duree_cour,text_cour,image,note));
            }

        } catch (IOException ex) {
        }

        return cours;
    }
    
/**********************************add Organisations*******************************************/
        public boolean addCour (Cour cour){
            System.out.println(cour);
            String url = "http://127.0.0.1:8000/cour/newC?titreCour="+cour.getTitre_cour()+"&formationId="+cour.getFormation_id()+"&descriptionCour="+cour.getDescription_cour()+"&image="+cour.getImage()+"&noteCour="+cour.getNote_cour()+"&textCour="+cour.getText_cour()+"&dureeCour="+cour.getDuree_cour();
            
            request.setUrl(url);
            request.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                     responseResult = request.getResponseCode()==200;
                     request.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);
            System.out.println(url);
            return responseResult;
        }

/**********************************Supprimer Organisations*******************************************/

        
        public boolean supCour (Cour cour){
            String url = Statics.BASE_URL + "/cour/deleteC/" + cour.getCour_id();
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

        public boolean modifCour(Cour cour){
            System.out.println(cour);
            String url = "http://localhost/TGT/web/app_dev.php/cour/"+cour.getCour_id()+"/editC?titreCour="+cour.getTitre_cour()+"&descriptionCour="+cour.getDescription_cour()+"&dureeCour="+cour.getDuree_cour()+"&textCour="+cour.getText_cour()+"&image="+cour.getImage()+"&formation_id="+cour.getFormation_id()+"&NoteCour="+cour.getNote_cour();
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
        
          public ArrayList<Cour> getMesCoursFormation(int formationId) {
        
        String url = Statics.BASE_URL + "/formation/api/Simulation/"+formationId;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseCours(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return cours;
    }

}
