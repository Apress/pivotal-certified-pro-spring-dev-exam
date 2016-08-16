<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="login">
    <h4><spring:message code="login.hint"/></h4>
    <table>
        <tr><th><spring:message code="login.username"/></th>
            <th><spring:message code="login.password"/></th>
            <th><spring:message code="login.role"/></th>
        </tr>
        <tr><td>john</td><td>doe</td><td>ROLE_USER</td></tr>
        <tr><td>jane</td><td>doe</td><td>ROLE_USER,ROLE_ADMIN</td></tr>
        <tr><td>admin</td><td>admin</td><td>ROLE_ADMIN</td></tr>
    </table>

    <p><h3><spring:message code="login.message"/></h3></p>

    <spring:url value="/login" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table>
            <tr>
                <td><label for="username"><spring:message code="login.username"/></label></td>
                <td> <input type='text' id='username' name='username' value='<c:out value="${user}"/>'/></td>
            </tr>
            <tr>
                <td><label for="password"><spring:message code="login.password"/></label></td>
                <td><input type='password' id='password' name='password'/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit"><spring:message code="login.submit"/></button>
                </td>
            </tr>
        </table>
    </form>

    <c:if test="${not empty param.auth_error}">
        <div id="errors" class="error">
            <p><spring:message code="login.fail"/>: ${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
        </div>
    </c:if>

</div>
