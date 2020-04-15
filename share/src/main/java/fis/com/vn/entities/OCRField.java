package fis.com.vn.entities;

import lombok.Data;

@Data
public class OCRField {
	String name;
	String id;
	String dob;
	String sex;
	String ethnicity;
	String home;
	String address;
	String religion;
	
	String userOid;
	String typeCode;
	String face;
	String issue_date;
	String issue_location;
	
	public OCRField createExample() {
		OCRField ocrField = new OCRField();
		ocrField.setEthnicity("KINH");
		ocrField.setSex("NAM");
		ocrField.setName("BÙI ĐỨC THIỆN");
		ocrField.setDob("27/01/1990");
		ocrField.setAddress("THÁI HỒNG, HUYỆN THÁI THỤY, THÁI BÌNH");
		ocrField.setHome("XÃ THÁI HỒNG, HUYỆN THÁI THỤY, THÁI BÌNH");
		ocrField.setId("151954047");
		ocrField.setReligion("KHÔNG");
		
		return ocrField;
	}
	
	public boolean compare(OCRField oCRField) {
		if(oCRField.getEthnicity().equals(ethnicity)
				&& oCRField.getSex().equals(sex)
				&& oCRField.getName().equals(name)
				&& oCRField.getDob().equals(dob)
				&& oCRField.getAddress().equals(address)
				&& oCRField.getHome().equals(home)
				&& oCRField.getId().equals(id)
				&& oCRField.getReligion().equals(religion)
				) {
			return true;
		}
		
		return true;
	}
}
