<%@ page import="java.util.List" %>
<%@ page import="com.example.Person" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table Dynamique en JSP</title>
</head>
<body>

<%
    // Supposons que "peopleList" est une liste d'objets Person dans votre modèle
    List<Person> peopleList = (List<Person>) request.getAttribute("peopleList");
%>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Prénom</th>
            <th>Nom</th>
        </tr>
    </thead>
    <tbody>
        <% for (Person person : peopleList) { %>
            <tr>
                <td><%= person.getId() %></td>
                <td><%= person.getFirstName() %></td>
                <td><%= person.getLastName() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
