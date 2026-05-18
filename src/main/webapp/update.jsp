<html>
<body>

<h2>Update Item</h2>

<form action="processUpdate" method="post">
    Id (to update): <input type="text" name="id"><br><br>
    New Name: <input type="text" name="name"><br><br>
    New Price: <input type="text" name="price"><br><br>
    New Type: <input type="text" name="type"><br><br>

    <input type="submit" value="Update">
</form>

<br>
${msg}

</body>
</html>