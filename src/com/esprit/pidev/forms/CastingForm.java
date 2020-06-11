/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class CastingForm extends BaseForm {
        public CastingForm() {
        this(Resources.getGlobalResources());
        
    }

    @Override
    protected boolean isCurrentCasting() {
        return true;
    }
    
    public CastingForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here   
         Button btnCastingList = new Button("Casting List");
         Button btnAddCasting = new Button("Add Casting");
         
           btnCastingList.addActionListener((evt) -> {
            new CastingListForm(this).show();
        });
          btnAddCasting.addActionListener((evt) -> {
            new AddCastingForm(this).show();
        });
                  this.addAll(new Label("Choose an option :"), btnCastingList,btnAddCasting);
                   this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            this.showBack();
        });

    }
}
