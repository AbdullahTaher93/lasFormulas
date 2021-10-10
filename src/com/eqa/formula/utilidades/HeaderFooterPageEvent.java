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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private PdfTemplate t;
    private Image total;
    
    static String idPlanta;
    static Date fechadesde;
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        addFooter(writer);
    }

    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaHasta = DateUtils.addMonths(fechadesde, 2);
        
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(1500);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(10);
            header.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            header.getDefaultCell().setBorderColor(BaseColor.WHITE);
            String URL=".\\resources\\EQAheader.jpg";
            // add image
            Image logo = Image.getInstance(URL);
            
           
            header.addCell(logo);

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            
            text.setBorder(Rectangle.NO_BORDER);
            text.setBorderColor(BaseColor.WHITE);
            text.addElement(new Phrase("LISTADO DE FORMULAS APROBADAS", new Font(Font.FontFamily.HELVETICA, 16)));
            text.addElement(new Phrase(String.format("Planta: "+idPlanta, writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));
            text.addElement(new Phrase(String.format("Valid desde : "+df.format(fechadesde)+" hasta : "+df.format(fechaHasta), writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));
            // text.addElement(new Phrase(String.format(, writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);
            
            

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    private void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(4);
        try {
            // set defaults
            footer.setWidths(new int[]{2, 0, 2,1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            //footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            // add copyright
            footer.addCell(new Phrase("\u00A9 https://eqalaboratorios.com", new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD)));
            footer.addCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 8, Font.DEFAULTSIZE)));
            footer.addCell(new Phrase("Note: Este certificado tiene validez de 2 mes a contar a partir de la fecha de firma", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC)));
                
            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));
            footer.addCell(new Phrase("Fecha: "+df.format(new Date()) , new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL)));
 
// add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}
