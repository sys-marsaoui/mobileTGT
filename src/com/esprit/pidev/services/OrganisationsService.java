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

import com.esprit.pidev.models.Organisations;
import com.esprit.pidev.models.Organisations;
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
public class OrganisationsService {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Organisations> organisations;

    public OrganisationsService() {
        request = DataSource.getInstance().getRequest();
    }
    
/**********************************Show Organisations*******************************************/
     public ArrayList<Organisations> getAllOrganisations() {
        String url = Statics.BASE_URL + "/organisations/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                organisations = parseOrganisations(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return organisations;
    }

    public ArrayList<Organisations> parseOrganisations(String jsonText) {
        try {
            organisations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> organisationsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) organisationsListJson.get("root");
            for (Map<String, Object> organisationsJson : list) {
                int id =organisationsJson.get("id")==null?0:(int)Float.parseFloat(organisationsJson.get("id").toString());
                String NomOrganisation =organisationsJson.get("nomOrganisation")==null?null:organisationsJson.get("nomOrganisation").toString();
                String Apropos =organisationsJson.get("apropos")==null?null:  organisationsJson.get("apropos").toString();
                String AdresseOrganisation =organisationsJson.get("adresseOrganisation")==null?null:(organisationsJson.get("adresseOrganisation").toString()) ;
                int tel_organisation=organisationsJson.get("telOrganisation")==null?0:(int)Float.parseFloat(organisationsJson.get("telOrganisation").toString());
                String Email_Org =organisationsJson.get("emailOrganisation")==null?null: (organisationsJson.get("emailOrganisation").toString());   
                String LoginOrganisation = organisationsJson.get("loginOrganisation")==null?null: organisationsJson.get("loginOrganisation").toString();
                String PasswordOrganisation = organisationsJson.get("passwordOrganisation")==null?null: organisationsJson.get("passwordOrganisation").toString();
           
               
                organisations.add(new Organisations(id, NomOrganisation, Apropos, AdresseOrganisation, tel_organisation, Email_Org, LoginOrganisation, PasswordOrganisation));
            }

        } catch (IOException ex) {
        }

        return organisations;
    }
    
/**********************************add Organisations*******************************************/
        public boolean addOrganisations (Organisations organisations){
            System.out.println(organisations);
            String url = Statics.BASE_URL + "/organisations/new" +"?NomOrganisation="+organisations.getNomOrganisation()+"&apropos="+organisations.getApropos()+"&adresseOrganisation="+organisations.getAdresseOrganisation()+"&telOrganisation="+organisations.getTel_organisation()+"&emailOrganisation="+organisations.getEmail_Org()+"&loginOrganisation="+organisations.getLoginOrganisation()+"&passwordOrganisation="+organisations.getPasswordOrganisation();

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

        public boolean supOrganisation (Organisations organisations){
            String url = Statics.BASE_URL + "/organisations/sup/" + organisations.getId();
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

        public boolean modifOrganisations(Organisations organisations){
            System.out.println(organisations);
            String url = Statics.BASE_URL + "/organisations/"+organisations.getId()+"/edit"+"?NomOrganisation="+organisations.getNomOrganisation()+"&apropos="+organisations.getApropos()+"&adresseOrganisation="+organisations.getAdresseOrganisation()+"&telOrganisation="+organisations.getTel_organisation()+"&emailOrganisation="+organisations.getEmail_Org();
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
