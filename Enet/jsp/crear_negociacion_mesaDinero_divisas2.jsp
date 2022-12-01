<!DOCTYPE html>
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    String operatePse = datos.get("operatePse")!=null?(String)datos.get("operatePse"):"";
    String contingencia = (String) datos.get("contingencia");
    if (contingencia.equalsIgnoreCase("ACTIVO") || operatePse.equalsIgnoreCase("NO")){
    	chekTipoBbva="checked";
    	chekTipoPse="";
    }
   
%>
<html lang="es-ES">
  <head>
		<meta charset="ISO-8859-1"/>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/version7/iecomp.js"></script>
		<script src="/js/version7/neg_divisas.js"></script>
		<script src="https:/js/jquery.cookie.js"></script>
		<script type="text/javascript" language="JavaScript" src="/js/version7/utilPibee.js"></script>
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
		<script type="text/javascript" src="/js/version7/script_org.js"></script>
		<script language="JavaScript" src="/js/version7/tiempo.js"></script>
		<link rel=stylesheet type='text/css' href="/estilos/version7/mainPagos.css" />
		<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"/>
		<link rel=stylesheet type='text/css' href="/estilos/version7/newLook.css"/>
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-ui.css" />
		<link rel="stylesheet" type='text/css' href="/estilos/version7/bbva-icon.css" />
		<link rel=stylesheet type='text/css' href="/estilos/version7/tooltipDivisas.css" />
		<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>
         <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
		<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
		<script>
			function changeTextPSECtaDev(pseBBVA){
				var fondoPSE_BBVA= pseBBVA.value;
				if (fondoPSE_BBVA == "fondoPSE"){
					document.getElementById("lbSeleccionCta").textContent ="* "+ document.getElementById("textDevPseCta").value;
					document.getElementById("textCtaPSE").style.display = "inline-block";
        			$('#textCtaPSE').addClass('activeTool');
        			disableContinue();
    				$("#btnContinuar2").hide();
		    		 $("#validarAvanceComp2").show();
				}else{
					document.getElementById("lbSeleccionCta").textContent ="* "+ document.getElementById("textBBVACta").value;
					document.getElementById("textCtaPSE").style.display = "none";
				}
				let iframe= window.parent.document.getElementById('kyop-central-load-area');
			   	iframe.style.height=1100+'px';
			}
		 function declinar(){
			 var declinar2= document.getElementById("decliButt");
				 declinar2.disabled=true;
				 declinar2.classList.remove("grandeAzul");
				 declinar2.classList.add("grandeGrid2");
		     if (confirm('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_DEC_OP") %>')) {
			 if($("#selectOpe").val()=="T2"){
			     window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
			 }
		     }
		 }
		 function continuar(){
			 if($("#selectCuentaOrden").val().trim() ==""){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_SEL_CUENTA_CONTINUAR") %>"); 
			 }else if($("#descripNegociacion").val().trim() ==""){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_CONT_NEG") %>");
			 }else if($("#indAvance").val() =="S"){
			     if($("#validaCta").val() =="N"){
				 alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_CUENTA_NO_PERMITIDA") %>");
			     }else if($("#validaCta").val() =="S"){
			    	 typeof value === "undefined"
			         var cuenta= typeof $("#selectCuentaOrden").val()=== "undefined" ?"@":$("#selectCuentaOrden").val();
			         var monto= typeof $("#monto").val() === "undefined" ?"":$("#monto").val();
			         var pesos=typeof $("#equivPesos2").val()=== "undefined" ?"":$("#equivPesos2").val();
			         var descrip=typeof $("#descripNegociacion").val()=== "undefined" ?"":$("#descripNegociacion").val();
			         var res = cuenta.split("@");
					 var cuenta_s=res[0];
					 var tipoFondoGiro = $('input[name="tipoFondoGiro"]:checked').val();
					 var operatePse = $("#operatePse").val();
					 var businessId = $("#nitVisual").val();
			    	 var parametros = {
			                 "selectCuentaOrden" : cuenta_s,
			                 "monto" : monto,
			                 "equivPesos" : pesos,
			                 "descripNegociacion" : descrip,
			                 "tipoFondoGiro" : tipoFondoGiro,
			                 "operatePse" : operatePse,
			                 "businessId" : businessId
			         };
			         $.ajax({
			                 data:  parametros, 
			                 url:   'OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=validarContinuar', 
			                 type:  'post', //metodo de envio
			                 success:  function (response) { //una vez que el archivo recibe el request lo procesa y lo devuelve
			                        var valido=0;
			                	    var respuesta=response.trim(); 
			                        var res = respuesta.split("@");
			           			    var banderaMonto=res[3];
				           			 if(banderaMonto != null || banderaMonto != ""){
				         				if(banderaMonto=="primera"){
				         					$("#montoInvalido").hide();
				         					disableContinue();
				         				}else if(banderaMonto=="s"){
				         					valido=1;
				         					disableContinue();
				         					$("#montoInvalido").show();
				         				}else if(banderaMonto=="n"){
				         					$("#montoInvalido").hide();
				         				}
				         			 }
				           			var validaDes=res[2];
				       		        if(validaDes =="VD" && validaDes != ""){
				       		          disableContinue();
				       			      $("#messageBoxCM9").show();
				       			      valido=1;
				       		        }
			           			     var validacuent=res[0].substr(res[0].length - 1);
			           			     if(validacuent =="N"){
			           			    	$("#messageBoxULCM3").html(res[1]);
			           				    $("#messageBoxCM3").show();
			           				    valido=1;
			           			     }else if(validacuent =="S"){
			           				    $("#messageBoxCM3").hide();
			           			     }
			           			    var validaPse=res[4];
			           			    if(validaPse != ""){
			           			    	$("#datosOperacion").css("display", "block");
		           			    		var datosPse=validaPse.split("/");
		           			    		$("#numTrans").val(datosPse[0]);
		           						$("#tipoFondoGiro").val(datosPse[1]);
		           						if(datosPse[2]==""||datosPse[3]==""||datosPse[4]==""||
		           								datosPse[5]==""||datosPse[6]==""){
		           							$("#negolinea").prop('disabled', true);
		           						}else{
		           							$("#comision").val(datosPse[2]);
			           						$("#iva").val(datosPse[3]);
			           						$("#totalDebito").val(datosPse[4]);
			           						$("#rate").val(datosPse[5]);
			           						$("#equivaPesos").val(datosPse[6]);
			           						$("#camp1").text("$ "+datosPse[7]);
			           						$("#camp2").text("$ "+datosPse[8]);
			           						$("#camp3").text("$ "+datosPse[9]);
			           						$("#camp4").text("$ "+datosPse[10]);	
		           						}
			           			    }
			           			    if(valido==0){
			           			      window.location.href="#top";
			           				  $("#tiempo").val("21");
			           				  $("#container").hide();
			           				  $("#msjImportante").show();
			           				  singleton.crearTimer();
			           			    }
			                 }
			         });
			     }
			 }else if($("#indAvance").val() =="N"){
			     alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_NUM_AVANCE") %>");
			 }else{
			     alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ALERTA_CONSULTAR_NUM_AVANCE") %>");
			 }
		}
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
		 function inicio(){
			 
		     $("#msjImportante").hide();
		     $("#messageBoxCM5").hide();
		     $("#btnContinuar1").hide();
		     $("#container").show();
		     $("#validarAvanceComp2").hide();
			 var isValid=true;
		     var str1 = $("#validaCta").val();
		     var res1 = str1.substring(0, 1);
		     $("#validaCta").val(res1);
		     if($("#validaCta").val() =="N"){
			 $("#messageBoxCM3").show();
		     }else if($("#validaCta").val() =="S"){
			 $("#messageBoxCM3").hide();
		     }
		     var str = $("#indAvance").val();
		     var res = str.substring(0, 1);
	
		     $("#indAvance").val(res);
		     if(res =="N"){
			 $("#messageBoxCM4").show();
			 $("#messageBoxCM5").hide();
			 $("#messageBoxCM6").hide();	
             $("#messageBoxCM7").hide();
		     }else if(res =="S"){
			 $("#messageBoxCM4").hide();
			 $("#messageBoxCM5").show()
			 $("#messageBoxCM6").hide();
             $("#messageBoxCM7").hide();
		     }else if(res =="C"){
				$("#messageBoxCM4").show();
		     }else if(res =="C" && str=="CNE0019"){
				 $("#messageBoxCM4").hide();
				 $("#messageBoxCM5").hide();
				 $("#messageBoxCM6").show();
				 isValid=false;
		     }
			 else if(res =="C" && str=="CNE0030"){
				 $("#messageBoxCM4").hide();
				 $("#messageBoxCM5").hide();
				 $("#messageBoxCM10").show();
		     }
			 else if(res =="C" && str=="CNE0020"){
				 $("#messageBoxCM4").hide();
				 $("#messageBoxCM5").hide();
				 $("#messageBoxCM7").show();
		     }
		     if(res =="N" || $("#validaCta").val() =="N" || $("#indAvance").val()=="CNE0020"){
                 $("#messageBoxCM7").hide();
             } else if(res =="S" && $("#validaCta").val() =="S"){
			 $("#btnContinuar1").show();
		     }
		     if(res =="N" || $("#validaCta").val() =="N" || $("#indAvance").val()=="CNE0019" ){ 
			 $("#btnContinuar1").hide();
		     }else if(res =="S" && $("#validaCta").val() =="S"){
			 $("#btnContinuar1").show();
		     }
			 if (!isValid){
				$("#btnContinuar2").hide();	
				$("#validarAvanceComp2").show(); 
			 }
		 }
		 function numberFormat(numero){
			 var montoTemp=$("#MontoTemporal").val();
			 if(montoTemp==""){
				 $("#MontoTemporal").val(numero); 
			 }
		     disableContinue();
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
			     // Si tiene decimales, se lo anadimos al numero una vez forateado con 
			     // los separadores de miles
			     if(numero.indexOf(".")>=0){
				 resultado+=numero.substring(numero.indexOf("."));
			     }
			     $("#monto").val(resultado);
			     if($("#E_MONTO").val() != "" && $("#E_MONTO").val() != "null"){
			         if($("#monto").val()==$("#MontoTemporal").val()){
			        	 $("#btnContinuar2").show();
			    		 $("#validarAvanceComp2").hide(); 
			         }else if($("#E_MONTO").val()==$("#monto").val() ){
			        	 $("#btnContinuar2").show();
			    		 $("#validarAvanceComp2").hide();
			    	 }else{
			    		 $("#btnContinuar2").hide();
			    		 $("#validarAvanceComp2").show();
			    	 }
			     }
			     if(numero[0]=="-")
				 {
				     // Devolvemos el valor anadiendo al inicio el signo negativo
				     return "-"+resultado;
				 }else{
				     return resultado;
				 }
			 }
		 function numberFormat2(id){
			 var numero=$("#"+id).val();
		     disableContinue();
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
			     // Si tiene decimales, se lo anadimos al numero una vez forateado con 
			     // los separadores de miles
			     if(numero.indexOf(".")>=0){
				 resultado+=numero.substring(numero.indexOf("."));
			     }
			     $("#"+id).val(resultado);
			 }	
		</script>
		<script>
		 $(document).ready(function() {
			$("#tooltipGMF").hover(function()			{
			$("#msnAyudaDiv").css("display", "inline-block");      
			}, function(){
			$("#msnAyudaDiv").css("display", "none");
			});
			 numberFormat2("tasa3");
			 numberFormat2("tasa2");
			 numberFormat2("tasa1");
			 numberFormat2("E_MONTO");
			 numberFormat2("monto");
			 numberFormat2("MontoTemporal");
			 var nit=$("#SALIDA_NIT").val();
			 $("#nitVisual").val(nit.substring(2));
			$("#montoInvalido").hide();
		     $("#tooltipAyuda").hover(function()			{
	    		 $(".msnAyuda").css("display", "block");      
		     }, function(){
			 $(".msnAyuda").css("display", "none");
		     });
			$("#tooltipAyudaMonto").hover(function()			{
			$(".msnAyudaMonto").css("display", "block");      
			}, function(){
			$(".msnAyudaMonto").css("display", "none");
			});
			$("#tooltipGMF2").hover(function(){
				$("#msnAyudaDiv2").css("display", "inline-block");      
				}, function(){
				$("#msnAyudaDiv2").css("display", "none");
			});
		     inicio();
		     var valida=document.getElementById('validaDes').value;
		     if(valida =="VD" && valida != ""){
		     disableContinue();
			 $("#messageBoxCM9").show();
		     }
			var banderaMonto = document.getElementById('montoBandera').value;
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
		     ajustar();
		     $('document').ready(function () {
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
		     });
		 });
	var funTimer = function () {
	var _ref = _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee() {
		var contador, estaVisible;
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
						console.log(contador);
						_context.next = 7;
						return sleep(1000);
					case 7:
						if (contador == 1) {
							singleton.eliminarTimer();
							estaVisible = $("#container").css("display");
							if (null != estaVisible && undefined != estaVisible && estaVisible == "none" && ejeVolver) {
								document.getElementById("validarAvanceComp").click();
							}
						}
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
					case "end":
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
function sleep(ms) {
	return new Promise(function (resolve) {
		return setTimeout(resolve, ms);
	});
}
var flag = false;
var ejeVolver = true;
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
		     function continuarBenef(a, b, c, d, e, f){
			 montoX = "#monto";
			 numA =  "#avanceOpe";
			 var monto = $(montoX).val();
			 monto.replace(",","");
			 document.getElementById("negolinea").disabled="true";
			 document.getElementById("btnDeclinar").disabled="true";
			 ejeVolver=false; 
			 var elementBTC= document.getElementById("negolinea");
			     elementBTC.classList.remove("grandeAzul");
			     elementBTC.classList.add("grandeGrid2");
			 if(null!=e && undefined!=e){
				var resultado=e.toString();
				while (resultado.length<10) {
					resultado = '0'+resultado;
				}
			    e =resultado;
			 }			 
			 var res = c.split("@");
			 var oficina=res[1].trim()==""?"0073":res[1];
			 var cuenta=res[0];
			 var nit=$("#SALIDA_NIT").val();
			tasaDivisaX = "#tasaDivisaPeso2";
			equivPesosX = "#equivPesos2";
			var tasaDivisaPeso = $(tasaDivisaX).val();
			var equivPesos = $(equivPesosX).val();
			var equivaPesos = $("#equivaPesos").val();
			equivaPesos = equivaPesos.replaceAll(",","");
			window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=selecBeneficiarios&monto=" + monto + "&selectMoneda="+ a + "&selectOpe=" + b + "&selectCuentaOrden=" + cuenta + "&descripNegociacion=" + d + "&avanceOpe=" + e + "&tasaDivisa=0.00&tasaDivisaPeso="+tasaDivisaPeso+"&tasaUsdPeso=0.00&equivPesos="+equivPesos+"&oficinaGestoraOP="+oficina+"&NitUnido="+nit+"&tipoFondoGiro="+$("#tipoFondoGiro").val()+"&rate="+$("#rate").val()+"&totalDebito="+$("#totalDebito").val()+"&numTrans="+$("#numTrans").val()+"&equivaPesos="+equivaPesos+"&comision="+$("#comision").val()+"&iva="+$("#iva").val()+"&businessId="+nit.replaceAll("-","")+"&amountEquiv="+equivPesos;
		     }	
		     function ajustar(){
			 com.bbva.kyop.controller.CoreController.resizeMainContent(300);
		     }
			 function disableContinue() {
			     $('#btnContinuar1').hide();
			      var element2= document.getElementById("validarAvanceComp");
			      var avanceOpe = $('#avanceOpe').val();
		            if ((avanceOpe != null)&&(avanceOpe != '')) {
		            	element2.disabled=false;
		            	element2.classList.remove("grandeGrid2");
		            	element2.classList.add("grandeAzul");
		            } else {
		            	element2.disabled=true;
		            	element2.classList.remove("grandeAzul");
		            	element2.classList.add("grandeGrid2");
		            }
				}
			function validarDecimal() {
				var validate = /^(\d{1,3},)*\d{1,3}(\.\d+)?$/;
				var monto = document.getElementById('monto').value;
				if (!(validate.test(monto))) {
					$("#messageBoxCM5").hide();
					$("#messageBoxCM8").show();
					return false;   
				} else {
					return true;
				}
			}
			function ValidarAvance() {
				var vDisplay=$("#ValidaDisplay").val(); 
				var avance=$("#avanceOpe").val();
				var elementBTC= document.getElementById("validarAvanceComp");
					elementBTC.disabled=true;
					elementBTC.classList.remove("grandeAzul");
					elementBTC.classList.add("grandeGrid2");
				window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarMesaDinero&ValidaAvance=SI&avanceOpe="+avance+"&ValidaDisplay="+vDisplay ;
			}
			
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
	<input type="hidden" id="selectOpe" name="selectOpe" value="<%=datos.get("selectOpe")%>" />
	<input type="hidden" id="validaCta" name="validaCta" value="<%=datos.get("validaCta")%>" />
	<input type="hidden" id="indAvance" name="indAvance" value="<%=datos.get("indAvance")%>" />
	<input type="hidden" id="validaDes"  value="<%=(String)datos.get("validaDes")%>" />
	<input type="hidden" id="montoBandera" name="montoBandera" value="<%=(String)datos.get("montoBandera")%>" />
	<input type="hidden" id="tasaDivisaPeso2" name="tasaDivisa2" value="<%=(String)datos.get("tasaDivisaPeso2")%>"/>
	<input type="hidden" id="equivPesos2" name="equivPesos2" value="<%=(String)datos.get("equivPesos2")%>"/>
    <input type="hidden" id="textDevPseCta" name="textDevPseCta" value="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA_DEV_PSE") %>">
	<input type="hidden" id="textBBVACta" name="textBBVACta" value="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA_DEBITAR") %>">
	<input type="hidden" id="operatePse" name="operatePse" value="<%=operatePse%>" />
	<input type="hidden" id="numTrans" name="numTrans" value="" />
	<input type="hidden" id="tipoFondoGiro" name="tipoFondoGiro" value="<%=tipoFondoGiro%>" />
	<input type="hidden" id="comision" name="comision" value="" />
	<input type="hidden" id="iva" name="iva" value="" />
	<input type="hidden" id="totalDebito" name="totalDebito" value="" />
	<input type="hidden" id="rate" name="rate" value="" />
	<input type="hidden" id="equivaPesos" name="equivaPesos" value="" />
   <body >
	    <%
	     String display="block";
	    String v="";
	    if(datos.get("S_ValidaDisplay")!=null){
	    	v=datos.get("S_ValidaDisplay").toString().trim();
	    	if(v.equalsIgnoreCase("Primero")){
	    		v="N";
	    	}else if(v.equalsIgnoreCase("Segundo")){
	    		v="S";
	    	}else if(v.equalsIgnoreCase("S")){
	    		v="S";
	    	}
	    	System.out.println("JSP: Display: "+v);
	    }
	     display=v.equalsIgnoreCase("N") || v.equals("") ?"none":"block"; 
	    %>
   <form method="post" role="form" method="POST" onsubmit="return validarDecimal()" action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=negociarMesaDinero" id="formDivisas">
	    <input type="hidden" id="ValidaDisplay" name="ValidaDisplay" value="<%=(String)datos.get("S_ValidaDisplay")%>"/>
	    <input type="hidden" id="SALIDA_NIT" name="SALIDA_NIT" value="<%=(String)datos.get("S_SALIDA_NIT")%>"/>
	    <input type="hidden" id="NOMBRE_CLIENTE" name="NOMBRE_CLIENTE" value="<%=(String)datos.get("S_NOMBRE_CLIENTE")%>"/>
	    <input type="hidden" id="DIVISA_COP" name="DIVISA_COP" value="<%=(String)datos.get("S_DIVISA_COP")%>"/>
	    <input type="hidden" id="DIVISA_DOLAR" name="DIVISA_DOLAR" value="<%=(String)datos.get("S_DIVISA_DOLAR")%>"/>
	    <input type="hidden" id="DIVISA_PESO" name="DIVISA_PESO" value="<%=(String)datos.get("S_DIVISA_PESO")%>"/>
	    <input type="hidden" id="DIVISA_MONEDA" name="DIVISA_MONEDA" value="<%=(String)datos.get("S_DIVISA_MONEDA")%>"/>
	    <input type="hidden" id="E_MONTO" name="E_MONTO" value="<%=(String)datos.get("S_MONTO")%>"/>
	    <input type="hidden" id="MontoTemporal" name="MontoTemporal" value="<%=(String)datos.get("monto")%>"/>	    
	<%
	int timerIN = Integer.parseInt("20");				  
	%>
	<input type="hidden" id="tiempo" name="tiempo" value="<%=timerIN%>"/>
	<input type="hidden" id="indiceGiro" name="indiceGiro" value=""/>
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
				  %>
    <div id="container" class="container; col-md-12">
        <div class="row titulo">
            <div class="col-md-8" style="margin-left: 15px;">
                <h1 style="font-weight: bold;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 1: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_EXTE_MESA_DINE") %></h1>
                <p class="subtituloPrincipal_2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INGRESAR_INF_GIRO") %></p> 
            </div>
            <div class="col-md-3" style=" float: right; padding-left: 75px;">
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
			<div class="col-md-12">
				<div class="divAzul">
					<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_1") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_2") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_7") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_3") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_5") %><a class="textCheckAzul" href="https://www.bbva.com.co/personas/informacion-practica/tasas-y-tarifas.html " target="_blank" rel="noopener noreferrer"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_6") %></a></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_8") %></p>
					<% String numavance=(String)datos.get("avanceOpe");
						if (numavance!= null && numavance!= "" && v=="S"){%>
							<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_1")+" "+numavance+" "+CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_9") %></p>
						<%}else{
							System.out.println("entro else numavance"+numavance);
						}
					%>					
			</div>
			</div>
			  <%if (numavance== null || numavance == ""){%>
			  <div class="sup">
                <div class="col-md-12">
                   <%--  <p>* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_INGRESE_DATOS_OP") %></p>  --%>
                </div>
            </div>
            <%}%>
                <div class="col-md-12" id="messageBoxCM4" style="display: none">
                	<div class="alerta bordeWarning clearfix" style="display: block">
                		<div class="interior1">
                        	<img src="/images/version7/iconoAlertaWarning.png">
                         </div>
                         <div class="interior3">
                         	<img src="/images/version7/cerrarAlerta.png">
                         </div>
                         <div class="interior2">
                         	<ul id="messageBoxULNOK">
                             	<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_1") %> <b><%=datos.get("avanceOpe")%></b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_2") %> <b><%=selectMoneda%></b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_3") %>
                            </ul>
                         </div>
                	</div>
					    	   
				     </div>
				   <div class="col-md-12" id="messageBoxCM3" style="display: none">
				   		<div class="alerta bordeWarning clearfix"  style="display: block">
							<div class="interior1">
								<img src="/images/version7/iconoAlertaWarning.png"> 
						    </div>
							<div id="cerrarmsj6" class="interior3">
						        <img src="/images/version7/cerrarAlertaDiv.png"> 
						    </div>
						     <div class="interior2">
						          <ul id="messageBoxULCM3">Fondos Insuficientes</ul>
						     </div>
						</div>
				</div>
            
			    <div class="col-md-3" align="center">
			    
			    </div>
				<div class="col-md-7" style="overflow-x: auto, overflow-y:auto">
				<div align="right" class="col-md-12" >
					<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="avanceOpe">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_AVANCE") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
					<div class="col-md-7" align="left">
				<input type="text" required="true" style="font-size: 14px; width: 180;" id="avanceOpe" value="<%=datos.get("avanceOpe")%>" name="avanceOpe" onkeyup="disableContinue()" onChange="disableContinue()" onkeypress="return permite(event, 'only_num')"  <%=display.equalsIgnoreCase("block")?"readonly":"" %>/> 
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>">
			<div  class="col-md-5">
				<label style="margin:10px 0px 0px 0px;font-size: 13px; " > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NIT") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" style="font-size: 14px; width: 152px;margin:10px 10px 10px 0px" readonly id="nitVisual" />
			<label style="margin: 10px -422px 0px 10px;font-size: 13px;width:400px;text-align:left"> <b><%=datos.get("S_NOMBRE_CLIENTE") %></b></label>
			</div>
			
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>">
			<div class="col-md-5">
				<label style="margin-top: 10px;font-size: 13px;padding-right: 9px;" for="fondosOrigin"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_ORIGEN_FONDOS") %></label>
			</div>
			<div class="col-md-7" align="left">
			<% 
			if(contingencia.equalsIgnoreCase("ACTIVO")){
			%>
				<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
			<%
			}else{
				if(operatePse.equalsIgnoreCase("SI")){ 
			%>
				<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
				<input type="radio" name="tipoFondoGiro" value="fondoPSE" id="fondoPSE"   <%=chekTipoPse %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA_PSE") %> <img src="/images/version7/pseDiv.png" class="pseItem">
			<%
				}else{
			%>
				<input type="radio" name="tipoFondoGiro" value="fondoBBVA" id="fondoBBVA" <%=chekTipoBbva %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA") %></br>
				<input type="radio" name="tipoFondoGiro" value="fondoPSE" id="fondoPSE"  disabled <%=chekTipoPse %> onchange="changeTextPSECtaDev(this);"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_BBVA_PSE") %> <img src="/images/version7/pseDiv.png" class="pseItem">
			<%
				}
			}%>
			</div>
		</div>
		<div align="right" class="col-md-12" style="margin-top: 10px;display: <%=display%>"> 
			<div class="col-md-5">
				<label align="left" style="margin-top: 10px; font-size: 13px;" for="selectCuentaOrden" id="lbSeleccionCta">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<select id="selectCuentaOrden" name="selectCuentaOrden" required="true" onChange="disableContinue()" style ="width: 180; margin-top: 6px; font-size: 11px;" >
					<option value=""> </option>
					<%
							Vector listaCuentas = (Vector) datos.get("listaCuentas");
							Enumeration enumListaCuentas = listaCuentas.elements();
							int cntRegC = 1;
							int n = listaCuentas.size();
							if(listaCuentas.size()>0){
								while (enumListaCuentas.hasMoreElements()) {
									Hashtable klistC = (Hashtable) enumListaCuentas.nextElement();
									String numCuenta = (String) klistC.get("clave_asunto");
									String oficina = (String) klistC.get("oficina");
									String asunto =numCuenta + "  " +  (String)klistC.get("divisa");
									String envio=numCuenta+"@"+oficina;
								%>	
									<option value='<%=envio%>' <%if(envio.equals( (String) datos.get("selectCuentaOrden") )){%> selected <%}%> ><%=asunto%></option>
								<%
									cntRegC ++;
					}}%>
				</select>
				<div class="col-md-4 ocultar msnAyudaDiv" id="msnAyudaDiv" style="width: 100%; max-width: 350px; margin-right: -161px; margin-top: -198px; margin-left: 55%; display: none; line-height: 1.8;">
					<p class="text12" style="padding: 10px 5px 3px 5px;">
						<b class="GMF_init"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_Q_GMF")%></b>
					<br><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_GMF_DESCRIPCION_2")%></p>
				</div>
				<p class="text10 activeTool" id="textCtaPSE" style="display: none;" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_1")%><b class="text10" id="tooltipGMF" style="text-decoration-line: underline; font-size: 10px !important;"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_2")%> </b> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_DESC_PSE_CTA_3") %></p>
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>"> 
			<div class="col-md-4 ocultar msnAyuda" style="width: 180px;top: 0px;float: right;margin-right: -181px;margin-top: -15px;text-align: left;">
				<p class="letragris">
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INGRESAR_DESCRIPCION") %>
				<br>
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_CARACTERES_ESPECIALES") %>
				</p>
			</div>
			<div class="col-md-5">
				<label align="left" style="margin-top: 10px; font-size: 13px; height: 20px;" for="descripNegociacion" >* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESCRIPCION_OP") %> </label>
				<label align="left" style="font-size: 11px;padding-right: 50px;" for="descripcion">
						 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MAX_35_CARACTERES") %>
					 </label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" required="true" style="font-size: 14px; width: 151;" value="<%=datos.get("descripNegociacion")%>" maxlength="35" id="descripNegociacion" name="descripNegociacion" onkeypress="return permite(event, 'text')" onChange="disableContinue()"/>		
			<a class="tooltipAyuda" id="tooltipAyuda" href="#">
					<img src="/images/ayudaEmergenteDerecha.png">
			</a>
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>">
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="selectMoneda">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONEDA") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<select id="selectMoneda" align="left" required="true" name="selectMoneda" onChange="disableContinue()" style ="width: 180; margin-top: 6px; font-size: 13px;" >
					<option  value="<%=datos.get("S_DIVISA_MONEDA")%>" ><%=CatalogoMultidioma.obtenerLiteral("PNET_MONEDAS_DIV",idioma,datos.get("S_DIVISA_MONEDA").toString().trim())%></option><%--SEGMENTADOR_TASA_V2_CMC_OSNEIDER_05_04_2020 --%>
		</select>
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>"> 
			<div class="col-md-4 ocultar msnAyudaMonto" style="width: 140px;top: -8px;float: right;margin-right: -160px;margin-top: -50px; text-align: left;">
				<p class="letragris">
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CAMPO_DECIMALES") %>
				<br>
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DELIMITADOR_DECIMALES") %>
				</p>
			</div>
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="monto">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" required="true" style="font-size: 14px; width: 152;" id="monto" value="<%=datos.get("monto")%>" name="monto" onChange="numberFormat(this.value)" onkeypress="return permite(event, 'montos')" onkeyup="validarDecimales(this.value)"/>
			<a class="tooltipAyudaMonto" id="tooltipAyudaMonto" href="#">
				<img src="/images/ayudaEmergenteDerecha.png">
			</a>
			<label align="right" for="montoInvalido" id="montoInvalido" style="margin-top: 1px;font-size: 11px;width: 155;padding-right: 1px;margin-right: 110px;background-color: white;">
						 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MONTO_NO_VALIDO") %>
			</label>
			</div>
			
		</div>
		<div align="right" class="col-md-12" style="display: <%=( display.equals("block") && !datos.get("S_DIVISA_MONEDA").equals("USD"))?"block" :"none" %>" >
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="tasa2" id="labelForTasa2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> <%=datos.get("S_DIVISA_MONEDA")%> / USD &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180;" id="tasa2" value="<%=datos.get("S_DIVISA_COP") %>" >
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=( display.equals("block") && !datos.get("S_DIVISA_MONEDA").equals("USD"))?"block":"none" %>">
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="tasa1" id="labelForTasa1"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA") %> <%=datos.get("S_DIVISA_MONEDA")%> / COP &nbsp; &nbsp; &nbsp; &nbsp;</label>
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180;" id="tasa1" value="<%=datos.get("S_DIVISA_DOLAR") %>" >	
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>">
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="tasa3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_PESO") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>	
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180;" id="tasa3" value="<%=datos.get("S_DIVISA_PESO") %>" >
			</div>
		</div>
		<div align="right" class="col-md-12" style="display: <%=display%>">
			<div class="col-md-5">
				<label style="margin-top: 10px; font-size: 13px; " for="tasa3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TASA_EQU1") %> &nbsp; &nbsp; &nbsp; &nbsp;</label>	
			</div>
			<div class="col-md-7" align="left">
				<input type="text" disabled="true" style="font-size: 14px; width: 180;" id="tasa3" value="<%=(String)datos.get("equivPesos2")%>" >
			</div>
		</div>
	</div>
		<div class="col-md-4">
			</div>
		<br><br>
		<div align="center" class="col-md-12">
			<br>
		     <% 
			String validaBtn=display.equalsIgnoreCase("none")?"block":"none";
		     System.out.println("Display1: "+validaBtn);
		     System.out.println("JSP: Display: "+display);
			%>
			<center>
		    <table style="width: 300px;">
		    <tr>
		       <td><button type="button" style="width: 100;" onclick="declinar()" id="decliButt" class="grandeAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DECLINAR") %></button></td>
		       <td><button id="validarAvanceComp2" type="submit" class="grandeAzul"  style="float:left;  display: none"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VALIDAR_AVANCE") %></button></td>
		       <td><button id="validarAvanceComp" type="#" class="grandeAzul" onclick="ValidarAvance()" style="float:left;  display: <%=validaBtn%>"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VALIDAR_AVANCE") %></button></td>
			   <td><button type="button" onclick="continuar()"  name="btnContinuar1" id="btnContinuar2" class="grandeAzul" style="width: 100; display: <%=display%>"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button></td>    
		    </tr>
		    </table> 
		     </center>
		</div>	
		</div>
		<div id="msjImportante" class="container; col-md-12" style="display: none;">
		<div class="col-md-8">
                <h1 style="font-weight: bold;" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 1: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIRO_EXTE_MESA_DINE") %></h1>
                <p class="subtituloPrincipal_2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMI_COSTO_SUBTITULO") %></p> 
            </div>
			<div class="col-md-12">
				<div class="divAzul">
					<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_ITEM_1") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_GMF_1") %> <a id="colorurl2" href="https://www.bbva.com.co/" target="_blank" rel="noopener noreferrer"><b style="text-decoration: underline;" id="tooltipGMF2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_GMF_2") %></b></a></p>
					<div class="col-md-4 ocultar msnAyudaDiv" id="msnAyudaDiv2" style="width: 310px; margin-left: 350px; margin-top: -174px; display: none;">
						<p class="text12" style="padding: 10px 5px 3px 5px;">
							<b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_Q_GMF") %></b>
						<br>
							<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TEXT_GMF_DESCRIPCION") %>
						</p>
					</div>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_RELOJ") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_OP_FIRME") %></p>
	        		        <p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_OP_RECHA") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO_1_CONF_CONT") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_1") %> <%=datos.get("avanceOpe")%> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_AVANCE_5") %></p>
			</div>
			<br>
			<div class="col-md-12" style="margin-bottom: 30px" >
                  	<h2 class="tituloNegri"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_RESUMEN_OPERACION")%></h2>
            </div>
			<div class="col-md-3" align="center">
			    <label align="left" style="font-size: 15px; font-weight: bold;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_TIEMPO2")%> </label><br><br>
			    <img id="timer" src="/images/version7/timer_icon.png" width=70px height= 70px/><br><br>
			    <label id="lb_time2" style="font-size: 15px; font-weight: normal;" align="left" > </label> 
			    <label style="font-size: 13px; font-weight: normal;" align="left" > <%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_SEGUNDOS")%> </label>
			    
			</div>
			<div align="center" class="col-md-9" id="datosOperacion" style="display:none">
				<div class="divGris col-md-12">
					<div class="col-md-3" align="left">
						<br></br>
						<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_EQUI_PESOS")%></p>
						<p id="camp1">$</p>
						<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_COMISION")%></p>
						<p id="camp2">$</p>
					</div>
					<div class="col-md-9" align="left">
						<br></br>
						<p class='text12B margin0'><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_IVA")%></p>
						<p id="camp3">$</p>
						<p class='text12B margin0' style="margin-bottom: 0px;"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_PASO_1_TOTAL_DEB")%></p>
						<p id="camp4">$</p>
					</div>
				</div>
			</div>
			<div align="right" class="col-md-12">
				<button type="button" id="btnDeclinar" name="btnDeclinar" style="width: 100;" onclick="inicio()"  class="grandeAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DECLINAR") %></button> &nbsp;&nbsp;
				<button type="button" id="negolinea" name="negolinea" style="width: 205px;" onclick="continuarBenef(selectMoneda.value, selectOpe.value, selectCuentaOrden.value, descripNegociacion.value, avanceOpe.value)"  class="grandeAzul"  > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BUTTON_CONFIRMAR_CON") %></button>
			</div>
		</div>
		</form>
		<div class="modal" id="modalComun"  ></div>
	<script src="/js/version7/vendorDivisas.js"></script>
	<script src="/js/version7/pluginsDivisas.js"></script>
	<script src="/js/version7/op_objDivisas.js"></script>
	<script src="/js/version7/mainPagosDivisas.js"></script>
   </body>
</html>				   