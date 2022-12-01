 const context={
	fechaActual:""
   }
//INI incidencia 217 FX CMC 10/07/2019
 function mostrasMensaje(tooltipTabla){
	 $(tooltipTabla).css("display", "block");  
	 //tooltipTabla.css("display", "block");
 }
 
 function ocultarMensaje(tooltipTabla){
	 $(tooltipTabla).css("display", "none");
	 //tooltipTabla.css("display", "none");
 }
 //FIN incidencia 217 FX CMC 10/07/2019
 
//INI incidencia 225B FX CMC 30/10/2019
function validaErrorSWIFT(){
	var  errorSWIFT = $("#errorCodigoSWIFT").val();
	if(errorSWIFT == "S"){
		$("#messageBoxCM6").show();
	}else{
		$("#messageBoxCM6").hide();
	}
}

function validaErrorSWIFTModificacion(){
	var  errorSWIFT = $("#errorCodigoSWIFT").val();
	if(errorSWIFT == "S"){
		$("#messageBoxCM7").show();
	}else{
		$("#messageBoxCM7").hide();
	}
}
//INI incidencia 225B FX CMC 30/10/2019
function desabilitarBtn(selector) {
	var btnABloquear = selector.id;
	document.getElementById(btnABloquear).disabled = true;
}
function desactivarEnlace(link) {
    link.onclick = function(event) {
       event.preventDefault();
    }
  }   
//GP17667 INI
function habilitarCheck(){
	document.getElementById("checkTerminosGiro").disabled = false;
	document.getElementById('checkTerminosGiro').checked = false;
	habilitarButtonCheck();
}
function habilitarButtonCheck(){
	var isCheckedTC = document.getElementById('checkTerminosGiro').checked;
	var elementBTC = document.getElementById("buttonGiroExt");
	if(isCheckedTC){
	  	elementBTC.disabled=false;
		elementBTC.classList.remove("buttonGiro");
		elementBTC.classList.add("buttonGiroAzul");
	}
	else{
	  	elementBTC.disabled=true;
		elementBTC.classList.add("buttonGiro");
		elementBTC.classList.remove("buttonGiroAzul");
	}
}

function cargarUrl(){
	var isCheckedTC2 = document.getElementById('checkTerminosGiro').checked;
	if(isCheckedTC2 && document.getElementById('haciaExteriorRadio').checked) {
		var urlh= document.getElementById("urlHacia").value;
		window.location.href = urlh;
	}else if(isCheckedTC2 && document.getElementById('desdeExteriorRadio').checked) {
		var urld= document.getElementById("urlDesde").value;
		window.location.href = urld;
	}
	var elementBTC = document.getElementById("buttonGiroExt");
	elementBTC.disabled=true;
	elementBTC.classList.add("buttonGiro");
	elementBTC.classList.remove("buttonGiroAzul");
}

function changeTextPSECtaDev(pseBBVA){
	var fondoPSE_BBVA= pseBBVA.value;
	if (fondoPSE_BBVA == "fondoPSE"){
		document.getElementById("lbSeleccionCta").textContent ="* "+ document.getElementById("textDevPseCta").value;
		document.getElementById("textCtaPSE").style.display = "inline-block";
		
	}if(document.getElementById('fondoPSE').checked){
		document.getElementById("lbSeleccionCta").textContent ="* "+ document.getElementById("textDevPseCta").value;
		document.getElementById("textCtaPSE").style.display = "inline-block";
	}
	else{
		document.getElementById("lbSeleccionCta").textContent ="* "+ document.getElementById("textBBVACta").value;
		document.getElementById("textCtaPSE").style.display = "none";
	}
}
//GP17667 FIN
// A $( document ).ready() block.

function getCommissionOperative(a, b, c, d, e, f, g, businessId, businessOp, numTrans, amountEquiv){
	var montoStr = a.replace(",","");
	url = "OperacionCBTFServlet";
	var query = {
		proceso:'comercio_exterior_bbva_pr',
		operacion:'consulta_operaciones_negociacion_op',
		accion:'negociarLinea',
		monto:montoStr,
		selectMoneda:b,
		selectOpe:c,
		selectCuentaOrden:d,
		descripNegociacion:e,
		ClienteCons:f,
		tipoFondoGiro:g,
		businessId:businessId,
		businessOp:businessOp,
		numTrans: numTrans,
		amountEquiv: amountEquiv
	};
	var respuesta = "";
	var peticion = {
		url : 'OperacionCBTFServlet',
		type : 'POST',
		dataType: "html",
		data : query,
		success : function(result, resultStatus) {
			const parser = new DOMParser();
			const data = parser.parseFromString(result, "text/html");
			let equivaPesos = data.getElementById("equivaPesos").value;
			let comision = data.getElementById("comision").value;
			let iva = data.getElementById("iva").value;
			let totalDebito = data.getElementById("totalDebito").value;
			let rate = data.getElementById("rate").value;
			if(equivaPesos==""||comision==""||iva==""||totalDebito==""||rate==""){
				$("#negolinea").prop('disabled', true);
			}else{
				$("#camp1").text("$ "+data.getElementById("tempor1").value);
				$("#camp2").text("$ "+data.getElementById("tempor2").value);
			 	$("#camp3").text("$ "+data.getElementById("tempor3").value);
				$("#camp4").text("$ "+data.getElementById("tempor4").value);
				$("#equivaPesos").val(equivaPesos);
				$("#comision").val(comision);
				$("#iva").val(iva);
				$("#totalDebito").val(totalDebito);
				$("#rate").val(rate);
			    $("#negolinea").prop('disabled', false).removeClass("buttonGiro").addClass("buttonGiroAzul");
			}		
		}
	};
	$.ajax(peticion);
}

// get bloter
function getBlotter(page=1, fromDate='NA', toDate='NA', nit='NA'){
	$(".btnBlotter").removeClass("bgGris").prop('disabled', false);
    $("#modificarPse").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);
    $("#finalizarPse").removeClass("azul").addClass( "bgGris" ).prop('disabled', true);
    $("#actualizarOperaPse").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);
    $("#actualizarOperaPse").css("display", "none");
    $("#btnDetalleBlotter").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);

	$("#tablaVerM").attr("data-fromDate",fromDate);
	$("#tablaVerM").attr("data-toDate",toDate);
	$("#tablaVerM").attr("data-nit",nit);
	let query={
		proceso:'comercio_exterior_bbva_pr',
		operacion:'consulta_operaciones_negociacion_op',
		accion:'getblotter',
		nit:nit,
		fromDate:fromDate,
		toDate:toDate,
	    page:page
	};
	var peticion = {
		url : 'OperacionCBTFServlet',
		type : 'POST',
		dataType: "html",
		data : query,
		success : function(result, resultStatus) {
			getBlotterResponse(result,page);
		}
	};
	$.ajax(peticion);
}

function getBlotterResponse(response, page){
	$("#tablaVerM").hide();
	let responseObj = JSON.parse(response);
	if(responseObj.status == "OK"){
		 context.fechaActual=responseObj.fecha_actual;
		let pageResponse =responseObj.data.pagination.page;
		page = parseInt(page);
		if(pageResponse>page) {
			let verMas=page+1;
			$("#tablaVerM").attr("data-pages",verMas);
			$("#tablaVerM").show();
		}else{
			$("#tablaVerM").hide();
		}
		if(page==1){
			$( "#tblBlotter tbody tr.registro" ).each( function(){
  				this.parentNode.removeChild(this); 
			});
		}
		let dataArray=responseObj.data.data;
		dataArray.forEach((resp) =>{
		let beneficiaryName="";
			resp.beneficiaries.forEach((beneficiario)=>{
				if(beneficiario.fullName == 'CAM59BENE1'){
					beneficiaryName=beneficiario.id;
				}
			});
		let nReintento="";
			resp.rates.itemizeRates.forEach((reintento)=>{
				if(reintento.rateType="REINTENTOS"){
					nReintento=reintento.itemizeRateUnit.percentage;
				} 
			});
		let ntasaDivisa="";
			resp.taxes.itemizeTaxes.forEach((tasaDivisa)=>{
				if(tasaDivisa.description="TRM DIV"){
					ntasaDivisa=tasaDivisa.itemizeTaxUnit.amount;
				} 
			});
		let avance = resp.advance.id;
		let tipavance="N.A";
		if(avance!="STD1"){
			tipavance=parseInt(avance);
		}
		
		
		
		
				
		$('#tblBlotter > tbody').append(`<tr class="registro">
		 <td class="selectora">
			 <input class="checkOperation" type="checkbox" name="row" 
		     data-estadogiro='${resp.status}'
             data-estadopse='${resp.pseStatus}'
			 data-estadoreintento='${nReintento}'
		     data-operation='${JSON.stringify(resp)}'  />
         </td>
		 <td>${formatDate(resp.operationDate)}</td>
         <td>${resp.id}</td>
         <td>${tipavance}</td>
         <td>${resp.advance.negotiatedAmount.currency}</td>
         <td>${getFormatAmount(resp.advance.negotiatedAmount.amount)}</td>
         <td>${getFormatAmount(ntasaDivisa)}</td>
         <td><p class="text10 ${getCassState(resp.status)}" id="textCtaPSE">
				<b class="text10 ${getLiteral(resp.status)}" style="text-decoration-line: underline;"> 			
					<a class="textCtaPSE Utoll ${getCassState(resp.status)}"  data-status="${resp.status}">${getLiteral(resp.status)}</a>
				</b>
			</p>
			
		 </td>
         <td>${beneficiaryName}</td>
         <td><p class="text10 ${getCassState(resp.pseStatus)}" id="textCtaPSE">
				<b class="text10" style="text-decoration-line: underline;" > 
					${getLiteral(resp.pseStatus)}
				</b>
			</p></td>
		</tr>
		`); 
		
    	});   
		renderPage(); 
		$('#filtrarBloter').hide();   
	}
}

function getFormatAmount(amount){		
	//return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return amount.toLocaleString('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2});
}

function formatDate(date){
	date=date.substring(0,10);
	date=date.replaceAll("-","/")
	return date;
}

function formatTime(date){
	date=date.substring(11,16);
	return date;
}

function getLiteral(literal){
   return $(`#${literal}`).val();
}

function filterBlotter(){
	let nitSelect = $("#selectNitBlotter").val();
	let nit=nitSelect.replace('-','').trim();
	let fromDate="NA"; // tomar los datos de los imputs y formatiar AAAA-MM-DD
	let toDate="NA"; // tomar los datos de los imputs y formatiar AAAA-MM-DD
	let fechaformat='';
	let fechaok=$("#filtroFechaDesdeBlo").val();
	if (fechaok != ''){		
		fechaformat=fechaok.split('-');
		fromDate=fechaformat[2]+'-'+fechaformat[1]+'-'+fechaformat[0];
	}
	fechaok=$("#filtroFechaHastaBlo").val();
	if (fechaok != ''){
		fechaformat=fechaok.split('-');		
		toDate=fechaformat[2]+'-'+fechaformat[1]+'-'+fechaformat[0];		
	}
	if(toDate=='NA' || fromDate=='NA'){
		alert('Diligencie la fecha correcta');
	}else{
		getBlotter(1,fromDate,toDate,nit);	
	} 
		
			
	
}

function showDetallePse(){
	
	let opcionSelect= $(".checkOperation:checked")[0];
	let op=JSON.parse($(opcionSelect).attr('data-operation'));
	let account = op.reimbursementAccount.account;
	let ordenante = getbeneficiario(op.id,account);
	let ntasaDivisa="";
	op.taxes.itemizeTaxes.forEach((tasaDivisa)=>{
		if(tasaDivisa.description="TRM DIV"){
					ntasaDivisa=tasaDivisa.itemizeTaxUnit.amount;
		} 
	});
	let operation ={
      id:op.id,	
      type:op.advance.id=='STD1'?'Detalle de Operaciones Realizadas Negociaci&oacute;n en L&iacute;nea':"Negociaci&oacute;n con mesa de dinero- Giro hacia el exterior",
      state:getLiteral(op.pseStatus),
      date:formatDate(op.operationDate),
      time:formatTime(op.operationDate),
      concept:ordenante.concepto,
      advanceId:op.advance.id,
   paymaster:{
	   id:op.beneficiaries.id,
	   name:op.beneficiaries.fullName
   },
   beneficiary:{
	   name:ordenante.beneficiario,
	   bank: ordenante.banco
   },
   chargeAmount:{
	   currency:op.chargeAmount.currency,
	   value:op.chargeAmount.amount
   },
   taxes:{
	   value:op.taxes.totalTaxes.amount
   },
   amount:{
	   value:op.advance.negotiatedAmount.amount,
	   currency:op.advance.negotiatedAmount.currency
   },
   fees:{
	   value:op.transferedTotalAmount.amount
   },
   totalDebited:{
	   value:op.netAmountSecureOnlinePayments.amount
   } 
   
};



let formatValueAmount = new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(operation.amount.value);
let formatValueTasaDivisa = new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(ntasaDivisa);
let formatValueChargeAmount = new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(operation.chargeAmount.value);
let formatTotataxes = new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(operation.taxes.value); 
let formatTotafees = new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(operation.fees.value);
let formatTotaldebited=new Intl.NumberFormat('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2}).format(operation.totalDebited.value);
let template=`
<div class="ventanaModal">
      <div class="modal-body">
         <div class="alineaDerecha"><a href="#" class="closeModal"><img src="/images/cerrarGrandeAzul.png"></a></div>
         <div class="plural row tituloModal ocultar">
            <div class="col-md-9">
               <h2></h2>
               <p class="subtituloPrincipal"></p>
            </div>
         </div>
         <div class="row modificarRow" id="datosModal">
            <title>${getLiteral("TITULO_MODAL")}</title>
            <h1>${getLiteral("TITULO_MODAL")}</h1>
            <h4>${getLiteral("SUBTITULO_MODAL")} </h4>
            <div class="panel-body detalleInvolucrado">
               <div>
                  <ul>
                     <li>
                        <b>${getLiteral("ESTADO_MODAL")}</b> ${operation.state}
                        <div class="capaConceptoOperacion flechaAbajo ocultar conceptoElevado">
                           <div class="cerrar">
                              <a href="#"><img src="/images/version7/cerrar.png"></a>
                           </div>
                           <div class="capaConceptoOperacionContenido"></div>
                        </div>
                     </li>
                     <li> </li>
                     <li><b>${getLiteral("DESCRIPC_MODAL")}:</b>  </li>
                     <li></li>
                  </ul>
               </div>
               <div class="row resumen">
                  <div class="col-md-5">
                     <ul>
                        <li><b>${getLiteral("TIPO_NEGO_MODAL")} </b> ${operation.type} </li>
                        <li><b>${getLiteral("FECHA_OPE_MODAL")}: </b> ${operation.date}</li>
                        <li><b>${getLiteral("REFERE_MODAL")} </b> ${operation.id}</li>
                        <li><b>${getLiteral("FECHA_VAL_MODAL")}:</b> ${operation.date}</li>
                        <li><b>${getLiteral("HORA_OPE_MODAL")}: </b> ${operation.time}</li>
                        <li><b>${getLiteral("ORDENANTE_MODAL")}: </b>     ${ordenante.ordenanteId} - ${ordenante.ordenante}</li>
                        <li><b>${getLiteral("BENEFI_MODAL")}: </b> ${operation.beneficiary.name}</li>
                        <li><b>${getLiteral("BANCO_BENEFI_MODAL")}: </b> ${operation.beneficiary.bank}</li>
                     </ul>
                  </div>
                  <div class="col-md-5">
                     <ul>
                        <li><b>${getLiteral("MONTO_MODAL")}: </b> ${formatValueAmount} ${operation.amount.currency}</li>
                        <li><b>${getLiteral("TASA_MODAL")}: ${operation.amount.currency}/COP: </b> ${formatValueTasaDivisa}</li>
                        <li><b>${getLiteral("EQUIV_PES_MODAL")}: </b>  ${formatValueChargeAmount}</li>
                        <li><b>${getLiteral("NUMERO_AVANCE_MODAL")} </b> ${operation.advanceId}</li>
                        <li><b>${getLiteral("CONCEPTO_MODAL")} </b> ${operation.concept}</li>
                        <li><b>${getLiteral("NIT_MODAL")} </b> ${ordenante.ordenanteId}</li>
                        <li><b>${getLiteral("NOMBR_NIT_MODAL")} </b> ${ordenante.ordenante}</li>
                        <li><b>${getLiteral("COMISIO_MODAL")} </b> ${formatTotafees}</li>
						<li><b>${getLiteral("IVA_MODAL")} </b> ${formatTotataxes}</li>
                     </ul>					 
                  </div>
				  <div class="col-md-pull-4">
                     <ul>
                        <li><b>${getLiteral("TOTAL_DEBITAR_MODAL")} </b> ${formatTotaldebited}</li>                        
                     </ul>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>`;
 $("#modalComun").html(template).css("display", "block");;
}

function getbeneficiario(opetation, account){
	let response={};
	let query={
		proceso:'comercio_exterior_bbva_pr',
		operacion:'consulta_operaciones_negociacion_op',
		accion:'beneficiario',
		numOperacion:opetation,
		cuentabenefica:account
	};
	var peticion = {
		async: false,
		url : 'OperacionCBTFServlet',
		type : 'POST',
		dataType: "html",
		data : query,
		success : function(result, resultStatus) {
			response=JSON.parse(result);
		}
	};
	$.ajax(peticion);
	return response;
}

function getCassState(state){
	let classState="";
	switch (state) {
  		case 'CLIENT_PROCESS':
    		classState="azulBlotter"
            break;
       case 'RETURN':
    		classState="rojoBlotter"
            break;
      case 'BANK_PROCESS':
    		classState="azulBlotter"
            break;
	  case 'REJECTED':
    		classState="rojoBlotter"
            break;
      case 'SENT_ABROAD':
    		classState="grisBlotter"
            break;
	  case 'PENDING':
    		classState="azulBlotter"
            break;
       case 'COMPLETED':
    		classState="verdeBlotter"
            break;
      case 'FAILED':
    		classState="rojoBlotter"
            break;
      case 'NO_PROCESS':
    		classState="azulBlotter"
            break;
      default:
       classState=""
    }
  return classState;
}

function redirectFinalizar(){
	let opcionSelect= $(".checkOperation:checked")[0];
	$("#operationObj").val($(opcionSelect).attr('data-operation'));
	document.getElementById("redirecPse").submit();
}

function statePago(status,tooltip){
	
	if (status === "CLIENT_PROCESS") {
		const estadoAyuda=`
		<p class="text12 textEstado" style="padding: 10px 5px 3px 5px;">
			<b class="nombre_estado">${getLiteral(status)}</b>
		<br>
		${getLiteral("MENSAJE_PSE_CLIENT_PROCESS")}
		</p>
		`;
			 
		tooltip.innerHTML = estadoAyuda;
	}else if(status == "SENT_ABROAD"){
		const estadoAyuda=`
		<p class="text12 textEstado" style="padding: 10px 5px 3px 5px;">
					<b class="nombre_estado">Enviada al exterior</b>
				<br>
					${getLiteral("MENSAJE_PSE_ABROAD")}
				</p>
		`;
		tooltip.innerHTML = estadoAyuda;
	}else if(status == "RETURN"){
		const estadoAyuda=`
		<p class="text12 textEstado" style="padding: 10px 5px 3px 5px;">
						<b class="nombre_estado">${getLiteral(status)}</b>
					<br>
					${getLiteral("MENSAJE_PSE_RETURN")}
					</p>
		`;
		tooltip.innerHTML = estadoAyuda;

	}else if(status == "REJECTED"){
		const estadoAyuda=`
		<p class="text12 textEstado" style="padding: 10px 5px 3px 5px;">
						<b class="nombre_estado">${getLiteral(status)}</b>
					<br>
					${getLiteral("MENSAJE_PSE_REJECTED")}
					</p>
		`;
		tooltip.innerHTML = estadoAyuda;
	}else if(status == "BANK_PROCESS"){
		const estadoAyuda=`
		<p class="text12 textEstado" style="padding: 10px 5px 3px 5px;">
						<b class="nombre_estado">${getLiteral(status)}</b>
					<br>
					${getLiteral("MENSAJE_PSE_BANK_PROCESS")}
					</p>
		`;
		tooltip.innerHTML = estadoAyuda;

	}
}

function renderPage(){
	let iframe= window.parent.document.getElementById('kyop-central-load-area');
    let alto= $('body').height();
   iframe.style.height=alto+'px';
}
function renderPagePse(){
	let iframe= window.parent.document.getElementById('kyop-central-load-area');
   iframe.style.height=1100+'px';
}

function redirectModificar(){
	let opcionSelect= $(".checkOperation:checked")[0];
	let dataString =$(opcionSelect).attr('data-operation');
	let opObj = JSON.parse(dataString);
	let noAvance=opObj.advance.id;
	let tipoOpe="T1"; 
	if(noAvance!="STD1"){
		tipoOpe="T2";
	}
	
	let id=opObj.id;
	let url = "OperacionCBTFServlet";
	let dataForm={
		proceso:"comercio_exterior_bbva_pr",
		operacion:"finalizar_operaciones_negociacion_op",
		accion:"modificarOperaciones",
		tipoOperacion:"T",
		selectOpe:tipoOpe,
		ppagina:"",
		refExtranjero:"NA",
		tipoFondoGiro:"fondoPSE",
		numOperacion:id,
		operationObj:dataString
	}
    sendForm(url,dataForm);
}

function redirectActualizar(){
	let opcionSelect= $(".checkOperation:checked")[0];
	let dataString =$(opcionSelect).attr('data-operation');
	let opObj = JSON.parse(dataString)
	
	let dataForm={
		proceso:"comercio_exterior_bbva_pr",
		operacion:"consulta_operaciones_negociacion_op",
		accion:"actualizarPse",
		idcuspse:opObj.pseOperationId
	};
	let peticion = {
		url : 'OperacionCBTFServlet',
		type : 'POST',
		dataType: "html",
		data : dataForm,
		success : function(result, resultStatus) {
			getBlotter();
		}
	}
	$.ajax(peticion);
}

function refrehPage(){
	setTimeout(function(){
       renderPagePse();
	}, 500);
}

function sendForm(url, data){
	 var form = document.createElement("form");
         form.method = "POST";
         form.action = url;
     for (const property in data) {
	     var input = document.createElement("input");
             input.setAttribute("type", "hidden");
             input.setAttribute("name", property);
             input.setAttribute("value", data[property]);
             form.appendChild(input);
	    }
      document.body.appendChild(form);
      form.submit();       
}

$(document).ready(function() {

   $( "#filtrarBloterCerrar" ).click(function() {
      $('#filtrarBloter').hide();
   });

   $( "#filtrarBlotterBtn" ).click(function() {
     $('#filtrarBloter').show();
   });

    $("#filtroBlotter").on("keyup", function() {
	      var value = $(this).val().toLowerCase();
		 $("#tblBlotter tr.registro").filter(function() {
	     	 $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });

    $(document).on("change", '.checkOperation', function(event) { 
	     var checked_status = this.checked;
         $(".checkOperation").prop('checked',false)
	     if(checked_status){
		    $(this).prop('checked',true)
	     }
        if($(".checkOperation:checked").length > 0){
	         $(".btnBlotter").removeClass("bgGris").prop('disabled', false);
             $("#modificarPse").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);
             $("#finalizarPse").removeClass("azul").addClass( "bgGris" ).prop('disabled', true);
             $("#actualizarOperaPse").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);
             $("#actualizarOperaPse").css("display", "none");

	          let estadoPse=  $(this).data('estadopse');
              let estadogiro=  $(this).data('estadogiro');
			  let estadoreintento= $(this).data('estadoreintento');
	          if(estadoPse=="COMPLETED"||estadoPse=="ABANDONED"||estadoPse=="REJECTED"){
				 if(estadogiro=="CLIENT_PROCESS" && estadoPse=="COMPLETED"){
			          $("#modificarPse").removeClass( "bgGris" ).addClass("verdeButtonBlotter").prop('disabled', false);
                      $("#finalizarPse").removeClass( "bgGris" ).addClass("azul").prop('disabled', false);
		           }else if(estadogiro=="RETURN" && estadoPse=="COMPLETED"){
			          $("#modificarPse").removeClass( "bgGris" ).addClass("verdeButtonBlotter").prop('disabled', false);
		           }else if(estadogiro=="CLIENT_PROCESS" && estadoPse=="ABANDONED"){
   		              $("#modificarPse").removeClass( "bgGris" ).addClass("verdeButtonBlotter").prop('disabled', false);
				   }else if(estadogiro=="CLIENT_PROCESS" && estadoPse=="REJECTED" && estadoreintento<3){
					  $("#modificarPse").removeClass( "bgGris" ).addClass("verdeButtonBlotter").prop('disabled', false);
				   }  
	          }else if(estadoPse=="PENDING"){
		          let op=JSON.parse($(this).attr('data-operation'));
                  let fechaOperacion=op.operationDate.split("T");
                  let fechaSistema=context.fechaActual;   
                  let d1 = new Date(fechaOperacion);
                  let d2 = new Date(fechaSistema);
                  let horas=(d2-d1)/3600000;
                  if(horas>1){
	              	$("#actualizarOperaPse").removeClass( "bgGris" ).addClass("verdeButtonBlotter").prop('disabled', false);
				  	$("#actualizarOperaPse").css("display", "inline");
                  }
			  }
        }else{
	         $(".btnBlotter").addClass( "bgGris" ).prop('disabled', true);
             $("#modificarPse").removeClass("verdeButtonBlotter").addClass( "bgGris" ).prop('disabled', true);
             $("#finalizarPse").removeClass("azul").addClass( "bgGris" ).prop('disabled', true);
        }

	});
	 
   if($("#li3").hasClass("activa")){
	   $( "#li3 a" ).trigger( "click" );
    } 
	$(document).on("mouseenter",".Utoll",function(event){
		const tooltip =document.querySelector(".msnAyudaDivPse");
		const estado = event.target;
		let status =estado.getAttribute("data-status");
		statePago(status,tooltip);
		$(".msnAyudaDivPse").removeClass( "ocultar" );
		calcularPosicionTooltip(estado, tooltip);
	});
	$(document).on("mouseleave",".Utoll",function(event){
		$(".msnAyudaDivPse").addClass( "ocultar" );
		calcularPosicionTooltip(estado, tooltip);
	});

	const calcularPosicionTooltip = (estado, tooltip) => {
		// Calculamos las coordenadas del estado.
		let x = estado.getBoundingClientRect().x;
		let y = estado.getBoundingClientRect().y;
		// Calculamos el tamaño del tooltip.
		const anchoTooltip = tooltip.clientWidth;
		const altoTooltip = tooltip.clientHeight;
		// Calculamos donde posicionaremos el tooltip.
		const izquierda = x + 83;
		const arriba = y - 85;

		tooltip.style.left = `${izquierda}px`;
		tooltip.style.top = `${arriba}px`;
	};
	$("#btnDetalleBlotter").click(function() {
	    showDetallePse();
       });
    $("#finalizarPse").click(function() {
	    redirectFinalizar();
		$("#finalizarPse").removeClass("azul").addClass( "bgGris" ).prop('disabled', true);
      });

    $("#tablaVerM").click(function() {
	    let page = $(this).attr("data-pages");
        let fromDate= $(this).attr("data-fromDate");
        let toDate= $(this).attr("data-toDate");
	    let nit= $(this).attr("data-nit");
        getBlotter(page,fromDate,toDate,nit);
     });

	$(".nav-tabs li").click(function() {
		$(".nav-tabs li").removeClass("active");
		$(this).addClass( "active" );
	});

	$(".nav-tabs li:first-child").click(function() {
		$(this).addClass( "active" );
	})
});






