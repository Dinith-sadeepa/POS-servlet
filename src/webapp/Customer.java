package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Customer")
public class Customer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head><title>POS-Customer</title>" +
                "<link rel=\"stylesheet\" " +
                    "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" " +
                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" " +
                "crossorigin=\"anonymous\"></head>");
        writer.write("<body style=\"background-color: #F6FAFB \">");
            writer.write("<h1 style=\"text-align: center;\">Customer Form</h1>");
            writer.write("<br>");
            writer.write("<div class=\"row align-middle\">");
                writer.write("<div class=\"col-md-1\"></div>");
                writer.write("<form class=\"col-md-4\">\n" +
                        "  <div class=\"form-row\">\n" +
                        "    <div class=\"form-group col-md-4\">\n" +
                        "      <label for=\"cusId\">Customer Id</label>\n" +
                        "      <input type=\"text\" class=\"form-control\" id=\"cusId\" placeholder=\"Id\">\n" +
                        "    </div>\n" +
                        "    <div class=\"form-group col-md-8\">\n" +
                        "      <label for=\"cusName\">Customer Name</label>\n" +
                        "      <input type=\"text\" class=\"form-control\" id=\"cusName\" " +
                        "       placeholder=\"Name\">\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "  <div class=\"form-group\">\n" +
                        "    <label for=\"inputAddress\">Address</label>\n" +
                        "    <input type=\"text\" class=\"form-control\" id=\"inputAddress\" " +
                        "       placeholder=\"1234 Main St\">\n" +
                        "  </div>\n" +
                        "<div class=\"row\">"+
                        "<div class=\"col-md-4\">"+
                        "  <button type=\"submit\" class=\"btn btn-primary\">Create Customer</button>\n" +
                        "</div>"+
                        "<div class=\"col-md-4\">"+
                        "  <button type=\"submit\" class=\"btn btn-secondary\">Update Customer</button>\n" +
                        "</div>"+
                        "<div class=\"col-md-4\">"+
                        "  <button type=\"submit\" class=\"btn btn-warning\">Delete Customer</button>\n" +
                        "</div>"+
                        "</div>"+
                        "</form>");
//                writer.write("</div>");
                writer.write("<div class=\"col-md-4\">");
                writer.write("<table class=\"table\">\n" +
                        "  <thead class=\"thead-dark\">\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Id</th>\n" +
                        "      <th scope=\"col\">Name</th>\n" +
                        "      <th scope=\"col-2\">Address</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"row\">1</th>\n" +
                        "      <td>Mark</td>\n" +
                        "      <td>Otto</td>\n" +
                        "    </tr>\n" +
                        "  </tbody>\n" +
                        "</table>");
                writer.write("</div>");
            writer.write("</div>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
