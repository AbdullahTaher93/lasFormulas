/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.auth;



/**
 *
 * @author Abdullah_PC
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Abdullah_PC
 */
public final class connctionWithFirebase {

String json="";
public connctionWithFirebase(String email,String password, String acount) throws MalformedURLException, ProtocolException, IOException, ParseException{
        String str="";
        String urlParameters  = "email="+email+"&password="+password;
        byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );

        URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyBgSSx85haRsF5eAhr_R3ac2ckjEIrYgDc");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
        conn.setRequestProperty("charset", "utf-8");
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write( postData );
        
        
         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((str = in.readLine()) != null)   //reading data
                    json=json+str; //process the response and save it in some string or so
                Tojson();
                in.close();


}
    
    
    
 public String Tojson() throws ParseException{
            
            JSONParser parser = new JSONParser();
            JSONObject jjson = (JSONObject) parser.parse(json);
            if (!"{}".equals(json))
                 return jjson.get("idToken").toString();
            else
                return "";
           
        }   
}

