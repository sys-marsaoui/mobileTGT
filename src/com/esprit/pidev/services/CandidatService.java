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
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.processing.Result;

/**
 *
 * @author aissa
 */
public class CandidatService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Candidat> candidat;

    public CandidatService() {
        request = DataSource.getInstance().getRequest();
    }


    public ArrayList<Candidat> getAllCandidats() {
        String url = Statics.BASE_URL + "/candidat/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                candidat = parseCandidats(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return candidat;
    }

   public ArrayList<Candidat> parseCandidats(String jsonText) {
        try {
            candidat = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> candidatListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidatListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
int id = result.getAsInteger("id");
int idcasting = result.getAsInteger("idcasting/id");
String titreCasting = result.getAsString("idcasting/titreCasting");
String nomC = result.getAsString("nomC");
int cinCondidat = result.getAsInteger("cinCondidat");
String cv = result.getAsString("cv");
String motivation = result.getAsString("motivation");
String mailaddress = result.getAsString("mailaddress");
int tel = result.getAsInteger("tel");
Casting casting = new Casting(idcasting,titreCasting);            
                candidat.add(new Candidat(id,casting, nomC, cinCondidat, cv, motivation, mailaddress, tel));
                System.out.println(casting);
            }

        } catch (IOException ex) {
        }

        return candidat;
    }

    public boolean addCandidat(Candidat candidat) {
        
        
        System.out.println(candidat);
        
         String url = Statics.BASE_URL + "/candidat/new" + "?nomC=" + candidat.getNomC() + "&cinCondidat=" +candidat.getCinCondidat()+ "&cv=" +candidat.getCv()+"&motivation=" +candidat.getMotivation()+"&mailaddress=" +candidat.getMailaddress()+"&tel=" +candidat.getTel()+"&id_casting="+candidat.getCasting().getId() ;
         
         

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
    
   public boolean supCandidat(Candidat candidat) {
        
        
         String url = Statics.BASE_URL + "/candidat/sup/" +candidat.getId() ;
         
         

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
    
     public boolean modifCandidat(Candidat candidat) {
        
        
        System.out.println(candidat);
        
         String url = Statics.BASE_URL + "/candidat/"+candidat.getId()+ "/edit?" + "nomC="  + candidat.getNomC() + "&cinCondidat=" +candidat.getCinCondidat()
                + "&cv=" +candidat.getCv()+"&motivation=" +candidat.getMotivation()+
                 "&mailaddress=" +candidat.getMailaddress()+"&tel=" +candidat.getTel();
         
         

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

