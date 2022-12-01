<!doctype html>
<%@page import="com.ibm.ws.webservices.xml.wassysapp.systemApp"%>
<%@ include file="includecbtf.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.ibm.dse.base.Hashtable"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.util.Locale"%>

<%
	String codError = "";
  	String valorError = "";
  	String propiedad = "none;";
  	if(datos.get("codError") != null){
		codError = (String) datos.get("codError");
	  	if(codError.equalsIgnoreCase("CNEP002")){
		  	valorError = (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VALIDACION_BANCO");
		  	propiedad = "block;";
	  	}
  	} 
%>
<input type="hidden" id="flagMessage" value="<%=codError%>"/>
<input type="hidden" id="nombreOperacion" value="<%=datos.get("nombreOperacion")%>"/>
<div class="alerta bordeWarning clearfix" id="messageBoxValBanco" style="display:<%=propiedad%> height:41px !important;">
	<div class="interior1">
		<img src="/images/version7/iconoAlertaWarning.png" style="margin-top:-1px"> 
	</div>
	<div id="cerrarmsj4" class="interior3">
		<img src="/images/version7/cerrarAlertaDiv.png"> 
	</div>
	<div class="interior2">
		<ul id="messageBoxULValBanco"><%=valorError%></ul>
	</div>
</div>
<!--INI INC 121 CMC 3-12-2018 -->
<script>
	function verificar_cuenta() {
		//INI incidencia 225 FX CMC 21/08/2019
		document.getElementById("flagExecute").value = "n";
	    var varcuenta = document.getElementById("tiposabanco1");
		var varcuentacod = document.getElementById("codigosabanco1");
		var varcuenta2 = document.getElementById("tiposabanco2");
		var varcuentacod2 = document.getElementById("codigosabanc2");
		var beneficiarioOK = true;
		var intermediarioOK = true;
		var infoCodigoUnitarioOK = true;
		beneficiarioOK = validarTpCun(varcuentacod,varcuenta);
		intermediarioOK = validarTpCun(varcuentacod2,varcuenta2);
		infoCodigoUnitarioOK = validarInfoCodigoUnitario(varcuentacod);
		if(!beneficiarioOK || !intermediarioOK || infoCodigoUnitarioOK){
			document.getElementById("flagExecute").value = "a";
			return false;
		}else{
			return true;
		}
		//FIN incidencia 225 FX CMC 21/08/2019
	}
</script>
<script>
function verificarCaracteres() {
	var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?"; 
	for (var i = 0; i < document.getElementById("nombresbeneficTMP").value.length; i++) {
	if (iChars.indexOf(document.getElementById("nombresbeneficTMP").value.charAt(i)) != -1){
		alert("<%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_VALIDAR_NOMBRE_BENEF") %> ");
		document.getElementById("nombresbeneficTMP").value = "";
		return false;
		}
	}
}
</script>
<!--FIN INC 121 CMC 3-12-2018 -->
<%
	  String selectDocumento = (String)datos.get("tipobeneficiar");

	  final String benLongMaxUno = (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_LONGMAX1");
	  final String benLongMaxDos = (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_LONGMAX2");
	  final String benLongMaxTres = (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_LONGMAX3");
	  
      String CodDocumento;
      if(selectDocumento!=""&& selectDocumento!=null){
      CodDocumento = selectDocumento;
      }else{
      CodDocumento ="";
      }
	  
	  String selectCodigo2 = (String)datos.get("tiposabanco2");
	  String Codigo2;
      if(selectCodigo2!=""&& selectCodigo2!=null){
      Codigo2 = selectCodigo2;
      }else{
      Codigo2 ="";
      }
      
      String selectCodigo1 = (String)datos.get("tiposabanco1");
	  String Codigo1;
      if(selectCodigo1!=""&& selectCodigo1!=null){
      Codigo1 = selectCodigo1;
      }else{
      Codigo1 ="";
      }
	  
	  %>
	  
<div class="panel-body">

	<form class="form-horizontal formMBefeniciarioManual"  onsubmit='return validarA1()' method="POST" id="formMBefeniciarioManual" name="formMBefeniciarioManual" >
	<input type="hidden" id="indBenef" name="indBenef" value="<%=(String)datos.get("indBenef")%>" />
	
	<input type="hidden" id="tasaDivisa" name="tasaDivisa" value="<%=datos.get("tasaDivisa")%>" />
	<input type="hidden" id="tasaDivisaPeso" name="tasaDivisaPeso" value="<%=datos.get("tasaDivisaPeso")%>" />
	<input type="hidden" id="tasaUsdPeso" name="tasaUsdPeso" value="<%=datos.get("tasaUsdPeso")%>" />
	<input type="hidden" id="equivPesos" name="equivPesos" value="<%=datos.get("equivPesos")%>" />
	
	<input type="hidden" id="selectOpe" name="selectOpe" value="<%=(String)datos.get("selectOpe")%>" />
	<input type="hidden" id="selectCuentaOrden" name="selectCuentaOrden" value="<%=(String)datos.get("selectCuentaOrden")%>" />
	<input type="hidden" id="monto" name="monto" value="<%=(String)datos.get("monto")%>" />
	<input type="hidden" id="selectMoneda" name="selectMoneda" value="<%=(String)datos.get("selectMoneda")%>" />
	<input type="hidden" id="descripNegociacion" name="descripNegociacion" value="<%=(String)datos.get("descripNegociacion")%>" />
	<input type="hidden" id="avanceOpe" name="avanceOpe" value="<%=(String)datos.get("avanceOpe")%>"/>
	<input type="hidden" id="numOperacion" name="numOperacion" value="<%=(String)datos.get("numOperacion")%>" />
	<input type="hidden" id="tipoOperacion" name="tipoOperacion" value="<%=(String)datos.get("tipoOperacion")%>"/>
	<input type="hidden" id="cuentaintermed" name="cuentaintermed" value=""/>
	<input type="hidden" id="apellidosbenef" name="apellidosbenef" value="<%=datos.get("apellidosbenef")%>"/>
	<input type="hidden" id="nombresbenefic" name="nombresbenefic" value="<%=datos.get("nombresbenefic")%>"/>
	<input type="hidden" id="referenciaOpe" name="referenciaOpe" value="<%=datos.get("referenciaOpe")%>"/>
	<input type="hidden" id="tipoFondoGiro2" name="tipoFondoGiro" value="<%=(String)datos.get("tipoFondoGiro")%>" />
	<input type="hidden" id="flagExecute" value="a"/>
	
	<%
			String idbeneficiario = "";
			String possibleCharM = "0123456789";
			String nameFiltering = (String) datos.get("nombresbenefic") + (String) datos.get("apellidosbenef");
			nameFiltering = nameFiltering.replace(".","");
			
			double X; 
				for( int i=0; i < 15; i++ ){
					X = Math.floor(Math.random()*10);
					int y = (int) X;
					idbeneficiario += possibleCharM.charAt(y);
				}
				
			if (datos.get("idbeneficiario")!=null && datos.get("idbeneficiario") != ""){
	%>
		<input type="hidden" id="idbeneficiario" name="idbeneficiario" value="<%=(String)datos.get("idbeneficiario")%>"/>
		<input type="hidden" id="tipobeneficiar" name="tipobeneficiar" value="<%=(String)datos.get("tipobeneficiar")%>" />
		<input type="hidden" id="digivbeneficia" name="digivbeneficia" value="<%=(String)datos.get("digivbeneficia")%>"/>
		
	<%
		}else{
	%>
		
		<input type="hidden" id="idbeneficiario" name="idbeneficiario" value="<%=idbeneficiario%>"/>
		<input type="hidden" id="tipobeneficiar" name="tipobeneficiar" value="2" />
		<input type="hidden" id="digivbeneficia" name="digivbeneficia" value="6"/>
	<%}%>
		<!-- INI Incidencia 103 FX 01/10/2018 -->
        <div class="form-group">
            <label for="nombresbeneficTMP" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NOMBRE_BENEFICIARIO") %></label>
            <div class="col-sm-9">
	 <%-- INI IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>
                <input type="text" required="true" class="marcaAgua form-control" id="nombresbeneficTMP" name="nombresbeneficTMP" onkeypress="return permite(event, 'swift')" onblur= "verificarCaracteres();" onChange="nameFiller(); validarSwift(this,'swift');" value="<%=nameFiltering%>"  maxlength="35" placeholder="<%=benLongMaxUno%>"/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <!-- FIN Incidencia 103 FX 01/10/2018 -->
		
        <div class="form-group">
            <label for="direccionebenef" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_DIRECCION") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="marcaAgua form-control" id="direccionebenef" name="direccionebenef" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("direccionebenef")%>" maxlength="35" placeholder="<%=benLongMaxUno%>"/> <!-- INC 103 CDAH -->
            </div>
        </div>
		<div class="form-group">
				<label for="paisbeneficiar" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PAIS2") %></label>
				<div class="col-sm-9">
				<select id="paisbeneficiar"  required="true" name="paisbeneficiar" style="width: 152px;">
					<option value=""> </option>
					<%
							String Paises = "";
							String valuePais = "";
							String namePais = "";
							
							for (int i = 0 ; i<=250; i++) {
									Paises = (String) CatalogoMultidioma.obtenerLiteral("PNET_PAISES_DIVISAS",idioma,"PNET_value" + i);
								try{
									String[] parts1 = Paises.split(";");
									 valuePais = parts1[0];
									 namePais = parts1[1];
								%>	
									<option value='<%=valuePais%>' <%if(valuePais.equals((String)datos.get("paisbeneficiar"))){%> selected <%}%> ><%=namePais%></option>
								<%
									}catch(Exception e){
						
							}}%>
				</select>
				</div>
		</div>
		<div class="form-group">
            <label for="ciudadbenefcia" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CIUDAD") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="marcaAgua form-control" id="ciudadbenefcia" name="ciudadbenefcia" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("ciudadbenefcia")%>" maxlength="12" placeholder="<%=benLongMaxTres%>"/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <div class="form-group">
            <label for="telefonobenefi" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TELEFONO") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="marcaAgua form-control" id="telefonobenefi" name="telefonobenefi" onkeypress="return permite(event, 'only_num')" onChange="validarSwift(this,'only_num');" value="<%=datos.get("telefonobenefi")%>" maxlength="15" placeholder="<%=benLongMaxDos%>"/> <!-- INC 103 CDAH -->
            </div>
        </div>
		<hr>
		<div class="form-group">
            <label class="col-sm-3 control-label" for="tiposabanco1">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO_CODIGO") %></label>
            <div class="col-sm-9">
                <select id="tiposabanco1" required="true" name="tiposabanco1" class="selectFormaPago" onclick="addEvent(this,'onchange','showSelected(this)')">
					<option value=""> </option>
					<option value="FW" <%if(Codigo1.equals("FW")){%> selected <%}%>>ABA</option>
					<option value="SW" <%if(Codigo1.equals("SW")){%> selected <%}%>>SWIFT</option>
					<!-- <option value="CT" <%if(Codigo1.equals("CT")){%> selected <%}%>>CUENTA</option> --> <!-- INC 124 FX CMC 18-12-2018  -->
                </select>
            </div>
        </div>
		<div class="form-group">
            <label class="col-sm-3 control-label" for="codigosabanco1">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CODIGO_O_CUENTA") %></label>
			<!--<label class="col-sm-3 control-label" id="ctaBenef" name="ctaBenef" for="codigosabanco1">* Cuenta</label>-->
            <div class="col-sm-9">
                <input type="text" required="true" name="codigosabanco1" maxlength="12" id="codigosabanco1" onkeypress="return permite(event, 'both')" onblur="execute(); validarTpCun(this, document.getElementById('tiposabanco1'));" class="marcaAgua codigoSwitAba form-control" value="<%=datos.get("codigosabanco1")%>"/><!-- INC 103 CDAH --><!-- INC 121 FX CMC 3-12-2018 -->
            </div>
        </div>
		<div class="form-group">
            <label for="nombrebancobe" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BAN_BENEF") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="form-control" id="nombrebancobe" name="nombrebancobe" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("nombrebancobe")%>" maxlength="35" readonly/> <!-- INC 103 CDAH -->
            </div>
        </div>
       	<div class="form-group">
            <label for="codePaisBene" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PAIS2") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="form-control" id="codePaisBene" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("codePaisBene")%>" readonly/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <input type="hidden" class="form-control" id="paisbancobene" name="paisbancobene" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("paisbancobene")%>"/>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="ciudadbancobe">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CIUDAD") %></label><!--INC FX CIUDAD NO OBLIGATORIA 26-07-2018-->
            <div class="col-sm-9">
            <input type="text" name="ciudadbancobe" id="ciudadbancobe" class="form-control" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("ciudadbancobe")%>" readonly/> <!--INC FX CIUDAD NO OBLIGATORIA 26-07-2018--><!-- INC 103 CDAH -->
            </div>
        </div>
        <% 
       		String valorInput = "none";
           	String propDisabled = "disabled";
        	String paisbancobene = datos.get("paisbancobene") != null && !datos.get("paisbancobene").toString().equalsIgnoreCase("") ? 
    				datos.get("paisbancobene").toString() : "";
    		valorInput = paisbancobene.equalsIgnoreCase("GBR") ? "block": paisbancobene.equalsIgnoreCase("CAN") ?
           			"block" : "none";

        	String literalCodigoUnitario = "";
        	if(valorInput.equalsIgnoreCase("block")){
        		propDisabled = "";
        		literalCodigoUnitario = paisbancobene.equalsIgnoreCase("GBR") ?
           			 (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_SORTCODE") :
           				 paisbancobene.equalsIgnoreCase("CAN") ?
           						 (String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_CODIGOTRANSITORIO") :
           							 "";
        	}
        %>
        <div class="form-group">
            <label for="cuentabenefica" class="col-sm-3 control-label">* <%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_NUM_CUENTA") %></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="form-control" id="cuentabenefica" name="cuentabenefica" onkeypress="return permite(event, 'both')" onChange="validarSwift(this,'both');" value="<%=datos.get("cuentabenefica")%>" 
                	<%if(paisbancobene.equalsIgnoreCase("GBR")){%>maxlength="22" minlength="14"
                	<%}else if(paisbancobene.equalsIgnoreCase("MEX")){%>maxlength="18" minlength="18"
                	<%}else if(paisbancobene.equalsIgnoreCase("CAN")){%>maxlength="12" minlength="7"
                	<%}else{%>maxlength="30" minlength="5"<%}%>/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <div class="form-group">
            <label for="codigoUnitario" id="literalCodigoUnitario" class="col-sm-3 control-label"><%=literalCodigoUnitario%></label>
            <div class="col-sm-9">
                <input type="text" required="true" class="marcaAgua form-control" id="codigoUnitario" 
                	<%if(paisbancobene.equalsIgnoreCase("GBR")){%>
                		maxlength="6" minlength="6" placeholder="<%=(String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_SORTCODE_LONG")%>"<%}%>
                	<%if(paisbancobene.equalsIgnoreCase("CAN")&&datos.get("codigoUnitario")!=null&&!datos.get("codigoUnitario").toString().equalsIgnoreCase("")){%>
                		maxlength="5" minlength="5" placeholder="<%=(String) CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BEN_CODIGOTRANSITORIO_LONG")%>"<%}%>
                	name="codigoUnitario" onkeypress="return permite(event, 'only_num')" onChange="validarSwift(this,'only_num');" value="<%=datos.get("codigoUnitario")%>" style="display:<%=valorInput%>" <%=propDisabled%>/>
            </div>
        </div>
		<hr>
		<div class="form-group">
            <label class="col-sm-3 control-label" for="tiposabanco2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_TIPO_CODIGO") %></label>
            <div class="col-sm-9">
                <select id="tiposabanco2" name="tiposabanco2" class="selectFormaPago" onclick="addEvent(this,'onchange','showSelected(this)')">
					<option value=""> </option>
					<option value="FW" <%if(Codigo2.equals("FW")){%> selected <%}%>>ABA</option>
					<option value="SW" <%if(Codigo2.equals("SW")){%> selected <%}%>>SWIFT</option>
					<!-- <option value="CT" <%//if(Codigo2.equals("CT")){%> selected <%//}%>>CUENTA</option> --><!--INC 168.A FX 11-04-2019-->
                </select>
            </div>
        </div>
		<div class="form-group">
            <label class="col-sm-3 control-label" for="codigosabanc2"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CODIGO") %></label><!--INC 168.A FX 11-04-2019-->
			 <div class="col-sm-9">
                <input type="text" name="codigosabanc2" id="codigosabanc2" maxlength="12" onkeypress="return permite(event, 'both')" value="<%=datos.get("codigosabanc2")%>" class="marcaAgua codigoSwitAba form-control" onblur="execute(); validarTpCun(this, document.getElementById('tiposabanco2'));"  /> <!-- INC 121 CMC 3-12-2018 -->
            </div>
        </div>
        <div class="form-group">
            <label for="nombrebancoin" class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_BAN_INTER") %></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="nombrebancoin" name="nombrebancoin" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("nombrebancoin")%>" maxlength="35" readonly/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <div class="form-group">
            <label for="codePaisInte" class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_PAIS2") %></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="codePaisInte" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("codePaisInte")%>" maxlength="35" readonly/> <!-- INC 103 CDAH -->
            </div>
        </div>
        <input type="hidden" class="form-control" id="paisbancointe" name="paisbancointe" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("paisbancointe")%>"/>
		<div class="form-group">
            <label for="ciudadbancoin" class="col-sm-3 control-label"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_CIUDAD") %></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="ciudadbancoin" name="ciudadbancoin" onkeypress="return permite(event, 'swift')" onChange="validarSwift(this,'swift');" value="<%=datos.get("ciudadbancoin")%>" maxlength="35" readonly/> <!-- INC 103 CDAH -->
	 <%-- FIN IN_319_PETICIONES_FX LEONARDO SANCHEZ 13/12/2019 --%>           
            </div>
        </div>
        <!--<div class="form-group sup">
            <div class="col-md-12">
                <p class="letragris">Los campos marcados con * son campos obligatorios</p>
            </div>
        </div>-->
        <div class="row botoneraCreacion">
            <div class="col-md-6 col-xs-6">
				<ul>
					<li><a href="#"> </a></li>
				</ul>
			</div>
            <div class="col-md-6 col-xs-6"> 
				<button type="submit" id="aceptarBeneficiario" name="aceptarBeneficiario" class="grandeAzul" onclick="return  verificar_cuenta()"><%= CatalogoMultidioma.obtenerLiteral("PNET_COME", idioma, "COME_ACEPTAR") %></button><!--INC 121 CMC 3-12-2018 -->
			</div>
        </div>
		
    </form>
</div>