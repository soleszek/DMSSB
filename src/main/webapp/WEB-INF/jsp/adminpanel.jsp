<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="style/style.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/jsscripts/app.js"></script>

    <title>Admin panel</title>
</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-start.jsp" %>

    <div id="content">
        <div class="square">
            <div class="tile1"><H1><a href="/registration" class="tilelink">Create new user</a> </H1></div>
            <div class="tile1"><H1><a href="/all/users" class="tilelink">Show all users</a> </H1><</div>
            <div style="clear: both"></div>

            <div class="tile2"><H1><a href="#" class="tilelink">Delete user</a></H1></div>
            <div class="tile3"><H1><a href="#" class="tilelink">Create workspace</a></H1></div>
            <div style="clear: both"></div>

            <div class="tile4">4</div>

        </div>
        <div class="square">
            <div class="tile5">5</div>

            <div class="tile6">6</div>
            <div class="tile7">7</div>
            <div class="tile8">8</div>
            <div class="tile9">9</div>
            <div style="clear: both"></div>
        </div>
        <div style="clear: both"></div>
    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

<script>
    $("a[href='/adminpanel']").addClass("current");
</script>

</body>
</html>
