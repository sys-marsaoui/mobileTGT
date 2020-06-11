/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.enumeration.StatutEnt;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.services.EntretientService;


/**
 *
 * @author goldzeo
 */
public class DetailCondidatForm  extends Form{
    
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
    public DetailCondidatForm(Candidat c){
        
        
         Label esLabel = new Label(" ");
            Label lnomCandidat = new Label("Nom candidat: " + c.getNomC());
            Label ltitreCasting = new Label("Titre Casting: " + c.getCasting().getTitreCasting());
            Label lcinCandidat = new Label("Cin Candidat: " + c.getCinCondidat());
            Label lcv = new Label("CV: " + c.getCv());
            Label lmotivation = new Label("Motivation: " + c.getMotivation());
            Label lmailAdress = new Label("MailAdress: " + c.getMailaddress());
            Label ltel = new Label("Tel: " + c.getTel());
            Button bModifier = new Button("modifier");
            Button btnAddentretien = new Button("Add affecter une date");
            
            Picker dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        String date=(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(dateTimePicker.getDate());
     //   String time=(new SimpleDateFormat("HH:MM")).format(timePicker.getDate());
        btnAddentretien.addActionListener((evt) -> {

                try {
                 
                    Entretien entretient = new Entretien(date, StatutEnt.encour, 9,c);
                    
                 if (new EntretientService().addEntretien(entretient)) {
                        Dialog.show("SUCCESS", "entretient inscrit", "OK", null);
                        new EntretientListForm(this).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "entretient must be a number", "OK", null);
                }
           
        });
            
              bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  
                    new ModifierCondidatForm(c).show();
                }
            });
              
                btnAddentretien.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
           
                }
            });
          
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
        Container cDetaille =new Container();
         ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(600, 600));
       cDetaille.addAll(image,esLabel,lnomCandidat,ltitreCasting,lcinCandidat,lcv,lmotivation,lmailAdress,ltel,bModifier,btnAddentretien,dateTimePicker);
        
        this.add(cDetaille);
        
                this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new EntretientForm().show();
        });
    }
    
}
