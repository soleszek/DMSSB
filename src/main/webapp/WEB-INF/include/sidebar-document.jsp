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