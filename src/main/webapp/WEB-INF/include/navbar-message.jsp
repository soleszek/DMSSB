<div id="navbar">
    <ul>
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
                <a href="#" onclick="moveToTrash()">
                    <div class="icon">
                        <i class="fas fa-trash-alt fa-2x"></i>
                        <i class="fas fa-trash-alt fa-2x" title="Move to trash"></i>
                    </div>
                </a>
            </sec:authorize>
        </li>
    </ul>
</div>