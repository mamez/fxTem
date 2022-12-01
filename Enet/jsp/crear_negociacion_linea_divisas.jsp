<!DOCTYPE html> 
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><!-- INC 202 FX CMC 20/03/2019 -->
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.ibm.dse.base.Hashtable"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.util.Locale"%>
<%
	String tipoFondoGiro = (String)datos.get("tipoFondoGiro");
    String chekTipoBbva=tipoFondoGiro.equals("fondoBBVA")?"checked":"";
    String chekTipoPse=tipoFondoGiro.equals("fondoPSE")?"checked":"";
	String stylePseMsg=tipoFondoGiro.equals("fondoPSE")?"":"display: none;";
    String isOpenModal = datos.get("isOpenModal") != null ? (String) datos.get("isOpenModal") : "";
    String contingencia = (String) datos.get("contingencia");
	String pseAutorizado = (String)datos.get("pseAutorizado");
	String rangoAutorizado = (String) datos.get("rangoAutorizado");    
    if (contingencia.equalsIgnoreCase("ACTIVO") || rangoAutorizado.equalsIgnoreCase("NO")){
    	chekTipoBbva="checked";
    	chekTipoPse="";
    }
%>
<html lang="es-ES">
  <head>
		<script type="text/javascript" src="/js/version7/negociacion_Divisas_Scripts.js"></script>  <%-- IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
		<meta charset="ISO-8859-1"/><!-- INC 202 FX CMC 20/03/2019 -->
		<script src="/js/jquery.min.js"></script>
		<!-- INI INC 133 FX CMC Incompatibilidad IE 09/01/2019 -->
		<script src="/js/version7/iecomp.js"></script>
		<!-- FIN INC 133 FX CMC Incompatibilidad IE 09/01/2019 -->
		<script src="/js/version7/neg_divisas.js"></script>
	<!-- INI GP 12886 MOISES LUNA FX 13-03-2018
		<script src="https:/js/jquery.cookie.js"></script>
		FIN GP 12886 MOISES LUNA FX 13-03-2018 -->
		<script type="text/javascript" language="JavaScript" src="/js/version7/utilPibee.js"></script>
		<!-- INI INC 23 FX 23/03/2018 -->
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
		 else{
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
		<!-- FIN INC 23 FX 23/03/2018 -->
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
		<link rel=stylesheet type='text/css' href="/estilos/version7/mainPagos.css" />
		<link rel=stylesheet type='text/css' href="/estilos/version7/newLook.css"/>
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-ui.css" />
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-icon.css" />
		<link rel=stylesheet type='text/css' href="/estilos/version7/tooltipDivisas.css" /><!--INI INC 213 FX CMC 06/05/2019-->
		<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"><!-- GP17667 -->
		<!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
 <!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
  <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
 <!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
		<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
		<!-- INI INC 133 FX CMC Incompatibilidad IE 03/01/2019 -->
		<script>
		   function declinar(){
			   var elementBT2= document.getElementById("btnCantelar");
				     elementBT2.disabled=true;
				     elementBT2.classList.remove("buttonGiroAzul");
				     elementBT2.classList.add("buttonGiro");
		     if (confirm('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_DEC_OP") %>'))//INI INC 49 FX CMC 28/02/2018
			 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
		   }//FIN INC 49 FX CMC 28/02/2018
		   function continuar(a){
			   	 var montoC = "<%=datos.get("cMonto")%>";
			   	 var montoSinFormato = a.replace(",","");
			 	 var tipoFondoGiro = $('input[name="tipoFondoGiro"]:checked').val();
				 var elementBTC2= document.getElementById("btnContinuar1");
				     elementBTC2.disabled=true;
				     elementBTC2.classList.remove("buttonGiroAzul");
				     elementBTC2.classList.remove("buttonGiroAzull3");
				     elementBTC2.classList.add("buttonGiro");
				 if(tipoFondoGiro=="fondoPSE"){
					getCommissionOperative($("#monto").val(), $("#selectMoneda").val(), $("#selectOpe").val(), $("#selectCta").val(), $("#descripcion").val(),$("#selectNit").val(), $('input[name="tipoFondoGiro"]:checked').val(), $("#selectNit").val(), $('input[name="tipoFondoGiro"]:checked').val(), $("#numTrans").val(), $("#tasa4").val());
				 	document.getElementById('datosOperacion').style.display = 'block';
				 }else{
					document.getElementById('equivaPesos').value = "";
					document.getElementById('comision').value = "";
					document.getElementById('iva').value = "";
					document.getElementById('totalDebito').value = "";
					document.getElementById('datosOperacion').style.display = 'none';
				 }
			   	 if(montoC === montoSinFormato){
			     	window.location.href="#top";
			     	$("#tiempo").val("21");//TEMP
			     	$("#container").hide();
			     	document.getElementById('msjImportante').style.display = 'contents';
			     	//$("#msjImportante").show();
				 	primerContador = 1;//INC 146 FX CMC 19/12/2018
		         	singleton.crearTimer();//INC 35 CMC FX 23/03/2018
			   	 }else{
			   		disableContinue();
			   		return false;
			   	 }
		   }
		   //INI incidencia 213 FX CMC 21/05/2019
		function validarDecimales(valorMonto){																				
			var cantidadDecimales = 0;
			 var tamanoMonto = 0;
			 var decimales = "";
			 var ubicacionPunto = String(valorMonto).indexOf(".")+1;
			 var entero = "";
			 var decimalFinal = "";
			 tamanoMonto = valorMonto.length;											
			 if(String(valorMonto).indexOf(".")>=0){	
				decimales = valorMonto.substring(ubicacionPunto,tamanoMonto);	
				entero = valorMonto.substring(0,ubicacionPunto-1);				
				cantidadDecimales = decimales.length;									
				if(cantidadDecimales > 2){
					for (i = 0 ; i <= 1; i++)
						decimalFinal += decimales.charAt(i);
					$("#monto").val(entero+"."+decimalFinal);
				}else{
					$("#monto").val(valorMonto);
				}
			 }			 
		}
		   //FIN incidencia 213 FX CMC 21/05/2019
		   function numberFormat(numero){
		     disableContinue();//INC 6 FX 16/04/2018
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
		 function disableContinue() {// INC 06 FX 16-04-2018
		     var elementBTC2= document.getElementById("btnContinuar1");
		     elementBTC2.disabled=true;
		     elementBTC2.classList.remove("buttonGiroAzul");
		     elementBTC2.classList.add("buttonGiro");
		 }
		 function inicio(){
			     var t1 = $("#tiempoIN").val();
			     $("#msjImportante").hide();
			     $("#tiempo").val(t1);
			     $("#container").show();
			     countredirect();
		 }
		 function cotizarNegociacion(a, b, c, d, e, f){
			     var t1 = $("#tiempoIN").val();
			     var montoStr = a.replace(",","");
			     if(valida_cotiz()){
				 $("#tiempo").val(t1);
				 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarLinea&monto="+ montoStr +"&selectMoneda="+ b + "&selectOpe=" + c + "&selectCuentaOrden=" + d + "&descripNegociacion=" + e+"&ClienteCons="+f+"&tipoFondoGiro="+$('input[name="tipoFondoGiro"]:checked').val();//L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			     }		
			 }
		</script>
		<!-- INI INC 133 FX CMC Incompatibilidad IE 03/01/2019 -->
		<script>
		 $(document).ready(function() {
//INI GP 12886 MOISES LUNA FX 13-03-2018
                       console.log("print1");     
//FIN GP 12886 MOISES LUNA FX 13-03-2018    
			$("#montoInvalido").hide();//Incidencia 213 FX CMC 03/05/2019 
		     $("#container").show();
		     $("#msjImportante").hide();
		     var numero = $("#monto").val()
		     numberFormat(numero);
		     var primerContador = 0;//INC 146 FX CMC 19/12/2018
		     var segundoContador = 0;//INC 146 FX CMC 19/12/2018	
		     var t1 = parseInt($("#tiempoIN").val());
		     var t2 = parseInt($("#tiempoIN").val()) + 1;
		     var tOnline = parseInt($("#tiempo").val());
		     if(tOnline == t2){
			 $("#tiempo").val(t1);
			 countredirect();
		     }
		     habContinuar();
		     //INI INC 214 y 137-147 FX CMC 27/05/2019
             var descErrorMontoJ=document.getElementById('descErrorMonto').value;
             var str1 = $("#validaCta").val();
		     var res1 = str1.substring(0, 1);
		     $("#validaCta").val(res1);
             if(null!=descErrorMontoJ && descErrorMontoJ != "" && descErrorMontoJ!="null" && descErrorMontoJ.length>0){
             	disableContinue();
             	$("#messageBoxCM7").show();
             } else if($("#validaCta").val() =="N"){
            	$("#messageBoxCM6").show();
             } else if($("#validaCta").val() =="S"){
			 	$("#messageBoxCM6").hide();
			 	habContinuar();
		     } else {
            	$("#messageBoxCM7").hide();
             }
        	 //FIN INC 214 y 137-147 FX CMC 27/05/2019
		     //INI INC 202 FX CMC 20/03/2019
		     var valida=document.getElementById('validaDes').value;
		     if(valida =="VD" && valida != ""){
		     disableContinue();
			 $("#messageBoxCM9").show();
		     }
			 // FIN INC 202 FX CMC 20/03/2019
			 //INI INC 213 FX CMC 22/05/2019
			 var banderaMonto = document.getElementById('montoBandera').value;//INC 213 FX CMC 06/05/2019
			 if(banderaMonto != null || banderaMonto != ""){
				if(banderaMonto=="primera"){
					$("#montoInvalido").hide();
					disableContinue();
				}else if(banderaMonto=="s"){
					disableContinue();
					$("#montoInvalido").show();
				}else if(banderaMonto=="n"){
					$("#montoInvalido").hide();
				}
			 }
			 try{
				 ajustar();
				 monedaSelected($("#selectMoneda").val());				 
			 }catch(error){
			 }
			 //FIN INC 213 FX CMC 22/05/2019
		     //INI INC 8 FX CMC - 15/02/2018
		     $("#tooltipAyuda").hover(function()			{
	    		 $(".msnAyuda").css("display", "block");      
		     }, function(){
			 $(".msnAyuda").css("display", "none");
		     });
			 //FIN INC 8 FX CMC - 15/02/2018
			//INI INC 213 FX CMC 03/05/2019
			$("#tooltipAyudaMonto").hover(function()			{
			$(".msnAyudaMonto").css("display", "block");      
			}, function(){
			$(".msnAyudaMonto").css("display", "none");
			});
			//FIN INC 213 FX CMC 03/05/2019
			$("#tooltipGMF").hover(function()			{
			$("#msnAyudaDiv").css("display", "inline-block");      
			}, function(){
			$("#msnAyudaDiv").css("display", "none");
			});
			
			$("#tooltipGMF2").hover(function()			{
			$("#msnAyudaDiv2").css("display", "inline-block");      
			}, function(){
			$("#msnAyudaDiv2").css("display", "none");
			});
		     //INI INC 6 FX 14/04/2018
		     function select() {
		     }
		     //FIN INC 6 FX 14/04/2018
		     //INI INC 6 FX 09-04-2018
		     $('document').ready(function () {
			 //INI INC 6 FX 14/04/2018
			 select();
			 //FIN INC 6 FX 14/04/2018
		     });
		     //FIN INC 6 FX 09-04-2018
		     $("#formDivisas input").keyup(function() {
		         validarCampos();
		     });
		     $("#formDivisas select").change(function() {
		         validarCampos();
		     });
		 });
/*INICIO Incidencia 133 CMC FX 09/01/2019*/ 		 
var funTimer = function () {
	var _ref = _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee() {
		var contador;
		return regeneratorRuntime.wrap(function _callee$(_context) {
			while (1) {
				switch (_context.prev = _context.next) {
					case 0:
						contador = 21;
						contador = 21;
					case 2:
						if (!(contador > 0)) {
							_context.next = 12;
							break;
						}
						$('#lb_time2').html(contador);
						console.log(contador); //INC 35 21/03/2018
						_context.next = 7;
						return sleep(1000);
					case 7:
						//INI INC 146 FX CMC 19/12/2018
						if (contador == 1) {
							singleton.eliminarTimer();
						}
						//FIN INC 146 FX CMC 19/12/2018	 
						if (flag) {
							flag = false;
							contador = 1;
							singleton.eliminarTimer();
							singleton.crearTimer();
						}
					case 9:
						contador--;
						_context.next = 2;
						break;
					case 12:
					case 'end':
						return _context.stop();
				}
			}
		}, _callee, this);
	}));
	return function funTimer() {
		return _ref.apply(this, arguments);
	};
}();
function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }
//INI Incidencia 23 CMC FX 26/03/2018
function sleep(ms) {
	return new Promise(function (resolve) {
		return setTimeout(resolve, ms);
	});
}
var flag = false;
var singleton = function () {
	var instancia;
	function nuevaInstancia() {
		return object = new Object(Math.random());
	}
	return {
		crearTimer: function crearTimer() {
			if (!instancia) {
				instancia = nuevaInstancia();
				funTimer();
			} else {
				flag = true;
			}
			return instancia;
		},
		eliminarTimer: function eliminarTimer() {
			if (instancia) instancia = undefined;
		}
	};
}();
/*FIN Incidencia 133 CMC FX 09/01/2019*/ 
			 function valida_cotiz(){
			     montoX="#monto";
			     var montoStr = $(montoX).val();
			     montoStr.replace(",","");
			     var monto = parseFloat(montoStr);
			     ctaX = "#selectCta";
<%-- INI INC CMC OFICINA GESTORA 27/09/2019 --%>
			     var cta_ofi = ($(ctaX).val()).split("_");
			     var cta = cta_ofi[0]
			     var ofi = cta_ofi[1]
<%-- FIN INC CMC OFICINA GESTORA 27/09/2019 --%>
			     descX = "#descripcion";
			     var desc = $(descX).val();
			     monX = "#selectMoneda";
			     var mone = $(monX).val();
			     //desc = desc.replace(" ", "");
			     if(desc==""){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_DESC_OP") %>");
				 return false;
			     }else if (cta=="128"){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_SEL_CUENTA") %>");
				 return false;
			     }else if (mone=="128"){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBE_SEL_TIPO_DIV") %>");
				 return false;
			     }else if(monto<1){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO_MAYOR_CERO") %>"); //Monto Maximo 200.000 Nuevo mensaje - Leonardo Sanchez CMC - 12/03/2020
				 return false;
			     }else{
				 return true;
			     }
			     return false;
			 }
			//INI incidencia 105 FX CMC 04/10/2018
			 function habContinuar(){
			     var TasaUSDCOP=$("#tasa3").val();
			     var TasaLinea=$("#tasa2").val();
			     var montoPesos=$("#tasa4").val();
			     TasaUSDCOP = TasaUSDCOP.replace(/,/g,"");
			     TasaLinea = TasaLinea.replace(/,/g,"");
			     montoPesos = montoPesos.replace(/,/g,"");
			     if($("#validaCta").val() =="S" && $("#tasa4").val() != 0.00){
					 $("#messageBoxCM1").hide();
					 var elementBTC2= document.getElementById("btnContinuar1");
				     elementBTC2.disabled=false;
				     elementBTC2.classList.remove("buttonGiro");
				     elementBTC2.classList.add("buttonGiroAzul");
				     validarCampos();
			     }else{
			    	 disableContinue();
			     }			     
			 }	
			
			 
			 //FIN incidencia 105 FX CMC 04/10/2018
		// INI IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019
			 function continuarBenef(selector,a, b, c, d, e, t1, t2, t3, t4, f, g, h, i, j, k, l, m){ //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
				 var idBtn = selector;
				 desabilitarBtn(idBtn);
		// FIN IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019
			     var tiempo2 = parseInt($("#tiempoIN").val()) + 1;
			     var montoStr = a.replace(",","");
			     $("#tiempo").val(tiempo2);
			     ctaX = "#selectCta";
<%-- INI INC CMC OFICINA GESTORA 27/09/2019 --%>
			     var cta_ofi = ($(ctaX).val()).split("_");
			     var cta = cta_ofi[0]
			     var ofi = cta_ofi[1]
<%-- FIN INC CMC OFICINA GESTORA 27/09/2019 --%>
			     segundoContador = 1;//INC 146 FX CMC 19/12/2018
			     document.getElementById("negolinea").disabled="true";
			     document.getElementById("negolinea").classList.remove('buttonGiroAzul');
			     document.getElementById("negolinea").classList.add('buttonGiro');
				 document.getElementById("btnDeclinar").disabled="true";// Incidencia 122 FX CMC 22/11/2018
				 let tipoFondoGiro=$('input[name="tipoFondoGiro"]:checked').val()
			     window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=selecBeneficiarios&monto="+ montoStr +"&selectMoneda="+ b + "&selectOpe=" + c + "&selectCuentaOrden=" + cta + "&descripNegociacion=" + e + "&tasaDivisa=" + t1 + "&tasaDivisaPeso=" + t2 + "&tasaUsdPeso="  + t3 + "&equivPesos=" + t4 + "&avanceOpe=" + f + "&oficinaGestoraOP=" + ofi+"&Nit_Nombre="+g+"&tipoFondoGiro="+tipoFondoGiro+"&rate="+h+"&totalDebito="+i+"&numTrans="+j+"&equivaPesos="+k+"&comision="+l+"&iva="+m+"&amountEquiv="+t4;//INC CMC OFICINA GESTORA 27/09/2019 //VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			 }
			//INI INC 146 FX CMC 21/12/2018	
			function declinarSegundoContador()
			{	
			var elementB= document.getElementById("btnDeclinar");
				elementB.disabled=true;
				elementB.classList.remove("buttonGiroAzul");
				elementB.classList.add("buttonGiro");
				contador = 1;
				$("#msjImportante").hide();
				$("#container").show();		
				$("#messageBoxCM5").hide();
				habContinuar();	
				var t1 = parseInt($("#tiempoIN").val());
				var t2 = parseInt($("#tiempoIN").val()) + 1;
				//var tOnline = parseInt($("#tiempo").val());				
				$("#tiempo").val(t1);
				countredirect();				
				cotizarNegociacion($("#monto").val(), $("#selectMoneda").val(), $("#selectOpe").val(), $("#selectCta").val(), $("#descripcion").val(),$("#selectNit").val());//L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			    singleton.eliminarTimer();
			}
			//FIN INC 146 FX CMC 21/12/2018	
			 function ajustar(){
			     /* com.bbva.kyop.controller.CoreController.resizeMainContent(300); */
			     let iframe= window.parent.document.getElementById('kyop-central-load-area');
				   iframe.style.height=1100+'px';
			 }
			 function monedaSelected(money){
			     if(money!="128" && money!="" && money!=null ){//FIN INC 6 FX CMC 07/05/2018
			     	if (money == 'GBP' || money == 'EUR')
			     		document.getElementById('labelForTasa1').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> " + money + " USD &nbsp; &nbsp; &nbsp; &nbsp;";
			     	else
						document.getElementById('labelForTasa1').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> USD/"+money + "  &nbsp; &nbsp;";//FIN INC 6 FX CMC 07/05/2018 
			     	document.getElementById('labelForTasa2').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> " +money+"/COP &nbsp; &nbsp;";//INC 23 FX CMC 16-02-2018
			     }else{
				 document.getElementById('labelForTasa1').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_USD") %> &nbsp; &nbsp; &nbsp; &nbsp;";
				 document.getElementById('labelForTasa2').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_DIV") %> &nbsp; &nbsp; &nbsp; &nbsp;";//INC 23 FX CMC 16-02-2018
			     }
			 }
			//INI INC 186 FX CMC 28/02/2019
			function validarDecimal() {
				 var cotizar= document.getElementById("btnCotizar1");
					 cotizar.disabled=true;
					 cotizar.classList.remove("buttonGiroAzul");
					 cotizar.classList.add("buttonGiro");
				var validate = /^(\d{1,3},)*\d{1,3}(\.\d+)?$/;
				var monto = document.getElementById('monto').value;
				if (!(validate.test(monto))) {
					$("#messageBoxCM8").show();
					return false;   
				} else {
					cotizarNegociacion($("#monto").val(), $("#selectMoneda").val(), $("#selectOpe").val(), $("#selectCta").val(), $("#descripcion").val(), $("#selectNit").val()); // L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
					return true;
				}
			}
			function validoCampo(){
			     montoX="#monto";
			     var montoStr = $(montoX).val();
			     montoStr.replace(",","");
			     var monto = parseFloat(montoStr);
			     ctaX = "#selectCta";
			     var cta_ofi = ($(ctaX).val()).split("_");
			     var cta = cta_ofi[0]
			     var ofi = cta_ofi[1]
			     descX = "#descripcion";
			     var desc = $(descX).val();
			     monX = "#selectMoneda";
			     var mone = $(monX).val();
			     if(desc==""){
				 return false;
			     }else if (cta=="128"){
				 return false;
			     }else if (mone=="128"){
				 return false;
			     }else if(monto<1){
				 return false;
			     }else{
				 return true;
			     }
			     return false;
			}
			function validarCampos() {
				var validate2 = /^(\d{1,9},)*\d{1,9}(\.\d+)?$/;
				var monto2 = document.getElementById('monto').value;
				var isCorrect=false;
				var elementBT = document.getElementById("btnCotizar1");
				if (!(validate2.test(monto2))) {
					isCorrect= false;   
				} else {
					isCorrect= validoCampo();
				}					
				if(isCorrect){
					elementBT.disabled=false;
					elementBT.classList.remove("buttonGiro");
					elementBT.classList.add("buttonGiroAzul");
				}else{
					elementBT.disabled=true;
					elementBT.classList.remove("buttonGiroAzul");
					elementBT.classList.add("buttonGiro");
				}
			}
			//FIN INC 186 FX CMC 28/02/2019	
	    //INI - L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
		function ListarCuentas(){
			BuscarCuentas( $("#descripcion").val(), $("#selectNit").val(),$('input[name="tipoFondoGiro"]:checked').val());	
	    }
		function BuscarCuentas(e,f,g){
			 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarLinea&descripNegociacion=" + e+"&ClienteCons="+f+"&tipoFondoGiro="+g;//L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019   		
		 }
		//FIN - L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
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
		</style>
  </head>
  <body onLoad="countredirect();setTimeout(ajustar, 400) "><!-- INC 23 FX 23/03/2018 -->
  
 
	<svg aria-hidden="true" style="position: absolute; width: 0; height: 0; overflow: hidden;" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">    
		<defs>
		
		<symbol id="icon-info" viewBox="0 0 32 32">
			<path d="M16 1.231c-8.157 0-14.769 6.612-14.769 14.769s6.612 14.769 14.769 14.769c8.157 0 14.769-6.612 14.769-14.769v0c0-8.157-6.612-14.769-14.769-14.769v0zM17.231 24.615l-2.462-2.462v-8.615h2.462zM16 11.077c-1.020 0-1.846-0.827-1.846-1.846s0.827-1.846 1.846-1.846c1.020 0 1.846 0.827 1.846 1.846v0c0 1.020-0.827 1.846-1.846 1.846v0z"></path>
		</symbol>
		
		</defs>    
	</svg>

  
  
   <form method="post" method="POST" enctype="multipart/form-data" name="formDivisas" id="formDivisas">
	<input type="hidden" id="selectOpe" name="selectOpe" value="<%=(String)datos.get("selectOpe")%>" />
	<input type="hidden" id="validaCta" name="validaCta" value="<%=(String)datos.get("validaCta")%>" />
	<input type="hidden" id="validaDes"  value="<%=(String)datos.get("validaDes")%>" /><!-- INC 202 FX 20/03/2018 -->
	<input type="hidden" id="montoBandera" name="montoBandera" value="<%=(String)datos.get("montoBandera")%>" /><!--INI INC 213 FX CMC 10/05/2019 -->
	<input type="hidden" id="avanceOpe" name="avanceOpe" value="N.A." />
	<input type="hidden" id="indiceGiro" name="indiceGiro" value=""/>
	<input type="hidden" id="pseAutorizado" name="pseAutorizado" value="<%=(String)datos.get("pseAutorizado")%>"/>
	<input type="hidden" id="textDevPseCta" name="textDevPseCta" value="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA_DEV_PSE") %>"><!-- GP17667 -->
	<input type="hidden" id="textBBVACta" name="textBBVACta" value="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA_DEBITAR") %>"><!-- GP17667 -->
	<input type="hidden" id="equivaPesos" name="equivaPesos" value="<%=datos.get("equivaPesos")%>">
	<input type="hidden" id="comision" name="comision" value="<%=datos.get("comision")%>">
	<input type="hidden" id="iva" name="iva" value="<%=datos.get("iva")%>">
	<input type="hidden" id="totalDebito" name="totalDebito" value="<%=datos.get("totalDebito")%>">
	<input type="hidden" id="rate" name="rate" value="<%=datos.get("rate")%>">
	<input type="hidden" id="numTrans" name="numTrans" value="<%=datos.get("numTrans")%>">
	<input type="hidden" id="tempor1" name="tempor1" value="<%=datos.get("tempor1")%>">
	<input type="hidden" id="tempor2" name="tempor2" value="<%=datos.get("tempor2")%>">
	<input type="hidden" id="tempor3" name="tempor3" value="<%=datos.get("tempor3")%>">
	<input type="hidden" id="tempor4" name="tempor4" value="<%=datos.get("tempor4")%>">
	<input type="hidden" id="tempor5" name="tempor5" value="<%=datos.get("tempor5")%>">
	
	<%
				 Hashtable cotizacion = (Hashtable) datos.get("cotizacion");
				 Vector listaMonedas = (Vector) datos.get("listaDivisas");
				 Hashtable parametria = (Hashtable) datos.get("parametria");
				 Hashtable moneda;
				 String selectMoneda = (String)datos.get("selectMoneda");
				 if(selectMoneda!= null && selectMoneda!= ""){
					selectMoneda= selectMoneda.substring(0,3);
					}
				 String selectCuentaOrden = (String)datos.get("selectCuentaOrden");
// INC GP 12886 MOISES LUNA FX 13-03-2018 -->
				  String timerInicio = "";
				  String piloto = "";
				timerInicio = (String) parametria.get("timer");
// FIN GP 12886 MOISES LUNA FX 13-03-2018 -->
// INI INC 214 FX - CMC 09/05/2019
				String descErrorMonto = (String)datos.get("descErrorMonto");
				if(descErrorMonto == ""){		%> 
					<input type="hidden" id="descErrorMonto"  value="" />
			<% 	}else{ %>
					<input type="hidden" id="descErrorMonto"  value="<%=descErrorMonto%>" />
			<%	}
// FIN INC 214 FX - CMC 09/05/2019
				  int timerIN = Integer.parseInt(timerInicio) + 1;
				  %>
	<input type="hidden" id="tiempoIN" name="tiempoIN" value="<%=timerIN%>"/>
	<input type="hidden" id="tiempo" name="tiempo" value="<%=timerIN%>"/>
    <div id="container" class="container; col-md-12">
        <div class="row titulo">
            <div class="col-md-9" style="height: 50px;">
                <h1 class="sbold tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 1: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_NEW_PASO_1") %></h1><%-- GP17667 --%>
                <p class="subtituloPrincipal"> </p>
            </div>
            <div class="col-md-3">
                <ul id="progreso">
                    <li>
						<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 1 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 4  </b>
                        <img src="/images/version7/barraProgresoVerdeLeft_20x25.png">
						<img src="/images/version7/barraProgresoVaciaCenter_20x25.png">
						<img src="/images/version7/barraProgresoVaciaCenter_20x25.png">
						<img src="/images/version7/barraProgresoVaciaRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>   
        <%-- GP17667 INI --%>
        <p class="textSubtitulo"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SUBTITULO_PASO1") %></p></br>
        
        <div class="divAzul">
        		<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_HORARIO_NEG") %></p>
				<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_TRANSACCION_ACEPTADA") %> <%=parametria.get("horaNegoIni") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <%=parametria.get("horaNegoFin") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_DIAS_ABILES") %></p>
        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_TRAN_COBRO_COMISION") %></p>
        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_CONOCER_COMISION") %> <a class="textCheckAzul" href="https://www.bbva.com.co/personas/informacion-practica/tasas-y-tarifas.html " target="_blank" rel="noopener noreferrer"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_TASAS_TARIFAS") %> </a> </p>
        </div>
        </br>
        <%-- GP17667 FIN --%>
			<div class="col-md-12">
				<div class="alerta bordeWarning clearfix" id="messageBoxCM5">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj5" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM5"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_FUERA_HORARIO") %></ul>
                    </div>
				</div>
				<div class="alerta bordeWarning clearfix" id="messageBoxCM6">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj6" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <!-- <ul id="messageBoxULCM6"> Cuenta <b><%=datos.get("selectCuentaOrden")%></b> no habilitada para la operaci&oacute;n.</ul> -->
			<ul id="messageBoxULCM6"><%=(String) datos.get("msjError") %></ul><!-- INC 35 FX 12/04/2018 -->
                    </div>
				</div>
				<div class="alerta bordeWarning clearfix" id="messageBoxCM7">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj7" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                    	<% descErrorMonto = descErrorMonto.replace("maximo", "m\u00e1ximo"); //INC 214 FX - CMC 02/07/2019 %>
                        <ul id="messageBoxULCM7"><%=descErrorMonto %></ul><% // INC 214 FX - CMC 09/05/2019 %> 
                    </div>
				</div>
				<!-- INICIO INC 186 FX CMC 28/02/2019 -->
				<div class="alerta bordeWarning clearfix" id="messageBoxCM8">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj4" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM8"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO_NO_VALIDO") %></ul>
                    </div>
				</div>
				<!-- FIN INC 186 FX CMC 28/02/2019 -->
				<!-- INICIO INC 202 FX CMC 18/03/2019 -->
				<div class="alerta bordeWarning clearfix" id="messageBoxCM9">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaWarning.png"> 
                    </div>
					<div id="cerrarmsj4" class="interior3">
                        <img src="/images/version7/cerrarAlertaDiv.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="messageBoxULCM9"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESC_INVALIDA") %></ul>
                    </div>
				</div>
				<% 
					String indiList = datos.get("indiList") != null ? (String) datos.get("indiList") : "";
					if(!indiList.equalsIgnoreCase("NN")){
				%>
				<div class="alerta clearfix" id="messageBoxCM10" style="background-color: #fceaea !important; border: 1px solid #d8b6c7 !important;">
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
				<% } %>
				<!-- FIN INC 202 FX CMC 18/03/2019 -->
			</div>
			<script type="text/javascript">
							  var tiempoTotal = parseInt($("#tiempoIN").val()) - 1;
							  //var t2 = parseInt($("#tiempoIN").val()) + 1;//INI INC 66 FX 07/06/18
                              //var countdownfrom=tiempoTotal;
                              function countredirect(){
							  var currentsecond=$("#tiempo").val();
							  //if(currentsecond!=t2){
							  if (currentsecond!=1){
								currentsecond-=1;
								//INI INC 146 FX CMC 21/12/2018	
                              	//document.getElementById('lb_time').innerHTML = currentsecond;
								$('#lb_time').html(currentsecond);
								//FIN INC 146 FX CMC 21/12/2018	
                              	setTimeout("countredirect()",1000);
                              }
                              else{
                              	currentsecond=tiempoTotal;
								//INI INC 146 FX CMC 21/12/2018	
								//document.getElementById('lb_time').innerHTML = currentsecond;
								$('#lb_time').html(currentsecond);
								//FIN INC 146 FX CMC 21/12/2018
									var monto = $("#monto").val();
									monto = monto.replace(",","");
									if(monto >= 1  && $("#selectMoneda").val() != "128" && $("#selectCta").val() != "128" && $("#descripcion").val() != ""){
									cotizarNegociacion($("#monto").val(), $("#selectMoneda").val(), $("#selectOpe").val(), $("#selectCta").val(), $("#descripcion").val());
									}
									setTimeout("countredirect()",1000);
							}
							//FIN INC 146 FX CMC 21/12/2018
							  //}//FIN INC 66 FX 07/06/18
                              $("#tiempo").val(currentsecond);
                              }
                  </script>
                  
			<div class="col-md-3" align="center">
				<!-- INI GP 12886 MOISES LUNA FX 13-03-2018 -->
					<label align="left" style="font-size: 15px; font-weight: bold;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TIEMPO")%></label>
					<label id="lb_piloto" align="left" style="font-size: 15px; font-weight: bold; margin-left: -3px;"></label>
					<br><br>
				<!-- FIN GP 12886 MOISES LUNA FX 13-03-2018 -->
					<img id="timer" src="/images/version7/timer_icon.png" width=70px height= 70px/><br><br>
						<label id="lb_time" style="font-size: 15px; font-weight: normal;" align="left" > </label> <label style="font-size: 13px; font-weight: normal;" align="left" > <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_SEGUNDOS")%> </label>
				  </div>
			<div class="col-md-7" style="overflow-x:auto,overflow-y:auto"><!-- GP17667 -->
				<!-- INI INC 8 FX CMC - 15/02/2018 -->
			    <div align="right" class="row" class="col-md-12"><!-- GP17667 -->
				    <div class="col-md-5">
				    	<label align="" style="padding: 9px 0px 0px 0px; font-size: 13px;" for="descripcion">
					    	*<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESCRIPCION_OP") %>
					    </label></br>					    
					    <label align="right" for="descripcion" style="margin-right: 45px;">
				         	<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MAX_35_CARACTERES") %>
	                    </label>
				    </div>
				    <div class="col-md-7" align="left">
				    	<input type="text" required="true" style="font-size: 14px; width: 170;" value="<%=datos.get("descripNegociacion")%>" maxlength="35" id="descripcion" name="descripciscripcion" onkeypress="return permite(event, 'text')" onChange="disableContinue()">
				      	<a class="tooltipAyuda" id="tooltipAyuda" href="#">
				        	<img src="/images/ayudaEmergenteDerecha.png">
						</a>
				    </div>    	
			    </div>
				<div align="right" class="col-md-12"> <!-- GP17667 -->
					<div class="col-md-5">
						<label align="left" style="margin-top: 10px; font-size: 13px;" for="selectNit">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
					</div>
					<div class="col-md-7" align="left">
						<select id="selectNit" required="true" name="selectNit" onChange="ListarCuentas()" style ="width: 180px; margin-top: 6px; font-size: 11px;" > <!-- INC 172 FX CMC 25/01/2019 -->
							<%
							Vector listaNit = (Vector) datos.get("IcListaNit");
							Enumeration enumlistaNit= listaNit.elements();
							int cant=listaNit.size();
							String cl="";
							cl=(String)datos.get("s_ClienteCons");
							if(cant!=1){ 
							%>
								<option value="NA"> </option>
							<% }
							if(cant>0){
								int i=0;
								while (enumlistaNit.hasMoreElements()) {
								    i++;
									Hashtable klistC = (Hashtable) enumlistaNit.nextElement();
									String Nit = (String) klistC.get("NumeroDocumento");
									String DigitoVerificacion = (String) klistC.get("DigitoVerificacion");
									String tipoD = (String) klistC.get("TipoDocumento");
									String oficina = (String) klistC.get("Oficina");
									Nit = Nit.replaceFirst ("^0*", "");
									String NitVisual =Nit+"-"+DigitoVerificacion;
									String trama =tipoD+""+Nit+""+DigitoVerificacion+"@"+oficina;
									String ClienteCons = (String) klistC.get("ClienteAltamira");
									if(!ClienteCons.trim().equalsIgnoreCase("")){
								    %>	
									<option value='<%=ClienteCons+"-"+trama%>' <%=ClienteCons.equals(cl)?"selected":"" %>><%=NitVisual%></option>
								    <%
									}
					            }
							}
							%>
						</select>
						<%
						int a=0;
						Vector listaNit2 = (Vector) datos.get("IcListaNit");
						Enumeration enumlistaNit2= listaNit2.elements();
						while (enumlistaNit2.hasMoreElements()) {
						    a++;
						    Hashtable klistC2 = (Hashtable) enumlistaNit2.nextElement();
						    //INI- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 //
						    String Nit = (String) klistC2.get("NumeroDocumento");
							String DigitoVerificacion = (String) klistC2.get("DigitoVerificacion");
							Nit = Nit.replaceFirst ("^0*", "");
							String NitVisual =Nit+"-"+DigitoVerificacion;
							//FIN- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 //
							String emp=(String) klistC2.get("NombreCliente");
							String clien=(String) klistC2.get("ClienteAltamira");
							String visual=clien.equals(cl)?"block":"none";
						%>
						 <div align="left" style="display:<%=visual%>" id="empresa">
			       	       <p><b id="VisualEmpresa" style="width: 150px;"><%=emp %></b></p>
			             </div>
			                        <%--INI- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
						<% 
						if(visual.equalsIgnoreCase("block")){ %>
							<input type="hidden" id="Nit_Nombre" name="Nit_Nombre" value="<%=NitVisual+"@"+emp %>" />
						<%}
						}
						%>
						<%--FIN VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%>
					</div>
				</div>
			<!-- GP17667 INI -->
			<div align="right" class="col-md-12">
				<div class="col-md-5">
					<label style="margin-top: 10px;font-size: 13px;padding-right: 9px;" for="fondosOrigin">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_ORIGEN_FONDOS") %></label>
				</div>
				<div class="col-md-7" align="left">
					<%
					
					if(contingencia.equalsIgnoreCase("ACTIVO")){
					%>
						<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
					<%
					}else{
						if(pseAutorizado.equalsIgnoreCase("true") && rangoAutorizado.equalsIgnoreCase("SI")){ 
					%>
					<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
					<input type="radio" name="tipoFondoGiro" value="fondoPSE" id="fondoPSE"   <%=chekTipoPse %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA_PSE") %> <img src="/images/version7/pseDiv.png" class="pseItem">
					<%}else{%>
						<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
						<input type="radio" name="tipoFondoGiro" value="fondoPSE" id="fondoPSE"  disabled <%=chekTipoPse %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA_PSE") %> <img src="/images/version7/pseDiv.png" class="pseItem">
					<%}}%>
				</div>
			</div>
			<!-- GP17667 FIN -->
			<div align="right" class="col-md-12"> <!-- GP17667 -->
				<div class="col-md-5"><!-- GP17667 -->
					<label align="left" id="lbSeleccionCta" style="margin-top: 10px; font-size: 13px;" for="selectCta">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA_DEBITAR") %></label>
				</div>
				<div class="col-md-7" align="left"><!-- GP17667 -->
				<div class="col-md-4 ocultar msnAyudaDiv" id="msnAyudaDiv" style="width: 310px; margin-right: -161px; margin-top: -110px;">
						<p class="text12" style="padding: 10px 5px 3px 5px;">
							<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_Q_GMF") %></b>
						<br>
							<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_GMF_DESCRIPCION") %>
						</p>
				</div>
					<select id="selectCta" required="true" name="selectCta" onChange="disableContinue()" style ="width: 180px; margin-top: 6px; font-size: 11px;" > <!-- INC 172 FX CMC 25/01/2019 -->
						<option value="128"> </option>
						<%
								Vector listaCuentas = (Vector) datos.get("IcListaCuenta");
								Enumeration enumListaCuentas = listaCuentas.elements();
								int cntRegC = 1;
								int n = listaCuentas.size();
								if(listaCuentas.size()>0){
									while (enumListaCuentas.hasMoreElements()) {
										Hashtable klistC = (Hashtable) enumListaCuentas.nextElement();
										String numCuenta = (String) klistC.get("Cuenta");
										String asunto =numCuenta + "  " +  "COP";
										if(!numCuenta.trim().equals("")){	
	//INC CMC OFICINA GESTORA 27/09/2019
											String oficinaGestora = (String) klistC.get("oficinaGestora");
											String cuentaOrdenante = (String) datos.get("selectCuentaOrden");
											if (cuentaOrdenante!=null){
												String [] cuenta_1 = cuentaOrdenante.split("_");
												cuentaOrdenante = cuenta_1[0];
											}									
									  %>	
										<option value='<%=numCuenta+"_"+oficinaGestora%>' <%if(numCuenta.equals(cuentaOrdenante)){%> selected <%}%> ><%=asunto%></option>
									  <%
	//FIN CMC OFICINA GESTORA 27/09/2019
										}
										cntRegC ++;
						}}%>
					</select>
					<p class="text10" id="textCtaPSE" style="<%=stylePseMsg%>"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_1")%><b class="text10" id="tooltipGMF" style="text-decoration-line: underline; font-size: 10px;"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_2")%> </b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_3") %></p>
					
				</div>
			</div>
			<!-- FIN - L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 -->
			<div align="right" class="col-md-12"> <!-- GP17667 -->
				<div class="col-md-5">
					<label style="margin-top: 10px; font-size: 13px; " for="selectMoneda" >* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONEDA") %> </label>
				</div>
				<div class="col-md-7" align="left">
					<select id="selectMoneda" required="true" align="left" name="selectMoneda" onChange="disableContinue()" style ="width: 180px !important; margin-top: 6px; font-size: 13px;" ><!-- INC 23 12/04/2018 -->
								<option value="128"> </option>
								<%  
								    for(int i=0;i<listaMonedas.size();i++){
	                                moneda =(Hashtable) listaMonedas.get(i);
									String money=(String) moneda.get("descMoneda");
									if(!money.trim().equals("") && money.length()>=3 ){ //SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020
										money = money.substring(0,3);
								%>
								<option  value="<%=money%>"  <%if(money.equals(selectMoneda)){%> selected <%}%>><%=CatalogoMultidioma.obtenerLiteral("PNET_MONEDAS_DIV",idioma,money)%></option><%--SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020 --%>
								<%}
								    } //SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020 
	                              %>
					</select>
				</div>
			</div>
			<div align="right" class="col-md-12"><!-- GP17667 -->
				<div class="col-md-5">
					<label style="margin-top: 10px; font-size: 13px; " for="monto">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO") %></label>
				</div>
				<div class="col-md-7" align="left">
					<div class="col-md-4 ocultar msnAyudaMonto" style="width: 140px;top: -8px;float: right;margin-right: 0px;margin-top: -50px; text-align: left;">
						<p class="letragris">
							<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CAMPO_DECIMALES") %>
						<br>
							<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DELIMITADOR_DECIMALES") %>
						</p>
					</div>
					
					<input type="text" name="monto" required="true" style="font-size: 14px; width: 152;" id="monto" onChange="numberFormat(this.value)" onkeyup="validarDecimales(this.value)" onkeypress="return permite(event, 'montos')" value="<%=datos.get("monto")%>"/>				
					<a class="tooltipAyudaMonto" id="tooltipAyudaMonto" href="#">
				    	<img src="/images/ayudaEmergenteDerecha.png">
					</a>
					<label align="right" for="montoInvalido" id="montoInvalido" style="margin-top: 1px;font-size: 11px;width: 155;padding-right: 1px;margin-right: 110px;background-color: white;">
				         		<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO_NO_VALIDO") %>
	                </label>
				</div>
			</div>
			<div align="right" class="col-md-12"><!-- GP17667 -->
				<div class="col-md-5">
					<label style="margin-top: 10px; font-size: 13px; " for="tasa2" id="labelForTasa2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_PESO") %></label>
				</div>
		      	<div class="col-md-7" align="left">
					<input type="text" disabled="true" style="font-size: 14px; width: 180px;" id="tasa2" value="<%=cotizacion.get("tasaDivisaPeso")%>" name="tasa2" />		
				</div>
			</div>
	<!-- INI GP 12886 MOISES LUNA FX 13-03-2018 -->
		   <input type="hidden" id="tiempoAux" name="tiempoAux" value="<%=cotizacion.get("timerS")%>" />
		   <input type="hidden" id="pilotoAux" name="pilotoAux" value="<%=cotizacion.get("piloto")%>" />
		   	<script>
		   			var pil = document.getElementById('pilotoAux').value;
		   			if(pil.includes("S")){
						document.getElementById('lb_piloto').innerHTML=":";
		   			}else{
						document.getElementById('lb_piloto').innerHTML=":";
					}
					if(parseInt(document.getElementById('tiempoAux').value) > 0){
						console.log("OK");
						document.getElementById('tiempoIN').value=document.getElementById('tiempoAux').value;
						document.getElementById('tiempo').value=document.getElementById('tiempoAux').value;
						tiempoTotal = parseInt(document.getElementById('tiempoAux').value);
						currentsecond = tiempoTotal;
						document.getElementById('lb_time').value=tiempoTotal;
					}else{
						console.log("fail");
					}
			</script>
	<!--	FIN GP 12886 MOISES LUNA FX 13-03-2018 -->
		<%
						selectMoneda = (String)datos.get("selectMoneda");
						if(!selectMoneda.contains("USD")){
		%>
		<div align="right" class="col-md-12"><!-- GP17667 -->
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="tasa1" id="labelForTasa1"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_USD") %></label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180px;" id="tasa1" value="<%=cotizacion.get("tasaDivisa")%>" name="tasa1" />		
			</div>
		</div>
		<div align="right" class="col-md-12"><!-- GP17667 -->
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; "  for="tasa3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_PESO") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180px;" id="tasa3" value="<%=cotizacion.get("tasaUsdPeso")%>" name="tasa3" />		
			</div>
		</div>
			<%
			}else{
		%> <input type="hidden" id="tasa1" name="tasa1" value="<%=cotizacion.get("tasaDivisa")%>" />
		   <input type="hidden" id="tasa3" name="tasa3" value="<%=cotizacion.get("tasaUsdPeso")%>" />
		<%  }%>
			<div align="right" class="col-md-12"><!-- GP17667 -->
				<div class="col-md-5">
					<label style="margin-top: 10px; font-size: 13px; " for="tasa4"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_EQU1") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
				</div>
				<div class="col-md-7" align="left">
					<input type="text" disabled="true" style="font-size: 14px; width: 180px;" id="tasa4" value="<%=cotizacion.get("equivPesos")%>" name="tasa4" />		
				</div>
			</div>
		<br><br>
		<div align="left" class="col-md-12">
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="declinar()" id="btnCantelar"  class="buttonGiroAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUTTON_CANCELAR") %></button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="validarDecimal()"  class="buttonGiro" id="btnCotizar1" disabled> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COTIZAR") %></button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- INC 186 FX CMC 28/02/2019 -->
			<!-- INI INC 133 FX CMC Incompatibilidad IE 03/01/2019 -->
			<%if(indiList.equalsIgnoreCase("NN")){
				if(isOpenModal.equalsIgnoreCase("false")){%>
					<button type="button" onclick="continuar(monto.value)"  id="btnContinuar1" name="btnContinuar1" class="buttonGiroAzul" disabled> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button>
				<%}else{ %>
   					<a href="#openModal">
		        		<button type="button" id="btnContinuar1" name="btnContinuar1" class="buttonGiroAzul" disabled><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button>
		    		</a>
				<%}%>
		    <div id="openModal" class="modalDialog">
		        <div>
		            <a href="#close" title="Close" class="closee2">X</a>
			            <svg class="icon icon-info"><use xlink:href="#icon-info"></use></svg>
			            <br>
			            <h1 class="centertext2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_ATENCION") %></h1>
			            <br>
			            <p class="centertext3"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_OPERACION") %></p>
			            <p class="centertext3"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_OPERACION_2") %></p>
			            <br>
			            <br>
			            <br>
			            <br>
			            <button type="button" onclick="continuar(monto.value)"  id="btnContinuar1" name="btnContinuar1" class="buttonGiroAzull3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button>
			            <br>
			            <br>
			            <a href="#" class="centeraa3"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_CANCELAR_OPE") %></a>
		        </div>
		    </div>
			<%}%>
			<!-- FIN INC 133 FX CMC Incompatibilidad IE 03/01/2019 -->
		</div>
		</div>	
		<!-- INI INC 8 FX CMC - 07/03/2018 -->
		<div class="col-md-4 ocultar msnAyuda" style="margin-left: -211px; margin-top: -9px;">
            <p class="letragris">
                <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INGRESAR_DESCRIPCION") %> <!-- INI INC 120 FX CMC - 22/11/2018 --> 
                <br>
                <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_CARACTERES_ESPECIALES") %>	<!-- INI INC 133 FX 04-01-2019 -->
            </p>
		</div>
		<!-- FIN INC 8 FX CMC - 07/03/2018 -->	
	</div>
		<div id="msjImportante" class="container; col-md-12" style="bottom: -160px; display: none;">
			<div class="row titulo">
	            <div class="col-md-9">
	                <h1 class="sbold tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 1: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_NEW_PASO_1") %></h1>
	            </div>
	        </div>
	        <p class="textSubtitulo"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SUBTITULO_PASO_1_COMISION") %></p></br>
	        
	        <div>
	        		<div class="col-md-12 divAzul2Titulo" >
	        			<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
	        		</div>
	        		<div class="col-md-12 divAzul2" >
	        			<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_1") %></p>
	        		</div>
	        		<div class="col-md-12 divAzul2">
		        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_GMF_1") %> <a id="colorurl" href="https://www.bbva.com.co/" target="_blank" rel="noopener noreferrer"><b style="text-decoration: underline;" id="tooltipGMF2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_GMF_2") %></b></a></p>
						<div class="col-md-4 ocultar msnAyudaDiv" id="msnAyudaDiv2" style="width: 310px; margin-right: 506px; margin-top: -174px;">
							<p class="text12" style="padding: 10px 5px 3px 5px;">
								<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_Q_GMF") %></b>
							<br>
								<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_GMF_DESCRIPCION") %>
							</p>
						</div>
					</div>
					<div class="col-md-12 divAzul2" align="left" >			
	        			<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_RELOJ") %></p>
	        		</div>
	        		<div class="col-md-12 divAzul2" align="left">
	        			<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_OP_FIRME") %></p>
	        		</div>
	        		<div class="col-md-12 divAzul2" align="left">
	        			<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_OP_RECHA") %></p>
	        		</div>
	        		<div class="col-md-12 divAzul2" align="left">
	        			<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_CONF_CONT") %></p>
	        		</div>
	        </div>
			<div class="col-md-12">
			</br>
			<H2><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_RESUMEN_COTIZACION") %></H2>
			</br>
			</br>
				<div align="center" class="col-md-2">
					<div class="col-md-12" align="center">
					    <label align="left" style="font-size: 15px; font-weight: bold;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TIEMPO2")%> </label><br><br>
					    <img id="timer" src="/images/version7/timer_icon.png" width=70px height= 70px/><br><br>
					    <label id="lb_time2" style="font-size: 15px; font-weight: normal;" align="left" > </label> 
					    <label style="font-size: 13px; font-weight: normal;" align="left" > <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_SEGUNDOS")%> </label>
					</div>
				</div>
				<div align="center" class="col-md-10" id="datosOperacion">
					<div class="divGris col-md-12">
						<div class="col-md-3" align="left">
							</br></br>
							<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_EQUI_PESOS")%></p>
							<p id="camp1">$</p>
							<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_COMISION")%></p>
							<p id="camp2">$</p>
						</div>
						<div class="col-md-9" align="left">
							</br></br>
							<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_IVA")%></p>
							<p id="camp3">$</p>
							<p class='text12B margin0' style="margin-bottom: 0px;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_TOTAL_DEB")%></p>
							<p id="camp4">$</p>
						</div>
					</div>
				</div>
				<div align="right" class="col-md-12">
					<button type="button" id="btnDeclinar" name="btnDeclinar" onclick="declinarSegundoContador()"  class="buttonGiroAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUTTON_CANCELAR") %></button> &nbsp;&nbsp;
					<%if(tipoFondoGiro.equalsIgnoreCase("fondoPSE")){%>
					<button type="button" id="negolinea" name="negolinea" style="width: 205px;" onclick="continuarBenef(this, monto.value, selectMoneda.value, selectOpe.value, selectCta.value, descripcion.value, tasa1.value, tasa2.value, tasa3.value, tasa4.value, avanceOpe.value, Nit_Nombre.value, rate.value, totalDebito.value, numTrans.value, equivaPesos.value, comision.value, iva.value)"  class="buttonGiro"  disabled="disabled"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUTTON_CONFIRMAR_CON") %></button> <%-- //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%> <%-- IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
					<%}else{%>
					<button type="button" id="negolinea" name="negolinea" style="width: 205px;" onclick="continuarBenef(this, monto.value, selectMoneda.value, selectOpe.value, selectCta.value, descripcion.value, tasa1.value, tasa2.value, tasa3.value, tasa4.value, avanceOpe.value, Nit_Nombre.value, rate.value, totalDebito.value, numTrans.value, equivaPesos.value, comision.value, iva.value)"  class="buttonGiroAzul"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUTTON_CONFIRMAR_CON") %></button> <%-- //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019 --%> <%-- IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
					<%}%>
				</div>
			</div>	
		</div>			
		</form>
		<!-- INI - L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019 -->
		<script>
	   <%
	    if(cant==1){ %>	
	     if(!$('#empresa').is(':visible')){
	    	 ListarCuentas();  
	     }
		<%
		}
		%>
		<%if(tipoFondoGiro.equalsIgnoreCase("fondoPSE")){%>
		changeTextPSECtaDev(document.getElementById("fondoPSE").value);	
		<%}%>
	  </script>
      <!-- FIN - L. DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019 -->
		<div class="modal" id="modalComun"  ></div>
            <!--end Modal unico -->
	<script src="/js/version7/vendorDivisas.js"></script>
	<script src="/js/version7/pluginsDivisas.js"></script>
	<script src="/js/version7/op_objDivisas.js"></script>
	<script src="/js/version7/mainPagosDivisas.js"></script>
   </body>			   
</html>