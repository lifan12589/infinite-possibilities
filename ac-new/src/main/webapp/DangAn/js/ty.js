

//获取选中的省下面所有的市
function getName(){
	var sheng = $('#sheng').find("option:selected").text();
	if(sheng ==""){
		//按钮设置为不可用
		$("#shi").attr("disabled",true);
		return;
	}
	$.ajax({
		type : "POST",
		//url : "http://localhost:8080/dangAn/shengShiQu.do?rootCode=area&code="+sheng,
				url : "http://localhost:8080/dangAn/shiQu.do?SHENG="+sheng,
		data : {},
		dataType : 'json',
		success : function(res) {
			$("#shi").empty();
			$("#shi").append("<option value=''  style='display:none'>请选择</option>");

			for(i=0 ; i<res.length;i++){
			$("#shi").append(
			"<option value='"+res[i].code+"'>"+res[i].name
			+ "</option>");
			}
			
		}
	});
}

//获取选中的市下面所有的档案馆
function getNames(value){
	var shi =$("#shi").val();
	if(shi ==""){
		//按钮设置为不可用
		$("#shi").attr("disabled",true);
		return;
	}
	$.ajax({
		type : "POST",
		//url : "http://localhost:8080/dangAn/shengShiQu.do?rootCode=area&code="+shi,
				url : "http://localhost:8080/dangAn/shiQu.do?SHI_ID="+shi,
		data : {},
		dataType : 'json',
		success : function(res) {
			$("#qu").empty();
			$("#qu").append("<option value=''  style='display:none'>请选择</option>");

			for(i=0 ; i<res.length;i++){
			$("#qu").append(
			"<option value='"+res[i].code+"'>"+res[i].name
			+ "</option>");
			}
			
		}
	});
}




//婚姻
function submit1(){
	var Mobile = $('#Mobile').val();
	var sheng = $('#sheng').val();
	var shi = $('#shi').val();
	var qu = $('#qu').val();
	var Man = $('#Man').val();
	var Woman = $('#Woman').val();
	var UsePurpose = $('#UsePurpose').val();
	var Radios2 = $('#optionsRadios2').val();
	var Radios3 = $('#optionsRadios3').val();
	var val=$('input:radio[name="optionsRadios"]:checked').val();
	var yzbm = $('#yzbm').val();
	var Addressee = $('#Addressee').val();
	var Mailingaddress = $('#Mailingaddress').val();
	if(Mobile==''||sheng==null||shi==null||qu==null||UsePurpose==null||Man==''||Woman=='' ){
		alert("请完善申报信息！");
		return;
	}
	if(val==Radios2||val==Radios3){
		if(yzbm==''||Addressee==''||Mailingaddress==''){
			alert("请完善申报信息！");
			return;
		}
	}
		$.ajax({
			type : "POST",
			 async: false,
			 url : "http://localhost:8080/dangAn/sendAndSave.do",
			//url : webRoot + "/dangAn/sendAndSave.do",
			data : $('#from').serialize(),
			dataType : "json",
			error : function() {
				alert("系统繁忙，请稍后再试！");
			},
			success : function(resData) {
				if(resData.code=="200"){
					window.location.href =  "http://localhost:8080/DangAn/DangAn/success.jsp";
					//window.location.href = webRoot + "/DangAn/DangAn/success.jsp";
				}else {
					alert("系统繁忙，请稍后再试！");
				}
			}
		});
	}


//独生子女
function submit2(){
	var Mobile = $('#Mobile').val();
	var sheng = $('#sheng').val();
	var shi = $('#shi').val();
	var qu = $('#qu').val();
	var UsePurpose = $('#UsePurpose').val();
	var Filedate = $('#Filedate').val();
	var Childname = $('#Childname').val();
	var Radios2 = $('#optionsRadios2').val();
	var Radios3 = $('#optionsRadios3').val();
	var val=$('input:radio[name="optionsRadios"]:checked').val();
	var yzbm = $('#yzbm').val();
	var Addressee = $('#Addressee').val();
	var Mailingaddress = $('#Mailingaddress').val();
	if(Mobile==''||sheng==null||shi==null||qu==null||UsePurpose==null ||Filedate==""||Childname==''){
		alert("请完善申报信息！");
		return;
	}
	if(val==Radios2||val==Radios3){
		if(yzbm==''||Addressee==''||Mailingaddress==''){
			alert("请完善申报信息！");
			return;
		}
	}
		$.ajax({
			type : "POST",
			 async: false,
			 url : "http://localhost:8080/dangAn/sendAndSave.do",
			//url : webRoot + "/dangAn/sendAndSave.do",
			data : $('#from').serialize(),
			dataType : "json",
			error : function() {
				alert("系统繁忙，请稍后再试！");
			},
			success : function(resData) {
				if(resData.code=="200"){
					window.location.href =  "http://localhost:8080/DangAn/DangAn/success.jsp";
					//window.location.href = webRoot + "/DangAn/DangAn/success.jsp";
				}else {
					alert("系统繁忙，请稍后再试！");
				}
			}
		});
	}

//知青
function submit3(){
	var Mobile = $('#Mobile').val();
	var sheng = $('#sheng').val();
	var shi = $('#shi').val();
	var qu = $('#qu').val();
	var UsePurpose = $('#UsePurpose').val();
	var Radios2 = $('#optionsRadios2').val();
	var Radios3 = $('#optionsRadios3').val();
	//判断选中的radio
	var val=$('input:radio[name="optionsRadios"]:checked').val();
	var yzbm = $('#yzbm').val();
	var Addressee = $('#Addressee').val();
	var Mailingaddress = $('#Mailingaddress').val();
	if(Mobile==''||sheng==null||shi==null||qu==null||UsePurpose==null ){
		alert("请完善申报信息！");
		return;
	}
	if(val==Radios2||val==Radios3){
		if(yzbm==''||Addressee==''||Mailingaddress==''){
			alert("请完善申报信息！");
			return;
		}
	}
		$.ajax({
			type : "POST",
			 async: false,
			 url : "http://localhost:8080/dangAn/sendAndSave.do",
			//url : webRoot + "/dangAn/sendAndSave.do",
			data : $('#from').serialize(),
			dataType : "json",
			error : function() {
				alert("系统繁忙，请稍后再试！");
			},
			success : function(resData) {
				if(resData.code=="200"){
					window.location.href =  "http://localhost:8080/DangAn/DangAn/success.jsp";
					//window.location.href = webRoot + "/DangAn/DangAn/success.jsp";
				}else {
					alert("系统繁忙，请稍后再试！");
				}
			}
		});
	}


