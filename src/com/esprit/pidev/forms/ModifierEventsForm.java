/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.esprit.pidev.models.Events;
import com.esprit.pidev.services.EventsService;

/**
 *
 * @author islem
 */
public class ModifierEventsForm extends Form {
        EventsService eventsService = new EventsService();
        Resources thRessources = UIManager.initFirstTheme("/theme");
        public ModifierEventsForm(Events e){
        System.out.println(e);
        Label esLabel = new Label(" ");
        
        Label lDateEvents = new Label("Date Evenement: ");
        Picker dateTimePicker =  new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        
        Button btn = new Button("modifier la date Evenement");
        btn.addActionListener((evt)->{
         
               try{
                   String date=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(dateTimePicker.getDate());
                   e.setDateEvents(date);
                   System.out.println(date);


               if(new EventsService().modifEvents(e)){
                                           Dialog.show("SUCCESS", "events modifier avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException ex){
                                   Dialog.show("ERROR", "exception", "OK", null);
               }});            
       this.addAll(dateTimePicker,btn);
       this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        this.showBack();
        });
        
}}
