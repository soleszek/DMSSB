<div id="sidebar">
    <div class="optionL"><a href="/documents">Documents</a></div>
    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
        <div class="optionL"><a href="/routeslist">Routes</a></div>
        <div class="optionL"><a href="/tasks">Tasks</a></div>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <div class="optionL"><a href="/adminpanel">Admin Panel</a></div>
    </sec:authorize>

    <div style="clear: both"></div>
</div>