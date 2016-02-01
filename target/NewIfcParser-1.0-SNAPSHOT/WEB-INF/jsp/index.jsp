<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IFC</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/main.css" />
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/bootstrap-theme.css" />
        
    </head>
    <body>
        
        <span class="blue hidden">${msg}</span>
        
        <div class="applicationContent"></div>
        
    </body>
    
    <script src="${cp}/resources/js/jquery-2.2.0.js"></script>
    <script src="${cp}/resources/js/bootstrap.js"></script>
    <script src="${cp}/resources/js/main.js"></script>
</html>
