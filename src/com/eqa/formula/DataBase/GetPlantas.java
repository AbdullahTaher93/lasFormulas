/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.DataBase;

/**
 *
 * @author Abdullah_PC
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import com.eqa.formula.utilidades.UsersGetPropertyValues;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author Abdullah_PC
 */
public class GetPlantas {
    int version;
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
   public GetPlantas() throws MalformedURLException, ProtocolException, IOException{
                    UsersGetPropertyValues getPropertyValues=new UsersGetPropertyValues();
                    Properties prop=getPropertyValues.getPropValues();
                    String plataFormaFirebase=prop.getProperty("plataFormaFirebase","plataformaeqa-test");
         URL url = new URL("https://us-central1-"+plataFormaFirebase+".cloudfunctions.net/user/Plantas");
         HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
         conn.setDoOutput(true);
         conn.setInstanceFollowRedirects(false);
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
         conn.setRequestProperty("charset", "utf-8");
         conn.setUseCaches(false);
         
         String str;
         String s = "";
         try{
             
                     BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                           while ((str = in.readLine()) != null){ 
                              
                                //reading data
                               s=s+str;
                                
                           } //process the response and save it in some string or so
                             //
                           
                             //JSONObject jjson = (JSONObject) XML.toJSONObject(json);
            JSONParser parser = new JSONParser();
            //JSONObject jjson = (JSONObject) parser.parse(json);
            //return  jjson.toJSONString();
            
            
            //JSONParser parser = new JSONParser();
                    Object obj  = parser.parse(s);
                    JSONArray array = new JSONArray();
                    array.put(obj);
                   
                    
             try (FileWriter myWriter = new FileWriter(".\\resources\\IdPlanta.json")) {
                 myWriter.write(array.get(0).toString());
                 /* //
                  */
             }
}catch(Exception ex){
                                System.out.println(ex);
                            }
                    

    }
   
   
     public GetPlantas(String json) throws IOException{
        UsersGetPropertyValues properties = new UsersGetPropertyValues();
      Properties prop=properties.getPropValues();
      String plataFormaFirebase=prop.getProperty("plataFormaFirebase","https://us-central1-plataformaeqa-test.cloudfunctions.net/");
       
       String query_url = plataFormaFirebase+"/apps/GetVersion";
        Post_JSON( json,query_url);
   }

    private void Post_JSON(String json, String query_url) {
        String urlParameters  = "idPlanta="+json;
        byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
         try {
           URL url = new URL(query_url);
           HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write( postData );
             String str;
             String v="";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((str = in.readLine()) != null)   //reading data
                    v=v+str; //process the response and save it in some string or so
                
                in.close();
           
           this.setVersion(Integer.parseInt(v));
           
           
           
           // read the response
           

           
	
           } catch (Exception e) {
   			System.out.println(e);
   		} //To change body of generated methods, choose Tools | Templates.
    }
   
}

