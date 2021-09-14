package com.eqa.formula.DataBase;


import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah_PC
 */
public class database {

    public database() {
    }

    public Hashtable<Integer, GetEHE> getStore() {
        return store;
    }
    
Hashtable<Integer, GetEHE> store = new Hashtable<Integer, GetEHE>();
List ambiente1;
List ambiente2;
double cemMax=0,acmin=100;

    public double getCemMax() {
        return cemMax;
    }

    public double getAcmin() {
        return acmin;
    }



    public List getAmbiente1() {
        return ambiente1;
    }

    public List getAmbiente2() {
        return ambiente2;
    }


    
    public database(int codeAmb,String EHE) {
        ambiente1=new  List();
         ambiente2=new  List();
        String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         String sql;
       switch(codeAmb){
           case 1:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)<8) AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
           default:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)>7) AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
          
       }
     
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
          Statement statement =connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                
                if(codeAmb==1){
                    ambiente1.add(result.getString("ambiente"));
                    System.out.println(result.getString("ambiente"));
                }else{
                    ambiente2.add(result.getString("ambiente"));
                    System.out.println(result.getString("ambiente"));
                }
                
               
                 
                System.out.println();
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
     public database(List formula) {
          
          
        String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         String sql;
         for(int i=1;i<formula.getItemCount();i++){
             
         
       sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ambiente)='"+formula.getItem(i)+"') AND ((indicativoEHE08.indicativoEHE)='"+formula.getItem(0)+"'));";
     
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
          Statement statement =connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                
                if(cemMax<Double.parseDouble(result.getString("Cem_Max"))){
                   cemMax=Double.parseDouble(result.getString("Cem_Max"));
                }
               if(acmin>Double.parseDouble(result.getString("a-cmin"))){
                     acmin=Double.parseDouble(result.getString("a-cmin"));
                }
               
                
                
               
                 
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        }
             
    }
     
  public void  SaveDate(List formula,int resistencia,String consistancia,double tamanoMax,double cem,double a_c,double  agua,int  grupo,double cemMax,double acMin){
      Hashtable<Integer, GetEHE> store = new Hashtable<Integer, GetEHE>();
      int index=0;
      GetEHE e=new GetEHE();
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
       String amb=formula.getItem(1);
        for(int i=2;i<formula.getItemCount();i++){
        amb=amb+"+"+formula.getItem(i);
        }
      try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "INSERT INTO Formulas (EHE, resistencia, consistencia,tamanoMax,ambiente,cem,a_c,agua,grupo,cemMax,acMin) VALUES (?, ?, ?,?,?,?,?,?,?,?,?)";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formula.getItem(0));
            preparedStatement.setInt(2, resistencia);
            preparedStatement.setString(3, consistancia);
            preparedStatement.setDouble(4, tamanoMax);
            preparedStatement.setString(5, amb);
            preparedStatement.setDouble(6, cem);
            preparedStatement.setDouble(7, a_c);
            preparedStatement.setDouble(8, agua);
            preparedStatement.setInt(9, grupo);
            preparedStatement.setDouble(10, cemMax);
            preparedStatement.setDouble(11, acMin);
            
            
            
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("A row has been inserted successfully.");
            }
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
  }
  public void  UpdateDate(double cem,double a_c,double  agua,int  grupo,int ID_formula){
      Hashtable<Integer, GetEHE> store = new Hashtable<Integer, GetEHE>();
      int index=0;
      GetEHE e=new GetEHE();
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
      
      try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "update Formulas set  cem=?,a_c=?,agua=?,grupo=? where Formulas.ID_formula="+ID_formula;
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setDouble(1, cem);
            preparedStatement.setDouble(2, a_c);
            preparedStatement.setDouble(3, agua);
            preparedStatement.setInt(4, grupo);
           
            
            
            
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("update successfully.");
            }
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
  }
  public void GetAll(String S) throws SQLException{
      
      int index=0;
      
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
         String sql;    
      if(S.isEmpty())      
       sql="SELECT Formulas.* FROM Formulas;";
            else
          sql=S;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
               
                GetEHE e=new GetEHE();
                e.setID_formula(result.getInt("ID_formula"));
                e.setIndicativoEHE08(result.getString("EHE"));
                e.setResistencia(result.getInt("resistencia"));
                e.setConsistencia(result.getString("consistencia"));
                e.setTamanomax(result.getDouble("tamanoMax"));
                e.setAmbiente(result.getString("ambiente"));
                e.setCm(result.getDouble("cem"));
                e.seta_c(result.getDouble("a_c"));
                e.setAgua(result.getDouble("agua"));
                e.setGrupo(result.getInt("grupo"));
                e.setCmmax(result.getDouble("cemMax"));
                e.setAcmin(result.getDouble("acMin"));
                store.put(index, e);
               // System.out.println(store.get(index).gettodo());
                index++;
                
            }
            
  }
  }
  
  public Hashtable<Integer,List> getMax_Min_Grupo() throws SQLException{
      Hashtable<Integer,List> MaxMinGrupos=new Hashtable<>();
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
       int Grupos=0;
         try (Connection connection = DriverManager.getConnection(databaseURL)) {
      String sql="SELECT MAX(Formulas.grupo) as Max FROM Formulas;";
      Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
            Grupos=result.getInt("Max");
            }
            
            for(int i=0;i<=Grupos;i++){
                 sql="SELECT MAX(Formulas.Cem) as Max,Min(Formulas.Cem) as mincem, Max(Formulas.a_c) as Macac,Min(Formulas.a_c) as Min FROM Formulas where(Formulas.grupo="+i+" );";
             statement = connection.createStatement();
             result = statement.executeQuery(sql);
                List l=new List();
                while (result.next()) {
                l.add(result.getDouble("Max")+"");
                l.add(result.getDouble("mincem")+"");
                l.add(result.getDouble("Macac")+"");
                l.add(result.getDouble("Min")+"");
                 
                
                MaxMinGrupos.put(i, l);
                //System.out.println("Grupo "+i+" = "+l.getItem(0)+" "+l.getItem(1)+",  "+l.getItem(2)+"   "+l.getItem(3));
                }
            }
         }
         return MaxMinGrupos;
  }
  
  public void deleteformula(int ID_formula) throws SQLException{
      
     
      
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
         String sql;    
            
       sql="delete  FROM Formulas where Formulas.ID_formula="+ID_formula+";";
           
            Statement statement = connection.createStatement();
             statement.execute(sql);
            
            
  }
  }
}