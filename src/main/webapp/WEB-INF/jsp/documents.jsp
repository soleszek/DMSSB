<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <title>Documents</title>

    <%@include file="/WEB-INF/include/modal.jsp" %>

</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-start.jsp" %>

    <div id="content">
        <div id="navbar">
            <ul class="sliding-icons">
                <li>
                    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                        <a href="#">
                            <div class="icon">
                                <i class="fas fa-plus-square fa-2x"></i>
                                <i class="fas fa-plus-square fa-2x" title="Create new document"
                                   onclick="document.getElementById('modal-wrapper').style.display='block'"></i>
                            </div>
                        </a>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('VIEWER')">
                        <a href="#">
                            <div class="icon-disabled">
                                <i class="fas fa-plus-square fa-2x" title="You don't have privileges"></i>
                            </div>
                        </a>
                    </sec:authorize>
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
            <sec:authorize access="hasRole('VIEWER')">
                <tbody>
                <c:forEach items="${approvedDocuments}" var="item">
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
            </sec:authorize>

            <sec:authorize access="hasAnyRole('CONTRIBUTOR','MANAGER','ADMIN')">
                <tbody>
                <c:forEach items="${documents}" var="item">
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
            </sec:authorize>

        </table>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <div id="modal-wrapper" class="modal">

        <form:form class="modal-content animate" action="/new/document" method="post" modelAttribute="document" enctype="multipart/form-data">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="style/document.jpg" alt="Document" class="avatar">
                <h1 style="text-align:center">Create new document</h1>
            </div>

            <div class="container">
                <div class="custom-select">
                    <select name="doctype">
                        <option value="drawing">Drawing (pdf)</option>
                        <option value="drawing">Drawing (pdf)</option>
                        <option value="document">Document (doc, docx)</option>
                        <option value="image">Image (jpg, png)</option>
                    </select>
                </div>
                <form:input type="text" class="modal-text" placeholder="Enter title" path="title"
                            required="required"></form:input>
                <sec:authentication var="principal" property="principal"/>
                <input type="text" class="modal-text" readonly="readonly" value="${principal.username}">
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="text" class="modal-text" readonly value="<fmt:formatDate type = "date" value = "${now}"/>">
                <input type="file" class="modal-text" name="file" class="file" required="required"/>
                <form:input type="text" class="modal-text" placeholder="Enter description" path="description"
                            required="required"></form:input>
                <button type="submit">Create</button>
            </div>
        </form:form>

    </div>

    <script>
        $("a[href='/documents']").addClass("current");
    </script>

    <script>
        // If user clicks anywhere outside of the modal, Modal will close

        var modal = document.getElementById('modal-wrapper');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

    <script src="/jsscripts/dropdownmenu.js"></script>
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
