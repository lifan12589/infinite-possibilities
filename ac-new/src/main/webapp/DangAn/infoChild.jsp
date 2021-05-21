<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import ="java.util.*"%>
<%@page import ="java.util.Map"%>
<%@page import ="com.alibaba.fastjson.*"%>
<%@page import ="javax.servlet.http.HttpServletRequest"%>

<%
	String webroot = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<meta http-equiv="X-UA-Compatible" content="IE=9">
		<meta name="renderer" content="webkit">
		<!-- <meta name="viewport" content="width=1190"> -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
		<title>长三角政务服务“一网通办”</title>
		<link href="http://csj.sh.gov.cn/govService/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="http://csj.sh.gov.cn/govService/plugin/fontawesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_tool_style.css" rel="stylesheet">
		<!-- style for large viewport -->
		<link href="http://zwdt.sh.gov.cn/zwdtSW/common/css/common.css" rel="stylesheet">
		<link rel="stylesheet" href="css/courtIndex.css">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/html5shiv.js"></script>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="container">
			<iframe class="customHeader_iframe visible-lg" scrolling="no" style="border:none;width:100%;height:69px;overflow:hidden;"  src="http://csj.sh.gov.cn/govService/head/commonHeaderSmall.jsp"></iframe>
			<div class="bsznContent">
				<h4><i class='leftBg visible-lg-inline-block'></i>长三角地区民生档案“异地查档、便民服务”上网查档须知<i class='rightBg visible-lg-inline-block'></i></h4>
				<h6>办理须知</h6>
				<p class='title'>一、查档范围</p>
				<p class='text'>申请查询的档案应为长三角地区内（上海市、江苏省、浙江省、安徽省）各级国家综合档案馆保管的民生档案（目前限定婚姻档案、独生子女档案、知青档案三种），且利用者为档案所记载的当事人本人，不得申请利用其他公民或组织的档案。</p>
				<p class='title'>二、申请方式</p>
				<p class='text'>（一）通过长三角“一网通办”平台或官方APP申请。利用者进入长三角“一网通办”平台或官方APP，注册并通过政务服务网高级实名用户认证，登录长三角“一网通办”平台或官方APP端并在首页“档案查询”中选择需查询的民生档案门类，按要求填写并提交查档申请，同时确定档案复制件或证明送交方式。</p>
				<p class='text'>（二）到档案馆查档。到档案馆查外省市档案。利用者持有效身份证明到三省一市综合档案馆提出查档请求。受理档案馆（接受申请的档案馆）工作人员通过检验身份证等方式检查其身份信息后，填写申请单，向相应省市提交查档申请，同时确定档案复制件或证明送交方式。</p>
				<p class='title'>三、受理情况反馈</p>
				<p class='text'>利用者自提交申请资料起2个工作日内，“一网通办”平台通过短信形式向利用者反馈查档结果，期间可实时查询办理情况。在规定期限内不能作出答复的，经受查档案馆负责人批准，可以适当延长期限，但最长不超过2个工作日，延长期限的理由“一网通办”平台通过短信形式向利用者反馈。</p>
				<p class='text'>利用者收到查档结果后，可在长三角“一网通办”平台或官方APP端和三省一市查档系统进行满意度评价。</p>
				<p class='title'>四、送交档案</p>
				<p class='text'>档案复印件或证明根据申请时提交的并经档案馆工作人员审核同意的方式送交档案：到指定馆（受理馆）自取、长三角统一物流平台快递送达（费用自理）。</p>
				<p class='text'>利用者须自觉遵守档案工作法律法规及相关工作制度，严格执行平台操作规范，确保档案信息安全，不违规留存、使用、披露、公布或者允许他人违规留存、使用、披露、公布在此项工作中接触的档案信息，并依法承担违反承诺的法律责任。</p>
				<div class='btn-box'>
					<button type='button'  onClick ="Jump()" class='btn btn-danger btn-lg'>开始办理</button>
				</div>
			</div>
			<iframe class="customFooter_iframe visible-lg" scrolling="no" src="http://csj.sh.gov.cn/govService/foot/commonFooter.html" frameborder="0"></iframe>
		</div>

		<script type="text/javascript">var webRoot = '/govPortals'</script>
		<script type="text/javascript">var webroot = "<%=webroot%>"</script>
		<script src="//zwdt.sh.gov.cn/log/wonders.log.min.js"></script>
		<script src="http://csj.sh.gov.cn/govService/plugin/jquery/jquery-1.12.0.min.js"></script>
		<script src='http://csj.sh.gov.cn/govService/plugin/bootstrap/js/bootstrap.js'></script>
		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/zgsh/jhelper_config.js" charset="gb2312"></script>
   		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_2.0.js" charset="gb2312"></script>
		<script src="http://zwdt.sh.gov.cn/govPortals/template/js/head.js"></script>
		<script>
			function doJump(url,name){
				trackEventByUrl(url, name);
				window.open(url, '_blank')
			}
			
			function Jump(){
				//跳人脸
//				window.location.href =  webroot+"/netapply/apply/dangAnFace.do?ArchivesType=02";
				window.location.href =  "./onlyChild.jsp";
			}
			
			
			
		</script>
	</body>
</html>