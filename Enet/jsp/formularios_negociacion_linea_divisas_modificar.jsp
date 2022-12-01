<!DOCTYPE html>
<%@ include file="includecbtf.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.ibm.dse.base.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<html id="ordenesPago" lang="es-ES">
  <head>
	<meta charset="utf-8">
    <title>bbva</title>
    <meta name="description">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="/estilos/version7/mainPagosDivisas.css"/>
	
	<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"/>
		<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
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
	<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->
    <script src="/js/version7/modernizr.js"></script>
	<!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
    <!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
    <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
    <!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
	<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
	<script language="Javascript" src="/js/version7/banner.js"></script>
	<% if((idioma!=null)&&(idioma.equalsIgnoreCase("en"))){ %>
	<script language="Javascript" src="/js/version7/utilidades_en.js" type="text/javascript"> </script>
	<% }else{ %>
	<script language="Javascript" src="/js/version7/utilidades.js" type="text/javascript"> </script>
	<% } %>
		<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
	<style>
		.display
		{
			display: none;
		}
		.space
		{
			padding-bottom: 12px;
		}
		.btnAdj
		{
			font-size: 12px !important;
			color: black !important;
			font-weight: bold !important;
		}			
		/* INI INC 26 FX CMC - 13/03/2018 */
		.alerta_Fallo {
			border: solid 1px;
			border-color: #D8B6C7;
			background: #F7E9E9;
			font-family: Arial;
			color: #666;
			font-size: 11px;
			height: 35px;
			border-radius: 5px;
			width: 99%;
			margin-top: 10px;
			margin-left: 0px;
			margin-right: 20px;
		}
		.liError {
			padding-top: 1px;
			padding-bottom: 1px;
		}
		/* FIN INC 26 FX CMC - 09/02/2018 */		
	</style>
	<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->
<%
	List<String> Numeral = new ArrayList<String>();
	String tmpN = ""; 
	String numTMP = "";
	String options1 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";//INI INC 44 03/05/2018
	String options2 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options3 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options4 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options5 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options6 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options7 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options8 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options9 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";
	String options10 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>";//FIN INC 44 03/05/2018
	String formularioTMP = "";
	String documentosTMP = "";
	String codNumeralTMP = "";
	String codNumeralTMP2 = "";//INC 44 03/05/2018
	String descripTMP = "";
	String tipoTmp="";
	String    tipoLiteral="";    
	String tipoFondoGiro = (String)datos.get("tipoFondoGiro");      
    if (tipoFondoGiro.equals("fondoPSE")){
       tipoLiteral="PNET_OpDETAILPSE";          
    }else{
       tipoLiteral="PNET_OpDETAIL";
    }        
	
		int n = 0;
			for (int i = 1 ; i<=228; i++) {
					tmpN = (String) CatalogoMultidioma.obtenerLiteral(tipoLiteral,idioma,"NC_" + i);
					try{
					String[] parts = tmpN.split(";");
					formularioTMP = parts[0];
					codNumeralTMP = parts[1];
		codNumeralTMP2 = codNumeralTMP.substring(2,codNumeralTMP.length());//INC 43 FX CMC 03-05-2018
					documentosTMP = parts[2];
		//INI INC 19 FX CMC 13-03-2018
					descripTMP = parts[4];//INC 118 FX CMC 23-11-2018
       		if (descripTMP.length() > 70) {
              	    descripTMP = descripTMP.substring(0,67) + "...";						
              	}
					tipoTmp = codNumeralTMP.substring(0,1);
					n = Integer.parseInt(formularioTMP);
					 if(tipoTmp.equals("E")){
						switch(n){
			case 1://INI INC 44 FX 03/05/2018
			options1 = options1 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+ descripTMP + " </option>";
							break;
							case 2:
			options2 = options2 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
							break;
							case 3:
			options3 = options3 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
							break;
							case 4:
			options4 = options4 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
							break;
							case 5:
			options5 = options5 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
							break;
							}
						}else{
							switch(n){
								case 1:
			options6 = options6 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
								break;
								case 2:
			options7 = options7 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
								break;
								case 3:
			options8 = options8 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
								break;
								case 4:
			options9 = options9 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
								break;
								case 5:
			options10 = options10 + "<option value='" + tmpN + "'>" + codNumeralTMP2 + " - "+  descripTMP + " </option>";
			break;//FIN INC 44 FX 03/05/2018
			//INI INC 19 FX CMC 13-03-2018
							}
						 }
					}catch(Exception e){
					}
			}
	//INI INC 44 03/05/2018
	if (options1.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options1 = options1 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options2.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options2 = options2 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options3.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options3 = options3 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options4.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options4 = options4 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options5.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options5 = options5 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options6.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options6 = options6 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options7.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options7 = options7 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options8.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options8 = options8 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options9.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options9 = options9 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options10.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") + " </option>")) {
		options10 = options10 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	//FIN INC 44 03/05/2018
			//INI INC 19 FX CMC 13-03-2018
%>
	<input type="hidden" name="options1" id="options1" value="<%=options1%>">
	<input type="hidden" name="options2" id="options2" value="<%=options2%>">
	<input type="hidden" name="options3" id="options3" value="<%=options3%>">
	<input type="hidden" name="options4" id="options4" value="<%=options4%>">
	<input type="hidden" name="options5" id="options5" value="<%=options5%>">
	<input type="hidden" name="options6" id="options6" value="<%=options6%>">
	<input type="hidden" name="options7" id="options7" value="<%=options7%>">
	<input type="hidden" name="options8" id="options8" value="<%=options8%>">
	<input type="hidden" name="options9" id="options9" value="<%=options9%>">
	<input type="hidden" name="options10" id="options10" value="<%=options10%>">
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
	 $(document).ready(function() {
	     inicio();
	     ajustar();
		//CMC - Incidencia 169 - 22/01/19 - Inicio
		document.getElementById("btnEnviar").disabled = false;
		//CMC - Incidencia 169 - 22/01/19 - Fin
	 });
	 /* INI Alcance Divisas CMC Archivos 29/01/2018 */
	 var trazaExtAndSize="";
	 var array = ["", "", "","", "", "","", "", "",""];
	 function tipoExt(name,ind)
	 {			
	     var subirFichero="";		
	     subirFichero = document.getElementById("subirFichero"+ind);			
	     var fileElement = subirFichero.files[0];
	     var bs64 = subirFichero;
	     var extensiones_permitidas = [".gif", ".jpg", ".jpeg", ".jpe", ".jfif", ".tif", ".tiff", ".bmp", ".pdf",".PDG", ".GIF", ".JPG", ".JPEG", ".JPE", ".JFIF", ".TIF", ".TIFF", ".BMP", ".PDF"]; //INC 25 FX CMC - 13/03/2018
	     var ext = "";
	     var permitida = false;
	     if (fileElement.name.lastIndexOf(".") > 0)
		 {			
		     ext = fileElement.name.substring(fileElement.name.lastIndexOf("."), fileElement.name.length);
		 }
	     for (var i = 0; i < extensiones_permitidas.length; i++)
		 {
		     if (extensiones_permitidas[i] == ext)
			 { 
			     permitida = true;
			     break; 
			 } 
		 }
	     if (!permitida)
		 { 
		     alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMPRUEBE_EXT") %> " + ".GIF .JPG .JPEG .JPE .JFIF .TIF .TIFF .BMP .PDF");//INC 44 FX 02/05/2018
		     subirFichero.value = "";		 
		 }
	     else
		 {	
		     loadImageFileAsURL(name,ind,fileElement, bs64, ext);
		     trazaExtAndSize += ext + "@";
		     array[ind - 1] = trazaExtAndSize;
		     console.log(trazaExtAndSize);
		     console.log(array);
		     return 1;
		 }	
	 }
	 function loadImageFileAsURL(name, ind, fileElement, bs64, ext)
	 {
	     var nfic = fileElement.name.replace(/^.*[\\\/]/, '');			
	     var tfic = fileElement.size;
	     var tfic = tfic / 1024 ;
	     var img64 ="";
	     if(tfic >= 1100)
		 {		
		     <!-- INI incidencia 26 FX CMC - 13/03/2018 -->
		     //alert("El archivo supera el tamaño de 1.1 MB.");
		     $("#alertSize").removeClass("display");
		     <!-- FIN incidencia 26 FX CMC - 02/02/2018 -->
		     bs64.value = "";		
		 }
	     else
		 {
		     <!-- INI incidencia 26 FX CMC - 13/03/2018 -->
		     $("#alertSize").addClass("display");
		     <!-- FIN incidencia 26 FX CMC - 13/03/2018 -->
		     if (bs64.files.length > 0)
			 {
			     var fileToLoad = bs64.files[0];
			     var fileReader = new FileReader();
			     fileReader.onload = function(fileLoadedEvent)
			     {
				 img64 =fileLoadedEvent.target.result;
				 img64 = img64.replace("data:", "")
				 $("#file"+ind).attr('value',img64);
			     };
			     fileReader.readAsDataURL(fileToLoad);
			 }
		 }
	     var tficRounded1 = Math.round(tfic);
	     trazaExtAndSize = tficRounded1+"-";
	     bas64X = "#file"+ind; 
	     var bas64 = $(bas64X).val();
	     var nfic1= nfic;
	     var tfic1= tfic;
	     var ext1= ext;		
	 }
	 $(document).ready(function()
	     {
		 var indice = 2;
		 var label, resultado;
		 $("#addBtnFiles").click(function()
		     {
			 indice++;		
			 $("#section" + indice).removeClass("display");			
			 if (indice == 10)	  	
			 {
			     $("#addBtnFiles").addClass("display");
			 }
		     });
	     });
	 function cargarArchivos()
		     {
			 var traza = "";
			 for (var i=0 ; i<array.length; i++) 
			     {
				 if (array[i] == "")
				     {
					 array[i] = "@";
				     }
				 traza += array[i];
			     }
			 $("#trazaExtAndSize").attr("value",traza);		
		     }
	 function functionResize()
	 {
	     var iframe = window.parent.document.getElementById('kyop-central-load-area');
	     $(iframe).height(1000); 
	 }
	 /* FIN Alcance Divisas CMC Archivos 29/01/2018 */
	 function numeralCamb(n){
	     var numc = n;
	     var codigoNumeral = "";
	     var docsNumeral = "";
	     var descNumeral = "";
	     var formularioAsociado = "";
	     var parts = numc.split(";");
	     formularioAsociado = parts[0];
	     codigoNumeral = parts[1];
	     docsNumeral = parts[2];
	     descNumeral = parts[3];
	     if (descNumeral == "" || descNumeral === null || descNumeral == undefined) {//INI INC 19 FX CMC 08/03/2018
		 descNumeral = '<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") %>';
	     }//FIN INC 19 FX CMC 13/03/2018
	     document.getElementById('descripcionNumeralC').innerHTML = descNumeral;
	     if (docsNumeral!=""){
		 document.getElementById('documentosNumeralC').innerHTML = docsNumeral;
	     }else{
		 document.getElementById('documentosNumeralC').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ANEXOS_OP") %>";//INC 44 FX 02/05/2018
	     }
	     //$("#NumeralC").val(codigoNumeral);
	     $("#NumeralC").val(codigoNumeral);
	     $("#descNumeral").val(descNumeral);	
	     ajustar();
	 }
	 function inicio(){	
	     //INI INC 19 FX CMC 13/03/2018 Eliminado if
	     ocultar();
	     $("#msjImportante").hide();
	     $("#container").show();				
	     $('html,body').scrollTop(0);
	 }
	 function formularioChange(id){	
	     //alert(id);
	     var optionsJS = "";
		 //INI incidencia 133 FX CMC - 27/12/2018
	     var selectOpera = $("#selectOpe").val();
         //FIN incidencia 133 FX CMC - 27/12/2018		 
	     if(selectOpera=="T1"||selectOpera=="T2"){	
		 if (id==0){
		     optionsJS = '<option value="" selected> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %>  </option>';//INC 44 FX 02/05/2018
		 }else if(id==1){
		     optionsJS = $("#options1").val();
		 }else if(id==2){
		     optionsJS = $("#options2").val();
		 }else if(id==3){
		     optionsJS = $("#options3").val();
		 }else if(id==4){
		     optionsJS = $("#options4").val();
		 }else if(id==5){
		     optionsJS = $("#options5").val();
		 }
	     }else if (selectOpera=="H1"||selectOpera=="H2"){
		 if (id==0){
		     optionsJS = '<option value="" selected> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %>  </option>';//INC 44 FX 02/05/2018
		 }else if(id==1){
		     optionsJS = $("#options6").val();
		 }else if(id==2){
		     optionsJS = $("#options7").val();
		 }else if(id==3){
		     optionsJS = $("#options8").val();
		 }else if(id==4){
		     optionsJS = $("#options9").val();
		 }else if(id==5){
		     optionsJS = $("#options10").val();
		 }
	     }
	     document.getElementById("selectNumCambiario").innerHTML = optionsJS;
	     document.getElementById('descripcionNumeralC').innerHTML = " ";
	     ajustar();
	 }
	 function  ocultar(){	
	     //INI incidencia 133 FX CMC - 27/12/2018
	     var selectOpera = $("#selectOpe").val();
         //FIN incidencia 133 FX CMC - 27/12/2018		 
	     if(selectOpera=="T1"||selectOpera=="T2"){
		 $("#hacia").show();
		 $("#desde").hide();
	     }else if (selectOpera=="H1"||selectOpera=="H2"){
		 $("#desde").show();
		 $("#hacia").hide();
	     }
	 }
/*INI incidencia 212 FX CMC 12/04/2019		 
    function finalizar(){		
			window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizar";			
	}
FIN incidencia 212 FX CMC 12/04/2019*/
	 function volver(){ 
	     selectCuentaOrdenX = "#selectCuentaOrden";
		 //INI INC 133 FX 17/01/2019
	     var selectCuentaOrden = $('#selectCuentaOrdenX').val();		 
	     //FIN INC 133 FX 17/01/2019
	     window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=mostrarAtras&selectCuentaOrden="+ selectCuentaOrden;
	 }
	 function declinar(){
	     if($("#selectOpe").val()=="T1" || $("#selectOpe").val()=="T2"){
		 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
	     }else if ($("#selectOpe").val()=="H1" || $("#selectOpe").val()=="H2"){
		 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosDesde";
	     }
	 }
	 function ajustar(){
	     com.bbva.kyop.controller.CoreController.resizeMainContent(100);
	 }		
	 //INI INC 44 FX 03/05/2018
	 /*INI incidencia 212 FX CMC 10/04/2019
	 function finalizarUrl() {
	 	return window.location.href = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizar";
	 }FIN incidencia 212 FX CMC 10/04/2019*/
	 //INI INC 149 FX 26/12/2018
	 function validarNumeral() {
		 var selectCuentaOrdenmodificar = $('#selectCuentaOrden').val();
		 //INI INC 133 FX 17/01/2019
		 var selectFomulario = $('#Formulario').val();
		 //FIN INC 133 FX 17/01/2019
		 var selectNumeral = $('#selectNumCambiario').val();
		 var elementBTC3= document.getElementById("btnEnviar");
		       elementBTC3.disabled=true;
		       elementBTC3.classList.remove("grandeAzul");
		       elementBTC3.classList.add("grandeGrid2");
		 if(selectCuentaOrdenmodificar == "" || selectCuentaOrdenmodificar == null)
		{		
			alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PORFAVOR_SEL_CUENTA_ABONAR") %>");	
			 elementBTC3.disabled=false;
		     elementBTC3.classList.remove("grandeGrid2");
		     elementBTC3.classList.add("grandeAzul");
			return false;
		}else if(selectFomulario == "" || selectFomulario == null)
		{		
			alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_VALIDO") %>");	
			 elementBTC3.disabled=false;
		     elementBTC3.classList.remove("grandeGrid2");
		     elementBTC3.classList.add("grandeAzul");
			return false;
		} else if(selectNumeral == "" || selectNumeral == null)
		{
			alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_NUMERAL") %>");
			 elementBTC3.disabled=false;
		     elementBTC3.classList.remove("grandeGrid2");
		     elementBTC3.classList.add("grandeAzul");
			return false;
		}else if ($('#selectNumCambiario option:selected').val() == 'No Aplica Numeral') 
		{
			alert('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_VALIDO") %>');//INC 42 FX 23/04/2018
			 elementBTC3.disabled=false;
		     elementBTC3.classList.remove("grandeGrid2");
		     elementBTC3.classList.add("grandeAzul");
			return false;
		}else {
			//CMC - Incidencia 169 - 22/01/19 - Inicio
			document.getElementById("btnEnviar").disabled = true;
	     	var formulario = document.getElementById("modoCreacionManual2");			
			cargarArchivos();
			//INI incidencia 212 FX CMC 10/04/2019
	     	//formulario.action = finalizarUrl();
			 formulario.action = "OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizar";
			formulario.submit();
			//FIN incidencia 212 FX CMC 10/04/2019
            return true;
			//CMC - Incidencia 169 - 22/01/19 - Fin
	     }
	 }
 	 //FIN INC 44 FX 03/05/2018
    //FIN INC 149 FX 26/12/2018
	</script>
  </head>
	<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
   <body onLoad="setTimeout(functionResize, 500);">
   <!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
	<!--CONTAINER -->
    <form id="modoCreacionManual2" method="POST" role="form"  class="form-horizontal"><!--Incidencia 212 FX CMC 04/10/2019--><!--INCIDENCIA 149 FX CMC 26/12/2018-->
	<div id="container" class="container">
        <div id="hacia" class="titulo">
            <div class="col-md-8">
                <h1 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 3: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_ADJUNTE_DOC") %></h1><!-- FIN INC 44 FX 02/05/2018 -->
            	<p class="subtituloPrincipal_2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SOPORTE_SUBTITULO") %> </p>
            </div>
            <div class="col-md-4">
                <ul id="progreso">
                    <li class="letragris"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 3 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 4</b>
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
                        <img src="/images/version7/barraProgresoVaciaRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>
		<div id="desde"  class="titulo2">
            <div class="col-md-8">
                <h1><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 2: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_ADJUNTE_DOC") %></h1><!-- INC 44 FX 02/05/2018 -->
            	<p class="subtituloPrincipal_2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_ADJUNTE_DOC_SOPORT_GIRO") %></p>
            </div>
            <div class="col-md-4">
                <ul id="progreso">
                    <li class="letragris"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 2 <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DE") %> 3</b>
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVerdeLeft_20x25.png">
                    </li>
					<li>
                        <img src="/images/version7/barraProgresoVerdeCenter_20x25.png">
                    </li>
                    <li>
                        <img src="/images/version7/barraProgresoVaciaRight_20x25.png">
                    </li>
                </ul>
            </div>
        </div>
		<div class="col-md-12" style="margin-bottom: 30px;">
				<div class="divAzul">
					<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_ARCHIVO_TAMANO") %></p>
					<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_ADJUNTAR_DOCUMENTO") %></p>
			</div>
		</div>
		<!-- INI incidencia 26 FX CMC - 13/03/2018 -->
		<div id="alertSize" class="col-md-12 display" style="padding-left: 0px; padding-right: 0px;">
			<div class="alerta_Fallo interior2" style="margin-left: 0px;">
				<div style="float:right; margin-right:15px; margin-top:3px;">
					<img id="iconoCerrar" src="/images/version7/cerrarAlertaDiv.png" onclick="cerrarMensajeAlerta();" style="cursor:pointer;">
				</div>				
				<div style="float:left; margin-left:15px; margin-right:12px;">
					<img id="iconoMensaje" src="/images/version7/iconoAlertaWarning.png">										
				</div>
				<ul id="" class="textoAlerta" style="margin-top: 5px;">
					<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ARCHIVO_SUPERA_TAM") %>				
				</ul>
			</div>
		</div>
		<!-- FIN incidencia 26 FX CMC - 13/03/2018 -->
			<input type="hidden" id="NumeralC" name="NumeralC" value=""/>
			<input type="hidden" id="descNumeral" name="descNumeral" value=""/>	
			<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
			<input type="hidden" id="file1" name="file1" value=""/>	
			<input type="hidden" id="file2" name="file2" value=""/>	
			<input type="hidden" id="file3" name="file3" value=""/>
			<input type="hidden" id="file4" name="file4" value=""/>
			<input type="hidden" id="file5" name="file5" value=""/>
			<input type="hidden" id="file6" name="file6" value=""/>
			<input type="hidden" id="file7" name="file7" value=""/>
			<input type="hidden" id="file8" name="file8" value=""/>
			<input type="hidden" id="file9" name="file9" value=""/>
			<input type="hidden" id="file10" name="file10" value=""/>
			<input type="hidden" id="trazaExtAndSize" name="trazaExtAndSize" value=""/>
			<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->
			<input type="hidden" id="avanceOpe" name="avanceOpe" value="<%=(String)datos.get("avanceOpe")%>" />	
			<input type="hidden" id="selectOpe" name="selectOpe" value="<%=(String)datos.get("selectOpe")%>" />
			<input type="hidden" id="docBenefi" name="docBenefi" value="<%=(String)datos.get("docBenefi")%>" />
			<input type="hidden" id="descripNegociacion" name="descripNegociacion" value="<%=(String)datos.get("descripNegociacion")%>" />
			<input type="hidden" id="monto" name="monto" value="<%=(String)datos.get("monto")%>" />
			<input type="hidden" id="selectMoneda" name="selectMoneda" value="<%=(String)datos.get("selectMoneda")%>" />
			<input type="hidden" id="referenciaOpe" name="referenciaOpe" value="<%=(String)datos.get("referenciaOpe")%>" />
			<input type="hidden" id="equivPesos" name="equivPesos" value="<%=datos.get("equivPesos")%>" />
			<input type="hidden" id="tasaDivisa" name="tasaDivisa" value="<%=datos.get("tasaDivisa")%>" />
			<input type="hidden" id="tasaUsdPeso" name="tasaUsdPeso" value="<%=datos.get("tasaUsdPeso")%>" />
			<input type="hidden" id="tasaDivisaPeso" name="tasaDivisaPeso" value="<%=datos.get("tasaDivisaPeso")%>" />
			<%  String selectCuentaOrden = (String)datos.get("selectCuentaOrden");
				String selectOpe = (String)datos.get("selectOpe");
				if (selectOpe.equals("T1") || selectOpe.equals("T2")){
			%>
			<input type="hidden" id="selectCuentaOrden" name="selectCuentaOrden" value="<%=(String)datos.get("selectCuentaOrden")%>" />
			<input type="hidden" id="tipoFondoGiro" name="tipoFondoGiro" value="<%=datos.get("tipoFondoGiro")%>" />
			
			<%
			      /* String tipoFondoGiro = (String)datos.get("tipoFondoGiro"); */
			      String showInfo = "";
				  if(tipoFondoGiro.equals("fondoPSE")){
					  showInfo = "ocultarInfo";
				  }
			  %>
			  <div class="sup <%= showInfo %> " id="ctaHacia">
				<div class="col-md-12">
				<!-- INC 90 FX CMC 07/09/2018-->
                 <label align="left" style="margin-top: 10px; font-size: 13px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_A") %> <% if (((String)datos.get("selectOpe")).equals("H1") || ((String)datos.get("selectOpe")).equals("H2")){ %><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ABONAR") %><% } else { %><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBITAR") %><% } %>&nbsp; &nbsp; &nbsp; &nbsp;</label>
		         <label style ="width: 180; margin-top: 6px; font-size: 11px;"><%=selectCuentaOrden%></label>
				<!-- FIN 90 FX CMC 07/09/2018-->
				</div>
			</div>
			<%
				}
				if (selectOpe.equals("H1") || selectOpe.equals("H2")){
			%>
			
			<div class="form-group">
                <label for="selectCuentaOrden" class="col-sm-3 control-label"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CUENTA_ABONAR") %></label><!-- INC 43 FX 17/05/2018 -->
                <div class="col-sm-9">
                    <select id="selectCuentaOrden" required="true" name="selectCuentaOrden" style="width: 197px;"><!-- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 -->
                        <option value=""> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") %> </option><!-- FIN INC 44 FX 02/05/2018 -->
						<%
							Vector listaCuentas = (Vector) datos.get("listaCuentas");
							Enumeration enumListaCuentas = listaCuentas.elements();
							int cntRegC = 1;
							if(listaCuentas.size()>0){
								while (enumListaCuentas.hasMoreElements()) {
									Hashtable klistC = (Hashtable) enumListaCuentas.nextElement();
									String numCuenta = (String) klistC.get("clave_asunto");
									String asunto =numCuenta + "  " +  (String)klistC.get("divisa");
								%>	
									<option value='<%=numCuenta%>' <%=datos.get("selectCuentaOrden").equals(numCuenta)?"selected":"style='display:none;'" %> ><%=asunto%></option><!-- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019-->
								<%
									cntRegC ++;
					}}%>
                    </select>
                </div>
            </div>
			<%
				}
			%>
			<br>
			<br>
			<%if (tipoFondoGiro.equals("fondoPSE")){%>
	           <div class="col-md-12" style="margin-bottom: 20px;" >
                  	<h2 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MEDIO_PAGO_PSE") %></h2>
                   </div>
            <br>
            <br>
            <%} %>
			<div class="form-group">
			    <!-- INI incidencia 133 FX CMC - 17/01/2019 -->
                <label for="Formulario" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_OP") %></label><!-- INC 44 FX 02/05/2018 -->
                <div class="col-sm-9">
                    <select id="Formulario" required="true" name="Formulario" onChange="formularioChange($('#Formulario').val())" style="width: 197px;">
			    <!-- FIN incidencia 133 FX CMC - 17/01/2019 -->
                        <option value=""> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") %> </option><!-- FIN INC 44 FX 02/05/2018 -->
                        <option value="1"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_IMPORTACION_BIENES") %></option><!-- FIN INC 44 FX 02/05/2018 -->
                        <option value="2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_EXPORTACION_BIENES") %></option><!-- FIN INC 44 FX 02/05/2018 -->
						<option value="3"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ENDEUDAMIENTO_EXT") %></option>
						<option value="4"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_INVERSIONES_INTERNACIONALES") %></option>
						<option value="5"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SERVICIOS_CONCEPTOS") %></option>
                    </select>
                </div>
            </div>
			<div class="form-group sup">
                <label for="selectNumCambiario" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_NUMERAL_CAMBIARIO") %> </label>
                <div class="col-md-6">
                    <select id="selectNumCambiario" required="true" name="selectNumCambiario" onChange="numeralCamb(selectNumCambiario.value)" class="1required_group_tipo_orden" style="width: 197px;">
                        <option value=""><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPC") %> </option><!-- FIN INC 44 FX 02/05/2018 -->
					</select>
                </div>
            </div>
			<div class="form-group">
                <label for="descNumCambiario" class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESCRIPCION_NUM_CAMBIARIO") %> </label><!-- FIN INC 44 FX 02/05/2018 -->
                <div class="col-sm-9">
                    <label id="descripcionNumeralC" name="descripcionNumeralC" style="padding-top: 7px; width: 600px;" align="left" > </label>
                </div>
            </div>
			<div class="row resumen">
				<ul><li><b style="vertical-align: top;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_OP_ADOC") %>  </b>
				<br>
				<label id="documentosNumeralC" name="documentosNumeralC" style="font-size: 11px; width: 600px !important" align="left" > </label></li></ul><!-- FIN INC 44 FX 02/05/2018 -->
				<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
				<div class="form-group space">
					<label class="col-sm-3 control-label"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 1 </label>
					<input type="file" id="subirFichero1" onchange="tipoExt(this.value,1)" name="subirFichero1"/>
				</div>								
				<div class="form-group space" align="left">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 2 </label>
					<input type="file" id="subirFichero2" onchange="tipoExt(this.value,2)" name="subirFichero2"/>
				</div>				
				<div class="form-group space" align="left" id="section3">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 3 </label>
					<input type="file"  id="subirFichero3" onchange="tipoExt(this.value,3)" name="subirFichero3"/>
				</div>		
				<div class="form-group space" align="left" id="section4">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 4 </label>
					<input type="file"  id="subirFichero4" onchange="tipoExt(this.value,4)" name="subirFichero4"/>
				</div>		
				<div class="form-group space" align="left" id="section5">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 5 </label>
					<input type="file"  id="subirFichero5" onchange="tipoExt(this.value,5)" name="subirFichero5"/>	
				</div>	
				<div class="form-group space" align="left" id="section6">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 6 </label>
					<input type="file"  id="subirFichero6" onchange="tipoExt(this.value,6)" name="subirFichero6"/>
				</div>		
				<div class="form-group space" align="left" id="section7">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 7 </label>
					<input type="file"  id="subirFichero7" onchange="tipoExt(this.value,7)" name="subirFichero7"/><!-- INC FX 02 CMC -13/03/2018 -->
				</div>		
				<div class="form-group space" align="left" id="section8">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 8 </label>
					<input type="file"  id="subirFichero8" onchange="tipoExt(this.value,8)" name="subirFichero8"/>
				</div>		
				<div class="form-group space" align="left" id="section9">
					<label class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 9 </label>
					<input type="file"  id="subirFichero9" onchange="tipoExt(this.value,9)" name="subirFichero9"/>
				</div>		
				<div class="form-group space" align="left" id="section10">
					<label class="col-sm-3 control-label" ><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTO") %> 10 </label>
					<input type="file"  id="subirFichero10" onchange="tipoExt(this.value,10)" name="subirFichero10"/><!-- INC FX 02 CMC - 13/03/2018 -->
				</div>
				<!-- <a id="addBtnFiles" name="addBtnFiles" class="btnAdj"> Ajuntar otro archivo </a> -->
				<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->
				<a class="tooltipAyuda" href="#">
                        <img src="/images/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class=" ocultar">
                        <p class="letragris" style="text-transform: uppercase;"> <!-- INC 25 FX CMC 13/03/2018 -->
						<!-- INI incidencia 25 FX CMC - 13/03/2018 -->
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO_FORMATOS_VALIDOS") %> GIF,JPG,JPEG,JPE,JFIF,TIF,TIFF,BMP,PDF.
						<!-- FIN incidencia 25 FX CMC - 13/03/2018 -->
                        </p>
                    </div>
			</div>
			<div class="row sup">
				<div class="col-md-12">
                    <p><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NOTA_TAM_ARHIVO") %></p>
                </div>
            </div>
			<hr>
			<div class="botoneraCreacion">
           <div class="col-md-6 col-xs-6">
                    <ul>
                       <li><a onclick="declinar()"><< <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DECLINAR") %></a><!-- INC 44 FX 02/05/2018-->
						<a class="tooltipAyuda" href="#">
                        <img src="/images/version7/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class="tooltipFlechaIzquierda ocultar">
                        <p class="letragris">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VERIFIQUE_DATOS_FORM") %>
                        </p>
                    </div></li>
                    </ul>
                </div>
			<div align="right" class="col-md-6 col-xs-6" >
	               <button type="button" class="grandeAzul"  style="width: 200px; margin-top: -80px; " id="btnEnviar"  onclick="return validarNumeral()"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GUARDAR_CONTINUAR") %></button><!--INC 44 FX 02/05/2018-->
			</div>
         </div>
		 </div>
		</form>
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
   </body>
</html>				   