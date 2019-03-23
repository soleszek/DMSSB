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

    <title>Document viewer</title>
</head>
<body onload="connect()">

<div id="container">

    <div id="logo">
        <span style="color:#c34f4f">Data</span> Management System
    </div>

    <div class="menu">

        <div class="topmenu">
            <label><c:out value="${document.getName()}"/></label>
        </div>
        <div id="search">
            <ul class="sliding-icons">
                <li>
                    <a href="/advancedsearch">
                        <div class="icon">
                            <i class="fas fa-search fa-2x"></i>
                            <i class="fas fa-search fa-2x" title="Advanced search"></i>
                        </div>
                    </a>
                </li>
            </ul>
            <form class="thing" action="/quicksearch" method="get">
                <label for="ddd" class="thing-label">
                    Type to search...
                </label>
                <input type="text" name="phrase" id="ddd" class="thing-text">
                <input type="submit" value="search" class="thing-btn">
            </form>
            <div style="clear: both"></div>
        </div>

        <div class="topmenu">
            <div class="optionSO">
                <form action="/logout" method="get">
                    <input type="hidden" name="login" value="<c:out value="${sessionScope.login}"/>">
                    <input type="submit" name="menu" value="Sign out">
                </form>
            </div>
            <div class="option">
                <form id="usershow" action="/userdetails" method="get">
                    <a href="#" onclick="document.getElementById('usershow').submit()">Witaj <sec:authentication
                            property="principal.username"/>
                    </a>
                </form>
            </div>
            <div class="optionSO">
                <a href="/dashboard" id="home"><i class="fas fa-play fa-lg" title="Home"></i></a>
            </div>
            <div class="notifications">
                <a href="notifications" id="notificationsT">
                    <i class="fas fa-bell fa-2x" title="Tasks"></i>
                    <span class="num" id="taskNumber"></span>
                </a>
            </div>
            <div class="notifications">
                <a href="/messages/unread" id="notificationsM">
                    <i class="fas fa-envelope fa-2x" title="Messages"></i>
                    <span class="num" id="messageNumber"></span>
                </a>
            </div>
            <div style="clear: both"></div>

        </div>
        <div style="clear:both;"></div>

    </div>
    <div style="clear:both"></div>

    <div id="sidebar">
        <div class="optionL"><a href="/document/${document.getId()}">Properties</a></div>
        <div class="optionL"><a href="/document/${document.getId()}/revisions">Revisions</a></div>

        <c:if test="${role ne 'viewer'}">
            <div class="optionL"><a href="/document/${document.getId()}/routes">Routes</a></div>
        </c:if>

        <div class="optionL"><a href="/document/${document.getId()}/lifecycle">Lifecycle</a></div>
        <c:if test="${document.getType() eq 'drawing'}">
            <div class="optionL"><a href="/document/${document.getId()}/viewer">Viewer</a></div>
        </c:if>

        <div style="clear: both"></div>
    </div>

    <div id="content">

        <object data="data:application/pdf;base64,${pdf}" width="100%" height="650" type='application/pdf'>
            <p>There is a problem with opening this file.</p>
        </object>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

<script>
    $("a[href='/document/${document.getId()}/viewer']").addClass("current");
</script>

</body>
</html>
