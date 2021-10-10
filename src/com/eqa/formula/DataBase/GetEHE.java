package com.eqa.formula.DataBase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;


/**
 *
 * @author Abdullah_PC
 */
public class GetEHE {
        
   String indicativoEHE08,consistencia,ambiente,idPlanta,correo;
   String ambiente1="",ambiente2="",ambiente3="",ambiente4="",des="";
    double cmmax,acmin,cm,agua,a_c;
   int grupo,resistencia,ID_formula,tamanomax;
   boolean validcm,validac,firmado,ouctar=false;
   long desdeFechadeValid,hastaFechadeValid;
   

    
    
   
   
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getA_c() {
        return a_c;
    }

    public void setA_c(double a_c) {
        this.a_c = a_c;
    }

    public String getAmbiente1() {
        return ambiente1;
    }

    public void setAmbiente1(String ambiente1) {
        this.ambiente1 = ambiente1;
    }

    public String getAmbiente2() {
        return ambiente2;
    }

    public void setAmbiente2(String ambiente2) {
        this.ambiente2 = ambiente2;
    }

    public String getAmbiente3() {
        return ambiente3;
    }

    public void setAmbiente3(String ambiente3) {
        this.ambiente3 = ambiente3;
    }

    public String getAmbiente4() {
        return ambiente4;
    }

    public void setAmbiente4(String ambiente4) {
        this.ambiente4 = ambiente4;
    }
   
  
   
   

    public boolean isOuctar() {
        return ouctar;
    }

    public void setOuctar(boolean ouctar) {
        this.ouctar = ouctar;
    }

    public long getDesdeFechadeValid() {
        return desdeFechadeValid;
    }

    public void setDesdeFechadeValid(long desdeFechadeValid) {
        this.desdeFechadeValid = desdeFechadeValid;
    }

    public long getHastaFechadeValid() {
        return hastaFechadeValid;
    }

    public void setHastaFechadeValid(long hastaFechadeValid) {
        this.hastaFechadeValid = hastaFechadeValid;
    }
   
   

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

    public int getTamanomax() {
        return tamanomax;
    }

    public void setTamanomax(int tamanomax) {
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
        setAgua(Math.round(getCm()*ac));
                
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
        if(getDes()==null||getDes().equalsIgnoreCase(""))
        return indicativoEHE08+"-"+resistencia+"/"+consistencia+"/"+tamanomax+"/"+getAmbientes()
                
                ;
        else
            return indicativoEHE08+"-"+resistencia+"/"+consistencia+"/"+tamanomax+"-"+getDes();
    }
    
    public String getAmbientes(){
        String amb="";
        if(!getAmbiente1().equalsIgnoreCase(""))
        {
            amb=getAmbiente1();
            if(!getAmbiente2().equalsIgnoreCase(""))
            {
              amb=amb+"+"+getAmbiente2();  
              if(!getAmbiente3().equalsIgnoreCase("")){
                   amb=amb+"+"+getAmbiente3();
              if(!getAmbiente4().equalsIgnoreCase(""))
                   amb=amb+"+"+getAmbiente4();
              }
            }
        }
        
        
        
        return amb;
    }
    public String toJson () {
        
     String json = new Gson().toJson(this);
     System.out.println(json);
     return json;
    }
     
}

