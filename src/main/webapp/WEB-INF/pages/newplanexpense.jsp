<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <c:url value="/addplanexpense" var="regUrl" />

    <form action="${regUrl}" method="POST">
        Description:<br/><input type="text" name="description" value="${description}"><br/>
        Value:<br/><input type="text" name="value" value="${value}"><br/>
        <input type="submit" />

        <c:if test="${exists ne null}">
            <p>Period already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>
