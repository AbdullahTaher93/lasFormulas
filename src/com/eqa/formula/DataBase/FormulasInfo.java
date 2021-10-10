/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.DataBase;

import com.google.gson.Gson;
import java.util.Hashtable;

/**
 *
 * @author Abdullah_PC
 */
public class FormulasInfo {
    String idPlanta,correo;
    String PDF;
    String Version="";
    int formulasSize;

    
    
    Hashtable<Integer, GetEHE> Formulas;

    public Hashtable<Integer, GetEHE> getStore() {
        return Formulas;
    }

    public void setStore(Hashtable<Integer, GetEHE> store) {
        this.Formulas = store;
        setFormulasSize(store.size());
      
    }
    
      public void setFormulasSize(int formulasSize) {
        this.formulasSize = formulasSize;
    }
    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }
long desdeFechadeValid,hastaFechadeValid;

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
    public String toJson () {
        
     String json = new Gson().toJson(this);
     System.out.println(json);
     return json;
    }

}

