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
import com.esprit.pidev.models.Formation;
import com.esprit.pidev.services.FormationService;


/**
 *
 * @author islem
 */
public class FormationsListForm extends Form {

    FormationService FormationsService = new FormationService();

    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public void afficher() {

        for (Formation f : FormationsService.getAllFormations()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label Titre = new Label("Titre : " + f.getTitre_formation());
            Label Description = new Label("Description : " + f.getDescription_formation());
            Label Duree = new Label("Duree formation: " + f.getDuree_formation());
            Label likes = new Label("likes: " + f.getLikes());
            Label nolikes = new Label("nolikes: " + f.getNolikes());

            Button bSupprimer = new Button("Supprimer");
            Button bModifier = new Button("Modifier");

            Container cb = new Container(BoxLayout.x());

            cb.addAll(bModifier, bSupprimer);

            cDetaille.addAll(esLabel, Titre, Description, Duree, likes, nolikes, cb);

            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);

            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    System.out.println("element selectioner " + f.getTitre_formation());
                    //service.delete(o);
                     FormationsService.supFormation(f);
                     new FormationsListForm().show();
                }
            });

            bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("element selectioner " + f.getTitre_formation());
                    //service.delete(o);
                     FormationsService.modifFormation(f);
                     new ModifierFormationForm(f).show();

                }
            });

        }

    }

    public FormationsListForm(Form previous) {
       

        afficher();
        
         this.setTitle("liste de formations");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                    new FormationsForm().show();

        });

    }

    public FormationsListForm() {

        afficher();
        this.setTitle("liste de formations");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                   new FormationsForm().show();

        });
    }

}
