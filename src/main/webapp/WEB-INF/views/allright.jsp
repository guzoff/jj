<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jailing Confirmation Page</title>
    </head>

    <body>
        message : ${result}
        <br/>
        <br/>
        Go back to <a href="<c:url value='/list' />">List of Convicts</a>
    </body>
</html>