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

/**
 *
 * @author Ghazi
 */
public class Register {

    Container Register = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Label LUsername = new Label("Username");
    Label LEmail = new Label("Email");
    Label LPassworde = new Label("Password");
    Label LRePassworde = new Label("Confirm Password");

    TextField TUsername = new TextField("", "Username");
    TextField TEmail = new TextField("", "Email");

    TextField TPassword = new TextField("", "Choose a Password", 20, TextField.PASSWORD);
    TextField TRePassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
    Button register = new Button("Register");
    Button login = new Button("Login");

    public Button getLoginBTN() {
        return this.login;
    }

    public Button getRegisterBTN() {
        return this.register;
    }

    public Container RegisterForm() {

        register.addActionListener((e) -> {
             Auth authService = new Auth();
              authService.Register(TUsername.getText(), TPassword.getText(),TEmail.getText());

        });

        Register.add(LUsername);
        Register.add(TUsername);
        Register.add(LEmail);
        Register.add(TEmail);
        Register.add(LPassworde);
        Register.add(TPassword);
        Register.add(LRePassworde);
        Register.add(TRePassword);
        Register.add(register);
        Register.add(login);

        return Register;
    }
}
