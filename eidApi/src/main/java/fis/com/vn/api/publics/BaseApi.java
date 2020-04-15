package fis.com.vn.api.publics;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
public class BaseApi {
	public String getStringParams(Map<String, String> allParams, String nameParam) {
		if (StringUtils.isEmpty(allParams.get(nameParam))) {
			return null;
		}
		return allParams.get(nameParam);
	}

	public Long getLongParams(Map<String, String> allParams, String nameParam) {
		if (StringUtils.isEmpty(allParams.get(nameParam))) {
			return null;
		}
		return Long.valueOf(allParams.get(nameParam));
	}

	public Integer getIntParams(Map<String, String> allParams, String nameParam) {
		if (StringUtils.isEmpty(allParams.get(nameParam))) {
			return null;
		}
		return Integer.valueOf(allParams.get(nameParam));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	<T> void updateMapToObject(Map<String, String> params, T source, Class cls) throws JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Object overrideObj = mapper.convertValue(params, cls);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.updateValue(source, overrideObj);
	}

	<T> void updateObjectToObject(T source, T objectEdit) throws JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.updateValue(source, objectEdit);
	}
}
