/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        
   String indicativoEHE08,resistencia,consistencia,ambiente,tamanomax;
   double cmmax,cmmin,cm,agua,ca;
   int grupo;
   boolean validcm,validac;

    public void setValidcm(boolean validcm) {
        this.validcm = validcm;
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

    public String getResistencia() {
        return resistencia;
    }

    public void setResistencia(String resistencia) {
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

    public String getTamanomax() {
        return tamanomax;
    }

    public void setTamanomax(String tamanomax) {
        this.tamanomax = tamanomax;
    }

    public double getCmmax() {
        return cmmax;
    }

    public void setCmmax(double cmmax) {
        this.cmmax = cmmax;
    }

    public double getCmmin() {
        return cmmin;
    }

    public void setCmmin(double cmmin) {
        this.cmmin = cmmin;
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

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
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
    
    
     
}
