<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>
<body>
<div align="center">
    <p>Click to go back: <a href="/">back</a></p>
    <button type="button" id="add_period">Add</button>
    <button type="button" id="delete_period">Delete</button>
    <table border="1">
        <c:forEach items="${periods}" var="period">
            <tr>
                <td><input type="checkbox" name="toDelete" value="${period.id}" id="check_${period.id}"></td>
                <td><c:out value="${period.period}"/></td>
                <td><c:out value="${period.state}"/></td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>

<script>
    $('#add_period').click(function(){
        window.location.href = "/newperiod";
    });

    $('#delete_period').click(function(){
        var data = { 'toDelete' : []};
        $(":checked").each(function() {
            data['toDelete'].push($(this).val());
        });
        $.post("/delete", data, function(data, status) {
            window.location.reload();
        });
    });
</script>

</body>
</html>
