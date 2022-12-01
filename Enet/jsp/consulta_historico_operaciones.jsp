<!doctype html>
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<html >
<head>
    <meta charset="utf-8">
    <title>bbva divisas</title>
	<%
	if ((idioma != null) && (idioma.equalsIgnoreCase("en"))) {
%>
<!-- INI Alcance Divisas CMC Archivos 30/01/2018 -->
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
	<!-- FIN Alcance Divisas CMC Archivos 30/01/2018 -->
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
	<script type="text/javascript" src="/js/version7/negociacion_Divisas_Scripts.js"></script>   <%-- IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
	<script type="text/javascript" language="JavaScript" src="/js/version7/utilPibee.js"></script>
    <link rel="stylesheet" type='text/css' href="/estilos/version7/mainPagos.css"/>
	<link rel=stylesheet type='text/css' href="/estilos/version7/newLook.css">
	<link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"><!-- GP17667 -->
    <script>
	</script>
</head>
<body onLoad="controlPibee()">

    <!--CONTAINER -->
    <div id="container" class="container">
    <%-- INI GP17667--%>
    <input type="hidden" id="urlHacia" value="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosHacia">
    <input type="hidden" id="urlDesde" value="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=girosDesde">
    <%-- FIN GP17667--%>
	   <div class="col-md-8">
			<div class="row">
				<h1 class="boldH1"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_COMPRA_VENTA_DIVISAS")%></h1>
                <p class="subtituloPrincipalGiro"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_NEG_DIV")%></p>
            </div>
        </div>
		<div class="col-md-8" align="center">
			<p align="justify" class="textLeft"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_REC_ENV_GIROS") %></p><%-- GP17667 --%>
			<%-- <p align="justify" class="textLeft"><%=CatalogoMultidioma.obtenerLiteral("PNET_COME",idioma,"COME_MSG_005")%>:</p>GP17667 --%>
			<br>
			<div align="left">
			<table border="0" >
				<tr>
			<td align="center"><img src="/images/version7/international_icon.png" style="width:40%;heigth:40%" /></td><!-- INC 6 FX CMC 12/04/2018 -->
			<td align="center"><img src="/images/version7/balance_icon.png" style="width:40%;heigth:40%" /></td><!-- INC 6 FX CMC 12/04/2018 -->
				</tr>
				<tr>
			<%-- GP17667 INI --%>
					<td> <input type="radio" name="tipoGiro" value="haciaExterior" id="haciaExteriorRadio" onclick="habilitarCheck()"> <b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIROS_HACIA_EXT") %></b></td>
					<td> <input type="radio" name="tipoGiro" value="desdeExterior" id="desdeExteriorRadio" onclick="habilitarCheck()"> <b><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_GIROS_REC_EXT") %></b></td>
				</tr>
				<tr>
					<td><p align="left" class="textLeftGiro"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_GIRO_EXT_MONEDAS") %></p></td>
					<td><p align="left" class="textLeftGiro"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_MSG_GIRO_MONEDA_LOCAL") %></p></td>						
				</tr>
			</table>
			</br></br>
			<input type="checkbox" id="checkTerminosGiro" disabled onclick="habilitarButtonCheck()"/><label class="textCheck"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TERMINOS1") %> </label> <label ><a class="textCheckAzul" href="<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_LINK_TERMINOS_CONDICIONES") %>" target="_blank"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TERMINOS2") %></a></label> <label class="textCheck"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TERMINOS3") %></label>
			</br></br></br>
			<button type="button" id="buttonGiroExt" class="buttonGiro" disabled onclick="cargarUrl()"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CONTINUAR2") %></button>
			<%-- GP17667 FIN --%>
				</div>
		</div>	
	     </div>
	 </div>
</body>	
</html>