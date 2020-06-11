/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ScaleImageButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.PublicationService;
import java.util.ArrayList;

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
        getToolbar().addCommandToRightBar("", r.getImage("logout.png"), e -> {
            ToastBar.showMessage("Déconnexion en cours", FontImage.MATERIAL_EXIT_TO_APP);
        });
        getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), e -> {
            new PublicationsForm().showBack();
        });

        Image nativeThemeImage = r.getImage("video.jpg");
        EncodedImage blurredPlaceholder;
        if (Display.getInstance().isGaussianBlurSupported()) {
            blurredPlaceholder = EncodedImage.createFromImage(Display.getInstance().gaussianBlurImage(nativeThemeImage, 20), true);
        } else {
            blurredPlaceholder = EncodedImage.createFromImage(nativeThemeImage.modifyAlpha((byte) 80), true);
        }
        //Parcourir les publications
        for (Publication publication : ps.getAllPublications()) {
            String contenu = publication.getContenu();
            ScaleImageButton theme = new ScaleImageButton();
            theme.setIcon(blurredPlaceholder);

            theme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new DetailsPublication(publication).show();
                }
            });

            Container cnt = LayeredLayout.encloseIn(theme,
                    BorderLayout.south(new Label(contenu, "TinyBoldLabel")));
            cnt.setLeadComponent(theme);
            this.add(cnt);
//            this.getContentPane().addPullToRefresh(() -> {
//                new PublicationsListForm(previous).showBack();
//            });
        }
        this.setScrollableY(true);
    }
}
