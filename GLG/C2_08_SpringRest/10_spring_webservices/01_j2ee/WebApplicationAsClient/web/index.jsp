<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <form action="computeClient" method="post">
            a: <input type="number" name="a"> b <input type="number" name="b"/> <input type="submit"/>
        </form>
        
    <c:if test="${not empty resultat}">
        Résultat <c:out value="${resultat}"/>
    </c:if>
        
    </body>
</html>
