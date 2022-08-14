var cookie={
    setCookie: function(c_name,value,expiremMinutes){
        var exdate = new Date();  
        exdate.setTime(exdate.getTime() + expiremMinutes * 60 * 1000);  
		document.cookie= c_name + "=" + value +((expiremMinutes==null) ? "" : ";expires="+exdate.toGMTString());    
    },
      
    getCookie: function(c_name){  
        if (document.cookie.length>0)  
        {  
            var c_start=document.cookie.indexOf(c_name + "=");  
            if (c_start!=-1)  
            {   
            c_start=c_start + c_name.length+1;  
            var c_end=document.cookie.indexOf(";",c_start);  
            if (c_end==-1)   
                c_end = document.cookie.length  
                return document.cookie.substring(c_start, c_end) 
            }  
        }  
        return ""     
    },  
      
    delCookie: function(c_name){  
        this.setCookie(c_name,"",0.1)
    }
};
export default cookie
