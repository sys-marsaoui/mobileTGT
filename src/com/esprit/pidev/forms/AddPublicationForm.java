/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.openGallery;
import com.codename1.ui.CN1Constants;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.CatégoriePublication;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.PublicationService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class AddPublicationForm extends Form {

    ArrayList<CatégoriePublication> catégories;
    Resources r = Resources.getGlobalResources();
    PublicationService ps = new PublicationService();

    public AddPublicationForm(Form previous) {
        super("Publication Video", BoxLayout.y());

        //Add Commands to toolbar
        getToolbar().addCommandToRightBar("", r.getImage("logout.png"), e -> {/*Login Form*/
        });
        getToolbar().addCommandToLeftBar("", r.getImage("left-arrow.png"), e -> {
            previous.showBack();
        });

        //Exprimez-vous
        Label lbContenu = new Label("Exprimez-vous");
        TextArea taContenu = new TextArea(10, 30);

        //Catégories
        ComboBox cbcatégories = new ComboBox(catégories);
        Label lbCatégorie = new Label("Catégories", r.getImage("controls.png").scaled(120, 120));
        for (CatégoriePublication cp : ps.getAllCatégories()) {
            cbcatégories.addItem(cp.getNomCat());
        }

        //Vidéo
        Label lbVideo = new Label("Video", r.getImage("photo.png").scaled(120, 120));
        Container vid = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnVideo = new Button("");
        btnVideo.setUIID("VideoPublication");
        Image defaultAvatar = r.getImage("circle.png");
        btnVideo.setIcon(defaultAvatar);
        Label vidroute = new Label("Aucun fichier choisi");
        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Dialog.show("Caméra ou Galerie", "Souhaitez-vous utiliser l'appareil photo ou la galerie pour la vidéo?", "Caméra", "Galerie")) {
                    String video = Capture.captureVideo();
                    if (video != null) {
                        OutputStream out = null;
                        try {
                            Random rand = new Random(); //instance of random class
                                int upperbound = 30;
                                //generate random values from 0-24
                                int int_random = rand.nextInt(upperbound);
                                FileSystemStorage fs = FileSystemStorage.getInstance();
                                InputStream stream = fs.openInputStream(video);
                                out = Storage.getInstance().createOutputStream("publication"+int_random+".mp4");
                                Util.copy(stream, out);
                                Util.cleanup(stream);
                                Util.cleanup(out);
                                String videoPath = fs.getAppHomePath() + "publication"+int_random+".mp4";
                                vidroute.setText(videoPath);
                        } catch (IOException ex) {
                            ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex);
                        }
                    }
                } else {
                    openGallery(ee -> {
                        if (ee.getSource() != null) {
                            OutputStream out = null;
                            try {
                                String video = ee.getSource().toString();
                                Random rand = new Random(); //instance of random class
                                int upperbound = 30;
                                //generate random values from 0-24
                                int int_random = rand.nextInt(upperbound);
                                FileSystemStorage fs = FileSystemStorage.getInstance();
                                InputStream stream = fs.openInputStream(video);
                                out = Storage.getInstance().createOutputStream("publication"+int_random+".mp4");
                                Util.copy(stream, out);
                                Util.cleanup(stream);
                                Util.cleanup(out);
                                String videoPath = fs.getAppHomePath() + "publication"+int_random+".mp4";
                                vidroute.setText(videoPath);
                            } catch (IOException ex) {
                                ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex);
                            } finally {
                                try {
                                    out.close();
                                } catch (IOException ex) {
                                    ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex);
                                }
                            }
                        }
                    }, CN1Constants.GALLERY_VIDEO);
                }
            }
        });
        vid.addAll(btnVideo, vidroute);

        //Bouton Publier
        Button btnPublier = new Button("PUBLIER");
        btnPublier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Localisation
                Location position;
                String localisation = null;
                if (LocationManager.getLocationManager().isGPSEnabled()) {
                    position = LocationManager.getLocationManager().getCurrentLocationSync();
                    String latitude = Double.toString(position.getLatitude());
                    String longitude = Double.toString(position.getLongitude());
                    localisation = ps.getAddresse(latitude, longitude);
                    System.out.println(localisation);
                } else {
                    ToastBar.showErrorMessage("Votre localisation est introuvable, veuillez verifier que votre GPS est activé");
                }
                ps.ajouter(new Publication(taContenu.getText(), vidroute.getText(), localisation, new CatégoriePublication(cbcatégories.getSelectedIndex(), (String) cbcatégories.getSelectedItem())));
                taContenu.setText("");
                vidroute.setText("Aucun fichier choisi");
                cbcatégories.setSelectedItem(null);
                ToastBar.showInfoMessage("Votre Publication a été ajouté avec succès ");
            }
        });
        this.addAll(lbContenu, taContenu, lbCatégorie, cbcatégories, lbVideo, vid, btnPublier);
    }
}
