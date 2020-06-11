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
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.models.Organisations;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author islem
 */
public class CastingService {

    private ConnectionRequest request;
    private boolean responseResult;
    
    public ArrayList<Casting> casting;

    public CastingService() {
        request = DataSource.getInstance().getRequest();
    }

/**********************************Show Casting*******************************************/
    public ArrayList<Casting> getAllCastings() {
        String url = Statics.BASE_URL + "/casting/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                casting = parseCastings(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return casting;
    }

    public ArrayList<Casting> parseCastings(String jsonText) {
        try {
            casting = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> castingListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            
            List<Map<String, Object>> list = (List<Map<String, Object>>) castingListJson.get("root");
            for (Map<String, Object> castingJson : list) {
                Result result = Result.fromContent(castingJson);
               int id = result.getAsInteger("id");
               int organisationId = result.getAsInteger("organisationId/id");
               String TitreCasting = result.getAsString("titreCasting");
               String DescriptionCasting = result.getAsString("descriptionCasting");
               String dateCastingString =castingJson.get("dateCasting")==null?null:(castingJson.get("dateCasting").toString()) ;
               String dateLPString =castingJson.get("dateLP")==null?null: (castingJson.get("dateLP").toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
                Date dateCasting = null;
                Date dateLP = null;
                try {
                    dateCasting = dateCastingString==null?null: simpleDateFormat.parse(dateCastingString);
                    dateLP = dateLPString==null?null: simpleDateFormat.parse(dateLPString);
                } catch (ParseException ex) {
              //      Logger.getLogger(CastingService.class.getName()).log(Level.SEVERE, null, ex);
                }
                       String AdresseCasting = result.getAsString("adresseCasting");
                       String ImageCasting = result.getAsString("imageCasting");
                       String ThemeCasting = result.getAsString("themeCasting");

                Organisations organisations = new Organisations(organisationId);
                casting.add(new Casting(id, organisationId, TitreCasting, DescriptionCasting, dateCastingString, dateLPString, AdresseCasting, ImageCasting, ThemeCasting));
            }

        } catch (IOException ex) {
        }

        return casting;
    }
    /**********************************add Casting******************************************/
        public boolean addCasting (Casting casting){
            System.out.println(casting);
            String url = Statics.BASE_URL + "/casting/new" +"?titreCasting="+casting.getTitreCasting()+"&descriptionCasting="+casting.getDescriptionCasting()+"&dateCasting="+casting.getDateCasting()+"&dateLP="+casting.getDateLP()+"&adresseCasting="+casting.getAdresseCasting()+"&imageCasting="+casting.getImageCasting()+"&themeCasting="+casting.getThemeCasting();

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
        
/**********************************Supprimer Casting*******************************************/

        public boolean supCasting (Casting casting){
            String url = Statics.BASE_URL + "/casting/sup/" + casting.getId();
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
        
/**********************************Modifier Casting*******************************************/
        public boolean modifCasting(Casting casting){
             System.out.println(casting);
             String url =Statics.BASE_URL +"/casting/"+casting.getId()+"/edit"+"?titreCasting="+casting.getTitreCasting()+"&descriptionCasting="+casting.getDescriptionCasting()+"&dateCasting="+casting.getDateCasting()+"&dateLP="+casting.getDateLP()+"&adresseCasting="+casting.getAdresseCasting()+"&imageCasting="+casting.getImageCasting()+"&themeCasting="+casting.getThemeCasting();
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
