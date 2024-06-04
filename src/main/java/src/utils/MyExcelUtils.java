package src.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import src.entity.Student;

public class MyExcelUtils {

	private static Integer totalRows = 0;
	private static Integer totalCells = 0;

	/**
	 * 获取excel表格的数据
	 * 
	 * @param inputStream 从Part中获取到的文件输入流，里面存储了文件信息，用来创建Workbook对象
	 * @param fileName    文件名，用来判断文件的类型
	 * @return 返回User对象的List集合
	 */
	public static List<Student> getExcelData(InputStream inputStream, String fileName) {
		List<Student> users = null;

		try {
			// 保险
			if (!validFileIsExcel(fileName)) {
				return null;
			}
			// 创建Workbook对象获取excel表格数据
			users = createWorkbook(inputStream, isExcel2007(fileName));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return users;
	}

	private static List<Student> createWorkbook(InputStream inputStream, boolean excel2007) {
		// TODO Auto-generated method stub
		List<Student> users = null;

		try {
			if (excel2007) {
				// 2007版本
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				// 获取数据
				users = readWoekbookData(workbook);
			} else {
				// 2003版本
				HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
				users = readWoekbookData(workbook);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return users;
	}

	private static List<Student> readWoekbookData(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		List<Student> users = new ArrayList<Student>();
		Student user = null;
		try {
			// 获取sheet表格，我们这里只用到了sheet1，如果sheet2,3有数据，遍历就是了
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 获取总共的行数
			totalRows = sheet.getLastRowNum();
			// 开始遍历获取数据，从1开始是因为第一行是表头（id, name, password）无数据
			for(int r = 1; r <= totalRows; r++) {
				 user = new Student();
				// 获取每一行
				HSSFRow row = sheet.getRow(r);
				// 每行的总列数
				totalCells = row.getPhysicalNumberOfCells();
				for (int c = 0; c < totalCells; c++) {
					// 获取行的每列(每列都是3行是可知的，下标从0开始)
					HSSFCell cell = row.getCell(c);
					switch (c) {
					case 0: {
						Integer id = (int) cell.getNumericCellValue();
						user.setId(id);
						break;
					}
					case 1: {
						String name = cell.getStringCellValue();
						user.setName(name);
						break;
					}
					case 2: {
						String sex= cell.getStringCellValue();
						user.setSex(sex);
						break;
					}
					case 3: {
						Integer age = (int) cell.getNumericCellValue();
						user.setAge(age);
						break;
					}
					case 4: {
						String grade = cell.getStringCellValue();
						user.setGrade(grade);
						break;
					}
					case 5: {
						float score = (float) cell.getNumericCellValue();
						user.setScore(score);
						break;
					}
					case 6: {
						String picpath = cell.getStringCellValue();
						user.setGrade(picpath);
						break;
					}
					default:
						break;
					}
				}
				// 打印查看信息
				System.out.println(user.getId() + " " + user.getName() + " " + user.getSex() +" "+user.getAge()+" "+user.getGrade()+""+user.getScore());
				// 将用户信息加入User的List集合
				users.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return users;
	}

	private static List<Student> readWoekbookData(XSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		List<Student> users = new ArrayList<Student>();
		Student user = null;
		try {
			// 获取sheet表格，我们这里只用到了sheet1，如果sheet2,3有数据，遍历就是了
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 获取总共的行数
			totalRows = sheet.getLastRowNum();
			// 开始遍历获取数据，从1开始是因为第一行是表头（id, name, password）无数据
			for(int r = 1; r <= totalRows; r++) {
				user = new Student();
				// 获取每一行
				XSSFRow row = sheet.getRow(r);
				// 每行的总列数
				totalCells = row.getPhysicalNumberOfCells();
				for (int c = 0; c < totalCells; c++) {
					// 获取行的每列(每列都是3行是可知的，下标从0开始)
					XSSFCell cell = row.getCell(c);
					switch (c) {
					case 0: {
						Integer id = (int) cell.getNumericCellValue();
						user.setId(id);
						break;
					}
					case 1: {
						String name = cell.getStringCellValue();
						user.setName(name);
						break;
					}
					case 2: {
						String sex= cell.getStringCellValue();
						user.setSex(sex);
						break;
					}
					case 3: {
						Integer age = (int) cell.getNumericCellValue();
						user.setAge(age);
						break;
					}
					case 4: {
						String grade = cell.getStringCellValue();
						user.setGrade(grade);
						break;
					}
					case 5: {
						float score = (float) cell.getNumericCellValue();
						user.setScore(score);
						break;
					}
					case 6: {
						String picpath = cell.getStringCellValue();
						user.setGrade(picpath);
						break;
					}
					default:
						break;
					}
					
					
				}
				// 打印查看信息
				System.out.println(user.getId() + " " + user.getName() + " " + user.getSex() +" "+user.getAge()+" "+user.getGrade()+""+user.getScore());
				// 将用户信息加入User的List集合
				users.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return users;
	}

	/**
	 * 判断文件的格式是否是excel格式
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean validFileIsExcel(String fileName) {

		if (isExcel2003(fileName) || isExcel2007(fileName)) {
			return true;
		}

		return false;
	}

	private static boolean isExcel2007(String fileName) {
		// 版本2007+的后缀名
		String type = ".xlsx";
		// 文件的后缀名
		String fileType = fileName.substring(fileName.lastIndexOf("."));

		if (type.equalsIgnoreCase(fileType)) {
			return true;
		}

		return false;
	}

	private static boolean isExcel2003(String fileName) {
		// 版本2003+的后缀名
		String type = ".xls";
		// 文件的后缀名
		String fileType = fileName.substring(fileName.lastIndexOf("."));

		if (type.equalsIgnoreCase(fileType)) {
			return true;
		}

		return false;
	}

}
