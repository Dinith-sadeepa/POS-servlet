package webapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import webapp.BO.CustomerBO;
import webapp.BO.ItemBO;
import webapp.BO.OrdersBO;
import webapp.BO.impl.CustomerBOImpl;
import webapp.BO.impl.ItemBOImpl;
import webapp.BO.impl.OrdersBOImpl;
import webapp.dto.CustomerDTO;
import webapp.dto.ItemDTO;
import webapp.dto.OrderDetailsDTO;
import webapp.dto.OrdersDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Orders")
public class Orders extends HttpServlet {

    private OrdersBO ordersBO;
    private CustomerBO customerBO;
    private ItemBO itemBO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        String orders = request.getParameter("order");
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) new JSONParser().parse(orders);
            jsonArray.forEach(order -> {
                JSONObject jsonObject = null;
                try {
                    jsonObject = (JSONObject) new JSONParser().parse(order.toString());
                    orderDetailsDTOS.add(new OrderDetailsDTO(request.getParameter("orderId"),
                            ((String)jsonObject.get("code")),((Long)jsonObject.get("qty")),((Long)jsonObject.get("price"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                System.out.println(orderDetailsDTOS);
            });
            boolean isPlaced = ordersBO.placeOrder(new OrdersDTO(request.getParameter("orderId"),
                    request.getParameter("orderDate"), new CustomerDTO(request.getParameter("cusId")), orderDetailsDTOS));

            JSONObject jsonObjectRes = new JSONObject();
            if (isPlaced) {
                jsonObjectRes.put("isPlaced", true);
                try {
                    response.getWriter().write(jsonObjectRes.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                jsonObjectRes.put("isPlaced", false);
                try {
                    response.getWriter().write(jsonObjectRes.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        boolean placed = ordersBO.placeOrder(new OrdersDTO(request.getParameter("orderId"),
//                        request.getParameter("orderDate"), new CustomerDTO(request.getParameter("cusId"))),
//                request.getParameter("orders"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CustomerDTO> allCustomers = customerBO.getAllCustomers();
        List<ItemDTO> allItems = itemBO.getAllItems();

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head><title>POS-Orders</title>" +
                "<link rel=\"stylesheet\" " +
                "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" " +
                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" " +
                "crossorigin=\"anonymous\"></head>");
        writer.write("<body style=\"background-color: #F6FAFB \">");
        writer.write("<h1 style=\"text-align: center;\">Place Order Form</h1>");
        writer.write("<br>");
        writer.write("<div class=\"row align-middle\">");
        writer.write("<div class=\"col-md-1\"></div>");
        writer.write("<div class=\"col-md-1\"></div>");
        writer.write("<form class=\"col-md-6\" id=\"orderForm\">\n" +
                "  <div class=\"form-row\">\n" +
                "    <div class=\"form-group col-md-4\">\n" +
                "      <label for=\"cusId\">Customer Id</label>\n" +
                "      <select class=\"form-control\" name=\"cusId\" id=\"cusId\" placeholder=\"Id\">" +
                "<option value=\"\">Select customer id</option> ");
        allCustomers.forEach(customerDTO -> {
            writer.write("<option value=\"" + customerDTO.getCusId() + "\"> " + customerDTO.getCusId() + " </option>");
        });
        writer.write("</select>\n" +
                "    </div>\n" +
                "    <div class=\"form-group col-md-3\">\n" +
                "      <label for=\"orderId\">Order Id</label>\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"orderId\" " +
                "       placeholder=\"Order Id\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group col-md-5\">\n" +
                "      <label for=\"orderDate\">Order Date</label>\n" +
                "      <input type=\"date\" class=\"form-control\" name=\"orderDate\" " +
                "       placeholder=\"Order Date\">\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"form-row\">\n" +
                "  <div class=\"form-group col-md-3\">\n" +
                "    <label for=\"itemCode\">Item Code</label>\n" +
                "    <select class=\"form-control\" name=\"itemCode\" id=\"itemCode\" placeholder=\"Item Code\">" +
                " <option value=\"\">Select item code</option> ");
        allItems.forEach(itemDTO -> {
            writer.write("<option value=\"" + itemDTO.getItemCode() + "," + itemDTO.getItemQty() + "," + itemDTO.getItemPrice() + "\"> " + itemDTO.getItemCode() + " </option>");
        });
        writer.write("</select>\n" +
                "  </div>\n" +
                "  <div class=\"form-group col-md-2\">\n" +
                "    <label for=\"itemQty\">Item Qty</label>\n" +
                "    <input type=\"text\" class=\"form-control\" name=\"itemQty\" id=\"itemQty\" " +
                "       placeholder=\"Item Qty\">\n" +
                "  </div>\n" +
                "  <div class=\"form-group col-md-3\">\n" +
                "    <label for=\"itemUnitPrice\">Item Unit Price</label>\n" +
                "    <input type=\"text\" class=\"form-control\" name=\"itemUnitPrice\" id=\"itemUnitPrice\" " +
                "       placeholder=\"Item Unit Price\">\n" +
                "  </div>\n" +
                "  </div>\n" +
                "<div class=\"row\">" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"addToListButton\" class=\"btn btn-secondary\">Add to List</button>\n" +
                "</div>" +
                "</div>" +
                "</form>");
        writer.write("<br>");
        writer.write("</div>");
        writer.write("<div class=\"row\">");
        writer.write("<div class=\"col-md-2\"></div>");
        writer.write("<table class=\"table col-md-7\" >\n" +
                "  <thead class=\"thead-dark\">\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Item Code</th>\n" +
                "      <th scope=\"col\">Item Qty</th>\n" +
                "      <th scope=\"col-2\">Item Price</th>\n" +
                "      <th scope=\"col-2\">Total</th>\n" +
                "    </tr>\n" +
                "  </thead>\n");
        writer.write("<tbody id=\"orderTable\">");
        writer.write("</tbody>");
        writer.write("</table>");
        writer.write("</div>");
        writer.write("<div class=\"row\">");
        writer.write("<div class=\"col-md-8 text-center blockquote\">" +
                "    <label id=\"total\">Total amount</label>\n" +
                "</div>");
        writer.write("<div class=\"col-md-2\">" +
                "  <button type=\"button\" id= \"placeOrder\" class=\"btn btn-primary\">Place order</button>\n" +
                "</div>" +
                "</div>");
        writer.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
        writer.write("<script>");
        writer.write("$(\"#itemCode\").change(function() {\n" +
                "    let valueString = this.value;\n" +
                "   var res = valueString.split(\",\");" +
                "   $(\"#itemQty\").val(res[1]);" +
                "   $(\"#itemUnitPrice\").val(res[2]);" +
                "});");
        writer.write("</script>");
        writer.write("<script>" +
                "var orders = [];");
        writer.write("$(\'#addToListButton\').click(function () {" +
                "$(\'#orderTable\').empty();" +
                "let code = $(\'#itemCode\').val().split(\",\")[0];" +
                "let qty = $(\'#itemQty\').val();" +
                "let price = $(\'#itemUnitPrice\').val();" +
                "if(orders.length > 0){" +
                "   var added = false;" +
                "for(var i=0; i<orders.length; i++){" +
                "  if(orders[i].code == code){" +
                "       orders[i].qty = (parseInt(orders[i].qty) + parseInt(qty));" +
//                "       orders[i].price = (parseFloat(orders[i].price) + parseFloat(price));" +
                "       orders[i].total = (parseInt(orders[i].qty) * parseFloat(orders[i].price));" +
                "       added = true;" +
                "   }" +
                "}" +
                "if(!added){" +
                "var order = { code: code, qty: parseInt(qty), price: parseFloat(price), total : (parseFloat(price) * parseInt(qty))  }; " +
                "orders.push(order); " +
                "}" +
                "}else{" +
                "var order = { code: code, qty: parseInt(qty), price: parseFloat(price), total : (parseFloat(price) * parseInt(qty))  }; " +
                "orders.push(order); " +
                "}" +
                "var total = 0;" +
                "for(var i=0; i<orders.length; i++){" +
                "total = (parseFloat(total) + parseFloat(orders[i].total));" +
                "$(\'#orderTable\').append(\'<tr><td>\'+orders[i].code+\'</td><td>\'+orders[i].qty+\'</td><td>\'+orders[i].price+\'</td><td>\'+orders[i].total+\'</td></tr>\');" +
                "}" +
                "console.log(total);" +
                "$(\'#total\').html(\"Total Price is : \" +total + \"/=\");" +
                "});");
        writer.write("$(\'#placeOrder\').click(function () {let orderForm = $(\'#orderForm\').serialize();" +
                "var jsonData = JSON.stringify(orders);" +
                "$.ajax({\n" +
                "        url: \"/web/orders\",\n" +
                "        method: \"POST\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: orderForm + \"&order=\" + jsonData,\n" +
                "        success: function(data){\n" +
                "           if(data.isPlaced){" +
                "               alert(\'Order placed!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to place order!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "});");
//        writer.write("$(\'#deleteCus\').click(function () {" +
//                "$.ajax({\n" +
//                "        url: \"/web/customer\",\n" +
//                "        method: \"DELETE\",\n" +
//                "        async: true,\n" +
//                "        dataType: \"json\",\n" +
//                "        data: $(\'#cusId\').val(),\n" +
//                "        success: function(data){\n" +
//                "           if(data.isDeleted){" +
//                "               alert(\'Customer deleted!\');" +
//                "               location.reload();" +
//                "           }else{" +
//                "              alert(\'Unable to delete customer!\');" +
//                "           }\n" +
//                "        }" +
//                "    });" +
//                "});");
        writer.write("</script>");
        writer.write("</body>");
        writer.write("</html>");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ordersBO = new OrdersBOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerBO = new CustomerBOImpl();
        itemBO = new ItemBOImpl();
    }

}
