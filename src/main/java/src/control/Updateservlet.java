package src.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.entity.Student;
import src.model.StudentModel;

public class Updateservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Updateservlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       this.doPost(request, response);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("gbk");
		 		
	 			// ???????????????ID
			    String id = request.getParameter("id");
			if (null==id)
			{
				request.setAttribute("error", "??��??????");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				
				return ;
			}
			Integer studentId = Integer.valueOf(id);
			// ???��??????????????????
     		StudentModel model = new StudentModel();

			Student student = model.load(studentId);
			if (null==student)
			{
				request.setAttribute("error", "????????????????");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return ;
			}
			// ?????????????��request??
			request.setAttribute("student", student);
			// ?????student.jsp
			request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
	
	}

}
