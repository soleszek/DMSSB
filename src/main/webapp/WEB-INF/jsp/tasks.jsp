<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="style/documents-view.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>

    <title>Tasks</title>
</head>
<body>

<div id="container">

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
            <form class="thing" action="QuickSearch" method="get">
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
                <form id="usershow" action="/displayUserDetails" method="get">
                    <a href="#" onclick="document.getElementById('usershow').submit()">Witaj <sec:authentication property="principal.username"/>
                    </a>
                </form>
            </div>
            <div class="optionSO">
                <a href="/dashboard" id="home"><i class="fas fa-play fa-lg" title="Home"></i></a>
            </div>
            <div style="clear: both"></div>

        </div>
        <div style="clear:both;"></div>

    </div>
    <div style="clear:both"></div>

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

    <div id="content">

        <div id="navbar">
            <input id="txtSearch" placeholder="Filter table" class="form-control"/>
        </div>

        <%--<table id="example" class="display" style="width:100%">
            <col width="60">

            <thead>
            <tr>
                <th>Task name</th>
                <th>Owner</th>
                <th>Submitted document</th>
                <th>State</th>
                <th>Due date</th>
                <th>Comments</th>
                <th>Completion date</th>
            </tr>
            </thead>

            <tbody>
            <% for (Task t : userTasks) {
            %>
            <tr>
                <td><a href="OpenTask?taskId=<%=t.getId()%>" id="doc-link"><%=t.getName()%>
                </a></td>
                <td><%=t.getOwner()%>
                </td>
                <td><span class="doc-link"
                          onclick="openPopup('OpenDocument?documentId=<%=t.getDocumentBeingApproved()%>')"><%=t.getDocumentBeingApprovedName()%></span>
                </td>
                <td><%=t.getState()%>
                </td>
                <td><%=t.getDueDate()%>
                </td>
                <td><%=t.getComments()%>
                </td>
                <td><%=t.getCompletionDate()%>
                </td>
            </tr>

            </tbody>

        </table>--%>

        <script src="jsscripts/popup.js"></script>

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

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

</body>
</html>
