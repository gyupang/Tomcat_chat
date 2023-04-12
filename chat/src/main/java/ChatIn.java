

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatIn extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("utf-8");
		String uid = req.getParameter("uid");
		String first = req.getParameter("first");
		if(uid == null || uid.trim().length() == 0) {
			res.sendRedirect("login.html");
			return;
		}

		String html = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"ko\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>chat</title>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n";
		out.println(html);
		out.println("<iframe src='Chat?first=first&uid="+uid+"' class='iframe' name='main'></iframe>");
		out.println("<form method='post' action='Chat' target='main' onSubmit='return send(this)'>");
		out.println("메시지 : <input type=text size=50 name=temp>");
		out.println("<input type=hidden name=msg>");
		out.println("<input type='hidden' name='uid' value='"+uid+"'>");

		out.println("</form>");
		out.println("<script src='js/script.js'></script>");
		out.println("</body></html>");
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException{
		doGet(req, res);
	}

	
}
