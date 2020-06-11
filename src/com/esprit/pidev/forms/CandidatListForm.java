/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.services.CandidatService;

/**
 *
 * @author aissa
 */
public class CandidatListForm extends Form {

    
    
    CandidatService candidatService = new CandidatService();
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
 
    public CandidatListForm(Form previous) {
        super("Candidat list", BoxLayout.y());

        for (Candidat c : candidatService.getAllCandidats()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnomCandidat = new Label("Nom candidat: " + c.getNomC());
            Label ltitreCasting = new Label("Titre Casting: " + c.getCasting().getTitreCasting());
            Label ltel = new Label("Tel: " + c.getTel());

            Button bSupprimer = new Button("Supprimer");
            Button bDetaille = new Button("Detaille");
            
            
            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                candidatService.supCandidat(c);
                previous.showBack();
                   
                }
            });
            
            
            bDetaille.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  
                    new DetailCondidatForm(c).show();
                }
            });
            
       
            Container cb = new Container(BoxLayout.x());
            

            cb.addAll(bDetaille, bSupprimer);

            cDetaille.addAll(esLabel,lnomCandidat,ltitreCasting,ltel,cb);

           ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(300, 300));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);


        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
        
        
}
}