

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Chat extends HttpServlet {

	String message[];
	int index=0, size=10;
	
	public void init() {
		message = new String[size];
		for(int i = 0; i < size; i++) {
			message[i] = "";
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("utf-8");
		String first = req.getParameter("first");
		
		if(first != null) {
			String uid = req.getParameter("uid");
			synchronized(message) {
			   message[index] = "<p style='text-align:center;color:red;'>"+ uid + "님이 입장 하셨습니다.</p>";
			   index = (index + 1) % size;
			}
		}
		
		String html = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"ko\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv='pragma' content='no-cache'>\r\n"
				+ "    <meta http-equiv='cache-control' content='no-cache'>\r\n"
				+ "    <meta http-equiv='refresh' content='2; URL=Chat'>\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>chat</title>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n";
		out.println(html);
		out.println("<h2 class='text-center'>채팅방</h2>");
		int i = index;
		for(String msgs : message) {
		   out.println(msgs);
		   out.println("<br>");
		   i = ++i % size;
		   if(i == index -1) break;
		   if(index == 0 && i == size - 1) break;
		}
		//out.print(message[i]);
		out.println("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String msg = request.getParameter("msg");
		if(msg != null && msg.trim().length() != 0) {
			synchronized(message) {
			  message[index] = uid + " : " + msg;
			  index = (index + 1) % size;
			}
		}
		doGet(request, response);
	}

}
