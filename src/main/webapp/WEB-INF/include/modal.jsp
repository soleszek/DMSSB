
<style>
    * {
        margin: 0;
        padding: 0;
        font-family: Helvetica, Arial, sans-serif;
    }

    .modal-text {
        width: 90%;
        padding: 12px 20px;
        margin: 8px 26px;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
        font-size: 16px;
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