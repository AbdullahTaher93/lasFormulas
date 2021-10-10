/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.DataBase;

import com.eqa.formula.utilidades.UsersGetPropertyValues;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Abdullah_PC
 */
public class ConnectionWithFirebase {
   
    String  Result="";

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
    
   public  ConnectionWithFirebase(Hashtable<Integer, GetEHE> store) throws FileNotFoundException, IOException{
       UsersGetPropertyValues properties = new UsersGetPropertyValues();
      Properties prop=properties.getPropValues();
      String plataFormaFirebase=prop.getProperty("plataFormaFirebase","https://us-central1-plataformaeqa-test.cloudfunctions.net/");
       
       String query_url = plataFormaFirebase+"/apps/SubirFormulas";
       for(int i=0;i<store.size();i++){
       //store.get(i).setBytes(encoded);
       Post_JSON(store.get(i).toJson(),query_url);
       }
          
          
//          storageClient.bucket().create(blobString, testFile , Bucket.BlobWriteOption.userProject("plataformaeqa-test"));
           
   }
   public ConnectionWithFirebase(String json,String postFunction) throws IOException{
        UsersGetPropertyValues properties = new UsersGetPropertyValues();
      Properties prop=properties.getPropValues();
      String plataFormaFirebase=prop.getProperty("plataFormaFirebase","https://us-central1-plataformaeqa-test.cloudfunctions.net/");
       
       String query_url = plataFormaFirebase+"apps/"+postFunction;
        Post_JSON(json,query_url);
   }

    private void Post_JSON(String json, String query_url) throws IOException {
            
        
        
        
            try {
           URL url = new URL(query_url);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setConnectTimeout(5000);
           conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
           conn.setDoOutput(true);
           conn.setDoInput(true);
           conn.setRequestMethod("POST");

           OutputStream os = conn.getOutputStream();
           os.write(json.getBytes("UTF-8"));
           os.close(); 
             
           
           
           
           
           
           
           // read the response
           InputStream in = new BufferedInputStream(conn.getInputStream());
           String result = IOUtils.toString(in, "UTF-8");
           System.out.println(result);
                setResult(result);
           in.close();
           conn.disconnect();

           
	
           } catch (Exception e) {
   			System.out.println(e);
   		}
	
	}
}