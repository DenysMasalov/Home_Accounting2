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
    <button type="button" id="add_">Add</button>
    <button type="button" id="delete_">Delete</button>
    <table border="1">
        <c:forEach items="${plangains}" var="plangain">
            <tr>
                <td><input type="checkbox" name="toDelete" value="${plangain.id}" id="check_${plangain.id}"></td>
                <td><c:out value="${plangain.period}"/></td>
                <td><c:out value="${plangain.state}"/></td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>

<script>
    $('#add_').click(function(){
        window.location.href = "/newplangain";
    });

    $('#delete_').click(function(){
        var data = { 'toDelete' : []};
        $(":checked").each(function() {
            data['toDelete'].push($(this).val());
        });
        $.post("/deleteplangain", data, function(data, status) {
            window.location.reload();
        });
    });
</script>

</body>
</html>
