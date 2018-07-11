<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type">
    <title>ztree</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript">
        $(function () {
                $.messager.show({
                    title:"标题",
                    msg:"内容",
                    timeout:5000,
                    showType:'show'
                });
        });

    </script>
</head>
<body class="easyui-layout">

<div title="管理系统" style="height: 100px" data-options="region:'north'">
    <a id="control_panel" class="easyui-menubutton" data-options="menu:'#control_panel_menu'">控制面板</a>
    <div id="control_panel_menu">
        <div data-options="iconCls:'icon-edit'">修改密码</div>
        <div>联系管理员</div>
        <div>退出登录</div>
    </div>
</div>
<div title="菜单系统" style="width: 200px" data-options="region:'west'">
    <div class="easyui-accordion" data-options="fit:true">
        <div title="ZTree" data-options="iconCls:'icon-cut'">
            <ul id="tree" class="ztree"></ul>
            <script type="text/javascript">
                $(function () {
                    //页面加载完成后，执行这段代码----动态创建ztree
                    var setting = {
                        data: {
                            simpleData: {
                                enable: true//使用简单json数据构造ztree节点
                            }
                        },
                        callback: {

                            onClick: function (event, treeId, treeNode) {
                                if (treeNode.page != null) {
                                    if ($("#center").tabs("exists", treeNode.name)) {
                                        $("#center").tabs("select", treeNode.name);
                                    } else {
                                        $("#center").tabs("add", {
                                            title: treeNode.name,
                                            closeable: true,
                                            content: treeNode.page == "" ? "没有数据" : '<iframe frameborder="0" height="100%" width="100%" src="' + treeNode.page + '"></iframe>'
                                        });
                                    }
                                }
                            }
                        }
                    };
                    var url = "${pageContext.request.contextPath }/json/menu.json";
                    $.post(url, {}, function (data) {
                        //调用API初始化ztree
                        $.fn.zTree.init($("#tree"), setting, data);
                    }, "json");

                });
            </script>

        </div>

        <div title="ZTree">

        </div>

    </div>

</div>
<div title="center" data-options="region:'center'">
    <div class="easyui-tabs" data-options="fit:true" id="center">
        <div title="sss">sss</div>
        <div title="ssds">sss</div>
    </div>
</div>
<div title="left" style="width: 200px" data-options="region:'east'">

</div>
<div title="bottom" style="height: 200px" data-options="region:'south'">

</div>
</body>
</html>