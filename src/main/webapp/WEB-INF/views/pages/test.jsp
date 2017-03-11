<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/11
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <%--<link href="/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>--%>
    <%--<link rel="stylesheet" href="/static/css/owl.carousel.css" type="text/css">--%>
    <!-- Custom styles for this template -->
    <%--<link href="/static/css/style.css" rel="stylesheet">--%>
    <%--<link href="/static/css/style-responsive.css" rel="stylesheet"/>--%>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="/static/css/mdb.css" rel="stylesheet"/>

</head>
<body>
<!-- Button trigger modal -->
<%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">--%>
    <%--Launch demo modal--%>
<%--</button>--%>

<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<!--Content-->--%>
        <%--<div class="modal-content">--%>
            <%--<!--Header-->--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span>--%>
                <%--</button>--%>
                <%--<h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
            <%--</div>--%>
            <%--<!--Body-->--%>
            <%--<div class="modal-body">--%>
                <%--...--%>
            <%--</div>--%>
            <%--<!--Footer-->--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
                <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!--/.Content-->--%>
    <%--</div>--%>
<%--</div>--%>

<!--Rotating card-->
<div class="card-wrapper">
    <div id="card-1" class="card-rotating effect__click">

        <!--Front Side-->
        <div class="face front">

            <!-- Image-->
            <div class="card-up">
                <img src="http://mdbootstrap.com/images/reg/reg%20(35).jpg" class="img-fluid">
            </div>
            <!--Avatar-->
            <div class="avatar"><img src="http://mdbootstrap.com/wp-content/uploads/2015/10/team-avatar-2.jpg" class="rounded-circle img-responsive">
            </div>
            <!--Content-->
            <div class="card-block">
                <h4>Jonathan Doe</h4>
                <p>Web developer</p>
                <!--Triggering button-->
                <a class="rotate-btn" data-card="card-1"><i class="fa fa-repeat"></i> Click here to rotate</a>
            </div>
        </div>
        <!--/.Front Side-->

        <!--Back Side-->
        <div class="face back">

            <!--Content-->
            <h4>About me</h4>
            <hr>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Maxime quae, dolores dicta. Blanditiis rem amet repellat, dolores nihil quae in mollitia asperiores ut rerum repellendus, voluptatum eum, officia laudantium quaerat?</p>
            <hr>
            <!--Social Icons-->
            <ul class="inline-ul">
                <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a></li>
                <li><a class="icons-sm tw-ic"><i class="fa fa-twitter"></i></a></li>
                <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"></i></a></li>
                <li><a class="icons-sm li-ic"><i class="fa fa-linkedin"></i></a></li>
            </ul>
            <!--Triggering button-->
            <a class="rotate-btn" data-card="card-1"><i class="fa fa-undo"></i> Click here to rotate back</a>

        </div>
        <!--/.Back Side-->

    </div>
</div>
<!--/.Rotating card-->
<!-- /.Live preview-->
<script src="/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/tether.min.js" type="text/javascript"></script>
<script src="/static/js/mdb.min.js" type="text/javascript"></script>
<!-- js placed at the end of the document so the pages load faster -->
<%--<script src="/static/js/jquery.scrollTo.min.js"></script>--%>
<%--<script src="/static/js/jquery.nicescroll.js" type="text/javascript"></script>--%>
<%--<script src="/static/js/jquery.sparkline.js" type="text/javascript"></script>--%>
<%--<script src="/static/js/owl.carousel.js"></script>--%>
<%--<script src="/static/js/jquery.customSelect.min.js"></script>--%>


<!--common script for all pages-->
<%--<script src="/static/js/common-scripts.js"></script>--%>

</body>
</html>