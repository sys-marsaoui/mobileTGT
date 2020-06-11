/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.enumeration.StatutEnt;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.services.EntretientService;


/**
 *
 * @author goldzeo
 */
public class EspaceEntretientForm  extends Form{
    
        
    public EspaceEntretientForm(Entretien e){
         super("espace entretien",  new BorderLayout());
        BrowserComponent browser = new BrowserComponent();
browser.setURL("http://localhost/TGT/web/app_dev.php/espaceEntretien");
 

        
 Container cDetaille =new Container();
       cDetaille.addAll();
       
        this.add(BorderLayout.CENTER,browser);
        
                this.getToolbar().addCommandToLeftBar("accepter", null, (evt) -> {
             new AddContratForm(e).show();
        });
                
                this.getToolbar().addCommandToRightBar("call", null, (evt) -> {
            Display.getInstance().dial("+21650713446");
        });
    }
    
}
