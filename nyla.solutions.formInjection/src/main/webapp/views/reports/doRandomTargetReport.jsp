<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<script src="<c:url value="/views/common/js/form.js"/>"></script>


<html:form action="/RandomReportAction?method=doRandomReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity">
<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>
<table width="100%" cellpadding="0" cellspacing="0" border="0" style="text-align:left;vertical-align:top">

<tr>
	<td class="reportsLink">
 	<bean:define id="reportTitle" name="reportForm" property="reportTitle"/>
	<h3 class="reportsHeader"><%=reportTitle%></h3>
	</td>
</tr>
	


<tr style="padding-left: 15px">
   <td align="left" valign="top">
    <bean:define id="reportName" name="reportForm" property="reportName"/>
   <input class="reportsLink" type="hidden" id="reportName" name="reportName" value="<%=reportName%>"/>
   <table border="0" cellpadding="0" cellspacing="0">
   <tbody>
   <tr>
      <td>
         <table border="0" cellpadding="1" cellspacing="1">
            <tbody>
              <tr>
                 <td class="reportsLink">Protocol</td>
                 <td class="reportsLink">
                     <bean:define id="protocolDropDown" name="reportForm" property="protocolDropDown"/>
                     <html:select property="protocol">
                     	<html:option value="All">All</html:option>
                        <html:options collection="protocolDropDown" property="label" labelProperty="label"/>
                     </html:select>
                 </td>
              </tr>

              <tr>
                 <td class="reportsLink">Personnel</td>
                 <td class="reportsLink">
                     <bean:define id="userDropDown" name="reportForm" property="userDropDown"/>
                     <html:select property="monitor" style="width:220px">
                       <html:options collection="userDropDown" property="value" labelProperty="label"/>
                     </html:select>
                 </td>
              </tr>


              <tr>
                 <td class="reportsLink">Date Range &nbsp;&nbsp; Begin</td>
                 <td class="reportsLink">
                 	<html:text name="reportForm" property="startDt" readonly="true"/>
                 	<img id="img_startDt" src="/gcsm/skins/default/images/button_calendar.gif" style="cursor:hand" onclick="showCalendar(this)">
                    &nbsp;&nbsp; End
                    <html:text name="reportForm" property="endDt" readonly="true"/>
                    <img id="img_endDt" src="/gcsm/skins/default/images/button_calendar.gif" style="cursor:hand" onclick="showCalendar(this)">
                 </td>
              </tr>
            </tbody>
         </table>
      </td>
   </tr>
   <tr>
      <td class="reportsLink">
         <table border="0" cellpadding="1" cellspacing="1">
            <tbody>
            <tr>
	            <td>
	               <input class="reportsLink"  id="btnCreate" name="btnCreate" type="submit" value="Go" onclick=""/>
	            </td>
            </tr>
            </tbody>
         </table>
      </td>
   </tr>
   </tbody>
 </table>
 </tr>
 </td>
 </table>
 <bean:define id="reportForm" name="reportForm" property="html"/>
            <table id="report_tbl">
               <tr><td>
               <span id="body">
                  <%=reportForm%>
               </span>
               </td></tr>
            </table>

</html:form>



