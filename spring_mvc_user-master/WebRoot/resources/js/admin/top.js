
//得到className
function getElementsByClassName(n) { 
	var el = [],
  			_el = document.getElementsByTagName('*');
	for(var i=0; i<_el.length; i++ ) {
		if (_el[i].className == n ) {
  			el[el.length] = _el[i];
		}
	}
	return el;
}
//点击菜单
function changeBag(obj){
	var preClass = getElementsByClassName("seartabsChange"); 
	for(var i=0;i<preClass.length;i++){
		preClass[i].className = "seartabs";
		preClass[i].childNodes[0].childNodes[0].className="arg1";
	}
	obj.parentNode.className="seartabsChange";
	obj.childNodes[0].className="arg2";
}
/**
菜单滚动，不要
var Speed_1 = 10; //速度(毫秒)
var Space_1 = 10; //每次移动(px)
var PageWidth_1 = 195 * 1; //翻页宽度
var fill_1 = 0; //整体移位
var MoveLock_1 = false;
var MoveTimeObj_1;
var MoveWay_1 = "right";//向右移动
var Comp_1 = 0;
var AutoPlayObj_1 = null;
var linum = 0;
//获取id对象
function GetObj(objName){
	if(document.getElementById){
		return eval('document.getElementById("'+objName+'")')
	}else{
		return eval('document.all.'+objName)
	}
}
//向左移动
function ISL_GoUp_1(){
	if(MoveLock_1)
		return;
	clearInterval(AutoPlayObj_1);
	MoveLock_1 = true;
	MoveWay_1 = "left";
	ISL_StopUp_1();
	MoveTimeObj_1 = setInterval('ISL_ScrUp_1();',Speed_1);
}
//停止
function ISL_StopUp_1(){
	if(MoveWay_1 == "right"){
		return
	};
	clearInterval(MoveTimeObj_1);
	if((GetObj('menu').scrollLeft - fill_1)% PageWidth_1 != 0){
		Comp_1 = fill_1-(GetObj('menu').scrollLeft % PageWidth_1);
		CompScr_1();
	}else{
		MoveLock_1=false
	}
}
//开始移动
function ISL_ScrUp_1(){
	//如果距左边为0的话
	if(GetObj('menu').scrollLeft <= 195){
		GetObj("lefy").innerHTML = "<a href='#' onmouseup='ISL_StopUp_1()' onmouseout='ISL_StopUp_1()'></a>";
	}
	if(GetObj('menu').scrollLeft < 0){
		GetObj('menu').scrollLeft = GetObj('menu').scrollLeft + GetObj('topMenu').offsetWidth;
	}
	GetObj('menu').scrollLeft -= Space_1;
}
//向右移动
function ISL_GoDown_1(){
	if(MoveLock_1)
		return;
	clearInterval(AutoPlayObj_1);
	MoveLock_1 = true;
	MoveWay_1 = "right";
	ISL_ScrDown_1();
	MoveTimeObj_1=setInterval('ISL_ScrDown_1()',Speed_1);
}
//移动停止
function ISL_StopDown_1(){
	if(MoveWay_1 == "left"){
		return ;
	}
	clearInterval(MoveTimeObj_1);
	if(GetObj('menu').scrollLeft == 0){
		return;
	}else{
		if(GetObj('menu').scrollLeft % PageWidth_1 - (fill_1 >=0 ? fill_1 : fill_1+1)!=0){
			Comp_1=PageWidth_1-GetObj('menu').scrollLeft%PageWidth_1+fill_1;
			CompScr_1();
		}else{
			MoveLock_1=false
		}
	}
	
}
//向右移动
function ISL_ScrDown_1(){
	//如果menu距左不为0可以点击向左的按钮
	if(GetObj('menu').scrollLeft != 0){
		GetObj("lefy").innerHTML = "<a onmousedown='ISL_GoUp_1()' onmouseup='ISL_StopUp_1()' onmouseout='ISL_StopUp_1()' href='javascript:void(0);'></a>";
	}
	if(GetObj('menu').scrollLeft >= GetObj('topMenu').scrollWidth){
		GetObj('menu').scrollLeft = GetObj('menu').scrollLeft - GetObj('topMenu').scrollWidth
	}
	GetObj('menu').scrollLeft += Space_1
}

function CompScr_1(){
	if(Comp_1==0){
		MoveLock_1=false;
		return
	}
	var num,TempSpeed=Speed_1,TempSpace=Space_1;
	if(Math.abs(Comp_1)<PageWidth_1/2){
		TempSpace=Math.round(Math.abs(Comp_1/Space_1));
		if(TempSpace<1){
			TempSpace=1
		}
	}
	if(Comp_1<0){
		if(Comp_1<-TempSpace){
			Comp_1+=TempSpace;num=TempSpace
		}else{
			num=-Comp_1;
			Comp_1=0
		}
		GetObj('menu').scrollLeft -= num;setTimeout('CompScr_1()',TempSpeed)
	}else{
		if(Comp_1>TempSpace){
			Comp_1-=TempSpace;num=TempSpace
		}else{
			num=Comp_1;Comp_1=0
		}
		GetObj('menu').scrollLeft += num;setTimeout('CompScr_1()',TempSpeed)
	}
}
**/
//初始化菜单滚动
/*
function topMenu_init(){
	//设置menu距左0
	GetObj('menu').scrollLeft = 0;
	if(GetObj('menu').scrollLeft == 0){
		GetObj("lefy").innerHTML = "<a href='#' onmouseup='ISL_StopUp_1()' onmouseout='ISL_StopUp_1()'></a>";
	}
	var content = GetObj('topMenu');
	var items = content.getElementsByTagName("ul");
	if(items.length > 0){
		var itemss = items[1].getElementsByTagName("li");
		linum = itemss.length;
		if(itemss.length <= 4){
			GetObj("lefr").innerHTML = "<a href='#' onmouseup='ISL_StopUp_1()' onmouseout='ISL_StopUp_1()'></a>";
		}
	}
}
*/