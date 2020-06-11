/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.CategorieProduit;
import com.esprit.pidev.services.CategorieProduitService;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class AddCategorieProduitForm extends Form {



    CategorieProduitService categorieProduitService = new CategorieProduitService();

    public AddCategorieProduitForm(Form previous) {

        super("Add a new categorie", BoxLayout.y());

        TextField nomcategorie = new TextField("", "nomcategorie");


        Button btn = new Button("ajouter categorie");

        btn.addActionListener((evt) -> {
            if ((nomcategorie.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    CategorieProduit categorieProduit = new CategorieProduit(nomcategorie.getText());
                   

                    if (new CategorieProduitService().addCandidat(categorieProduit)) {
                        new CategorieProduitListForm(this).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "candidat must be a number", "OK", null);
                }
                

            }
        });

        this.addAll(nomcategorie, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }

}
