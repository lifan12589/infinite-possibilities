<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import ="java.util.*"%>
<%@page import ="java.util.Map"%>
<%@page import ="com.alibaba.fastjson.*"%>
<%@page import ="javax.servlet.http.HttpServletRequest"%>
<%
	String webRoot = request.getContextPath();
    //JSONObject userInfo = (JSONObject)request.getSession().getAttribute("userInfo"); 
    JSONObject success = (JSONObject)request.getSession().getAttribute("success"); 
    String ApplyNo = "csj00000000000000";
    String ArchivesType = "01";
   // if (userInfo == null) 
  //  	response.sendRedirect("https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://ywtb.sh.gov.cn:18018/ac-product-net/dangAn/Index.do");
    if(success!=null){
    	ApplyNo = success.getString("ApplyNo");
    	ArchivesType = success.getString("ArchivesType");
    	System.out.print("111--->"+ArchivesType);
    }
    if(ArchivesType.equals("01")){
    	ArchivesType="婚姻登记档案申请";
    }else if(ArchivesType.equals("02")){
    	ArchivesType="独生子女档案申请";
    }else if(ArchivesType.equals("03")){
    	ArchivesType="知青档案申请";
    }
    System.out.print("222--->"+ArchivesType);
    Calendar cal = Calendar.getInstance();
	 cal.setTime(new Date());
	int year=cal.get(Calendar.YEAR);
	int month=cal.get(Calendar.MONTH)+1;
	int date=cal.get(Calendar.DATE);
	int hour=cal.get(Calendar.HOUR);
	int minute=cal.get(Calendar.MINUTE);
	String nowTime = year+"年"+month+"月"+date+"日 "+hour+":"+minute;
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
		<link href="../plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="../plugin/fontawesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_tool_style.css" rel="stylesheet">
		<!-- style for large viewport -->
		<link href="http://zwdt.sh.gov.cn/zwdtSW/common/css/common.css" rel="stylesheet">
		<link rel="stylesheet" href="css/courtIndex.css">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/html5shiv.js"></script>
		<script src="http://zwdt.sh.gov.cn/zwdtSW/plugin/supportolder/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript">var ApplyNo = "<%=ApplyNo%>"</script>
		<script type="text/javascript">var ArchivesType = "<%=ArchivesType%>"</script>
		<script type="text/javascript">var nowTime = "<%=nowTime%>"</script>
	</head>
	<body>
		<div id="container">
			<iframe class="customHeader_iframe visible-lg" scrolling="no" style="border:none;width:100%;height:69px;overflow:hidden;"  src="http://csj.sh.gov.cn/govService/head/commonHeaderSmall.jsp"></iframe>
			<div class="bsznContent">
				<div class="success">
					<div>
						<b class="glyphicon glyphicon-ok"></b>
						<span>申报提交成功</span>
					</div>
					<p>统一审批编码：<span><%=ApplyNo%></span></p>
					<p>申请内容：<span><%=ArchivesType%></span></p>
					<p style="margin-top:30px;">您的申请于 <%=nowTime%>  提交成功</p>
					<p>您可至一网通办<a href="http://zwdt.sh.gov.cn">（http://zwdt.sh.gov.cn）</a>查询办件信息</p>
				</div>
			</div>
			<iframe class="customFooter_iframe visible-lg" scrolling="no" src="http://csj.sh.gov.cn/govService/foot/commonFooter.html" frameborder="0"></iframe>
		</div>

		<!-- <script type="text/javascript">var webRoot = '/govPortals'</script>
		<script src="//zwdt.sh.gov.cn/log/wonders.log.min.js"></script> -->
		<script src="../plugin/jquery/jquery-1.12.0.min.js"></script>
		<script src='../plugin/bootstrap/js/bootstrap.js'></script>
		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/zgsh/jhelper_config.js" charset="gb2312"></script>
   		<script src="http://www.shanghai.gov.cn/jhelper_V2.0/jhelper_2.0.js" charset="gb2312"></script>
		<script src="http://zwdt.sh.gov.cn/govPortals/template/js/head.js"></script>
		<script>
			function doJump(url,name){
				trackEventByUrl(url, name);
				window.open(url, '_blank')
			}
		</script>
	</body>
</html>