package webapp;

import org.json.simple.JSONObject;
import webapp.BO.LogInBO;
import webapp.BO.impl.LogInBOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    private LogInBO logInBO;

    @Override
    public void init() throws ServletException {
        super.init();
        logInBO = new LogInBOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isCredentials = logInBO.checkCredentials(username, password);
        JSONObject jsonObject = new JSONObject();
        if(isCredentials) {
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", true);
            String urlToRedirect = "/web/dashboard";
            jsonObject.put("url",urlToRedirect);
            response.getWriter().write(jsonObject.toString());
        }else{
            String urlToRedirect = "/";
            jsonObject.put("url",urlToRedirect);
            response.getWriter().write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
            writer.write("<head><title>POS-Login</title><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"></head>");
            writer.write("<body style=\"background-color: #F6FAFB \">");
                    writer.write("<h1 style=\" margin: 20px; text-align: center;\">Good to see you again!</h1>");
                    writer.write("<hr>");
                    writer.write("<div class=\"card\" style= \" position: absolute; width: 400px; height: 250px; left: 0; right: 0; top: 0; bottom: 0; margin: auto; background-color: white; box-shadow: 5px 5px #aaaaaa \">");
                        writer.write("<form style=\" margin: 20px 10px;\" id=\"loginForm\"/*action=\"/\" method=\"post\"*/>");
                            writer.write("<div class=\"form-group\">");
                            writer.write("<label for= \"username\">User Name</label>");
                            writer.write("<input type= \"text\" class=\"form-control\" name= \"username\">");
                            writer.write("</div>");
                            writer.write("<div class=\"form-group\">");
                            writer.write("<label for= \"password\">Password</label>");
                            writer.write("<input type= \"password\" class=\"form-control\" name= \"password\">");
                            writer.write("</div>");
                            writer.write("<input type= \"button\" class= \" btn btn-primary \" id=\"login\" value= \" Let's roll! \">");
                        writer.write("</form>");
                    writer.write("</div>");
                writer.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
                writer.write("<script>");
                writer.write("$(\'#login\').click(function () {let loginForm = $(\'#loginForm\').serialize();" +
                        "$.ajax({\n" +
                        "        url: \"/\",\n" +
                        "        method: \"POST\",\n" +
                        "        async: true,\n" +
                        "        dataType: \"json\",\n" +
                        "        data: loginForm,\n" +
                        "        success: function(data){\n" +
                        "           window.location = data.url;\n" +
                        "        }" +
                        "    });" +
                        "});");
                writer.write("</script>");
            writer.write("</body>");
        writer.write("</html>");
    }
}
