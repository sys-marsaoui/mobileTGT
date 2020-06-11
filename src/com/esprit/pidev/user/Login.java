/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.user;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.BaseForm;
import com.esprit.pidev.forms.LoginForm;
import com.esprit.pidev.forms.OrganisationsForm;
import com.esprit.pidev.forms.Welcome;

/**
 *
 * @author Ghazi
 */
public class Login {

    Container Login = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Label LUsername = new Label("Username or Email ");
    Label LPassworde = new Label("Password");

    TextField TUsername = new TextField("", "Username or Email ");
    TextField TPassword = new TextField("", "Password", 20, TextField.PASSWORD);
    Button register = new Button("Register");
    Button login = new Button("Login");

    public Button getLoginBTN() {
        return this.login;
    }

    public Button getRegisterBTN() {
        return this.register;
    }

    public Container LoginForm() {

        login.addActionListener((e) -> {
            Auth authService = new Auth();
            authService.Login(TUsername.getText(), TPassword.getText());
            new Welcome().show();
                  
          
        });

        Login.add(LUsername);
        Login.add(TUsername);

        Login.add(LPassworde);
        Login.add(TPassword);
        Login.add(register);
        Login.add(login);

        return Login;
    }
    
    
    

}
