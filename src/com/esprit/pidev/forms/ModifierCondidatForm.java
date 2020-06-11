/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CandidatService;
import com.esprit.pidev.services.CastingService;
import java.util.ArrayList;


/**
 *
 * @author goldzeo
 */
public class ModifierCondidatForm  extends Form{
    ArrayList<Casting> castings;
     CastingService castingService = new CastingService();
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
    public ModifierCondidatForm(Candidat c){
        
         
           
           
           
      
           
            Label lnomCandidat = new Label("Nom candidat: " );
        TextField firstName = new TextField(c.getNomC(), "nomC", 20, TextArea.ANY);
         Label lcinCandidat = new Label("Cin Candidat: " );
        TextField cin = new TextField(c.getCinCondidat().toString(), "12345678", 20, TextArea.NUMERIC);
         Label lcv = new Label("CV: ");
        TextField cv = new TextField(c.getCv(), "cv", 20, TextArea.ANY);
         Label lmotivation = new Label("Motivation: ");
        TextField motivation = new TextField(c.getMotivation(), "motivation", 20, TextArea.ANY);
         Label lmailAdress = new Label("MailAdress: " );
        TextField email = new TextField(c.getMailaddress(), "E-Mail", 20, TextArea.EMAILADDR);
         Label ltel = new Label("Tel: " );
        TextField phone = new TextField(c.getTel().toString(), "Phone", 20, TextArea.PHONENUMBER);
//         ComboBox idcasting = new ComboBox(c.getCasting().getId(),castings);
//
//        for (Casting cs : castingService.getAllCastings()) {
//
//            idcasting.addItem(cs);
//            
//        }
            
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
       Button btn = new Button("modifier condidature");

        btn.addActionListener((evt) -> {

                try {
                    // Casting  casting = new  Casting((int)idcasting.getSelectedItem()) ;
                    Candidat candidat = new Candidat(c.getId(),firstName.getText(), Integer.parseInt(cin.getText()), cv.getText(), motivation.getText(), email.getText(),
                            Integer.parseInt(phone.getText()));
                   

                    if (new CandidatService().modifCandidat(candidat)) {
                        Dialog.show("SUCCESS", "candidat modifier", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "candidat must be a number", "OK", null);
                }

            new EntretientForm().show();
        });

        this.addAll(lnomCandidat,firstName,lcinCandidat,cin,lcv, cv,lmotivation, motivation,lmailAdress, email,ltel, phone, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
             new DetailCondidatForm(c).show();
        });
    }
    
}
