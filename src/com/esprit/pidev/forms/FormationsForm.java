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
public class FormationsForm extends BaseForm {
        public FormationsForm() {
        this(Resources.getGlobalResources());
        
    }

    @Override
    protected boolean isCurrentFormations() {
        return true;
    }
    
    public FormationsForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here    
         Button btnFormationList = new Button("Formation List");
         Button btnCourList = new Button("Cour List");
         Button btnAddFormation = new Button("Add Formation");
         Button btnAddCour = new Button("Add Cour");
         
          btnAddFormation.addActionListener((evt) -> {
            new AddFormationForm(this).show();
        });
           
           btnAddCour.addActionListener((evt) -> {
            new AddCourForm(this).show();
        });
            btnFormationList.addActionListener((evt) -> {
            new FormationsListForm().show();
        });
           
         btnCourList.addActionListener((evt) -> {
            new ListCourForm().show();
        }); 
         
                 this.addAll(new Label("Choose an option :"),btnAddFormation,btnAddCour,btnFormationList,btnCourList);

    }
}
