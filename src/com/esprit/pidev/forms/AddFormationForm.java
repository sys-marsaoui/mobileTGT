/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;



import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.FormationService;
import com.esprit.pidev.models.Formation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddFormationForm extends Form{
    ArrayList<Formation> formations;
    FormationService formationsService = new FormationService();
       private Image img;
    private String image;
    public AddFormationForm(Form previous){
        super ("Add a new Formation",BoxLayout.y());
      
        TextField id = new TextField("", "ID categorie");
        TextField titre_formation = new TextField("", "titre formation");
        TextField description_formation = new TextField("", "description formation");
        //TextField image = new TextField("", "Image formation");
        TextField duree_formation = new TextField("", "Duree formation");
        int likes=0;
        int nolikes=0;
         Button uploadImage = new Button("Upload Image");
         uploadImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      image =   Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                      img = Image.createImage(image);
                     // check path
                     System.out.println(image);
                 
                     
                 } catch (IOException ex) {
                  
                 }
             ;
                         }
     
     });
        
        
        
       Button btn = new Button ("Ajouter Formation");
       btn.addActionListener((evt)->{
           if((titre_formation.getText().length()==0)||(description_formation.getText().length()==0)||(duree_formation.getText().length()==0)||(id.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Formation formation = new Formation(titre_formation.getText(),Integer.parseInt(id.getText()),description_formation.getText(),image,Integer.parseInt(duree_formation.getText()),0,0);
               if(new FormationService().addFormation(formation)){
                                           Dialog.show("SUCCESS", "Formation ajouter avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "Note must be a number", "OK", null);
           }
           }
       });
       this.addAll(id,titre_formation,description_formation,uploadImage,duree_formation,btn);
     this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> { 
            previous.showBack();
        });
    }
    
}
