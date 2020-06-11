/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;



import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Categorie;
import com.esprit.pidev.services.FormationService;
import com.esprit.pidev.models.Formation;
import com.esprit.pidev.utils.RatingWidget;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddFormationForm extends Form{
    ArrayList<Formation> formations;
    ArrayList<Categorie> categories = new ArrayList<Categorie>();
    FormationService formationsService = new FormationService();
    private static String i;
    Image img, img1;
    public AddFormationForm(Form previous){
        super ("Add a new Formation",BoxLayout.y());
        TextField id = new TextField("", "ID categorie");
        TextField titre_formation = new TextField("", "titre formation");
        TextField description_formation = new TextField("", "description formation");
      
        RatingWidget rw = new RatingWidget();
        Slider rating = rw.createStarRankSlider(true);
        
        
        TextField duree_formation = new TextField("", "Duree formation");
       
        Button btncapture = new Button("Capture");
        int nolikes=0;
        
       
       Button btn = new Button ("Ajouter Formation");
       TextField path = new TextField("");
       btncapture.addActionListener(x -> {
            ScaleImageLabel imageForm = new ScaleImageLabel();

            i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i != null) {
                Image im;
                try {
                    im = Image.createImage(i).scaledSmallerRatio(1000, 500);
                    imageForm.setIcon(im);
                    refreshTheme();
                    System.out.println("path :" + i);
                    path.setText(i);

                } catch (IOException ex) {
                    System.out.println("Could not load image!");
                }
            }
        });
       
       btn.addActionListener((evt)->{
           if((titre_formation.getText().length()==0)||(description_formation.getText().length()==0)||(duree_formation.getText().length()==0)||(path.getText().length()==0)||(id.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   int likes=rating.getProgress();
        System.out.println(likes);
                   Formation formation = new Formation(titre_formation.getText(),Integer.parseInt(id.getText()),description_formation.getText(),path.getText(),Integer.parseInt(duree_formation.getText()),likes,0);
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
       this.addAll(id,titre_formation,description_formation,duree_formation,btncapture,rating,btn);
     this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> { 
            previous.showBack();
        });
    }
    
}
