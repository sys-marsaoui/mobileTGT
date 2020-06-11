/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.CategorieProduit;
import com.esprit.pidev.services.CategorieProduitService;

/**
 *
 * @author aissa
 */
public class CategorieProduitListForm extends Form {

    
    
    CategorieProduitService categorieProduitService = new CategorieProduitService();
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
 
    public CategorieProduitListForm(Form previous) {
        super("Categorie list", BoxLayout.y());

        for (CategorieProduit c : categorieProduitService.getAllCaegorie()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnomCategorie = new Label("Nom categorie: " + c.getNomcategorie());

            Button bSupprimer = new Button("Supprimer");
            Button bModifier = new Button("modifier");
            
            
            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                categorieProduitService.supCategorie(c);
                Dialog.show("SUCCESS", "Categorie Supprimé", "OK", null);
                previous.showBack();
                   
                }
            });
            
            
            bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  
                    new ModifierCategorieProduitForm(c).show();
                }
            });
            

            
       
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bModifier, bSupprimer);

            cDetaille.addAll(esLabel,lnomCategorie,cb);

           ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(300, 300));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);


        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
        
        
}
}