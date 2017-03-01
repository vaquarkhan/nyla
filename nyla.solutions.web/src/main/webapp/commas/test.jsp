<!--
  Displays the content of the file specified in request
  parameter "filename".
  <%-- Warning!  Can be used to retrieve the source code for
       any file in the 'standard-examples' application.
       It is not advisable to insert any sensitive code
       (even as an experiment) into this application --%>
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="ex" uri="/jstl-examples-taglib" %>

<%-- 
  Let's get back the URL as a String so we can use it to
  demonstrate "c:import"
--%>
<% pageContext.setAttribute("filepath","hello"); %>
<% pageContext.setAttribute("filename", request.getParameter("filename")); %>

<html>
<head>
  <title>JSTL: Source code for <c:out value="${filename}"/></title>
</head>
<body bgcolor="#FFFFFF">
<h3>Source code for:&nbsp; <c:out value="${filename}"/>: pageContext: <c:out value="${pageContext.request.contextPath}"/></h3>

<hr>
<hr>
</body>
</html>