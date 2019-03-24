<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Information about user</title>
    <link rel="stylesheet" href="/style/documents-view.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="/jsscripts/app.js"></script>

</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-message.jsp" %>

    <div id="content">

        <div id="navbar">
            <ul>
                <li>
                    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                        <a href="#" onclick="deletePermanently()">
                            <div class="icon">
                                <i class="fas fa-minus-square fa-2x"></i>
                                <i class="fas fa-minus-square fa-2x" title="Delete"></i>
                            </div>
                        </a>
                    </sec:authorize>
                </li>
            </ul>
        </div>

        <form id="myForm" action="/trash/messages" method="post">
            <table id="example" class="display" style="width:100%">
                <col width="60">
                <thead>
                <tr>
                    <th><input type="checkbox" id='selectAllChecks'></th>
                    <th>From</th>
                    <th><i class="far fa-window-restore"></i></th>
                    <th>Title</th>
                    <th>Date</th>
                </tr>
                </thead>

                <sec:authorize access="hasAnyRole('CONTRIBUTOR','MANAGER','ADMIN')">
                    <c:if test="${fn:length(messages) > 0}">
                        <tbody>
                        <c:forEach items="${messages}" var="item">
                            <tr>
                                <td><input type="checkbox"
                                           name="messagesChecked" value="${item.getMessage_id()}"></td>
                                <td>${item.getReceiver().getUsername()}
                                </td>
                                <td>
                                    <div id="popup" onclick="openPopup('/message/${item.getMessage_id()}/deleted')"><i
                                            class="far fa-window-restore"></i></div>
                                </td>
                                <td><a href="/message/${item.getMessage_id()}/deleted" id="doc-link">${item.getTitle()}</a>
                                </td>
                                <td>${item.getReceivingDate()}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>
                </sec:authorize>
            </table>
            <input type="text" name="view" value="/messages-deleted" hidden>
        </form>
    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

<script src="/jsscripts/deletePermanently.js"></script>

<script>
    $("a[href='/messages/deleted']").addClass("current");
</script>

<script type="text/javascript">
    $(document).ready(function () {

        // Setup - add a text input to each footer cell
        $('#example tfoot th').each(function () {
            var title = $(this).text();
            $(this).html('<input type="text" placeholder="Search ' + title + '" />');
        });

        // DataTable
        var table = $('#example').DataTable({
            "lengthMenu": [[10, 20], [10, 20]]
        });

    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#example').DataTable();

        $('#example_filter').hide(); // Hide default search datatables where example is the ID of table

        $('#txtSearch').on('keyup', function () {
            $('#example')
                .DataTable()
                .search($('#txtSearch').val(), false, true)
                .draw();
        });
    });
</script>

<script>
    $("#selectAllChecks").change(function () {
        $('input[name=messagesChecked]').prop("checked", $(this).prop("checked"))
    })
    $('input[name=messagesChecked]').change(function () {
        if ($(this).prop("checked") == false) {
            $("#selectAllChecks").prop("checked", false)
        }
        if ($('input[name=messagesChecked]:checked').length == $('input[name=messagesChecked]').length) {
            $("#selectAllChecks").prop("checked", true)
        }
    })
</script>

</body>
</html>
