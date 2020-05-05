package entities;

import lombok.Data;

@Data
public class ResponeAcs {
	String message;
	int status;
	String description;
	int type_anonymous;
	String description_anonymous;
	Result result;
}
