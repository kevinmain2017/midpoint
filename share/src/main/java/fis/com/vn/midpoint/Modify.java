package fis.com.vn.midpoint;

import lombok.Data;

@Data
public class Modify {
	String modificationType = "replace";
	String path;
	String value;
}
