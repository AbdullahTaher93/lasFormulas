/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.DataBase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.FirebaseApp;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Abdullah_PC
 */
public class uploadPDF {
    public uploadPDF() throws FileNotFoundException, IOException{
     //private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS);   
    FileInputStream serviceAccount = new FileInputStream(".\\resources\\serviceAccountKey.json");
    System.out.print(serviceAccount.getFD());
    FirebaseOptions options = FirebaseOptions.builder()
    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    .setStorageBucket("plataformaeqa-test.appspot.com")
    .build();
    FirebaseApp.initializeApp(options);
    Storage storage = StorageOptions.getDefaultInstance().getService();
    
    Bucket bucket = StorageClient.getInstance().bucket("plataformaeqa-test.appspot.com");
    FileInputStream testFile = new FileInputStream(".\\resources\\arriba.png");
    String blobString = "PDfs/" + "arriba.png";
    Blob blob = bucket.create(blobString, testFile, "image/png", 
                Bucket.BlobWriteOption.userProject("plataformaeqa-test"));

    
}
}
