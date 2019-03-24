<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/style/documents-view.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="/jsscripts/app.js"></script>

    <title>Revisions</title>
</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-document.jsp" %>

    <div id="content">

        <div id="navbar">
            <ul>
                <li>
                    <c:if test="${document.getState() eq 'released'}">
                        <sec:authorize access="!hasRole('VIEWER')">
                            <a href="#">
                                <div class="icon">
                                    <i class="far fa-clone fa-2x"></i>
                                    <i class="far fa-clone fa-2x" title="Create new revision"
                                       onclick="javascript:location.href='/new/revision/${document.getId()}'"></i>
                                </div>
                            </a>
                        </sec:authorize>
                    </c:if>
                    <c:if test="${document.getState() eq 'in work' or document.getState() eq 'frozen'}">
                        <a href="#">
                            <div class="icon-disabled">
                                <i class="far fa-clone fa-2x" title="You don't have privileges"></i>
                            </div>
                        </a>
                    </c:if>
                </li>
            </ul>

            <input id="txtSearch" placeholder="Filter table" class="form-control"/>

        </div>


        <table id="example" class="display" style="width:100%">
            <col width="60">

            <thead>
            <tr>
                <th>Name</th>
                <th>Title</th>
                <th><i class="far fa-window-restore"></i></th>
                <th>Type</th>
                <th>State</th>
                <th>Revision</th>
                <th>Owner</th>
                <th>Creation date</th>
                <th>Last modified</th>
                <th>Attachement</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${revisions}" var="item">
                <tr>
                    <td><a href="/document/${item.getId()}" id="doc-link">${item.getName()}
                    </a></td>
                    <td>${item.getTitle()}
                    </td>
                    <td>
                        <div id="popup" onclick="openPopup('/document/${item.getId()}')"><i
                                class="far fa-window-restore"></i></div>
                    </td>
                    <td>${item.getType()}
                    </td>
                    <td>${item.getState()}
                    </td>
                    <td>${item.getRevision()}
                    </td>
                    <td>${item.getOwner().getUsername()}
                    </td>
                    <td>${item.getCreationDate()}
                    </td>
                    <td>${item.getLastModification()}
                    </td>
                    <td>${item.getLink()}
                    </td>
                    <td>${item.getDescription()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <script src="/jsscripts/popup.js"></script>

    <script>
        $("a[href='/document/${document.getId()}/revisions']").addClass("current");
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

</div>

</body>
</html>
