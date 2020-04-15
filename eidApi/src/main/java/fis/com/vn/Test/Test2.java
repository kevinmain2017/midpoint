package fis.com.vn.Test;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

import fis.com.vn.entities.OCRField;
import fis.com.vn.response.NoiDungOCR;

import org.apache.commons.io.IOUtils;
import vn.com.fis.esigncloud.eSignDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.ServerError;
import java.util.Base64;

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
//        
//        System.out.println(signed);
//        IOUtils.write(signed, new FileOutputStream(new File("C:\\Users\\vdc\\Downloads\\file\\xml_signed.xml")));

//        // sign file pdf
//        byte[] pdfBinary = IOUtils.toByteArray(new FileInputStream("C:\\Users\\vdc\\Downloads\\file\\test.pdf"));
//        byte[] signedPdf = eSignDemo.signPdfUsingPassCode(agreementUUID, pdfBinary, pwd, "C:\\Users\\vdc\\Downloads\\file\\eSignCloud.p12");
//        IOUtils.write(signedPdf, new FileOutputStream(new File("C:\\Users\\vdc\\Downloads\\file\\pdf_signed.pdf")));
//
//        // Face ID ;
//        String imageBase64 = OCRParser.encodeFileToBase64Binary(new File("C:\\Users\\vdc\\Downloads\\imgpsh_fullsize_anim.jpg"));
//        System.out.println(imageBase64);
//        System.err.println(FaceID.faceRecognition(imageBase64));
		File file1 = new File("C:\\Users\\chinhvd4\\Pictures\\mt.jpg");
        File file2 = new File("C:\\Users\\chinhvd4\\Pictures\\ms.jpg");
        byte[] fileContent1 = null;
        byte[] fileContent2 = null;



        String encodstring1 = encodeFileToBase64Binary(file1);

        String encodstring2 = encodeFileToBase64Binary(file2);
        System.out.println(encodstring1);
        System.out.println(encodstring2);
        
        
		FaceID.init("C:\\Users\\chinhvd4\\Downloads\\lbpcascade_frontalface_improved.xml");
//		String base64Image = OCRParser.encodeFileToBase64Binary(new File("C:\\Users\\chinhvd4\\Pictures\\imgpsh_fullsize.jpg"));
//		System.err.println(FaceID.faceCrop(base64Image));
		
		OCRParser parser = new OCRParser();
		String jsonOcr = parser.parsing(encodstring1, encodstring2);
        
        
        OCRField ocrField = new Gson().fromJson(jsonOcr, OCRField.class);
        
        NoiDungOCR noiDungOCR = new NoiDungOCR();
        noiDungOCR.convert(ocrField);
        
        System.out.println(new Gson().toJson(noiDungOCR));
	}
	private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }
}
