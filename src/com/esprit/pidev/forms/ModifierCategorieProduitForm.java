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
import com.esprit.pidev.models.CategorieProduit;
import com.esprit.pidev.services.CategorieProduitService;
import java.util.ArrayList;


/**
 *
 * @author goldzeo
 */
public class ModifierCategorieProduitForm  extends Form{
         Resources thRessources = UIManager.initFirstTheme("/theme");
    public ModifierCategorieProduitForm(CategorieProduit c){
        
         
           
           
           
      
           
            Label lnomCandidat = new Label("Nom categorie: " );
        TextField firstName = new TextField(c.getNomcategorie(), "nomcategorie", 20, TextArea.ANY);
//         Label lcinCandidat = new Label("Cin Candidat: " );
//        TextField cin = new TextField(c.getCinCondidat().toString(), "12345678", 20, TextArea.NUMERIC);
//         Label lcv = new Label("CV: ");
//        TextField cv = new TextField(c.getCv(), "cv", 20, TextArea.ANY);
//         Label lmotivation = new Label("Motivation: ");
//        TextField motivation = new TextField(c.getMotivation(), "motivation", 20, TextArea.ANY);
//         Label lmailAdress = new Label("MailAdress: " );
//        TextField email = new TextField(c.getMailaddress(), "E-Mail", 20, TextArea.EMAILADDR);
//         Label ltel = new Label("Tel: " );
//        TextField phone = new TextField(c.getTel().toString(), "Phone", 20, TextArea.PHONENUMBER);
//         ComboBox idcasting = new ComboBox(c.getCasting().getId(),castings);
//
//        for (Casting cs : castingService.getAllCastings()) {
//
//            idcasting.addItem(cs);
//            
//        }
            
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
       Button btn = new Button("modifier categorie");

        btn.addActionListener((evt) -> {

                try {
                    // Casting  casting = new  Casting((int)idcasting.getSelectedItem()) ;
                    CategorieProduit categorieProduit = new CategorieProduit(c.getId(),firstName.getText());
                   

                    if (new CategorieProduitService().modifCategorie(categorieProduit)) {
                        Dialog.show("SUCCESS", "categorie modifié", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "categorie must be a number", "OK", null);
                }

            new CategorieProduitListForm(this).show();
        });

        this.addAll(lnomCandidat,firstName, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
             new CategorieProduitListForm(this).show();
        });
    }
    
}
