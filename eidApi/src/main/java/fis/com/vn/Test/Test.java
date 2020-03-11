package fis.com.vn.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fis.ocr.OCRParser;

public class Test {
	public static void main(String[] args) {
		File file1 = new File("C:\\Users\\vdc\\Pictures\\mattruoc.jpg");
        File file2 = new File("C:\\Users\\vdc\\Pictures\\matsau.jpg");
        byte[] fileContent1 = null;
        byte[] fileContent2 = null;
        try {
            fileContent1 = Files.readAllBytes(file1.toPath());
            fileContent2 = Files.readAllBytes(file2.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long s = System.currentTimeMillis();
        OCRParser parser = new OCRParser();
        System.err.println(parser.parsingToJson(fileContent1, null));
        System.err.println(System.currentTimeMillis() - s + " ms");
	}
}
