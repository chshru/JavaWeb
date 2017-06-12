(function(){
	function $(id){
		return document.getElementById(id);
	}

	function addEvent(){
		var ico=$("ico");
		ico.onclick=function(){
			var inputValue=$("input").value;
			window.location.href="./search.html?name="+inputValue;
		}
	}
	addEvent()
})()