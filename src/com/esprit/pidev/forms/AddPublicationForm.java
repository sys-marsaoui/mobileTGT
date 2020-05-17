/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.CatégoriePublication;
import com.esprit.pidev.services.PublicationService;
import java.util.ArrayList;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class AddPublicationForm extends Form {
    ArrayList<CatégoriePublication> catégories;
    Resources r = Resources.getGlobalResources();
    PublicationService ps = new PublicationService();
    

    public AddPublicationForm(Form previous) {
        super("Publication Video", BoxLayout.y());
        getToolbar().addCommandToRightBar("", r.getImage("logout.png"), e -> {/*Login Form*/});
        getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), e -> {new PublicationsForm().show();});

        Label lbContenu = new Label("Exprimez-vous");
        TextArea taContenu = new TextArea(10, 30);
        ComboBox catégories = new ComboBox(this.catégories);
        Label lbCatégorie = new Label("Catégories",r.getImage("controls.png").scaled(120,120));
        for (CatégoriePublication cp : ps.getAllCatégories()) {
            catégories.addItem(cp.getNomCat());
        }
        Label lbVideo = new Label("Video",r.getImage("photo.png").scaled(120,120));
        Location position;
        if (LocationManager.getLocationManager().isGPSEnabled()) {
            position = LocationManager.getLocationManager().getCurrentLocationSync();
            String latitude = Double.toString(position.getLatitude());
            String longitude = Double.toString(position.getLongitude());
            System.out.println(ps.getAddresse(latitude, longitude));
        } else {
            Dialog.show("NOT FOUND", "Votre localisation est introuvable, veuillez verifier que votre GPS est activé", "OK", null);
        }
        this.addAll(lbContenu,taContenu,lbCatégorie,catégories,lbVideo);
    }
}
