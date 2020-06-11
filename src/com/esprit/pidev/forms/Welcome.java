/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;


import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.User;
import com.esprit.pidev.user.Login;
import com.esprit.pidev.user.UserService;

/**
 *
 * @author islem
 */
public class Welcome  extends BaseForm{
     public Welcome() {
        this(Resources.getGlobalResources());
        
    }
      @Override
    protected boolean isCurrentOrganisations() {
        return true;
    }
    
     public Welcome(com.codename1.ui.util.Resources resourceObjectInstance){
         installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("logout.png"), e -> {/*Login Form*/});
        //Choose your layout set in super methode
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    

    
    Login login = new Login();
    UserService userService = new UserService();
    Resources thRessources = UIManager.initFirstTheme("/theme_1");
    
    
        for (User u : userService.getAllUsers()){
           //  Container cDetaille = new Container(BoxLayout.y());
             Label esLabel = new Label(" ");
             Label LUsername = new Label("bonjour madame / monsieur "+u.getUsername());
             Label LEmail = new Label("votre @email est "+u.getEmai()); 
             this.addAll(esLabel, LUsername,LEmail);
                     
        }
          this.setTitle("Welcome");
           
        
    
    
}}
