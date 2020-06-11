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
public class EvenementsForm extends BaseForm {
        public EvenementsForm() {
        this(Resources.getGlobalResources());
        
    }

    @Override
    protected boolean isCurrentEvenements() {
        return true;
    }
    
    public EvenementsForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here    
        Button btnEventsList = new Button("Events List");
        Button btnAddEvents = new Button("Add Events");
        
         btnEventsList.addActionListener((evt) -> {
            new EventsListForm(this).show();
        });
          btnAddEvents.addActionListener((evt) -> {
            new AddEventsForm(this).show();
        });
        
                  this.addAll(new Label("Choose an option :"),btnEventsList,btnAddEvents);
                   this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            this.showBack();
        });

    }
}
