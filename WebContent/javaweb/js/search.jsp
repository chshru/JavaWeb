<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="movie.select.*" %>
<%@ page import="movie.util.*" %>
<%@ page import="movie.sql.*" %>


<%

	List<Movie> list = null;
	String name = "-1";
	Select s =  new Select();
	
	
	try{
		
		name = request.getParameter("name");

		
		
	}catch(Exception e){
		
	}

	if(name==null||name==""){
		
	}else{
		
		list = s.getMovieByName(name);

	}
	
	
	out.println("<br/>");
	
	
	for(Movie m:list){
		
		
		out.println("<div class=\"info\">");
		out.println("<img src=\""+m.getPic()+"\">");
		out.println("<div class=\"item_title\">");
		out.println("<h3><a target=\"_blank\" href =\"info.jsp?id="+m.getId()+"&tid="+m.getTid()+"\">"+m.getName()+"</a></h3>");
		out.println("<p>"+m.getText()+"</p>");
		out.println("</div>");
		out.println("</div> ");
		
		
	}
	
	
	out.println("<br/>");
	
	
%>