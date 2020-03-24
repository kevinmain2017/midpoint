package fis.com.vn.Test;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import org.apache.commons.io.IOUtils;
import vn.com.fis.esigncloud.eSignDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.ServerError;

public class Test2 {
	final public static String agreementUUID = "202003042228"; //
    final public static String pwd = "25585901";

    final public static String FILE_XML = "test.xml";
    final public static String FILE_PDF = "license.pdf";

    final public static String FILE_PDF_01 = "test01.pdf";
    final public static String FILE_PDF_02 = "test02.pdf";


    public static void main(String[] args) throws Exception {
//        byte[] xmlBinary = IOUtils.toByteArray(new FileInputStream("C:\\Users\\vdc\\Downloads\\file\\test.xml"));
//        String xmlData = new String(xmlBinary, "UTF-8");
//        byte[] signed = eSignDemo.signXmlUsingPassCode(agreementUUID, xmlData, pwd, "C:\\Users\\vdc\\Downloads\\file\\eSignCloud.p12");
//        IOUtils.write(signed, new FileOutputStream(new File("C:\\Users\\vdc\\Downloads\\file\\xml_signed.xml")));
//
//        // sign file pdf
//        byte[] pdfBinary = IOUtils.toByteArray(new FileInputStream("C:\\Users\\vdc\\Downloads\\file\\test.pdf"));
//        byte[] signedPdf = eSignDemo.signPdfUsingPassCode(agreementUUID, pdfBinary, pwd, "C:\\Users\\vdc\\Downloads\\file\\eSignCloud.p12");
//        IOUtils.write(signedPdf, new FileOutputStream(new File("C:\\Users\\vdc\\Downloads\\file\\pdf_signed.pdf")));

        // Face ID ;
        String imageBase64 = OCRParser.encodeFileToBase64Binary(new File("C:\\Users\\vdc\\Downloads\\imgpsh_fullsize_anim.jpg"));
        System.out.println(imageBase64);
        System.err.println(FaceID.faceRecognition(imageBase64));
    }
}
