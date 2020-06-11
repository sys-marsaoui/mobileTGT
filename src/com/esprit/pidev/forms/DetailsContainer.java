/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.utils.RatingWidget;
import java.io.IOException;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class DetailsContainer extends Container {

    public DetailsContainer(Publication publication) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        try {
            Media video = MediaManager.createMedia(publication.getVideo(), true);
            MediaPlayer player = new MediaPlayer(video);
            player.showControls();
            player.run();
            this.addComponent(player);
        } catch (IOException ex) {
            ToastBar.showErrorMessage("Erreur lors du chargement du vidéo");
        }
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        SpanLabel contenu = new SpanLabel(publication.getContenu(), "HugeDarkLabel");
        String datePub = publication.getDatePub().toString();
        int index = datePub.lastIndexOf(" ");
        Label date = new Label(datePub.substring(0, 10) + datePub.substring(index), FontImage.createMaterial(FontImage.MATERIAL_TODAY, s));
        Label time = new Label(datePub.substring(11, 16), FontImage.createMaterial(FontImage.MATERIAL_SCHEDULE, s));
        c1.addAll(date,time);
        Label localisation = new Label(publication.getLocalisation(), FontImage.createMaterial(FontImage.MATERIAL_ROOM, s));
        Label ratingLabel = new Label("Rating : "+publication.getRatingPub(), FontImage.createMaterial(FontImage.MATERIAL_STAR, s));
        c.addAll(contenu,c1,localisation,ratingLabel);
        RatingWidget rw = new RatingWidget();
        Slider rating = rw.createStarRankSlider(false);
        rating.setProgress((int)Math.round(publication.getRatingPub()));
        this.add(c);
        this.add(FlowLayout.encloseCenter(rating));
    }
    
}
