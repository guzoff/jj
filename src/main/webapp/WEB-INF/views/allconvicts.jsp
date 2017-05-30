<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Convicts List</title>

        <style>
            table{
                border: 1px solid black;
                border-collapse: collapse;
            }
            td, th{
                border: 1px solid black;
            }
            tr:first-child{
                font-weight: bold;
                color: white;
                background-color: brown;
            }
            .td_red_center{
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
                <th>Nickname</th><th>Sex</th><th>Jailing Date</th><th>Crime Code</th>
                <th>Sentence Period</th><th>Crime Description</th><th></th>
            </tr>
            <c:forEach items="${convicts}" var="convict">
                <tr>
                    <td><a href="<c:url value='/edit-${convict.nickname}-convict' />">${convict.nickname}</a></td>
                    <td>${convict.sex}</td>
                    <td>${convict.jailingDate}</td>
                    <td class="td_red_center"><fmt:formatNumber value="${convict.intCode}" pattern="00000000"/></td>
                    <td class="td_center">${convict.sentencePeriod}</td>
                    <td>${convict.CRIME_MAP[convict.crimeCode]}</td>
                    <td><a href="<c:url value='/delete-${convict.nickname}-convict' />">RELEASE</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="<c:url value='/new' />">Jail New Convict</a>
    </body>
</html>
