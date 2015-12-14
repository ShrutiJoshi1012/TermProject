package org.sjsu.cmpe.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();

	    if("/login".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request,response);
	    }

	    if("/signup".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/signup.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/dashboard".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
			dispatcher.forward(request,response);
	    }
	    if("/createProject".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/createProject.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/listProject".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/listProject.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/updateProject".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/updateProject.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/addTask".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/addTask.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/listTask".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/listTask.jsp");
			dispatcher.forward(request,response);
	    }
	    
	    if("/updateTask".equalsIgnoreCase(servletPath))
	    {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/updateTask.jsp");
			dispatcher.forward(request,response);
	    }
	}

}
