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
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.openGallery;
import com.codename1.ui.CN1Constants;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.CategoriePublication;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.PublicationService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class ModifierContainer extends Container {
    
    ArrayList<CategoriePublication> catégories;
    Resources r = Resources.getGlobalResources();
    PublicationService ps = new PublicationService();
    HashMap<String, Integer> catégoriesMap1 = new HashMap();
    HashMap<Integer, String> catégoriesMap = new HashMap();
    private final String[] locations = {"Ariana", "Ben Arous", "Tunis", "Manouba", "Sfax", "Béja", "Bizerte", "Gabes", "Gafsa", "Jendouba",
        "Kairaouan", "Kasserine", "Kébili", "Le Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sidi Bouzid",
        "Siliana", "Sousse", "Tataouine", "Tozeur", "Zaghouan"};

    public ModifierContainer(Publication publication) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
        //Exprimez-vous
        Label lbContenu = new Label("Exprimez-vous");
        TextArea taContenu = new TextArea(7, 30);
        taContenu.setText(publication.getContenu());
        
        //Localisation
        Label lbLocalisation = new Label("Je suis là", r.getImage("placeholder.png").scaled(120, 120));
        AutoCompleteTextField localisation = new AutoCompleteTextField(locations);
        localisation.setText(publication.getLocalisation());
        Button btnModifier = new Button("MODIFIER");
        btnModifier.setPreferredSize(new Dimension(90, 130));
        btnModifier.addActionListener((evt) -> {
            if (localisation.getText().length() == 0) {
                Location position;
                String local = null;
                if (LocationManager.getLocationManager().isGPSEnabled()) {
                    position = LocationManager.getLocationManager().getCurrentLocationSync();
                    String latitude = Double.toString(position.getLatitude());
                    String longitude = Double.toString(position.getLongitude());
                    local = ps.getAddresse(latitude, longitude);
                    System.out.println(local);
                } else {
                    ToastBar.showErrorMessage("Votre localisation est introuvable, veuillez verifier que votre GPS est activé");
                }
                localisation.setText(local);
                ToastBar.showMessage("Votre position " + local + " a été pris comme localisation !",FontImage.MATERIAL_ROOM);
            } else if (Arrays.asList(locations).contains(localisation.getText())) {
                System.out.println(localisation.getText());
                ToastBar.showMessage("Votre nouvelle position est "+localisation.getText(),FontImage.MATERIAL_ROOM);
            } else {
                System.out.println(localisation.getText());
                ToastBar.showErrorMessage("Votre Choix est Invalide");
                localisation.setText(publication.getLocalisation());
            }
        });
        

        //Catégories
        ComboBox cbcatégories = new ComboBox(catégories);
        Label lbCatégorie = new Label("Catégories", r.getImage("controls.png").scaled(120, 120));
        for (CategoriePublication cp : ps.getAllCatégories()) {
            cbcatégories.addItem(cp.getNomCat());
            catégoriesMap1.put(cp.getNomCat(), cp.getId_cat());
            catégoriesMap.put(cp.getId_cat(), cp.getNomCat());
        }
        cbcatégories.setSelectedItem(catégoriesMap.get(publication.getId_cat()));

        //Vidéo
        Label lbVideo = new Label("Video", r.getImage("photo.png").scaled(120, 120));
        Container vid = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnVideo = new Button("");
        btnVideo.setUIID("VideoPublication");
        Image defaultAvatar = r.getImage("circle.png");
        btnVideo.setIcon(defaultAvatar);
        Label vidroute = new Label(publication.getVideo());

        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Dialog.show("Caméra ou Galerie", "Souhaitez-vous utiliser l'appareil photo ou la galerie pour la vidéo?", "Caméra", "Galerie")) {
                    String video = Capture.captureVideo();
                    int extpos = video.lastIndexOf(".");
                    String extension = video.substring(extpos);
                    System.out.println(extension);
                    if (video != null) {
                        OutputStream out = null;
                        try {
                            Random rand = new Random(); //instance of random class
                            int upperbound = 30;
                            //generate random values from 0-24
                            int int_random = rand.nextInt(upperbound);
                            FileSystemStorage fs = FileSystemStorage.getInstance();
                            InputStream stream = fs.openInputStream(video);
                            out = Storage.getInstance().createOutputStream("file://C:/wamp64/www/TGTFinale/web/uploads/assets/publication" + int_random + extension);
                            Util.copy(stream, out);
                            Util.cleanup(stream);
                            Util.cleanup(out);
                            String videoPath = "file://C:/wamp64/www/TGTFinale/web/uploads/assets/publication" + int_random + extension;
                            vidroute.setText(videoPath);
                        } catch (IOException ex) {
                            ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
                        }
                    }
                } else {
                    openGallery(ee -> {
                        if (ee.getSource() != null) {
                            OutputStream out = null;
                            try {
                                String video = ee.getSource().toString();
                                System.out.println(video);
                                int extpos = video.lastIndexOf(".");
                                String extension = video.substring(extpos);
                                Random rand = new Random(); //instance of random class
                                int upperbound = 30;
                                //generate random values from 0-24
                                int int_random = rand.nextInt(upperbound);
                                FileSystemStorage fs = FileSystemStorage.getInstance();
                                InputStream stream = fs.openInputStream(video);
                                out = fs.openOutputStream("file://C:/wamp64/www/TGTFinale/web/uploads/assets/publication" + int_random + extension);
                                Util.copy(stream, out);
                                Util.cleanup(stream);
                                Util.cleanup(out);
                                String videoPath = "file://C:/wamp64/www/TGTFinale/web/uploads/assets/publication" + int_random + extension;
                                vidroute.setText(videoPath);
                            } catch (IOException ex) {
                                ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
                            } finally {
                                try {
                                    out.close();
                                } catch (IOException ex) {
                                    ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
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
        btnPublier.addActionListener((evt) -> {
            if (cbcatégories.getSelectedItem() == "null"){
                ToastBar.showErrorMessage("Veuillez choisir une catégorie valide");
            } else {
            int indexCat = catégoriesMap1.get(cbcatégories.getSelectedItem().toString());
            Publication nouvellePub = new Publication(publication.getId_pub(), taContenu.getText(), vidroute.getText(), localisation.getText(),publication.getDatePub()
                    , publication.getRatingPub(), indexCat);
            ps.modifier(nouvellePub);
            ToastBar.showMessage("Publication Modifiée avec Succés", FontImage.MATERIAL_DONE);
            new DetailsPublication(nouvellePub).showBack();
            }
        });
        
        this.addAll(lbContenu, taContenu, lbLocalisation, localisation, btnModifier, lbCatégorie, cbcatégories, lbVideo, vid, btnPublier);
    }
    
}
