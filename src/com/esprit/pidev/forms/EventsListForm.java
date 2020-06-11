/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Events;
import com.esprit.pidev.services.EventsService;

/**
 *
 * @author islem
 */
public class EventsListForm extends Form  {
    

    EventsService eventsService = new EventsService();
    Resources thRessources = UIManager.initFirstTheme("/theme_1");

    public EventsListForm(Form previous) {
//            Style s = UIManager.getInstance().getComponentStyle("Button");
//            FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
//            EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 4, p.getHeight() * 4), false);
        for (Events e : eventsService.getAllEvents()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lTitreEvents = new Label("Titre: " + e.getTitreEvents());
            Label lAdresseEvents = new Label("Adresse: " + e.getAdresseEvents());
            
            Button Details = new Button("Details");
            Container cb = new Container(BoxLayout.x());
            cb.addAll(Details);
            cDetaille.addAll(esLabel,lTitreEvents,lAdresseEvents,cb);
           

          // System.out.println(e.getImageEvents());
          // Image img = URLImage.createToStorage(placeholder, "list", "http://127.0.0.1:8000/images/"+e.getImageEvents(), URLImage.RESIZE_SCALE);
           //ImageViewer image = new ImageViewer(img);

             ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);
            Details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                new DetailsEventsForm(e).show();
                }
            });
        }
            
             this.setTitle("liste d'Evenements");
           this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });

    }}

  
    

