<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="movie.select.*" %>
<%@ page import="movie.util.*" %>
<%@ page import="movie.sql.*" %>


<%

	List<Movie> list = null;

	Select s =  new Select();
	



		list = s.getMovieByDownload(1);
	
	
	
	
	for(int i=0;i<list.size();i++){
		Movie m = list.get(i);
		
		if((i+1)<=3){
			out.print("<li><span class=\"one\">"+(i+1)+"</span> <a target=\"_blank\" href =\"info.jsp?id="+m.getId()+"&tid="+m.getTid()+"\">"+m.getName()+"</a></li>");
		}else{
			out.print("<li><span>"+(i+1)+"</span> <a target=\"_blank\" href =\"info.jsp?id="+m.getId()+"&tid="+m.getTid()+"\">"+m.getName()+"</a></li>");
		}

	}
%>