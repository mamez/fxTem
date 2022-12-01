<%@ include file="includecbtf_rs7.jsp" %>
    <%@ page import="com.ibm.dse.base.Hashtable" %>
        <html lang="es-ES">

        <head>
            <title>bbva</title>
            <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
            <meta http-equiv="EXPIRES" content="-1">
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
            <script language="Javascript">
                //Variable para cambiar el idioma de los calendarios
                var _idiomaApp;
                _idiomaApp = "ES";
                var fechaSist='<%= df.format(new java.util.Date()) %>';
            </script>    
            
            
            
            <script language="Javascript">
			    function enviar()
				{
					var f =  document.formulario;
					var envia = true;
					var envia1 = true;
					var envia2 = true;
					
					
					if (f.fechaDesde.value != "" && f.fechaHasta.value != "") {
						var envia3 = ComparacionFechas(f.fechaDesde.value,f.fechaHasta.value);
						if (!envia3) {
							alert("La Fecha Hasta debe ser posterior a la Fecha Desde.");
							f.fechaDesde.focus();
							envia=false;		 
							return false;
						}
					}
				
					
			
					if(envia) 
					{
						f.submit();
					}
					else
					{
						f.importeDesde.focus();
						return false;
					}
				}
            
            </script>       
            
                    
            
            
            <!--CSS-->
            <link rel="stylesheet" type='text/css' href="/estilos/mainPagos.css" />
            <link rel="stylesheet" type='text/css' href="/estilos/bbva-ui.css" />
            <link rel="stylesheet" type='text/css' href="/estilos/bbva-icon.css" />
            <link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css">
            

            <!--JS-->


            
            <script language="Javascript" src="/js/UtilidadesPortal.js"></script>
            <script type="text/javascript" src="/js/version7/autogestion/jquery.js"></script>
 	   		<script type="text/javascript" src="/js/version7/autogestion/jquery-ui.js"></script>
 	    	<script type="text/javascript" src="/js/version7/autogestion/bootstrap.js"></script>
 	    	<script language="Javascript" src="/js/version7/MainPseDivisas.js"></script>
        </head>

        <% java.util.Vector listaUsuarios=(java.util.Vector)(datos.get("listaUsuariosAuto")); java.util.Enumeration
            enumListaUsuarios=listaUsuarios.elements(); java.util.Hashtable listUsuario; %>

            <body>
            <!-- Catalogo javaScript -->
	        <input type="hidden" id="mensajeOk" value=" <%=CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_MENSAJE_CONFIGURACION") %>" />
	        <input type="hidden" id="mensajeNok" value=" <%=CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_ERROR_CONFIGURACION") %>">
            <input type="hidden" id="PDPSE_TITULOSUB" value=" <%=CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_MENSAJE_CONFIGURACION") %>">

            
                <div class="row">
                    <div class="col-md-8">
                        <a name="top"></a>
                        <h1>
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_TITULO") %>
                        </h1>
                        <p class="subtituloPrincipal" style="margin-left: 2px;">
                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_TITULOSUB") %>
                        </p>
                    </div>
                </div>

                <div class="row">
                    <!--NAV-TABS -->
                    <div class="col-md-12">
                        <!--4 pestañas -->
                        <ul id="pagosPestanas" class="nav nav-tabs">
                            <li id="activeButton" class="activeButton active" onclick="FuncionAdd1();"><a href="#enPagosPse">
                                    <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_VENTANA1") %>
                                </a>
                            </li>
                            <li id="activeButtonPane" class="activeButtonPane" onclick="FuncionAdd2();"><a href="#enConsultasPse">
                                    <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_VENTANA2") %>
                                </a>
                            </li>
                        </ul>

                        <!--Contenido de cada pestaña -->
                        <div class="tab-content">
                            <div id="pagosPse" class="tab-pane active" style="height: 650px;">
                                <form action="" name="formulario" id="formulario">
                                    <div>
                                        <h4>
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                idioma,"PDPSE_TITULO_VENTANA1") %>
                                        </h4>
                                        <p>
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                idioma,"PDPSE_TITULOSUB_VENTANA1") %>
                                        </p>
                                    </div>
                                    <!-- Mensajes -->
                                    <div id="messageBox" class="alerta bordeOk clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaOk.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULOk">
                                               
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    <div id="messageGuardar" class="alerta bordeInfo clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaInfo.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULOk">
                                                <li>
	                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
	                                            	idioma,"PDPSE_GUARDAR_CONF") %>
                                            	</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div id="messageBoxNOK" class="alerta bordeWarning clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaWarning.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULNOK">
                                             
                                            </ul>
                                        </div>
                                    </div>
                                    <div id="messageValor" class="alerta bordeWarning clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaWarning.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULNOK">
                                            	<li>
	                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
	                                            	idioma,"PDPSE_MENOR_CAMPO") %>
                                            	</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div id="messageValorDesde" class="alerta bordeWarning clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaWarning.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULNOK">
                                            	<li>
	                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
	                                            	idioma,"PDPSE_MENSAJE_DESDE") %>
                                            	</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div id="messageFechaActual" class="alerta bordeWarning clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaWarning.png">
                                        </div>
                                        <div class="interior3">
                                            <img src="/images/version7/cerrarAlerta.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULNOK">
                                            	<li>
	                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
	                                            	idioma,"PDPSE_MENSAJE_FECHA") %>
                                            	</li>
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    <div id="messageRequerido" class="alerta bordeInfo clearfix ocultar">
                                        <div class="interior1">
                                            <img src="/images/version7/iconoAlertaInfo.png">
                                        </div>
                                        <div class="interior2">
                                            <ul id="messageBoxULNOK">
                                                <li>
	                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
	                                            	idioma,"PDPSE_COMPLETAR_CAMPOS") %>
                                            	</li>
                                            </ul>
                                        </div>
                                    </div>


                                    <div class="row titleTable">
                                        <div class="col-md-12">
                                            <!-- Ini incidencias cmc-->
                                            <h2>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_TITULO_CONTENIDO") %>
                                            </h2>
                                            <!-- Fin incidencias cmc-->
                                        </div>
                                    </div>
                                    <div class="row subtitleTable">
                                        <!--botonera lado izquierdo-->
                                        <div class="row titleTable">
                                            <table class="col-md-12">
                                                <tr>
                                                    <td colspan="5">
                                                        <input type="radio" id="huey" name="tipoPago" value="yes"
                                                            checked="checked">
                                                        <label for="huey">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_RADIO_PAGO") %>
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> <label for="usuario">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_USUARIO_AUTORIZADO") %>
                                                        </label>
                                                    </td>
                                                    <td colspan="5">
                                                        <select id="usuario" name="usuario" size="1" class="select">
                                                        	 <option value=""> </option>
                                                            <% while(enumListaUsuarios.hasMoreElements()){
                                                                listUsuario=(java.util.Hashtable)enumListaUsuarios.nextElement();
                                                                %>
                                                                <option value="<%=listUsuario.get("id") %>">
                                                                    <%=listUsuario.get("name") %>
                                                                </option>
                                                                <% } %>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> <label for="usuario">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_RANGO_VALOR") %>
                                                        </label>
                                                   	</td>
                                                    <td>
                                                        <label for="desde">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_CALENDARIO_DESDE") %>
                                                        </label>
                                                        <input type="text"  class="currency" minlength="3" id="valorDesde" name="valorDesde" data-type="number">
                                                    </td>
                                                    <td>
                                                        <label for="hasta">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_CALENDARIO_HASTA") %>
                                                        </label>
                                                          <input type="text"  class="currency" minlength="3" id="valorHasta" name="valorHasta" data-type="number">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> <label for="usuario">
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_FECHA_GIRO") %>
                                                        </label></td>
                                                    <td colspan="5">
                                                        <!--<input class="obligatorio" tabindex="5" type="text" onChange="javascript:checkedRadio('false','hasta','true')" onclick="javascript:checkedRadio('false','hasta','false')" name="fechaHasta" id="fechaHasta" size="13" value="" maxlength="10">-->
                                                        <span class="ui-calendar ui-general ui-box" name="fechaHasta" id="fechaHasta" wv="fechaHasta">
                                                        </span>
                                                        <script>
                                                            _$$IN({
                                                                "fn": UI.calendar,
                                                                "arg": "fechaHasta"
                                                            });
                                                        </script>                      
                                                        
                                                    </td>
                                                </tr>              
                                                <tr>
                                                <td colspan="5">
                                                    <p align="right">
							  							<button id="btnGuardarPagos" onclick="configurarUsuarioReq()"
                                                            type="button" style="margin: 8px 0 5px 10px;"
                                                            class="buttonGiro formAjax" disabled>
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_BOTON_AZUL_GUARDAR") %>
                                                        </button>
													</p>
                                                </td>
                                            </tr>
                                                
                                            </table>
                                           
                                        </div>
                                    </div>
                                </form>
                                <br> <br>
                                <br> <br>
                                 
                            </div>

                            <!--PESTAÑA CONSULTA -->
                            <div id="consultasPse" class="tab-pane">
                                <div>
                                    <h4>
                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                            idioma,"PDPSE_TITULO_VENTANA2") %>
                                    </h4>
                                    <p>
                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                            idioma,"PDPSE_TITULOSUB_VENTANA2") %>
                                    </p>
                                </div>
                                <div id="messageBox2" class="alerta bordeOk clearfix ocultar">
                                    <div class="interior1"><img src="/images/version7/iconoAlertaOk.png"></div>
                                    <div class="interior3"><img src="/images/version7/cerrarAlerta.png"></div>
                                    <div class="interior2">
                                        <ul id="messageBoxULOk">
                                            <li>
                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                            idioma,"PDPSE_VALIDA_ELIMINAR") %>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div id="messageBoxNOK2" class="alerta bordeWarning clearfix ocultar">
                                    <div class="interior1"><img src="/images/version7/iconoAlertaWarning.png"></div>
                                    <div class="interior3"><img src="/images/version7/cerrarAlerta.png"></div>
                                    <div class="interior2">
                                        <ul id="messageBoxULOk">
                                            <li>
                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                            idioma,"PDPSE_FECHA_VACIO") %>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div id="messageBoxNOK3" class="alerta bordeWarning clearfix ocultar">
                                    <div class="interior1"><img src="/images/version7/iconoAlertaWarning.png"></div>
                                    <div class="interior3"><img src="/images/version7/cerrarAlerta.png"></div>
                                    <div class="interior2">
                                        <ul id="messageBoxULOk">
                                            <li>
                                            	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                            idioma,"PDPSE_FECHA_VALIDA") %>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="row titleTable">
                                    <div class="col-md-12">
                                        <!-- Ini incidencias cmc-->
                                        <h2>
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                idioma,"PDPSE_FILTRO_CONSULTA") %>
                                        </h2>
                                        <!-- Fin incidencias cmc-->
                                    </div>

                                </div>

                                <div class="row subtitleTable">
                                    <!--botonera lado derecho-->
                                    <div class="row titleTable">
                                        <table class="col-md-12">
                                            <tr>
                                                <td colspan="5">
                                                    <input type="radio" id="radioCheck" name="parambus" value="0" class="checkpse" >
                                                    <label for="huey">
                                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                            idioma,"PDPSE_DIA_ACTUAL") %>
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="5">
                                                    <input type="radio" id="radioCheck" name="parambus" value="45" class="checkpse">
                                                    <label for="huey">
                                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                            idioma,"PDPSE_ULTIMOS_DIAS") %>
                                                    </label>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <input type="radio" id="rangoFechas" name="parambus" value="-1" class="checkpse" >
                                                    <label for="huey">
                                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                            idioma,"PDPSE_RANGO_FECHAS") %>
                                                    </label>
                                                </td>
                                                <td><strong>
                                                        <p>
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_DESDE_FECHA") %>
                                                        </p>
                                                    </strong></td>
                                                <td><span class="ui-calendar ui-general ui-box" wv="RangoDesde"
                                                        name="fechaDesde"  id="fechaDesde" disabled=true> </span> </td>
                                                <script>
                                                    _$$IN({
                                                        "fn": UI.calendar,
                                                        "arg": "RangoDesde"
                                                    });
                                                </script>
                                                <td><strong>
                                                        <p>
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_HASTA_FECHA") %>
                                                        </p>
                                                    </strong></td>
                                                <td><span class="ui-calendar ui-general ui-box" wv="RangoHasta"
                                                        name="fechaHasta" id="fechaHasta" disabled=true> </span> </td>
                                                <script>
                                                    _$$IN({
                                                        "fn": UI.calendar,
                                                        "arg": "RangoHasta"
                                                    });
                                                </script>
                                            </tr>
                                            
                                            
                                            <tr>
                                                <td colspan="5">
                                                    <p align="right">
						    <button id="btnNuevaConsulta" type="button"
                                                        style="margin: 8px 0 5px 10px;" class="grandeAzul ocultar formAjax">
                                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                            idioma,"PDPSE_BOTON_CONSULTAR_NUEVO") %>
                                                    </button>
                                                    <button id="btnDesPagos" type="button" onClick="consultarAutorizado()"
                                                        style="margin: 8px 0 5px 10px;" class="buttonGiroAzul formAjax">
                                                        <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                            idioma,"PDPSE_BOTON_CONSULTAR") %>
                                                    </button>
													</p>
                                                </td>
                                            </tr>
                                        </table>         
                                    </div>                             
                                     <div class= "lineaGray"></div>                           
                                </div>
                                
                                <div id="tablaConsulta" class="row titleTable ocultar" style="margin-top: 25px;">
                                    <div class="col-md-12">
                                        <!-- Ini incidencias cmc-->
                                        <div class="alineaIzquierda">
                                        <h2>
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                idioma,"PDPSE_RESULTADO_CONSULTA") %>
                                        </h2>
                                        </div>
                                        <div class="alineaDerecha">
                                            <button type="button"
                                                        class="btnEliminarTodo buttonIcon jqBotonConTooltip jqBotonMicro formAjax"
                                                        id="btnEliminarPse" value="eliminarBoton" style="margin: 8px 0 5px 10px"
                                                        data-target="#modalComun" onclick="eliminarConfiguracion()">
                                                        <span>
                                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                                idioma,"PDPSE_BOTON_ELIMINAR_TABLA") %>
                                                        </span>
                                                        <img src="/images/botonEliminar.png" />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div id="tablaResultados" class="row subtitleTable ocultar">
                                <table id="usuarios">
                                    <thead>
                                        <tr>
                                            <th>
                                                
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_TIPO_DE_PAGO") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_USUARIO_AUTORIZADO") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_NUMERO_TRANS") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_VALOR_DESDE") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_VALOR_HASTA") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_MOSTRAR_HORA") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_MOSTRAR_ESTADO") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_USUARIO_QUE_AUTORIZA") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_FECHA_TRANSACCION") %>
                                            </th>
                                            <th>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                    idioma,"PDPSE_IP_AUTORIZACION") %>
                                            </th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td class="selectora">
                                                <input class="selectall" type="checkbox" name="row" />
                                            </td>
                                            <td><a href="#detalle" class="botonIcono" type="button" data-toggle="modal" data-target="#myModal">
										    	<%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_TABLA_PUNTUAL") %>
										    	</a>
					    					</td>
                                        </tr>

                                    </tbody>
                                </table>
				</div>

                                <div id="tablaVermas" class="row verMas ocultar">
                                    <div class="col-md-12">
                                        <a href="#" data-page="1"  onclick="return getUsers();" class="linkverMas">
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE",
                                                idioma,"PDPSE_VER_DATOS") %>
                                                <img src="/images/version7/ver_mas.png" />
                                        </a>
                                        <div class="alineaDerecha">
                                            <a href="#top" class="btnFlechaArriba">
                                                <img src="/images/version7/flechaArribaTabla.png" />
                                            </a>
                                        </div>
                                    </div>
                                    <!--end-button ver mas-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row footer"></div>
                    <!--end-footer-->
                </div>
				<br>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<div class="alineaDerecha">
            <a href="" class="cerrar" data-dismiss="modal" aria-hidden="true">
                <img src="/images/cerrarAlerta.png">
            </a>
        </div>
								<h2><p><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DETALLE_AUTORIZACION") %></p></h2>
							</div>
							<div class="modal-body">
								
								<div class="row titleTable">
                                        <div class="col-md-12">
                                            <h2>
                                            <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_CABECERA") %>      
                                            </h2>
                                        </div>
                                </div>
                                    <div class="row subtitleTable">
                                        <div class="row titleTable">
                                            <table class="col-md-12">
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_TIPO_DE_PAGO2") %> <span id="mod-tipoPago"></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_AUTORIZACION_CONS") %> <span id="mod-consecutivo"></span></td>
                                                </tr>
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_USER_AUTORIZADOR") %> <span id="mod-usuariAutori"></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_VAL_DESDE") %> <span id="mod-valorDesd" ></span></td>
                                                </tr>
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_PAGOS_CANTIDAD") %> <span id="mod-cantidaPagos"></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_VAL_HASTA") %> <span id="mod-valorHas"></span></td>
                                                </tr>
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_FAUTORIZACION") %> <span id="mod-fechaAutor"></span></td>
													<td></td>
                                                </tr>
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_AUTORIZACION_ESTADO") %> <span id="mod-estado"></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_IP_TERMINAL") %> <span id="mod-ipTerminal"></span></td>
                                                </tr>
             
                                            </table>
                                        </div>
                                    </div>
                                    
                                    <div class="row titleTable">
                                        <div class="col-md-12">
                                            <h2>
                                                <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_DPAGOS") %>
                                            </h2>
                                        </div>
                                </div>
                                    <div class="row subtitleTable">
                                        <div class="row titleTable">
                                            <table class="col-md-12">
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_NUMTRANS") %> <span id="mod-transaEjec"></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_USER_EJECUTOR") %> <span id="mod-usuarioEjec" ></span></td>
                                                </tr>
                                                <tr>
                                                    <td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_EJECUTADAS") %> <span id="mod-MontoEjec" ></span></td>
								
								    				<td><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma,"PDPSE_DATOS_ULPAGO") %> <span id="mod-FechaUltim" ></span></td>
                                                </tr>
                                            </table>
                                     
                                        </div>
                                    </div>	
							</div>
						</div>
					</div>
				</div>
            </body>

        </html>