package fis.com.vn.vision;

import java.io.File;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.Common;
import fis.com.vn.vision.entities.Ocr;
import fis.com.vn.vision.entities.OcrHoChieu;

public class Test {
	public static void main(String[] args) throws Exception {
		OCRParser ocrParser = new OCRParser();
		
		File file1 = new File("D:\\CHINH\\anh\\anhcmtreal\\HiepDV10_cmt_mt.JPEG");
//        File file2 = new File("D:\\CHINH\\anh\\anhcmtreal\\");

        String encodstring1 = Common.encodeFileToBase64Binary(file1);

//        String encodstring2 = Common.encodeFileToBase64Binary(file2);
		
		Ocr ocr = ocrParser.parsing(encodstring1, null, "cmtnd");
		
		System.out.println(new Gson().toJson(ocr));
	}
}

