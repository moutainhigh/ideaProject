 <!-- Copyright:   互融云 -->
 <!-- AppUserInfoModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-07-04 15:33:43      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">个人信息</h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${info.id}"/>
 
 
 <div class="row">
 	<div class="col-md-8 column">
 
		 <div class="row">
			<div class="col-md-6 column">
				<div class="form-group">
					 <label for="userid">姓名</label>
					 <input type="text" class="form-control" name="truename" id="truename" value="${info.truename}"/>
				</div>
				
				<div class="form-group">
					 <label for="sex">性别</label>
					 <@HrySelect key="sex"  name="sex"  id="sex"  value="${info.sex}"  > </@HrySelect>
				</div>
				<div class="form-group">
					 <label for="age">年龄</label>
					 <input type="text" class="form-control" name="age" id="age" value="${info.age}"/>
				</div>
				<div class="form-group">
					 <label for="picturePath">qq号</label>
					 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${info.qqNumber}"/>
				</div>
			
			</div>
			<div class="col-md-6 column">
				<div class="form-group">
					 <label for="email">手机号</label>
					 <input type="text" class="form-control" name="user_mobile" id="user_mobile" value="${user.mobile}" />
				</div>
				<div class="form-group">
					 <label for="email">邮箱</label>
					 <input type="text" class="form-control" name="user_email" id="user_email" value="${user.email}"/>
				</div>
				<div class="form-group">
					 <label for="birthday">生日</label>
					 <input type="text" class="form-control" name="birthday" id="birthday" readonly   data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">
				</div>
				<div class="form-group">
					 <label for="picturePath">微信号</label>
					 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${info.wxnumber}"/>
				</div>
			</div>
		
		 </div>
		 
		 <div class="row">
			<div class="col-md-12 column">
				<div class="form-group">
					 <label for="picturePath">个性签名</label>
					 <textarea rows="6" class="form-control" />
				</div>
			</div>
		</div>
		 	
		 
 	</div>
 	
 	<!--<div class="col-md-4 column">
 			<div class="row">
 				形像展示</br>
				<img class="img-thumbnail" alt="logo" src="http://101.200.86.70/static/static/images/logo_person.png">
			</div>
	</div>-->
 	
</div>
 


 
 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >保存</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use(["js/oauth/user/AppUserInfo","js/base/HryDate"],function(o,HryDate){
 	HryDate.init();
 	o.info();
 });
 </script>






