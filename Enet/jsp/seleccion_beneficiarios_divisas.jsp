<!doctype html>
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.ibm.dse.base.Hashtable"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.util.Locale"%>
<html id="ordenesPago" lang="es-ES">
<head>
    <meta charset="utf-8">
    <title>bbva</title>
    <meta name="description">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="/estilos/version7/mainPagosDivisas.css"/>
    <link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"><!-- GP17667 -->
    <script src="/js/version7/modernizr.js"></script>
	<!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
    <!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
     <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
    <!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
	<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
	<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>
	<script language="JavaScript" src="/js/version7/negociacion_Divisas_Scripts.js"></script><%--INI INC 225B FX CMC 30/10/2019--%>
	<%
	if ((idioma != null) && (idioma.equalsIgnoreCase("en"))) {
%>
<script type="text/javascript" src="/js/version7/form_validate_en.js"></script>
<script language="Javascript" src="/js/version7/banner_en.js"></script>
<script language="Javascript" src="/js/version7/utilidades_en.js"> </script>
<input type="hidden" name="s_idioma" value="en">
<%
	} else {
%>
<script type="text/javascript" src="/js/version7/form_validate.js"></script>
<script language="Javascript" src="/js/version7/banner.js"></script>
<script language="Javascript" src="/js/version7/utilidades.js"> </script>
<input type="hidden" name="s_idioma" value="es">
<%
	}
%> 
	 <script>
		 $(document).ready(function() {
			validaErrorSWIFT();<%--Incidencia 225B FX CMC 30/10/2019--%>
			var ind  = $("#indBenef").val();
			$("#btnContinuar").hide();
			agregarBenef();
				if(ind=="B"){
					$("#messageBoxCM3").show();
					$("#messageBoxCM4").hide();
					$("#messageBoxCM5").hide();
					$("#messageBoxCM1").hide();
					$("#messageBoxCM2").hide();
				}
				if(ind=="A"){	
					$("#messageBoxCM4").show();
					$("#messageBoxCM3").hide();
					$("#messageBoxCM5").hide();
					$("#messageBoxCM1").hide();
					$("#messageBoxCM2").hide();
				}
				if(ind=="M"){
					$("#messageBoxCM5").show();
					$("#messageBoxCM3").hide();
					$("#messageBoxCM4").hide();
					$("#messageBoxCM1").hide();
					$("#messageBoxCM2").hide();
				}
				ajustar();
		 });
		function validarA1(){
			document.getElementById("aceptarBeneficiario").disabled="true";
			document.formMBefeniciarioManual.action = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=selecBeneficiarios";
			document.formMBefeniciarioManual.submit();
		}
		 function declinar(){
			if($("#selectOpe").val()=="T1" || $("#selectOpe").val()=="T2"){
				window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
			}else if ($("#selectOpe").val()=="H1" || $("#selectOpe").val()=="H2"){
				window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosDesde";
			}
		 }
		//INI CASO 4 FX CMC 21/01/2019
          function bloquearBoton(){
	       var elementBTC3= document.getElementById("btnContinuar");
		       elementBTC3.disabled=true;
		       elementBTC3.classList.remove("buttonGiroAzul");
		       elementBTC3.classList.add("buttonGiro");
	       document.getElementById("modoCreacionManual2").submit();
        }
        //FIN CASO 4 FX CMC 21/01/2019
		function checkBtn(cntReg){
			cntReg2="#checkBtn" + cntReg;
			var doc = $(cntReg2).val();
			$("#docBenefi").val(doc);
			modificarBenef();
			var totalCheck = $("input[type='checkbox']").length;
			for (var i = 0; i <= totalCheck; i++) {
					tmp= "#checkBtn" + i;
					if(i!=cntReg){
						$(tmp).prop('checked', false);
					}
				}
			var totalChecked = $("input[type='checkbox']:checked").length;
			if(totalChecked==1){
				$("#btnModificarBeneficiarioManual").show();
				$("#btnEliminarBeneficiarioManual").show();
				$("#btnContinuar").show();
			}else{
				$("#btnModificarBeneficiarioManual").hide();
				$("#btnEliminarBeneficiarioManual").hide();
				$("#btnContinuar").hide();
			}
		}
		 function eliminarBenef(){
			var docBenefi = $("#docBenefi").val();
			var tasaDivisa = $("#tasaDivisa").val();
			var tasaDivisaPeso = $("#tasaDivisaPeso").val();
			var tasaUsdPeso = $("#tasaUsdPeso").val();
			var equivPesos = $("#equivPesos").val();
			var selectOpe = $("#selectOpe").val();
			var selectCuentaOrden = $("#selectCuentaOrden").val();
			var monto = $("#monto").val();
			var selectMoneda = $("#selectMoneda").val();
			var avanceOpe = $("#avanceOpe").val();
			var descripNegociacion = $("#descripNegociacion").val();
			var tipoOperacion = $("#tipoOperacion").val();
			var numOperacion = $("#numOperacion").val();
			var tipoFondoGiro = $("#tipoFondoGiro").val();
			window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=eliminarBenef&indBenef=B&docBenefi=" + docBenefi 
			+ "&tasaDivisa=" + tasaDivisa
			+ "&tasaDivisaPeso=" + tasaDivisaPeso
			+ "&tasaUsdPeso=" + tasaUsdPeso
			+ "&equivPesos=" + equivPesos
			+ "&selectOpe=" + selectOpe
			+ "&selectCuentaOrden=" + selectCuentaOrden
			+ "&monto=" + monto
			+ "&selectMoneda=" + selectMoneda
			+ "&avanceOpe=" + avanceOpe
			+ "&descripNegociacion=" + descripNegociacion
			+ "&numOperacion=" + numOperacion
			+ "&tipoOperacion=" + tipoOperacion
			+ "&tipoFondoGiro=" + tipoFondoGiro;
		 }
		 function modificarBenef(){
		 var docBenefi = $("#docBenefi").val();
		 var tasaDivisa = $("#tasaDivisa").val();
		 var tasaDivisaPeso = $("#tasaDivisaPeso").val();
		 var tasaUsdPeso = $("#tasaUsdPeso").val();
		 var equivPesos = $("#equivPesos").val();
		 var selectOpe = $("#selectOpe").val();
		 var selectCuentaOrden = $("#selectCuentaOrden").val();
		 var monto = $("#monto").val();
		 var selectMoneda = $("#selectMoneda").val();
		 var avanceOpe = $("#avanceOpe").val();
		 var descripNegociacion = $("#descripNegociacion").val();
		 var tipoOperacion = $("#tipoOperacion").val();
		 var numOperacion = $("#numOperacion").val();
		 var tipoFondoGiro = $("#tipoFondoGiro").val();
		 var urlM = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=gestionBeneficiarios&indBenef=M&docBenefi=" + docBenefi
		 + "&tasaDivisa=" + tasaDivisa
		 + "&tasaDivisaPeso=" + tasaDivisaPeso
		 + "&tasaUsdPeso=" + tasaUsdPeso
		 + "&equivPesos=" + equivPesos
		 + "&selectOpe=" + selectOpe
		 + "&selectCuentaOrden=" + selectCuentaOrden
		 + "&monto=" + monto
		 + "&selectMoneda=" + selectMoneda
		 + "&avanceOpe=" + avanceOpe
		 + "&descripNegociacion=" + descripNegociacion
		 + "&numOperacion=" + numOperacion
		 + "&tipoOperacion=" + tipoOperacion
		 + "&tipoFondoGiro=" + tipoFondoGiro;
		 $("#btnModificarBeneficiarioManual").attr("data-url", urlM);
		}
		function agregarBenef(){
		 var tasaDivisa = $("#tasaDivisa").val();
		 var tasaDivisaPeso = $("#tasaDivisaPeso").val();
		 var tasaUsdPeso = $("#tasaUsdPeso").val();
		 var equivPesos = $("#equivPesos").val();
		 var selectOpe = $("#selectOpe").val();
		 var selectCuentaOrden = $("#selectCuentaOrden").val();
		 var monto = $("#monto").val();
		 var selectMoneda = $("#selectMoneda").val();
		 var avanceOpe = $("#avanceOpe").val();
		 var descripNegociacion = $("#descripNegociacion").val();
		 var tipoOperacion = $("#tipoOperacion").val();
		 var numOperacion = $("#numOperacion").val();
		 var tipoFondoGiro = $("#tipoFondoGiro").val();
		 var urlA = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=gestionBeneficiarios&indBenef=A"
		 + "&tasaDivisa=" + tasaDivisa
		 + "&tasaDivisaPeso=" + tasaDivisaPeso
		 + "&tasaUsdPeso=" + tasaUsdPeso
		 + "&equivPesos=" + equivPesos
		 + "&selectOpe=" + selectOpe
		 + "&selectCuentaOrden=" + selectCuentaOrden
		 + "&monto=" + monto
		 + "&selectMoneda=" + selectMoneda
		 + "&avanceOpe=" + avanceOpe
		 + "&descripNegociacion=" + descripNegociacion
		 + "&numOperacion=" + numOperacion
		 + "&tipoOperacion=" + tipoOperacion
		 + "&tipoFondoGiro=" + tipoFondoGiro;
		 $("#btAgregarBeneficiarioManual").attr("data-url", urlA);
		}
		function ajustar(){
				com.bbva.kyop.controller.CoreController.resizeMainContent(300);
			}
		function nameFiller(){
			var nameTMP = document.formMBefeniciarioManual.nombresbeneficTMP.value;
			var n = nameTMP.length;
			if(n > 20){
				var nombreTMP = nameTMP.substring(0, 20);
				var apellidoTMP= nameTMP.substring(20);
				document.formMBefeniciarioManual.nombresbenefic.value = nombreTMP;
				document.formMBefeniciarioManual.apellidosbenef.value = apellidoTMP;
			}else{
				document.formMBefeniciarioManual.nombresbenefic.value = nameTMP;
				document.formMBefeniciarioManual.apellidosbenef.value = ".";
			}
		}
		 //INC 102 FX  CMC 30/10/2018 Inicio		 
		 function verMas(){
			 var selectOpeB = $("#selectOpe").val();
			 var tasaDivisaB = $("#tasaDivisa").val();
			 var tasaDivisaPesoB = $("#tasaDivisaPeso").val();
			 var tasaUsdPesoB = $("#tasaUsdPeso").val();
			 var equivPesosB = $("#equivPesos").val();
			 var selectCuentaOrdenB = $("#selectCuentaOrden").val();
			 var montoB = $("#monto").val();
			 var selectMonedaB = $("#selectMoneda").val();
			 var avanceOpeB = $("#avanceOpe").val();
			 var descripNegociacionB = $("#descripNegociacion").val();
			 var referenciaOpeB = $("#referenciaOpe").val();
			 var tipoFondoGiro = $("#tipoFondoGiro").val();
	 		 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=selecBeneficiarios"
	 				 +"&monto="+ montoB 
	 				 +"&selectMoneda="+ selectMonedaB 
	 				 +"&selectOpe=" + selectOpeB 
	 				 +"&selectCuentaOrden=" +selectCuentaOrdenB 
	 				 +"&descripNegociacion=" + descripNegociacionB 
	 				 +"&tasaDivisa=" + tasaDivisaB 
	 				 +"&tasaDivisaPeso=" + tasaDivisaPesoB 
	 				 +"&tasaUsdPeso="  + tasaUsdPesoB 
	 				 +"&equivPesos=" + equivPesosB 
	 				 +"&avanceOpe=" + avanceOpeB 
	 				 +"&referenciaOpe=" + referenciaOpeB
	 				 +"&tipoFondoGiro=" + tipoFondoGiro;
		  //window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=selecBeneficiarios";		 
	 	 ajustar();
		 }
		 function verAtras(){
		 window.location.href="#top";
		 }
		//INC 102 FX  CMC 30/10/2018 Fin
		//inicio Incidencia 103 FX CDAH 8-11-2018
		function validarSwift(valor,tipe){
			var bandera = true;  	//INC 121 CMC 22-11-2018
			switch(tipe) {  	
				case 'swift':       
				tipe = /^([0-9a-zA-Z()-:,.+\s]*)$/;   //INC 121 CMC 29-11-2018     
				break;     
				case 'text':       
				tipe = /[A-Za-z0-9\s]/;       
				break;
				case 'only_num':       
				tipe = /^[0-9]+$/;//Incidencia 225 FX CMC 05/08/2019       
				break; 
				case 'both':       
				tipe = /^[0-9a-zA-Z]+$/;//Incidencia 225 FX CMC 05/08/2019        
				break;
			}
			if(tipe.test(valor.value)){
				valor.style.borderColor = "#F1F1F1";
				valor.style.backgroundColor = "#FFFEE8";
				bandera = true;		//INC 121 CMC 29-11-2018 
			}
			else {
				valor.style.borderColor = "#C4136C";
				valor.style.backgroundColor = "#F6E8E8";
				bandera = false;	//INC 121 CMC 29-11-2018 
			}
			return  bandera;		//INC 121 CMC 29-11-2018 
		}
		//fin INcidencia 103 FX CDAH 8-11-2018	
		//INI INC 121 FX CMC 29-11-2018  -->
		function validarTpCun (cunVal,tpCun) {
			//INI incidencia 225 FX CMC 05/08/2019 
			 var block, varcuentacod;
			 var correctoSwift = 0;
			 switch (tpCun.value) {
				case 'SW':
					if (cunVal.value.length == 8 || cunVal.value.length ==11)
					{							
						block = validarSwift(cunVal,'swift');
					    var letras="abcdefghijklmnopqrstuvwxyz";
						varcuentacod = cunVal.value.toLowerCase();
						varcuentacod = varcuentacod.substring(0,6);
						for(i=0; i<=varcuentacod.length-1; i++){
							if ((letras.indexOf(varcuentacod.charAt(i),0)!=-1)){//Si contiene algo diferente a letras en los primeros 6 caracteres
								correctoSwift++;
							}
						}				
					}else{
						if(cunVal.value.length != 8 && cunVal.value.length != 11){
							removeValInputIntBenf(cunVal.id);
						}
					}					
					break;
				case 'FW':
					if (cunVal.value.length == 9){block = validarSwift(cunVal,'only_num');}
					else{
						if(cunVal.value.length != 9){
							removeValInputIntBenf(cunVal.id);
						}
					}
				break;
					case 'CT':
					block = validarSwift(cunVal,'swift');
					break;
				default:
					cunVal.value = "";
					break;
			 }
			 if (correctoSwift != 6 && tpCun.value == 'SW'){
				cunVal.style.borderColor = "#C4136C";
				cunVal.style.backgroundColor = "#F6E8E8";
				alert('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_COD_SWIFT") %>');
				return false;	
			 }else if(correctoSwift == 6 && tpCun.value == 'SW'){
					cunVal.style.borderColor = "#F1F1F1";
					cunVal.style.backgroundColor = "#FFFEE8";
				}
			if(block == true && tpCun.value == 'FW'){
				cunVal.style.borderColor = "#F1F1F1";
				cunVal.style.backgroundColor = "#FFFEE8";
			}else if(block == block && tpCun.value == 'FW'){
				cunVal.style.borderColor = "#C4136C";
				cunVal.style.backgroundColor = "#F6E8E8";
				alert('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_COD_ABA") %>');
				return false;
			}
			//if (block ==false){cunVal.value = "";} //INC 121 FX CMC 3/12/2018
			var flagExecute = document.getElementById("flagExecute").value;
			if(block == true && flagExecute=="a"){
				enviarCodigo(cunVal); 
			}
			return true;
			//FIN incidencia 225 FX CMC 06/08/2019
		}
		// FIN INC 121 FX CMC 29-11-2018 -->
		 </script>
</head>
<body>
	<!--CONTAINER -->
    <div id="container" class="container">
        <div class="titulo">
            <div class="col-md-8" style="margin-left: -17px;">
                <h1 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 2: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_BENEFICIARIO") %></h1>
                
                <p> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LISTADO_PASO_2") %> </p>
            </div>
            <div class="col-md-4" style="margin-left: 17px;">
                <ul id="progreso">
                    <li class="letragris"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 2 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 4</b>
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeLeft_20x25.png">
                    </li>
					<li>
                        <img src="/images/version7/barraProgresoVerdeCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVaciaCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVaciaRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>
        <div class="divAzull3">
        		<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_REALIZAR_OPERACION") %></p>
        		<p class="textSubtitulo" align="left" ><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_CORRECCION") %>, <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_CORRECCION_2") %></p>
        </div>
        <%-- <div class="col-md-12">
                <!--<div class="alerta bordeInfo" id="messageBoxCM1" style=" height: 37px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj1" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM1"> Usted ha aceptado la compra de divisas para giro al exterior a trav&eacute;s del aplicativo. Por favor diligencie la siguiente informaci&oacute;n acerca del destino de las divisas.</ul>
                    </div>
                </div>-->
				<div class="alerta bordeInfo" id="messageBoxCM1" style=" height: 50px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj1" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM1"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_HORARIO_OP_DOC") %></ul>
                    </div>
                </div>
				<div class="alerta bordeInfo" id="messageBoxCM2" style=" height: 50px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj2" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_SOL_BBVA") %></ul>
                    </div>
                </div>
				<div class="alerta bordeOk ocultar" id="messageBoxCM3" style=" height: 37px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaOk.png"> 
                    </div>
					<div id="cerrarmsj3" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEF_ELMINADO") %></ul>
                    </div>
                </div>
				<div class="alerta bordeOk ocultar" id="messageBoxCM4" style=" height: 37px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaOk.png"> 
                    </div>
					<div id="cerrarmsj4" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM4"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEF_CREADO") %></ul>
                    </div>
                </div>
				<div class="alerta bordeOk ocultar" id="messageBoxCM5" style=" height: 37px;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaOk.png"> 
                    </div>
					<div id="cerrarmsj5" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM5"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEF_MODIFICADO") %></ul>
                    </div>
				</div>
				INI incidencia 225B FX CMC 02/09/2019
				<div class="alerta bordeWarning ocultar" id="messageBoxCM6">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj7" class="cerrarAlerta">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM7"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_COD_SWIFT2") %></ul>
                    </div>
				</div>
				FIN incidencia 225B FX CMC 02/09/2019
								<%
				String errorBeneficiario = (String) datos.get("errorBeneficiario");
				if(errorBeneficiario.equalsIgnoreCase("S")){%>
				<div class="alerta borderWarning clearfix" id="messageBoxCM8" style="height:41px !important;">
					<div class="interior1">
						<img src="/images/version7/iconoAlertaInfo.png"> 
					</div>
					<div id="cerrarmsj1" class="interior3">
						<img src="/images/version7/cerrarAlertaDiv.png"> 
					</div>
					<div class="interior2">
						<ul id="messageBoxValidBenef"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BEN_VALIDACIONBENEFICIARIO")%></ul>
					</div>
				</div>
				<%} %>
			</div> --%>
		<form id="modoCreacionManual2" name="modoCreacionManual2" action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=inicio" method="POST" role="form"  class="form-horizontal">
			<%
				String selectCuentaOrden = (String)datos.get("selectCuentaOrden");
				 %>
			<input type="hidden" id="selectOpe" name="selectOpe" value="<%=(String)datos.get("selectOpe")%>" />
			<input type="hidden" id="indBenef" name="indBenef" value="<%=(String)datos.get("indBenef")%>" />
			<input type="hidden" id="selectCuentaOrden" name="selectCuentaOrden" value="<%=selectCuentaOrden%>" />
			<input type="hidden" id="docBenefi" name="docBenefi" value="" />
			<input type="hidden" id="descripNegociacion" name="descripNegociacion" value="<%=(String)datos.get("descripNegociacion")%>" />
			<input type="hidden" id="referenciaOpe" name="referenciaOpe" value="<%=(String)datos.get("referenciaOpe")%>" />
			<input type="hidden" id="monto" name="monto" value="<%=(String)datos.get("monto")%>" />
			<input type="hidden" id="selectMoneda" name="selectMoneda" value="<%=(String)datos.get("selectMoneda")%>" />
			<input type="hidden" id="avanceOpe" name="avanceOpe" value="<%=(String)datos.get("avanceOpe")%>" />
			<input type="hidden" id="tipoOperacion" name="tipoOperacion" value="<%=(String)datos.get("tipoOperacion")%>" />
			<input type="hidden" id="numOperacion" name="numOperacion" value="NA" />
			<input type="hidden" id="equivPesos" name="equivPesos" value="<%=datos.get("equivPesos")%>" />
			<input type="hidden" id="tasaDivisa" name="tasaDivisa" value="<%=datos.get("tasaDivisa")%>" />
			<input type="hidden" id="tasaUsdPeso" name="tasaUsdPeso" value="<%=datos.get("tasaUsdPeso")%>" />
			<input type="hidden" id="tasaDivisaPeso" name="tasaDivisaPeso" value="<%=datos.get("tasaDivisaPeso")%>" />
			<input type="hidden" id="errorCodigoSWIFT" name="errorCodigoSWIFT" value="<%=(String)datos.get("errorSwift")%>" /><%--INC 225B FX CMC 02/09/2019--%>
			<input type="hidden" id="tipoFondoGiro" name="tipoFondoGiro" value="<%=(String)datos.get("tipoFondoGiro")%>" />
			<input type="hidden" id="nitNombre" name="nitNombre" value="<%=(String)datos.get("Nit_Nombre")%>" />
			<input type="hidden" id="rate" name="rate" value="<%=(String)datos.get("rate")%>" />
			<input type="hidden" id="totalDebito" name="totalDebito" value="<%=(String)datos.get("totalDebito")%>" />
			<input type="hidden" id="numTrans" name="numTrans" value="<%=(String)datos.get("numTrans")%>" />
			<input type="hidden" id="equivaPesos" name="equivaPesos" value="<%=datos.get("equivaPesos")%>">
			<input type="hidden" id="comision" name="comision" value="<%=datos.get("comision")%>">
			<input type="hidden" id="iva" name="iva" value="<%=datos.get("iva")%>">
           <%-- <div class="sup">
				<div class="col-md-12">
                    <p><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CUENTA_DEBITAR") %>: </b> <%=selectCuentaOrden%></p>
                </div>
                <div class="col-md-12">
                    <p>* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_BENEFICIARIO") %></p>
                </div>
            </div> --%>
			<div class="col-md-12 posicionAlerta" style="width: 103%; margin-left: -17px;">
                    <div class="row titleTable">
                        <div class="col-md-9">
                            <h3><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LISTADO_BENEF") %></h3>
                        </div>
                        <div class="col-md-3">
                            <p><span>1</span> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEFICIARIOS") %> </p>
                        </div>
                    </div>
                    <div class="row subtitleTable">
                        <div class="col-md-7">
                            <ul class="containerDrop">
                                <li>
                                    <div class="ocultar tooltipNormal" id="tooltip_btnModificarBeneficiario"><span class="letragris"></span>
                                    </div>
                                    <button data-toggle="modal" data-url="" value="irEdicionBoton" data-target="#modalComun" class="btnModale3 buttonIcon jqBotonConTooltip jqBotonMicro ocultar" id="btnModificarBeneficiarioManual" name="btnModificarBeneficiarioManual" type="button"><!--INC 209.B BENEFICIARIOS 25-07-2019-->
									<span><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEFICIARIO_MOD") %></span>
                                        <img src="/images/version7/botonFirmar.png" />
                                    </button>
                                </li>
								<li>
                                        <button type="button" id="btnEliminarBeneficiarioManual" class="btnEliminarTodo buttonIcon jqBotonConTooltip jqBotonMicro ocultar" value="eliminarBoton">
                                            <span><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BENEFICIARIO_ELIM") %></span>
                                            <img src="/images/version7/botonEliminar.png" />
                                        </button>
                                        <div class="capaEliminar flechaArriba ocultar">
                                            <div class="cerrar">
                                                <a href="#">
                                                    <img src="/images/version7/cerrar.png" />
                                                </a>
                                            </div>
                                            <div class="alerta bordeWarning clearfix">
                                                <br>
                                                <div class="interior2 msgEliminar">
													<h4 style="color: #2f62a1;" align="center">  <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESEA_ELIMINAR_BENEF") %></h4>
													<br>
													<p><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_RECUPERAR_REG") %></p>
												</div>
                                            </div>
                                            <div >
                                                <button type="button" onclick="eliminarBenef()" class="azul ">
                                                    <span><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ELIMINAR") %></span>
                                                    <img src="/images/version7/eliminar_blanco.png" />
                                                </button>
                                            </div>
                                        </div>
                                    </li>
                            </ul>
                        </div>
                        <div class="col-md-5">
                            <ul class="containerDrop">
                                <li>
                                    <input type="text" placeholder="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUSCAR") %>" class="inpSearchConFondo" />
                                </li>
                                <li>
                                    <div class="ocultar tooltipNormal" id="tooltip_btnAgregarBeneficiario"><span class="letragris"></span>
                                    </div>
                                    <button data-toggle="modal" data-url="" value="irAgregarBeneficiario" data-target="#modalComun" class="btnModal buttonIcon jqBotonConTooltip jqBotonMicro" id="btAgregarBeneficiarioManual" name="btAgregarBeneficiarioManual" type="button">
                                        <span><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_AGREGAR_BENEF") %></span>
                                        <img src="/images/version7/botonFirmar.png" />
                                    </button>
                                </li>
                            </ul>
                        </div>
                        <!--end-botoneraDerecha-->
                 </div>
                    <div class="row">
                        <div class="class="tab-pane divtamdiv" id="tab1Hist" style="display: block;margin-left: 15px;margin-right:15px"><!--INC 102 FX  CMC 6/11/2018--> 
                            <div class="datosTabla">
								<input type="hidden" name="y" value="1">
                                <table id="listadoBeneficiarios">
                                    <thead>
                                        <tr>
                                            <th style="width: 39px;">
                                            </th>
                                            <th style="width: 276px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NOMBRE") %> </th>
                                            <th style="width: 150px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %> </th>
                                            <th style="width: 206;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BAN_BENEF") %> </th>
											<th style="width: 200;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO") %> </th>
                                            <th style="width: 190;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CODIGO_CUENTA") %> </th>
                                       </tr>
                                    </thead>
                                    <tbody>
                                        <%
									Vector listaCuentasB = (Vector) datos.get("listaCuentasB");
									Enumeration enumListaB = listaCuentasB.elements();
									int cntReg = 1;
									String tipoId = "";
									String numId = "";
									String digVer = "";
									String docBenef = "";
									String ctaBenef = "";
									String nombres = "";
									String apellidos = "";
									String code = "";
									String codeName = "";
									if(listaCuentasB.size()>=1 ){
										while (enumListaB.hasMoreElements()) {
											Hashtable klist = (Hashtable) enumListaB.nextElement();
											tipoId = (String) klist.get("tipobeneficiar");
											numId = (String)  klist.get("idbeneficiario");
											digVer = (String)  klist.get("digivbeneficia");
											ctaBenef = (String)  klist.get("cuentabenefica");
											nombres = (String)  klist.get("nombresbenefic");
											apellidos = (String)  klist.get("apellidosbenef");
											code = (String)  klist.get("tiposabanco1");
											if(code.equals("FW")){
												codeName="ABA";
											}else if(code.equals("SW")){
												codeName="SWIFT";
											}else if(code.equals("CT")){
												codeName=CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CUENTA_MAYUS");
											}
											docBenef =  tipoId + "|@|" + numId + "|@|" + digVer + "|@|" + ctaBenef + "|@|" + nombres + "|@|" + apellidos; /*INC 46 FX */
											out.println("<tr ><td class='selectora'><input  id='checkBtn" +  cntReg + "' value='" + docBenef + "' onClick='checkBtn("+ cntReg + ")' name='fieldsCheck' type='checkbox' class='checkbox'></input></td>"
											+ "<td >"
											//INI Incidencia 103 FX 08/10/2018
											+ klist.get("nombresbenefic") + "" + klist.get("apellidosbenef")
											//FIN Incidencia 103 FX 08/10/2018
											+ "</td><td > "
											+ klist.get("cuentabenefica")
											+ "</td><td > "
											+ klist.get("nombrebancobe")
											+ "</td><td > "
											+ codeName
											+ "</td><td > "
											+ klist.get("codigosabanco1")
											+ "</td></tr> ");
											cntReg++;
										}
									}else{
										out.println("<tr><td colspan='5' style='text-align:center;'> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_BENEFICIARIOS_MOSTRAR") + " <td></tr>");
									}
								%>
                                    </tbody>
                                </table>
                            </div>
                            <!--INC 102 FX  CMC 22/10/2018 Inicio-->
                            <div id="verMasP" >
								<%
								String hayMas; 
								if(datos.get("bandera_paginacion") == null || datos.get("bandera_paginacion").equals(""))
								{
									hayMas = "0";								
								}else
									hayMas = (String)datos.get("bandera_paginacion"); //Variable que se utiliza para saber si hay mas paginas de beneficiarios	
								if(hayMas.equals("1")){
								%>
								<span  onclick="verMas()" style="100%" ><p style="cursor:pointer;display:inline-block;text-align:center;margin-left: 46%"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VER_MAS")%><img src="/images/version7/ver_mas.png"/> </p></span><!--INC 102 FX  CMC 1/11/2018--> 
								<% }
								%>
								<a onclick="verAtras()"> <img style="display:inline-block;float:right" src="/images/version7/ico_irArriba.png"/> </a>
							</div>
							<!--INC 102 FX  CMC 22/10/2018 Fin--> 
                        </div>
                    </div>
                    <br>
                </div>
				<div class="botoneraCreacion">
                <div class="col-md-6 col-xs-6">
                    <ul>
                    <!-- INI INC 99 FX CMC 24/09/2018 -->
                        <li><a href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia">&lsaquo;&lsaquo; <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VOLVER_INICIO") %></a>
                        <!-- FIN INC 99 FX CMC 24/09/2018 -->
						<a class="tooltipAyuda" href="#">
                        <img src="/images/version7/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class="tooltipFlechaIzquierda ocultar">
                        <p class="letragris">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NECESARIO_ORD_BEN") %>
                        </p>
                    </div></li>
                    </ul>
                </div>
                <div class="col-md-6 col-xs-6">
                    <!--<button class="grandeAzul" id="btnContinuar" name="btnContinuar" type="button" onclick="continuarBenef()"  >Continuar</button>-->
					<button class="buttonGiroAzul" id="btnContinuar" name="btnContinuar" type="submit"  onclick="bloquearBoton();"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button><!-- INI CASO 4 FX CMC 21/01/2019-->
                </div>
            </div>
        </form>
		<form id="modoCreacionManual3" method="POST" role="form" action="" class="form-horizontal">
		</form>
    </div>
    <!--end-container-->
    <div class="row">
        <div class="col-md-12">
            <!-- Modal unico-->
            <div class="modal" id="modalComun"></div>
            <!--end Modal unico -->
        </div>
    </div>
    <script src="/js/version7/vendorDivisas.js"></script>
    <script src="/js/version7/pluginsDivisas.js"></script>
    <script src="/js/version7/op_objDivisas.js"></script>
	<% if((idioma!=null)&&(idioma.equalsIgnoreCase("en"))){ %>
	<script src="/js/version7/Divisas_EN.js"></script>
	<% }else{ %>
	<script src="/js/version7/Divisas_ES.js"></script>
	<% } %>
	<script src="/js/version7/mainPagosDivisas.js"></script>
    <script src="/js/version7/mainCreacionNegDivisas.js"></script>
	<script> 
		PAGOS.setFormatDataObjeto($('form'));
	</script>
</body>
</html>