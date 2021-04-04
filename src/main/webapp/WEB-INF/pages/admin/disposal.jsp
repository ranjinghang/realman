<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>处分显示</title>

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
                        <h1 class="col-md-5">处分列表</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>处分人学号</th>
                        <th>处分内容</th>
                        <th>处分时间</th>
                        <th>申诉内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${disposalInfo.list}" var="item">
                        <tr>
                            <td>${item.studentId}</td>
                            <td>${item.content}</td>
                            <td>${item.createTime}</td>
                            <td>${item.shensu}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="panel-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共${disposalInfo.pages} 页，共${disposalInfo.total} 条数据。 每页
                            <select class="form-control" id="changPageSize" onchange="changPageSize()">
                                <option>请选择</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select> 条
                        </div>
                    </div>
                </div>

                <%--分页--%>
                <div class="panel-footer">
                    <nav style="text-align: center">
                        <ul class="pagination">
                            <li>
                                <a aria-label="Previous"
                                   href="${pageContext.request.contextPath}/student/chufen?page=1&pageSize=${disposalInfo.pageSize}">首页</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/student/chufen?page=${disposalInfo.pageNum-1}&pageSize=${disposalInfo.pageSize}">上一页</a>
                            </li>

                            <c:forEach begin="1" end="${disposalInfo.pages}" var="i">
                                <c:if test="${i==disposalInfo.pageNum}">
                                    <li><a style="background-color: #2aabd2"
                                           href="${pageContext.request.contextPath}/student/chufen?page=${i}&pageSize=${disposalInfo.pageSize}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${i!=disposalInfo.pageNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/student/chufen?page=${i}&pageSize=${disposalInfo.pageSize}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>

                            <li>
                                <a href="${pageContext.request.contextPath}/student/chufen?page=${disposalInfo.pageNum+1}&pageSize=${disposalInfo.pageSize}">下一页</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/student/chufen?page=${disposalInfo.pages}&pageSize=${disposalInfo.pageSize}"
                                   aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </nav>
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
<script type="text/javascript">

    <%--设置菜单--%>
    $("#nav li:nth-child(4)").addClass("active")

    //改变每页显示条数
    function changPageSize() {
        var pageSize = $("#changPageSize").val();
        location.href = "${pageContext.request.contextPath}/student/chufen?page=1&pageSize=" + pageSize;
    }

    function sleep(numberMillis) {
        var now = new Date();
        var exitTime = now.getTime() + numberMillis;
        while (true) {
            now = new Date();
            if (now.getTime() > exitTime)
                return;
        }
    }

    $("#sub").click(function () {
        findByName();
    });

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        var modal = $(this)
        modal.find('.modal-title').text('申诉')
        modal.find('.modal-body input').val(recipient)
    });

    function shensu() {
        var id = $("#userId").val();
        var shensuContent = $("#contentChufen").val();

        if (!shensuContent) {
            confirm("请输入内容");
        } else {
            $.ajax({
                type: "GET",
                url: "/student/shensu?id=" + id,
                data: {
                    "shensuContent": shensuContent
                },
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    alert("处分完成");
                    window.location.reload();
                }
            });
        }
    };


</script>
</html>