<%@ page import="com.test.model.Item" %>

<html>
<body>

<h2>Item Details</h2>

<form action="details" method="post">
    Enter Item Id:
    <input type="text" name="id">
    <input type="submit" value="Search">
</form>

<br>
${msg}
<%
Item item = (Item) request.getAttribute("item");
if(item != null){
%>
<br>
Id: <%= item.getId() %><br>
Name: <%= item.getName() %><br>
Price: <%= item.getPrice() %><br>
Type: <%= item.getType() %>
<%
}
%>

</body>
</html>