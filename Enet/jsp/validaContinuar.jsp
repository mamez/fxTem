<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
	<%
	String datosPse="";
	String tipoFondoGiro = datos.get("tipoFondoGiro").toString();
	String validaCta=datos.get("validaCta")==null && datos.get("validaCta").toString().equals("") ?" ":datos.get("validaCta").toString();
	String msjError=datos.get("msjError")==null && datos.get("msjError").toString().equals("")?"":datos.get("msjError").toString();
	String validaDes=datos.get("validaDes")==null && datos.get("validaDes").toString().equals("")?"":datos.get("validaDes").toString();
	String montoBandera=datos.get("montoBandera")==null && datos.get("montoBandera").toString().equals("")?"":datos.get("montoBandera").toString();
	validaCta=validaCta.substring(0,1);
	if(tipoFondoGiro.equalsIgnoreCase("fondoPSE")){
		datosPse = datos.get("numTrans").toString()+"/"+tipoFondoGiro+"/"+datos.get("comision").toString()+"/"+datos.get("iva").toString()+"/"
			+datos.get("totalDebito").toString()+"/"+datos.get("rate").toString()+"/"+datos.get("equivaPesos").toString()+"/"+datos.get("tempor1").toString()+"/"+datos.get("tempor2").toString()+"/"
			+datos.get("tempor3").toString()+"/"+datos.get("tempor4").toString()+"/"+datos.get("tempor5").toString();
	}
	String res=validaCta+"@"+msjError+"@"+validaDes+"@"+montoBandera+"@"+datosPse;
	%>
	<%=res%>