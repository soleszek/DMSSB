<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <script src="/jsscripts/app.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="/jsscripts/jquery.autocomplete.min.js"></script>

    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: Helvetica, Arial, sans-serif;
        }

        /* Set a style for all buttons */
        button {
            background-color: #46b7ce;
            color: white;
            padding: 14px 20px;
            margin: 8px 26px;
            border: none;
            cursor: pointer;
            width: 90%;
            font-size: 20px;
        }

        button:hover {
            opacity: 0.8;
        }

        .file {
            background-color: #46b7ce;
            color: white;
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            border: none;
            cursor: pointer;
            font-size: 15px;
            width: 85%;
        }

        /* Center the image and position the close button */
        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
            position: relative;
        }

        .avatar {
            width: 150px;
            height: 150px;
            border-radius: 50%;
        }

        /* The Modal (background) */
        .modal {
            display: none;
            position: fixed;
            z-index: 12;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        /* Modal Content Box */
        .modal-content {
            background-color: #fefefe;
            margin: 4% auto 15% auto;
            border: 1px solid #888;
            width: 40%;
            padding-bottom: 30px;
        }

        /* The Close Button (x) */
        .close {
            position: absolute;
            right: 25px;
            top: 0;
            color: #000;
            font-size: 35px;
            font-weight: bold;
        }

        .close:hover, .close:focus {
            color: red;
            cursor: pointer;
        }

        /* Add Zoom Animation */
        .animate {
            animation: zoom 0.6s
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        .custom-select {
            position: relative;
        }

        .custom-select select {
            display: none; /*hide original SELECT element:*/
        }

        .select-selected {
            background-color: #46b7ce;
        }

        /*style the arrow inside the select element:*/
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /*point the arrow upwards when the select box is open (active):*/
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /*style the items (options), including the selected item:*/
        .select-items div, .select-selected {
            color: #ffffff;
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            border: 1px solid transparent;
            border-color: transparent transparent #46b7ce transparent;
            cursor: pointer;
            user-select: none;
        }

        /*style items (options):*/
        .select-items {
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            position: absolute;
            background-color: #46b7ce;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 99;
        }

        /*hide the items when the select box is closed:*/
        .select-hide {
            display: none;
        }

        .select-items div:hover, .same-as-selected {
            background-color: rgba(0, 0, 0, 0.1);
        }


    </style>

</head>
<body onload="connect()">

<div id="container">

    <%@include file="/WEB-INF/include/topbar.jsp" %>

    <%@include file="/WEB-INF/include/sidebar-message.jsp" %>

    <div id="content">

        <div id="navbar">
            <ul>
                <c:if test="${not oneMessage.getIsDeleted()}">
                    <li>
                        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                            <a href="#">
                                <div class="icon">
                                    <i class="far fa-comments fa-2x"></i>
                                    <i class="far fa-comments fa-2x" title="Create new message"
                                       onclick="document.getElementById('modal-wrapper-newmessage').style.display='block'"></i>
                                </div>
                            </a>
                        </sec:authorize>
                    </li>
                    <li>
                        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                            <a href="#">
                                <div class="icon">
                                    <i class="fas fa-trash-alt fa-2x"></i>
                                    <i class="fas fa-trash-alt fa-2x" title="Move to trash"
                                       onclick="document.getElementById('modal-wrapper-movemessagetotrash').style.display='block'"></i>
                                </div>
                            </a>
                        </sec:authorize>
                    </li>
                    <li>
                        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                            <a href="#">
                                <div class="icon">
                                    <i class="fas fa-user-plus fa-2x"></i>
                                    <i class="fas fa-user-plus fa-2x" title="Change your role"
                                       onclick="document.getElementById('modal-wrapper-deactivateuser').style.display='block'"></i>
                                </div>
                            </a>
                        </sec:authorize>
                    </li>
                </c:if>
                <c:if test="${oneMessage.getIsDeleted()}">
                    <li>
                        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                            <a href="#">
                                <div class="icon">
                                    <i class="fas fa-minus-square fa-2x"></i>
                                    <i class="fas fa-minus-square fa-2x" title="Delete"
                                       onclick="document.getElementById('modal-wrapper-deletemessage').style.display='block'"></i>
                                </div>
                            </a>
                        </sec:authorize>
                    </li>
                </c:if>
            </ul>
        </div>

        <table id="example" class="display" style="width:100%">
            <col width="220">

            <tr>
                <td>Name</td>
                <td>${oneMessage.getName()}
                </td>
            </tr>
            <tr>
                <td>Title</td>
                <td>${oneMessage.getTitle()}</td>
            </tr>
            <tr>
                <td>Content</td>
                <td><textarea class="noedit-text" rows="4" cols="50" name="content"
                              style="resize:none" disabled>${oneMessage.getContent()}</textarea>
                </td>
            </tr>
            <tr>
                <td>Sender</td>
                <td>${oneMessage.getSender().getUsername()}</td>
            </tr>
            <tr>
                <td>Sending date</td>
                <td>${oneMessage.getSendingDate()}
                </td>
            </tr>
            <tr>
                <td>Receiving date</td>
                <td>${oneMessage.getReceivingDate()}
                </td>
            </tr>

        </table>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <div id="modal-wrapper-newmessage" class="modal">

        <form:form class="modal-content animate" id="usrform" action="/new/${originView}/${user.getUser_id()}" method="post"
                   modelAttribute="message">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-newmessage').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/create-new-message.png" alt="Document" class="avatar">
                <h1 style="text-align:center">Create new message</h1>

                <br><br>
                <input type="text" name="username" value="" placeholder="To..." id="w-input-search"/>
                <br><br>
                <form:input path="title" placeholder="Enter title" class="modal-text" type="text"/>
                <br><br>
                <textarea class="modal-text" rows="4" cols="50" name="content" form="usrform"
                          placeholder="Enter your message..." style="resize:none" value=""></textarea><br>
                <button type="submit">Send</button>
                <br>
            </div>
        </form:form>
    </div>

    <div id="modal-wrapper-movemessagetotrash" class="modal">

        <form class="modal-content animate" action="/trash/${originView}/${oneMessage.getMessage_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-movemessagetotrash').style.display='none'"
                      class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/trash.png" alt="Trash" class="avatar">
                <h1 style="text-align:center">Move message to trash</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">Are you sure you
                want to move this message to the trash?
            </h3>
                <button type="submit">Confirm</button>
            </div>
        </form>
    </div>

    <div id="modal-wrapper-deletemessage" class="modal">

        <form class="modal-content animate" action="/trash/${originView}/${oneMessage.getMessage_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-deletemessage').style.display='none'"
                      class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/delete.png" alt="Trash" class="avatar">
                <h1 style="text-align:center">Delete message permanently</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">Are you sure you
                want to delete this message permanently?
            </h3>
                <button type="submit">Confirm</button>
            </div>
        </form>
    </div>

    <div id="modal-wrapper-deactivateuser" class="modal">

        <form class="modal-content animate" action="/status/user/${user.getUser_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-deactivateuser').style.display='none'"
                      class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/deactivate-user.png" alt="Document" class="avatar">
                <c:choose>
                <c:when test="${user.getEnabled() eq '1'}">
                <h1 style="text-align:center">Deactivate user</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
                deactivate ${user.getUsername()}
            </h3>
                <button type="submit">Complete</button>
            </div>
            </c:when>
            <c:otherwise>
            <h1 style="text-align:center">Activate user</h1>
    </div>

    <div class="container"><h3
            style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
        activate ${user.getUsername()}
    </h3>
        <button type="submit">Complete</button>
    </div>
    </c:otherwise>
    </c:choose>

</div>

<script src="/jsscripts/popup.js"></script>

<script>
    $("a[href='/messages/${originView}']").addClass("current");
</script>

<script>
    // If user clicks anywhere outside of the modal, Modal will close

    var modal = document.getElementById('modal-wrapper-movemessagetotrash');
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

<script>
    // If user clicks anywhere outside of the modal, Modal will close

    var modal = document.getElementById('modal-wrapper-deletemessage');
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

<script>
    // If user clicks anywhere outside of the modal, Modal will close

    var modal = document.getElementById('modal-wrapper-newmessage');
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

<script>
    // If user clicks anywhere outside of the modal, Modal will close

    var modal = document.getElementById('modal-wrapper-deactivateuser');
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
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
    $(document).ready(function () {

        $('#w-input-search').autocomplete({
            serviceUrl: '${pageContext.request.contextPath}/receiver',
            paramName: "tag",
            delimiter: ",",
            transformResult: function (response) {

                return {

                    suggestions: $.map($.parseJSON(response), function (item) {

                        return {value: item.username, data: item.userId};
                    })
                };
            }
        });
    });
</script>

</body>
</html>
