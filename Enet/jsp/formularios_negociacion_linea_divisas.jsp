<!DOCTYPE html>
<%@ include file="includecbtf.jsp"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<html id="ordenesPago" lang="es-ES">
  <head>
	<meta charset="utf-8">
    <title>bbva</title>
    <meta name="description">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="/estilos/version7/mainPagosDivisas.css"/>
	<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"><!-- GP17667 -->
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
	
	<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>
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
		/* INI INC 26 FX CMC - 09/02/2018 */
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
	//INI INC 19 FX CMC 08/03/2018
	String options1 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options2 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options3 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options4 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options5 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options6 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options7 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options8 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options9 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	String options10 = "<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>";
	//FIN INC 19 FX CMC 08/03/2018
		String formularioTMP = "";
		String documentosTMP = "";
		String codNumeralTMP = "";
		String descripTMP = "";
		String tipoTmp="";
                String codNumeralTMP2 = "";//INC 19 FX CMC 13-03-2018
        String    tipoLiteral="";    
        String tipoGiro=(String)datos.get("tipoFondoGiro");      
        if (tipoGiro.equals("fondoPSE")){
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
		codNumeralTMP2 = codNumeralTMP.substring(2,codNumeralTMP.length());//INC 19 FX CMC 03-04-2018
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
							case 1:
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
								break;
			//INI INC 19 FX CMC 13-03-2018
							}
						 }
					}catch(Exception e){
					}
			}
	if (options1.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options1 = options1 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options2.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options2 = options2 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options3.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options3 = options3 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options4.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options4 = options4 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options5.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options5 = options5 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options6.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options6 = options6 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options7.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options7 = options7 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options8.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options8 = options8 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options9.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options9 = options9 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
	if (options10.equals("<option value=''> " + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") + " </option>")) {
		options10 = options10 + "<option value='No Aplica Numeral'>" + CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") + "</option>";
	}
			//INI INC 19 FX CMC 08-03-2018
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
    	var extensiones_permitidas = [".gif", ".jpg", ".jpeg", ".jpe", ".jfif", ".tif", ".tiff", ".bmp", ".pdf",".PDG", ".GIF", ".JPG", ".JPEG", ".JPE", ".JFIF", ".TIF", ".TIFF", ".BMP", ".PDF"]; //INC 25 FX CMC - 21/02/2018
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
	     alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_COMPRUEBE_EXT") %> " + ".GIF .JPG .JPEG .JPE .JFIF .TIF .TIFF .BMP .PDF");
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
			<!-- INI incidencia 26 FX CMC - 02/02/2018 -->
			//alert("El archivo supera el tamaño de 1.1 MB.");
			$("#alertSize").removeClass("display");
			<!-- FIN incidencia 26 FX CMC - 02/02/2018 -->
			bs64.value = "";		
		}
		else
		{
			<!-- INI incidencia 26 FX CMC - 02/02/2018 -->
			$("#alertSize").addClass("display");
			<!-- FIN incidencia 26 FX CMC - 02/02/2018 -->
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
	function cargaArchivo (){
		var elementBTC5= document.getElementById("btnEnviar");
	        elementBTC5.disabled=true;
	        elementBTC5.classList.remove("buttonGiroAzul");
	        elementBTC5.classList.add("buttonGiro");
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
		if (parts[3] == undefined || parts[3] == null || parts[3] == "") {//INI INC 42 FX CMC 25/04/2018
			descNumeral = '<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") %>';
		} else {
			descNumeral = parts[3];
		}//FIN INC 42 FX CMC 25/04/2018
		document.getElementById('descripcionNumeralC').innerHTML = descNumeral;
     if (docsNumeral != "" && docsNumeral !== undefined && docsNumeral !== null){//INC 19 FX CMC 21/03/2018
			document.getElementById('documentosNumeralC').innerHTML = docsNumeral;
			}else{
				document.getElementById('documentosNumeralC').innerHTML = "<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ANEXOS_OP") %>";
			}
		//$("#NumeralC").val(codigoNumeral);
		$("#NumeralC").val(codigoNumeral);
		$("#descNumeral").val(descNumeral);
		ajustar();	
	}
	function inicio(){	
	 	//INI INC 19 FX CMC 07/03/2018 Eliminado if
			ocultar();
			$("#msjImportante").hide();
			$("#container").show();				
			$('html,body').scrollTop(0);
	}
	function formularioChange(id){	
		//alert(id);
		var optionsJS = "";
		//INI incidencia 133 FX CMC - 17/01/2019
		var selectOpera = $('#selectOpe').val();
        //FIN incidencia 133 FX CMC - 17/01/2019		
		if(selectOpera=="T1"||selectOpera=="T2"){	
			if (id==0){
				optionsJS = '<option value="" selected> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %>  </option>';
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
				optionsJS = '<option value="" selected><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %>  </option>';
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
	//INI incidencia 149 FX CMC 24/12/2018	 
	function continuar(){
		var selectFomulario = $('#Formulario').val();
		var selectNumeral = $('#selectNumCambiario').val();
		var elementBTC4= document.getElementById("buttonConti");
		elementBTC4.disabled=true;
        elementBTC4.classList.remove("buttonGiroAzul");
        elementBTC4.classList.add("buttonGiro");
		if(selectFomulario == "" || selectFomulario == null)
		{		
			alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_VALIDO") %>");	
			elementBTC4.disabled=false;
			elementBTC4.classList.remove("buttonGiro");
			elementBTC4.classList.add("buttonGiroAzul");
			return false;
		}else if(selectNumeral == "" || selectNumeral == null)
		{
			alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DEBE_SEL_NUMERAL") %>");
			elementBTC4.disabled=false;
			elementBTC4.classList.remove("buttonGiro");
			elementBTC4.classList.add("buttonGiroAzul");
			return false;
		}else if ($('#selectNumCambiario option:selected').val() == '<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NO_APLICA_NUMERAL") %>') 
		{
			alert('<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_VALIDO") %>');//INC 42 FX 23/04/2018
			elementBTC4.disabled=false;
			elementBTC4.classList.remove("buttonGiro");
			elementBTC4.classList.add("buttonGiroAzul");
			return false;
		}else
		{
			window.location.href="#top";
			$("#container").hide();
			$("#msjImportante").show();		
			return true;	
		}
	}
	//FIN incidencia 149 FX CMC 24/12/2018
	function  ocultar(){
        //INI incidencia 133 FX CMC - 17/01/2019		
		var selectOpera = $("#selectOpe").val();
       //FIN incidencia 133 FX CMC - 17/01/2019		
		if(selectOpera=="T1"||selectOpera=="T2"){
			$("#hacia").show();
			$("#desde").hide();
			$("#H").hide();
			$("#T").show();
			$("#H2").hide();
			$("#T2").show();
		}else if (selectOpera=="H1"||selectOpera=="H2"){
			$("#desde").show();
			$("#hacia").hide();
			$("#H").show();
			$("#T").hide();
			$("#H2").show();
			$("#T2").hide();
		}
	}
	 /*INI incidencia 212 FX CMC 10/04/2019
	 function finalizar(){		
	     window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizar";			
	 }
	 FIN incidencia 212 FX CMC 10/04/2019*/
	function volver(){ 
	     //ELIMINA IF INC 49 FX CMC 28/02/2018
		selectCuentaOrdenX = "#selectCuentaOrden";
		var selectCuentaOrden = $(selectCuentaOrdenX).val();		 
		 window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=mostrarAtras&selectCuentaOrden="+ selectCuentaOrden;
	}
	function ajustar(){
				com.bbva.kyop.controller.CoreController.resizeMainContent(100);
			}
	//INI incidencia 122 FX CMC 03/12/2018	 
	function volverCreacion(){
		var declinar2= document.getElementById("H2");
			declinar2.disabled=true;
			declinar2.classList.remove("buttonGiroAzul");
			declinar2.classList.add("buttonGiro");
	
		if($("#selectOpe").val()=="T1" || $("#selectOpe").val()=="T2" || $("#selectOpe").val()=="T")
		{
			window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia";
	    }else if ($("#selectOpe").val()=="H1" || $("#selectOpe").val()=="H2" || $("#selectOpe").val()=="H")
		{
			window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosDesde";
	    }
	}
	//INI incidencia 122 FX CMC 03/12/2018
	//INI incidencia 149 FX CMC 24/12/2018
	function clicSubmit()
	{
		document.getElementById("btnEnviar").disabled = true;
		return true;
	}
	//INI incidencia 149 FX CMC 24/12/2018
  </script>
  </head>
	<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
   <body onLoad="setTimeout(functionResize, 500);">
   <!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
	<!--CONTAINER -->
    <form id="modoCreacionManual2" method="POST" role="form"  class="form-horizontal" action="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=finalizar_operaciones_negociacion_op&accion=finalizar" onsubmit="return clicSubmit();"><!--Incidencia 149 FX CMC 24/12/2018-->
	<div id="container" class="container">
        <div id="hacia" class="titulo">
            <div class="col-md-8" style="margin-left: -10px;">
                <h1 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 3: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_ADJUNTE_DOC") %></h1>
            	<p> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SOPORTE_SUBTITULO") %> </p>
            </div>
            <div class="col-md-4" style="margin-left: 10px;">
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
                <h1><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PASO") %> 2: <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO_ADJUNTE_DOC") %></h1>
                
                <p> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LISTADO_PASO_3") %> </p>
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
        
        <div class="divAzull3">
        		<p class="tituloNotaDiv"><img src="/images/version7/infDiv.png" class="imgInf"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TITULO_NOTA") %></p>
        		<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_ARCHIVO_TAMANO") %></p>
				<p class="textSubtitulo"><img src="/images/version7/itemDiv.png" class="imgItem"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_ADJUNTAR_DOCUMENTO") %></p>
        </div>
        <br/>
		<!-- INI incidencia 26 FX CMC - 02/02/2018 -->
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
		<!-- FIN incidencia 26 FX CMC - 02/02/2018 -->
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
			<input type="hidden" id="selectCuentaOrden" name="selectCuentaOrden" value="<%=(String)datos.get("selectCuentaOrden")%>" />
			<input type="hidden" id="docBenefi" name="docBenefi" value="<%=(String)datos.get("docBenefi")%>" />
			<input type="hidden" id="descripNegociacion" name="descripNegociacion" value="<%=(String)datos.get("descripNegociacion")%>" />
			<input type="hidden" id="monto" name="monto" value="<%=(String)datos.get("monto")%>" />
			<input type="hidden" id="selectMoneda" name="selectMoneda" value="<%=(String)datos.get("selectMoneda")%>" />
			<input type="hidden" id="referenciaOpe" name="referenciaOpe" value="<%=(String)datos.get("referenciaOpe")%>" />
			<input type="hidden" id="equivPesos" name="equivPesos" value="<%=datos.get("equivPesos")%>" />
			<input type="hidden" id="tasaDivisa" name="tasaDivisa" value="<%=datos.get("tasaDivisa")%>" />
			<input type="hidden" id="tasaUsdPeso" name="tasaUsdPeso" value="<%=datos.get("tasaUsdPeso")%>" />
			<input type="hidden" id="tasaDivisaPeso" name="tasaDivisaPeso" value="<%=datos.get("tasaDivisaPeso")%>" />
			<input type="hidden" id="tipoFondoGiro" name="tipoFondoGiro" value="<%=datos.get("tipoFondoGiro")%>" />
			<input type="hidden" id="rate" name="rate" value="<%=(String)datos.get("rate")%>" />
			<input type="hidden" id="totalDebito" name="totalDebito" value="<%=(String)datos.get("totalDebito")%>" />
			<input type="hidden" id="numTrans" name="numTrans" value="<%=(String)datos.get("numTrans")%>" />
			<input type="hidden" id="equivaPesos" name="equivaPesos" value="<%=datos.get("equivaPesos")%>">
			<input type="hidden" id="comision" name="comision" value="<%=datos.get("comision")%>">
			<input type="hidden" id="iva" name="iva" value="<%=datos.get("iva")%>">
			<!-- INI incidencia 26 FX CMC - 02/02/2018 -->
			<div class="interior2 display" id="alertSize">
                <ul id="messageBoxULCM1"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ARCHIVO_SUPERA_TAM") %></ul>
            </div>
			<!-- FIN incidencia 26 FX CMC - 02/02/2018 -->
			<%  String selectCuentaOrden = (String)datos.get("selectCuentaOrden");
			    /* String tipoGiro=(String)datos.get("tipoFondoGiro"); */
			   if(!tipoGiro.equals("fondoPSE")){
			%>
			    <div class="sup">
				<div class="col-md-12">
                 <p><b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CUENTA_DEBITAR_OP") %>  </b> <%=selectCuentaOrden%></p>
				</div>
			</div>
			<%}else{%>
	           <div class="col-md-12" style="margin-bottom: 20px;" >
                  	<h2 class="tituloNegri"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MEDIO_PAGO_PSE") %></h2>
                   </div>
             <%}%>
            <br>
            <br>
			<div class="form-group">
                <label for="Formulario" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_CONCEPTO") %> </label>
                <div class="col-sm-9">
                    <select id="Formulario" name="Formulario" onChange="formularioChange(Formulario.value)" style="width: 197px;">
                        <option value=""> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %> </option>
                        <option value="1"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_IMPORTACION_BIENES") %></option>
                        <option value="2"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_EXPORTACION_BIENES") %></option>
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
                        <option value=""> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_SEL_OPCION") %> </option>
					</select>
                </div>
            </div>
			<div class="form-group">
                <label for="descNumCambiario" class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DESCRIPCION_NUM_CAMBIARIO") %> </label>
                <div class="col-sm-9">
                    <label id="descripcionNumeralC" name="descripcionNumeralC" style="padding-top: 7px; width: 600px;" align="left" > </label>
                </div>
            </div>
			<div class="row resumen">
				<ul><li><p style="vertical-align: top; font-weight: bold; font-size: 15px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_OP_ADOC") %>  </p><label id="documentosNumeralC" name="documentosNumeralC" style="font-size: 13px; width: 600px !important; margin-left: 15px; " align="left" > </label></li></ul>
				<ul><li><p style="vertical-align: top; margin-top: -10px;"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_REALIZAR_OP_ADOC_2") %>  </p><label id="documentosNumeralC" name="documentosNumeralC" style="font-size: 13px; width: 600px !important; margin-left: 15px;" align="left" > </label></li></ul>
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
					<input type="file"  id="subirFichero7" onchange="tipoExt(this.value,7)" name="subirFichero7"/><!-- INC FX 02 CMC - 21/02/2018 -->
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
					<input type="file"  id="subirFichero10" onchange="tipoExt(this.value,10)" name="subirFichero10"/><!-- INC FX 02 CMC - 21/02/2018 -->
				</div>
				<!--<a id="addBtnFiles" name="addBtnFiles" class="btnAdj"> Ajuntar otro archivo </a>-->
				<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->			
				<a class="tooltipAyuda" href="#">
                        <img src="/images/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class=" ocultar">
                        <p class="letragris" style="text-transform: uppercase;"> <!-- INC 25 FX CMC 09/02/2018 -->
						<!-- INI incidencia 25 FX CMC - 21/02/2018 -->
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO_FORMATOS_VALIDOS") %> GIF,JPG,JPEG,JPE,JFIF,TIF,TIFF,BMP,PDF.
						<!-- FIN incidencia 25 FX CMC - 02/02/2018 -->
                        </p>
                    </div>
			</div>
			<hr>
			<div class="botoneraCreacion">
           <div id="T" class="col-md-6 col-xs-6">
                    <ul>
                        <li><a onclick="volverCreacion()">«« <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VOLVER_INICIO") %></a><!--INI incidencia 122 FX CMC 03/12/2018-->
						<a class="tooltipAyuda" href="#">
                        <img src="/images/version7/ayudaEmergenteDerecha.png" />
                    </a>
                    <div class="tooltipFlechaIzquierda ocultar">
                        <p class="letragris">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LINK_DOCUMENTACION") %> <a href="https://www.bbva.com.co/meta/formato-comercio-exterior/"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DOCUMENTACION") %>...</a>
                        </p>
                    </div></li>
                    </ul>
                </div>
				<div id="H" class="col-md-6 col-xs-6">
                    <ul>
                        <li><a onclick="volverCreacion()">«« <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VOLVER") %></a><!--INI incidencia 122 FX CMC 03/12/2018-->
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
               <button type="button" id="buttonConti" class="buttonGiroAzul" style="width: 200px;"  onclick=" return continuar()"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GUARDAR_CONTINUAR") %></button>
			</div>
         </div>
		 </div>
			<div id="msjImportante" class="container; col-md-12" style="bottom: -160px;">
			<div class="alerta bordeInfo clearfix" id="msjImportante">
                    <div class="interior1">
                        <img src="/images/version7/iconoAlertaInfo.png"> 
                    </div>
                    <div class="interior2">
                        <ul id="msjImportante"><!--INI INC 78 FX 24-07-2018 --><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_FUNDAMENTOS_RES_EXTERNA") %><!--FIN INC 78 FX 24-07-2018 --><br><br>
						</ul>
                    </div>
                </div>
			<div align="right" class="col-md-12">
				<!--<button type="button" style="width: 100;" onclick="declinar()"  class="grandeAzul" > Declinar</button>&nbsp; &nbsp;&nbsp;-->
				<button id="T2" type="button" style="width: 100;" onclick="inicio()"  class="buttonGiroAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DECLINAR") %></button>&nbsp; &nbsp;&nbsp;
				<button id="H2" type="button" style="width: 100;" onclick="volverCreacion()"  class="buttonGiroAzul" > <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DECLINAR") %></button>&nbsp; &nbsp;&nbsp;<!-- INC REDIRECCIÓN 217 FX CMC -->
				<!-- INI Alcance Divisas CMC Archivos 29/01/2018 -->
				<!-- INI Incidencia 92 FX CMC 17/09/2018 -->
				<button style="width: 100;" type="button" class="buttonGiroAzul" id="btnEnviar" onclick="cargaArchivo(); this.form.submit()"> <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ACEPTAR") %></button><!--Incidencia 149 FX CMC 24/12/2018-->
				<!-- FIN Incidencia 92 FX CMC 17/09/2018 -->
				<!-- FIN Alcance Divisas CMC Archivos 29/01/2018 -->
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