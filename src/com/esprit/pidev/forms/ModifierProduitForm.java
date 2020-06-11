/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Produit;
import com.esprit.pidev.services.ProduitService;
import java.util.ArrayList;


/**
 *
 * @author goldzeo
 */
public class ModifierProduitForm  extends Form{
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
    public ModifierProduitForm(Produit c){
        
         
           
           
           
      
           
         Label lnomProduit = new Label("Nom Produit: " );
        TextField firstName = new TextField(c.getNom(), "nom", 20, TextArea.ANY);
        
        Label lDescription = new Label("Description: " );
        TextField description = new TextField(c.getDescription(), "Description", 20, TextArea.ANY);
        
         Label lPrix = new Label("Prix: " );
        TextField prix = new TextField(c.getPrix().toString(), "prix", 20, TextArea.NUMERIC);
        
         Label lQuantite= new Label("Quantite: ");
        TextField quantite = new TextField(c.getQuantite().toString(), "quantite", 20, TextArea.NUMERIC);
         
         Label limage = new Label("image: " );
        TextField image = new TextField(c.getImage(), "Image", 20, TextArea.ANY);
//         ComboBox idcasting = new ComboBox(c.getCasting().getId(),castings);
//
//        for (Casting cs : castingService.getAllCastings()) {
//
//            idcasting.addItem(cs);
//            
//        }
            
        this.setLayout(new FlowLayout(CENTER, TOP));
        
        
       Button btn = new Button("modifier Produit");

        btn.addActionListener((evt) -> {

                try {
                    // Casting  casting = new  Casting((int)idcasting.getSelectedItem()) ;
                    Produit produit = new Produit(c.getId(),firstName.getText(), description.getText(), Integer.parseInt(prix.getText()),Integer.parseInt(quantite.getText()), image.getText());
                   

                    if (new ProduitService().modifProduit(produit)) {
                        Dialog.show("SUCCESS", "Produit modifier", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Produit must be a number", "OK", null);
                }

            new DetailProduitForm(c).show();
        });

        this.addAll(lnomProduit,firstName,lDescription,description,lPrix,prix,lQuantite,quantite,limage,image, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
             new DetailProduitForm(c).show();
        });
    }
    
}
