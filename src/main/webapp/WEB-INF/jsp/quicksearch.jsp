<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

    <title>Quick search results</title>

</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-start.jsp" %>

    <div id="content">
        <div id="navbar">
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
            <sec:authorize access="hasRole('VIEWER')">
                <c:if test="${fn:length(results) > 0}">
                    <c:forEach var="d" items="${results}">
                        <c:if test="${d.getState() eq 'released'}">
                            <tr>
                                <td><a href="/document/${d.getId()}" id="doc-link">${d.getName()}
                                </a></td>
                                <td>${d.getTitle()}
                                </td>
                                <td>
                                    <div id="popup" onclick="openPopup('/document/${d.getId()}')"><i
                                            class="far fa-window-restore"></i></div>
                                </td>
                                <td>${d.getType()}
                                </td>
                                <td>${d.getState()}
                                </td>
                                <td>${d.getRevision()}
                                </td>
                                <td>${d.getOwner().getUsername()}
                                </td>
                                <td>${d.getCreationDate()}
                                </td>
                                <td>${d.getLastModification()}
                                </td>
                                <td>${d.getLink()}
                                </td>
                                <td>${d.getDescription()}
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
            </sec:authorize>
            </tbody>

            <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                <tbody>
                <c:if test="${fn:length(results) > 0}">
                    <c:forEach var="d" items="${results}">
                        <tr>
                            <td><a href="/document/${d.getId()}" id="doc-link">${d.getName()}
                            </a></td>
                            <td>${d.getTitle()}
                            </td>
                            <td>
                                <div id="popup" onclick="openPopup('/document/${d.getId()}')"><i
                                        class="far fa-window-restore"></i></div>
                            </td>
                            <td>${d.getType()}
                            </td>
                            <td>${d.getState()}
                            </td>
                            <td>${d.getRevision()}
                            </td>
                            <td>${d.getOwner().getUsername()}
                            </td>
                            <td>${d.getCreationDate()}
                            </td>
                            <td>${d.getLastModification()}
                            </td>
                            <td>${d.getLink()}
                            </td>
                            <td>${d.getDescription()}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </sec:authorize>

        </table>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <script>
        // If user clicks anywhere outside of the modal, Modal will close

        var modal = document.getElementById('modal-wrapper');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

    <script src="/jsscripts/popup.js"></script>

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
