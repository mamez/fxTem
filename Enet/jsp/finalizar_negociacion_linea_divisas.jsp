<!DOCTYPE html>
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.ibm.dse.base.Hashtable"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.util.Locale"%>
<%@page import="com.ibm.dse.base.Trace"%>
<%@page import="java.math.BigDecimal"%>
<html id="ordenesPago" lang="es-ES">
  <head>
	<meta charset="utf-8">
    <title>bbva</title>
    <meta name="description">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="/estilos/version7/mainPagosDivisas.css"/>
	<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css">
    <script src="/js/version7/modernizr.js"></script>
	<!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
    <!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
      <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
    <!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
	<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
	<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>
	<%
	if ((idioma != null) && (idioma.equalsIgnoreCase("en"))) {
%>
<script language="Javascript" src="/js/version7/banner_en.js"></script>
<script language="Javascript" src="/js/version7/utilidades_en.js"> </script>
<input type="hidden" name="s_idioma" value="en">
<%
	} else {
%>
<script language="Javascript" src="/js/version7/banner.js"></script>
<script language="Javascript" src="/js/version7/utilidades.js"> </script>
<input type="hidden" name="s_idioma" value="es">
<%
	}
%> 
 <script>
		 
			
 			//FX INI inc 33 07/03/2018
 			$(function()
			{
				/*INI INC 61 Fx - 30-05-2018*/
				$("#dejarPdte").click(function()
				{
					//INI INC RAD 33 CMC 18/10/2019
					document.getElementById('detalleBenef').removeAttribute("data-url");
					document.getElementById('detalleBenef').removeAttribute("data-toggle");
					document.getElementById('detalleBenef').removeAttribute("href");
					document.getElementById('detalleBenef').classList.remove("botonDetalle");
					//FIN INC RAD 33 CMC 18/10/2019
					$('#btnFirmar').prop('disabled',true);//FX inc 140 17/01/2019
					$('#dejarPdte').prop('disabled',true);//INC 6 FX 17/05/2018
 			 var url = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=pendienteEnvio"; // A dónde se realizará la petición.
 			  var selectOpera = $("#selectOpe").val();		
			  var selectCuentaOrden = $("#selectCuentaOrden").val();		 
			  var selectOpe = selectOpera.substring(0,1);
					$.ajax(
					{
 			           type: "POST",
 			           url: url,
 			           data: $("#modoCreacionManual2").serialize(),            
 			           success: function(data)
 			           {
						   $("#divContenedorHTML").html(data);
							if($("#msjError").val() === "F")
							{
								if(selectOpera=="T1"||selectOpera=="T2")
							window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
								else if (selectOpera=="H1"||selectOpera=="H2")
							window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=traerGiros&selectCuentaOrden=" + selectCuentaOrden+"&selectOpe="+selectOpe;
						}			   		
							else
							{
								$('#dejarPdte').prop('disabled',false);//INC 6 FX 17/05/2018
								$('#btnFirmar').prop('disabled',false);//FX inc 140 17/01/2019
								$("#divErrorWS").removeAttr("style");
 			           }
						}
 			         });
 			    return true; 
					/*FIN INC 61 Fx - 30-05-2018*/
 			 });
 			});
			//FX FIN inc 33 07/03/2018
   $(document).ready(function() {
	ocultar();
	ajustar();
  });
  //FX INI inc 140 27/12/2018
  function deshabilitarBotones(){
	//INI INC RAD 33 CMC 18/10/2019
	document.getElementById('detalleBenef').removeAttribute("data-url");
	document.getElementById('detalleBenef').removeAttribute("data-toggle");
	document.getElementById('detalleBenef').removeAttribute("href");
	document.getElementById('detalleBenef').classList.remove("botonDetalle");
	//FIN INC RAD 33 CMC 18/10/2019
	//FX INI inc 140 17/01/2019
	$('#dejarPdte').prop('disabled',true);
	$('#btnFirmar').prop('disabled',true);
	document.getElementById("modoCreacionManual2").submit();
	//FX FIN inc 140 17/01/2019
  }
  //FX FIN inc 140 27/12/2018
  function disabledPse(){
	  document.getElementById('origin').value = window.location.origin;
	  document.getElementById('detalleBenef').removeAttribute("data-url");
	  document.getElementById('detalleBenef').removeAttribute("data-toggle");
	  document.getElementById('detalleBenef').removeAttribute("href");
	  document.getElementById('detalleBenef').classList.remove("botonDetalle");
	  document.getElementById("postPayment").submit();
  }
 function  ocultar(){
		var selectOpera = $("#selectOpe").val();		
		if(selectOpera=="T1"||selectOpera=="T2"){
			$("#hacia").show();
			$("#desde").hide();
		}else if (selectOpera=="H1"||selectOpera=="H2"){
			$("#desde").show();
			$("#hacia").hide();
			$("#datos").hide();
		}
	}
		//FX INI INC 33 07/03/2018
		// function enviarFirmas(){
		//	document.modoCreacionManual2.action = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=pendienteEnvio";
		//	document.modoCreacionManual2.submit();
		// }
		//FX FIN INC 07 07/03/2018
		 function ajustar(){
				com.bbva.kyop.controller.CoreController.resizeMainContent(200);
			}
		
		function bloquearBoton(){
			  var element2= document.getElementById("pushPse");
		      var selecBank = $('#selecBank').val();
	           if (selecBank ) {
		           	element2.disabled=false;
		           	element2.classList.remove("grandeGrid2");
		           	element2.classList.add("grandeAzul");
	           } else {
		           	element2.disabled=true;
		           	element2.classList.remove("grandeAzul");
		           	element2.classList.add("grandeGrid2");
	           }
		}
  </script>
  </head>
   <body >
   <style>
	.divAzul{
		background-color:#d4edfc;
		padding: 15px 22px 14px 30px;
		color: black;
}

.frow .col3 {
  width: 100%;
  max-width: 600px;
	}

.textSubtitulo{
	margin-top:15spx;
}
@media only screen and (max-width: 480px) {
	.col {  margin: 1% 0 1% 0%;  margin-left: 0 !important;}
	.frow .col3, .frow .col4 { width: 100%; }
}
.grandeAzul2{
	margin-top: 15px;
	width: 100%;
	max-width: 220px;
}
button.grandeAzul.grandeAzul2 {
	border-radius: 0px;
    border: none;
    background: #318fd7;
    color: #fff;
    margin: 15px 0 5px 0px;
    height: 45px;
}
.titulodiv {
    font-size: 30px;
    margin-top: 20px;
    font-weight: bolder;
    margin-bottom: 10px;
}
.col-sm-12.col-md-6.azulHelp {
    background: #D4EDFC;
    padding: 20px;
    width: 100%;
    max-width: 500px;
}
.frow{
	margin-top:50px;
}

.dividirImgP {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 15px;
}

.dividirImgP img {
	float: left;
    margin-right: 1px;
    margin-left: 15px;
}
.dividirImg img{
	float:left;
	margin: 5px 15px 20px 0px;
}
.dividirImg .imgItemL{
	float:left;
	margin: 5px 15px 40px 0px;
}

.col-md-3.cuadro2 {
    padding-left: 30px;
}

    </style>
	<!--CONTAINER -->
    <div id="container" class="container">
        <div id="hacia" class="titulo">
            <div class="col-md-8" style="margin-left: -30px !important;">
                <h1 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 4: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_RESUMEN_OP_FIN_FIRMA") %></h1>
				<p class="subtituloPrincipal"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_ADJ_GIRO") %></p>
			 </div>
            <div class="col-md-4" style="margin-left: 30px !important;">
                <ul id="progreso">
                    <li class="letragris"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 4 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 4</b>
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeLeft_20x25.png">
                    </li>
					<li>
                        <img src="/images/version7/barraProgresoVerdeCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>
		<div id="desde" class="titulo">
            <div  class="col-md-8">
                <h1><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 3: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_RESUMEN_OP_FIN_FIRMA") %></h1> <!-- INC 27 FX CMC - 21/02/2018 -->
				<p class="subtituloPrincipal"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_ADJ_GIRO") %></p>
			 </div>
            <div class="col-md-4">
                <ul id="progreso">
                    <li class="letragris"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 3 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 3</b>
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeLeft_20x25.png">
                    </li>
					<li>
                        <img src="/images/version7/barraProgresoVerdeCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>
		<div class="col-md-12" >
		<% 	String validacionCredenciales = (String) datos.get("msjError");
			if (validacionCredenciales.equals("")){%>
				<!--<div class="alerta bordeOk clearfix" id="messageBoxCM1" style="height: 42px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaOk.png"> 
                    </div>
					<div id="cerrarmsj1" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM1"> Operación diligenciada con éxito, debe ser firmada y enviada.</ul>
                    </div>
                </div>-->
		  <%}else if (validacionCredenciales.equals("TKN")){%>
				<div class="alerta bordeWarning clearfix" id="messageBoxCM2" style="height: 42px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj2" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TOKEN_CLAVE_OP") %></ul>
                    </div>
				</div>
		  <%}%>
		</div>
		<!--INI INC 61 Fx - 30-05-2018-->
		<div class="col-md-12" id="divErrorWS" style="display: none;">
			<div class="alerta bordeWarning clearfix" id="messageBoxCM2" style="height: 42px;">
				<div class="interior1">
					<img src="/images/version7/iconoAlertaWarning.png"> 
				</div>
				<div id="cerrarmsj2" class="interior3">
					<img src="/images/version7/cerrarAlertaDiv.png"> 
				</div>
				<div class="interior2">
					<ul id="messageBoxULCM2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_COMPLETAR_ENVIO") %></ul>
				</div>
			</div>
		</div>
		<!--FIN INC 61 Fx - 30-05-2018-->
	</div>	
		<div class="row sup">
			<div class="col-md-8">
				<!--
				<p align="justify">Este es el resumen de su operación, por favor verifique la información antes de enviarla al aplicativo. </p>
				<p align="justify">BBVA Colombia le notificará mediante un correo electrónico el mensaje Swift que soporta la operación.</p>
					-->
			</div>
		</div>
		<%
		boolean isPseAction = ((String) datos.get("tipoFondoGiro")).equals("fondoPSE")?true:false;
		if(!isPseAction){%>
		<form id="modoCreacionManual2" name="modoCreacionManual2"method="POST" role="form"  action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=enviarOperacion" class="form-horizontal">
		<%}else{ %>
		<form id="postPayment" name="postPayment" method="POST" role="form" action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=postPayment">
		<%} %>
		<%
			String equivPesos = "0.00";
			String tasaDivisa = "0.00";
			String tasaUsdPeso = "0.00";
			String tasaDivisaPeso = "0.00";
			if(datos.get("equivPesos")!=null && datos.get("equivPesos")!=""){
				equivPesos= (String) datos.get("equivPesos");
			}
			if(datos.get("tasaDivisa")!=null && datos.get("tasaDivisa")!=""){
				tasaDivisa= (String) datos.get("tasaDivisa");
			}
			if(datos.get("tasaUsdPeso")!=null && datos.get("tasaUsdPeso")!=""){
				tasaUsdPeso= (String) datos.get("tasaUsdPeso");
			}
			if(datos.get("tasaDivisaPeso")!=null && datos.get("tasaDivisaPeso")!=""){
				tasaDivisaPeso= (String) datos.get("tasaDivisaPeso");
			}
			String mancomunada = (String) datos.get("mancomunada");
			
		%>
			<input type="hidden" id="selectOpe" name="selectOpe" value="<%=datos.get("selectOpe")%>"/>
			<input type="hidden" id="NumeralC" name="NumeralC" value="<%=datos.get("NumeralC")%>"/>
			<input type="hidden" id="descNumeral" name="descNumeral" value="<%=datos.get("descNumeral")%>"/>
			<input type="hidden" id="monto" name="monto" value="<%=datos.get("monto")%>" />
			<input type="hidden" id="Formulario" name="Formulario" value="<%=datos.get("Formulario")%>" />
			<input type="hidden" id="selectMoneda" name="selectMoneda" value="<%=datos.get("selectMoneda")%>" />
			<input type="hidden" id="selectCuentaOrden" name="selectCuentaOrden" value="<%=datos.get("selectCuentaOrden")%>" />
			<input type="hidden" id="docBenefi" name="docBenefi" value="<%=datos.get("docBenefi")%>" />
			<input type="hidden" id="descripNegociacion" name="descripNegociacion" value="<%=datos.get("descripNegociacion")%>" />
			<input type="hidden" id="referenciaOpe" name="referenciaOpe" value="<%=datos.get("referenciaOpe")%>" />
			<input type="hidden" id="avanceOpe" name="avanceOpe" value="<%=datos.get("avanceOpe")%>" />
			<input type="hidden" id="equivPesos" name="equivPesos" value="<%=equivPesos%>" />
			<input type="hidden" id="tasaDivisa" name="tasaDivisa" value="<%=tasaDivisa%>" />
			<input type="hidden" id="tasaUsdPeso" name="tasaUsdPeso" value="<%=tasaUsdPeso%>" />
			<input type="hidden" id="tasaDivisaPeso" name="tasaDivisaPeso" value="<%=tasaDivisaPeso%>" />
			<input type="hidden" id="firma" name="firma" value="<%=datos.get("firma")%>" />
			<div id="divContenedorHTML" style="display: none;"></div>  <!--INC 61 Fx - 30-05-2018-->
			<input type="hidden" id="E_Fijo_Nit" name="E_Fijo_Nit" value="<%=datos.get("s_Fijo_Nit")%>" /> <%-- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
			<input type="hidden" id="E_Fijo_Nombre" name="E_Fijo_Nombre" value="<%=datos.get("s_Fijo_Nombre")%>" /> <%-- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
			<input type="hidden" id="rate" name="rate" value="<%=datos.get("rate")%>" />
			<input type="hidden" id="totalDebito" name="totalDebito" value="<%=datos.get("totalDebito")%>" />
			<input type="hidden" id="numTrans" name="numTrans" value="<%=datos.get("numTrans")%>" />
			<input type="hidden" id="origin" name="origin" value="" />
			<input type="hidden" id="equivaPesos" name="equivaPesos" value="<%=datos.get("equivaPesos")%>">
			<input type="hidden" id="comision" name="comision" value="<%=datos.get("comision")%>">
			<input type="hidden" id="iva" name="iva" value="<%=datos.get("iva")%>">
		<%
			String documentofull = (String) datos.get("docBenefi");
			String[] parts = documentofull.split("\\|@\\|"); /*INC 02 FX CMC 09/02/2018 */
			String tipoId = parts[0];
			String numId = parts[1];
			String digVer = parts[2];
			String ctaBenef = parts[3];
			String nombres = parts[4];
			//INI INC 257 CMC 22-08-2018
			String apellidos="";
			if(parts.length>5){
				apellidos = parts[5];
			}
			//FIN INC 257 CMC 22-08-2018
			String descOpe = "";
			String formNumber = (String) datos.get("Formulario");
			String equivPesosTMP=equivPesos.replaceAll(",", "");
			double firmaNum=new Double("0");
			double importe=new Double("0");
			String formulario = "";
			if(formNumber!= null){
				int numberForm = Integer.parseInt(formNumber);
			 switch (numberForm) {
				case 1:
					formulario = CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_IMPORTACION_BIENES");
					break;
				case 2:
					formulario = CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_EXPORTACION_BIENES");
					break;
				case 3:
					formulario = CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ENDEUDAMIENTO_EXT");
					break;
				case 4:
					formulario = CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INVERSIONES_INTERNACIONALES");
					break;
				case 5:
					formulario = CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SERVICIOS_CONCEPTOS");
					break;
				default:
					formulario = "";
					break;	
				}
			}		
			String esLinea = "S";
			String selectOpe = (String) datos.get("selectOpe");
			String referenciaOpe = (String) datos.get("referenciaOpe");
			if(selectOpe.equals("T1")){
			selectOpe= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_LINEA") + " - " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_HACIA_EXT");
			descOpe="girosHacia";
			referenciaOpe = "T"+referenciaOpe; 
			esLinea = "S";
		}else if(selectOpe.equals("H1")){
			selectOpe=CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_LINEA") + " - " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_DESDE_EXT");
			descOpe="girosDesde";
			referenciaOpe = "H"+referenciaOpe;
			esLinea = "S";
		}else if(selectOpe.equals("T2")){
			selectOpe=CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_MESA_DINERO") + " - " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_HACIA_EXT");
			descOpe="girosHacia";
			referenciaOpe = "T"+referenciaOpe;
			esLinea = "N";
		}else if(selectOpe.equals("H2")){
			selectOpe=CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_MESA_DINERO") + " - " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_DESDE_EXT");
			descOpe="girosDesde";
			referenciaOpe = "H"+referenciaOpe;
			esLinea = "N";
		}
		String selectMoneda = (String)datos.get("selectMoneda");
				 if(selectMoneda!= null && selectMoneda!= ""){
					selectMoneda= selectMoneda.substring(0,3);
					}
		String avanceOpe = (String) datos.get("avanceOpe");
		//INI INC FX 76 19-07-2018
		//INI INC 209.B 10-07-2019	
		if(null!=avanceOpe && avanceOpe.length()>1){
			try{
				avanceOpe= avanceOpe.trim();
				if(avanceOpe.contains("STD1")){
					avanceOpe = "N.A.";
				}
				else{					
					avanceOpe=""+Integer.parseInt(avanceOpe);
				}				
			}catch(Exception exN){}
		}
		//FIN INC 209.B 10-07-2019
		//FIN INC FX 76 19-07-2018	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String fechaActual = sdf.format(new Date());
		if(datos.get("firma")!= null){
			String firmaSt = (String) datos.get("firma");
			firmaNum=Double.parseDouble(firmaSt);
			importe=Double.parseDouble(equivPesosTMP);
		}
		%>
		<input type="hidden" id="firmaNum" name="firmaNum" value="<%=firmaNum%>" />
		<input type="hidden" id="importe" name="importe" value="<%=importe%>" />
			<div class="row resumen">
			<div class="alineaDerecha2">
				<button type="button" class="buttonGiroAzul" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_IMPRIMIR") %></button>
			</div>
			<div class="col-md-11" style="margin-top: -35px !important;">
				<div class="col-md-4">				
					<ul>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO_NEG") %>: </b> <%=selectOpe%></li>
						<!-- INI INC  28 FX CMC 06/02/2018 -->
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_FECHA_OPE") %>: </b><%=fechaActual%> </li>
						<!-- FIN INC  28 FX CMC 06/02/2018 -->
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_OPE") %>: </b> <%=referenciaOpe%></li>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_AVANCE") %> </b><%=avanceOpe%>  </li>
<%-- 						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <% if(selectOpe.contains("hacia")){%><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBITAR") %><% } else {%><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ABONAR") %><% } %>: </b> <%=datos.get("selectCuentaOrden")%></li><!-- INC 6 FX 18/05/2018 -->
 --%>					
						<li><%if(!isPseAction){%>
						<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <% if(selectOpe.contains("hacia")){%><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBITAR") %><% } else {%><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ABONAR") %><% } %>: </b> <%=datos.get("selectCuentaOrden")%>
						<%}else{%>
						<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_METO_PAGO")%></b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA_PSE")%>
						<%}%>
						</li><!-- INC 6 FX 18/05/2018 -->	
						 
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT") %> </b><%=datos.get("s_Fijo_Nit")%>  </li><%-- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT_NOM_CLIENT") %></b><%=datos.get("s_Fijo_Nombre") %></li><%-- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
					</ul>
				</div>
				<div class="col-md-3 cuadro2">
				    <ul>
					<!-- INI INC 6 FX 05-04-2018 -->
					<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESCRIPCION") %> </b> <%=datos.get("descripNegociacion")%></li>
					<!-- INI INC 6.1 04-07-2018 -->
					<%
				            String resultadoMonto=(String)datos.get("monto");
				            try{
				                String numero=resultadoMonto;
				                numero= numero.replaceAll(",", "");
				                Double numeroResul = new Double(numero);
				                 java.text.DecimalFormatSymbols simbolos = new  java.text.DecimalFormatSymbols();
				                simbolos.setDecimalSeparator('.');
				                simbolos.setGroupingSeparator(',');
				                java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.00##", simbolos);
				                resultadoMonto=df.format(numeroResul);
				            }catch(Exception e){
				            }
						%>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO") %>: </b> <%=resultadoMonto%> <%=selectMoneda%></li>
						<!-- FIN INC 6.1 04-07-2018 -->
						<!--<li><b>Moneda negociada: </b>  <%=selectMoneda%></li>-->
						<%//if(esLinea.equals("S")){INC 85 FX CMC 07-11-2018%>
						<%//INI INC 6 FX 18/05/2018
							if(!selectMoneda.contains("USD") && !((String)datos.get("tasaDivisa")).equals("")){
							 if (selectMoneda.contains("GBP") || selectMoneda.contains("EUR")) { %>
							 <!--Inicio inc 50 eliminacion temporal divisas/usd cuando no es dolar pero es moneda fuerte 26062018-->
						<!--descomentarear <li><b>Tasa <%=selectMoneda%>/USD: </b>  <%=datos.get("tasaDivisa")%></li>-->
							<!--Fin inc 50 eliminacion temporal divisa fuerte 26062018-->
							 <% } else { %>
							 <!--Inicio inc 50 eliminacion temporal divisas cuando no es dolar pero no es moneda fuerte 26062018-->
						<!-- descomentarear <li><b>Tasa USD/<%=selectMoneda%>: </b>  <%=datos.get("tasaDivisa")%></li> -->
							<!--Fin inc50 eliminacion temporal divisas 26062018-->
						<% } } %>
						<!-- Descomentarear <li><b>Tasa USD/COP: </b><%=datos.get("tasaUsdPeso")%> </li> ini inc50 26062018-->						
						<% if (!tasaDivisa.equals("") && !selectMoneda.equals("USD")) { %>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> <%=selectMoneda%>/COP: </b> <%=tasaDivisaPeso%></li>
						<% } %>
						<!-- FIN INC 6 FX 18/05/2018 -->
						<!--INI FX Caso Victor Arevalo 2020-08-20 -->
						<% String operacionDivisaFinaliza = (String) datos.get("selectOpe");
						if ((operacionDivisaFinaliza != null) && !(operacionDivisaFinaliza.trim().equalsIgnoreCase("T2") || operacionDivisaFinaliza.trim().equalsIgnoreCase("H2"))){ %>
							<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_EQU") %>: </b>  <%=equivPesos%></li>
						<% } %>
						<!--FIN FX Caso Victor Arevalo 2020-08-20 -->
						<%//}INC 85 FX CMC 07-11-2018%>
						<!-- FIN INC 6 FX 05-04-2018 -->
						
						<li><b><!--Información mínima cambiaria:--><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONCEPTO") %> </b> <%=formulario%></li>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CAMBI") %> </b> <%=datos.get("NumeralC")%></li>
						<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ESTADO") %> </b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_EN_PROCESO_CLIENTE") %></li>
						<%
				            String resultadoComision=(String)datos.get("comision");
				            try{
				                String numero=resultadoComision;
				                numero= numero.replaceAll(",", "");
				                Double numeroResul = new Double(numero);
				                 java.text.DecimalFormatSymbols simbolos = new  java.text.DecimalFormatSymbols();
				                simbolos.setDecimalSeparator('.');
				                simbolos.setGroupingSeparator(',');
				                java.text.DecimalFormat df = new java.text.DecimalFormat("###,###.##", simbolos);
				                resultadoComision=df.format(numeroResul);
				            }catch(Exception e){
				            }
						%>
						<%if(!isPseAction){%>
							
						<%}else{%>
							<li><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_COMISION") %> </b> <%=resultadoComision%></li>
						<%}%><!-- INC 61 FX 30/05/2018 -->
					</ul>
				</div>
				<div class="col-md-2">				
					<ul>
						<%
				            String resultadoIva=(String)datos.get("iva");
				            try{
				                String numero=resultadoIva;
				                numero= numero.replaceAll(",", "");
				                Double numeroResul = new Double(numero);
				                 java.text.DecimalFormatSymbols simbolos = new  java.text.DecimalFormatSymbols();
				                simbolos.setDecimalSeparator('.');
				                simbolos.setGroupingSeparator(',');
				                java.text.DecimalFormat df = new java.text.DecimalFormat("###,###.##", simbolos);
				                resultadoIva=df.format(numeroResul);
				            }catch(Exception e){
				            }
						%>
					
						<%
				            String resultadoDebito=(String)datos.get("totalDebito");
				            try{
				                String numero=resultadoDebito;
				                numero= numero.replaceAll(",", "");
				                Double numeroResul = new Double(numero);
				                 java.text.DecimalFormatSymbols simbolos = new  java.text.DecimalFormatSymbols();
				                simbolos.setDecimalSeparator('.');
				                simbolos.setGroupingSeparator(',');
				                java.text.DecimalFormat df = new java.text.DecimalFormat("###,###.##", simbolos);
				                resultadoDebito=df.format(numeroResul);
				            }catch(Exception e){
				            }
						%>
						<%if(!isPseAction){%>
							
						<%}else{%>
							<li><b class="izquiFinIva"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_IVA") %> </b><%=resultadoIva%></li>
							<li><b class="izquiFinTotal"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_TOTAL_DEB") %> </b><%=resultadoDebito%></li>
						<%}%>
						
					</ul>
				</div>
			</div>
		</div>
		<div id="datos" class="row">
			<div class="col-md-12">
				<div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a id="detalleBenef" class="botonDetalle collapsed" data-url="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=verUnico&docBenefi=<%=datos.get("docBenefi")%>" data-toggle="collapse" href="#modalCollapseUno"><%--INC RAD 33 CMC 18/10/2019 --%>
                                    <img class="closedAccord" alt="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PLEGAR") %>" src="/images/version7/mas16x16Gris.png" onclick="ajustar()">
                                    <img class="openAccord" alt="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESPLEGAR") %>" src="/images/version7/menos16x16Azul.png" >
									<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEFICIARIO") %>: <%=nombres%> <%=apellidos%> | <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CUENTA") %>: <%=ctaBenef%><!-- INC 6 FX 05-04-2018 -->
                                </a>								
                            </h3>
						</div>
                        <div id="modalCollapseUno" class="panel-collapse collapse clearAll"></div>
                    </div>
                </div>
			</div>
		</div>
	<%
	Trace.trace(64, "Informacion: " + this.getClass().getCanonicalName() + "._JSP():" + " validacion mancomunado ----> " + mancomunada);
	if(!isPseAction){
	if (mancomunada!=null){
		if(mancomunada!="" && !mancomunada.equals("SI") && firmaNum>=importe){
	%>
		<div class="row">
			<div class="col-md-12 posicionAlerta">
				<h2><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_FIRMAR_OP_PEND") %></h2>
				<p class="subtituloPrincipal"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBE_COMPLETAR_CAMPOS") %></p>
				<div class="form-group">
					<label for="ClaveOperacion" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CLAVE_OP") %>:</label>
					<div class="col-sm-9">
						<input class="inputPassword" required="true" id="ClaveOperacion" type="password" name="ClaveOperacion" maxlength="8" />
					</div>
				</div>
				<div class="form-group">
					<label for="token" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CLAVE_TOKEN") %>:</label>
					<div class="col-sm-9">
						<input class="inputPassword" required="true" id="token" type="password" name="token" maxlength="8" />
						<img class="imgToken" src="/images/version7/icon-dispositivo.png" />
					</div>
				</div>
			</div>
		</div>
	<%}}%>
		<div class="botoneraCreacion">
           <div  class="col-md-6 col-xs-6">
                    <ul>
                        <li><a href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=<%=descOpe%>">«« <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VOLVER_INICIO") %></a>
						<a class="tooltipAyuda" href="#">
                        <img src="/images/version7/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class="tooltipFlechaIzquierda ocultar">
                        <p class="letragris">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INF_AYUDA") %>
                        </p>						
                    </div></li>
                    </ul>					
                </div>
			<!-- FX INI INC 140 17/01/2019 -->
			<div  class="col-md-6 col-xs-6" >
				<%
				if (mancomunada!=null){
					if(mancomunada!="" && !mancomunada.equals("SI") && firmaNum>=importe){
				%>
					<!-- FX INI INC 140 27/11/2018 -->
					<button  type="button" id="btnFirmar" class="grandeAzul" onclick="deshabilitarBotones();"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_FIRMAR") %></button>	
				<%}}%>
				<!-- FX INI INC 33 07/03/2018 -->
				<!-- <button type="button" onclick="enviarFirmas()" class="grandeAzul" > Dejar pendiente de firma</button> -->
				<button type="button" id="dejarPdte" class="grandeAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEJAR_PENDIENTE_FIRMA") %></button>
				<!-- FX FIN INC 33 07/03/2018 -->
				<!-- FX FIN INC 140 27/12/2018 -->
			</div>
			<!-- FX FIN INC 140 17/01/2019 -->
         </div>
		</form>
		<%
		// Funcionalidad pagos pse PSE
	   }else{
		   java.util.Vector listaBancoAch = (java.util.Vector)(datos.get("listaBancoAch")); 
		   java.util.Enumeration enumlistaBancoAch = listaBancoAch.elements();
		   java.util.Hashtable bancoAch;
	   %>
	<!-- INICIO Desarrollos PSE  -->
	<div class="frow">
        <div class="col-sm-12 col-md-6">
         <img alt="" src="/images/version7/pseDiv.png" width="35px">
				<h1 class="titulodiv"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_PSE_2") %></h1>
				<p style="font-size:15px; max-width: 330px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TRANSFE_PAGO_PSE") %></p>
				<select id="selecBank" name="selectBank" onchange="bloquearBoton()" style ="width:100%; max-width:350px; margin-top: 6px; font-size: 15px; height:30px ;">
					<option id="bloqeuadoBtnOption" value=""><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BANCO_ORIEN") %></option>
					<%while(enumlistaBancoAch.hasMoreElements()){ 
	        	  bancoAch=(java.util.Hashtable)enumlistaBancoAch.nextElement();
	    %>
					<option id="bloqeuadoBtnOption2"  value="<%=(String)bancoAch.get("idBanco")+"@"+(String)bancoAch.get("name")%>"><%=bancoAch.get("name") %></option>
					<%}%>
				</select><br>
				<button type="button" class="grandeGrid2" id="pushPse" onclick="disabledPse()" style="margin: 15px 0 5px 0px; height: 45px;" disabled>
				<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR_PAGO") %></button>
        </div>
        <div class="col-sm-12 col-md-6 azulHelp">
			<div class="dividirImgP">
				<img src="/images/version7/infDiv.png" class="imgInf">
				<p class="tituloNotaDiv" >
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %>
				</p>
			</div>
			<div class="dividirImg">
				<img src="/images/version7/itemDiv.png" class="imgItem">
				<%
					String cuentaOrden = (String) datos.get("selectCuentaOrden");
					cuentaOrden = cuentaOrden.substring(cuentaOrden.length()-4);
				%>
				<p class="textSubtitulo"style="font-size:15px;">
					 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TRANSFE_DINERO") %><b>*<%=cuentaOrden%>.</b>
				</p>
			</div>
				
				<div class="dividirImg">
					<img src="/images/version7/itemDiv.png" class="imgItem">
					<p class="textSubtitulo"style="font-size:15px;">
						<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LIMI_POR_BANCO") %>
					</p>
				</div>
				
				<div class="dividirImg">
					<img src="/images/version7/itemDiv.png" class="imgItem"> 
					<p class="textSubtitulo"style="font-size:15px;">
						<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_PAGO_CUENTA") %> <b style="text-decoration: underline; display: contents;">
						<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_PAGO_CUENTA_2") %></b>
						 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_PAGO_CUENTA_3") %>
					</p>
				</div>
				
				<div class="dividirImg">
					<img src="/images/version7/itemDiv.png" class="imgItem">
					<p class="textSubtitulo"style="font-size:15px;">
						 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PAGO_BAN_REDI_NET_FIN") %>
					</p>
				</div>
				
        </div>
        
      </div>
		<!-- FIN Desarrollos PSE  -->
		<% }
	String mensajeError = (String) datos.get("msjError");
	Trace.trace(64, "Informacion: " + this.getClass().getCanonicalName() + "._JSP():" + " valor msjError ----> " + mensajeError);
	%>
	</div>
	<!-- <script src="/js/version7/vendorDivisas.js"></script> --><!-- FX - CASO 3 - CMC -  PRUEBAS - 15/02/2019 -->
	<script src="/js/version7/pluginsDivisas.js"></script>
    <script src="/js/version7/op_objDivisas.js"></script>
	<% if((idioma!=null)&&(idioma.equalsIgnoreCase("en"))){ %>
	<script src="/js/version7/Divisas_EN.js"></script>
	<% }else{ %>
	<script src="/js/version7/Divisas_ES.js"></script>
	<% } %>
	<script src="/js/version7/mainPagosDivisas.js"></script>
	<script src="/js/version7/mainCreacionNegDivisas.js"></script>
   </body>
</html>				   