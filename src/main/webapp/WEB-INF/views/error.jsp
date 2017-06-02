<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h2>Ooops! There something wrong with our jail.</h2>
        <h3>Error: ${errmsg}</h3>
        <br/>
        <a href="<c:url value='/' />" >Try again</a>
    </body>
</html>
