/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.enumeration.StatutEnt;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.services.EntretientService;

/**
 *
 * @author aissa
 */
public class EntretientListForm extends Form {

    
    
    EntretientService entretientService = new EntretientService();
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
 
    public EntretientListForm(Form previous) {
        super("Entretient list", BoxLayout.y());

        for (Entretien e : entretientService.getAllEntretients()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnomCandidat = new Label("nom candidat: " + e.getCandidat().getNomC());
            System.out.println(e.getCandidat().getCasting());
            Label ltitreCasting = new Label("Titre Casting: " + e.getCandidat().getCasting().getTitreCasting());
            Label ldateCasting = new Label("date entretient: " + e.getDateEnt());
            Label ltel = new Label("statut: " + e.getStatutEnt());
           
            Button bSupprimer = new Button("Supprimer");
            Button bDetaille = new Button("Detail");
            
            System.out.println(e.getCandidat().getNomC());
//            bSupprimer.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    
//                entretientService.supEntretient(e);
//                previous.showBack();
//                   
//                }
//            });
            
            
            bDetaille.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                 //  Entretien entretient = new Entretien(date, StatutEnt.encour, 9,e.getCandidat());
                    new DetailEntretientForm(e).show();
                }
            });
            
            
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bDetaille);

            cDetaille.addAll(esLabel,lnomCandidat,ltitreCasting,ldateCasting,ltel,cb);

           ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(300, 300));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);


        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
        
        
}
}