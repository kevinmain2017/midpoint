package fis.com.vn.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import com.fis.ocr.OCRParser;

public class Test {
	public static void main(String[] args) throws Exception {
		File file1 = new File("C:\\Users\\chinhvd4\\Pictures\\matsau.jpg");
        File file2 = new File("C:\\Users\\chinhvd4\\Pictures\\mattruoc.jpg");
        byte[] fileContent1 = null;
        byte[] fileContent2 = null;
        try {
        	String encodstring1 = encodeFileToBase64Binary(file1);
        	System.out.println(encodstring1);
            String encodstring2 = encodeFileToBase64Binary(file2);
            System.out.println(encodstring2);
            long s = System.currentTimeMillis();
            OCRParser parser = new OCRParser();
            System.err.println(parser.parsingToJson(encodstring2, encodstring1));
            System.err.println(System.currentTimeMillis() - s + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }
}
