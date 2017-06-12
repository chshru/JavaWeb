<%@page import="javax.sound.midi.MidiDevice.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="movie.select.*" %>
<%@ page import="movie.util.*" %>
<%@ page import="movie.sql.*" %>


<%


	
	List<Movie> list = null;
	String time = "-1";
	Select s =  new Select();
	
	
	try{
		time = request.getParameter("times");
		
		
	}catch(Exception e){
		
	}
	
	if(time==null||time==""){
		time = "2";
	}
		
	int num = Integer.valueOf(time);
	list = s.getMovieByClick(num);
		
		
	
	
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