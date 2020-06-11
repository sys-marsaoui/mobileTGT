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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.enumeration.TypeContrat;
import com.esprit.pidev.models.Contrat;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.services.ContratService;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class AddContratForm extends Form {
private final String[] typecontrat = {"stage", "sivp", "cdd", "cdi"};
    
    
    ArrayList<Contrat> contrat;

    ContratService contratService = new ContratService();
  private Image img;
    private String cv;
  //  TypeContrat typeContrat;
    public AddContratForm(Entretien e) {

        super("Add a new contrat", BoxLayout.y());
        Label firstName = new Label(e.getCandidat().getNomC());
       Label titrecasting = new Label(e.getCandidat().getCasting().getTitreCasting());
         ComboBox listcontrat = new ComboBox(typecontrat);

         //  listcontrat.addItem(typecontrat);
            
      

        TextField duree = new TextField("", "duree");
        TextField salaire = new TextField("", "salaire");
      
      

        Button btn = new Button("cree le contrat");

        btn.addActionListener((evt) -> {
            if ((firstName.getText().length() == 0)){
            } else {
               
        
                    Contrat contrat = new Contrat(e.getCandidat().getCasting(),e.getCandidat(),Float.parseFloat(salaire.getText()),listcontrat.getSelectedItem().toString(),Integer.parseInt(duree.getText()));
                   

                    if (new ContratService().addContrat(contrat)) {
                    
                     
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
               

            }
        });

        this.addAll(firstName, listcontrat, duree, salaire, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new EntretientForm().show();
            
        });
    }

}
