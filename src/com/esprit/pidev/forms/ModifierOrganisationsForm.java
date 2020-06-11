/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Organisations;
import com.esprit.pidev.services.OrganisationsService;

/**
 *
 * @author islem
 */
public class ModifierOrganisationsForm extends Form{
     OrganisationsService organisationsService = new OrganisationsService();

     Resources thRessources = UIManager.initFirstTheme("/theme_1");
     
     public ModifierOrganisationsForm(Organisations o){
        Label esLabel = new Label(" ");
        
        Label lnom = new Label("Nom Organisation: ");
        TextField NomOrganisation = new TextField(o.getNomOrganisation(),"NomOrganisation",20,TextArea.ANY);
        
        Label lApropos = new Label("a propos: ");
        TextField Apropos = new TextField(o.getApropos(), "apropos",20,TextArea.ANY);
        
        Label lAdresseOrganisation = new Label("adresse: ");
        TextField AdresseOrganisation = new TextField(o.getAdresseOrganisation(), "AdresseOrganisation",30,TextArea.ANY);
        
        Label ltel_organisation = new Label("tel: " );
        TextField tel_organisation = new TextField(o.getTel_organisation().toString(), "telOrganisation",8,TextArea.PHONENUMBER);
        
        Label lEmail_Org = new Label("Email: ");
        TextField Email_Org = new TextField(o.getEmail_Org(), "emailOrganisation", 8, TextArea.EMAILADDR);
        
        Button btn = new Button("modifier organisations");
         btn.addActionListener((evt)->{
         
               try{
                   Organisations organisations = new Organisations(o.getId(),NomOrganisation.getText(),Apropos.getText(),AdresseOrganisation.getText(),Integer.parseInt(tel_organisation.getText()),Email_Org.getText());
               if(new OrganisationsService().modifOrganisations(organisations)){
                                           Dialog.show("SUCCESS", "organisations modifier avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "exception", "OK", null);
               }});
           
         
       this.addAll(NomOrganisation,Apropos,AdresseOrganisation,tel_organisation,Email_Org,btn);
       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
       new OrganisationsListForm(this).show();
        });
    

         
     }}
    
    
    
