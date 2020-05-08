package fis.com.vn.common;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileHandling {
	public static void main(String[] args) {
		File file = new File("/data/abc/xyz/create.txt");
		file.getParentFile().mkdirs();
	}
	@SuppressWarnings("resource")
	public String save(String enCodeFile, String folDer) {
		String nameFileOut = UUID.randomUUID().toString() + "." + getTypeFile();

		try {
			File file = new File(folDer+"create.txt");
			file.getParentFile().mkdirs();
			new FileWriter(file);
			
			byte[] decodedImg = Base64.getDecoder()
                    .decode(enCodeFile.getBytes(StandardCharsets.UTF_8));
			
			Path destinationFile = Paths.get(folDer, nameFileOut);
			Files.write(destinationFile, decodedImg);
			
			return folDer+nameFileOut;
		} catch (Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}

		return "";
	}
	public String getTypeFile() {
		return "jpg";
	}
}
