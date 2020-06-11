/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.PublicationService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class PublicationsListForm extends Form {

    Resources r = Resources.getGlobalResources();
    ArrayList<Publication> publications;
    PublicationService ps = new PublicationService();

    public PublicationsListForm(Form previous) {
        super("Mes Publications", BoxLayout.y());

        //Add Commands to toolbar
        getToolbar().addCommandToRightBar("", r.getImage("logout.png"), e -> {/*Login Form*/
        });
        getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), e -> {
            previous.showBack();
        });

        //Parcourir les publications
        for (Publication publication : ps.getAllPublications()) {
            Container pDetaille = new Container(BoxLayout.y());
            Container pGlobal = new Container(BoxLayout.x());

            Label contenuLabel = new Label(publication.getContenu());
            Label datePubLabel = new Label(publication.getDatePub().toString());

            //Bouton Supprimer
            Button btnSupprimer = new Button("");
            btnSupprimer.setUIID("Supprimer");
            Image deletePub = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "" , 4);
            btnSupprimer.setIcon(deletePub);
            //Bouton Modifier
            Button btnModifier = new Button("");
            btnSupprimer.setUIID("Modifier");
            Image editPub = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "", 4);
            btnModifier.setIcon(editPub);
            //Bouton Commentaires
            Button btnComments = new Button("");
            btnComments.setUIID("Commentaires");
            Image commentsPub = FontImage.createMaterial(FontImage.MATERIAL_COMMENT, "", 4);
            btnComments.setIcon(commentsPub);
            //Bouton Ajout Commentaires
            Button btnAddComments = new Button("");
            btnAddComments.setUIID("Ajouter Commentaire");
            Image addCommentsPub = FontImage.createMaterial(FontImage.MATERIAL_ADD_COMMENT, "", 4);
            btnAddComments.setIcon(addCommentsPub);

            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Dialog.show("Suppression Publication", "Voulez-vous vraiment supprimer cette publication ?", "Supprimer", "Annuler")) {
                        ps.supprimer(publication.getId_pub());
                    }
                }
            });
            
            btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new EditPublicationForm(previous).show();
                }
            });

            Container cb = new Container(BoxLayout.x());

            cb.addAll(btnModifier, btnSupprimer, btnComments , btnAddComments);

            pDetaille.addAll(contenuLabel, datePubLabel, cb);

            pGlobal.addAll(pDetaille);
            
            this.getContentPane().addPullToRefresh(() -> {
                System.out.println("Pulled at " + L10NManager.getInstance().formatDateTimeShort(new Date()));
                new PublicationsListForm(previous).show();
            });

            this.add(pGlobal);

        }
    }
}
