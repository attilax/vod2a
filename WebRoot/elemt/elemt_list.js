function navi_click(cateid)
{
	try{
		$("#applicationType").val(cateid);
		query(1);
	}catch(e)
	{
		alert(e);
	}
}

function search_click()
{
	try{
	$("#applicationType").val("");
	query(1);
	}catch(e)
	{
		alert(e);
	}
}

function delx(id,obj)
{
	//alert();
	  if (confirm("确认要删除？"))
	  {
	var mp=	elmtC.delete_ByID(id,function(){
		//	alert("删除成功");
			 query();
			});
			
	  }
		 
		
	
}

function delxSlktRows()
{
	 //todox get checkboxs vals  o7f
	var str="";     
    $("[name='idsCheck']").each(function(){ 
	// $("[name='idsCheck'][checked]")  cant work.
	  if($(this).attr("checked"))  
	  {
		    str+=$(this).val()+",";   
	  }
	//alert(   $(this).attr("name")) ;     
	//alert ($(this).val());
	 
     
   //alert($(this).val());     
    });
//	alert(str);
	$("#idsCheckVals").val(str);
	  if (confirm("确认要删除所选？"))
	  {
		  var mp=	dwr.util.getFormValues("formx");
		 // alert(mp);
	var mp2=elmtC.delete_ByIDss(mp,function(data){
					delxSlktRowsRetEvt(data);
			});
			
	  }
		 
		
	
}

function delxSlktRowsRetEvt(data)
{
	
	
	//	alert("删除成功");
		try{
			processJavaEXob7(data);
		}catch(e)
		{
 
//catch check ex
			logx(e);
			if(e instanceof JavaEx && e.typex=="PartProcessErrEx")
			
			{
			
				//alert(e.message);
				
				alert(formatPartE(e.message,"未能全部完成处理："));
				
				throw "stop..";
			
			}
		}
			loadIniData();	
	
}

function editSlktRow()
{
	
	var str="";     
    $("[name='idsCheck']").each(function(){ 
	// $("[name='idsCheck'][checked]")  cant work.
	  if($(this).attr("checked"))  
	  {
		  if(str=="")
		    str=$(this).val();
	  }
    });
	var mp={};
	mp.id=str;
	mp.meth="queryMtrlReviewStat";
	dwrC.execOb(mp,function(data){
		//if ex then to ex process hadler
		if(data)		
			alert("不能编辑,已经审核通过");
		else
			window.location.href="elemt_edit.jsp?id="+str;
		 	
		});

	
}

function loadIniData()
{
		//elmtC.findAllWzPage(   function(data)
//	
//			 {
//	$('#formx').scope().total =data.total;
//			 bindTableData(data.rows);
//			 setPageInfo({"total":data.total,"pageSize":10});
//			 }
//	
//			);
			//$scope.hello = "Hello, World!"
			try{
		query();	
			}catch(e){logx(e);}

}

function bindTableData(data)
{
//	alert( $('#tabid1'));
	
 			 $('#tabid1').scope().listO7 = data;
		//	 alert($('#formx'));
			   $('#formx').scope().$digest();	
	
}
(function (bodyStyle) {


	loadIniData();
	
	dwr.engine.setAsync(false);	

})(document.body.style);

//queryBtn_click
function query(isResetPage1)
		{
			if(isResetPage1==1)
			 resetPageTo1();
		var mp=	dwr.util.getFormValues("formx");
		elmtC.findByPropertyss_page(mp ,function(data){
			//alert("保存成功");
			 bindTableData(data.rows);
			  setPageInfo({"total":data.total,"pageSize":10});
			});	
		}


//
//$(function(){
//    $("#loading").ajaxStart(function(){
//        $(this).html("<img src='/jqueryStu/images/loading.gif' />");
//      });
//      $("#loading").ajaxSuccess(function(){
//        $(this).html("");
//        // $(this).empty(); // 或者直接清除
//      });
//
//});

