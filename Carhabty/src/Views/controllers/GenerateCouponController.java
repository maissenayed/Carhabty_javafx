/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Functions.CurrentOffre;
import Services.CouponServices;
import Services.UserServices;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class GenerateCouponController implements Initializable {

    @FXML
    private Label desc;

    @FXML
    void GenerateCouponPDF(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        Document pdfReport = new Document();
        PdfWriter.getInstance(pdfReport, new FileOutputStream("Coupon-Carhabty-2017.pdf"));
        pdfReport.open();

        Image img = Image.getInstance("src/Image/header.png");
        img.scaleToFit(500f, 750f);
        pdfReport.add(img);

  
        UserServices userService = new UserServices();
        String NomOffre = "Offre : " + CurrentOffre.Currento.getNomOffre();
        String nom = "Nom : " + Session.actualUser.getNom();
        String prenom = "Prenom : " + Session.actualUser.getPrenom();
        String prix = "Prix : " + (CurrentOffre.Currento.getPrix() - ((CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100))+" DT";
        String partenaire = "Partenaire : " + userService.findById(CurrentOffre.Currento.getUser().getId()).getNomSociete();
        String adresse = "Adresse : " + userService.findById(CurrentOffre.Currento.getUser().getId()).getAdresse();

       
        Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
        pdfReport.add(new Paragraph(nom,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph(prenom,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph(NomOffre,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph(prix,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph(partenaire,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph(adresse,titleFont));
        pdfReport.add(Chunk.NEWLINE);
        CouponServices cs = new CouponServices();   
        

        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(cs.getLastCoupon().getReference(), 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        pdfReport.add(codeQrImage);
        pdfReport.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        desc.setText("CONDITIONS DU L'OFFRE\n"
                + "Lisez-moi :\n"
                + "- Le coupon est valable pour une seule personne\n"
                + "-La garantie Satisfait ou Remboursé de Carhabty est un service exclusif que nous offrons à nos clients pour les préserver de toute déconvenue.");

    }

}
