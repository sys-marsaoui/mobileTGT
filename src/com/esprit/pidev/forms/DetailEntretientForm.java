/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Entretien;


/**
 *
 * @author goldzeo
 */
public class DetailEntretientForm  extends Form{
    
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
    public DetailEntretientForm(Entretien e){
        
        
         Label esLabel = new Label(" ");
            Label lnomCandidat = new Label("Nom Candidat: " + e.getCandidat().getNomC());
          Label ltitreCasting = new Label("Titre Casting: " + e.getCandidat().getCasting().getTitreCasting());
            Label lDateEntretient = new Label("Date Entretient: " + e.getDateEnt());
            Label lStatuts = new Label("Statuts: " + e.getStatutEnt());
            Label lNote = new Label("Note: " + e.getNoteEnt());
             
            Button devGuide = new Button("voir cv");
            Button btnCall = new Button("call");
            


devGuide.addActionListener(d -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "3.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://localhost/TGT/web/app_dev.php/PDF/"+fileName, fileName, true);
    }
    Display.getInstance().execute(fileName);
});

            
              btnCall.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                 
                    new EspaceEntretientForm(e).show();
                }
            });
              
          
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
        Container cDetaille =new Container();
         ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(600, 600));
       cDetaille.addAll(image,esLabel,lnomCandidat,ltitreCasting,lDateEntretient,lStatuts,lNote,devGuide,btnCall);
        
        this.add(cDetaille);
        
                this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                    
             new EntretientForm().show();
        });

    }
    
}
