<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/style/style.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/jsscripts/app.js"></script>

    <title>Lifecycle</title>
</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-document.jsp" %>

    <div id="content">

        <ul class="lifecycle">

            <c:choose>
                <c:when test="${document.getState() eq 'in work'}">
                    <li class="active">In work</li>
                </c:when>
                <c:when test="${document.getState() ne 'in work'}">
                    <li>In work</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'frozen'}">
                    <li class="active">Frozen</li>
                </c:when>
                <c:when test="${document.getState() ne 'frozen'}">
                    <li>Frozen</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'released'}">
                    <li class="active">Released</li>
                </c:when>
                <c:when test="${document.getState() ne 'released'}">
                    <li>Released</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'cancelled'}">
                    <li class="active">Canceled</li>
                </c:when>
                <c:when test="${document.getState() ne 'cancelled'}">
                    <li>Canceled</li>
                </c:when>
            </c:choose>
        </ul>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

<script>
    $("a[href='/document/${document.getId()}/lifecycle']").addClass("current");
</script>

</body>
</html>
