package com.railway.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railway.constant.UserRole;
import com.railway.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/userhome")
public class UserHome extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Welcome" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'>User Home</p1></div>");
		pw.println("<div class='tab'>Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Good to See You here.<br/> Here you can Check up the train "
				+  "</div>");

	}

}
