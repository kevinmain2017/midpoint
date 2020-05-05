package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

public class Test {
	static String folderPath = "D:\\CHINH\\anh\\anhcmt";

	public static void main(String[] args) throws Exception {
//		final File folder = new File(folderPath);
//		listFilesForFolder(folder);

		ArrayList<String> arr = new ArrayList<String>();

		File myObj = new File("info.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			arr.add(data);
		}
		myReader.close();
		exportXslt(arr);
	}

	public static void listFilesForFolder(final File folder) throws Exception {

		FaceID.init(
				"D:\\CHINH\\project\\midpoint\\testIcomm\\src\\main\\java\\test\\lbpcascade_frontalface_improved.xml");
		StringBuilder stringBuilder = new StringBuilder();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String[] arr = fileEntry.getName().toLowerCase().split("_");
				if (arr[2].split("\\.")[0].equals("mt")) {

					String info = readFileExel(arr[0]);
					OCRParser parser = new OCRParser();

					File file1 = new File(folderPath + "\\" + fileEntry.getName());
					File file2 = new File(
							folderPath + "\\" + arr[0] + "_" + arr[1] + "_" + arr[2].replaceAll("mt.", "ms."));

					String encodstring1 = encodeFileToBase64Binary(file1);

					String encodstring2 = encodeFileToBase64Binary(file2);

					String jsonOcr = parser.parsing(encodstring1, encodstring2);

					OCRField ocrField = new Gson().fromJson(jsonOcr, OCRField.class);

					NoiDungOCR noiDungOCR = new NoiDungOCR();
					noiDungOCR.convert(ocrField);

//			        System.out.println(new Gson().toJson(noiDungOCR));
					if (info != null) {
						System.out.println(fileEntry.getName() + "|" + info);
						stringBuilder.append(fileEntry.getName());
						stringBuilder.append("|");
						stringBuilder.append(info);
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getDanToc());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getGioiTinh());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getHoVaTen());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getNamSinh());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getNgayCap());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getNoiCap());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getNoiTru());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getQueQuan());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getSoCmt());
						stringBuilder.append("|");
						stringBuilder.append(noiDungOCR.getTonGiao());
						stringBuilder.append("\n");
					}
				}
			}
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter("info.txt"));
		writer.write(stringBuilder.toString());

		writer.close();
	}

	public static String readFileExel(String username) {
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Users\\vdc\\Downloads\\mail.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			ArrayList<String> arr = new ArrayList<String>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				String info = "";
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 2 || cell.getColumnIndex() == 5 || cell.getColumnIndex() == 6) {
						String value = null;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue()).replaceAll("@fpt.com.vn", "")
									.toLowerCase();
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue().replaceAll("@fpt.com.vn", "").toLowerCase();
							break;
						}

						info += value + "|";
					}
				}
				arr.add(info);

			}
			for (String str : arr) {
				String[] arr1 = str.split("\\|");
				if (username.toLowerCase().equals(arr1[0])) {
					return str;
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username + "|||";
	}

	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		return new String(Base64.getEncoder().encode(bytes));
	}

	public static void exportXslt(ArrayList<String> arr) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Tên file", "username", "email", "số đt", "Dân tộc", "Giới tính", "họ và tên",
				"Năm sinh", "Ngày cấp", "Nơi cấp", "Nơi trú", "Quê quán", "Số giấy tờ", "Tôn giáo" });

		int i = 2;
		for (String str : arr) {
			String[] arr1 = str.split("\\|");
			data.put(i + "", new Object[] { arr1[0], arr1[1], arr1[2], arr1[3], arr1[5], arr1[6], arr1[7], arr1[8],
					arr1[9], arr1[10], arr1[11], arr1[12], arr1[13], arr1[14] });
			i++;
		}

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
