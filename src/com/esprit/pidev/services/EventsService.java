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
import com.esprit.pidev.models.Events;
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
public class EventsService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Events> events;

    public EventsService() {
        request = DataSource.getInstance().getRequest();
    }

    /**
     * ********************************Show
     * Events******************************************
     */
    public ArrayList<Events> getAllEvents() {
        String url = Statics.BASE_URL + "/events/all";

        System.out.println(url);

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return events;
    }

    public ArrayList<Events> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> eventsJson : list) {
                Result result = Result.fromContent(eventsJson);

                int id = result.getAsInteger("id");
                int organisationId = result.getAsInteger("organisationId/id");
                String TitreEvents = result.getAsString("titreEvents");

                String DescriptionEvents = result.getAsString("descriptionEvents");
                String ThemeEvents = result.getAsString("themeEvents");
                String AdresseEvents = result.getAsString("adresseEvents");

                String dateEventsString = eventsJson.get("dateEvents") == null ? null : (eventsJson.get("dateEvents").toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
                Date DateEvents = null;
                try {
                    DateEvents = dateEventsString == null ? null : simpleDateFormat.parse(dateEventsString);

                } catch (ParseException ex) {
                    //      Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
                }

                int prixEvents = result.getAsInteger("prixEvents");

                String ImageEvents = result.getAsString("imageEvents");
                Organisations organisations = new Organisations(organisationId);

                events.add(new Events(id, organisationId, TitreEvents, DescriptionEvents, ThemeEvents, AdresseEvents, dateEventsString, prixEvents, ImageEvents));
            }

        } catch (IOException ex) {
        }

        return events;
    }

    /**
     * ********************************add Events*****************************************
     */
    public boolean addEvents(Events events) {
        System.out.println(events);
        String url = Statics.BASE_URL + "/events/new" + "?titreEvents=" + events.getTitreEvents() + "&descriptionEvents=" + events.getDescriptionEvents() + "&themeEvents=" + events.getThemeEvents() + "&adresseEvents=" + events.getAdresseEvents() + "&DateEvents=" + events.getDateEvents() + "&prixEvents=" + events.getPrixEvents() + "&imageEvents=" + events.getImageEvents();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }

    /**
     * ********************************Supprimer
     * Evenement******************************************
     */
    public boolean supEvent(Events events) {
        String url = Statics.BASE_URL + "/events/sup/" + events.getId();
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

    /**
     * ********************************Modifier
     * evenement******************************************
     */
    public boolean modifEvents(Events events) {
        System.out.println(events);
        // String url = Statics.BASE_URL+"/events/"+events.getId()+"/edit"+"?titreEvents="+events.getTitreEvents()+"&descriptionEvents="+events.getDescriptionEvents()+"&themeEvents="+events.getThemeEvents()+"&adresseEvents="+events.getAdresseEvents()+"&DateEvents="+events.getDateEvents()+"&prixEvents="+events.getPrixEvents()+"&imageEvents="+events.getImageEvents();
        String url = Statics.BASE_URL + "/events/" + events.getId() + "/edit" + "?dateEvents=" + events.getDateEvents();

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
    /******************************Supprimer par date********************************************/

    public void supprimerParDate() {

        /****formater la date systeme en format yyyy-mm-dd*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateSysString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Date dateSys = null;
        try {
            dateSys = dateSysString == null ? null : simpleDateFormat.parse(dateSysString);

        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }
 
        for (Events e : this.getAllEvents()) {

            
             /****formater la date d'evenement en format yyyy-mm-dd*/
            Date dateEvent = null;
 

            try {
               dateEvent = simpleDateFormat.parse(e.getDateEvents());

            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            
            
           /*****comparer les deux dates**/
            
           if(dateEvent.getTime()<dateSys.getTime()){
                
                System.out.println("suppression ok "+e.getId());
                
                this.supEvent(e);
                
            }
            
            
           

        }

    }

}
