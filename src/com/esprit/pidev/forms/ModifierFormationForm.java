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
import com.esprit.pidev.models.Formation;
import com.esprit.pidev.services.FormationService;

/**
 *
 * @author islem
 */
public class ModifierFormationForm extends Form{
     FormationService formationsService = new FormationService();

     Resources thRessources = UIManager.initFirstTheme("/theme_1");
     
     public ModifierFormationForm(Formation f){
        
        Label ids = new Label("id: ");
        TextField id = new TextField("", "ID categorie");
        
        Label ltitre = new Label("Titre formation: ");
        TextField TitreFormation = new TextField(f.getTitre_formation(),"TitreFormation",20,TextArea.ANY);

        Label lDescription = new Label("Description : ");
        TextField Description = new TextField(f.getDescription_formation(), "Description",20,TextArea.ANY);
        
        Label limage = new Label("Image : " );
        TextField image = new TextField(f.getImage(), "image ",8,TextArea.ANY);
        
        Label lduree_formation = new Label(" Duree formations");
        TextField duree_formation = new TextField("", "Duree formation");
     
        
        Button btn = new Button("modifier formation");
         btn.addActionListener((evt)->{
         
               try{
                   Formation formations = new Formation(f.getFormation_id(),Integer.parseInt(id.getText()),TitreFormation.getText(),Description.getText(),image.getText(),Integer.parseInt(duree_formation.getText()));
               if(new FormationService().modifFormation(formations)){
                                           Dialog.show("SUCCESS", "Formation modifier avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "exception", "OK", null);
               }});
           
         
       this.addAll(id,TitreFormation,Description,image,duree_formation,btn);
       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        //    previous.showBack();
                  new ListCourForm().show();

        });
    

         
     }
     }
    
    
    
