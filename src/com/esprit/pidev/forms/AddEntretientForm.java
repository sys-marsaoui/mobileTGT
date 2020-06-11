/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.pidev.enumeration.StatutEnt;
import com.esprit.pidev.models.Candidat;
import com.esprit.pidev.models.Entretien;
import com.esprit.pidev.services.CandidatService;
import com.esprit.pidev.services.CastingService;
import com.esprit.pidev.services.EntretientService;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author aissa
 */
public class AddEntretientForm extends Form {
//ArrayList<Candidat> candidatlist;
//Candidat candidat;
//     private ArrayList<Button> allButtons = new ArrayList<Button>();
//
//    CandidatService candidatService = new CandidatService();
//    public AddEntretientForm(Candidat c) {
//        super("Add a new candidat", BoxLayout.y());
//
//Picker dateTimePicker = new Picker();
//dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
//ComboBox  idcandidat = new ComboBox("", candidat );
//
//
//for(Candidat cs :candidatService.getAllCandidats()){
//    
//    idcandidat.addItem(cs);
//}
//dateTimePicker.setDate(new Date());
//
//
//
// Button btn = new Button("passer condidature");
//        String date=(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(dateTimePicker.getDate());
//     //   String time=(new SimpleDateFormat("HH:MM")).format(timePicker.getDate());
//        btn.addActionListener((evt) -> {
//          
//                try {
//                 
//                    Entretien entretient = new Entretien(date, StatutEnt.encour, 9,(Candidat)idcandidat.getSelectedItem());
//                    
//                 if (new EntretientService().addEntretien(entretient)) {
//                        Dialog.show("SUCCESS", "entretient inscrit", "OK", null);
//                        
//                    } else {
//                        Dialog.show("ERROR", "Server error", "OK", null);
//                    }
//                } catch (NumberFormatException e) {
//                    Dialog.show("ERROR", "entretient must be a number", "OK", null);
//                }
//        });
//
//this.addAll(dateTimePicker,idcandidat,btn);
//this.show();
//                  this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            this.showBack();
//        });
//    }    
//        
//        
//  
        
        
        
        
//    Container calendarTitle = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
//            Container title = new Container(new GridLayout(1,7));
//            Container days = new Container(new GridLayout(6, 7));
//            Container calendarTitleCopy = new Container(new GridLayout(1, 1));
//                calendarTitleCopy.setUIID("CalendarTitleCopy");
//            this.addComponent(calendarTitleCopy);
//            this.addComponent(title);
//            this.addComponent(days);
//
//  Button prevMonth = new Button("<");
//            Button nextMonth = new Button(">");
//            SpanLabel monthYear = new SpanLabel("Month " + " Year");
//            calendarTitle.add(BorderLayout.WEST, prevMonth);
//            calendarTitle.add(BorderLayout.CENTER, monthYear);
//            calendarTitle.add(BorderLayout.EAST, nextMonth);
//            calendarTitleCopy.add(calendarTitle);
//            Button dayButton= new Button();
//
//
//
//
//           if(UIManager.getInstance().isThemeConstant("calTitleDayStyleBool", false)) {
//                title.setUIID("CalendarTitleArea");
//                days.setUIID("CalendarDayArea");
//            }
//            for (int iter = 0; iter < DAYS.length; iter++) {
//                title.addComponent(createDayTitle(iter));
//            }
//            for (int iter = 1; iter < length; iter++) {
//               dayButton = new Button(""+iter);
//
//                     dayButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//                            Log.p("Action event triggered");
//                            Picker datePicker = new Picker();
//                            datePicker.setType(Display.PICKER_TYPE_DATE);
//
//                            Button b1 = (Button)(evt.getActualComponent());
//                            Log.p( b1.getText() );
//                        }
//                    });
//
//               allButtons.add(dayButton);
//                days.addComponent(dayButton);
//                if (iter <= 7) {
//                    dayButton.setNextFocusUp(year);
//                }
//            }
//    }
//  protected Label createDayTitle(int day) {
//        String value = getUIManager().localize("Calendar." + DAYS[day], LABELS[day]);
//        Label dayh = new Label(value, "Label");
//        dayh.setEndsWith3Points(false);
//        dayh.setTickerEnabled(false);
//        return dayh;
//    }
}
