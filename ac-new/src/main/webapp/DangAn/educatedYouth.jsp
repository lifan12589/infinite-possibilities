<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import ="java.util.*"%>
<%@page import ="java.util.Map"%>
<%@page import ="com.alibaba.fastjson.*"%>
<%@page import ="javax.servlet.http.HttpServletRequest"%>
<%
String webRoot = request.getContextPath();
//JSONObject userInfo = (JSONObject) session.getAttribute("zwdtUser");
//String xm = "";
//String zjhm = "";
//if (userInfo == null) {
	//response.sendRedirect(
		//	"https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://ywtb.sh.gov.cn:18018/ac-product-net/dangAn/Index.do");
//}else{
	// xm = userInfo.getString("username");
	 //zjhm = userInfo.getString("idCardNo");
//}
	
%>   





<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<meta http-equiv="X-UA-Compatible" content="IE=9">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
		<title>长三角政务服务“一网通办”</title>
		<link href="../plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="../plugin/fontawesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_tool_style.css" rel="stylesheet">
		<link rel="stylesheet" href="../plugin/bootstrap-datepicker/css/bootstrap-datetimepicker.min.css">
		<!-- style for large viewport -->
		<link href="http://zwdt.sh.gov.cn/zwdtSW/common/css/common.css" rel="stylesheet">
		<link rel="stylesheet" href="css/courtIndex.css">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/html5shiv.js"></script>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/respond.min.js"></script>
		<![endif]
		-->
		<script type="text/javascript">var webRoot = "<%=webRoot%>"</script>

	</head>
	<body>
		<div id="container">
			<iframe class="customHeader_iframe visible-lg-block" scrolling="no" style="border:none;width:100%;height:69px;overflow:hidden;"  src="http://csj.sh.gov.cn/govService/head/commonHeaderSmall.jsp"></iframe>
			<div class="common-content">
				<!-- <div class="marriage-registration">
					
				</div> -->
				<form class="form-horizontal"  id= "from"  >
					<div class="form-content clearfix">
						<div class="form-group marriage-title">知青档案申报</div>
						<!-- 申请人名称 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="username" class="col-lg-3 col-sm-2 control-label is-required">申请人名称:</label>
							<div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="Username"  name="Username"  readonly value="周金富">
							</div>
						</div>
						<!-- 证件类型 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="card-type" class="col-lg-3 col-sm-2 control-label is-required">证件类型:</label>
							<div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="ApplyerPageType"   name="ApplyerPageType"  readonly value="中华人民共和国居民身份证">
							</div>
						</div>
						<!-- 申请人证件号 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="id-card" class="col-lg-3 col-sm-2 control-label is-required">申请人证件号:</label>
							<div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="ApplyerPageCode"  name="ApplyerPageCode"  readonly value="4616464351343483135">
							</div>
						</div>
						<!-- 联系电话 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="phone" class="col-lg-3 col-sm-2 control-label is-required">联系电话:</label>
							<div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="Mobile" name="Mobile"   value="">
							</div>
						</div>
						
						
						<!-- 收件地区 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="address" class="col-lg-3 col-sm-2 control-label is-required">受理地区:</label>
							<div class="col-lg-9 col-sm-10">
								<select class="form-control"  id="sheng"   name="sheng"   onchange="getName()" >
									<option disabled selected hidden>请选择</option>
									<option value = "上海市">上海市</option>
									<option value = "浙江省">浙江省</option>
									<!--  <option value = "江苏省">江苏省</option>-->
									<option value = "安徽省">安徽省</option>
								</select>
							</div>
						</div>
						<!-- 收件部门 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="department" class="col-lg-3 col-sm-2 control-label is-required">受理地点:</label>
							<div class="col-lg-9 col-sm-10 department">
								<div class="col-xs-6">
									<select class="form-control"   id="shi"  name = "shi"  onchange="getNames(this.value)">
										<option disabled selected hidden>请选择</option>
									</select>
								</div>
								<div class="col-xs-6">
									<select class="form-control"  id="qu" name = "qu">
										<option disabled selected hidden>请选择</option>
									</select>
								</div>
							</div>
						</div>
						<!-- 利用目的 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="purpose" class="col-lg-3 col-sm-2 control-label is-required">利用目的:</label>
							<div class="col-lg-9 col-sm-10">
								<select class="form-control"  id = "UsePurpose"  name = "UsePurpose">
									<option disabled selected hidden>请选择</option>
									<option value = "办理医保">办理医保</option>
									<option value = "户籍证明">户籍证明</option>
									<option value = "其它">其它</option>
								</select>
							</div>
						</div>
						<!-- 上山下乡地点 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="location" class="col-lg-3 col-sm-2 control-label">上山下乡地点:</label>
							<div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="location"  value="" name="Countrysideaddress">
							</div>
						</div>
						<!-- 上山下乡时间 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="time" class="col-lg-3 col-sm-2 control-label">上山下乡时间:</label>
							<div class="input-group col-lg-9 col-sm-10">
								<input type="text" class='form-control form_datetime data-time-pick' id='time' name="Waygoingtime">
						  		<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						  	</div>
							<!-- <div class="col-lg-9 col-sm-10">
								<input type="text" class="form-control" id="time" value="" placeholder="请选择">
							</div> -->
						</div>
						<!-- 档案送交方式 -->
						<div class="form-group col-lg-6 col-sm-12">
							<label for="wife-id-card" class="col-lg-3 col-sm-2 control-label is-required"   id="UseWay"  name="UseWay" >档案送交方式:</label>
							<div class="col-lg-9 col-sm-10">
								<div class="radio-inline">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
										到查收馆取件
									</label>
								</div>
								<div class="radio-inline">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
										挂号信
									</label>
								</div>
								<div class="radio-inline">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">
										快递<span class="tip-txt">(到付)</span>
									</label>
								</div>
							</div>
						</div>
						<!-- 档案送交地址 -->
						<div class="col-sm-12 file-address">
							<!-- 收件人 -->
							<div class="form-group col-lg-6 col-sm-12">
								<label for="recipient" class="col-lg-3 col-sm-2 control-label is-required">收件人:</label>
								<div class="col-sm-10 col-lg-9">
									<input type="text" class="form-control" id="Addressee" value=""  name="Addressee">
								</div>
							</div>
							<!-- 邮件地址 -->
							<div class="form-group col-lg-6 col-sm-12">
								<label for="email-address" class="col-lg-3 col-sm-2 control-label is-required">邮件地址:</label>
								<div class="col-lg-9 col-sm-10">
									<input type="text" class="form-control" id="Mailingaddress" value=""  name="Mailingaddress"  placeholder="XX省XX市XX区XXX">
								</div>
							</div>
							<!-- 邮政编码 -->
							<div class="form-group col-lg-6 col-sm-12">
								<label for="postal-code" class="col-lg-3 col-sm-2 control-label is-required" >邮政编码:</label>
								<div class="col-lg-9 col-sm-10">
									<input type="text" class="form-control" id="yzbm" value=""  name = "yzbm">
								</div>
							</div>
						</div>
						<input type="text"  style="display:none" class="form-control" id="postal-code" value="03"  name = "ArchivesType">
					</div>
					
					<!-- 提交按钮 -->
					<div class="form-group">
						<div class="col-sm-12 marriage-submit">
							<button type="button" class="btn btn-default marriage-btn"  onClick ="submit3()">提 交</button>
						</div>
					</div>
				</form>
			</div>
			<iframe class="customFooter_iframe visible-lg-block" scrolling="no" style="height: 169px;" src="http://csj.sh.gov.cn/govService/foot/commonFooter.html" frameborder="0"></iframe>
		</div>
<!-- 
		<script type="text/javascript">var webRoot = '/govPortals'</script>
		<script src="//zwdt.sh.gov.cn/log/wonders.log.min.js"></script> -->
		<script src="../plugin/jquery/jquery-1.12.0.min.js"></script>
		<script src='./js/ty.js'></script>
		<script src='../plugin/bootstrap/js/bootstrap.min.js'></script>
		<script src="../plugin/bootstrap-datepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../plugin/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/zgsh/jhelper_config.js" charset="gb2312"></script>
   		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_2.0.js" charset="gb2312"></script>
		<!-- <script src="http://zwdt.sh.gov.cn/govPortals/template/js/head.js"></script> -->
		<script>
			function doJump(url,name){
				trackEventByUrl(url, name);
				window.open(url, '_blank')
			}
			$(document).ready(function() {
					$('input[type=radio][name=optionsRadios]').change(function() {
						const fileDom = $(".file-address")
						if (this.value === 'option1' && fileDom.hasClass('show-file')) {
							$(".file-address").removeClass("show-file")
						} else {
							$(".file-address").addClass("show-file")
						}

					});

					$("#time").datetimepicker({
						language: "zh-CN",
						autoclose: true, //选择后自动关闭
						format: "yyyy-mm-dd",//日期格式
						minView: 4,
					})
			});

		
			
		</script>
	</body>
</html>