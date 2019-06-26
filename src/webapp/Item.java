package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Item")
public class Item extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head><title>POS-Item</title>" +
                "<link rel=\"stylesheet\" " +
                "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" " +
                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" " +
                "crossorigin=\"anonymous\"></head>");
        writer.write("<body style=\"background-color: #F6FAFB \">");
        writer.write("<h1 style=\"text-align: center;\">Item Form</h1>");
        writer.write("<br>");
        writer.write("<div class=\"row align-middle\">");
        writer.write("<div class=\"col-md-1\"></div>");
        writer.write("<form class=\"col-md-4\">\n" +
                "  <div class=\"form-row\">\n" +
                "    <div class=\"form-group col-md-4\">\n" +
                "      <label for=\"itemCode\">Item Code</label>\n" +
                "      <input type=\"text\" class=\"form-control\" id=\"itemCode\" placeholder=\"Code\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group col-md-8\">\n" +
                "      <label for=\"itemName\">Item Name</label>\n" +
                "      <input type=\"text\" class=\"form-control\" id=\"itemName\" " +
                "       placeholder=\"Name\">\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"itemQty\">Item Qty</label>\n" +
                "    <input type=\"text\" class=\"form-control\" id=\"itemQty\" " +
                "       placeholder=\"Qty\">\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"itemPrice\">Item Price</label>\n" +
                "    <input type=\"text\" class=\"form-control\" id=\"itemPrice\" " +
                "       placeholder=\"Price\">\n" +
                "  </div>\n" +
                "<div class=\"row\">"+
                "<div class=\"col-md-4\">"+
                "  <button type=\"submit\" class=\"btn btn-primary\">Create Item</button>\n" +
                "</div>"+
                "<div class=\"col-md-4\">"+
                "  <button type=\"submit\" class=\"btn btn-secondary\">Update Item</button>\n" +
                "</div>"+
                "<div class=\"col-md-4\">"+
                "  <button type=\"submit\" class=\"btn btn-warning\">Delete Item</button>\n" +
                "</div>"+
                "</div>"+
                "</form>");
                writer.write("</div>");
        writer.write("<div class=\"col-md-4\">");
        writer.write("<table class=\"table\">\n" +
                "  <thead class=\"thead-dark\">\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Code</th>\n" +
                "      <th scope=\"col\">Name</th>\n" +
                "      <th scope=\"col\">Qty</th>\n" +
                "      <th scope=\"col\">Price</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">1</th>\n" +
                "      <td>Mark</td>\n" +
                "      <td>Otto</td>\n" +
                "      <td>123</td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>");
        writer.write("</div>");
        writer.write("</div>");
        writer.write("</body>");
        writer.write("</html>");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
