/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;


import com.codename1.ui.Label;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.codename1.ui.plaf.UIManager;

import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CastingService;


/**
 *
 * @author islem
 */
public class DetailCastingForm extends Form{
             Resources thRessources = UIManager.initFirstTheme("/theme_1");
             CastingService castingService = new CastingService();

public DetailCastingForm (Casting c ){
            Label esLabel = new Label(" ");
            Label lTitreCasting = new Label("Titre: " + c.getTitreCasting());
            Label lDescriptionCasting = new Label("Description: " + c.getDescriptionCasting());
            Label lDateCasting = new Label("Date casting: " + c.getDateCasting());
            Label lDateLP = new Label("Date limite pour postuler: " + c.getDateLP());
            Label lAdresseCasting = new Label("Adresse: " + c.getAdresseCasting());
            Label lThemeCasting = new Label("theme: " + c.getThemeCasting());
            Label lImageCasting = new Label("Image: " + c.getImageCasting());
            
            Button bSupprimer = new Button("Supprimer");
            Button bModifier = new Button("Modifier");
            Button bParticiper = new Button("Participer");
             
            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    System.out.println("element selectioner " + c.getTitreCasting());
                     castingService.supCasting(c);
                     Dialog.show("Alert", "Casting Supprimer", "OK", null);
                }
            });
            bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     System.out.println("element selectioner " + c.getTitreCasting());
                    
                     new ModifierCastingForm(c).show();
                    // castingService.modifCasting(c);

                }
            });
            
            bParticiper.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  new AddCandidatForm(c).show();
                }
            });
            this.setLayout(new FlowLayout(CENTER, TOP));
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bModifier, bSupprimer,bParticiper);
            Container cDetaille = new Container();
            cDetaille.addAll(lTitreCasting,lDescriptionCasting,lAdresseCasting,lDateCasting,lDateLP,lThemeCasting,lImageCasting,cb);
            this.add(cDetaille);
            this.setTitle("Details des Casting");
            this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
         new CastingForm().show();
        });
            
        
             
         
     
}
}
    

