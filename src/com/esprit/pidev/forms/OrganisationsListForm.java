/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;


import com.codename1.components.ImageViewer;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Organisations;
import com.esprit.pidev.services.OrganisationsService;


/**
 *
 * @author islem
 */
public class OrganisationsListForm extends Form {

    OrganisationsService organisationsService = new OrganisationsService();

    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public OrganisationsListForm(Form previous) {

        for (Organisations o : organisationsService.getAllOrganisations()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnom = new Label("Nom: " + o.getNomOrganisation());
            Label lApropos = new Label("a propos: " + o.getApropos());
            Label lAdresseOrganisation = new Label("adresse: " + o.getAdresseOrganisation());
            Label ltel_organisation = new Label("tel: " + o.getTel_organisation());
            Label lEmail_Org = new Label("Email: " + o.getEmail_Org());

            Button bSupprimer = new Button("Supprimer");
            Button bModifier = new Button("Modifier");

            Container cb = new Container(BoxLayout.x());

            cb.addAll(bModifier, bSupprimer);

            cDetaille.addAll(esLabel, lnom, lApropos, lAdresseOrganisation, ltel_organisation, lEmail_Org, cb);

            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);

            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    System.out.println("element selectioner " + o.getNomOrganisation());
                    //service.delete(o);
                     organisationsService.supOrganisation(o);
                     new OrganisationsListForm(previous).show();
                }
            });

            bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("element selectioner " + o.getNomOrganisation());
                    //service.delete(o);
                     organisationsService.modifOrganisations(o);
                     new ModifierOrganisationsForm(o).show();

                }
            });

        }
          this.setTitle("liste d'organisations");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new OrganisationsForm().show();
        });


    }

 
}
