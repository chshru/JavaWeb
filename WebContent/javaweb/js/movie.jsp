<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="movie.select.*" %>
<%@ page import="movie.util.*" %>
<%@ page import="movie.sql.*" %>


<%

	List<Movie> list = null;
	String type = "-1";
	String time = "-1";
	Select s =  new Select();
	
	
	try{
		
		type = request.getParameter("name");
		time = request.getParameter("times");
		
		
	}catch(Exception e){
		
	}

	if(type==null||type==""||time==null||time==""){
		
	}else{
		
		int num = Integer.valueOf(time);
		list = s.getMovieByType(type, num);
		
		
	}
	
	
	
	
	
	for(Movie m:list){
		
		
		out.println("<div class=\"info\">");
		out.println("<img src=\""+m.getPic()+"\">");
		out.println("<div class=\"item_title\">");
		out.println("<h3><a target=\"_blank\" href =\"info.jsp?id="+m.getId()+"&tid="+m.getTid()+"\">"+m.getName()+"</a></h3>");
		out.println("<p>"+m.getText()+"</p>");
		out.println("</div>");
		out.println("</div> ");
		
		
	}
%>