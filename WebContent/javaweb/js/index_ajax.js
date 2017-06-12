(function(){
	function ajax(){
		var xml=false;
		try{
			xml=new ActiveXOject("Msxml2.XMLHTTP")
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
	document.body.onmousewheel=function(e){
			var dis=document.body.scrollHeight-document.body.scrollTop-document.body.clientHeight;
			var e=window.event||e;
			if(e.wheelDelta<0){
		if(dis<50){
			var xml=ajax(),
				obj=document.getElementById("main_content");
				tot++;
			xml.open("GET",url+'&'+"times="+tot);
			xml.onreadystatechange=function(){
				if(xml.readyState==4&&xml.status==200){
					var div=document.createElement("div");
					obj.appendChild(div);
					div.innerHTML=xml.responseText;
				}
			}
			xml.send(null);
			dis=50;
		}
	}
	}
})()