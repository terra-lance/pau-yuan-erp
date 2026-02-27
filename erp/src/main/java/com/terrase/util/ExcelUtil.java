package com.terrase.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static LinkedHashMap<String, List<String[]>> loadExcelFromFile(String filePath) throws Exception {
		if (FilenameUtils.getExtension(filePath).equalsIgnoreCase("xls")) {
			return loadXlsFromFile(filePath);
		} else if (FilenameUtils.getExtension(filePath).equalsIgnoreCase("xlsx")) {
			return loadXlsxFromFile(filePath);
		} else {
			return null;
		}
	}

	public static LinkedHashMap<String, List<String[]>> loadXlsFromFile(String filePath) throws Exception {
		LinkedHashMap<String, List<String[]>> result = new LinkedHashMap<>();
		DataFormatter dataFormatter = new DataFormatter();
		try (FileInputStream excelFile = new FileInputStream(new File(filePath))) {
			try (Workbook workbook = new HSSFWorkbook(excelFile)) {
				Iterator<Sheet> iterator = workbook.sheetIterator();
				Sheet sheet = null;
				while (iterator.hasNext()) {
					List<String[]> sheetResult = new ArrayList<String[]>();

					sheet = iterator.next();

					if (workbook.isSheetHidden(workbook.getSheetIndex(sheet.getSheetName()))) {
						continue;
					}

					int lastCellNum = 0;

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							if (lastCellNum < row.getLastCellNum())
								lastCellNum = row.getLastCellNum();
						}
					}

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							List<String> line = new ArrayList<String>();
							for (int j = 0; j <= lastCellNum; j++) {
								Cell cell = row.getCell(j);

								if (cell == null) {
									line.add("");
								} else {
									line.add(dataFormatter.formatCellValue(cell));
								}
							}

							sheetResult.add(Arrays.copyOf(line.toArray(), line.toArray().length, String[].class));
						}
					}

					result.put(sheet.getSheetName(), sheetResult);
				}
			}
		}

		return result;
	}

	public static LinkedHashMap<String, List<String[]>> loadXlsxFromFile(String filePath) throws Exception {
		LinkedHashMap<String, List<String[]>> result = new LinkedHashMap<>();
		DataFormatter dataFormatter = new DataFormatter();
		try (FileInputStream excelFile = new FileInputStream(new File(filePath))) {
			try (Workbook workbook = new XSSFWorkbook(excelFile)) {
				Iterator<Sheet> iterator = workbook.sheetIterator();
				Sheet sheet = null;
				while (iterator.hasNext()) {
					List<String[]> sheetResult = new ArrayList<String[]>();

					sheet = iterator.next();

					if (workbook.isSheetHidden(workbook.getSheetIndex(sheet.getSheetName()))) {
						continue;
					}

					int lastCellNum = 0;

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							if (lastCellNum < row.getLastCellNum())
								lastCellNum = row.getLastCellNum();
						}
					}

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							List<String> line = new ArrayList<String>();
							for (int j = 0; j <= lastCellNum; j++) {
								Cell cell = row.getCell(j);

								if (cell == null) {
									line.add("");
								} else {
									line.add(dataFormatter.formatCellValue(cell));
								}
							}

							sheetResult.add(Arrays.copyOf(line.toArray(), line.toArray().length, String[].class));
						}
					}

					result.put(sheet.getSheetName(), sheetResult);
				}
			}
		}

		return result;
	}

	public static LinkedHashMap<String, List<String[]>> loadExcelFromFile(File file) throws Exception {
		if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("xls")) {
			return loadXlsFromFile(file);
		} else if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("xlsx")) {
			return loadXlsxFromFile(file);
		} else {
			return null;
		}
	}

	public static LinkedHashMap<String, List<String[]>> loadXlsFromFile(File file) throws Exception {
		LinkedHashMap<String, List<String[]>> result = new LinkedHashMap<>();
		DataFormatter dataFormatter = new DataFormatter();
		try (FileInputStream excelFile = new FileInputStream(file)) {
			try (Workbook workbook = new HSSFWorkbook(excelFile)) {
				Sheet sheet = null;
				Iterator<Sheet> iterator = workbook.sheetIterator();
				while (iterator.hasNext()) {
					List<String[]> sheetResult = new ArrayList<String[]>();

					sheet = iterator.next();

					if (workbook.isSheetHidden(workbook.getSheetIndex(sheet.getSheetName()))) {
						continue;
					}

					int lastCellNum = 0;

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							if (lastCellNum < row.getLastCellNum())
								lastCellNum = row.getLastCellNum();
						}
					}

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							List<String> line = new ArrayList<String>();
							for (int j = 0; j <= lastCellNum; j++) {
								Cell cell = row.getCell(j);

								if (cell == null) {
									line.add("");
								} else {
									line.add(dataFormatter.formatCellValue(cell));
								}
							}

							sheetResult.add(Arrays.copyOf(line.toArray(), line.toArray().length, String[].class));
						}
					}

					result.put(sheet.getSheetName(), sheetResult);
				}
			}
		}

		return result;
	}

	public static LinkedHashMap<String, List<String[]>> loadXlsxFromFile(File file) throws Exception {
		LinkedHashMap<String, List<String[]>> result = new LinkedHashMap<>();
		DataFormatter dataFormatter = new DataFormatter();
		try (FileInputStream excelFile = new FileInputStream(file)) {
			try (Workbook workbook = new XSSFWorkbook(excelFile)) {
				Sheet sheet = null;
				Iterator<Sheet> iterator = workbook.sheetIterator();
				while (iterator.hasNext()) {
					List<String[]> sheetResult = new ArrayList<String[]>();

					sheet = iterator.next();

					if (workbook.isSheetHidden(workbook.getSheetIndex(sheet.getSheetName()))) {
						continue;
					}

					int lastCellNum = 0;

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							if (lastCellNum < row.getLastCellNum())
								lastCellNum = row.getLastCellNum();
						}
					}

					for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);

						if (row == null) {
							continue;
						} else {
							List<String> line = new ArrayList<String>();
							for (int j = 0; j <= lastCellNum; j++) {
								Cell cell = row.getCell(j);

								if (cell == null) {
									line.add("");
								} else {
									line.add(dataFormatter.formatCellValue(cell));
								}
							}

							sheetResult.add(Arrays.copyOf(line.toArray(), line.toArray().length, String[].class));
						}
					}

					result.put(sheet.getSheetName(), sheetResult);
				}
			}
		}

		return result;
	}

	public static void writeString(Sheet sheet, int rowIndex, int cellIndex, String value) {
		Row row = sheet.getRow(rowIndex);

		if (row == null) {
			row = sheet.createRow(rowIndex);
		}

		Cell cell = row.getCell(cellIndex);

		if (cell == null) {
			cell = row.createCell(cellIndex);
		}

		cell.setCellValue(value);
	}

	public static void writeBigDecimal(Sheet sheet, int rowIndex, int cellIndex, BigDecimal value) {
		Row row = sheet.getRow(rowIndex);

		if (row == null) {
			row = sheet.createRow(rowIndex);
		}

		Cell cell = row.getCell(cellIndex);

		if (cell == null) {
			cell = row.createCell(cellIndex);
		}

		cell.setCellValue(value.doubleValue());
	}
}
