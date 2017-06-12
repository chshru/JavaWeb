<%@page import="javax.sound.midi.MidiDevice.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="movie.select.*" %>
<%@ page import="movie.util.*" %>
<%@ page import="movie.sql.*" %>

<!DOCTYPE html>
<html>
<head>
	<title>蚂蚁电影</title>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1,width=device-width,maximum-scale=3, minimum-scale=1,user-scalable=no">
	<link rel="shortcut icon" type="text/css" href="./images/ico.png">
	<link rel="stylesheet" type="text/css" href="./css/index.css">
	<link rel="stylesheet" type="text/css" href="./css/info.css">
</head>
<body>
	<nav>
		<div class="header" id="header">
			<div class="nav_bar">
			<a href="index.html" id="logo"><img src="./images/ant.png" height="60px" width="109px" style="float: left"></a>
				<ul id="small_nav">
					<li><a href="dz.html">动作片</a></li>
					<li><a href="xj.html">喜剧片</a></li>
					<li><a href="kh.html">科幻片</a></li>
					<li><a href="wx.html">武侠片</a></li>
				</ul>
				<div class="search" id="search">
					<input type="text" id="input" name="">
					<div class="ico" id="ico"></div>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="left small" id="small">
			<div class="content" id="content">
				
				<div class="main_content" id="main_content">
					<div class="text">
						<div>
						<img src="<%
								Movie m = null;
								int id = -1;
								int tid = -1;
								try{
									id = Integer.valueOf(request.getParameter("id"));
									tid = Integer.valueOf(request.getParameter("tid"));
									Select s = new Select();
									m = s.getMovieById(id, tid);
									
								}catch(Exception e){
									e.printStackTrace();
								}
								out.print(m.getPic());
							%>" width="200px" height="194px" >
						</div>
						<div class="item_title">
							<h3>
							<%out.print(m.getName()); %>
							</h3>
							<div><span>类型:</span><% out.print(m.getType()); %></div>
							<div><span>上映日期:</span><% out.print(m.getDate()); %></div>
						</div>
					</div>

				</div>
			</div>
			<div class="juqing">
					<h4>剧情介绍:</h4>
					<% out.print(m.getText()); %>
			</div>
			<div class="link"><p><button>迅雷链接:</button><a href="<% out.print(m.getLink()); %>">点击此处或右键复制链接地址</a></p></div>
			<div class="jietu">
				<p>视频截图:</p>
				<div class="bg">
					<img src="<% out.print(m.getImage()); %>" width=100% >
				</div>
			</div>
		</div>
		<div class="right">
			<div class="sub_nav">
				<div class="rank">
					<h3>下载排行榜</h3>
					<div class="list">
						<ul id="sub_list">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		
		new Update().normalClick(m.getId(), m.getTid());
	
	
	%>
	<script type="text/javascript" src="./js/rank.js"></script>
	<script type="text/javascript" src="./js/a_link.js"></script>
	
</body>
</html>