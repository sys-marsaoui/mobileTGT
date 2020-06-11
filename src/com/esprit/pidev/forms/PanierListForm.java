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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Panier;
import com.esprit.pidev.services.CommandeService;
import com.esprit.pidev.services.PanierService;

/**
 *
 * @author aissa
 */
public class PanierListForm extends Form {

    
    
    PanierService panierService = new PanierService();
    CommandeService commandeService = new CommandeService();
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
 
    public PanierListForm(Form previous) {
        super("Panier", BoxLayout.y());
        
        
        Button bCommande = new Button("Commander");
        
        

        for (Panier c : panierService.getAllPanier()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnomProduit = new Label("Produit: " + c.getProduit_id().getNom());
            Label lDescription = new Label("Description: " + c.getProduit_id().getDescription());
            Label lPrix = new Label("Prix: " + c.getProduit_id().getPrix());
            Label lQuantite = new Label("Quantite: ");
            TextField quantite = new TextField("1", "quantite", 20, TextArea.NUMERIC);

            Button bSupprimer = new Button("Supprimer");
            Button bDetaille = new Button("Detaille");
            
            
            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                panierService.supPanier(c);
                Dialog.show("SUCCESS", "voulez vous supprimer ce produit ?", "Confirmer", "Annuler");
                previous.showBack();
                   
                }
            });
            
            
       
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bSupprimer);

            cDetaille.addAll(esLabel,lnomProduit,lDescription,lPrix,lQuantite,quantite,cb);

           ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(300, 300));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);


        
    }
        
        bCommande.addActionListener((evt) -> {
              commandeService.addCommande();
              Dialog.show("Alert", "Commande effectué avec succée", "OK", null);
              new ProduitListForm(this).show();
              
        });
        
        Container cGlobal = new Container(BoxLayout.x());
        
        this.addAll(bCommande);
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
        
}
}