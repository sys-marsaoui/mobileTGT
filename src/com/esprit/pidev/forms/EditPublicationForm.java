/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class EditPublicationForm extends Form {
    
    Resources r = Resources.getGlobalResources();
    
    public EditPublicationForm(Form previous){
        super("Modifier Publication", BoxLayout.y());
        
        //Add Commands to toolbar
        getToolbar().addCommandToRightBar("", r.getImage("logout.png"), e -> {/*Login Form*/
        });
        getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), e -> {
            new PublicationsListForm(previous).show();
        });
        
    }
    
}
