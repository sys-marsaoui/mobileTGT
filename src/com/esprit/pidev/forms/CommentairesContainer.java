/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.pidev.models.CommentairePublication;
import com.esprit.pidev.models.Publication;
import com.esprit.pidev.services.CommentairePublicationService;
import com.esprit.pidev.services.PublicationService;
import com.esprit.pidev.utils.RatingWidget;
import java.util.ArrayList;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class CommentairesContainer extends Container {

    CommentairePublicationService cps = new CommentairePublicationService();
    PublicationService ps = new PublicationService();

    public CommentairesContainer(Publication publication) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        Container comments = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<CommentairePublication> cmnts = cps.getAllCommentaires(publication);
        //Ajout Commentaire
        Container addComm = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label addComment = new Label("Votre Commentaire", "RedLabel");
        Label indicator = new Label("Donnez votre avis aux utilisateurs", "SmallLabel");
        TextField tfComment = new TextField(null, "Ajoutez votre commentaire");
        RatingWidget rw = new RatingWidget();
        Slider rating = rw.createStarRankSlider(true);
        Button btnPublier = new Button("PUBLIER");
        btnPublier.addActionListener((evt) -> {
            if (tfComment.getText() == null) {
                tfComment.setText("");
            }
            CommentairePublication cp = new CommentairePublication(tfComment.getText(), rating.getProgress());
            cp.setP(publication);
            if (cps.ajouter(cp)) {
                cmnts.add(cp);
                tfComment.setText("");
                rating.setProgress(0);
                ToastBar.showMessage("Commentaire ajouté avec succés", FontImage.MATERIAL_DONE);
                System.out.println(cp);
                new DetailsPublication(ps.getPublication(publication.getId_pub())).show();
                //comments.add(AddComment(cp, s, rw, datePub));
            }
        });
        Button overflow = new Button(FontImage.createMaterial(FontImage.MATERIAL_MORE_VERT, s), "Label");
        overflow.addActionListener(e -> {
            Button op1 = new Button("Trier par Date", FontImage.createMaterial(FontImage.MATERIAL_TODAY, s), "Label");
            Button op2 = new Button("Trier par Rating", FontImage.createMaterial(FontImage.MATERIAL_STAR, s), "Label");
            Button op3 = new Button("Restaurer", FontImage.createMaterial(FontImage.MATERIAL_RESTORE, s), "Label");
            Dialog d = new Dialog("", BoxLayout.y());
            op1.addActionListener((evt) -> {
                comments.removeAll();
                for (CommentairePublication commentaire : cps.trieParDate(publication)) {
                    Container comment = AddComment(commentaire, s, rw, commentaire.getDateComm().toString());
                    commentaire.setP(publication);
                    comments.add(comment);
                }
                d.dispose();
            });
            op2.addActionListener((evt) -> {
                comments.removeAll();
                for (CommentairePublication commentaire : cps.trieParRating(publication)) {
                    Container comment = AddComment(commentaire, s, rw, commentaire.getDateComm().toString());
                    commentaire.setP(publication);
                    comments.add(comment);
                }
                d.dispose();
            });
            op3.addActionListener((evt) -> {
                comments.removeAll();
                for (CommentairePublication commentaire : cmnts) {
                    Container comment = AddComment(commentaire, s, rw, commentaire.getDateComm().toString());
                    commentaire.setP(publication);
                    comments.add(comment);
                }
                d.dispose();
            });
            d.addAll(op1, op2, op3);
            d.showPopupDialog(overflow);
        });
        Container firstLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        firstLine.add(addComment);
        firstLine.add(FlowLayout.encloseCenter(overflow));
        addComm.addAll(firstLine, indicator, tfComment);
        addComm.add(FlowLayout.encloseCenter(rating));
        addComm.add(btnPublier);
        //Liste des comentaires
        for (CommentairePublication commentaire : cmnts) {
            Container comment = AddComment(commentaire, s, rw, commentaire.getDateComm().toString());
            commentaire.setP(publication);
            comments.add(comment);
        }
        this.setScrollableY(true);
        this.addAll(addComm, comments);
    }

    private Container AddComment(CommentairePublication cp, Style s, RatingWidget rw, String datePub) {
        Container comment = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container dateTime = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label contenu = new Label(cp.getContenu(), FontImage.createMaterial(FontImage.MATERIAL_COMMENT, s));
        Slider ratingComm = rw.createStarRankSlider(false);
        ratingComm.setProgress(cp.getRatingComm());
        int index = datePub.lastIndexOf(" ");
        Label date = new Label(datePub.substring(0, 10) + datePub.substring(index), FontImage.createMaterial(FontImage.MATERIAL_TODAY, s), "SmallLabel");
        Label time = new Label(datePub.substring(11, 16), FontImage.createMaterial(FontImage.MATERIAL_SCHEDULE, s), "SmallLabel");
        dateTime.addAll(date, time);
        Button overflow = new Button(FontImage.createMaterial(FontImage.MATERIAL_MORE_VERT, s), "Label");
        overflow.addActionListener(e -> {
            Button op1 = new Button("Modifier", FontImage.createMaterial(FontImage.MATERIAL_EDIT, s), "Label");
            Button op2 = new Button("Supprimer", FontImage.createMaterial(FontImage.MATERIAL_DELETE, s), "Label");
            Dialog d = new Dialog("", BoxLayout.y());
            op1.addActionListener((evt) -> {
                Dialog id = new Dialog("Modifier ce commentaire");
                id.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                Label editComment = new Label("Votre Commentaire", "RedLabel");
                Label indicator = new Label("Donnez votre avis aux utilisateurs", "SmallLabel");
                TextField tfComment = new TextField(cp.getContenu(), "Ajoutez votre commentaire");
                Slider rating = rw.createStarRankSlider(true);
                rating.setProgress(cp.getRatingComm());
                Button btnPublier = new Button("PUBLIER");
                btnPublier.addActionListener((evt1) -> {
                    cp.setContenu(tfComment.getText());
                    cp.setRatingComm(rating.getProgress());
                    if (cps.modifier(cp)) {
                        ToastBar.showMessage("Commentaire modifié avec succés", FontImage.MATERIAL_DONE);
                        id.dispose();
                        contenu.setText(cp.getContenu());
                        ratingComm.setProgress(cp.getRatingComm());
                    }
                });
                id.setDisposeWhenPointerOutOfBounds(true);
                id.addAll(editComment, indicator, tfComment);
                id.add(FlowLayout.encloseCenter(rating)).add(btnPublier);
                id.show();
                d.dispose();
            });
            op2.addActionListener((evt) -> {
                if (Dialog.show("Suppression Commentaire", "Voulez-vous vraiment supprimer cet commentaire ?", "Supprimer", "Annuler")) {
                    if (cps.supprimer(cp)) {
                        ToastBar.showMessage("Commentaire Supprimé avec succés", FontImage.MATERIAL_DELETE);
                        comment.remove();
                        d.dispose();
                    }
                }
            });
            d.addAll(op1, op2);
            d.showPopupDialog(overflow);
        });
        Container firstLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        firstLine.add(contenu);
        firstLine.add(FlowLayout.encloseCenter(overflow));
        comment.add(firstLine);
        comment.add(FlowLayout.encloseCenter(ratingComm));
        comment.add(dateTime);
        return comment;
    }

}
