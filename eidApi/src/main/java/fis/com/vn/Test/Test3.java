package fis.com.vn.Test;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.request.Params;

public class Test3 {
	public static void main(String[] args) throws Exception {
		try {
			Params params = new Params();
			params.setSoCmt("1234");
			
			Field[] fields = params.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();
				Object value = field.get(params);
				if(!StringUtils.isEmpty(value)) {
					System.out.println(name+"|"+value);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
