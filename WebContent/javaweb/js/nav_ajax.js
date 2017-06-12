url="js/zuixin.jsp?name=2";
var tot = 2;
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
	var top_nav=$("top_nav"),
		a=top_nav.getElementsByTagName("a"),
		main_content=$("main_content");
	function $(id){
		return document.getElementById(id);
	}
	function addEvent(){
		for(var i=0,lenght=a.length;i<lenght;i++){
			a[i].onclick=(function(i){
				return function(e){
					stopDefault(e);
					tot=2;
					url=a[i].getAttribute("href")+"?name=1";
					for(var j=0,length=a.length;j<length;j++){
						if(i!=j){
							a[j].style.backgroundColor="#F9F9F9";
						}
						a[i].style.backgroundColor="#1ec7e6";
					}
					nav_request(a[i].getAttribute("href")+"?times=2",main_content);
				}
			}(i))
		}
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
	function stopDefault(e)
	{ 
   		if(e&&e.preventDefault) 
      		e.preventDefault(); 
     	else 
        	window.event.returnValue=false;  
	} 
	

	tot=2;
	nav_request("js/zuixin.jsp?name=2",main_content);
	addEvent()


})()