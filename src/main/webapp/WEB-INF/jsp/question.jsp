<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Question</title>
</head>
<body>
<c:forEach items="${paramValues.message}" var="message">
    ${message}
</c:forEach>

<form:form modelAttribute="questionForm">
    <form:textarea path="text" readonly="true"/>
    <form:textarea path="answer"/>
    <form:errors path="answer"/>
    <form:button>Answer</form:button>
</form:form>


</body>
</html>
