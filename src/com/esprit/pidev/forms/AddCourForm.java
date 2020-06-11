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
import com.esprit.pidev.services.CourService;
import com.esprit.pidev.models.Cour;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddCourForm extends Form{
    ArrayList<Cour> cours;
    CourService coursService = new CourService();
     private Image img;
    private String image;
    public AddCourForm(Form previous){
        super ("Add a new Cour",BoxLayout.y());
      
        TextField formations_id = new TextField("", "id formation");
        TextField titre_cour= new TextField("", "titre cour");
        TextField description_cour = new TextField("", "description cour");
        TextField duree_cour = new TextField("", "Duree cour");
        TextField text_cour = new TextField("", "Text cour");
      //  TextField image = new TextField("", "Image cour");
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
        
        
       Button btn = new Button ("Ajouter Cour");
       btn.addActionListener((evt)->{
           if((titre_cour.getText().length()==0)||(description_cour.getText().length()==0)||(duree_cour.getText().length()==0)||(formations_id.getText().length()==0)||(text_cour.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Cour cour = new Cour(Integer.parseInt(formations_id.getText()),titre_cour.getText(),description_cour.getText(),Integer.parseInt(duree_cour.getText()),text_cour.getText(),image);
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
       this.addAll(formations_id,titre_cour,description_cour,duree_cour,text_cour,uploadImage,btn);
     this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> { 
            previous.showBack();
        });
    }
    
}
