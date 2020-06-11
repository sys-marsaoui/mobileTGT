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
public class OrganisationsForm extends BaseForm{
        public OrganisationsForm() {
        this(Resources.getGlobalResources());
        
    }

    @Override
    protected boolean isCurrentOrganisations() {
        return true;
    }
    
    public OrganisationsForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here  
         Button btnOrganisationsList = new Button("Organisations List");
         Button btnAddOrganisations = new Button("Add Organisation");
         
          btnOrganisationsList.addActionListener((evt) -> {
            new OrganisationsListForm(this).show();
        });
           btnAddOrganisations.addActionListener((evt) -> {
            new AddOrganisationsForm(this).show();
        });
           
        this.addAll(new Label("Choose an option :"), btnOrganisationsList,btnAddOrganisations);


        
    }
}
