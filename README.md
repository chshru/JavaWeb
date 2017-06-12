# 陈善如

## 2017/5/24

##### 确定项目主题
* 一个类似于80s，电影天堂等的电影网站。提供电影的检索、推荐及下载等服务。


##### 寻找资源
* 利用Java网络爬虫，从各大电影网站获取资源。资源放出的同时保留来源及版权信息。


##### 建立相关数据表
* movie表：用于存储资源的名称、略缩图、简述、类型、上映日期等等。
* link表：用于存储资源的链接(一个资源可能同时拥有多个下载链接)。
* image表：用于存储资源的截图(很多网站在电影的祥细页会放出一些截图用于用户预览资源的大致信息)。
* 为了方便操作资源和消除框架数据冗余(调查发现绝大多数资源只有一个link和一个image)，决定把三表合一，后期如果需要其他表会再做调整(2017/5/26)。


##### 确定Java爬虫结构
* DocBulider类，传入固定格式的网络地址。通过网络返回Doc文档。
* SpliderMain类，获取到Doc文档并解析出所需资源。
* ThreadCtrl类，开启多线程同时去抓取不同页面的资源。
* ClientTest类，程序入口。

## 2017/5/25

##### 编写Java爬虫
* 编写ThreadCtrl类，根据客户端输入的线程数和资源数创建线程，为每个线程分配固定的资源，启动线程。

* 编写DocBulider类，根据传入的网络地址和页数，利用网络请求返回下载的Doc文档。

* 编写SpliderMain类，解析构建文档类返回的文档。抓取有价值的标签、网址、图片以及文字资源。把抓取到的资源按照一定格式存储到Mysql数据库。部分代码如下：

  ```java
  ······
  String name = ename.get(0).text();
  String pic = epic.get(0).attr("src");
  String type,date,text,link,image; 
  link=detail.select("span.dlbutton1").select("a").attr("href");
  for(Element e:etype.get(0).select("a")){
  	type += e.text()+" ";
  }
  if(etype.size()<9)
  	return false;
  date = etype.get(8).text().replace("上映日期：", "");
  if(date.contains("片长")) return false;
  text = detail.select("div.clearfix").get(7).text().replace("剧情介绍： ", "");
  if(text.contains("80s")){
  	text = text.substring(0, text.indexOf("80s"));
  }
  ······
  ```
* 编写ClientTest类，输入线程数和资源数，调用ThreadCtrl即可。

##### 抓取80s网站资源

* 共有384页资源，每页25个。开启10个线程进行抓取，线程平均耗时92352ms。舍去网页超时、抓取错误、抓取失败的资源数。总计资源9454条。每条资源包含：名称、下载地址、视频截图、封面、简介、类型、日期。客户端部分代码如下：

  ```java
  boolean thread_flag = false;
  if (thread_flag) {
  	ThreadCtrl.splider(384, 10);
  } else {
  	SpliderMain sRun = new SpliderMain();
  	System.err.println("It cost " + sRun.getMovie(1, 384) + " ms!");  
  }
  ```
*********************************
## 2017/5/26

##### 编写数据库操作类

* 首先为了在调用资源的时候尽量方便，编写Movie类用来存放资源，拥有资源的各个属性以及对应的get、set方法。再加入List集合以便排序之后再返回。
* 后台使用Mysql数据库，所以加入mysql对应的jar包，使用jdbc连接数据库。
* 编写Query类，主页有最新、最热等标签，还有一些地方根据资源类型进行分类显示供客户浏览，编写多种不同的方法。部分方法如下：
  ```java
  public ArrayList<Movie> getAllMovie() {}
  public ArrayList<Movie> getMovieByName(String name) {}
  public ArrayList<Movie> getMovieByType(String type) {}
  public List<Movie> getMovieByClick(int num) {}
  public List<Movie> getMovieByDate(int num) {}
  private List<Movie> getMovieByTerm(Comparator<Movie> com,int num) {}
  private ArrayList<Movie> get80SByName(String name) {}
  private ArrayList<Movie> get80SByType(String type) {}
  ```

##### 对接Web主页

* 主页的局部刷新需要用jsp返回一定格式的数据若干行。经过与吴磊的协商决定编写一些jsp用来返回数据，主页传来参数，表明需要的数据类型、排序方式、资源数量。拿到参数之后，通过query获得对应的数据并逐个输出。部分代码如下：

  ```java
  ......
  for(Movie m : list){
  	out.println("<div class=\"info\">");
  	out.println("<div class=\"img_box\">");
  	out.println("<img src=\""+m.getPic()+"\">");
  	out.println("</div>");
  	out.println("<div class=\"item_title\">");
  	out.println("<h3>"+m.getName()+"</h3>");
  	out.println("<p>"+m.getText()+"</p>");
  	out.println("</div>");
  	out.println("</div> ");
  }
  ......
  ```

* 共同把主页进行若干项优化，包括刷新的速度以及稳定性、各盒子的阴影处理、排行榜的位置、资源图片的位置大小、导航栏的缩放以及资源简介信息的处理。

## 2017/5/27~2017/6/2


## 2017/6/3

##### 变动数据库

* 由于项目需要，表中增加了下载量一列，只有用户点击迅雷地址或者复制地址时才会累加。

##### 对接大部分界面

* 主页局部刷新的修复：原局部刷新会刷新出相同的数据，引入刷新变量控制后台取数据。
* 最新和热门刷新变量重复的修复：最新和热门的刷新变量冲突，会导致两者切换时丢失部分数据，适当的时机重置变量。
* 类似于主页，对接了分类栏：动作、喜剧、科幻等等页面。

##### 对接额外的界面

- 详情界面：详情页提供了：标题、类型、上映日期、剧情介绍、链接、截图等信息。前端提供id和tid到后台查询对应资源并打印相关信息。
- 排行榜：排行榜目前只有根据资源下载量(不同于点击量)进行排列，后续会加入更多人性化排榜。例：分类栏按分类展示榜单、详情或搜索栏展示类似于猜你喜欢的榜单。
##### 细节优化

* 共同优化了上述界面各种小细节，小bug，不一一阐述。


## 2017/6/4

* 完成剩余的搜索功能，完善其他界面细节。
*************************************************

# 吴磊

## 2017/5/24

##### 确定项目主题

* 一个类似于80s，电影天堂等的电影网站。提供电影的检索、推荐及下载等服务。

##### 网站名称

* 经过共同探讨，暂定为“蚂蚁电影”。

##### 设计主页布局

* 整体布局颜色采用暗色调

* 主体内容分为三部分，头部、左部、右部，头部主要为导航栏，左部为具体信息、右部位排行少量信息。

* 代码编写主要是编写所需要的基本框架、基本想法，以利于日后的层层叠加、逐渐改善、完善。

* 参考v电影网、80s网、等。

  ​

## 2017/5/25

##### 主页代码的编写

* 网页编写使用HTML、CSS、JavaScript语言编写，但页面一开始基本没使用到JavaScript。
* 导航栏的选项当鼠标划过时背景颜色会有短暂的延迟效果，这使用了CSS3的过渡效果实现的。
* 网页宽、高采用百分比单位计算，这样可以随着浏览器窗口大小变化而变换,以及使用了简单的响应式技术，媒体查询功能。

* 导航栏部分代码

	.rank .list ul li span{
	display: inline-block;
	height:18px;
	width:15px;
	line-height:18px;
	background:#1EC7E6;
	text-align: center;
	border-radius:3px;
	transition:transform 1s,width 1s,height 1s,background 1s;
	}
	.rank .list ul li span:hover{
	-webkit-transform:rotate(360deg);
	width:23px;
	height:23px;
	line-height: 23px;
	background-color:yellow;
	}


* 响应式媒体查询

	@media screen and (max-width:680px){
		.right{
  			display:none;
		}
		header{
  			background:#F5F7F7;
  			position:relative;
  			width:100%;
  			text-align:center;
		}
		header ul{
  			border-top: 1px solid #fff; 
  			position:absolute;
  			top:60px;
  			left:-40px;
  			width:100%;
  			background-color:black;
		}
	}
	



## 2017/5/26

##### 页面使用ajax发送异步请求

* 利用浏览器提供内置对象ActiveXObject或XMLHttpRequest，使用JavaScript语言发送异步请求，可以实现页面的局部刷新。

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
  页面的滑动刷新也可是上述所说实现。

##### 与后台对接

* html页面发送请求到jsp页面，使jsp页面查询数据库，获取所需数据，输出并返回至前端，与前端页面实现简单交互。
* 页面排行榜部分也用到CSS3过渡技术，实现简单小特效。
