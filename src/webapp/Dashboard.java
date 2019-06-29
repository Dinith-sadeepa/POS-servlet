package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head><title>POS-Dashboard</title>" +
                "<link rel=\"stylesheet\" " +
                "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" " +
                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" " +
                "crossorigin=\"anonymous\"></head>");
        writer.write("<body style=\"background-color: #F6FAFB \">");
            writer.write("<h1 class=\" text-center  \">Dashboard</h1>");
            writer.write("<br>");
    //        writer.write("<div class=\"row\"></div>");
            writer.write("<div class=\"row\">\n" +
                    "  <div class=\"col-sm-3\">");
            writer.write("<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <img src=\"https://projectriskcoach.com/wp-content/uploads/2015/01/Dollarphotoclub_72963857.jpg\"" +
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">Manage Customers</h5>\n" +
                    "    <p class=\"card-text\">Let's create, delete, update and see your customers</p>\n" +
                    "    <a href=\"/web/customer\" class=\"btn btn-primary\">Take me to customers..</a>\n" +
                    "  </div>\n" +
                    "</div>");
            writer.write("</div>");
            writer.write("<div class=\"col-sm-3\">");
            writer.write("<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <img src=\"https://newageprinting.com/wp-content/uploads/2013/03/promo.jpg\" " +
                    "class=\"card-img-top\" alt=\"item\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">Manage Items</h5>\n" +
                    "    <p class=\"card-text\">Let's create, delete, update and see your items</p>\n" +
                    "    <a href=\"/web/item\" class=\"btn btn-primary\">Take me to items..</a>\n" +
                    "  </div>\n" +
                    "</div>");
            writer.write("</div>");
            writer.write("<div class=\"col-sm-3\">");
            writer.write("<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <img src=\"https://www.hashmicro.com/blog/wp-content/uploads/2018/08/Purchase-" +
                    "Orders-All-You-Need-to-Know-About-Them-870x522.jpg\" class=\"card-img-top\" alt=\"order\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">Place Order</h5>\n" +
                    "    <p class=\"card-text\">Place a order</p>\n" +
                    "    <a href=\"/web/orders\" class=\"btn btn-primary\">Place order now!</a>\n" +
                    "  </div>\n" +
                    "</div>");
            writer.write("</div>");
            writer.write("</div>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
