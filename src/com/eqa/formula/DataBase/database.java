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
List ambiente3;
List ambiente4;
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
        public List getAmbiente3() {
        return ambiente3;
    }
         public List getAmbiente4() {
        return ambiente4;
    }
    


    
    public database(int codeAmb,String EHE,String Amb) {
         ambiente1=new  List();
         ambiente2=new  List();
         ambiente3=new List();
         ambiente4=new List();
        String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         String sql;
       switch(codeAmb){
           case 1:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)<8) AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
           case 2:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)>7) AND((ambiente1.ID_ambiente)<11) AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
               case 3:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)>10)  AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
               default:
               sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ID_ambiente)>10)  AND ((ambiente1.ambiente)<>'"+Amb+"')  AND ((indicativoEHE08.indicativoEHE)='"+EHE+"'));";
               break;
          
       }
     
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
          Statement statement =connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                
              switch (codeAmb) {
                  case 1:
                      ambiente1.add(result.getString("ambiente"));
                      // System.out.println(result.getString("ambiente"));
                      break;
              //System.out.println();
                  case 2:
                      ambiente2.add(result.getString("ambiente"));
                      //System.out.println(result.getString("ambiente"));
                      break;
                  case 3:
                      ambiente3.add(result.getString("ambiente"));
                      // System.out.println(result.getString("ambiente"));
                      break;
                  default:
                      ambiente4.add(result.getString("ambiente"));
                      // System.out.println(result.getString("ambiente"));
                      break;
              }
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
     public database(GetEHE eHE) { 
         
         
          List formula=new List();
         
          if(!eHE.getAmbiente1().equalsIgnoreCase("")){
              formula.add(eHE.getAmbiente1());
              if(!eHE.getAmbiente2().isEmpty())
              {
                  
                  if(!eHE.getAmbiente3().equalsIgnoreCase(""))
                  {
                      formula.add(eHE.getAmbiente3());
                      
                     if(!eHE.getAmbiente4().equalsIgnoreCase(""))
                           formula.add(eHE.getAmbiente4());
                  }
              }
          }
          
          
        String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
         String sql;
         for(int i=0;i<formula.getItemCount();i++){
             
         
       sql = "SELECT indicativoEHE08.ID_EHE08, ambiente1.ID_ambiente, indicativoEHE08.indicativoEHE, ambiente1.ambiente, Values.[a-cMin], Values.Cem_Max\n" +
"FROM ((ambiente1 INNER JOIN con ON ambiente1.ID_ambiente = con.ID_ambiente) INNER JOIN indicativoEHE08 ON con.ID_EHE08 = indicativoEHE08.ID_EHE08) INNER JOIN [Values] ON con.ID_code = Values.ID_code\n" +
"WHERE (((ambiente1.ambiente)='"+formula.getItem(i)+"') AND ((indicativoEHE08.indicativoEHE)='"+eHE.getIndicativoEHE08()+"'));";
     
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
     
  public void  SaveDate(GetEHE eHE){
      Hashtable<Integer, GetEHE> store = new Hashtable<Integer, GetEHE>();
      
      
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
       
      try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "INSERT INTO Formulas (EHE, resistencia, consistencia,tamanoMax,ambiente,cem,a_c,agua,grupo,cemMax,acMin,des,formulaCompleta,ouctar,validcem,validac) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eHE.getIndicativoEHE08());
            preparedStatement.setInt(2, eHE.getResistencia());
            preparedStatement.setString(3, eHE.getConsistencia());
            preparedStatement.setInt(4, eHE.getTamanomax());
            preparedStatement.setString(5, eHE.getAmbientes());
            preparedStatement.setDouble(6, eHE.getCm());
            preparedStatement.setDouble(7, eHE.geta_c());
            preparedStatement.setDouble(8, eHE.getAgua());
            preparedStatement.setInt(9, eHE.getGrupo());
            preparedStatement.setDouble(10, eHE.getCmmax());
            preparedStatement.setDouble(11, eHE.getAcmin());
            preparedStatement.setString(12, eHE.getDes());
            preparedStatement.setString(13, eHE.gettodo());
            preparedStatement.setBoolean(14, eHE.isOuctar());
            preparedStatement.setBoolean(15, eHE.isValidac());
            preparedStatement.setBoolean(16, eHE.isValidcm());
            
            
            
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("A row has been inserted successfully.");
            }
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
  }
  public void  UpdateDate(double cem,double a_c,double  agua,int  grupo,int ID_formula){
     
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
  public Hashtable<Integer, GetEHE> GetAll(String S) throws SQLException{
      
      int index=0;
      Hashtable<Integer, GetEHE> alldata = new Hashtable<>();
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
                e.setTamanomax(result.getInt("tamanoMax"));
                e.setAmbiente1(result.getString("ambiente"));
                e.setCm(result.getDouble("cem"));
                e.seta_c(result.getDouble("a_c"));
                e.setAgua(result.getDouble("agua"));
                e.setGrupo(result.getInt("grupo"));
                e.setCmmax(result.getDouble("cemMax"));
                e.setAcmin(result.getDouble("acMin"));
                e.setDes(result.getString("des"));
                e.setOuctar(result.getBoolean("ouctar"));
                
                e.setIdPlanta(result.getString("IdPlanta"));
                alldata.put(index, e);
               // System.out.println(store.get(index).gettodo());
                index++;
                
            }
            
  }
         return alldata;
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
                 sql="SELECT MAX(Formulas.Cem) as Max,Min(Formulas.Cem) as mincem, Max(Formulas.a_c) as Macac,Min(Formulas.a_c) as Min FROM Formulas where(Formulas.grupo="+i+" ) AND (Formulas.ouctar=false);";
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

    public void ocultadaformula(int ID_formula,boolean ouctar) {
        
       String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
      
      try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "update Formulas set  ouctar=? where Formulas.ID_formula="+ID_formula;
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setBoolean(1, ouctar);
            
            
            
            
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("update successfully.");
            }
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     
       
    }
    public void deleteall(){
          String URL=".\\resources\\EHE081.accdb";
       String databaseURL = "jdbc:ucanaccess://"+URL;
      
      try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
             
            String sql = "delete * from Formulas ";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            
            
            
            
            
             
            int row = preparedStatement.executeUpdate();
             
            if (row > 0) {
                System.out.println("update successfully.");
            }
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}