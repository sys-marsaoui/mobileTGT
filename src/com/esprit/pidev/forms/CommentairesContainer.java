/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Publication;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class CommentairesContainer extends Container{

    public CommentairesContainer(Publication publication) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
    }
        
}
