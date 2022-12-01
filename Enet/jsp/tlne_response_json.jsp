<jsp:useBean id="datos" type="java.util.Hashtable" scope="request" />
<%
response.setHeader("Pragma","No-Cache");
response.setDateHeader("Expires",0);
response.setHeader("Cache-Control","no-Cache");
response.addHeader( "X-FRAME-OPTIONS", "SAMEORIGIN" ); 
response.setContentType("application/json");
String  jsonResponse =  (String)datos.get("jsonData");
response.getWriter().write(jsonResponse);
%>
