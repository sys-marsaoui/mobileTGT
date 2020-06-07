/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ToastBar;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.PublicationService;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class DetailsPublication extends Form {
    
    Resources r = Resources.getGlobalResources();
    PublicationService ps = new PublicationService();

    public DetailsPublication(Publication publication) {
        super("Détails", new BorderLayout());
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        Style s1 = UIManager.getInstance().getComponentStyle("Button");
        //Add Commands to toolbar
        this.getToolbar().addCommandToRightBar("", FontImage.createMaterial(FontImage.MATERIAL_DELETE, s1), (e) -> {
            if (Dialog.show("Suppression Publication", "Voulez-vous vraiment supprimer cette publication ?", "Supprimer", "Annuler")) {
                if (ps.supprimer(publication.getId_pub())) {
                    ToastBar.showMessage("Publication Supprimé avec succés", FontImage.MATERIAL_DELETE);
                    new PublicationsListForm(this).showBack();
                }
            }
        });
        this.getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), (e) -> {
            new PublicationsListForm(this).showBack();
        });
        Tabs t = new Tabs();
        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LABEL, s);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_EDIT, s);
        FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_COMMENT, s);
        t.addTab("Afficher", icon1, new DetailsContainer(publication));
        t.addTab("Modifer", icon2, new ModifierContainer(publication));
        t.addTab("Commentaires", icon3,new CommentairesContainer(publication));
        t.getTabsContainer().setScrollableX(false);
        this.add(BorderLayout.CENTER, t);
    }
}
