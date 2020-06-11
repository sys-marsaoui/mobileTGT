/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Formation;
import com.esprit.pidev.services.FormationService;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class FormationsListForm extends Form {

    FormationService FormationsService = new FormationService();
    private ArrayList<Formation> formations = new ArrayList<>();
    Container cnt, icp, ic;
    Resources thRessources = UIManager.initFirstTheme("/theme");

    public void afficher() {
        cnt = new Container();
        ComboBox x = new ComboBox();
        x.addItem("Duree Elevé");
        x.addItem("Duree Bas");
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        Container row = new Container(BoxLayout.x());
        Container row1 = new Container(BoxLayout.y());
        row1.add(searchField);
        row1.add(x);
        row.add(row1);
        cnt.add(row);
        ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    formations = FormationsService.getAllFormations();
                    amount = formations.size();

                }
                Component[] elments = new Component[amount];
                int i = 0;
                for (Formation f : formations) {
                    Container cDetaille = new Container(BoxLayout.y());
                    Container cGlobal = new Container(BoxLayout.x());
                    Label esLabel = new Label(" ");
                    Label Titre = new Label("Titre : " + f.getTitre_formation());
                    Label Description = new Label("Description : " + f.getDescription_formation());
                    Label Duree = new Label("Duree formation: " + f.getDuree_formation());
                    Label likes = new Label("Rating : " + f.getLikes());

                    Button bSupprimer = new Button("Supprimer");
                    Button bModifier = new Button("Modifier");
                    Button bShowCours = new Button("Mes Cours");

                    Container cb = new Container(BoxLayout.x());

                    cb.addAll(bModifier, bSupprimer,bShowCours);

                    cDetaille.addAll(esLabel, Titre, Description, Duree, likes, cb);

                    ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

                    cGlobal.addAll(image, cDetaille);
                    bSupprimer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            System.out.println("element selectioner " + f.getTitre_formation());
                            //service.delete(o);
                            FormationsService.supFormation(f);
                            new FormationsListForm().show();
                        }
                    });

                    bModifier.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("element selectioner " + f.getTitre_formation());
                            //service.delete(o);
                            FormationsService.modifFormation(f);
                            new ModifierFormationForm(f).show();

                        }
                    });
                    bShowCours.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           new ListCourForm(f).show();
                        }
                    });
                    elments[i] = cGlobal;
                    i++;
                }

                return elments;
            }
        };
        ic.setScrollableY(false);
        cnt.add(ic);
        x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                formations.clear();
                cnt.removeComponent(ic);
                if (cnt.contains(icp) == true) {
                    cnt.removeComponent(icp);
                }
                if (x.getSelectedItem().equals("Duree Elevé")) {
                    icp = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {
                                formations = FormationsService.TrierFormation("PE");
                                amount = formations.size();

                            }
                            if (index + amount > formations.size()) {

                                amount = formations.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            // System.out.println(amount);
                            Component[] elments = new Component[amount];

                            int i = 0;
                            for (Formation f : formations) {
                                Container cDetaille = new Container(BoxLayout.y());
                                Container cGlobal = new Container(BoxLayout.x());
                                Label esLabel = new Label(" ");
                                Label Titre = new Label("Titre : " + f.getTitre_formation());
                                Label Description = new Label("Description : " + f.getDescription_formation());
                                Label Duree = new Label("Duree formation: " + f.getDuree_formation());
                                Label likes = new Label("Rating : " + f.getLikes());

                                Button bSupprimer = new Button("Supprimer");
                                Button bModifier = new Button("Modifier");

                                Container cb = new Container(BoxLayout.x());

                                cb.addAll(bModifier, bSupprimer);

                                cDetaille.addAll(esLabel, Titre, Description, Duree, likes, cb);

                                ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

                                cGlobal.addAll(image, cDetaille);
                                bSupprimer.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        System.out.println("element selectioner " + f.getTitre_formation());
                                        //service.delete(o);
                                        FormationsService.supFormation(f);
                                        new FormationsListForm().show();
                                    }
                                });

                                bModifier.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        System.out.println("element selectioner " + f.getTitre_formation());
                                        //service.delete(o);
                                        FormationsService.modifFormation(f);
                                        new ModifierFormationForm(f).show();

                                    }
                                });
                                elments[i] = cGlobal;
                                i++;
                            }
                            return elments;
                        }
                    };
                    cnt.add(icp);
                }
                if (x.getSelectedItem().equals("Duree Bas")) {
                    icp = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {
                                formations = FormationsService.TrierFormation("PB");
                                amount = formations.size();

                            }
                            if (index + amount > formations.size()) {

                                amount = formations.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            // System.out.println(amount);
                            Component[] elments = new Component[amount];

                            int i = 0;
                            for (Formation f : formations) {
                                Container cDetaille = new Container(BoxLayout.y());
                                Container cGlobal = new Container(BoxLayout.x());
                                Label esLabel = new Label(" ");
                                Label Titre = new Label("Titre : " + f.getTitre_formation());
                                Label Description = new Label("Description : " + f.getDescription_formation());
                                Label Duree = new Label("Duree formation: " + f.getDuree_formation());
                                Label likes = new Label("Rating : " + f.getLikes());

                                Button bSupprimer = new Button("Supprimer");
                                Button bModifier = new Button("Modifier");

                                Container cb = new Container(BoxLayout.x());

                                cb.addAll(bModifier, bSupprimer);

                                cDetaille.addAll(esLabel, Titre, Description, Duree, likes, cb);

                                ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

                                cGlobal.addAll(image, cDetaille);
                                bSupprimer.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        System.out.println("element selectioner " + f.getTitre_formation());
                                        //service.delete(o);
                                        FormationsService.supFormation(f);
                                        new FormationsListForm().show();
                                    }
                                });

                                bModifier.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        System.out.println("element selectioner " + f.getTitre_formation());
                                        //service.delete(o);
                                        FormationsService.modifFormation(f);
                                        new ModifierFormationForm(f).show();

                                    }
                                });
                                elments[i] = cGlobal;
                                i++;
                            }
                            return elments;
                        }
                    };
                    cnt.add(icp);
                }
            }
        });
        searchField.addDataChangedListener((i1, i2) -> {
            String t = searchField.getText();
            formations.clear();
            cnt.removeComponent(ic);
            if (cnt.contains(icp) == true) {
                cnt.removeComponent(icp);
            }
            if (t.equals("")) {
                icp = new InfiniteContainer() {
                    @Override
                    public Component[] fetchComponents(int index, int amount) {
                        if (index == 0) {
                            formations = FormationsService.getAllFormations();
                            amount = formations.size();

                        }
                        if (index + amount > formations.size()) {

                            amount = formations.size() - index;

                        }
                        if (amount <= 0) {
                            return null;
                        }
                        Component[] elments = new Component[amount];
                        int i = 0;
                        for (Formation f : formations) {
                            Container cDetaille = new Container(BoxLayout.y());
                            Container cGlobal = new Container(BoxLayout.x());
                            Label esLabel = new Label(" ");
                            Label Titre = new Label("Titre : " + f.getTitre_formation());
                            Label Description = new Label("Description : " + f.getDescription_formation());
                            Label Duree = new Label("Duree formation: " + f.getDuree_formation());
                            Label likes = new Label("Rating : " + f.getLikes());

                            Button bSupprimer = new Button("Supprimer");
                            Button bModifier = new Button("Modifier");

                            Container cb = new Container(BoxLayout.x());

                            cb.addAll(bModifier, bSupprimer);

                            cDetaille.addAll(esLabel, Titre, Description, Duree, likes, cb);

                            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

                            cGlobal.addAll(image, cDetaille);
                            bSupprimer.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {

                                    System.out.println("element selectioner " + f.getTitre_formation());
                                    //service.delete(o);
                                    FormationsService.supFormation(f);
                                    new FormationsListForm().show();
                                }
                            });

                            bModifier.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println("element selectioner " + f.getTitre_formation());
                                    //service.delete(o);
                                    FormationsService.modifFormation(f);
                                    new ModifierFormationForm(f).show();

                                }
                            });
                            elments[i] = cGlobal;
                            i++;
                        }

                        return elments;
                    }
                };
            } else {
                icp = new InfiniteContainer() {
                    @Override
                    public Component[] fetchComponents(int index, int amount) {
                        if (index == 0) {
                            formations = FormationsService.RechercchFormation(t);
                            amount = formations.size();

                        }
                        if (index + amount > formations.size()) {

                            amount = formations.size() - index;

                        }
                        if (amount <= 0) {
                            return null;
                        }
                        Component[] elments = new Component[amount];
                        int i = 0;
                        for (Formation f : formations) {
                            Container cDetaille = new Container(BoxLayout.y());
                            Container cGlobal = new Container(BoxLayout.x());
                            Label esLabel = new Label(" ");
                            Label Titre = new Label("Titre : " + f.getTitre_formation());
                            Label Description = new Label("Description : " + f.getDescription_formation());
                            Label Duree = new Label("Duree formation: " + f.getDuree_formation());
                            Label likes = new Label("Rating : " + f.getLikes());

                            Button bSupprimer = new Button("Supprimer");
                            Button bModifier = new Button("Modifier");

                            Container cb = new Container(BoxLayout.x());

                            cb.addAll(bModifier, bSupprimer);

                            cDetaille.addAll(esLabel, Titre, Description, Duree, likes, cb);

                            ImageViewer image = new ImageViewer(thRessources.getImage("images.png"));

                            cGlobal.addAll(image, cDetaille);
                            bSupprimer.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {

                                    System.out.println("element selectioner " + f.getTitre_formation());
                                    //service.delete(o);
                                    FormationsService.supFormation(f);
                                    new FormationsListForm().show();
                                }
                            });

                            bModifier.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println("element selectioner " + f.getTitre_formation());
                                    //service.delete(o);
                                    FormationsService.modifFormation(f);
                                    new ModifierFormationForm(f).show();

                                }
                            });
                            elments[i] = cGlobal;
                            i++;
                        }

                        return elments;
                    }
                };
            }
            cnt.add(icp);
        });
        super.add(cnt);
    }

    public FormationsListForm(Form previous) {

        afficher();

        this.setTitle("liste de formations");
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new HomeForm().show();
        });

    }

    public FormationsListForm() {

        afficher();
        this.setTitle("liste de formations");
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new HomeForm().show();
        });

    }

}
