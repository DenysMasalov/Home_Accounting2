<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
    <div align="center">
        <h1>Your login is: ${login}, your roles are:</h1>
        <c:forEach var="s" items="${roles}">
            <h3><c:out value="${s}" /></h3>
        </c:forEach>

        <c:if test="${admin}">
            <c:url value="/admin" var="adminUrl" />
            <p><a href="${adminUrl}">Click</a> for admin page</p>
        </c:if>

        <c:url value="/period" var="periodUrl" />
        <p><a href="${periodUrl}">Click</a> for periods</p>

        <c:url value="/plangains" var="periodUrl" />
        <p><a href="${periodUrl}">Click</a> for plan gains</p>
        <c:url value="/planexpenses" var="periodUrl" />
        <p><a href="${periodUrl}">Click</a> for plan expenses</p>
        <c:url value="/homeaccounting" var="periodUrl" />
        <p><a href="${periodUrl}">Click</a> for home accounting</p>
        <c:url value="/accountinghistory" var="periodUrl" />
        <p><a href="${periodUrl}">Click</a> for accounting history</p>


     <!--   <c:url value="/update" var="updateUrl" />
        <form action="${updateUrl}" method="POST">
            E-mail:<br/><input type="text" name="email" value="${email}" /><br/>
            Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
            <input type="submit" value="Update" />
        </form> -->

        <c:url value="/logout" var="logoutUrl" />
        <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
    </div>
</body>
</html>
