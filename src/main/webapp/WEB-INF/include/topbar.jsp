<div id="logo">
    <span style="color:#c34f4f">Data</span> Management System
</div>

<div class="menu">

<div class="topmenu">
    <label></label>
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
