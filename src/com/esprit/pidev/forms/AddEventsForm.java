/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.capture.Capture;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.pidev.models.Events;
import com.esprit.pidev.services.EventsService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class AddEventsForm extends Form {
     ArrayList<Events> events;
     EventsService eventsService = new EventsService();
     private Image img;
    private String ImageEvents;
     public AddEventsForm(Form previous){
        super ("Add a new Event",BoxLayout.y());
        TextField TitreEvents = new TextField("", "titreEvents");
        TextField DescriptionEvents = new TextField("", "descriptionEvents");
      //  TextField ThemeEvents = new TextField("", "themeEvents");
        Picker ThemeEvents = new Picker() ; 
        ThemeEvents.setStrings("Culture","Economie","Politique","Musique & Dance","Scientifique","Social","Sport");
        TextField AdresseEvents = new TextField("", "adresseEvents");
        TextField prixEvents = new TextField("", "prixEvents");
        //TextField ImageEvents = new TextField("", "imageEvents");
        Picker dateTimePicker =  new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        
        Button uploadImage = new Button("Upload Image");
         uploadImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      ImageEvents =   Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                      img = Image.createImage(ImageEvents);
                     // check path
                     System.out.println(ImageEvents);
                 
                     
                 } catch (IOException ex) {
                  
                 }
             ;
                         }
     
     });
        
        Button btn = new Button("Ajouter evenement");
        String DateEvents=(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(dateTimePicker.getDate());
        
          btn.addActionListener((evt) -> {
          
               
           if((TitreEvents.getText().length()==0)||(DescriptionEvents.getText().length()==0)||(AdresseEvents.getText().length()==0)||(prixEvents.getText().length()==0)){
                               Dialog.show("Alert", "Please fill all the fields", "OK", null);

           }else{
               try{
                   Events events = new Events(TitreEvents.getText(), DescriptionEvents.getText(),ThemeEvents.getText(),AdresseEvents.getText(),DateEvents,Integer.parseInt(prixEvents.getText()),ImageEvents);
               if(new EventsService().addEvents(events)){
                                           Dialog.show("SUCCESS", "evenement ajouter avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "candidat must be a number", "OK", null);
           }
           }
       });
       this.addAll(TitreEvents,DescriptionEvents,AdresseEvents,ThemeEvents,dateTimePicker,prixEvents,uploadImage,btn);
       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
        
}
