 <!-- Copyright:    -->
 <!-- YwkCustomerMinerSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-16 16:44:13      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
  <div class="row">
  	<div class="col-md-12 column">
	    <table class="table table-striped table-bordered table-hover" id="configTable">
	        <thead>
	        <tr>
	            <th class="text-center" style="width: 200px;">星期</th>
	            <th class="text-center" style="width: 200px;">下单手机号</th>
	            <th class="text-center" style="width: 200px;">下单时间</th>
	        </tr>
	        </thead>
	        <tbody>
			    <tr>
			    	<td class="text-center">星期一</td>
			        <td class="text-center">${model.mondayNumber}</td>
			        <td class="text-center">${(model.mondayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期二</td>
			        <td class="text-center">${model.tuesdayNumber}</td>
			        <td class="text-center">${(model.tuesdayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期三</td>
			        <td class="text-center">${model.wednesdayNumber}</td>
			        <td class="text-center">${(model.wednesdayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期四</td>
			        <td class="text-center">${model.thursdayNumber}</td>
			        <td class="text-center">${(model.thursdayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期五</td>
			        <td class="text-center">${model.fridayNumber}</td>
			        <td class="text-center">${(model.fridayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期六</td>
			        <td class="text-center">${model.saturdayNumber}</td>
			        <td class="text-center">${(model.saturdayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
			    <tr>
			    	<td class="text-center">星期日</td>
			        <td class="text-center">${model.sundayNumber}</td>
			        <td class="text-center">${(model.sundayDate?string("yyyy-MM-dd HH:mm:ss"))!} </td>
			    </tr>
	        </tbody>
	    </table>
	 </div>
 </div>
 </div>

 <script type="text/javascript">
 
 </script>




