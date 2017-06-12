var tot=2;
var ul=document.getElementById("small_nav");
var li=ul.getElementsByTagName("li");
	for(var i=0,length=li.length;i<length;i++){
		if(li[i].getAttribute("id")){
			var id=li[i].getAttribute("id");
		}
	}
var url='./js/movie.jsp?name='+id;
(function(){
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
	function nav_request(url,obj){
		var xml=ajax();
		xml.open("GET",url);
		xml.onreadystatechange=function(){
			if(xml.readyState==4&&xml.status==200){
				obj.innerHTML=xml.responseText;
			}
		}
		xml.send(null);
	}
	var mian_content=document.getElementById("main_content");
	nav_request(url+'&times=2',mian_content);
})()