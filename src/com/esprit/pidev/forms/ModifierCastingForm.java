/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Casting;
import com.esprit.pidev.services.CastingService;

/**
 *
 * @author islem
 */
public class ModifierCastingForm extends Form{
         CastingService castingService = new CastingService();
         Resources thRessources = UIManager.initFirstTheme("/theme_1");
        public ModifierCastingForm(Casting c){
            System.out.println(c);
            Label esLabel = new Label(" ");
        
                    Label lTitreCasting = new Label("Titre: ");
                    TextField TitreCasting = new TextField(c.getTitreCasting(), "titreCasting",20,TextArea.ANY);
                    Label lDateCasting = new Label("Date casting: ");
                    Picker DateCastingPicker =  new Picker();
                    DateCastingPicker.setType(Display.PICKER_TYPE_DATE);
                    Label lAdresseCasting = new Label("Adresse: ");
                    TextField AdresseCasting = new TextField(c.getAdresseCasting(), "adresseCasting",20,TextArea.ANY);

        Button btn = new Button("modifier Casting");
        btn.addActionListener((evt)->{
         
               try{
                c.setTitreCasting(TitreCasting.getText());
               
                String date=(new SimpleDateFormat("yyyy-MM-dd")).format(DateCastingPicker.getDate());
                String DateLP=(new SimpleDateFormat("yyyy-MM-dd")).format(c.getDateLP());

                c.setDateLP(DateLP);
                c.setDateCasting(date);
                c.setAdresseCasting(AdresseCasting.getText());
                System.out.println(c);
               
               if(new CastingService().modifCasting(c)){
                                           Dialog.show("SUCCESS", "Casting modifier avec succes", "OK", null);

               }else {
                                           Dialog.show("ERROR", "Server error", "OK", null);

               }
               }catch (NumberFormatException e){
                                   Dialog.show("ERROR", "exception", "OK", null);
               }});            
        this.addAll(TitreCasting,DateCastingPicker,AdresseCasting,btn);
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
        new CastingListForm(this).show();
        });
 }
}
