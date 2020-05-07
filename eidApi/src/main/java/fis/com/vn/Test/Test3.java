package fis.com.vn.Test;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.midpoint.JsonUserImage;
import fis.com.vn.midpoint.JsonUserType;
import fis.com.vn.midpoint.UserImage;
import fis.com.vn.midpoint.UserType;
import fis.com.vn.request.Params;

public class Test3 {
	public static void main(String[] args) throws Exception {
		JsonUserType jsonUserType = new JsonUserType();
		UserType userType = new UserType();
		userType.setTypeCode("ca");
		userType.setUserOid("21231231231231");
		
		jsonUserType.setUserTypeType(userType);
		
		System.out.println(new Gson().toJson(jsonUserType));
		
		JsonUserImage jsonUserImage = new JsonUserImage();
		UserImage userImage = new UserImage();
		userImage.setImage("ca");
		userImage.setUserOid("21231231231231");
		
		jsonUserImage.setUserImage(userImage);
		
		System.out.println(new Gson().toJson(jsonUserImage));
	}
}
