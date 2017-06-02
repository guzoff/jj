<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Convicts List</title>

        <style>
            tr:first-child{
                font-weight: bold;
                background-color: #C4C9C2;
            }
            .td_center_red{
                color: #ff0000;
                text-align: center;
            }
            .td_center{
                text-align: center;
            }
        </style>

    </head>


    <body>
        <h2>Welcome to Java Jail!</h2>  
        <h3>Our convicts for now:</h3>
        <table>
            <tr>
                <th>Nickname</th><th>Sex</th><th>Jailing Date</th><th>Crime Code</th><th>Sentence Period</th><th></th>
            </tr>
            <c:forEach items="${convicts}" var="convict">
                <tr>
                    <td><a href="<c:url value='/edit-${convict.nickname}-convict' />">${convict.nickname}</a></td>
                    <td>${convict.sex}</td>
                    <td>${convict.jailingDate}</td>
                    <td class="td_center_red">${convict.crimeCode}</td>
                    <td class="td_center">${convict.sentencePeriod}</td>
                    <td><a href="<c:url value='/delete-${convict.nickname}-convict' />">RELEASE</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="<c:url value='/new' />">Jail New Convict</a>
    </body>
</html>
