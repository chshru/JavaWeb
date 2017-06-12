(function(){
var	canvas=document.getElementById("canvas"),
		context=canvas.getContext("2d"),
		txtcanvas=document.createElement("canvas"),
		txtcontext=txtcanvas.getContext("2d");
		txtcanvas.height=canvas.width;
		txtcanvas.width=canvas.height;
		txtcontext.font="normal 20px Sans-serif";
		txtcontext.textAlign="center";
	 	txtcontext.baseline="middle";
		txtcontext.fillText("M",canvas.width/2,canvas.height/2),
		circle_array=[];
	var	imgdata=txtcontext.getImageData(0,0,txtcanvas.width,txtcanvas.height).data;
	function Circle(x,y,tx,ty,r){
		this.x=x;
		this.y=y;
		this.r=r;
		this.br=r;
		this.tx=tx;
		this.ty=ty;
		this.sx = Math.random() * 0.5;
        this.sy = Math.random() * 0.5;
		this.hue=Math.random()*360;
		this.a = Math.random() * Math.PI * 2;
		this.delay = Math.random() * 200;
        this.delayCtr = 0;
        this.o=Math.random()*1;
		}
	Circle.prototype={
		constructor:Circle,
		update:function(){
			if (this.delayCtr < this.delay) {
                this.delayCtr++;
                return;
            }
			this.hue+=1;
			this.a+=0.1;
			this.r = this.br + Math.cos(this.a)*(this.br * 0.5);
			this.x += (this.tx - this.x) * this.sx;
            this.y += (this.ty - this.y) * this.sy;	
		},
		draw:function(context){
			context.save();
			context.beginPath();
			context.fillStyle="hsla("+this.hue+",100%,60%,1)";
			context.globalAlpha=this.o;
			context.translate(this.x,this.y);
			context.beginPath()
			context.arc(0,0,this.r,0,Math.PI*2,false);
			context.fill();
			context.restore();
		}
	}
	function draw(){
		for(var y=0;y<canvas.height;y+=4){
			for(var x=0;x<canvas.width;x+=4){
				var id=(canvas.width*y+x)*4-1;
					if(imgdata[id]>0){
						var a=Math.PI*2*Math.random();
						 circle = new Circle(canvas.width+canvas.width/2,canvas.height+canvas.height/2,x,y,Math.random()*4);
						circle_array.push(circle);
					}
				}
			}
		}
	draw()
	function run(){
		context.clearRect(0,0,canvas.width,canvas.height)
		for(var i=0;i<circle_array.length;i++){
			c=circle_array[i].update();
			circle_array[i].draw(context);
		}
		requestAnimationFrame(run);
	}
	requestAnimationFrame(run);
})();