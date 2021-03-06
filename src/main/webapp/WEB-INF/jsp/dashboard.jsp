<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/style/style.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/jsscripts/app.js"></script>

</head>
<body onload="startTime(); connect()">
<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-start.jsp" %>

    <div id="content">
        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
            <div class="square">
                <div class="tile1"><H1><a href="/documents" class="tilelink">
                    All documents<br><br>
                    <c:out value="${documentCount}"/>
                </a></H1></div>
                <div class="tile1"><H1><a href="/routeslist" class="tilelink">
                    Active routes<br><br>
                    <c:out value="${routeCount}"/>
                </a></H1><
                </div>
                <div style="clear: both"></div>

                <div class="tile2"><H1><a href="AllUserTasks" class="tilelink">
                    Your active tasks<br><br>
                    <c:out value="${userTasksCount}"/>
                </a></H1></div>
                <div class="tile3">

                    <div class="calc">

                        <div class="display"><input type="text" id="wynik" readonly/></div>

                        <div class="tile"><input type="button" value="9" onclick="pobierz('9')"/></div>
                        <div class="tile"><input type="button" value="8" onclick="pobierz('8')"/></div>
                        <div class="tile"><input type="button" value="7" onclick="pobierz('7')"/></div>
                        <div class="tile"><input type="button" value="+" onclick="pobierz('+')"/></div>
                        <div style="clear:both;"></div>

                        <div class="tile"><input type="button" value="6" onclick="pobierz('6')"/></div>
                        <div class="tile"><input type="button" value="5" onclick="pobierz('5')"/></div>
                        <div class="tile"><input type="button" value="4" onclick="pobierz('4')"/></div>
                        <div class="tile"><input type="button" value="-" onclick="pobierz('-')"/></div>
                        <div style="clear:both;"></div>

                        <div class="tile"><input type="button" value="3" onclick="pobierz('3')"/></div>
                        <div class="tile"><input type="button" value="2" onclick="pobierz('2')"/></div>
                        <div class="tile"><input type="button" value="1" onclick="pobierz('1')"/></div>
                        <div class="tile"><input type="button" value="x" onclick="pobierz('*')"/></div>
                        <div style="clear:both;"></div>

                        <div class="tile"><input type="button" value="0" onclick="pobierz('0')"/></div>
                        <div class="tile"><input type="button" value="C" onclick="czysc()"/></div>
                        <div class="wynik"><input type="button" value="=" onclick="oblicz()" style="width:75px"/></div>
                        <div style="clear:both;"></div>

                    </div>

                </div>
                <div style="clear: both"></div>

                <div class="tile4">
                    <div id="txt"></div>
                </div>
            </div>
            <div class="square">
                <div class="tile5">

                    <a href="/messages/unread" class="tilelink">
                        <div class="icon">
                            <br><br>
                            <i class="far fa-envelope fa-10x" title="Messages"></i>
                            <H1>New messages<br><br>
                                <c:out value="${newMessagesCount}"/>
                            </H1>
                        </div>
                    </a>

                </div>

                <div class="tile6" id="notify">You spend ... in the system</div>
                <div class="tile7">7</div>
                <div class="tile8">8</div>
                <div class="tile9">9</div>
                <div style="clear: both"></div>
            </div>
            <div style="clear: both"></div>
        </sec:authorize>
    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <script src="../../jsscripts/clock.js"></script>
    <script src="../../jsscripts/calc.js"></script>

</div>


</body>
</html>