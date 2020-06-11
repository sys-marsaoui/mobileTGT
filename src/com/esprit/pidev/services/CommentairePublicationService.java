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
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.CommentairePublication;
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
public class CommentairePublicationService {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<CommentairePublication> commentairePublications;

    public CommentairePublicationService() {
         request = DataSource.getInstance().getRequest();
    }
    
    public boolean ajouter(CommentairePublication cp) {
        String url = Statics.BASE_URL + "publications/" + cp.getP().getId_pub() +
                "/commentaires/new?contenu=" + cp.getContenu() + "&ratingComm=" +
                cp.getRatingComm();

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
    
    public boolean modifier(CommentairePublication cp) {
        String url = Statics.BASE_URL + "publications/" + cp.getP().getId_pub() +
                "/commentaires/" + cp.getId_comment() + "/edit?contenu=" +
                cp.getContenu() + "&ratingComm=" + cp.getRatingComm();

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
    
    public boolean supprimer(CommentairePublication cp) {
        String url = Statics.BASE_URL + "publications/" + cp.getP().getId_pub()
                + "/commentaires/" + cp.getId_comment() + "/delete";

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
    
    public ArrayList<CommentairePublication> getAllCommentaires(Publication p) {
        String url = Statics.BASE_URL + "/publications/" + p.getId_pub() + "/commentaires" ;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    commentairePublications = parseCommentairesPublications(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commentairePublications;
    }
    
    /*public CommentairePublication getCommentaire(){
        
    }*/
    
        public ArrayList<CommentairePublication> parseCommentairesPublications(String jsonText) throws ParseException {
        try {
            commentairePublications = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> commentairesListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) commentairesListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String contenu = obj.get("contenu").toString();
                int nbinutile = (int)Float.parseFloat(obj.get("nbinutile").toString());
                int ratingComm = (int) Float.parseFloat(obj.get("ratingComm").toString());
                String dateComm = obj.get("dateComm").toString();
                SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00"); 
                Date dateTimeComm=dateFormat.parse(dateComm);
                commentairePublications.add(new CommentairePublication(id, contenu, nbinutile, ratingComm, dateTimeComm));
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return commentairePublications;
    }
}
