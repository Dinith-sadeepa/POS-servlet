package webapp;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    private String loginStylePath;

    public LogIn() {
        loginStylePath = "<link rel=\'stylesheet\' type=\'text/css\' href=\'/src/webapp/css/loginStyles.css\'>";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getRequestDispatcher("/web/dashboard").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
            writer.write("<head><title>POS-Login</title><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"></head>");
            writer.write("<body style=\"background-color: #F6FAFB \">");
//                writer.write("<div style= \" position: absolute; width: 550px; height: 400px; left: 0; right: 0; top: 0; bottom: 0; margin: auto; background-color: white; box-shadow: 5px 5px #aaaaaa \"");
                    writer.write("<h1 style=\" margin: 20px; text-align: center;\">Good to see you again!</h1>");
                    writer.write("<hr>");
                    writer.write("<div class=\"card\" style= \" position: absolute; width: 400px; height: 250px; left: 0; right: 0; top: 0; bottom: 0; margin: auto; background-color: white; box-shadow: 5px 5px #aaaaaa \">");
                        writer.write("<form style=\" margin: 20px 10px;\" action=\"/\" method=\"post\">");
                            writer.write("<div class=\"form-group\">");
                            writer.write("<label for= \" username \">User Name</label>");
                            writer.write("<input type= \"text\" class=\"form-control\" name= \" username \">");
                            writer.write("</div>");
                            writer.write("<div class=\"form-group\">");
                            writer.write("<label for= \" password \">Password</label>");
                            writer.write("<input type= \"password\" class=\"form-control\" name= \" password \">");
                            writer.write("</div>");
                            writer.write("<input type= \"submit\" class= \" btn btn-primary \" value= \" Let's roll! \">");
                        writer.write("</form>");
                    writer.write("</div>");
//                writer.write("</div>");
            writer.write("</body>");
        writer.write("</html>");
    }
}
