/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CastingService;



/**
 *
 * @author aissa
 */
public class CastingListForm extends Form {
  CastingService castingService = new CastingService();

    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public  CastingListForm(Form previous) {

        for (Casting c : castingService.getAllCastings()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lTitreCasting = new Label("Titre: " + c.getTitreCasting());
            Label lAdresseCasting = new Label("Adresse: " + c.getAdresseCasting());

            

            
            Button Details = new Button("Details");

            Container cb = new Container(BoxLayout.x());

            cb.addAll(Details);

            cDetaille.addAll(esLabel,lTitreCasting,lAdresseCasting,cb);

            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);
            this.add(cGlobal);
            Details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                new DetailCastingForm(c).show();
                }
            });
            
           this.setTitle("liste des Casting");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new CastingForm().show();
        });
          
        }}
    

   
   
    
}
