/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.models.Contrat;
import com.esprit.pidev.services.CastingService;
import com.esprit.pidev.services.ContratService;



/**
 *
 * @author aissa
 */
public class ContratListForm extends Form {
  ContratService castingService = new ContratService();

    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public  ContratListForm(Form previous) {

        for (Contrat c : castingService.getAllContrats()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
         // Label lNomCandidat = new Label("Nom condidat: " + c.getIdcandidat().getNomC());
          //  Label lTitreCasting = new Label("Titre Casting: " + c.getId_casting().getTitreCasting());
            Label lTypeContrat = new Label("TypeContrat: " + c.getTypeContrat());
            Label lDureeContrat = new Label("DureeContrat: " + c.getDureeContrat());
            Label lSalaire = new Label("Salaire: " + c.getSalaire());


            

            
            Button Details = new Button("cree un pdf de contrat");

            Details.addActionListener(d -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "3.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://localhost/TGT/web/app_dev.php/PDF/"+fileName, fileName, true);
    }
    Display.getInstance().execute(fileName);
});
            
            Container cb = new Container(BoxLayout.x());

            cb.addAll(Details);

            cDetaille.addAll(esLabel,lTypeContrat,lDureeContrat,lSalaire,cb);

            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);
            this.add(cGlobal);
            Details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                //new DetailCastingForm(c).show();
                }
            });
            
           //this.setTitle("liste des con");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new EntretientForm().show();
        });
          
        }}
    

   
   
    
}
