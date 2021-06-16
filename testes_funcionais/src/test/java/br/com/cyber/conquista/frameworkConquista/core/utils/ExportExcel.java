package br.com.cyber.conquista.frameworkConquista.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExportExcel {
	private static String base = System.getProperty("user.dir");
	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;
	private static File myFile;
	private static FileInputStream fileInput;

	public void criarevidencia() {

		if (!new File(caminhoarquivo()).exists()) {
			create();
			populate();
		} else {
			populate();
		}
	}

	public static void create() {
		int cellNum = 0;
		myFile = new File(caminhoarquivo());
		;
		workBook = new XSSFWorkbook();
		sheet = workBook.createSheet("Principal");
		Row row = sheet.createRow(0);
		Object[] header = { 
				"cpf", 
				"nome", 
				"nascimento", 
				"email", 
				"celular",				
				"cep",
				"numero_residencia",
				};

		for (Object item : header) {
			Cell cell = row.createCell(cellNum++);

			if (item instanceof String) {
				cell.setCellValue((String) item);

			} else if (item instanceof Boolean) {
				cell.setCellValue((Boolean) item);

			} else if (item instanceof Date) {
				cell.setCellValue((Date) item);

			} else if (item instanceof Double) {
				cell.setCellValue((Double) item);
			}
		}
		save();
		close();
	}

	public static void populate() {
		open();
		int rowNum = sheet.getLastRowNum();
		int cellNum = 0;
		Row row = sheet.createRow(++rowNum);
		Object[] rowToAdd = { 
				AtributosExcel.getCpf(),
				AtributosExcel.getNome(),
				AtributosExcel.getNascimento(), 
				AtributosExcel.getEmail(),
				AtributosExcel.getCelular(),
				AtributosExcel.getCep(),
				AtributosExcel.getNumeroResidencia(),
		};
		
		for (Object item : rowToAdd) {
			System.out.println(item);
			Cell cell = row.createCell(cellNum++);

			if (item instanceof String) {
				cell.setCellValue((String) item);

			} else if (item instanceof Boolean) {
				cell.setCellValue((Boolean) item);

			} else if (item instanceof Date) {
				cell.setCellValue((Date) item);

			} else if (item instanceof Double) {
				cell.setCellValue((Double) item);
			}
		}
		save();
		close();
	}

	public static void open() {
		try {
			myFile = new File(caminhoarquivo());
			fileInput = new FileInputStream(myFile);
			workBook = new XSSFWorkbook(fileInput);
			sheet = workBook.getSheetAt(0);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void close() {
		try {
			workBook.close();
			fileInput.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(myFile);
			workBook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	private static String caminhoarquivo() {
		return System.getProperty("user.dir") + File.separator + 
				"src" + File.separator + 
				"test" + File.separator +
				"resources" + File.separator + 
				"planilhas" + File.separator + 
				"DadosInserido.xlsx";
	}

}
