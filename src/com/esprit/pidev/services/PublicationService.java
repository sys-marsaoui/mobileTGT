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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Addresse;
import com.esprit.pidev.models.CatégoriePublication;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class PublicationService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Publication> publications;
    public ArrayList<CatégoriePublication> catégories;
    public String addresse;

    public PublicationService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean ajouter(Publication p) {
        String url = Statics.BASE_URL + "/publications/new?contenu="
                + p.getContenu() + "&video=" + p.getVideo() + "&localisation=" + p.getLocalisation()
                + "&id_cat=" + p.getCp().getId_cat();

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

    public boolean modifier(Publication p) {
        String url = Statics.BASE_URL + "/publications/" + p.getId_pub() + "/edit?contenu="
                + p.getContenu() + "&video=" + p.getVideo() + "&localisation=" + p.getLocalisation()
                + "&id_cat=" + p.getCp().getId_cat();

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

    public boolean supprimer(int id_pub) {
        String url = Statics.BASE_URL + "/publications/" + id_pub + "/delete";

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

    public ArrayList<Publication> getAllPublications() {
        String url = Statics.BASE_URL + "/publications";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    publications = parsePublications(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return publications;
    }
    
    public ArrayList<CatégoriePublication> getAllCatégories(){
         String url = Statics.BASE_URL + "/categorie";
         request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    catégories = parseCatégories(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return catégories;
    }
    
    public ArrayList<Publication> parsePublications(String jsonText) throws ParseException {
        try {
            publications = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> publicationsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) publicationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String contenue = obj.get("contenue").toString();
                String video = obj.get("video").toString();
                String localisation = obj.get("localisation").toString();
                String datePub = obj.get("datePub").toString();
                float ratingPub = Float.parseFloat(obj.get("ratingPub").toString());
                SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00"); 
                Date dateTimePub=dateFormat.parse(datePub);
                Result result = Result.fromContent(publicationsListJson);
                int idCat = result.getAsInteger("categorie/id");
                String nomCat = result.getAsString("categorie/nomCat");
                CatégoriePublication cp = new CatégoriePublication(idCat, nomCat);
                publications.add(new Publication(id, contenue, video, localisation,dateTimePub, cp));
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return publications;
    }
    
    public ArrayList<CatégoriePublication> parseCatégories(String jsonText) throws ParseException{
        try {
            catégories = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> catégoriesListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) catégoriesListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idCat = (int)Float.parseFloat(obj.get("id").toString());
                String nomCat = obj.get("nomCat").toString();
                catégories.add(new CatégoriePublication(idCat, nomCat));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return catégories;
    }
    
    public String getAddresse(String lat,String longitude){
        String url = Statics.BASE_URL1 + "&lat=" + lat + "&lon=" + longitude + "&format=json";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("Accept", "application/json");
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    addresse = parseAddresse(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return addresse;
    }
    
    public String parseAddresse(String jsonText) throws ParseException{
       JSONParser jp = new JSONParser();
        try {
            Map<String, Object> addressesMap = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            Result result = Result.fromContent(addressesMap);
            addresse = result.getAsString("address/state");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return addresse ;
    }
}
