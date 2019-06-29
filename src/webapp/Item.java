package webapp;

import org.json.simple.JSONObject;
import webapp.BO.ItemBO;
import webapp.BO.impl.ItemBOImpl;
import webapp.dto.ItemDTO;

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

@WebServlet(name = "Item")
public class Item extends HttpServlet {

    private ItemBO itemBO;

    @Override
    public void init() throws ServletException {
        super.init();
        itemBO = new ItemBOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        boolean item = itemBO.createItem(new ItemDTO(request.getParameter("itemCode"),
                request.getParameter("itemName"),
                Integer.parseInt(request.getParameter("itemQty")),
                Double.parseDouble(request.getParameter("itemPrice"))));
        JSONObject jsonObject = new JSONObject();
        if (item) {
            jsonObject.put("isCreatedItem", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isCreatedItem", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();
        String[] split = data.split("&");
        HashMap<String, String> stringHashMap = new HashMap<>();

        for (int i = 0; i < split.length; i++) {
            String[] keyValue = split[i].split("=");
            stringHashMap.put(keyValue[0], keyValue[1]);
        }

        boolean item = itemBO.updateItem(new ItemDTO(stringHashMap.get("itemCode"),
                stringHashMap.get("itemName"),
                Integer.parseInt(stringHashMap.get("itemQty")),
                Double.parseDouble(stringHashMap.get("itemPrice"))));
        JSONObject jsonObject = new JSONObject();
        if (item) {
            jsonObject.put("isUpdatedItem", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isUpdatedItem", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();
        boolean item = itemBO.deleteItem(data);
        JSONObject jsonObject = new JSONObject();
        if (item) {
            jsonObject.put("isDeletedItem", true);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("isDeletedItem", false);
            response.getWriter().write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json");
        List<ItemDTO> allItems = itemBO.getAllItems();

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
        writer.write("<form class=\"col-md-4\" id=\"itemForm\">\n" +
                "  <div class=\"form-row\">\n" +
                "    <div class=\"form-group col-md-4\">\n" +
                "      <label for=\"itemCode\">Item Code</label>\n" +
                "      <input type=\"text\" class=\"form-control\" id=\"itemCode\" name=\"itemCode\" " +
                "placeholder=\"Code\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group col-md-8\">\n" +
                "      <label for=\"itemName\">Item Name</label>\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"itemName\" " +
                "       placeholder=\"Name\">\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"itemQty\">Item Qty</label>\n" +
                "    <input type=\"text\" class=\"form-control\" name=\"itemQty\" " +
                "       placeholder=\"Qty\">\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"itemPrice\">Item Price</label>\n" +
                "    <input type=\"text\" class=\"form-control\" name=\"itemPrice\" " +
                "       placeholder=\"Price\">\n" +
                "  </div>\n" +
                "<div class=\"row\">" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"itemCreate\" class=\"btn btn-primary\">Create Item</button>\n" +
                "</div>" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"itemUpdate\" class=\"btn btn-secondary\">Update Item</button>\n" +
                "</div>" +
                "<div class=\"col-md-4\">" +
                "  <button type=\"button\" id= \"itemDelete\" class=\"btn btn-warning\">Delete Item</button>\n" +
                "</div>" +
                "</div>" +
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
                "  </thead>");
        writer.write("<tbody>");
        allItems.forEach((itemDTO -> {
            writer.write("<tr><td>" + itemDTO.getItemCode() + "</td>" +
                    "<td>" + itemDTO.getItemName() + "</td>" +
                    "<td>" + itemDTO.getItemQty() + "</td>" +
                    "<td>" + itemDTO.getItemPrice() + "</td></tr>");
        }));
        writer.write("</tbody></table>");
        writer.write("</div>");
        writer.write("</div>");
        writer.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
        writer.write("<script>");
        writer.write("$(\'#itemCreate\').click(function () {let itemForm = $(\'#itemForm\').serialize();" +
                "var empty = true;\n" +
                "$('input[type=\"text\"]').each(function(){\n" +
                "   if($(this).val()!=\"\"){\n" +
                "      empty =false;\n" +
                "    }\n" +
                " });" +
                "if(!empty){" +
                "$.ajax({\n" +
                "        url: \"/web/item\",\n" +
                "        method: \"POST\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: itemForm,\n" +
                "        success: function(data){\n" +
                "           if(data.isCreatedItem){" +
                "               alert(\'Item added!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to add Item!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "}else{alert(\'should define all fields!\');}" +
                "});");
        writer.write("$(\'#itemUpdate\').click(function () {let itemForm = $(\'#itemForm\').serialize();" +
                "var empty = true;\n" +
                "$('input[type=\"text\"]').each(function(){\n" +
                "   if($(this).val()!=\"\"){\n" +
                "      empty =false;\n" +
                "    }\n" +
                " });" +
                "if(!empty){" +
                "$.ajax({\n" +
                "        url: \"/web/item\",\n" +
                "        method: \"PUT\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: itemForm,\n" +
                "        success: function(data){\n" +
                "           if(data.isUpdatedItem){" +
                "               alert(\'Item updated!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to update Item!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "}else{alert(\'should define all fields!\');}" +
                "});");
        writer.write("$(\'#itemDelete\').click(function () {" +
                "var empty = true;\n" +
                "$('input[type=\"text\"]').each(function(){\n" +
                "   if($(this).val()!=\"\"){\n" +
                "      empty =false;\n" +
                "    }\n" +
                " });" +
                "if(!empty){" +
                "$.ajax({\n" +
                "        url: \"/web/item\",\n" +
                "        method: \"DELETE\",\n" +
                "        async: true,\n" +
                "        dataType: \"json\",\n" +
                "        data: $(\'#itemCode\').val(),\n" +
                "        success: function(data){\n" +
                "           if(data.isDeletedItem){" +
                "               alert(\'Item deleted!\');" +
                "               location.reload();" +
                "           }else{" +
                "              alert(\'Unable to delete Item!\');" +
                "           }\n" +
                "        }" +
                "    });" +
                "}else{alert(\'should define item code!\');}" +
                "});");
        writer.write("</script>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
