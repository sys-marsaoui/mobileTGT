/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image publicationImage = null;
        if(isCurrentPublications()) publicationImage = selection;

        Image organisationImage = null;
        if(isCurrentOrganisations()) organisationImage = selection;
        
        Image eventsImage = null;
        if(isCurrentEvenements()) eventsImage = selection;
        
        Image castingImage = null;
        if(isCurrentCasting()) castingImage = selection;
        
        Image formationImage = null;
        if(isCurrentFormations()) formationImage = selection;
        
        Image articlesImage = null;
        if(isCurrentArticles()) articlesImage = selection;
        
        Image entretientImage = null;
        if(isCurrentEntretient()) entretientImage = selection;
        
        Image welcomeImage = null;
        if(isCurrentWelcome()) welcomeImage = selection;
        
       
        
        Button publicationsButton = new Button("Publications", publicationImage);
        publicationsButton.setUIID("SideCommand");
        publicationsButton.getAllStyles().setPaddingBottom(0);
        Container publication = FlowLayout.encloseMiddle(publicationsButton);
        publication.setLeadComponent(publicationsButton);
        publication.setUIID("SideCommand");
        publicationsButton.addActionListener(e -> new PublicationsForm().show());
        getToolbar().addComponentToSideMenu(publication);
        
        
        
        getToolbar().addCommandToSideMenu("Organisations", organisationImage, e -> {new OrganisationsForm().show();});
        getToolbar().addCommandToSideMenu("Evenements", eventsImage, e ->{new EvenementsForm().show();});
        getToolbar().addCommandToSideMenu("Casting", castingImage, e -> {new CastingForm().show();});
        getToolbar().addCommandToSideMenu("Formations", formationImage, e -> {new FormationsForm().show();});
        getToolbar().addCommandToSideMenu("Articles", articlesImage, e -> {new ArticlesForm().show();});
        getToolbar().addCommandToSideMenu("Entretient", entretientImage, e -> {new EntretientForm().show();});
        getToolbar().addCommandToSideMenu("Welcome", welcomeImage, e -> {new Welcome().show();});
        
        
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Nom Prénom", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Adresse", "SideCommandSmall"));
    }

        
    protected boolean isCurrentPublications() {
        return false;
    }
    
    protected boolean isCurrentOrganisations() {
        return false;
    }

    protected boolean isCurrentEvenements() {
        return false;
    }

    protected boolean isCurrentCasting() {
        return false;
    }
    
    protected boolean isCurrentFormations() {
        return false;
    }
    
    protected boolean isCurrentArticles() {
        return false;
    }   
    
     protected boolean isCurrentEntretient() {
        return false;
    }   
     
     protected boolean isCurrentWelcome() {
        return false;
    }   
 
}
