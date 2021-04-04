<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    $.ajax({
        type: "GET",
        url: "/student/gonggao",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            $("#findByName").val(data.msg);
        }
    });
</script>

<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <h3 class="col-md-5">公告信息</h3>
            <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" id="form1"
                  method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="" name="findByName" id="findByName">
                </div>
            </form>

        </div>
    </div>
</div>