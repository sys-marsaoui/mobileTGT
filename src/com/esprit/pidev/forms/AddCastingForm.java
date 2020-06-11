/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.capture.Capture;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CastingService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddCastingForm extends Form {
    private Image img;
    private String ImageCasting;
     ArrayList<Casting> casting;
     CastingService castingService = new CastingService();
    public AddCastingForm(Form previous){
        super ("Ajouter Casting",BoxLayout.y());
        TextField TitreCasting = new TextField("", "titreCasting");
        TextField DescriptionCasting = new TextField("", "descriptionCasting");
        Picker DateCastingPicker =  new Picker();
        DateCastingPicker.setType(Display.PICKER_TYPE_DATE);
        Picker DateLPPicker = new Picker();
        DateLPPicker.setType(Display.PICKER_TYPE_DATE);
        TextField AdresseCasting = new TextField("", "adresseCasting");
        //TextField ImageCasting = new TextField("", "imageCasting");
       Picker ThemeCasting = new Picker() ; 
       ThemeCasting.setStrings("Mode & Pub","Theatre & Humour","Cinéma & Fiction","Musique & Dance","Télévision & Radio","Peinture & Art plastique","Sports & Arts de cirque","Audiovisuel");
     //  ThemeCasting.setSelectedString("");
     Button uploadImage = new Button("Upload Image");
     uploadImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      ImageCasting =   Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                     img = Image.createImage(ImageCasting);
                     // check path
                     System.out.println(ImageCasting);
                     
                 } catch (IOException ex) {
                  
                 }
             ;
                         }
     
     });
       Button btn = new Button ("Ajouter Casting");
               String DateCasting=(new SimpleDateFormat("yyyy-MM-dd")).format(DateCastingPicker.getDate());
               String DateLP=(new SimpleDateFormat("yyyy-MM-dd")).format(DateLPPicker.getDate());
   
       btn.addActionListener((evt)->{
           if((TitreCasting.getText().length()==0)||(DescriptionCasting.getText().length()==0)||(AdresseCasting.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Casting casting = new Casting(TitreCasting.getText(), DescriptionCasting.getText(), DateCasting, DateLP, AdresseCasting.getText(),ImageCasting , ThemeCasting.getText());
               if(new CastingService().addCasting(casting)){
                                           Dialog.show("SUCCESS", "Casting ajouter avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "candidat must be a number", "OK", null);
           }
           }
       });
       // consturcture hadha mfmash !!! li fih image upload 
       this.addAll(TitreCasting,DescriptionCasting,DateCastingPicker,DateLPPicker,AdresseCasting,ThemeCasting,uploadImage,btn);
       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           this.showBack();
        });
    }
    
    
}
