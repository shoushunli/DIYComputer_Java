<%--
  Created by IntelliJ IDEA.
  User: shoushunli
  Date: 2020/11/28
  Time: 10:45 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/DIYComputer/">
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#ajax").click(function (){
                $.getJSON("http://127.0.0.1:8080/DIYComputer/computerServlet",
                    "action=list&cpu=heavy&gpu=extreme&memory=small&power=0&pccAndMotherboard=three&ssd=one&hdd=two&maximum=1200",
                    function (data){
                    $("#res").html(data[0].cpu.sku);
                })
            });
        });
    </script>
</head>
<body>
<button id="ajax">ajax</button>
<span id="res">woha</span>
</body>
</html>
