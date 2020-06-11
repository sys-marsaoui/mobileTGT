/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Events;
import com.esprit.pidev.services.EventsService;


/**
 *
 * @author islem
 */
public class DetailsEventsForm extends Form{
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
     EventsService eventsService = new EventsService();
          
        public DetailsEventsForm(Events e){
            
            Label esLabel = new Label(" ");
            Label lTitreEvents = new Label("Titre: " + e.getTitreEvents());
            Label lDescriptionEvents = new Label("Description: " + e.getDescriptionEvents());
            Label lThemeEvents = new Label("Theme: " + e.getThemeEvents());
            Label lAdresseEvents = new Label("Adresse: " + e.getAdresseEvents());
            Label lDateEvents = new Label("Date evenements: " + e.getDateEvents());
            Label lprixEvents = new Label("Prix: " + e.getPrixEvents());
           // Label lImageEvents = new Label("Image : "+ e.getImageEvents());
            
            Style s = UIManager.getInstance().getComponentStyle("Button");
            FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
            EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 10, p.getHeight() * 10), false);
            Image img = URLImage.createToStorage(placeholder, "list", "http://127.0.0.1:8000/images/"+e.getImageEvents(), URLImage.RESIZE_SCALE);
            ImageViewer image = new ImageViewer(img);
            
         
            
            Button bSupprimer = new Button("Supprimer");
            Button bModifier = new Button("Modifier");

            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                     System.out.println("element selectioner " + e.getTitreEvents());
                     eventsService.supEvent(e);
                     //new EventsListForm().show();
                }
            });
            bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     System.out.println("element selectioner " + e.getTitreEvents());
                    
                     new ModifierEventsForm(e).show();
                     // eventsService.modifEvents(e);
                }
            });
                        
            this.setLayout(new FlowLayout(CENTER, TOP));
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bModifier, bSupprimer);
            Container cDetaille = new Container();
            cDetaille.addAll(lTitreEvents,lDescriptionEvents,lThemeEvents,lAdresseEvents,lDateEvents,lprixEvents,image,cb);
            this.add(cDetaille);
             this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new EvenementsForm().show();
        });
    
        }
        
         
    
}
