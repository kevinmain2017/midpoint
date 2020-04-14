package fis.com.vn.resp;

import lombok.Data;

@Data
public class RespApi {
	Object data;
	int status;
	String message;
	Object included;
}
