package webapp;

import org.json.simple.JSONObject;
import webapp.BO.CustomerBO;
import webapp.BO.impl.CustomerBOImpl;
import webapp.dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Customer")
public class Customer extends HttpServlet {
    private CustomerBO customerBO;

    @Override
    public void init() throws ServletException {
        super.init();
        customerBO = new CustomerBOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean customer = customerBO.createCustomer(new CustomerDTO(request.getParameter("cusId"),
                request.getParameter("cusName"),
                request.getParameter("inputAddress")));
        JSONObject jsonObject = new JSONObject();
        if (customer) {
            jsonObject.put("isCreated", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isCreated", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();
        String[] split = data.split("&");
        HashMap<String,String> stringHashMap = new HashMap<>();

        for(int i=0 ; i< split.length ; i++){
            String[] keyValue = split[i].split("=");
            stringHashMap.put(keyValue[0],keyValue[1]);
        }

        boolean customer = customerBO.updateCustomer(new CustomerDTO(stringHashMap.get("cusId"),
                stringHashMap.get("cusName"),
                stringHashMap.get("inputAddress")));
        JSONObject jsonObject = new JSONObject();
        if (customer) {
            jsonObject.put("isUpdated", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isUpdated", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();
        boolean customer = customerBO.deleteCustomer(data);
        JSONObject jsonObject = new JSONObject();
        if (customer) {
            jsonObject.put("isDeleted", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isDeleted", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<CustomerDTO> allCustomers = customerBO.getAllCustomers();

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
        writer.write("<form class=\"col-md-4\" id=\"customerForm\">\n" +
                "  <div class=\"form-row\">\n" +
                "    <div class=\"form-group col-md-4\">\n" +
                "      <label for=\"cusId\">Customer Id</label>\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"cusId\" id=\"cusId\" placeholder=\"Id\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group col-md-8\">\n" +
                "      <label for=\"cusName\">Customer Name</label>\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"cusName\" " +
                "       placeholder=\"Name\">\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"inputAddress\">Address</label>\n" +
                "    <input type=\"text\" class=\"form-control\" name=\"inputAddress\" " +
                "       placeholder=\"1234 Main St\">\n" +
                "  </div>\n" +
                "<div class=\"row\">" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"createCus\" class=\"btn btn-primary\">Create Customer</button>\n" +
                "</div>" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"updateCus\" class=\"btn btn-secondary\">Update Customer</button>\n" +
                "</div>" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"deleteCus\" class=\"btn btn-warning\">Delete Customer</button>\n" +
                "</div>" +
                "</div>" +
                "</form>");
        writer.write("<div class=\"col-md-4\">");
        writer.write("<table class=\"table\">\n" +
                "  <thead class=\"thead-dark\">\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Id</th>\n" +
                "      <th scope=\"col\">Name</th>\n" +
                "      <th scope=\"col-2\">Address</th>\n" +
                "    </tr>\n" +
                "  </thead>\n");
        writer.write("<tbody>");
        allCustomers.forEach((customerDTO -> {
            writer.write("<tr><td>" + customerDTO.getCusId() + "</td>" +
                    "<td>" + customerDTO.getCusName() + "</td>" +
                    "<td>" + customerDTO.getCusAddress() + "</td></tr>");
        }));
        writer.write("</tbody>");
        writer.write("</table>");
        writer.write("</div>");
        writer.write("</div>");
        writer.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
        writer.write("<script>");
        writer.write("$(\'#createCus\').click(function () {let customerForm = $(\'#customerForm\').serialize();" +
                "$.ajax({\n" +
                "        url: \"/web/customer\",\n" +
                "        method: \"POST\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: customerForm,\n" +
                "        success: function(data){\n" +
                "           if(data.isCreated){" +
                "               alert(\'Customer added!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to add customer!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "});");
        writer.write("$(\'#updateCus\').click(function () {let customerForm = $(\'#customerForm\').serialize();" +
                "$.ajax({\n" +
                "        url: \"/web/customer\",\n" +
                "        method: \"PUT\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: customerForm,\n" +
                "        success: function(data){\n" +
                "           if(data.isUpdated){" +
                "               alert(\'Customer updated!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to update customer!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "});");
        writer.write("$(\'#deleteCus\').click(function () {" +
                "$.ajax({\n" +
                "        url: \"/web/customer\",\n" +
                "        method: \"DELETE\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: $(\'#cusId\').val(),\n" +
                "        success: function(data){\n" +
                "           if(data.isDeleted){" +
                "               alert(\'Customer deleted!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to delete customer!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "});");
        writer.write("</script>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
