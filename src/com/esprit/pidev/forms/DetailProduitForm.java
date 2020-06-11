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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Panier;
import com.esprit.pidev.models.Produit;
import com.esprit.pidev.models.Wishlist;
import com.esprit.pidev.services.PanierService;
import com.esprit.pidev.services.WishlistService;
import java.io.InputStream;


/**
 *
 * @author goldzeo
 */
public class DetailProduitForm  extends Form{
    
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
         int i=0;
         int k=0;
    public DetailProduitForm(Produit c){
        
        
         Label esLabel = new Label(" ");
            Label lnom = new Label("Nom Produit: " + c.getNom());
            Label lcategorieProduit= new Label("categorie Produit: " + c.getCategorie_id().getNomcategorie());
            Label lDescription = new Label("Description: " + c.getDescription());
            Label lPrix = new Label("Prix: " + c.getPrix());
            Label lQuantite = new Label("Quantite: " + c.getQuantite());
            Button bModifier = new Button("modifier");
            Button bProduits = new Button("produits");
            Button bAddWishlist = new Button("ajouter wishlist");
            Button bAddPanier = new Button("ajouter au panier");
            Button bPanier = new Button("Panier");
            Button bWishlist = new Button("Wishlist");
            
            
            //aj prod wishlist
            TextField id = new TextField("1", "produit_id", 20, TextArea.ANY);
            
              bModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  
                    new ModifierProduitForm(c).show();
                }
            });
              
              bAddWishlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {


                try {
                   Produit  produit = new  Produit(c.getId()) ;
                    Wishlist wishlist = new Wishlist(produit);
                   

                    
                    if (new WishlistService().addWishlist(wishlist)) {
                        
                        if(i==0){
                           
                        Dialog.show("SUCCESS", "produit ajouté", "OK", null);
                         i=i+1;
                        } else {
                          
                        Dialog.show("opps!!", "produit existe", "OK", null);
                        i=i-1;
                        }
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Wishlist must be a number", "OK", null);
                }

                }
            });
              
              bAddPanier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {


                try {
                   Produit  produit = new  Produit(c.getId()) ;
                    Panier panier = new Panier(produit);
                   

                    
                    if (new PanierService().addPanier(panier)) {
                        
                        if(k==0){
                           
                        Dialog.show("SUCCESS", "produit ajouté", "OK", null);
                         k=k+1;
                        } else {
                            
                        Dialog.show("opps!!", "produit existe", "OK", null);
                        k=k-1;
                        }
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Wishlist must be a number", "OK", null);
                }

                }
            });
              
              bProduits.addActionListener((evt) -> {
              new ProduitListForm(this).show();
        });
              bPanier.addActionListener((evt) -> {
              new PanierListForm(this).show();
        });
              
              bWishlist.addActionListener((evt) -> {
              new WishlistListForm(this).show();
        });
            
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
        Container cDetaille =new Container(BoxLayout.y());
         ImageViewer image = new ImageViewer(thRessources.getImage("images.png").scaled(600, 600));
       cDetaille.addAll(image,esLabel,lnom,lcategorieProduit,lDescription,lPrix,lQuantite,bModifier,bAddWishlist,bAddPanier,bProduits,bPanier,bWishlist);
        
        this.add(cDetaille);
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        this.showBack();
        });
        
        
    }
    
}
