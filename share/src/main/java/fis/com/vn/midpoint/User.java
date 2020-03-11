package fis.com.vn.midpoint;

import lombok.Data;

@Data
public class User {
	String oid;
	String name;
	Assignment assignment;
	Activation activation;
	String fullName;
	String givenName;
	String familyName;
	String title;
	Credentials credentials;
}
