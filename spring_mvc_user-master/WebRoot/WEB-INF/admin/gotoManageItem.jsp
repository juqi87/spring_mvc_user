<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="../resources/zTree/css/zTreeStyle/zTreeStyle.css">
  	<script type="text/javascript" src="../resources/js/admin/gotoManageItem.js"></script>
	<script type="text/javascript" src="../resources/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../resources/zTree/js/jquery.ztree.core-3.5.js"></script>
  	<script type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: true,
				showLine: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};
		
	
	var zNodes =[${allItem}];

	function onClick(e, treeId, treeNode) {

		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var sNodes = treeObj.getSelectedNodes();
		if (sNodes.length > 0) {
			var id = sNodes[0].id;
			var name = sNodes[0].name;
			var url = sNodes[0].visitUrl;
			$("#itemId").val(id);
			$("#fatherItemId").val(id);
			$("#itemName").val(name);
			$("#itemURL").val(url);
			$("#itemName").attr("readonly", false);
			$("#itemURL").attr("readonly", false);
		}
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	</script>
  </head>
  
 <body>
 	<div style="text-align: center;">
   		<h3 style="margin-top: 10px;">模块管理</h3>
   	</div>
   	<br/>
    <div class="mainc2">
       <table border="0" cellspacing="0" cellpadding="0" class="maintable" id="tab" name="tab">
          <tr align="left" style="height: 350px;">
            <td valign="top" width="20%">
				<div class="content_wrap">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</td>
            <td valign="top" width="40%"><br />
            	<form action="../item/gotoUpdateItem" method="post" id="frmp">
	            	模 块 ID：<input type="text" name="itemId" id="itemId" readonly="readonly"/><br/>
	            	模块名称：<input type="text" name="itemName" id="itemName"/><br/>
	            	链接地址：<input type="text" name="itemURL" id="itemURL"/><br/>&nbsp;<br/>
            	</form>
            	<input type="button" name="updateItem" id="updateItem" class="use_input" onclick="updateItem()" value="修改" /> &nbsp;
            	<input type="button" name="delItem" id="delItem" class="use_input" onclick="delItem()" value="删除" />
            </td>
            <td valign="top" width="40%"><br/>
	            <form action="../item/gotoUpdateItem" method="post" id="frm">
	            	所属上级模块ID：<input type="text" name="fatherItemId" id="fatherItemId" readonly="readonly" /><br/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模块名称：<input type="text" name="newItemName" id="newItemName"/><br/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;链接地址：<input type="text" name="newItemURL" id="newItemURL"/><br/>&nbsp;<br/>
	            </form>
            	<input type="button" name="addItem" id="addItem" class="use_input" onclick="addItem()" value="添加" /> &nbsp;
            </td>
          </tr>
        </table>
    </div>
 </body>
</html>
