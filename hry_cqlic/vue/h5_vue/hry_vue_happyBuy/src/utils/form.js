var form={
	formVerification:function(formName,_this){
		let _form=document.getElementsByClassName(formName)
		let a=_form[0];
		for(var i=0;i<a.length;i++){
			if(!a[i].value&&a[i].alt!='false'&&a[i].localName=='input'&&a[i].placeholder!="推荐码非必填"){
				_this.$message({
					type: 'warning',
					duration:5000,
					message: a[i].title+'不能为空！'
				});
				return false;
			}
		}
		return true;
	},
  messages:function(formName,_this){
    let _form=document.getElementsByClassName(formName)
    let a=_form[0];
    for(var i=0;i<a.length;i++){
      if(!a[i].value&&a[i].alt!='false'&&a[i].localName=='input'&&a[i].placeholder!="推荐码非必填"){
        _this.$message({
					type: 'warning',
					duration:5000,
          message: '请填写完整信息！'
        });
        return false;
      }
    }
    return true;
  },
	getForm:function(formName,_this){
		let _form=document.getElementsByClassName(formName)
		let a=_form[0];
		let maps={};
		for(var i=0;i<a.length;i++){
			if(a[i].localName=='input'&&a[i].type=='text'){
				maps[a[i].name]=a[i].value;
			}
		}
		return maps;
	},
};
export default form
