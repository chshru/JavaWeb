(function(){
	var url1=window.location.href.split("/"),
		url=url1[url1.length-1];
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
				var div=document.createElement("div");
					obj.appendChild(div);
					div.innerHTML=xml.responseText;
			}
		}
		xml.send(null);
	}
	var main_content=document.getElementById("main_content");
	function addEvent(){
		document.body.onmousewheel=function(e){
			var dis=document.body.scrollHeight-document.body.scrollTop-document.body.clientHeight;
			var e=e||window.event;
			if(e.wheelDelta<0){
				if(dis<50){
					request("3.html",main_content);
				}
			}
		} 
	}
	addEvent()
})()