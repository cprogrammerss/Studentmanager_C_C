package src.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import src.entity.Student;
import src.model.StudentModel;
import src.utils.MyExcelUtils;

/**
 * Servlet implementation class uploadServlet
 */
@MultipartConfig
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ���ñ���
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/json;charset=gbk");
		// ��ȡPart�ļ��ϴ�����
		Part part = request.getPart("excelFile");

		// ��ȡPrintWriter���󣬿�������ҳ��ʾ��ʾ��Ϣ
		PrintWriter printWriter = response.getWriter();

		if (part != null) {
			// ��ȡ�ļ�ͷ��Ϣ
			String fileHeader = part.getHeader("Content-Disposition");
			// �ļ���
			String fileName = fileHeader.substring(fileHeader.lastIndexOf("=") + 2, fileHeader.length() - 1);
			// ��ȡ������
			InputStream inputStream = part.getInputStream();
			
			if(!"".equals(fileName)) {
				if(MyExcelUtils.validFileIsExcel(fileName)) {
					List<Student> studentsusers = MyExcelUtils.getExcelData(inputStream, fileName);
					// ���û���Ϣ��һ��ӵ����ݿ�
					StudentModel model = new StudentModel();
			 	    
					String msg = "";
					for (Student stuser : studentsusers) {
						int isSuccess = model.insert(stuser.getId(),stuser.getName(), stuser.getSex(), stuser.getAge(), stuser.getGrade(), stuser.getScore());
						if (isSuccess > 0) {
							msg += "<span style='color: green;'>ID "+ stuser.getId() + " ��ѧ�� " + stuser.getName() + " ��ӳɹ�</span><br/>";
						}else {
							msg += "<span style='color: red;'>ID "+ stuser.getId() + " ��ѧ�� " + stuser.getName() + " �Ѿ�����</span><br/>";
						}
					}
					request.setAttribute("msg", msg);
					part.delete();
					response.sendRedirect("ListStudentServlet.do");
					
				}else {
					printWriter.write("<h1 style='color: red'>�ļ���ʽ��֧�֣���֧��excel����ļ�</h1>");
				}
			}else {
				printWriter.write("<h1 style='color: red'>�ļ����𻵻򲻴���</h1>");
			}
			
		} else {
			printWriter.write("<h1 style='color: red'>δ��ȡ���ļ�</h1>");
		}
		// ɾ��������ļ�,�ͷ���Դ
		part.delete();
		printWriter.flush();
		printWriter.close();

		
		 
	}
	

}
