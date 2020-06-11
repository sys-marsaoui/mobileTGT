/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;



import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Organisations;
import com.esprit.pidev.services.OrganisationsService;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddOrganisationsForm extends Form{
    ArrayList<Organisations> organisations;
    OrganisationsService organisationsService = new OrganisationsService();
    public AddOrganisationsForm(Form previous){
        super ("Add a new Organisation",BoxLayout.y());
        TextField NomOrganisation = new TextField("", "nomOrganisation");
        TextField Apropos = new TextField("", "apropos");
        TextField AdresseOrganisation = new TextField("", "AdresseOrganisation",30, TextArea.ANY);
        TextField tel_organisation = new TextField("", "telOrganisation", 8, TextArea.PHONENUMBER);
        TextField Email_Org = new TextField("", "emailOrganisation", 8, TextArea.EMAILADDR);
        TextField LoginOrganisation = new TextField("", "loginOrganisation");
        TextField PasswordOrganisation = new TextField("", "passwordOrganisation", 8, TextArea.PASSWORD);

       Button btn = new Button ("Remplir le formulaire");
       btn.addActionListener((evt)->{
           if((NomOrganisation.getText().length()==0)||(Apropos.getText().length()==0)||(Apropos.getText().length()==0)||(AdresseOrganisation.getText().length()==0)||(tel_organisation.getText().length()==0)||(Email_Org.getText().length()==0)||(LoginOrganisation.getText().length()==0)||(PasswordOrganisation.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Organisations organisations = new Organisations(NomOrganisation.getText(),Apropos.getText(),AdresseOrganisation.getText(),Integer.parseInt(tel_organisation.getText()),Email_Org.getText(),LoginOrganisation.getText(),PasswordOrganisation.getText());
               if(new OrganisationsService().addOrganisations(organisations)){
                                           Dialog.show("SUCCESS", "organisations ajouter avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "candidat must be a number", "OK", null);
           }
           }
       });
       this.addAll(NomOrganisation,Apropos,AdresseOrganisation,tel_organisation,Email_Org,LoginOrganisation,PasswordOrganisation,btn);
     this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
}
