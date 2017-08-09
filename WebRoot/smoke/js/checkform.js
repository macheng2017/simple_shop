function regForm(){
	var a = document.getElementById("province").value;
	var b = document.getElementById("city").value;
   
	var patnum = /[^0-9.]/g;
	flags = patnum.test(document.getElementById("number").value);
	if(flags){
		alert("请输入正确的数量！");
		document.getElementById("number").focus();
		return false;
	}  
	
    
	/*
	if (a == "" || b == "" ){
		alert("对不起，请选择所在地！")
		document.getElementById("address").focus();
		return false;
	}
*/
	if (document.getElementById("address").value == "" ){
		alert("对不起，请输入您的详细收货地址！")
		document.getElementById("address").focus();
		return false;
		}
		
	if (document.getElementById("address").value.length<2 || document.getElementById("address").value.length>101){
		alert("对不起，您输入的地址长度不符合要求！")
		document.getElementById("address").focus();
		return false;
		}
		
	if (document.getElementById("name").value == ""){
		alert("对不起，请填写订购人姓名！");
		document.getElementById("name").focus();
		return false;
	}
	var mob =document.getElementById("mob").value;
	mob=mob.replace(/\s+/g,"");
	mob=mob.replace(/o/g, '0');
	mob=mob.replace(/O/g, '0');
	if(mob=="" || mob.length<11){
	 	alert('手机号为空或有误.');
		document.getElementById("mob").focus();
		return false;
	}else{ 
		var pattern = /^1\d{10}$/;
		flag = pattern.test(mob);
		if(!flag){
			alert("您输入的手机号有误！");
			document.getElementById("mob").focus();
			return false;
		} 
		
	}
  // if($("#form_code").val()!=''){ return false;}
  // 	$("#form_code").val(Date.parse(new Date())/1000);
    return true;
}

