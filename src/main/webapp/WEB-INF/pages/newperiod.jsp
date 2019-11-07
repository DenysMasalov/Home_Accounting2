<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <c:url value="/addperiod" var="regUrl" />

    <form action="${regUrl}" method="POST">
        Period:<br/><input type="text" name="period" value="${period}"><br/>
        <input type="submit" />

        <c:if test="${exists ne null}">
            <p>Period already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>
