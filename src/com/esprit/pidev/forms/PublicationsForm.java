/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author aissa
 */
public class PublicationsForm extends BaseForm {

    public PublicationsForm() {
        this(Resources.getGlobalResources());

    }

    @Override
    protected boolean isCurrentPublications() {
        return true;
    }

    public PublicationsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);

        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {
            ToastBar.showMessage("Déconnexion en cours", FontImage.MATERIAL_EXIT_TO_APP);
        });
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //Type your code here    
        Button btnAddPublication = new Button("Ajout Publication");
        Button btnPublicationsList = new Button("Mes Publications");
        btnAddPublication.addActionListener((evt) -> {
            new AddPublicationForm(this).show();
        });
        btnPublicationsList.addActionListener((evt) -> {
            new PublicationsListForm(this).show();
        });

        this.addAll(btnAddPublication, btnPublicationsList);
    }
}
