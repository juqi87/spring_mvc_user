function updateItem() {
	var id = $("#itemId").val();
	var name = $("#itemName").val();
	var url = $("#itemURL").val();
	if (id == "") {
		alert("请选择目录！");
		return false;
	}
	if (name == "") {
		alert("请输入目录名称！");
		return false;
	}
	if(!confirm("确认修改？"))return;
	$("#frmp").attr("action", "../admin/gotoUpdateItem");
	$("#frmp").submit();
}

function delItem() {
	if(!confirm("确认删除？"))return;
	$("#frmp").attr("action", "../admin/gotoDeletItem");
	$("#frmp").submit();
}

function addItem() {
	var id = $("#fatherItemId").val();
	var name = $("#newItemName").val();
	if (id == "") {
		alert("请选择目录！");
		return false;
	}
	if (name == "") {
		alert("请输入目录名称！");
		return false;
	}
	$("#frm").attr("action", "../admin/gotoAddItem");
	$("#frm").submit();
}