var local = window.location;
var basepath = local.protocol+"//"+local.host;

/** 
* http://localhost:8083/proj 
*/  
/*function getRootPath(){  
   //获取当前网址，如： http://localhost:8083/proj/meun.jsp  
   var curWwwPath = window.document.location.href;  
   //获取主机地址之后的目录，如： proj/meun.jsp  
   var pathName = window.document.location.pathname;  
   var pos = curWwwPath.indexOf(pathName);  
   //获取主机地址，如： http://localhost:8083  
   var localhostPath = curWwwPath.substring(0, pos);  
   //获取带"/"的项目名，如：/proj  
   var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
   return(localhostPath + projectName);  
}  
var basepath = getRootPath();*/

////////////////订单相关/////////////////////////////////////////
$(function(){
	 
	getPrice();
    $('.unique input:radio').change(function(){
        getPrice(); 
    });
    $('.number').blur(function(){
      getPrice();
    }); 
    
    $('#submit').click(function(){
    	submitOrder();
    });
  
});


var choosed = function(id){
    return document.getElementById(id);
}
function getPrice(){
    var a = choosed('number').value;
    var b = document.getElementsByName('wfproduct');
    for(var i = 0; i < b.length; i++){
        if(b[i].checked == true){
            var c = b[i].getAttribute("price");
            var d = b[i].getAttribute("title");
            $('#totalPrice').html(c*a);
            $('.totalPrice').html(c*a);
            $('#totalPrice').text(c*a);
            $('.totalPrice').text(c*a);
            $('#totalPrice1').val(c*a);
            $('#price').val(c);
            $('#orderDetail').val(d);
            choosed('totalPrice1').value = c*a;
            choosed('price').value = c;
            choosed('orderDetail').value = d;
        }
    }
} 
//外层提交订单
function topsubmit(){
	var d =$('#address').val();
	var n = $('#name').val();
	var p =$('#mob').val();
	if(d==""&&n==""&&p==""){
		return false;
	}
	submitOrder();
}

//订单提交
var isSubmitOrder = 0;
function submitOrder(){ 
	if(isSubmitOrder==1){
		alert('已经提交，请等候');
		return false;
	}
	if(isSubmitOrder==2){
		alert('您的订单已经提交，请留意电话，发货前将会有客服和您电话确认，谢谢您的购买！');
		return false;
		/*
		if(window.confirm('您的清单已经提交，请留意电话，发货前将会有客服和您电话确认，谢谢您的购买！')){
			//isSubmitOrder=0;
		}else{
			return false;
		} 
		*/
	}
	
	if(!regForm()){
		return false;
	}
	isSubmitOrder = 1; 
	$.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType:"JSON", 
        url: basepath+"/servlet/OrderServlet?method=add_order",
        data:JSON.stringify(GetJsonData()),
       
        success: function(data){ 
        	if("1"==data.flag){
        		alert("【订单提交成功】恭喜您，正品"+productnameCh+"订单提交成功，我们会尽快和您电话联系，并发货!");
        		isSubmitOrder = 2;
        	}else{
        		isSubmitOrder = 0;
        		//error
        		alert(data.msg)
        	}
        },
        error:function(data){
        	//isSubmitOrder = false;
        	//alert(JSON.stringify(data))
        	alert("网络出现异常，请联系在线客服下单，谢谢！，鉴于此情况，客服将会给您8折优惠！")
        }
    })
}
function getReferer(){
	try{ 
		 return  top.document.referrer ; 
	}catch (e) {
		 
	}
}

function getPage(){
	try{ 
		//return opener?opener.location.href:(top.document.referrer?top.document.referrer:top.location.href);
		if(top.location.href&&top.location.href!=null){
			return top.location.href;
		}else{ 
			return top.location.href;
		}
	}catch (e) {
		 
	}
}

function GetJsonData() {
    var json = {
   /*     "classid": 2,
        "name": $("#tb_name").val(),
        "zlclass": "测试类型1,测试类型2,测试类型3",
        "pname": $("#tb_contact_people").val(),
        "tel": $("#tb_contact_phone").val(),*/
        "productname":$('#productname').val(),//产品名称
        "orderDetail":$('#orderDetail').val(),//产品套餐
        "ordernum":$('#number').val(),
        "price":$('#price').val(),

        "province":$('#province').val(),
        "city":$('#city').val(),
        "zone":$('#zone').val(),
        "address":$('#address').val(),

        "name":$('#name').val(),
        "phone":$('#mob').val(),
        "qq":$('#qq').val(),
        "paytype":$('#paytype').val(),
        "guestremark":$('#guestremark').val(),
        "referer":getReferer(), 
        "device":_device,
        "page":getPage()
    };
    return json;
}

/*data:{
productname:$('#productname').val(),//产品名称
orderDetail:$('#orderDetail').val(),//产品套餐
ordernum:$('#number').val(),
price:$('#price').val(),

province:$('#province').val(),
city:$('#city').val(),
zone:$('#zone').val(),
address:$('#address').val(),

name:$('#name').val(),
phone:$('#mob').val(),
qq:$('#qq').val(),

paytype:$('#paytype').val(),

guestremark:$('#guestremark').val(),
referer:getReferer(), 
device:_device,
page:getPage()
},*/