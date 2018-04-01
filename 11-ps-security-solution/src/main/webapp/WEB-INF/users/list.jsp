<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <spring:message code="home.title"/>
    </title>
    <spring:theme var="cssStyle" code="css.style"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="${cssStyle}" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
</head>
<body>
<div id="page">
    <div class="banner"></div>
    <div class="themeLocal">
        <c:choose>
            <c:when test="${requestContext.locale.language eq 'en'}">
                <c:url var="localeUrl" value="/">
                    <c:param name="locale" value="de"/>
                </c:url>
                <a href="${localeUrl}"><spring:message code="locale.de"/></a>
            </c:when>
            <c:otherwise>
                <c:url var="localeUrl" value="/">
                    <c:param name="locale" value="en"/>
                </c:url>
                <a href="${localeUrl}"><spring:message code="locale.en"/></a>
            </c:otherwise>
        </c:choose> |
        <c:choose>
            <c:when test="${requestContext.theme.name eq 'green'}">
                <c:url var="themeUrl" value="/">
                    <c:param name="theme" value="blue"/>
                </c:url>
                <a href="${themeUrl}"><spring:message code="theme.Blue"/></a>
            </c:when>
            <c:otherwise>
                <c:url var="themeUrl" value="/">
                    <c:param name="theme" value="green"/>
                </c:url>
                <a href="${themeUrl}"><spring:message code="theme.Green"/></a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="menu">
        <ul>
            <li><c:if test="${menuTab eq 'home'}">
                <strong><a href="<c:url value="/"/>"><spring:message code="menu.home"/></a></strong>
            </c:if>
                <c:if test="${menuTab != 'home'}">
                    <a href="<c:url value="/"/>"><spring:message code="menu.home"/></a>
                </c:if>
            </li>
            <li><c:if test="${menuTab eq 'users'}">
                <strong><a href="<c:url value="/users/list"/>"><spring:message code="menu.users"/></a></strong>
            </c:if>
                <c:if test="${menuTab != 'users'}">
                    <a href="<c:url value="/users/list"/>"><spring:message code="menu.users"/></a>
                </c:if>
            </li>
            <sec:authorize access="isAuthenticated()">
                <li>
                    <spring:url value="/logout" var="logoutUrl"/>
                    <form action="${logoutUrl}" id="logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a href="#" onclick="document.getElementById('logout').submit();"><spring:message code="menu.logout"/></a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="content">
        <h2>
            <spring:message code="users.list.title"/>
        </h2>

        <div class="users">
            <c:if test="${not empty confirmationMessage}">
                <h3><spring:message code="${confirmationMessage}"/></h3>
            </c:if>
            <table>
                <thead>
                <tr>
                    <sec:authorize url='/users/show/*'>
                        <td>
                            <spring:message code="label.User.count"/>
                        </td>
                    </sec:authorize>
                    <td>
                        <spring:message code="label.User.username"/>
                    </td>
                    <td>
                        <spring:message code="label.User.firstname"/>
                    </td>
                    <td>
                        <spring:message code="label.User.lastname"/>
                    </td>
                    <td>
                        <spring:message code="label.User.type"/>
                    </td>
                    <sec:authorize url='/users/delete/*'>
                        <td>
                            <spring:message code="label.delete"/>
                        </td>
                    </sec:authorize>
                </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <sec:authorize url='/users/show/*'>
                            <td>
                                <spring:url var="showUrl" value="show/{id}">
                                    <spring:param name="id" value="${user.id}"/>
                                </spring:url>
                                <a href="${showUrl}">${user.id}</a>
                            </td>
                        </sec:authorize>
                        <td>
                                ${user.username}
                        </td>
                        <td>
                                ${user.firstName}
                        </td>
                        <td>
                                ${user.lastName}
                        </td>
                        <td>
                                ${user.userType}
                        </td>
                        <sec:authorize url='/users/delete/*'>
                            <td>
                                <spring:url var="deleteUrl" value="delete/{id}">
                                    <spring:param name="id" value="${user.id}"/>
                                </spring:url>
                                <a href="${deleteUrl}"><spring:message code="label.delete"/></a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="footer">
        <sec:authorize access="isAuthenticated()">
            <p><spring:message code="user.loggedin"/>:
                <sec:authentication property="principal.username"/>
            </p>
        </sec:authorize>
        <p><spring:message code="footer.text"/></p>
    </div>
</div>
</body>
</html>