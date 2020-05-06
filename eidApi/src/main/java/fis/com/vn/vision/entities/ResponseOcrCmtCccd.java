package fis.com.vn.vision.entities;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ResponseOcrCmtCccd {
	int errorCode;
	String errorMessage;
	
	ArrayList<OcrCmtCccd> data;
}
