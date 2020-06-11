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

/**
 *
 * @author ajmim_9xsk8tf
 */
public class ArticlesForm extends BaseForm{
        public ArticlesForm() {
        this(Resources.getGlobalResources());
        
    }

    @Override
    protected boolean isCurrentArticles() {
        return true;
    }
    
    public ArticlesForm(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here    
        Button btnAfficheCategorie = new Button("Categories");
        Button btnAfficheProduit = new Button("Produits");
        Button btnAddProduit = new Button("Ajouter Produit");
        Button btnAddWishlist = new Button("Wishlist");
        Button btnAddPanier = new Button("Panier");
        
          btnAfficheCategorie.addActionListener((evt) -> {
              new CategorieProduitListForm(this).show();
        });
          
          
          btnAfficheProduit.addActionListener((evt) -> {
              new ProduitListForm(this).show();
        });
          
          btnAddProduit.addActionListener((evt) -> {
              new AddProduitForm(this).show();
        });
          btnAddWishlist.addActionListener((evt) -> {
              new WishlistListForm(this).show();
        });
          
          btnAddPanier.addActionListener((evt) -> {
              new PanierListForm(this).show();
        });
        

        this.addAll(new Label("Choose an option :"),btnAfficheCategorie, btnAfficheProduit, btnAddProduit,btnAddWishlist,btnAddPanier );
    }
    
}
