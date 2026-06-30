<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>

<body>

<h1>

Welcome

<%= session.getAttribute("username") %>

</h1>

<br>

<a href="LogoutServlet">

Logout

</a>

</body>
</html>