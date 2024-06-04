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
	 * ��ȡexcel��������
	 * 
	 * @param inputStream ��Part�л�ȡ�����ļ�������������洢���ļ���Ϣ����������Workbook����
	 * @param fileName    �ļ����������ж��ļ�������
	 * @return ����User�����List����
	 */
	public static List<Student> getExcelData(InputStream inputStream, String fileName) {
		List<Student> users = null;

		try {
			// ����
			if (!validFileIsExcel(fileName)) {
				return null;
			}
			// ����Workbook�����ȡexcel�������
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
				// 2007�汾
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				// ��ȡ����
				users = readWoekbookData(workbook);
			} else {
				// 2003�汾
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
			// ��ȡsheet�����������ֻ�õ���sheet1�����sheet2,3�����ݣ�����������
			HSSFSheet sheet = workbook.getSheetAt(0);
			// ��ȡ�ܹ�������
			totalRows = sheet.getLastRowNum();
			// ��ʼ������ȡ���ݣ���1��ʼ����Ϊ��һ���Ǳ�ͷ��id, name, password��������
			for(int r = 1; r <= totalRows; r++) {
				 user = new Student();
				// ��ȡÿһ��
				HSSFRow row = sheet.getRow(r);
				// ÿ�е�������
				totalCells = row.getPhysicalNumberOfCells();
				for (int c = 0; c < totalCells; c++) {
					// ��ȡ�е�ÿ��(ÿ�ж���3���ǿ�֪�ģ��±��0��ʼ)
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
				// ��ӡ�鿴��Ϣ
				System.out.println(user.getId() + " " + user.getName() + " " + user.getSex() +" "+user.getAge()+" "+user.getGrade()+""+user.getScore());
				// ���û���Ϣ����User��List����
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
			// ��ȡsheet�����������ֻ�õ���sheet1�����sheet2,3�����ݣ�����������
			XSSFSheet sheet = workbook.getSheetAt(0);
			// ��ȡ�ܹ�������
			totalRows = sheet.getLastRowNum();
			// ��ʼ������ȡ���ݣ���1��ʼ����Ϊ��һ���Ǳ�ͷ��id, name, password��������
			for(int r = 1; r <= totalRows; r++) {
				user = new Student();
				// ��ȡÿһ��
				XSSFRow row = sheet.getRow(r);
				// ÿ�е�������
				totalCells = row.getPhysicalNumberOfCells();
				for (int c = 0; c < totalCells; c++) {
					// ��ȡ�е�ÿ��(ÿ�ж���3���ǿ�֪�ģ��±��0��ʼ)
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
				// ��ӡ�鿴��Ϣ
				System.out.println(user.getId() + " " + user.getName() + " " + user.getSex() +" "+user.getAge()+" "+user.getGrade()+""+user.getScore());
				// ���û���Ϣ����User��List����
				users.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return users;
	}

	/**
	 * �ж��ļ��ĸ�ʽ�Ƿ���excel��ʽ
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
		// �汾2007+�ĺ�׺��
		String type = ".xlsx";
		// �ļ��ĺ�׺��
		String fileType = fileName.substring(fileName.lastIndexOf("."));

		if (type.equalsIgnoreCase(fileType)) {
			return true;
		}

		return false;
	}

	private static boolean isExcel2003(String fileName) {
		// �汾2003+�ĺ�׺��
		String type = ".xls";
		// �ļ��ĺ�׺��
		String fileType = fileName.substring(fileName.lastIndexOf("."));

		if (type.equalsIgnoreCase(fileType)) {
			return true;
		}

		return false;
	}

}
