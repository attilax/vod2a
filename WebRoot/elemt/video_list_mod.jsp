<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*"   %>
<ul id="listdivP1"  >
     <li ng-repeat="itemO7 in listO7">
       <table width="100%" border="0" cellspacing="0">
         <tr>
             <td align="center"><img src="img/thumb.png" width="161" height="122" />
             <p><a href="javascript:void(0)"  style="color:#333; font-weight:bold" title="{{itemO7.filePath}}" onclick="playx(this.title); ">{{itemO7.materialDescription}}</a>&nbsp;&nbsp;&nbsp;</p>
             </td>
           
           </tr>
       </table>
  
     </li>
     </ul>