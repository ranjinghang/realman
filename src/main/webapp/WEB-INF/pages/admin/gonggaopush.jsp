<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">发布公告</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form name="reset" class="form-horizontal" role="form" action="" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">公告</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="gonggaoContent" id="inputEmail3"
                                       placeholder="请输入公告">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" onclick="gonggaopush()">提交</button>
                            <button class="btn btn-default">重置</button>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script>
    $("#nav li:nth-child(5)").addClass("active")

    function gonggaopush() {
        var gonggaopush = $("#inputEmail3").val();
        $.ajax({
            type: "GET",
            url: "/admin/gonggaoPush?gonggaopush=" + gonggaopush,
            dataType: "json",
            success: function (data) {
                alert("发布完成");
                window.location.reload();
            }
        });
    }
</script>
</html>