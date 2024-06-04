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
		// 设置编码
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/json;charset=gbk");
		// 获取Part文件上传对象
		Part part = request.getPart("excelFile");

		// 获取PrintWriter对象，可以在网页显示提示信息
		PrintWriter printWriter = response.getWriter();

		if (part != null) {
			// 获取文件头信息
			String fileHeader = part.getHeader("Content-Disposition");
			// 文件名
			String fileName = fileHeader.substring(fileHeader.lastIndexOf("=") + 2, fileHeader.length() - 1);
			// 获取输入流
			InputStream inputStream = part.getInputStream();
			
			if(!"".equals(fileName)) {
				if(MyExcelUtils.validFileIsExcel(fileName)) {
					List<Student> studentsusers = MyExcelUtils.getExcelData(inputStream, fileName);
					// 将用户信息逐一添加到数据库
					StudentModel model = new StudentModel();
			 	    
					String msg = "";
					for (Student stuser : studentsusers) {
						int isSuccess = model.insert(stuser.getId(),stuser.getName(), stuser.getSex(), stuser.getAge(), stuser.getGrade(), stuser.getScore());
						if (isSuccess > 0) {
							msg += "<span style='color: green;'>ID "+ stuser.getId() + " 的学生 " + stuser.getName() + " 添加成功</span><br/>";
						}else {
							msg += "<span style='color: red;'>ID "+ stuser.getId() + " 的学生 " + stuser.getName() + " 已经存在</span><br/>";
						}
					}
					request.setAttribute("msg", msg);
					part.delete();
					response.sendRedirect("ListStudentServlet.do");
					
				}else {
					printWriter.write("<h1 style='color: red'>文件格式不支持，仅支持excel表格文件</h1>");
				}
			}else {
				printWriter.write("<h1 style='color: red'>文件已损坏或不存在</h1>");
			}
			
		} else {
			printWriter.write("<h1 style='color: red'>未获取到文件</h1>");
		}
		// 删除缓存的文件,释放资源
		part.delete();
		printWriter.flush();
		printWriter.close();

		
		 
	}
	

}
