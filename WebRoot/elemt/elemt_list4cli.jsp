﻿﻿<%@ page language="java" import="java.util.*,com.focusx.util.*,com.focusx.entity.*,org.apache.struts2.ServletActionContext" pageEncoding="UTF-8" %>
<%@page import="com.focusx.auth.authx"%>
<%@page import="com.focusx.elmt.GvMaterialSvs"%>
<%@page import="net.sf.json.JSONArray"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="atiMod">
 
<head>
<title>素材管理</title>
<!--o88.-->
 <script src="../com.attilax/util/datadic_js.jsp"></script>
 <!--///-->
<!--set ie8设定要用IE8标准模式渲染网页，而不会使用兼容的模式。-->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../resources/css/style.css" type="text/css"></link>
   <%@include file="../common/global_v.jsp" %>
<script type="text/javascript" src="../com.attilax/time/time.js"></script>
<script src="date.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>



 <link rel="stylesheet" type="text/css" href="../js/themes/default/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="../js/themes/icon.css"/>  
	    <link rel="stylesheet" type="text/css" href="../js/demo/demo.css">


 
<script language="JavaScript" type="text/javascript" src="../js/jquery.easyui.min.js">
</script>
<script language="JavaScript" type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
 
<script src="js/angular.min.js"></script>

 
 
  <script type='text/javascript' src='../dwr/engine.js'></script>
  <script type='text/javascript' src='../dwr/interface/elmtC.js'></script>

 
  <script type='text/javascript' src='../dwr/interface/dwrC.js'></script>
  <script type='text/javascript' src='../dwr/util.js'></script>
  <script src="elemt_list_head.js" type="text/javascript"></script>
  
     <%@include file="../com.attilax/web/dwrExCptr.html" %>
     <script type="text/javascript" src="../com.attilax/core/core.js"></script>
     <script type="text/javascript" src="../com.attilax/ui/checkbox.js"></script>
     
     <script type="text/javascript" src="../com.attilax/parte/parte.js"></script>
     
     <script type="text/javascript" src="../com.attilax/exception/ex.js"></script>
     <link rel="stylesheet" href="elemt_list4cli.css" type="text/css"></link>
</head>

<body leftmargin="0" topmargin="0" scrolling="no" style="padding: 0px;">
	<table width="100%" height="100%" id="center-table">
		<tr>
			<td colspan="3" valign="top" class="f2"><form id="formx" name="formx" action="" method="post">

			  <!--   set navi kmpnt start -->                  
<%
GvMaterialSvs mtrlC=new GvMaterialSvs();
//JSONArray naviList=mtrlC.getCate();
//new nave().setdatasource（naviList）； 
%>
 
           
           
 <p>
   
   <!-- // navi end-->		 
   <script >
function playx(video)
{
//	alert(video);
	try{
 console.info(video);
	}catch(e){}
	try{
		
 sendNSCommand('play',video);
	}catch(e){alert(e);}
}
   </script>
 <table width="100%" border="0">
   <tr>
     <td width="80%" valign="top"  id="tabid1"  ng-controller="listCtrl">
     <table width="100%" border="0"   class="tab0100" id="demo1_table2">
       <tr>
         <td width="200">&nbsp;&nbsp;&nbsp;logo&nbsp;&nbsp;&nbsp;</td>
         <td width="50">名称：</td>
         <td style="text-align:left;"><input name="materialDescription"  style="width:100%" type="text" class="input-text" id="materialDescription" value="" /></td>
         <td style="text-align:left;">&nbsp;</td>
         <td style="text-align:left;"><input name="logicDel" type="hidden" id="logicDel" value="8" />
           <input name="applicationType" type="hidden" id="applicationType" value="" />
           <a href="javascript:void(0)" onclick="search_click()"><img src="search.jpg" width="58" height="57" />查询</a>
           <input  type='button' onclick="query(1)" id='id_ev_query' name='ev_query' class='bt00' value='查询' style="display:none"/></td>
       </tr>
     </table>
     <%@include file="video_list_mod.jsp" %>

       <div class="splitPage33">
         <div align="right"  style="display:none" >
           <script src="pagging.js"></script>
           共有<span id="totalRows2">0</span>条&nbsp;&nbsp;&nbsp;&nbsp; <span id="firstPrePageBtnArea2"> <a href="javascript:firstPage()" id="firstPageBtn2">首页</a> &nbsp;<a href="javascript:prePage()" id="prePageBtn2">上页</a></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="nextLastPageBtnArea2"> <a href="javascript:nextPage()" id="nextPageBtn2">下页</a> &nbsp;<a href="javascript:lastPage()" id="lastPageBtn2">尾页</a></span>&nbsp;&nbsp;&nbsp;输入页码：
           <input type="text" id="pageItem2" size="4" maxlength="5" class="TextStyle"onkeydown="if(event.keyCode==0xD) page_go()" />
           &nbsp;&nbsp;&nbsp;<a href="javascript:page_go()">跳转</a>&nbsp;&nbsp;&nbsp;&nbsp;第<span id="page_page_lab2">1</span>页/共<span id="totalPages2">10</span>页 <span style="display:none">
             <input name="pagesize2" type="hidden" id="pagesize2" value="10" />
             <input name="page_page2" type="text" id="page_page2" value="1" />
             </span>
           <p/>&nbsp;</p>
           &nbsp;
           </p>
         </div>
         <!-- <div align="right" class='FormFont'>共有3条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第1页/共1页<p/> -->
       </div>
       <table width="100%" border="0" cellspacing="0" class="cateP1">
         <tr>
           <td><h1><strong>dap</strong>ye</h1></td>
           <td><h1><strong>neidi</strong></h1></td>
           </tr>
       </table>
       <p>&nbsp;</p></td>
     <td width="20%" valign="top"><table width="95%" border="0" align="center" cellpadding="7" cellspacing="8" class="cateP1" style="border-collapse:separate; border-spacing:10px">
       <tr class="">
         <td><p>&nbsp;</p>
           <p>&nbsp;</p>
           <p>&nbsp;</p></td>
       </tr>
       <tr class="">
         <td>热播</td>
       </tr>
       <tr class="">
           <td>&nbsp;推荐</td>
         </tr>
         <tr>
           <td>影库</td>
         </tr>
         <tr>
           <td>预告</td>
         </tr>
         <tr>
           <td>预告</td>
         </tr>
         <tr>
           <td>退出</td>
         </tr>
     </table></td>
   </tr>
 </table>
 <p>&nbsp; </p>
 
				</form>
			</td>
		</tr>
	</table>
     <div id="loading"></div> 
   <script>
   function listCtrl($scope) { 

    $scope.listO7 =[ ] ; 

} 

   </script>
      <script type='text/javascript' src='elemt_list.js'></script>
    <script type='text/javascript' src='elemt_list_man.js'></script>
   <script type="text/javascript" defer="defer" src="aaa../resources/js/publicTbody2.js"></script>
</body>
</html>
