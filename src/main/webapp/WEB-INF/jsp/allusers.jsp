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

    <title>Search results of all users</title>

</head>
<body  onload="connect()">

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
                <th>User name</th>
                <th>Login</th>
                <th>Name</th>
                <th>Last name</th>
                <th>Role</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><a href="/userdetails/${user.getUser_id()}" id="doc-link">${user.getName()}
                    </a></td>
                    <td><span class="doc-link"
                              onclick="openPopup('/userdetails/${user.getUser_id()}')">${user.getUsername()}</span></td>
                    <td>${user.getFirstName()}
                    </td>
                    <td>${user.getLastName()}
                    </td>
                    <td><c:forEach var="role" items="${user.getRoles()}">
                        ${role.getRole()}
                    </c:forEach>
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
