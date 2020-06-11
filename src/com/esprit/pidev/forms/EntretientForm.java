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
import com.esprit.pidev.models.Casting;

/**
 *
 * @author islem
 */
public class EntretientForm extends BaseForm{
     public EntretientForm() {
        this(Resources.getGlobalResources());
        
    }

   Casting ca = new Casting();
     @Override
    protected boolean isCurrentEntretient() {
        return true;
    }
     public EntretientForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here   
          Button btnCandidatList = new Button("candidat list");
        Button btnEntretientList = new Button("entretient list");
         Button btnContrattList = new Button("contrat list");
        
         
          btnCandidatList.addActionListener((evt) -> {
            new CandidatListForm(this).show();
        });
           btnEntretientList.addActionListener((evt) -> {
            new EntretientListForm(this).show();
        });
            btnContrattList.addActionListener((evt) -> {
            new ContratListForm(this).show();
        });
                   this.addAll(new Label("Choose an option :"),btnCandidatList,btnEntretientList,btnContrattList);
                    this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            this.showBack();
        });

    
}}
