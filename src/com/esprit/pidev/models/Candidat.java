/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author goldzeo
 */
public class Candidat {
    
    private int id;
    private Casting  casting;
    private Entretien entretient;
    private String nomC;
    private Integer cinCondidat;
    private String cv;
    private String motivation;
    private String mailaddress;
    private Integer tel;

    public Candidat( String nomC,Casting casting, Integer cinCondidat, String cv, String motivation, String mailaddress, Integer tel ) {
        this.nomC = nomC;
        this.casting = casting;
        this.cinCondidat = cinCondidat;
        this.cv = cv;
        this.motivation = motivation;
        this.mailaddress = mailaddress;
        this.tel = tel;
    }

    public Candidat(int id, String nomC,Casting casting) {
        this.casting = casting;
        this.id = id;
        this.nomC = nomC;
    }
    
        public Candidat(int id, String nomC) {
        this.id = id;
        this.nomC = nomC;
    }

    public Candidat(int id) {
        this.id = id;
    }

    public Candidat(int id, Casting casting, String nomC, Integer cinCondidat, String cv, String motivation, String mailaddress, Integer tel) {
        this.id = id;
        this.casting = casting;
        this.nomC = nomC;
        this.cinCondidat = cinCondidat;
        this.cv = cv;
        this.motivation = motivation;
        this.mailaddress = mailaddress;
        this.tel = tel;
    }

    public Candidat(int id,String nomC, Integer cinCondidat, String cv, String motivation, String mailaddress, Integer tel) {
        this.id = id;
        this.nomC = nomC;
        this.cinCondidat = cinCondidat;
        this.cv = cv;
        this.motivation = motivation;
        this.mailaddress = mailaddress;
        this.tel = tel;
    }


    
    

    public Candidat(int id, Casting casting, Entretien entretient, String nomC, Integer cinCondidat, String cv, String motivation, String mailaddress, Integer tel) {
        this.id = id;
        this.casting = casting;
        this.entretient = entretient;
        this.nomC = nomC;
        this.cinCondidat = cinCondidat;
        this.cv = cv;
        this.motivation = motivation;
        this.mailaddress = mailaddress;
        this.tel = tel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Casting getCasting() {
        return casting;
    }

    public void setCasting(Casting casting) {
        this.casting = casting;
    }

    public Entretien getEntretient() {
        return entretient;
    }

    public void setEntretient(Entretien entretient) {
        this.entretient = entretient;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public Integer getCinCondidat() {
        return cinCondidat;
    }

    public void setCinCondidat(Integer cinCondidat) {
        this.cinCondidat = cinCondidat;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Candidat{" + "id=" + id + ", casting=" + casting + ", entretient=" + entretient + ", nomC=" + nomC + ", cinCondidat=" + cinCondidat + ", cv=" + cv + ", motivation=" + motivation + ", mailaddress=" + mailaddress + ", tel=" + tel + '}';
    }

   
    
    
    
}
