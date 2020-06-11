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
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.processing.Result;
import com.esprit.pidev.enumeration.StatutEnt;
import com.esprit.pidev.models.Casting;
import java.util.Date;

/**
 *
 * @author aissa
 */
public class EntretientService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Entretien> entretient;

    public EntretientService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addEntretien(Entretien entretient) {
        System.out.println(entretient);
        String url = Statics.BASE_URL + "/entretien/new" + "?dateEnt=" + entretient.getDateEnt() + "&statutEnt=" + entretient.getStatutEnt() + "&noteEnt=" + entretient.getNoteEnt() + "&id_candidat=" + entretient.getCandidat().getId();

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

    public ArrayList<Entretien> getAllEntretients() {
        String url = Statics.BASE_URL + "/entretien/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    entretient = parseEntretient(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                    //   Logger.getLogger(EntretientService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return entretient;
    }

    public ArrayList<Entretien> parseEntretient(String jsonText) throws ParseException {
        try {
            entretient = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> entretientListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) entretientListJson.get("root");
            for (Map<String, Object> entretientJson : list) {
                Result result = Result.fromContent(entretientJson);
                int id = result.getAsInteger("id");
                String dateEntretient = result.getAsString("dateEnt");
                String statutEnt = result.getAsString("statutEnt");
                int noteEnt = result.getAsInteger("noteEnt");
                int idCandidat = result.getAsInteger("idCandidat/id");
                String nomC = result.getAsString("idCandidat/nomC");
                int idcasting = result.getAsInteger("idCandidat/idcasting/id");
                String titrecasting = result.getAsString("idCandidat/idcasting/titreCasting");
                Casting casting = new Casting(idcasting,titrecasting);
                Candidat candidat = new Candidat(idCandidat,nomC,casting);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
                Date dateEnt = null;
                dateEnt = simpleDateFormat.parse(dateEntretient);
                entretient.add(new Entretien(id, dateEnt.toString(), StatutEnt.accepte, noteEnt, candidat));
            }

        } catch (IOException ex) {
        }

        return entretient;
    }

       public boolean supEntretient(Entretien entretient) {
        
        
         String url = Statics.BASE_URL + "/entretien/sup/" +entretient.getId() ;
         
         

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
