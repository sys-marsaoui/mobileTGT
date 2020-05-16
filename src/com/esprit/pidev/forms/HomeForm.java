/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author aissa
 */
public class HomeForm extends Form {

    public HomeForm() {
        super("Home", BoxLayout.y());
        
        Button btnAddPublication = new Button("Ajout Publication");
        Button btnPublicationsList = new Button("Mes Publications");
        
        btnAddPublication.addActionListener((evt) -> {
            new AddPublicationForm(this).show();
        });
        btnPublicationsList.addActionListener((evt) -> {
            //new TasksListForm(this).show();
        });
        
        this.addAll(new Label("Veuillez choisir une action:"), btnAddPublication, btnPublicationsList);
    }
    
}
