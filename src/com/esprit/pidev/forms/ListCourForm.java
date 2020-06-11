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
import com.esprit.pidev.models.Cour;
import com.esprit.pidev.services.CourService;


/**
 *
 * @author nadhem
 */
public class ListCourForm extends Form {

    CourService CoursService = new CourService();

    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public void afficher() {

        for (Cour c : CoursService.getAllCour()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label Titre = new Label("Titre Cour : " + c.getTitre_cour());
            Label Description = new Label("Description Cour: " + c.getDescription_cour());
            Label Duree = new Label("Duree Cour: " + c.getDuree_cour());
            Label Note = new Label("Note Cour: " + c.getNote_cour());
            Label Text = new Label("Text Cour: " + c.getText_cour());
            Label images = new Label("image: " + c.getImage());

            Button Supprimer = new Button("Supprimer");
            Button Modifier = new Button("Modifier");

            Container cb = new Container(BoxLayout.x());

            cb.addAll(Modifier, Supprimer);

            cDetaille.addAll(esLabel, Titre, Description, Duree, Note, Text, images,cb);

            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);

            Supprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    System.out.println("element selectioner " + c.getTitre_cour());
                    //service.delete(o);
                     CoursService.supCour(c);
                     new ListCourForm().show();
                }
            });

            Modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("element selectioner " + c.getTitre_cour());
                    //service.delete(o);
                     CoursService.modifCour(c);
                     new ModifierCourForm(c).show();

                }
            });

        }

    }

    public ListCourForm(Form previous) {
       

        afficher();
        
         this.setTitle("liste de cour");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new FormationsForm().show();
        });

    }

    public ListCourForm() {

        afficher();
        this.setTitle("liste de cour");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new FormationsForm().show();
        });
    }

}
