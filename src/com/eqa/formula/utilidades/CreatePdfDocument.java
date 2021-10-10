/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.utilidades;

/**
 *
 * @author Abdullah_PC
 */

import com.eqa.formula.DataBase.ConnectionWithFirebase;
import com.eqa.formula.DataBase.FormulasInfo;
import com.eqa.formula.DataBase.GetEHE;
import com.eqa.formula.DataBase.GetPlantas;
import static com.eqa.formula.utilidades.HeaderFooterPageEvent.fechadesde;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
import com.lowagie.text.pdf.PdfTable;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Date;
import java.util.Hashtable;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.codec.binary.Base64;
public class CreatePdfDocument {
    
    public CreatePdfDocument(Hashtable<Integer, GetEHE> store,String path,String name,Date fechadesde,String idPlantades,String plantaID) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        String pdfpath=idPlantades+new Date().getTime()+".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfpath));

        // add header and footer
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        HeaderFooterPageEvent.idPlanta=idPlantades;
        HeaderFooterPageEvent.fechadesde=fechadesde;
        writer.setPageEvent(event);
            PdfPCell text = new PdfPCell();
            PdfPCell text1 = new PdfPCell();
            PdfPCell text2 = new PdfPCell();
            PdfPCell text3 = new PdfPCell();
            PdfPCell text4 = new PdfPCell();
            
        // write to document
        document.open();
                PdfPTable pdfTable=new PdfPTable(5);
                pdfTable.setPaddingTop(20);
                pdfTable.setWidths(new int []{1,1,3,1,1});
                text.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 text1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                  text2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                   text3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    text4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                   
                text.addElement(new Phrase("NO", new Font(Font.FontFamily.HELVETICA, 10)));
                
                
                pdfTable.addCell(text);
                text1.addElement(new Phrase("GRUPO", new Font(Font.FontFamily.HELVETICA, 10)));
               pdfTable.addCell(text1);
                text2.addElement(new Phrase("FORMULA", new Font(Font.FontFamily.HELVETICA, 10)));
                pdfTable.addCell(text2);
                text3.addElement(new Phrase("CEM KG", new Font(Font.FontFamily.HELVETICA, 10)));
              pdfTable.addCell(text3);
                text4.addElement(new Phrase("REL a/c", new Font(Font.FontFamily.HELVETICA, 10)));
                pdfTable.addCell(text4);
                int j=0;
                int k=1;
                do{
                for(int i=0;i<store.size();i++){
                    
                    if(j==store.get(i).getGrupo()&&!store.get(i).isOuctar()){
                   
                    int no=k;
                    int GRUPO=store.get(i).getGrupo();
                    String FORMULA=store.get(i).gettodo();
                    double CEM=store.get(i).getCm();
                    double a_c=store.get(i).geta_c();
                    pdfTable.addCell(no+"");
                    pdfTable.addCell(GRUPO+"");
                    pdfTable.addCell(FORMULA+"");
                    pdfTable.addCell(CEM+"");
                    pdfTable.addCell(a_c+"");
                     k++;
                    }
                    
                     
                }
                j++;
              
                }while(j<8);
                
                
                
                
                 String URL=".\\resources\\EQAfirma.jpg";
            // add image
                Image firma = Image.getInstance(URL);
                firma.setAbsolutePosition(450f, 60f);
                firma.scaleToFit(100f, 100f);
                if(fechadesde==null)
                    fechadesde=new Date();
                PdfPCell fecha=new PdfPCell();
                
               
               document.add(fecha);
               document.add(pdfTable);
               if(!path.equals("")){
                    Image firma2 = Image.getInstance(path);
                    firma2.setAbsolutePosition(350f, 60f);
                    firma2.scaleToFit(100f, 100f);
                     document.add(firma2);
               }
               document.add(firma);
               
               
        document.close();
       
        
        byte[] inFileBytes = Files.readAllBytes(Paths.get(pdfpath));
        byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(inFileBytes);
        String encodedString =  new String(encoded);
        
        
        
        for(int i=0;i<encoded.length;i++)
        System.out.print(encoded[i]+" ");
        GetPlantas getPlantas=new GetPlantas(plantaID);
        FormulasInfo formulasInfo=new FormulasInfo();
        formulasInfo.setIdPlanta(plantaID);
        formulasInfo.setDesdeFechadeValid(fechadesde.getTime());
        formulasInfo.setHastaFechadeValid(DateUtils.addMonths(fechadesde, 2).getTime());
        formulasInfo.setPDF(encodedString);
        formulasInfo.setVersion((getPlantas.getVersion()+1)+"");
        formulasInfo.setStore(store);
        System.out.println(formulasInfo.getVersion());
        new ConnectionWithFirebase(formulasInfo.toJson(),"SubirFormulasinfo");
        
        
        //new ConnectionWithFirebase(store);
        try {
        File myFile = new File(pdfpath);
        Desktop.getDesktop().open(myFile);
      
        
    } catch (IOException ex) {
        // no application registered for PDFs
    }
    }

    
}
