
<!DOCTYPE html>
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
<html lang="es-ES">
<%
   String pestana=(String)datos.get("pestana");    
   boolean showBlotterPage = new Boolean((String)datos.get("showBlotterPage"));
   String activePestana="";
   if(showBlotterPage){
	   activePestana="activa";
   }
%>
   <head>
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMPRA_VENTA_DIVISAS") %></title>
		<script language="Javascript">
		  //Variable para cambiar el idioma de los calendarios
		  	  var _idiomaApp;
			  <% 
			  if((idioma!=null)&&(idioma.equalsIgnoreCase("en"))){
			  %>
			  	_idiomaApp = "EN";
			  <% 
			  }
			  %>
		  </script>
	<!-- INI Alcance Divisas 02 CMC Archivos 30/01/2018 -->
	<script>
	var host = window.location.host;
	console.info("El host encontrado es: " + host);
	if (host.indexOf("bbvanetcash.com") != -1) {       
		netJS = 'https://www.bbvanetcash.com.co/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js';
	} else if (host.indexOf("150.250.251.65:843") != -1) {         
		netJS = 'https://150.250.251.65:843/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js';
	}
	//INI Global NetCash 08/01/2019
    else if (host.indexOf("ei-bbvaglobalnetcash.igrupobbva") != -1){
        netJS = 'https://ei-bbvaglobalnetcash.igrupobbva/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js'; //Calidad
    } else if (host.indexOf("bbvaglobalnetcash.com") != -1){
        netJS = 'https://www.bbvaglobalnetcash.com/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js'; //Produccion
    } //FIN Global NetCash 08/01/2019
	else {
		netJS = 'https://150.250.251.65/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js';
	}
	loadScriptNet(netJS, function() {
		// initialization code
	});
	function loadScriptNet(url, callback) {
		console.info("La url a cargar es: " + url);
		var script = document.createElement("script")
		script.type = "text/javascript";
		if (script.readyState) { // IE
			script.onreadystatechange = function() {
				if (script.readyState == "loaded"
						|| script.readyState == "complete") {
					script.onreadystatechange = null;
					callback();
				}
			};
		} else { // Others
			script.onload = function() {
				callback();
			};
		}
		script.src = url;
		document.getElementsByTagName("head")[0].appendChild(script);
	}
	</script>
	<!-- FIN Alcance Divisas 02 CMC Archivos 30/01/2018 -->
		<script src="/js/jquery.min.js"></script>
		<script src="/js/version7/neg_divisas.js"></script>
		<script src="https:/js/jquery.cookie.js"></script>
		<script type="text/javascript" language="JavaScript" src="/js/version7/utilPibee.js"></script>
		<%
	if ((idioma != null) && (idioma.equalsIgnoreCase("en"))) {
%>
<script language="Javascript" src="/js/version7/banner_en.js"></script>
<script language="Javascript" src="/js/version7/utilidades_en.js"> </script>
<script type="text/javascript" src="/js/version7/form_validate_en.js"></script>
<input type="hidden" name="s_idioma" value="en">
<%
	} else {
%>
<script language="Javascript" src="/js/version7/banner.js"></script>
<script language="Javascript" src="/js/version7/utilidades.js"> </script>
<script type="text/javascript" src="/js/version7/form_validate.js"></script>
<input type="hidden" name="s_idioma" value="es">
<%
	}
%>	 
		<script type="text/javascript" src="/js/version7/script_org.js"></script>
		<script language="JavaScript" src="/js/version7/tiempo.js"></script>
		<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>
<%-- 		// INI  - NETCAS V7-CMC-Julio Diaz-28/11/2019 --%>
		<link rel=stylesheet type='text/css' href="/estilos/version7/mainPagosGirosDesdeHacia.css" />
<%-- 		<link rel=stylesheet type='text/css' href="/estilos/version7/mainPagos.css" /> --%>
<%-- 		// FIN  - NETCAS V7-CMC-Julio Diaz-28/11/2019 --%>		
		<link rel=stylesheet type='text/css' href="/estilos/version7/lookDivisas.css">
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-ui.css" />
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-icon.css" />
		 <!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
		<!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
		<script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
		<!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
		<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
		<script>
		 //INI INC 6 FX 05-04-2018
		 $('document').ready(function () {
		 });
		 //FIN INC 6 FX 05-04-2018
		function checkBtn(cntReg, nAvance){
			var totalCheck = $("input[type='checkbox']").length;
			$("#indiceGiro").val(cntReg);
			cntReg2="#checkBtn" + cntReg;
			var nOperacion = $(cntReg2).val();
			var datosOpe = nOperacion.split("|@|");//INC 46 CMC FX 12-02-2018
			var montoM = datosOpe[0];
			var monedaM = datosOpe[1];
			var operanionM = datosOpe[2];
			var estadoM = datosOpe[3];
			var url1 = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=detalle&indiceGiro=";
			$("#btnVerDetallePagos").attr("data-url", url1);
			if (nAvance!=0){
				var urlCS = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=crearSimilar&selectOpe=T2&msjError="+nOperacion;
				var urlMDF = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=modificarOperaciones&tipoOperacion=T&selectOpe=T2&numOperacion=" + operanionM + "&refExtranjero=NA"+"&ppagina="+"";//INC 102 FX  CMC 6/11/2018
			}else{
				var urlCS = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=crearSimilar&selectOpe=T1&msjError="+nOperacion;
				var urlMDF = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=modificarOperaciones&tipoOperacion=T&selectOpe=T1&numOperacion=" + operanionM + "&refExtranjero=NA"+"&ppagina="+"";//INC 102 FX  CMC 6/11/2018
			}
			$("#btnCrearSimilar").attr("data-url", urlCS);
			$("#btnModificarDivisa").attr("data-url", urlMDF);
			for (var i = 0; i <= totalCheck; i++) {
					tmp= "#checkBtn" + i;
					if(i!=cntReg){
						$(tmp).prop('checked', false);
					}
				}
			var totalChecked = $("input[type='checkbox']:checked").length;
			if(totalChecked==1){
				$("#btnVerDetallePagos").show();
				//$("#btnCrearSimilar").show();
			 /*INI INC 61 - ESTADO - FX - 05-06-2015*/
			/* ESTADO ----- 1 , 7 =  En proceso Cliente
			   ESTADO ----- 5 , 9 =  Devuelta	*/
			 if (estadoM == 1 || estadoM == 7 || estadoM == 5 || estadoM == 9){
			     $("#negoLinea").hide();
			     $("#negoMesa").hide();
					$("#btnModificarDivisa").show();
				}else{
			     $("#negoLinea").show();
			     $("#negoMesa").show();
					$("#btnModificarDivisa").hide();
				}
			/* ESTADO ----- 4 , 8 =  Rechazada
			   ESTADO ----- 10    =  Enviada al Extrajero
			   ESTADO ----- 6     =  Enviada al Exterior  */			   
			 if (estadoM == 4 || estadoM == 6 || estadoM == 8 || estadoM == 10 || estadoM == 3){ //INC 135 CMC 17-01-2019 ESTADO 3
			     $("#negoLinea").hide();
			     $("#negoMesa").hide();
			     $("#btnModificarDivisa").hide();
			 }
			}else{
			 $("#negoLinea").show();
			 $("#negoMesa").show();//FIN INC 45 FX 02/05/2018
				$("#btnVerDetallePagos").hide();
				$("#btnCrearSimilar").hide();
				$("#btnModificarDivisa").hide();
			}
			/*FIN INC 61 - ESTADO - FX - 05-06-2015*/			
			ajustar();
		}
//INI GP 12886 MOISES LUNA FX 13-03-2018
		 function realizarCotizacion(a,b,c){
				if(valida_cotiz(b)){
				var tiempoIN = parseInt($("#tiempoIN").val());
				$("#tiempo").val(tiempoIN);
				$("#pestana").val("2");
		    	url = "OperacionCBTFServlet";
				var query = {
					proceso:'comercio_exterior_bbva_pr',
					operacion:'consulta_operaciones_negociacion_op',
					accion:'cotizador',
					monto:a,
					selectMoneda:b,
					selectOpe:c
				};
				var respuesta = "";
				var peticion = {
					type : 'POST',
					dataType : 'html',
					url : 'OperacionCBTFServlet',
					data : query,
					success : function(result, resultStatus) {
                        $("#datosCotizador2").html(result);     
						$("#datosCotizador2").css("display","block");
						document.getElementById('lb_piloto').innerHTML=document.getElementById('pilotoAux').value;
						document.getElementById('tiempoIN').value=document.getElementById('tiempoAux').value;
						document.getElementById('tiempo').value=document.getElementById('tiempoAux').value;
						tiempoTotal = parseInt(document.getElementById('tiempoAux').value);
						currentsecond = tiempoTotal;
						document.getElementById('lb_time').value=tiempoTotal;
					//INI INC 214 FX - CMC 18/06/2019
						var ErrorMonto = document.getElementById('descErrorMonto').value;
						if(null != ErrorMonto && ErrorMonto != ""){
							document.getElementById('messageBoxULCM7').innerHTML=ErrorMonto;
							$("#messageBoxCM7").show();
						}else {
							$("#messageBoxCM7").hide();
						}
					//FIN INC 214 FX - CMC 18/06/2019 
					}
				};
				$.ajax(peticion);
				}
				ajustar();
			}
//FIN GP 12886 MOISES LUNA FX 13-03-2018
			function verMas(){
				window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=mostrarMas&selectOpe=T";
				ajustar();
			}
			function verAtras(){
				window.location.href="#top";
			}
			function valida_cotiz(b){
				montoX="#monto";
				var monto = $(montoX).val();
				if (b=="128"){
					alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBE_SEL_TIPO_DIV") %>");
					return false;
				}else if(monto<1){
					alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO_MAYOR_CERO") %>"); //Leonardo Sanchez CMC - 12/03/2020
					return false;
				}else{
					return true;
				}
				ajustar();
			}
			function filtrarOperaciones(){
	// INI  - NETCAS V7-CMC-Julio Diaz-28/11/2019				
			//desde = _$$R('filtroFechaDesde').getValue();
			var desde =document.getElementById("filtroFechaDesde").value
			//hasta = _$$R('filtroFechaHasta').getValue();
			var hasta =document.getElementById("filtroFechaHasta").value
   // FIN  - NETCASH V7-CMC-Julio Diaz-28/11/2019		
			var Nit = $('#selectNit').val();//FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 26-06-2019 
			// INI NITS 1.2 CMC FX 20/08/2019
			var cadenaNIT = Nit.split("@|");
			Nit=cadenaNIT[0];
			var IndicadorNIT=cadenaNIT[1];
			// FIN NITS 1.2 CMC FX 20/08/2019
		    filtro="S";
				if(desde==""){
					alert ("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CAMPO_FECHA_INICIAL_REQ") %>");
				}else if(hasta==""){
					alert ("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CAMPO_FECHA_FINAL") %>");
				}else{
					//alert("entramos " + desde + " " + hasta);
					window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia&filtro=" + filtro + "&fechaDesde=" + desde +"&fechaHasta=" + hasta+"&selectNit="+Nit+"&IndicadorNIT="+IndicadorNIT; //FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 26-06-2019 - INI NITS 1.2 CMC FX 20/08/2019
				}
				ajustar();
			}
			function ajustar(){
				com.bbva.kyop.controller.CoreController.resizeMainContent(300);
			}
			function numberFormat(numero){
				// Variable que contendra el resultado final
				var resultado = "";
				// Si el numero empieza por el valor "-" (numero negativo)
				if(numero[0]=="-")
				{
					// Cogemos el numero eliminando los posibles puntos que tenga, y sin
					// el signo negativo
					nuevoNumero=numero.replace(/\,/g,'').substring(1);
				}else{
					// Cogemos el numero eliminando los posibles puntos que tenga
					nuevoNumero=numero.replace(/\,/g,'');
				}
				// Si tiene decimales, se los quitamos al numero
				if(numero.indexOf(".")>=0)
					nuevoNumero=nuevoNumero.substring(0,nuevoNumero.indexOf("."));
				// Ponemos un punto cada 3 caracteres
				for (var j, i = nuevoNumero.length - 1, j = 0; i >= 0; i--, j++)
					resultado = nuevoNumero.charAt(i) + ((j > 0) && (j % 3 == 0)? ",": "") + resultado;
				// Si tiene decimales, se lo añadimos al numero una vez forateado con 
				// los separadores de miles
				if(numero.indexOf(".")>=0){
					resultado+=numero.substring(numero.indexOf("."));
					}
				$("#monto").val(resultado);
				if(numero[0]=="-")
				{
					// Devolvemos el valor añadiendo al inicio el signo negativo
					return "-"+resultado;
				}else{
					return resultado;
				}
			}
						//INI INC 6 FX 07/05/2018
		     function select() {
				 $('#selectMoneda > option').each(function () {
				     switch($(this).text()) {
					 case 'USD':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_USD") %>');
					     break;
					 case 'EUR':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_EURO") %>');
					     break;
					 case 'GBP':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_LIBRA_ESTERLINA") %>');
					     break;
					 case 'CAD':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_DOLAR_CAD") %>');
					     break;
					 case 'CHF':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_FRANCO_SUIZO") %>');
					     break;
					 case 'SEK':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_CORONA_SUECA") %>');
					     break;
					 case 'JPY':
					     $(this).text('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIV_YEN_JAP") %>');
					     break;
					 case 'CNY':
					     $(this).text('CNY - Yuan Renminbi');
					     break;
				     }
				 });
		     }
		     $(document).ready(function(){
		     	select();
		     });
		     
		     //FIN INC 6 FX 07/05/2018
		</script>
	  <style type="text/css">
.sortable .head {
	background: url("/images/version7/sort.gif") no-repeat 6px #f8f8f5; padding-left: 18px; cursor: pointer; height: 32px; text-transform: uppercase; text-align: left; 
}
.sortable .desc {
	background: url("/images/version7/desc.gif") no-repeat 6px #f8f8f5; padding-left: 18px; cursor: pointer; height: 32px; text-transform: uppercase; text-align: left; 
}
.sortable .asc {
	background: url("/images/version7/asc.gif") no-repeat 6px #f8f8f5; padding-left: 18px; cursor: pointer; height: 32px; text-transform: uppercase; text-align: left; 
}
.ui-arrow-up2:before {
	content: "";
	border-style: solid;
	border-color: rgba(255, 255, 255, 0) rgba(255, 255, 255, 0) #B5E5F9 rgba(255, 255, 255, 0);
	border-width: 12px;
	display: inline-block;
	position: absolute;
	top: -25px;
	left: 208px;
}
.ui-arrow-up2:after {
	content: "";
	border-style: solid;
	border-color: rgba(255, 255, 255, 0) rgba(255, 255, 255, 0) #FFF rgba(255, 255, 255, 0);
	border-width: 10px;
	display: inline-block;
	position: absolute;
	top: -20px;
	left: 210px;
}
@media screen and (max-width: 1241px) and (min-width: 520px) {
   .divtamdiv{
       max-width: 950px;
	   min-width: 950px;
   }
}
@media screen and (max-width: 1400px) and (min-width: 1242px) {
   .divtamdiv{
       max-width: 1266px;
	   min-width: 1070px;
   }
}
@media screen and (min-width: 1401px) {
   .divtamdiv{
       max-width: 1266px;
	   min-width: 1070px;
   }
}
</style>
   </head>
<body >
<!-- Literales tabla -->
<input id="CLIENT_PROCESS" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_GIRO_CLIENT_PROCESS")%>">
<input id="RETURN" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_GIRO_RETURN")%>">
<input id="BANK_PROCESS" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_GIRO_BANK_PROCESS")%>">
<input id="REJECTED" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_GIRO_REJECTED")%>">
<input id="SENT_ABROAD" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_GIRO_SENT_ABROAD")%>">
<input id="ABANDONED" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_PSE_ABANDONED")%>">
<input id="FAILED" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_PSE_FAILED")%>">
<input id="COMPLETED" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_PSE_COMPLETED")%>">
<input id="PSE_SI" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_PSE_SI")%>">
<input id="PENDING" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_PSE_PENDING")%>">
<input id="NO_PROCESS" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_STATUS_NO_PROCESS")%>">
<input id="MENSAJE_PSE_CLIENT_PROCESS" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_MENSAJE_PSE_CLIENT_PROCESS")%>">
<input id="MENSAJE_PSE_REJECTED" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_MENSAJE_PSE_REJECTED")%>">
<input id="MENSAJE_PSE_ABROAD" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_MENSAJE_PSE_ABROAD")%>">
<input id="MENSAJE_PSE_RETURN" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_MENSAJE_PSE_RETURN")%>">
<input id="MENSAJE_PSE_BANK_PROCESS" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"BLOTTER_MENSAJE_PSE_BANK_PROCESS")%>">
<input id="TITULO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DIV_DETALLE")%>">
<input id="SUBTITULO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DIV_DET_INF")%>">
<input id="ESTADO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO")%>">
<input id="DESCRIPC_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DESCRIPCION_OP")%>">
<input id="TIPO_NEGO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TIPO_NEG")%>">
<input id="FECHA_OPE_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECHA_OPE")%>">
<input id="REFERE_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_NUM_OPE")%>">
<input id="FECHA_VAL_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECHA_VAL")%>">
<input id="HORA_OPE_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_HORA_OPE")%>">
<input id="ORDENANTE_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ORDENANTE")%>">
<input id="BENEFI_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BENEFICIARIO")%>">
<input id="BANCO_BENEFI_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BAN_BENEF")%>">
<input id="MONTO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MONTO")%>">
<input id="TASA_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TASA")%>">
<input id="EQUIV_PES_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TASA_EQU")%>">
<input id="NUMERO_AVANCE_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_NUM_AVANCE")%>">
<input id="CONCEPTO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_CONCEPTO")%>">
<input id="NIT_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_NIT")%>">
<input id="NOMBR_NIT_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_NIT_NOM_CLIENT")%>">
<input id="COMISIO_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_COMISION")%>">
<input id="IVA_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_IVA")%>">
<input id="TOTAL_DEBITAR_MODAL" type="hidden" value="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_TOTAL_DEB")%>">

	<!--<div id="cab-principal" class="IdCap-principal">
		<h1><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_COMPRA_VENTA_DIVISAS")%></h1>
		<h4><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_GIROS_HACIA")%></h4>
	</div>-->
	<div class="row">
            <div class="col-md-4">
                <h1 style="padding-left: 3px;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_COMPRA_VENTA_DIVISAS")%></h1>
                <p class="subtituloPrincipal" style="padding-left: 5px !important;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_GIROS_HACIA")%></p>
            </div>
			<!-- CMC - FX Incidencia 135 - 17/01/2019 - Inicio -->
			<!-- CMC - FX Incidencia 135 - 02/01/2019 - Inicio -->
			<%
				 Vector listaMonedas = (Vector) datos.get("listaDivisas");
				 Hashtable parametria = (Hashtable) datos.get("parametria");
				 Hashtable moneda;
				 String selectMoneda = (String)datos.get("selectMoneda");
				 /*int codMoneda;
				 if(!selectMoneda.equals("")&& selectMoneda!=null){
				 codMoneda = Integer.parseInt(selectMoneda);
				 }else{
					codMoneda =128;
				 }*/
				 String timerInicio = (String) parametria.get("timer");
				  int timerIN = Integer.parseInt(timerInicio) + 1;
						String indHorario = (String) datos.get("indHorario");
						String indHorarioModif = (String) datos.get("indHorarioModif");
						//INI NITS 1.2 CMC FX 20/08/2019
						String indNITPrinHacia = (String) datos.get("indNITPrinHacia");
						String NitNombre = (String) datos.get("NitNombre");
						//FIN NITS 1.2 CMC FX 20/08/2019
						if (indHorario.equals("Si")){
					%>
			<div class="col-md-8 divNuevoPago">
	<%-- INI IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
				<button type="button" class="grandeAzul btnRedireccion" id="negoLinea" onclick="desabilitarBtn(this)" data-url="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarLinea&selectOpe=T1"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NEG_LINEA") %></button>
				<button type="button" class="grandeAzul btnRedireccion" id="negoMesa"  onclick="desabilitarBtn(this)" data-url="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarMesaDinero&selectOpe=T2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMPLETAR_OP") %></button>
	<%-- FIN IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
			</div>
			<%} else if (indHorarioModif.equals("Si")){
					%>
			<div class="col-md-8 divNuevoPago">
				<button type="button" class="grandeAzul btnRedireccion" id="negoMesa" data-url="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarMesaDinero&selectOpe=T2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMPLETAR_OP") %></button>
			</div>
			<%}%>
			<!-- CMC - FX Incidencia 135 - 17/01/2019 - Fin -->
			<!-- CMC - FX Incidencia 135 - 02/01/2019 - Fin -->
        </div>
	<input type="hidden" id="pestana" name="pestana" value="<%=pestana%>" />
	<input type="hidden" id="indiceGiro" name="indiceGiro" value="0"/>
	<input type="hidden" id="pagina" name="pagina" value="<%=(String)datos.get("pagina")%>" />
	<input type="hidden" id="tiempoIN" name="tiempoIN" value="<%=timerIN%>"/>
	<input type="hidden" id="tiempo" name="tiempo" value="<%=timerIN%>"/>
	<form method="post" method="POST" enctype="multipart/form-data" name="formDivisas">
		<input type="hidden" id="selectOpe" name="selectOpe" value="T" />
		<div id="container" class="container" style="padding-left: 5px !important;">
			<ul class="nav nav-tabs">
				<li id="li1" class="active"><a onclick="ajustar(); location.reload();" href="#tab1Hist"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_CONS_HIST_OPE")%></a></li><!-- FX CMC Ajuste Entrega 2018-01-19 --><!-- FX CMC INC 178 04-02-2019 -->
				<li id="li2"><a onclick="ajustar()" href="#tab2Cotiz"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_COTI_MONE_LINEA")%></a></li>
				<li id="li3" ><a onclick="getBlotter();" href="#tabPSE"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TITULO_PSE")%></a></li><%-- GP17667 --%>
			</ul>
			<div class="tab-content">
				<div id="tab1Hist" class="tab-pane divtamdiv">
				<div class="descriptinPanel1">
					<p class="textPse"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_OPERACIONES_REALI_NIT") %></p>
				</div>
                <!-- FIN NITS 1.2 CMC FX 20/08/2019 -->
				<%
						if (indHorario.equals("No")){
					%>
				<div class="alerta bordeWarning clearfix" id="messageBoxCM6" style="display: block;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj6" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
						<ul id="messageBoxULCM6" style="font-size: 11.5px;"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_HORARIO_1") %> <%=parametria.get("horaNegoIni") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <%=parametria.get("horaNegoFin") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_HORARIO_2") %> <%=parametria.get("horaInicio") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <%=parametria.get("horaFin") %>. <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_HORARIO_3") %></ul><!-- Incidencia 119 FX CMC 14/11/2018 --><!-- INC 158 FX CMC 22/01/2019 -->
                    </div>
				</div>
				<%}%>
				<%
				String indiList = datos.get("indiList") != null ? (String) datos.get("indiList") : "";
				if(!indiList.equalsIgnoreCase("NN")){%>
				<div class="alerta clearfix" id="messageBoxNITP" style="background-color: #fceaea !important; border: 1px solid #d8b6c7 !important;">
					<div class="interior1">
						<img src="/images/version7/iconoAlertaInfo.png"> 
					</div>
					<div id="cerrarmsj1" class="interior3">
						<img src="/images/version7/cerrarAlertaDiv.png"> 
					</div>
					<div class="interior2">
						<ul id="messageBoxValdBaep"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VALIDACION_BAEP")%></ul>
					</div>
				</div>
				<%} %>
				<%if(null!=indNITPrinHacia && indNITPrinHacia.equals("N")){%><p> <B><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT") %>: </B> <%=NitNombre%> </p><%}%><!-- INI NITS 1.2 CMC FX 20/08/2019 -->
					<table class="simple3 sortable" id="sorter">
						<thead>
							<tr>
								<td id="cab" class="title-tr" colspan="9" align="left"><h2><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_OPE_REAL")%></h2></td><!-- FX CMC Ajuste Entrega 2018-01-19 -->
							</tr>
							<tr>
								<td id="cab"></td>
								<td id="cab" colspan="4">
                                    <button type="button" id="btnVerDetallePagos" style="width: 34%; text-align: left !important;" data-target="#modalComun" data-toggle="modal" class="btnModal buttonIcon jqBotonConTooltip jqBotonMicro ocultar"><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
										<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VER_DET")%> <img src="/images/version7/verDetalle.png">
									</button>
									<%
										if (indHorarioModif.equals("Si")){
									%>
									<button type="button" id="btnCrearSimilar" style="text-align:left !important;width:27%"  class="buttonIcon jqBotonConTooltip jqBotonMicro ocultar btnRedireccion"><!--INC FX BOTON 26-07-2018 -->
										<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CREAR_SIMILAR") %> 
									</button>
									<button type="button" id="btnModificarDivisa" style="width: 34%; text-align: left !important;"  class="buttonIcon jqBotonConTooltip jqBotonMicro ocultar btnRedireccion"><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
										<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MODIFICAR") %> <img src="/images/version7/icono_modificar.png">
									</button>
									<%}%>
								</td>
								<td id="cab"></td>
								<td id="cab" align="right" align="right"> <%if(null!=indNITPrinHacia && indNITPrinHacia.equals("N")){%><a href="<%=urls.get("quitarFiltro")%>" class="linkverMas"> <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_REM_FILTRO")%></a><%} %></td><!-- INI NITS 1.2 CMC FX 20/08/2019 -->
								<td id="cabfil" class="btnTab" colspan="3" align="right">
									<input type="text" id="filtro" placeholder="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BUSCAR")%>" class="inpSearchConFondo" style="background: url(/images/version7/icoBuscar.png) no-repeat scroll 97% 57%/16px; height: 22px;" !important> 										
									</input>
							<%-- INI  - NETCAS V7-CMC-Julio Diaz-28/11/2019--%>
                                                         <button type="button" id="filtrarbtn" class="botonIcono" onclick="ajustar(),fechaHasta()" >
                                                        <%-- FIN  - NETCASH V7-CMC-Julio Diaz-28/11/2019--%>        
                                        <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILTRAR")%><img src="/images/version7/filtros.png"/>
                                    </button>
				                                   <!-- INI - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 12-07-2019 -->
									<div id="capaFiltrarPagos" class="ui-general ui-panel ui-toltip-panel ui-arrow-up2 ui-floating-panel" style="width: 260px;padding: 10px;float:right;margin:15px 0px 0px 100px">
										<div >
											<a id="filtrarPagosCerrar" href="#">
												<img src="/images/version7/cerrar.png" />
											</a>
										</div>
										<div id="contentFiltrarPagos" align="left" style="height: 180px;">
											<h2><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILT_OPE")%></h2>
											<label for="filtroPagosFechaEnvio"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECH_ENV")%></label>
											<br><br>
											<div class="form-group col-md-14 " >
												<div class="form-group col-md-12" >
													<label ><strong><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DESDE")%> </strong></label>
													<div >
										<%-- INI  - NETCAS V7-CMC-Julio Diaz-28/11/2019--%>			
<!-- 											<span class="ui-calendar ui-general ui-box" wv="filtroFechaDesde" name="filtroFechaDesde" id="filtroFechaDesde" > </span> -->
													<input class="ui-calendar ui-general ui-box"   wv="filtroFechaDesde" name="filtroFechaDesde" id="filtroFechaDesde" style="width : 160px;position: relative;-webkit-border-radius: 4px 4px 4px 4px;" /> 							
							                          <script>
// 															_$$IN({
// 															"fn" : UI.calendar,
// 															"arg" : "filtroFechaDesde"
// 																});
                                                               $(function () {
                                                        	   $("#filtroFechaDesde").datepicker({minDate:'-90d' ,
                                                        		   maxDate:new Date() ,
                                                        		   dateFormat: 'dd-mm-yy',
                                                        		   "arg" : "filtroFechaDesde",
                                                        		    showOn: "button",
                                                        		    buttonText: "<i class='ui-input-container ui-icon ui-icon-calendar'></i>"
                                                                   });
                                                               });
														</script>
										<%-- FIN  - NETCASH V7-CMC-Julio Diaz-28/11/2019--%>			
													</div>
												</div><br><br>
												<div class="form-group col-md-12">
													<label ><strong><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_HASTA")%> </strong></label>
													<div >
									<%-- INI  - NETCAS V7-CMC-Julio Diaz-28/11/2019--%>				
<!-- 											<span class="ui-calendar ui-general ui-box" wv="filtroFechaHasta" name="filtroFechaHasta" id="filtroFechaHasta"  > </span>  -->
													<input class="ui-calendar ui-general ui-box"   wv="filtroFechaHasta" name="filtroFechaHasta" id="filtroFechaHasta" style="width : 160px;position: relative;-webkit-border-radius: 4px 4px 4px 4px;" disabled /> 							
							                          <script>
// 															_$$IN({
// 															"fn" : UI.calendar,
// 															"arg" : "filtroFechaHasta"
// 																});
                                                             function fechaHasta(){
                                                            	var d = new Date(); // today!
                                                				var day = d.getDate()  ;
                                                				var month = d.getMonth() + 1;
                                                				var year = d.getFullYear();
                                                			  	var todayDay = (day <= 9 ? '0' + day : day)+ '-' + (month<=9 ? '0' + month : month) + '-' + year ;
                                                			   	$("#filtroFechaHasta").val(todayDay);
                                                            }
                                                            $(function () {
                                                                $("#filtroFechaHasta").datepicker({minDate: new Date(),
                                                                	maxDate:new Date(),
                                                                	dateFormat: 'dd-mm-yy',
                                                                	showOn: "button",
                                                        		    buttonText: "<i class='ui-input-container ui-icon ui-icon-calendar'></i>"
                                                                	});
                                                                 });
														</script>
								<%-- FIN  - NETCASH V7-CMC-Julio Diaz-28/11/2019--%>					
													</div>
												</div>
												<div class="form-group col-md-12" style="margin-top:10px;margin-bottom: 10px;">
													<label ><strong><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_NIT") %>: </strong></label>
													<select id="selectNit" style="float:left;width: 200px;margin-top:6px; font-size: 11px;" required="true" name="selectNit"  > 
														<%
														Vector listaNit = (Vector) datos.get("IcListaNit");
														Enumeration enumlistaNit= listaNit.elements();
														int cant=listaNit.size();
														if(cant!=1){ 
														%>
															<option value=""> </option>
														<% }
														if(cant>0){
															int i=0;
															String Empresa="|";
															while (enumlistaNit.hasMoreElements()) {
															    i++;
																Hashtable klistC = (Hashtable) enumlistaNit.nextElement();
																String Nit = (String) klistC.get("NumeroDocumento");
																String tipoDocumento = (String) klistC.get("TipoDocumento");
																String DigitoVerificacion = (String) klistC.get("DigitoVerificacion");
																Empresa = (String) klistC.get("NombreCliente") +  Empresa;
																String ClienteCons2 = klistC.get("TipoDocumento")+Nit+DigitoVerificacion;
																Nit = Nit.replaceFirst ("^0*", "");
																String NitVisual =Nit+"-"+DigitoVerificacion;
																String ClienteCons = (String) klistC.get("ClienteAltamira");
																if(!ClienteCons.trim().equalsIgnoreCase("")){
																	//INI NITS 1.2 CMC FX 20/08/2019
																	if(i==1){%>
																		<option value='<%=tipoDocumento+""+NitVisual+"@"+ClienteCons+"@|"+i%>' ><%=NitVisual%> (<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT_PRINCIPAL") %>)</option>
																	<%} else{ %>	
																		<option value='<%=tipoDocumento+""+NitVisual+"@"+ClienteCons+"@|"+i%>' ><%=NitVisual%></option>
															    	<% }
																	//FIN NITS 1.2 CMC FX 20/08/2019
																}
												            }
														}
														%>
													</select>
												</div>
											</div>
											<div class="col-md-12" align="center" >
												<button type="button" id="filtrarbtnX" class="botonIcono" onclick="filtrarOperaciones()">
													<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILTRAR")%><img src="/images/version7/filtros.png"/>
												</button>
											</div>
										</div>
										<!-- FIN - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 12-07-2019 -->
									</div>
								</td>
							</tr>
					</thead>
						<tbody id="mas" >
							<tr id="tr-cab" class="tr-cab-class">
								<!--<td style="width: 9%" id="cab1" class='tdchekc td-check'><input type="checkbox" disabled="disabled" style="display:none"/> </td>-->
								<th style="width: 4%"  id="cab1" class='nosort'><!--<input id="chkSelectAll" type="checkbox"  />--></th>
								<th style="width: 10%" id="cab2" class="td-tit-fechpro"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECHA")%></th>
								<th style="width: 11%" id="cab3"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_OPERACION")%></th>
								<th style="width: 10%" id="cab3"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_AVANCE")%></th>
								<th style="width: 10%" id="cab4"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DIVISA")%></th>
								<th style="width: 12%" id="cab5"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MONTO")%></th>
								<th style="width: 13%" id="cab6"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TASA")%></th>
								<th style="width: 14%" id="cab7"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO")%></th>
								<th style="width: 16%" id="cab8"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BENEFICIARIO")%></th>
							</tr>
								<%
									Vector listaGiros = (Vector) datos.get("listaGiros");
									Enumeration enumLista = listaGiros.elements();
									int cntReg = 0;
									int nAvance= 1;
									String nAvanceData = "";
									String operacion = "";
									String avanceOpe = "";
									String nombreEstado = "";/*INC 61 - ESTADO - FX - 05-06-2015*/
									if(listaGiros.size()>0){
										while (enumLista.hasMoreElements()) {
											Hashtable klist = (Hashtable) enumLista.nextElement();
											/*INI INC 61 - ESTADO - FX - 05-06-2015*/
											int estado = Integer.parseInt((String)klist.get("estado"));
											switch (estado)
											{
												case 1:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PROCLIE");
													break;
												case 2:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PENDFIR");
													break;
												case 3:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PROCBAN");
													break;
												case 4:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_RECHAZA");
													break;
												case 5:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_DEVUELT");
													break;
												case 6:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_ENVIEXT");
													break;
												case 7:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PROCLIE");
													break;
												case 8:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_RECHAZA");
													break;
												case 9:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_DEVUELT");
													break;
												case 10:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_ENVIEXT");
													break;
												case 11:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PENDFIR");
													break;
												default:
													nombreEstado = CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PROCBAN");
													break;
											}
											/*FIN INC 61 - ESTADO - FX - 05-06-2015*/										
											operacion = (String) klist.get("monto") + "|@|" + (String) klist.get("moneda") + "|@|" + (String) klist.get("nOperacion") + "|@|" + (String) klist.get("estado");//INC 46 CMC FX 12-02-2018
											if(klist.get("nAvance")!=null){
												nAvanceData = (String) klist.get("nAvance");
												avanceOpe = nAvanceData;
												//FX - INI INC 88 22-08-2017
												String reultAvance=avanceOpe;
												boolean numericoAvance=true;
												try{
													Integer.parseInt(avanceOpe);
												}catch(Exception exN){
													numericoAvance= false;
												}
												if (numericoAvance){
													reultAvance= reultAvance.trim();
													nAvance = Integer.parseInt(avanceOpe); //Varios NITS MOD CMC 12-07-2019
													reultAvance=""+nAvance;//INC 209.B FX 25-07-2019 Se elimina subString
												}
												avanceOpe=reultAvance;
												//FX - FIN INC 88 22-08-2017
												if(nAvanceData.equals("STD1")){
													nAvance = 0;
													nAvanceData = "0";
													avanceOpe = "N.A.";
												}
											}
											//INI INC 132 21-12-2018
											operacion = operacion.replaceAll("\"", "");
											operacion = operacion.replaceAll("\'", "");																			
											//FIN INC 132 21-12-2018	
											out.println("<tr ><td class='tdchekc' style='width: 4%' ><input  value='"+ operacion +"' id='checkBtn" +  cntReg + "' onClick='checkBtn("+ cntReg + "," + nAvance + ")' name='fieldsCheck' type='checkbox' ></input></td>"//Incidencia 119 FX CMC 13/11/2018
											+ "<td style='width: 10%'>"
											+ klist.get("fechaOperacion")
											+ "</td><td style='width: 11%'> "
											+ klist.get("nOperacion")
											+ "</td><td style='width: 10%'> "
											+ avanceOpe
											+ "</td><td style='width: 10%'> "
											+ klist.get("moneda")
											+ "</td><td style='width: 12%'> "
											+ klist.get("monto")
											+ "</td><td style='width: 13%'> "
											+ klist.get("tasaPeso")
										+ "</td><td style='width: 14%' id='"+klist.get("estado")+"'> "/*INI INC 61 - ESTADO - FX - 05-06-2015*/
											+ nombreEstado /*FIN INC 61 - ESTADO - FX - 05-06-2015*/
											+ "</td><td style='width: 16%'> "
											+ klist.get("beneficiario")
											+ "</td></tr> ");
											cntReg++;
										}
									}else{
										out.println("<tr><td colspan='8' style='text-align:center;'> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_OP_MOSTRAR") + " <td></tr>");
									}%>
							</tbody>
					</table>
					<script type="text/javascript">
						var sorter=new table.sorter("sorter");
						sorter.init("sorter",1);
					</script>
					<div id="verMasP" >
					<%
						String hayMas = (String)datos.get("hayMas");
						if(hayMas.equals("S")){
		%>
							<span  onclick="verMas()" style="100%" ><p style="display:inline-block;text-align:center;margin-left:calc(100%/2-34px)"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VER_MAS")%><img src="/images/version7/ver_mas.png"/> </p></span>
						<% }
						%>
							<a onclick="verAtras()"> <img style="display:inline-block;float:right" src="/images/version7/ico_irArriba.png"/> </a>
					</div>
					</div>
				<div id="tab2Cotiz" class="tab-pane divtamdiv">
				  <!--<INPUT TYPE="hidden" name="monto" value="">-->
				  <form method="post" id="modoCreacionManual2" method="POST" role="form"  >
					<script type="text/javascript">
                              //var tiempoTotal = parseInt(document.getElementById('tiempo').value);
							  var tiempoTotal = parseInt($("#tiempoIN").val()) - 1;
							  //alert($("#tiempo").val());
                              var countdownfrom=tiempoTotal;
                              function countredirect(){
							  var currentsecond=$("#tiempo").val();
							  if($("#pestana").val()=="2"){
							  //alert(currentsecond);
                              if (currentsecond!=1){
                              	//alert(currentsecond);
								currentsecond-=1;
                              	document.getElementById('lb_time').innerHTML = currentsecond;
                              	setTimeout("countredirect()",1000);
								//countredirect();
                              }
                              else{
                              	currentsecond=tiempoTotal;
								document.getElementById('lb_time').innerHTML = currentsecond;
								realizarCotizacion($("#monto").val(), $("#selectMoneda").val(), $("#selectOpe").val());
								setTimeout("countredirect()",1000);
								//countredirect();
                              	//document.cotizador.submit();
                              }
							  $("#tiempo").val(currentsecond);
							  }
                              }
                              //countredirect();
                  </script>
				<%//INI GP17663 MONTO 25-05-2021 CMC%>
                  <div class="alerta bordeInfo clearfix" id="messageBoxCMMaxPermitidoHacia">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png">
                    </div>
                    <div id="cerrarmsjMaxPermitidoH" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png">
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULMaxPermitidoD" style="font-size: 11.5px;">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_RECUERDE_MAX_PERMITIDO_1") %>
                                <%=datos.get("montoMaximoCotizar")%>
                                <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_RECUERDE_MAX_PERMITIDO_2") %>
                        </ul>
                    </div>
                </div>
                <%//FIN GP17663 MONTO 25-05-2021 CMC%>
					<!--<div class="alerta bordeInfo clearfix" id="messageBoxCM2" style="border: 1px solid #9FD9E8;background-color:#C5FAFF;">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj2" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM2" style="font-size: 11.5px;"> A trav&eacute;s de esta opci&oacute;n usted podr&aacute; realizar una cotizaci&oacute;n en L&iacute;nea. Por favor digite la informaci&oacute;n de su transacci&oacute;n.</ul>
                    </div>
                  </div>
				  <div class="alerta bordeInfo clearfix" id="messageBoxCM3">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj3" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM3" style="font-size: 11.5px;"> El Horario para negociaci&oacute;n en L&iacute;nea es de <%=parametria.get("horaInicio") %> hasta <%=parametria.get("horaFin") %>.</ul>
                    </div>
                  </div>
				  <div class="alerta bordeInfo clearfix" id="messageBoxCM4">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj4" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM4" style="font-size: 11.5px;"> Horario para completar la operaci&oacute;n (adjuntar y enviar documentaci&oacute;n) <%=parametria.get("horaNegoIni") %> - <%=parametria.get("horaNegoFin") %>.</ul>
                    </div>
                  </div>-->
				  <div class="alerta bordeInfo clearfix" id="messageBoxCM5">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
					<div id="cerrarmsj5" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM5" style="font-size: 11.5px;"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_RECUERDE_CAMBIO") %></ul>
                    </div>
                  </div>
                <%//INI INC 214 FX - CMC 18/06/2019 %>
				<div class="alerta bordeWarning clearfix" id="messageBoxCM7">
                   	<div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj7" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM7" style="font-size: 11.5px;"></ul>
                    </div>
               	</div>
               	<%//FIN INC 214 FX - CMC 18/06/2019 %>
				 <br><br>
				 <div  class="row" >
				  <div class="col-md-4" align="center">
						<!-- INI GP 12886 MOISES LUNA FX 13-03-2018 -->
					<label align="left" style="font-size: 15px; font-weight: bold;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TIEMPO")%></label>
					<label id="lb_piloto" align="left" style="font-size: 15px; font-weight: bold;"></label>
					<br><br>
						<!-- FIN GP 12886 MOISES LUNA FX 13-03-2018 -->
					<img id="timer" src="/images/version7/timer_icon.png" width=70px height= 70px/><br><br>
						<label id="lb_time" style="font-size: 15px; font-weight: normal;" align="left" > </label> <label style="font-size: 13px; font-weight: normal;" align="left" > <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_SEGUNDOS")%> </label>
				  </div>
                  <!-- INI INC 133 IE FX 17-01-2019 -->
				  <div class="col-md-8" id="datosCotizador" >
				  <!-- FIN INC 133 IE FX 17-01-2019 -->
					<div >
						<label align="left" style="margin-top: 10px; font-size: 13px; font-weight: bold;" for="selectMoneda" class="col-md-4">* <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MONEDA")%></label>
						<select id="selectMoneda" align="left" name="selectMoneda" style ="width: 180; margin-top: 6px; font-size: 13px;" >
							<option value="128"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_SEL_MON")%></option>
							<%  
							    for(int i=0;i<listaMonedas.size();i++){
                                moneda =(Hashtable) listaMonedas.get(i);
								String money=(String) moneda.get("descMoneda");
								if(!money.trim().equals("") && money.length()>=3 ){ //SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020
								money = money.substring(0,3);
							%>
							<option value="<%=money%>"  <%if(selectMoneda.equals((String) moneda.get("descMoneda"))){%> selected <%}%>><%=CatalogoMultidioma.obtenerLiteral("PNET_MONEDAS_DIV",idioma,money)%></option><%--SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020 --%>
							<%}
							 } //SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020 
                              %>
						</select>
					</div>
					<div   >
						<label align="left" style="margin-top: 10px; font-size: 13px; font-weight: bold;" for="monto"  class="col-md-4">* <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MONTO")%></label>
						<input type="text"  style="margin-top: 6px; font-size: 14px; width: 180;" id="monto" name="monto" onChange="numberFormat(this.value)" maxlength="50" onkeypress="return permite(event, 'montos')" value=""/>
					</div>
					<br><br>
					<div  align="center" id="datosCotizador2">
				   </div>
				   </div>
				  </div>
				  <div align="center">
				  <br>
				   <button type="button" onclick="realizarCotizacion(monto.value, selectMoneda.value, selectOpe.value)"  class="grandeAzul" ><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_REAL_COT")%></button>
				  </div>
				 </form>
				</div>
				<%-- GP17667 INI --%>
				<div id="tabPSE" class="tab-pane divtamdiv">
				
	                 <p class="textPse"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_GIRO_OPERA_CONFIR")%></p>
                		<table class="simple3 sortable" id="tblBlotter">
                			<thead>
							<tr >
								<td id="cab" class="title-tr" colspan="10" align="left" ><h2 style="color:#757575 ;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_OPE_REAL")%></h2></td>
								<tr style="height: 43px;">
								<td id="cab" colspan="6" >
								<div align="left">
                                    <button type="button" id="btnDetalleBlotter"   style="width: 20%; text-align: center !important;"  class="bgGris btnBlotter" disabled><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
										<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VER_DET") %> 
									</button>
									
									<button type="button" id="modificarPse"   onclick="redirectModificar()" style="width: 20%; text-align: center !important;"  class="bgGris btnBlotter" disabled><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
										<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MODIFICAR") %> 
									</button>
									
									<button type="button" id="actualizarOperaPse"   onclick="redirectActualizar()" style="width: 20%; text-align: center !important; display: none;"  class="bgGris btnBlotter" disabled ><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
										<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ACTUALIZAR") %> 
									</button>
								</div>
								</td>
								
								<td id="cabfil" class="btnTab" colspan="6" align="right">
									<input type="text" id="filtroBlotter" placeholder="<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BUSCAR")%>"  style="background: url(/images/version7/icoBuscar.png) no-repeat scroll 97% 57%/16px;height: 26px!important" /> 
									<button type="button" id="filtrarBlotterBtn"  class="botonIcono"  >
										<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILTRAR")%>
									    <img src="/images/version7/filtros.png"/>
                                   	</button>
                                   	 
									<div id="filtrarBloter" class="ui-general ui-panel ui-toltip-panel ui-arrow-up2 ui-floating-panel" style="width: 260px;padding: 10px;float:right;margin:15px 0px 0px 257px">
										<div >
											<a id="filtrarBloterCerrar" href="#">
												<img src="/images/version7/cerrar.png" />
											</a>
										</div>
										<div id="contentFiltrarBloter" align="left" style="height: 180px;">
											<h2><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILT_OPE")%></h2>
											<label for="filtroPagosFechaEnvio"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECH_ENV")%></label>
											<br><br>
											<div class="form-group col-md-14 " >
												<div class="form-group col-md-12" >
													<label ><strong><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DESDE")%> </strong></label>
													<div >
													<input class="ui-calendar ui-general ui-box"   wv="filtroFechaDesdeBlo" name="filtroFechaDesdeBlo" id="filtroFechaDesdeBlo" style="width : 160px;position: relative;-webkit-border-radius: 4px 4px 4px 4px;" /> 							
							                          <script>
// 															_$$IN({
// 															"fn" : UI.calendar,
// 															"arg" : "filtroFechaDesdeBlo"
// 																});
                                                               $(function () {
                                                        	   $("#filtroFechaDesdeBlo").datepicker({minDate:'-90d' ,
                                                        		   maxDate:new Date() ,
                                                        		   dateFormat: 'dd-mm-yy',
                                                        		   "arg" : "filtroFechaDesdeBlo",
                                                        		    showOn: "button",
                                                        		    buttonText: "<i class='ui-input-container ui-icon ui-icon-calendar'></i>"
                                                                   });
                                                               });
														</script>
													</div>
												</div><br><br>
												<div class="form-group col-md-12">
													<label ><strong><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_HASTA")%> </strong></label>
													<div >
													<input class="ui-calendar ui-general ui-box"   wv="filtroFechaHastaBlo" name="filtroFechaHastaBlo" id="filtroFechaHastaBlo" style="width : 160px;position: relative;-webkit-border-radius: 4px 4px 4px 4px;" disabled /> 							
							                          <script>
// 															_$$IN({
// 															"fn" : UI.calendar,
// 															"arg" : "filtroFechaHastaBlo"
// 																});
                                                             function fechaHasta(){
                                                            	var d = new Date(); // today!
                                                				var day = d.getDate()  ;
                                                				var month = d.getMonth() + 1;
                                                				var year = d.getFullYear();
                                                			  	var todayDay = (day <= 9 ? '0' + day : day)+ '-' + (month<=9 ? '0' + month : month) + '-' + year ;
                                                			   	$("#filtroFechaHastaBlo").val(todayDay);
                                                            }
                                                            $(function () {
                                                                $("#filtroFechaHastaBlo").datepicker({minDate: new Date(),
                                                                	maxDate:new Date(),
                                                                	dateFormat: 'dd-mm-yy',
                                                                	showOn: "button",
                                                        		    buttonText: "<i class='ui-input-container ui-icon ui-icon-calendar'></i>"
                                                                	});
                                                                 });
														</script>					
													</div>
												</div>
										
											   <div class="form-group col-md-12" style="margin-top:10px;margin-bottom: 10px;">
													<label ><strong><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_NIT") %>: </strong></label>
													<select id="selectNitBlotter" style="float:left;width: 200px;margin-top:6px; font-size: 11px;" required="true" name="selectNit"  > 
														<%
														Vector listaNit1 = (Vector) datos.get("IcListaNit");
														Enumeration enumlistaNit1= listaNit.elements();
														int cant1=listaNit1.size();
														if(cant1!=1){ 
														%>
															<option value=""> </option>
														<% }
														if(cant1>0){
															int i=0;
															String Empresa1="|";
															while (enumlistaNit1.hasMoreElements()) {
															    i++;
																Hashtable klistC1 = (Hashtable) enumlistaNit1.nextElement();
																String Nit1 = (String) klistC1.get("NumeroDocumento");
																String tipoDocumento1 = (String) klistC1.get("TipoDocumento");
																String DigitoVerificacion1 = (String) klistC1.get("DigitoVerificacion");
																Empresa1 = (String) klistC1.get("NombreCliente") +  Empresa1;
																String ClienteCons21 = klistC1.get("TipoDocumento")+Nit1+DigitoVerificacion1;
																Nit1 = Nit1.replaceFirst ("^0*", "");
																String NitVisual1 =Nit1+"-"+DigitoVerificacion1;
																String ClienteCons1 = (String) klistC1.get("ClienteAltamira");
																if(!ClienteCons1.trim().equalsIgnoreCase("")){
																	//INI NITS 1.2 CMC FX 20/08/2019
																	if(i==1){%>
																		<option value='<%=tipoDocumento1+""+NitVisual1%>' ><%=NitVisual1%> (<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT_PRINCIPAL") %>)</option>
																	<%} else{ %>	
																		<option value='<%=tipoDocumento1+""+NitVisual1%>' ><%=NitVisual1%></option>
															    	<% }
																	//FIN NITS 1.2 CMC FX 20/08/2019
																}
												            }
														}
														%>
													</select>
												</div>
											</div>
											<div class="col-md-12" align="center" >
												<button type="button"  class="botonIcono" onclick="filterBlotter()">
													<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FILTRAR")%><img src="/images/version7/filtros.png"/>
												</button>
											</div>
										</div>
										<!-- FIN - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 12-07-2019 -->
									</div>                                   	 
                                   	 							
							</td>
								</tr>
							</thead>	
						<tbody id="mas" >
							<tr id="tr-cab" class="tr-cab-class">
								<!--<td style="width: 9%" id="cab1" class='tdchekc td-check'><input type="checkbox" disabled="disabled" style="display:none"/> </td>-->
								<th style="width: 4%"  id="cab1" class='nosort'><!--<input id="chkSelectAll" type="checkbox"  />--></th>
								<th style="width: 9%" id="cab2" class="td-tit-fechpro"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_FECHA")%></th>
								<th style="width: 10%" id="cab3"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_OPERACION")%></th>
								<th style="width: 10%" id="cab3"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_AVANCE")%></th>
								<th style="width: 10%" id="cab4"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_DIVISA")%></th>
								<th style="width: 10%" id="cab5"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MONTO")%></th>
								<th style="width: 10%" id="cab6"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TASA")%></th>
								<th style="width: 16%" id="cab7"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_GIRO")%></th>
								<th style="width: 10%" id="cab8"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_BENEFICIARIO")%></th>
								<th style="width: 20%" id="cab8"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_ESTADO_PAGO_PSE")%></th>
							</tr>
							</tbody>
							
                		</table>
                		
					<div id="tablaVerM" class="">
						<span style="100%">
						<p style="display:inline-block;text-align:center;margin-left:calc(100%/2-34px)">
						<%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VER_MAS")%>
						<img src="/images/version7/ver_mas.png">  </p>
	             		</span>
						<a onclick="verAtras()"> <img style="display:inline-block;float:right" src="/images/version7/ico_irArriba.png"> </a>
					</div>
					<button type="button" id="finalizarPse" style="width: 15%; height: 27px !important; text-align: center !important; margin-top: 10px; margin-left: 85%;"  class="bgGris btnConfirmar " disabled><!--INC FX BOTON 26-07-2018 --><!-- Incidencia 119 FX CMC 13/11/2018 -->
							<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONFIRMAR") %> 
					</button>
				</div>
				
				<%-- GP17667 FIN --%>
				<a href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=tipoGiros" class="linkverMas"><< <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_VOL_INI")%></a>
			</div>
		</div>	
	</form>
		
	<div class="modal" id="modalComun"  ></div>
	<form action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizarPse" method="post" id="redirecPse">
	  <input type="hidden" id="operationObj" name="operationObj">
	</form>
	
            <!--end Modal unico -->
	<script src="/js/version7/vendorDivisas.js"></script>
	<script src="/js/version7/pluginsDivisas.js"></script>
	<script src="/js/version7/op_objDivisas.js"></script>
	<script src="/js/version7/mainPagosDivisas.js"></script>
	<script src="/js/version7/negociacion_Divisas_Scripts.js"></script>  <%-- IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
		<div class="col-md-3  msnAyudaDivPse proceso ocultar" id="msnAyudaDiv" >
			
	</div>
	
</body>
</html>