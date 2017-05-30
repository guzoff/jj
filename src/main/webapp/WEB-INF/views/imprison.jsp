<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jailing Form</title>

        <style>
            .error {
                color: #ff0000;
            }
        </style>

    </head>

    <body>

        <h2>Jailing Form</h2>

        <form:form method="POST" modelAttribute="convict">
            <form:input type="hidden" path="id" id="id"/>
            <table>
                <tr>
                    <td><label>Nickname: </label> </td>
                    <td><form:input path="nickname" id="nickname"/></td>
                    <td><form:errors path="nickname" cssClass="error"/></td>
                </tr>
                
                <tr>
                    <td><label>Sex: </label> </td>
                    <td>
                        <form:select path="sex" id="sex">
                            <form:option value="M" label="male"/>
                            <form:option value="F" label="female"/>
                        </form:select>
                    </td>
                    <td><form:errors path="sex" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label>Jailing Date: </label> </td>
                    <td><form:input path="jailingDate" id="jailingDate"/></td>
                    <td><form:errors path="jailingDate" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label>Crime: </label> </td>
                    <td>
                        <form:select path="crimeCode" id="crimeCode">
                            <form:options items="${convict.CRIME_MAP}"/>
                        </form:select>
                    </td>
                    <td><form:errors path="crimeCode" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label>Sentence Period: </label> </td>
                    <td><form:input path="sentencePeriod" id="sentencePeriod"/></td>
                    <td><form:errors path="sentencePeriod" cssClass="error"/></td>
                </tr>

                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Jail!"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form:form>
        <br/>
        <br/>
        Go back to <a href="<c:url value='/list' />">List of Convicts</a>
    </body>
</html>