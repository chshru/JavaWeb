(function(){
	function ajax(){
		var xml=false;
		try{
			xml=new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				xml=new ActiveXObject("Microsoft.XMLHTTP")
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
	var node=document.getElementById("sub_list");
	request("./js/rank.jsp",node);
})()