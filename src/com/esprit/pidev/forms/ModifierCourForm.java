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
import com.esprit.pidev.models.Cour;
import com.esprit.pidev.services.CourService;

/**
 *
 * @author islem
 */
public class ModifierCourForm extends Form{
     CourService coursService = new CourService();

     Resources thRessources = UIManager.initFirstTheme("/theme_1");
     
     public ModifierCourForm(Cour c){
        
        Label ids = new Label("id: ");
        TextField id = new TextField("", "ID Cour");
        
        Label ltitre = new Label("Titre cour: ");
        TextField Titre = new TextField(c.getTitre_cour(),"Titre Cour",20,TextArea.ANY);

        Label lDescription = new Label("Description : ");
        TextField Description = new TextField(c.getDescription_cour(), "Description",20,TextArea.ANY);
        
        Label limage = new Label("Image : " );
        TextField image = new TextField(c.getImage(), "image ",8,TextArea.ANY);
        
        TextField duree_cour = new TextField("", "Duree cour");
        
        Label ltext = new Label("TEXT : " );
        TextField Text_cour = new TextField(c.getText_cour(), "TEXT ",8,TextArea.ANY);
        
        
        Button btn = new Button("Modifier cour");
         btn.addActionListener((evt)->{
         
               try{
                   Cour cours = new Cour(c.getCour_id(),Integer.parseInt(id.getText()),Titre.getText(),Description.getText(),Integer.parseInt(duree_cour.getText()),Text_cour.getText(),image.getText());
               if(new CourService().modifCour(cours)){
                                           Dialog.show("SUCCESS", "Cour modifier avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "exception", "OK", null);
               }});
           
         
       this.addAll(id,Titre,Description,duree_cour,Text_cour,image,btn);

       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        // previous.showBack();
                  new FormationsForm().show();

        });
    

         
     }
     }
    
    