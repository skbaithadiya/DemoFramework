package com.AutoMaven.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static String path; //= System.getProperty("user.dir") + "//resource//dataFile.xlsx";
	public FileInputStream excelFileReader;
	public FileOutputStream excelFileWriter;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public Row row;
	public static String[] data = null;
	static final Logger log = Logger.getLogger(ExcelReader.class.getName());

	public FileInputStream setExcelFileReader(String path) throws FileNotFoundException {
		excelFileReader = new FileInputStream(new File(path));
		return excelFileReader;
	}
	
	public ExcelReader(String fullPath){
		path = System.getProperty("user.dir") + fullPath;
	}

	/***
	 * get the cell data from Excel sheet based on Row Number and Column Number
	 * @param colNum column Number of the Cell 
	 * @param rowNumber Row number of the Cell
	 * @return returns the cell data as String Value
	 */
	public String getCellData(int colNum, int rowNumber) {

		String cellData = "";
		cell = sheet.getRow(rowNumber).getCell(colNum);
		if (cell != null) {
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				cellData = cell.getStringCellValue();
			}
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				cellData = String.format("%.0f", cell.getNumericCellValue());
			}
		}
		return cellData;

	}

	/***
	 * this method is to get the Cell Data specifically for Login Page. 
	 * @param sheetName Name of the sheet when Login data need to fetched
	 * @param titleName Name of the Title of the Column (For ex. to get the Username: "Username")
	 * @return returns the cell data as String Value
	 */
	public String excelReaderLoginPage(String sheetName, String titleName) {
		String cellData = null;
		try {
			workbook = new XSSFWorkbook(setExcelFileReader(path));
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 0; i < rowCount + 1; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j) != null && row.getCell(j).getStringCellValue().equalsIgnoreCase(titleName)) {
						cellData = sheet.getRow(i + 1).getCell(j).getStringCellValue();
						break;
					}
				}
				if (cellData != null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("returning:"+ cellData);
		return cellData;
	}

	/***
	 * to get the Column Number based on Column Name which need to be passed as Parameter
	 * @param colName Value which is present in Cell and the one needs to get the number
	 * @return returns the integer value of Column Number
	 */
	public int getColNumber(String colName) {
		int colNum = 0;
		try {
			int i, j = 0;
			int lastRow = sheet.getLastRowNum();
			// get col number
			for (i = 0; i <= lastRow; i++) {
				row = sheet.getRow(i);
				for (j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j).getStringCellValue().isEmpty() || row.getCell(j) != null) {
						// System.out.println(row.getCell(j).getStringCellValue());
						if (row.getCell(j).getStringCellValue().equalsIgnoreCase(colName)) {
							colNum = j;
							break;
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return colNum;
	}

	/***
	 * to get the Row Number based on Testcase name found in the Excel Sheet
	 * @param testCaseName test case name
	 * @return returns the integer value of Row Number
	 */
	public int getRowNumber(String testCaseName) {
		int rowNum = 0;
		int lastRow = sheet.getLastRowNum();
		int i, j = 0;
		// get row no
		for (i = 0; i <= lastRow; i++) {
			row = sheet.getRow(i);
			for (j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j).getStringCellValue().isEmpty() || row.getCell(j) != null) {
					// System.out.println(row.getCell(j).getStringCellValue());
					if (row.getCell(j).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						rowNum = i;
						// System.out.println("col number is: " + i);
						break;
					}
				}
			}
		}
		return rowNum;
	}

	/***
	 * to read the data from the excel sheet
	 * @param sheetName Name of the sheet
	 * @param testCaseName test case name
	 * @param colName column name 
	 * @return returns the String value of Cell Data
	 */
	public String excelDataReader(String sheetName, String testCaseName, String colName) {
		String sValue = "";
		try {
			workbook = new XSSFWorkbook(setExcelFileReader(path));
			sheet = workbook.getSheet(sheetName);
			getColNumber(colName);
			getRowNumber(testCaseName);
			sValue = sheet.getRow(getRowNumber(testCaseName)).getCell(getColNumber(colName)).getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sValue;
	}

	private int getRowCount(String sheetName) throws FileNotFoundException, IOException {

		workbook = new XSSFWorkbook(setExcelFileReader(path));
		sheet = workbook.getSheet(sheetName);
		int RowCount = sheet.getLastRowNum();

		return RowCount + 1;
	}

	private int getColCount(String sheetName) throws FileNotFoundException, IOException {
		workbook = new XSSFWorkbook(setExcelFileReader(path));
		sheet = workbook.getSheet(sheetName);
		int colCount = sheet.getRow(0).getLastCellNum();

		return colCount;
	}

	/***
	 * ToDO need to complete as Array String to get whole row based on testcase and sheetname in parameters
	 * @param sheetName
	 * @param testcase
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Object[][] getData(String sheetName, String testcase) throws FileNotFoundException, IOException {

		int rowNum = getRowCount(testcase);
		int colNum = getColCount(testcase);

		Object sampleData[][] = new Object[rowNum][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j <= colNum; j++) {
				sampleData[i - 2][j] = excelDataReaderViaDataProvider(testcase, j, i);
				System.out.println(sampleData);
			}
		}
		return sampleData;
	}

	@SuppressWarnings("static-access")
	private String excelDataReaderViaDataProvider(String sheetName, int colNum, int rowNum) {
		try {

			workbook = new XSSFWorkbook(setExcelFileReader(path));
			sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(colNum - 1);

			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/***
	 * to set the value in Excel Sheet. it will set the testcase result after execution
	 * @param sheetName Name of the sheet
	 * @param testCaseName test case name
	 * @param colName Column Name
	 * @param result result of testcase execution for ex. "Passed|Failed"
	 */
	public void excelDataWriter(String sheetName, String testCaseName, String colName, String result) {
		try {
			workbook = new XSSFWorkbook(setExcelFileReader(path));
			sheet = workbook.getSheet(sheetName);
			int colNum = getColNumber(colName);
			int rowNum = getRowNumber(testCaseName);
			cell = sheet.getRow(rowNum).getCell(colNum);

			if (cell == null || cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {

				cell = sheet.getRow(rowNum).getCell(colNum, XSSFRow.CREATE_NULL_AS_BLANK);
			} else {

				cell = sheet.getRow(rowNum).getCell(colNum, XSSFRow.RETURN_NULL_AND_BLANK);
			}
			cell.setCellValue(result);
			excelFileWriter = new FileOutputStream(path);
			workbook.write(excelFileWriter);
			excelFileWriter.close();
			log.info("Result ["+result+"] has been set into Excel File");
			log.info("Path of Excel File ["+path+"]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}