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
        
        Button btnAddTask = new Button("Add Task");
        Button btnCastingList = new Button("Casting List");
        Button btnOrganisationsList = new Button("Organisations List");
        Button btnEventsList = new Button("Events List");
        Button btnFormationList = new Button("Formation List");
        Button btnCourList = new Button("Cour List");

        Button btnAddOrganisations = new Button("Add Organisation");
        Button btnAddCasting = new Button("Add Casting");
        Button btnAddEvents = new Button("Add Events");
        Button btnAddFormation = new Button("Add Formation");
         Button btnAddCour = new Button("Add Cour");

        btnCastingList.addActionListener((evt) -> {
            new CastingListForm(this).show();
        });
        
         btnOrganisationsList.addActionListener((evt) -> {
            new OrganisationsListForm(this).show();
        });
         
          btnEventsList.addActionListener((evt) -> {
            new EventsListForm(this).show();
        });
          
           btnAddOrganisations.addActionListener((evt) -> {
            new AddOrganisationsForm(this).show();
        });
           
           btnAddFormation.addActionListener((evt) -> {
            new AddFormationForm(this).show();
        });
           
           btnAddCour.addActionListener((evt) -> {
            new AddCourForm(this).show();
        });
           
           
            btnAddCasting.addActionListener((evt) -> {
            new AddCastingForm(this).show();
        });
              btnAddEvents.addActionListener((evt) -> {
            new AddEventsForm(this).show();
        });
              
         btnFormationList.addActionListener((evt) -> {
            new FormationsListForm(this).show();
        });
           
        
        this.addAll(new Label("Choose an option :"), btnAddTask, btnCastingList,btnFormationList,btnOrganisationsList,btnEventsList,btnAddOrganisations,btnAddCasting,btnAddEvents,btnAddFormation,btnAddCour,btnCourList);
        

    }
    
}
