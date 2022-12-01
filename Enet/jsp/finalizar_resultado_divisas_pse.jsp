<%@ include file="includecbtf.jsp" %>
<%!
	public String formalDecimal(String resultadoDeci ) {
		try{
	        String numero=resultadoDeci;
	        numero= numero.replaceAll(",", "");
	        Double numeroResul = new Double(numero);
	        java.text.DecimalFormatSymbols simbolos = new  java.text.DecimalFormatSymbols();
	        simbolos.setDecimalSeparator('.');
	        simbolos.setGroupingSeparator(',');
	        java.text.DecimalFormat df = new java.text.DecimalFormat("###,###.##", simbolos);
	        resultadoDeci=df.format(numeroResul);
	    }catch(Exception e){
	    	
	    }
		return resultadoDeci;
	}
%>
    <link rel="stylesheet" href="/estilos/version7/finalizarResultadoDivisas.css" />
    <link rel="stylesheet" href="/estilos/version7/mainPagosDivisas.css"/>
    <link rel=stylesheet type='text/css' href="/estilos/version7/newLookGiros.css"><!-- GP17667 -->
    <script src="/js/version7/modernizr.js"></script>
    
	<!-- INI  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
    <!-- <script src="/js/jquery-2.1.3.js" type="text/javascript"></script> -->
      <script src="/js/jquery-2.2.4.js" type="text/javascript"></script>
    <!-- FIN  - Hacking etico V7-CMC-Osneider Acevedo-10/06/2019-->
	<script src="/js/version7/bbva-ui.js" type="text/javascript"></script>
	<script language="JavaScript" src="/js/version7/marcoDivisas.js"></script>

    <script language="Javascript" src="/js/version7/banner.js"></script>
<script language="Javascript" src="/js/version7/utilidades.js"> </script>
<script type="text/javascript" src="/js/version7/negociacion_Divisas_Scripts.js"></script> 
<input type="hidden" name="s_idioma" value="es">

    <script>
    function retornarBlotter(){
    	 var element= document.getElementById("FinalizaPSEBtn");
		     element.disabled=true;
		     element.classList.remove("buttonGiroAzul");
		     element.classList.add("buttonGiro");
    	window.location.href="OperacionCBTFServlet?proceso=comercio_exterior_bbva_pr&operacion=consulta_operaciones_negociacion_op&accion=tipoGiros&origen=finalizar";    	                    	
    }
     
            $(function () {
            $(".botonDetalle").click(function () {
                $(this).toggleClass("activer_1").next("div").slideToggle();
            });
        });

        $(function () {
            $(".activer_1").click(function () {
                $(".filtraroculta").slideToggle();
            });
        });
        
    </script>
    <svg aria-hidden="true" style="position: absolute; width: 0; height: 0; overflow: hidden;" version="1.1"
        xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <defs>
            <symbol id="icon-right" viewBox="0 0 32 32">
                <path
                    d="M16 1.231c-8.157 0-14.769 6.612-14.769 14.769s6.612 14.769 14.769 14.769c8.157 0 14.769-6.612 14.769-14.769v0c0-8.157-6.612-14.769-14.769-14.769v0zM14.831 20.652l-1.871 1.871-5.218-5.218 1.735-1.735 3.483 3.483 9.575-9.575 1.735 1.735z">
                </path>
            </symbol>
        </defs>
    </svg>
    <%
    	
    	String tmpN ="";
        String codNumeralTMP = "";
        String descripTMP = "";
        String numeralCC=""+datos.get("NumeralC");
        String descnumeralok="";
        int n = 0;
        for (int i = 1 ; i<=228; i++) {
            tmpN = (String) CatalogoMultidioma.obtenerLiteral("PNET_OpDETAILPSE",idioma,"NC_" + i);
                    try{
                        String[] parts = tmpN.split(";");
                        codNumeralTMP = ""+parts[0]+parts[1];
                        descripTMP = parts[4];
                         if (numeralCC.equals(codNumeralTMP)){
                        	 descnumeralok=parts[4];
                            break;
                        }
                    }catch(Exception e){
                    }
        }

    %>
    <section>
        <div class="container">
            <div class="titulo">
                <h1 class="negriFinal"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_RESULT_OPERATION") %></h1>
            </div>
            <div class="azulPse">
                <svg class="icon icon-right" style="margin-bottom: 10px;">
                    <use xlink:href="#icon-right"></use>
                </svg>
                <p><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_RESULT_OPERATION_DESC1") %></p>
            </div>

            <div class="grisPse">
                <div class="descripcion1">
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_TIPO_NEGOCIACION") %></span><%=" "+datos.get("descripcionoperPSE")+"")%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_FECHA_OP") %></span><%=" "+datos.get("fechaActual")+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_REFERENCIA") %></span><%=" "+datos.get("numTrans")+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NUMERO_AVANCE") %></span><%=" "+datos.get("avanceOpe")+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_METODO_PAGO") %></span> Desde otro banco por PSE</p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NIT") %></span><%=" "+datos.get("nitConsulta")+""%></p>
                    <P><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NOMBRE_CLIENTE") %></span><%=" "+datos.get("nitNombre")+""%></P>
                </div>
                <div class="descripcion2">
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_DESCRIPCION") %></span> <%=" "+datos.get("descripNegociacion")+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_VALOR") %></span> <%=" "+formalDecimal((String)datos.get("monto"))+" "+datos.get("selectMoneda")+""%> </p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_EQUIVALENTE_PESOS") %></span> <%="$ "+formalDecimal((String)datos.get("equivaPesos"))+"")%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_CONCEPTO") %></span><%=" "+datos.get("descNumeral")+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NUMERAL_CAMBIARIO") %></span> <%=" "+descnumeralok+""%></p>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_ESTADO") %></span><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_ESTADO_BANCO") %></p>
                    <P><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_COMISION") %></span><%=" $ "+ formalDecimal((String)datos.get("comision"))+""%></P>
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_IVA") %></span><%=" $ "+formalDecimal((String)datos.get("iva"))+""%></p>
                </div>
                <div class="descripcion3">
                    <p><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_TOTAL_DEBITAR") %></span><%=" $"+formalDecimal((String)datos.get("totalDebito"))+""%></p>
                </div>
                <div class="botonIm">
                    <button class="buttonGiroAzul" onclick="javascript:window.print()"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_IMPRIMIR") %></button>
                </div>
            </div>
            <div id="datos" class="row">
                <div class="col-md-12">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <a id="detalleBenef" onclick="refrehPage()"  class="botonDetalle collapsed activer_1">
                                        <img class="closedAccord" src="/images/version7/mas16x16Gris.png">
                                        <img class="openAccord" src="/images/version7/menos16x16Azul.png">
									    <%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_BENEFICIARIO") %> <%=" "+datos.get("nombresbenefic")+" | Cuenta: "+datos.get("cuentaintermed")+""%>
                                    </a>
                                    
                                </h3>
                            </div>
                            <div id="modalCollapseUno" class="panel-collapse collapse clearAll filtraroculta">
                                <div class="panel-body">

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NOMBRE_BENEFICIARIO") %> </b></label><label
                                            class="col-sm-3 control-label"><%=""+datos.get("nombresbenefic")+""%></label>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_NUMERO_CUENTA_DEVOLUCION") %>
                                            </b></label><label class="col-sm-3 control-label"><%=""+datos.get("cuentaintermed")+""%></label>
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_BANCO_BENEFICIARIO") %> </b></label><label
                                            class="col-sm-3 control-label"><%=""+datos.get("nombrebancobe")+""%></label>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_TIPO_BENEFICIARIO") %></b></label><label
                                            class="col-sm-3 control-label"> <%=""+datos.get("tiposabanco1")+""%> </label>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><b><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_CODIGO_CUENTA") %> </b></label><label
                                            class="col-sm-3 control-label"><%=""+datos.get("cuentaintermed")+""%></label>
                                    </div>



                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" style="display:none"><b>:
                                            </b></label><label class="col-sm-3 control-label"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panelEstadoPago">
                <div class="descripcionOP">
                    <svg class="icon icon-right">
                        <use xlink:href="#icon-right"></use>
                    </svg>
                    <p class="estadoOPd"><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_PAGO_PSE_APROBADO") %></span></p>
                    <p class="estadoText"><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_PRODUCTO_TRANSFERENCIA") %></p>
                    <p class="estadoText"><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_BANCO") %> </span><%=""+datos.get("codigoConsBanco")+""%></p>
                    <p class="estadoText"><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_CODIGO_CONFIRMACION") %> </span><%=""+datos.get("codpseCus")+""%></p>
                    <p class="estadoText"><span class="detalleOP"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_ESTADO_TRANS") %></span> Aprobado</p>
                </div>
                <div class="valorOP">
                    <p class="estadoOPd"><%="$"+formalDecimal((String)datos.get("totalDebito"))+""%></p>
                    <p class="detalleOP estadoText"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_VALOR_OPERACION") %></p>
                    <div class="linea"></div>
                    <p class="finalDetalle estadoText"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_MENSAJE_FINAL_IVA") %></p>
                </div>
            </div>
            <form id="reenviarInicio" method="POST" role="form"  class="form-horizontal">
            <div class="botonTer">
                <button type="button" id="FinalizaPSEBtn"class="buttonGiroAzul" onclick="retornarBlotter()"><%= CatalogoMultidioma.obtenerLiteral("PNET_PDPSE", idioma, "PDPSE_BOTON_FINAL") %></button>                
            </div>
            </form>
        </div>


    </section>
    