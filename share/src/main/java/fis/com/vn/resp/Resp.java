package fis.com.vn.resp;

import lombok.Data;

@Data
public class Resp {
	Object data;
	int statusCode;
	String msg;
	Object included;
}
