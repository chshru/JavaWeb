(function(){
	function $(id){
		return document.getElementById(id);
	}
	function ajax(){
		var xml=false;
		try{
			xml=new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				xml=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(E){
				xml=false;
			}
		}
		if(!xml&&typeof XMLHttpRequest!='undefined'){
			xml=new XMLHttpRequest();
		}
		return xml;
	}
	function request(url,obj){
		var xml=ajax();
		xml.open("GET",url);
		xml.onreadystatechange=function(){
			if(xml.readyState==4&&xml.status==200){
				obj.innerHTML=xml.responseText;
			}
		}
		xml.send(null);
	}

	function addEvent(){
		var ico=$("ico");
		var main_content=$("main_content");
		ico.onclick=function(){
			var inputValue=$("input").value;
			request("./js/search.jsp?name="+inputValue,main_content);
				
		}
	}
	window.onload=function(){
		var main_content1=$("main_content");
		var url=window.location.href;
		url=url.replace("html","jsp");
		url=url.replace("javaweb", "javaweb/js")
		request(url,main_content1);
	}
	addEvent()
})()
