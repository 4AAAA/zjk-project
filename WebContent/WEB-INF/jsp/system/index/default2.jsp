﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 百度echarts -->
<script src="plugins/echarts/echarts.min.js"></script>
<script type="text/javascript">
setTimeout("top.hangge()",500);
</script>
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
<%-- 							<div  style="width: 1000px;">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<td style="width: 100px;">订单金额</td>
										<td><font style="color: red"><b>${pd.MONEY }</b></font>&nbsp;元</td>
										<td style="width: 100px;">实收金额</td>
										<td><font style="color: red"><b>${pd.INCOME }</b></font>&nbsp;元</td>
										<td style="width: 100px;">欠费金额</td>
										<td><font style="color: red"><b>${pd.OUTMONEY}</b></font>&nbsp;元</td>
										<td style="width: 120px;">维修成本</td>
										<td><font style="color: red"><b>${pd.QQ }</b></font>&nbsp;元</td>
										<td style="width: 120px;">利润</td>
										<td><font style="color: red"><b>${pd.BILLFEE }</b></font>&nbsp;元</td>
									</tr>
								</table>
							</div> --%>
<!-- 							<div class="row">
								<div class="col-xs-12">
									<h3 class="header smaller lighter grey">
										<i><img src="static/login/logoIndex.png" style="width:170px" /></i>
										
										
									</h3>
								</div>
							</div> -->
<%-- 							<div  class="col-xs-12" >
								<div class="infobox infobox-purple infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-bell"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">客户数量</div>
										<div class="infobox-content">${pd.NUMBER }&nbsp;份</div>
									</div>
								</div>
								<div class="infobox infobox-pink infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-pencil-square-o"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">订单数量</div>
										<div class="infobox-content">${pd.MONEY }&nbsp;元</div>
									</div>
								</div>
								<div class="infobox infobox-blue infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-book"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">维修员</div>
										<div class="infobox-content">${pd.INCOME }&nbsp;元</div>
									</div>
								</div>
								<div class="infobox infobox-red infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-gavel"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">配件销售</div>
										<div class="infobox-content">${pd.OUTMONEY}&nbsp;元</div>
									</div>
								</div>
								<div class="infobox infobox-grey infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-download"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">电脑维修</div>
										<div class="infobox-content">${pd.QQ }&nbsp;元</div>
									</div>
								</div>
								<div class="infobox infobox-green infobox-small infobox-dark">
									<div class="infobox-icon">
										<i class="ace-icon fa fa-bar-chart-o"></i>
									</div>
				
									<div class="infobox-data">
										<div class="infobox-content">打印机维修</div>
										<div class="infobox-content">${pd.BILLFEE }&nbsp;元</div>
									</div>
								</div>
							</div>	 --%>	
							
							

							<img src="static/login/images/6767.gif" style="width:98%;height:80%;" />
							<!-- <div id="main1" style="width: 1000px;height:400px;top:60px;margin-left:50px"></div> -->
							

				
							
						
						</div>
						<!-- /.col -->
						
<%-- 						<div class="span6" style="padding-top: 13px;">
									<div class="tabbable">
								            <ul class="nav nav-tabs" id="myTab">
								              <li class="active"><a data-toggle="tab" href="#home"><i class="green icon-home bigger-110"></i>折线图</a></li>
								              <li><a data-toggle="tab" href="#profile"><i class="green icon-cog bigger-110"></i>柱状图</a></li>
								            </ul>
								            <div class="tab-content">
											  <div id="home" class="tab-pane in active">
												<table style="width:865px;" border="0px;">
													<tr>
														<td>
				
														</td>
													</tr>
												</table>
											  </div>
											  <div id="profile" class="tab-pane">
												 <table style="width:865px;" border="0px;">
													<tr>
														<td>

														</td>
													</tr>
												</table>
											  </div>
								            </div>
									</div>
								 </div><!--/span-->		 --%>
						
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
	</script>
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
</body>
</html>