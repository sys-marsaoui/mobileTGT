/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.CategorieProduit;
import com.esprit.pidev.models.Produit;
import com.esprit.pidev.services.CategorieProduitService;
import com.esprit.pidev.services.ProduitService;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class AddProduitForm extends Form {

    ArrayList<CategorieProduit> categorieProduits;

    CategorieProduitService categorieProduitService = new CategorieProduitService();

    public AddProduitForm(Form previous) {

        super("Add a new produit", BoxLayout.y());

        TextField nom = new TextField("", "nom");
        TextField description = new TextField("", "description", 8, TextArea.ANY);
        TextField prix = new TextField("", "prix", 20, TextArea.NUMERIC);
        TextField quantite = new TextField("", "quantite", 20, TextArea.NUMERIC);
        TextField image = new TextField("", "image", 8, TextArea.ANY);
        ComboBox idcategorieProduit = new ComboBox("", categorieProduits);

        for (CategorieProduit c : categorieProduitService.getAllCaegorie()) {

            idcategorieProduit.addItem(c.getId());
            
        }

        Button btn = new Button("ajouter");

        btn.addActionListener((evt) -> {
            if ((nom.getText().length() == 0) || (description.getText().length() == 0) || (prix.getText().length() == 0)
                    || (quantite.getText().length() == 0) || (image.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                   CategorieProduit  categorieProduit = new  CategorieProduit((int)idcategorieProduit.getSelectedItem()) ;
                    Produit produit = new Produit(nom.getText(),categorieProduit, description.getText(),
                            Integer.parseInt(prix.getText()), Integer.parseInt(quantite.getText()),
                            image.getText());
                    
                    if (new ProduitService().addProduit(produit)) {
                        Dialog.show("SUCCESS", "produit ajouté", "OK", null);
                        new ProduitListForm(this).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "candidat must be a number", "OK", null);
                }

            }
        });

        this.addAll(nom, description, prix, quantite, image, idcategorieProduit, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }

}
