<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%@page import="java.io.File"%>  
<html >
  <head>
  <script>var localflag=false;
 var   ReverseAjaxOoo=<%=!new File("C:\\NotReverseAjax").exists()%>;
  </script>
   <%@include file="../com.attilax/util/localflag.jsp" %>
   
    <title>素材编辑</title>
    <!--set ie8设定要用IE8标准模式渲染网页，而不会使用兼容的模式。-->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="../resources/css/style.css" type="text/css"></link>
      <%@include file="../common/global_v.jsp" %>

   <script type="text/javascript" src="../com.attilax/time/time.js"></script>
   <script src="date.js"></script>
    <script type="text/javascript">
			function link_back(){
						location.href = "elemt_list.jsp";
					}
			function MM_openBrWindow(theURL,winName,features) { //v2.0
			  window.open(theURL,winName,features);
			}
    </script>
    
    
   <script language="JavaScript" type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>



 <link rel="stylesheet" type="text/css" href="../js/themes/default/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="../js/themes/icon.css"/>  
	    <link rel="stylesheet" type="text/css" href="../js/demo/demo.css" />


 
<script language="JavaScript" type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
 <!--dwr start -->

  <script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
  <script type='text/javascript' src='<%=path%>/dwr/interface/elmtC.js'></script>
    <script type='text/javascript' src="<%=path%>/dwr/util.js"></script>
 <!-- /// dwr --> 
  
  <!--o7f-->
   <script type='text/javascript' src='js/req.js'></script>
   
   <!--o81  add tips-->
      <script type="text/javascript" src="<%=path%>/comm/Jquery notice JBox_files/jBox.js"></script>
         <link type="text/css" rel="stylesheet" href="<%=path%>/comm/Jquery notice JBox_files/c74f71-921494.css"/>
  
  <!--
      
  <script type="text/javascript" src="<%=path%>/comm/jBox/jquery.jBox-2.3.min.jsxkkkkk00000"></script>
 
  <script type="text/javascript" src="<%=path%>/comm/jBox/i18n/jquery.jBox-zh-CN.js"></script>
 
  <link type="text/css" rel="stylesheet" href="<%=path%>/comm/jBox/Skins/Green/jbox.css000"/>

  <link type="text/css" rel="stylesheet" href="<%=path%>/comm/jBox/Skins2/Blue/jbox.css——-"/>
  
  -->
  
  <!---->
     <%@include file="../com.attilax/web/dwrExCptr.html" %>
     
</head>
  
  <body leftmargin="0" topmargin="0" scrolling="no" style="padding:0px; margin:0px">
		<table width="100%" height="100%" id="center-table">
        <tr>
          <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="0%" align="left" class="f1"><img src="../resources/images/center-lt.gif" /></td>
              <td width="0%" align="right" class="f1"><img src="../resources/images/center-rt.gif" /></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td colspan="3" valign="top" class="f2"><div class="current">
            <div class="l">
              <div class="r">
                <p>&nbsp;</p>
              </div>
            </div>
          </div>
            <div class="form">
              <div class="l">
                <div class="r"><span>文件信息编辑</span> 
                  <script type="text/javascript" src="../com.attilax/core/core.js"></script>
                </div>
              </div>
            </div>
            <form action="" method="post" enctype="multipart/form-data" name="formx" id="formx">
            <table width="100%" class="tab01" id="demo1_table">
            <tbody>
              <tr id="upTr" style="display:nonex">
                <td width="130"><span class="required">*</span>图片上传：</td>
                <td class="tdr">
                <iframe id="upIfrm" src="up.jsp?callx=callO7" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" style="width:100%; height:60px"></iframe>
                      
                </td>
              </tr>
              <tr style="">
                <td><span class="tdr"><span class="required">*</span>文件路径：</span></td>
                <td class="tdr">   
                <!-- server up path-->
                <input name="filePath" type="text"   id="filePath" class="ati-validatebox input-text" data-options="required:true,novalidate:true,missingMessage:'没有选择文件',deltaX:50,delay:20000"  onchange="fileChangeEvent()"/>
                <input name="button" type="button" class="bt00" id="button" onclick="MM_openBrWindow('http://<%=hostOaa%>:8892/elfinder-2.0-rc1/elfinder.php?jsopUrl=http://<%=hostOaa%>/vod/elemt/callback.jsp&callback=callEvent&scriptSessionPage=<%=new com.attilax.util.PropXwebrootBase().getConfig_o99("cfg.txt", "scriptSessionPage")%>','','toolbar=yes,location=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes')" value="服务端浏览" />
                <%//include file="up.jsp" %>
                <input type="hidden" name="materialId" id="materialId"  />
                <input type="hidden" class="" name="filePath" id="filePath" data-options="required:true,missingMessage:'没有选择文件',deltaX:50,delay:20000">
                <input type="hidden" class="" name="thumb" id="thumb" data-options="required:true,missingMessage:'没有选择文件',deltaX:50,delay:20000" />                <script >
				function callO7(path)
				{
					//alert(path);
					$("#thumb").val(path);
					okTips();
				}
				function okTips()
				{
					//$.jBox.tip('文件上传完毕！！');
					tipsO81();
				}
				</script>
                
                <script>
 
function hotkey(event)
{
var a=event.keyCode;
if((a==68)&&(event.ctrlKey)&& event.altKey )
{
//alert("你按了ctrl+alt+d键吧");
//okTips()
//tipsO81();
$('#dbgW').window('open');
}
}// end hotkey
document.onkeydown = hotkey; //当onkeydown 事件发生时调用hotkey函数

   var colorsN2 = ['red', 'green', 'blue', 'yellow'];
    var currentColorN2 = 0;
var  tipsO81=function() {
        new jBox('Notice', {
            animation: 'flip',
            position: {
                x: 315,
                y: 65
            },
            content: '文件上传完毕！',
            onInit: function() {
                this.options.color = colorsN2[currentColorN2];
                currentColorN2++; (currentColorN2 >= colorsN2.length) && (currentColorN2 = 0)
            },
            zIndex: 12000
        })
    }

//console.log("hotkey...");
 

 
</script>
     <div id="dbgW" class="easyui-window" title="Basic dbgW Window" data-options="iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
 <div style="margin:20px 0;">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dbgW').window('open')">Open</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dbgW').window('close')">Close</a><p>
<script>
function open_up_box()
{
	$("#upTr").show();
}
function hide_up_box()
{
	$("#upTr").hide();
}
 </script>
<input type="button" onclick="open_up_box()" value="open up box" />
<input type="button" onclick="hide_up_box()" value="hide_up_box" />
</div>
</div>  

<script >
$(function () {
	try{
$('#dbgW').window('close');
	}catch(e){}
});
</script>
  <!-- //// server up path-->  </td>
              </tr>
              <tr style="display:none">
                <td><span class="required">*</span>文件：</td>
                <td class="tdr"><label for="filePath_del">.</label>
                <input type="file" name="filePath_del" id="filePath_del" class="xxati-validatebox"  data-options="required:true,novalidate:true,missingMessage:'没有选择文件',deltaX:50,delay:20000">
                <input style="display:none" name="Action2" type="button" onClick="MM_openBrWindow('up.html','','')" value="选择文件"></td>
              </tr>
              <tr>
                <td width="130"><span class="required">*</span>描述：</td>
                <td class="tdr"><input name="materialDescription" type="text"   id="materialDescription" class="ati-validatebox input-text" data-options="required:true,novalidate:true,missingMessage:'描述不能为空的',deltaX:50,delay:20000" validType="remote['checkMtrlDescExist','materialDescription']"></td>
              </tr>
              <tr>
                <td width="130"><span class="required"></span><span class="required">*</span>文件类型：</td>
                <td class="tdr">
                <%if("1"=="2"){%>
                <select></select>
                <%}%>
                <!--o88 ati-->
                <d:select DPcode="WJLX"
												tagId="materialType" />
                                                <!-- <%//--@include file="cate.html" %>-->
               
                
                  <!-- /*<select name="materialTypeT" value="" id="materialTypeT" ng-model="orderProp">
						    		  <option value="">-请选择-</option>
						    		 
					    		    </select>*/--></td>
                                    
                                   
              </tr>
              <tr>
                <td width="130"><span class="required"></span><span class="required">*</span>应用分类：</td>
                <td class="tdr">
               
                 <%if("1"=="2"){%>
                <select></select>
                <%}%> <!--o88-->
                                                 <d:select DPcode="appcate"
												tagId="applicationType" />
				<!--<%//--@include file="appcate.html" %>-->
				
</td>
</tr>
<tr>
  <td width="130"><span class="required"></span><span class="required">*</span>播放时长：</td>
  <td class="tdr">
      <input name="playtime"
    required="required" class="easyui-timespinner " id="playtime" style="width:188px;" value="00:00:31" data-options="showSeconds:true,value:'00:00:32'">
      <input type="button" name="button2" id="button2" value="手动获取时长" />
  </td>
</tr>
<tr>
  <td width="130"><span class="required"></span><span class="required">*</span>生效时间：</td>
  <td class="tdr"><input name="effectieTime" type="text" required  class="input-text easyui-datetimebox  ati-validatebox" id="effectieTime"  data-options="required:true,formatter:formatDateTextO7,missingMessage:'生效时间不能为空的'"  formatter="formatDateTextO7" value="2014-10-27 07:44:50"></td>
</tr>
<tr>
  <td width="130"><span class="required"></span><span class="required">*</span>失效时间：</td>
  <td class="tdr"><input name="failureTime" type="text" class="input-text  easyui-datetimebox  ati-validatebox" id="failureTime" required  data-options="required:true,formatter:formatDateTextO7,missingMessage:'失效时间不能为空的'" value="2015-08-27 07:44:50"></td>
</tr>
<tr>
  <td colspan="2" class="tdb tdr"><table cellpadding=0 cellspacing=0 width="100%"  border="0"  class="toolBar">
    <tr>
      <td><table id="toolBar" border="0">
        <tr>
          <td class='coolButton' align='left' width='0'></td>
          <td class="coolButton" ><div style=''>
            <input  type='button' id='id_ev_add' name='ev_add' class='bt00' value='保存' onClick=" save()" />
            </div></td>
          <td class="coolButton" ><div style=''>
            <input  type='button' id='id_ev_edit' name='ev_edit' class='bt00' value='取消' onclick="link_back()"  />
            </div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
</tr>
</tbody>
</table>
</form>
</td>
</tr>
<tr>
  <td><table width="100%">
    <tr>
      <td align="left" class="f3"><img src="../resources/images/center-lb.gif" /></td>
      <td align="right" class="f3"><img src="../resources/images/center-rb.gif" /></td>
    </tr>
  </table></td>
</tr>
</table>
  <script src="elemt_edit.js" type="text/javascript"></script>
  <script >
$(function () {
	try{
$('#dbgW').window('close');
	}catch(e){}
});
</script>
</body>
</html>
