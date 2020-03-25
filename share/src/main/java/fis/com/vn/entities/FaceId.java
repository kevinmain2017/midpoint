package fis.com.vn.entities;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FaceId {
	String message;
	ArrayList<FaceIdInfo> body;
}
