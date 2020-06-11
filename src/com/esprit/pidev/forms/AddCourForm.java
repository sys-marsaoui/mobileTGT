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
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.CourService;
import com.esprit.pidev.models.Cour;
import com.esprit.pidev.models.Formation;
import com.esprit.pidev.services.FormationService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddCourForm extends Form{
    ArrayList<Cour> cours;
    ArrayList<Formation> formations = new ArrayList<Formation>();
    CourService coursService = new CourService();
    FormationService formationService = new FormationService();
    private int idF;
    Image img, img1;
    private static String i;
    public AddCourForm(Form previous){
        super ("Add a new Cour",BoxLayout.y());
        formations = formationService.getAllFormations();
        ComboBox cFormtion = new ComboBox<Formation>();
        for (Formation f : formations) {
            cFormtion.addItem(f.getTitre_formation());
        }
        TextField formations_id = new TextField("", "id formation");
        TextField titre_cour= new TextField("", "titre cour");
        TextField description_cour = new TextField("", "description cour");
        TextField duree_cour = new TextField("", "Duree cour");
        TextField text_cour = new TextField("", "Text cour");
        TextField Note = new TextField("", "Note");
        Button btncapture = new Button("Capture");
        
       Button btn = new Button ("Ajouter Cour");
       this.addAll(cFormtion,titre_cour,description_cour,duree_cour,text_cour,Note,btncapture,btn);
      
       
       
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
       
      
       
       
       cFormtion.addActionListener((evt) -> {
           idF = formations.get(cFormtion.getSelectedIndex()).getFormation_id();
           System.out.println(idF);
       });
       btn.addActionListener((evt)->{
           if((titre_cour.getText().length()==0)||(description_cour.getText().length()==0)||(duree_cour.getText().length()==0)||(btncapture.getText().length()==0)||(text_cour.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Cour cour = new Cour(idF,titre_cour.getText(),description_cour.getText(),Integer.parseInt(duree_cour.getText()),text_cour.getText(),path.getText(),Integer.parseInt(Note.getText()));
                   System.out.println(cour);
                   
                   if(new CourService().addCour(cour)){
                                           Dialog.show("SUCCESS", "Cour ajouter avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "Note must be a number", "OK", null);
           }
           }
       });
       
     this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> { 
            previous.showBack();
        });
    }
    
}
