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
import com.esprit.pidev.models.Wishlist;
import com.esprit.pidev.services.WishlistService;

/**
 *
 * @author aissa
 */
public class WishlistListForm extends Form {

    
    
    WishlistService wishlistService = new WishlistService();
     Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
 
    public WishlistListForm(Form previous) {
        super("Wishlist", BoxLayout.y());

        for (Wishlist c : wishlistService.getAllWishlist()) {

            Container cDetaille = new Container(BoxLayout.y());
            Container cGlobal = new Container(BoxLayout.x());

            Label esLabel = new Label(" ");
            Label lnomProduit = new Label("Nom Produit: " + c.getProduit_id().getNom());
            Label lDescription = new Label("Description: " + c.getProduit_id().getDescription());

            Button bSupprimer = new Button("Supprimer");
            Button bDetaille = new Button("Detaille");
            
            
            bSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                wishlistService.supWishlist(c);
                Dialog.show("SUCCESS", "voulez vous supprimer ce produit ?", "Confirmer", "Annuler");
                previous.showBack();
                   
                }
            });
            
            
            bDetaille.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  
//                    new DetailCondidatForm(c).show();
                }
            });
            
       
            Container cb = new Container(BoxLayout.x());

            cb.addAll(bDetaille, bSupprimer);

            cDetaille.addAll(esLabel,lnomProduit,lDescription,cb);

           ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(300, 300));

            cGlobal.addAll(image, cDetaille);

            this.add(cGlobal);


        
    }
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
        
}
}