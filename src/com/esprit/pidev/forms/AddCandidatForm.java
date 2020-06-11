/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CandidatService;
import com.esprit.pidev.services.CastingService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class AddCandidatForm extends Form {

    ArrayList<Casting> castings;

    CastingService castingService = new CastingService();
  private Image img;
    private String cv;
    public AddCandidatForm(Casting ca) {

        super("Add a new candidat", BoxLayout.y());
        TextField firstName = new TextField("", "nomC",20, TextArea.ANY);
        TextField cin = new TextField("", "12345678", 8, TextArea.ANY);
        TextField motivation = new TextField("", "motivation", 20, TextArea.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField phone = new TextField("", "Phone", 20, TextArea.PHONENUMBER);
        Button uploadImage = new Button("cv");
     uploadImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      cv =   Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                     img = Image.createImage(cv);
                     // check path
                     System.out.println(cv);
                     // mzal just shn3dw imgPath fil parmeter
                     // mwlf y3mlk haka ?non ya barktek
                     
                 } catch (IOException ex) {
                  
                 }
             ;
                         }
     
     });

      

        Button btn = new Button("passer condidature");

        btn.addActionListener((evt) -> {
            if ((firstName.getText().length() == 0) || (cin.getText().length() == 0) 
                    || (motivation.getText().length() == 0) || (email.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
        
                    Candidat candidat = new Candidat(firstName.getText(),ca, Integer.parseInt(cin.getText()), cv, motivation.getText(), email.getText(),
                            Integer.parseInt(phone.getText()));
                   

                    if (new CandidatService().addCandidat(candidat)) {
                    
                     
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "candidat must be a number", "OK", null);
                }
         
            }
        });

        this.addAll(firstName, cin, uploadImage, motivation, email, phone, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new EntretientForm().show();
            
        });
    }

}
