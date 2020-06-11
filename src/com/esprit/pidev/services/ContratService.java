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
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.processing.Result;
import com.esprit.pidev.models.Contrat;

/**
 *
 * @author aissa
 */
public class ContratService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Contrat> contrat;

    public ContratService() {
        request = DataSource.getInstance().getRequest();
    }

    public ArrayList<Contrat> getAllContrats() {
        String url = Statics.BASE_URL + "/contrat/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                contrat = parseContrats(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return contrat;
    }

    public ArrayList<Contrat> parseContrats(String jsonText) {
        try {
            contrat = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> candidatListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidatListJson.get("root");
            for (Map<String, Object> candidatJson : list) {
                Result result = Result.fromContent(candidatJson);
                int id = result.getAsInteger("id");
                int idcasting = result.getAsInteger("idcasting/id");
                String titreCasting = result.getAsString("idcasting/titreCasting");
                int id_candidat = result.getAsInteger("id_candidat/id");
                String nomC = result.getAsString("id_candidat/nomC");
                float salaire = result.getAsLong("salaire");
                String typeContrat = result.getAsString("typeContrat");
                int dureeContart = result.getAsInteger("dureeContart");
                Casting casting = new Casting(idcasting, titreCasting);
                Candidat candidat = new Candidat(id_candidat, nomC);
                contrat.add(new Contrat(id, casting, candidat, salaire, typeContrat, dureeContart));
                System.out.println(casting);
            }

        } catch (IOException ex) {
        }

        return contrat;
    }

    public boolean addContrat(Contrat contrat) {

        System.out.println(contrat);

        String url = Statics.BASE_URL + "/contrat/new" + "?+&salaire=" + contrat.getSalaire() + "&typeContrat=" + contrat.getTypeContrat() + "&dureeContart=" + contrat.getDureeContrat();

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

        String url = Statics.BASE_URL + "/candidat/sup/" + candidat.getId();

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

        String url = Statics.BASE_URL + "/candidat/" + candidat.getId() + "/edit?" + "nomC=" + candidat.getNomC() + "&cinCondidat=" + candidat.getCinCondidat()
                + "&cv=" + candidat.getCv() + "&motivation=" + candidat.getMotivation()
                + "&mailaddress=" + candidat.getMailaddress() + "&tel=" + candidat.getTel();

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
