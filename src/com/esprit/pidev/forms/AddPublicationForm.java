/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class AddPublicationForm extends Form {

    public AddPublicationForm(Form previous) {
        super("Publication Video", BoxLayout.y());

        
        TextField tfContenu = new TextField(null, "Exprimez-vous");
        Location position;
        if (LocationManager.getLocationManager().isGPSEnabled()) {
            position = LocationManager.getLocationManager().getCurrentLocationSync();
            System.out.println(position);
        } else {
            Dialog.show("NOT FOUND", "Votre localisation est introuvable, veuillez verifier que votre GPS est activé", "OK", null);
        }
        this.addAll(tfContenu);
    }

}
