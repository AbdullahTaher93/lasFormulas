package com.eqa.formula.DataBase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Abdullah_PC
 */
public class GetEHE {
        
   String indicativoEHE08,consistencia,ambiente,idPlanta,correo;
    double tamanomax,cmmax,acmin,cm,agua,a_c;
   int grupo,resistencia,ID_formula;
   boolean validcm,validac,firmado;
   long fechadevalid;
   

   public void setID_formula(int ID_formula){
       this.ID_formula=ID_formula;
   }
    public int getID_formula(){
      return this.ID_formula;
   }
    public boolean isFirmado() {
        return firmado;
    }

    public void setFirmado(boolean firmado) {
        this.firmado = firmado;
    }
   
 
    public void setValidcm(boolean validcm) {
        this.validcm = validcm;
    }

    public String getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getFechadevalid() {
        return fechadevalid;
    }

    public void setFechadevalid(long fechadevalid) {
        this.fechadevalid = fechadevalid;
    }

    public void setValidac(boolean validac) {
        this.validac = validac;
    }

    public boolean isValidcm() {
        return validcm;
    }

    public boolean isValidac() {
        return validac;
    }
   

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
   
    public String getIndicativoEHE08() {
        return indicativoEHE08;
    }

    public void setIndicativoEHE08(String indicativoEHE08) {
        this.indicativoEHE08 = indicativoEHE08;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getConsistencia() {
        return consistencia;
    }

    public void setConsistencia(String consistencia) {
        this.consistencia = consistencia;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public double getTamanomax() {
        return tamanomax;
    }

    public void setTamanomax(double tamanomax) {
        this.tamanomax = tamanomax;
    }

    public double getCmmax() {
        return cmmax;
    }

    public void setCmmax(double cmmax) {
        this.cmmax = cmmax;
    }

    public double getAcmin() {
        return acmin;
    }

    public void setAcmin(double cmmin) {
        this.acmin = cmmin;
    }

    public double getCm() {
        return cm;
    }

    public void setCm(double cm) {
        this.cm = cm;
    }

    public double getAgua() {
        return agua;
    }

    public void setAgua(double agua) {
        this.agua = agua;
    }

    public double geta_c() {
        return a_c;
    }

    public void seta_c(double ac) {
        this.a_c = ac;
    }
   
   public void valid(double cm,double ac,double cm1,double ac1){
       if(cm1<cm)
           this.setValidcm(false);
       else
           this.setValidcm(true);
       
       if(ac1>ac)
           this.setValidac(false);
       else
           this.setValidac(true);
       
       System.out.println(cm1+" " +cm+"  ="+this.isValidcm()+"  , "+ac1+" "+ac+" = "+this.isValidac());
       
   }
    
    public String gettodo(){
        return indicativoEHE08+"-"+resistencia+"/"+consistencia+"/"+tamanomax+"/"+ambiente;
    }
    
      
    public String toJson () {
        
     String json = new Gson().toJson(this);
     System.out.println(json);
     return json;
    }
     
}
