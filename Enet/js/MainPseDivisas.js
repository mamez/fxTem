/**Inicializador */
$(document).ready(function(){
    funcionesFormularios();
    funcionesAlertas();
    funcionesTap();
    funcionesModal();
	$(".nav-tabs > li").click(function() {
	     $(".nav-tabs > li").removeClass("active");
	     $(this).addClass( "active" );
	});
	$(".nav-tabs li:first-child").click(function() {
	     $(this).addClass( "active" );
	});
	$('#btnDesPagos').click(function () {
		$('#btnDesPagos').attr('disabled', true);
		$("#btnDesPagos").addClass("buttonGiro");
		$("#btnDesPagos").removeClass("buttonGiroAzul");
		$('.checkpse').click(function () {
		if($('input.radio:checked').length == 0){
			$("#btnDesPagos").removeAttr('disabled');
			$("#btnDesPagos").removeClass("buttonGiro");
			$("#btnDesPagos").addClass("buttonGiroAzul");
		}
		});
		$('#btnNuevaConsulta').click(function () {
			$("#btnDesPagos").removeAttr('disabled');
			$("#btnDesPagos").removeClass("buttonGiro");
			$("#btnDesPagos").addClass("buttonGiroAzul");
		});
		if ($('#messageBoxNOK2').is(':visible')) {
			$("#btnDesPagos").removeAttr('disabled');
			$("#btnDesPagos").removeClass("buttonGiro");
			$("#btnDesPagos").addClass("buttonGiroAzul");
		}
	});
    $('input[type="text"]').keypress(function(){
	if($("#valorDesde").val() != '' && $("#valorHasta").val() != '') {
			$("#fechaHasta").on('DOMSubtreeModified',function(){
	         $("#btnGuardarPagos").removeAttr('disabled');
			 $("#btnGuardarPagos").removeClass("buttonGiro");
			 $("#btnGuardarPagos").addClass("buttonGiroAzul");
    	});
	}
   });
	$('#btnGuardarPagos').click(function () {
		$('#btnGuardarPagos').attr('disabled', true);
		$("#btnGuardarPagos").addClass("buttonGiro");
		$("#btnGuardarPagos").removeClass("buttonGiroAzul");
	if ($('#messageBox').is(':visible')) {
			$("#btnGuardarPagos").attr('disabled', true);
			$("#btnGuardarPagos").removeClass("buttonGiroAzul");
			$("#btnGuardarPagos").addClass("buttonGiro");
		}
	});
	 $('.currency').keyup(function(e) { 
		 setTimeout(() => { 
		 let parts = $(this).val().split(".");
		 let v = parts[0].replace(/\D/g, ""), dec = parts[1] 
		 let n = new Intl.NumberFormat('en-US').format(v); 
		 n = dec !== undefined ? n + "." + dec : n; $(this).val(n); 
		 })
	 })
});

function funcionesModal(){
	$("div.tab-content,#modalComun").on("click", ".globalSelector", function () {
    if (typeof ($("div.contentTodosVisibles input[name=todasVisibles]:checked").val()) === "undefined") {
        $(".aTodasVisibles").click();
        $(".divTodasVisibles div a").click();
        $("div.contentTodosVisibles input[name=todasVisibles]:checked").prop("checked", !0);
    }
    if ($("div.contentTodosVisibles input[name=todasVisibles]:checked").val() == "true") {
        $(".aTodasVisibles").click();
        $("#rButtonTodasVisibles0").prop("checked", $(".globalSelector").is(":checked"));
    } else {
        if ($("div.contentTodosVisibles input[name=todasVisibles]:checked").val() == "true") {
            $(".aTodasVisibles").click();
            $("#rButtonTodasVisibles1").prop("checked", $(".globalSelector").is(":checked"));
        }
    }
})
}

function funcionesAlertas(){
	//Ocultar y mostrar div de alerta 
	$('.interior3').on('click', function () {
        $("#messageBox").addClass('ocultar');
    });
    
    $('.interior3').on('click', function () {
        $("#messageBox2").addClass('ocultar');
    });
    	$('.interior3').on('click', function () {
        $("#messageBoxNOK").addClass('ocultar');
    });
}

function funcionesTap(){
  //Mostrar boton de nueva consulta
  $('#btnDesPagos').on('click', function () {
     $("#btnNuevaConsulta").removeClass('ocultar');
     $("#tablaResultados").removeClass('ocultar');
	 $("#tablaConsulta").removeClass('ocultar');
	 $("#tablaVermas").removeClass('ocultar');			
   });

 //Funciones tap
  $('#btnNuevaConsulta').on('click', function () {
		$("#tablaResultados").addClass('ocultar');
		$("#tablaConsulta").addClass('ocultar');
		$("#tablaVermas").addClass('ocultar');
		$("#btnNuevaConsulta").addClass('ocultar');
		document.querySelector('#radioCheck').checked = true;
		UI.calendar("RangoDesde").disable()
        UI.calendar("RangoHasta").disable()
    });

    /**Recarga de segunda pestaña limpia */
     $('.activeButtonPane').on('click', function () {
		    $("#btnNuevaConsulta").addClass('ocultar');
			$("#tablaConsulta").addClass('ocultar');
			$("#tablaResultados").addClass('ocultar');
			$("#tablaVermas").addClass('ocultar');	
			UI.calendar("fechaHasta").disable();
	    });
}


function funcionesFormularios(){
	/**funciones campos numericos */
	$('.currency').on('keypress', function() {
    return  event.charCode==46 || event.charCode >= 48 && event.charCode <= 57;
   });
    
    /**Decimales campos moneda */
//	$(".currency").on({
//	    "focus": function(event) { 
//         $(event.target).select();
//	    },
//	    "keypress": function(event) {
//	        var val = $(this).val();            
//	        $(this).val(val);
//	        $(this).val(function(index, value) {
//	            return value.replace(/\D/g, "")
//	            .replace(/([0-9])([0-9]{2})$/, '$1.$2')
//            .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ".");
//	        });
//	    }, 
//	    "change":function(event){
//	        var val = $(this).val() + ',00';
//	        $(this).val(val)	    }
//	});
	
	
	
	/**Funciones check */
	$(document).on('click', 'div#tablaResultados input[type=checkbox]', function (e) {
		let numCheck=0;
		$('div#tablaResultados input[type=checkbox]').each(function() {
	    if($(this).is(":checked")) {
	          numCheck++;
		   }
		});
		if(numCheck==0){
			$("#btnEliminarPse").hide();
		}else{
			$("#btnEliminarPse").show();
		}
	});
	
	//fecha enabled
	  $("input[type='radio']").click(function () {
        marcado = $("#rangoFechas").is(":checked");
        if (marcado) {
            UI.calendar("RangoDesde").enable()
            UI.calendar("RangoHasta").enable()
        }
        else {
            UI.calendar("RangoDesde").disable()
            UI.calendar("RangoHasta").disable()
        }
    });
    $("#buscador").keyup(function () {
        if ($(this).val() != "") {
            $("#tablaConsulta tbody>tr").hide();
            $("#tablaConsulta td:contains('" + $(this).val().toLowerCase() + "')").parent("tr").show();
        }
        else {
            $("#tablaConsulta tbody>tr").show();
        }
    });

       $('.activeButton').on('click', function () {
		    $("form select").each(function() { this.selectedIndex = 0 });
     		$("form input[type=text] , form textarea").each(function() { this.value = '' });	
			UI.calendar("fechaHasta").enable()		
	    });
}


/** Cambio de pestaña modulos */
function FuncionAdd1() {
    $("#consultasPse").removeClass("active");
    $("#pagosPse").addClass("active");
}

function FuncionAdd2() {
    $("#pagosPse").removeClass("active");
    $("#consultasPse").addClass("active");
    $('#usuarios tbody').empty();  
    $('.linkverMas').attr("data-page",1);
    $( "#radioCheck" ).trigger( "click" );
    UI.calendar("RangoDesde").disable();
    UI.calendar("RangoHasta").disable();
}


/**Configuracion de usuario */

function configurarUsuarioReq() {		
	if(validarConfiguracion()){
	   var url = '/scopeco/servlet/PIBEE?proceso=gestion_pagos_pse_divisas_pr&operacion=gestion_pagos_pse_divisas_op&accion=configurarUsuario';
       var formData = {};
       formData["tipoPago"] = "P";
       formData["usuarioAutoIn"] = $("#usuario").val();
       let valordesde=$("#valorDesde").val().replaceAll(",", '')
       formData["valorTransacDesde"] =valordesde ;
       let valorHasta=$("#valorHasta").val().replaceAll(",", '')
       formData["valorTransacHasta"] = valorHasta;
       formData["fechaTransac"] = formatDateRequest('fechaHasta');
       $("#messageGuardar").removeClass('ocultar');
					setTimeout(function() { 
				        $("#messageGuardar").addClass('ocultar');
				    }, 10000);
	   ajaxRequest(url, formData, configurarUsuarioResp);
      }				
}

function configurarUsuarioResp(data) {
	let mensaje = getLiteralMesaje(data.msg);
	if(data.status == 'OK'){
		pushMessage(mensaje, data.status);
		$("form select").each(function() { this.selectedIndex = 0 });
     		$("form input[type=text] , form textarea").each(function() { this.value = '' });	
		UI.calendar("fechaHasta").enable()	
 	}else{
		pushMessage(mensaje, data.status);
	}
   
}

function validarConfiguracion(){
	let valDesde = $('#valorDesde').val().trim();
    let valHasta = $('#valorHasta').val().trim(); 
    let fechaHasta=formatDateRequest('fechaHasta');
    
    if(valDesde=="" || valHasta==""|| fechaHasta.length != 10){
		$('#messageRequerido').show(1000);
		setTimeout(function() { 
				$("#messageRequerido").hide('ocultar');
			}, 3000);
      return false;
    }else{
	   valDesde= Number(valDesde.replaceAll(".", '').replaceAll(",", '.'));
       valHasta= Number(valHasta.replaceAll(".", '').replaceAll(",", '.'));
       let fechaActual=new Date(fechaSist); ;
       let fechaForm=new Date(formatDateRequest('fechaHasta'));
       let fechaDif=fechaForm-fechaActual;
       if(valDesde < 1) {
         $("#messageValorDesde").removeClass('ocultar');
				setTimeout(function() { 
				$("#messageValorDesde").addClass('ocultar');
			}, 4000);
			$('.interior3').on('click', function () {
        	$("#messageValorDesde").addClass('ocultar');
   		 });
		return false;
     }else if(valDesde > valHasta){
	    $("#messageValor").removeClass('ocultar');
				setTimeout(function() { 
				$("#messageValor").addClass('ocultar');
			}, 4000);
		$('.interior3').on('click', function () {
        	$("#messageValor").addClass('ocultar');
   		 });
	    return false;
     }else if(fechaDif<0){
	     // mensaje la fecha debe de ser superior o igual a la de hoy
         $("#messageFechaActual").removeClass('ocultar');
				setTimeout(function() { 
				$("#messageFechaActual").addClass('ocultar');
			}, 4000);
			$('.interior3').on('click', function () {
        	$("#messageFechaActual").addClass('ocultar');
   		 });
	    return false;
     }
   }
	return true;
}

/**Funcionalidad del listador de usuarios autorizados */
function consultarAutorizado(){
    $('#usuarios tbody').empty();
    $('.linkverMas').attr("data-page",1); 
    getUsers();	
}

function getUsers(){
	$("#btnEliminarPse").hide();
	let url = '/scopeco/servlet/PIBEE?proceso=gestion_pagos_pse_divisas_pr&operacion=gestion_pagos_pse_divisas_op&accion=listarAutorizados';
    let formData = {};
    let dias=$('input[name="parambus"]:checked').val();
    if(validarListador(dias)){
	    if(dias >= 0){
		  formData["numDias"] = dias;
		  formData["fechaDesde"] = "";
	      formData["fechaHasta"] = "";
	    }else{
		 formData["numDias"] = "NA";
	     formData["fechaDesde"] = formatDateRequest("RangoDesde");
	     formData["fechaHasta"] = formatDateRequest("RangoHasta");
	    }
	    formData["numTransa"] = "";
	    formData["pagina"] = $('.linkverMas').attr("data-page");
	  ajaxRequest(url, formData, getUserResponde);
   }else{
	 console.log("Rango de fechas invalido");
	$("#messageBoxNOK2").removeClass('ocultar');
	$('.interior3').on('click', function () {
	$("#messageBoxNOK2").addClass('ocultar');
	});
	
  }
 }
   
function validarListador(dias){
	try{
		if(dias<0){
		let rangoDesde=formatDateRequest("RangoDesde");
		let rangoHasta= formatDateRequest("RangoHasta");
		let dateDesde = new Date(rangoDesde);
        let dateHasta = new Date(rangoHasta);
		if((dateDesde==dateHasta) || (dateHasta > dateDesde)){
			return true;
		}else if(dateHasta < dateDesde){
			$("#messageBoxNOK3").removeClass('ocultar');
			$('.interior3').on('click', function () {
		    $("#messageBoxNOK3").addClass('ocultar');
		    });
		}else{
			return false;
		}	
	 }
	}catch (e){
		return false;
	}
   return true;
}

function getUserResponde(data){
	let usuarios=data.usuarios;
	let pageResponse=data.page;
	let page = $('.linkverMas').attr("data-page");
	if(usuarios.length > 0){
		usuarios.forEach(function(usuario) { 
		let template=buildTr(usuario);
		$('#usuarios > tbody').append(template);
      });
	}else{
		$('#usuarios > tbody').append( buildTrClean());
	}
	
	if(pageResponse == page){
		$('.linkverMas').css('display', 'none');
		$('.linkverMas').attr("data-page",1); 
	}else{
		$('.linkverMas').css('display', 'block')
		$('.linkverMas').attr("data-page",pageResponse); 
	}
	ajustar();
}

/**Funcionalidad de eliminar configuracion */
function eliminarConfiguracion(){
	let url = '/scopeco/servlet/PIBEE?proceso=gestion_pagos_pse_divisas_pr&operacion=gestion_pagos_pse_divisas_op&accion=eliminarConfiguracion';
	let selected = [];
	$('div#tablaResultados input[type=checkbox]').each(function() {
	   if($(this).is(":checked")) {
	     let numTran = $(this).data('id');
	      selected.push(numTran);
	   }
	});
	
  if(validarEliminacion(selected)){
	let numeroTrans="";
	for (let value of selected) {
		numeroTrans=numeroTrans+"&"+value;
	}
	let formData = {};
    formData["numTransa"] = numeroTrans;
	ajaxRequest(url, formData, eliminarConfiguracionResp);
   }
}

function validarEliminacion(selected){
	if(selected.length == 0){
		return false;
	}
	return true;
}

function eliminarConfiguracionResp(data){
	if(data.status == 'OK'){
		pushMessageElimi(data.msg, "OK");
		$('#usuarios tbody').empty();  
        getUsers();
 	}else{
		pushMessageElimi(data.msg, "NOK");
	}
}

/**Funciones generales */

function formatDateRequest(calendar) {
	try{
		let fecha = _$$R(calendar).getValue();
        let fechaFormat = fecha.substring(6, 10) + "-" + fecha.substring(3, 5) + "-" + fecha.substring(0, 2);
        return fechaFormat;
	}catch(error){
		throw error;
	}
}

/*** Operaciones modal */
function openModal(item){
	let usuario = $(item).data('user');
	$("#mod-tipoPago").text("PUNTUAL");
	$("#mod-consecutivo").text(usuario.numeroTrans);
	$("#mod-usuariAutori").text(usuario.usuarioAudi);
	$("#mod-valorDesd").text(formatCurrency(usuario.valorDesde));
	$("#mod-cantidaPagos").text("1");
	$("#mod-valorHas").text(formatCurrency(usuario.valorHasta));
	$("#mod-fechaAutor").text(usuario.fechaTrans);
	$("#mod-estado").text(usuario.estado);
	$("#mod-ipTerminal").text(usuario.ipUsuarioAudi);
	$("#mod-transaEjec").text(usuario.transaccioEject);
	$("#mod-usuarioEjec").text(usuario.usuarioAuto);
	$("#mod-MontoEjec").text(usuario.valorUtilizado);
	$("#mod-FechaUltim").text(usuario.fechaEjecu);
	$('#myModal').modal('toggle');
}

/**manejo de ajax */
function driverAjax(state){
	if(state=="IN_SENT"){
		$('.formAjax').prop('disabled', true);
	}else{
		$('.formAjax').prop('disabled', false);
	}
}

function formatCurrency(value){
	let currencyLocale = Intl.NumberFormat('en-US');
	return currencyLocale.format(value);
}

function getLiteralMesaje(id){
		let msg= $("#"+id).val();
		return msg;
}

function buildTr(user){
		let sUser=JSON.stringify(user);
		let valEstado = user.estado == "CREADO" ? "" : 'disabled' ;
		let template=`<tr>
		<td class="selectora">
            <input class="selectall" data-id='${user.numeroTrans}' type="checkbox" ${valEstado} name="row" />
        </td>
        <td>
			<a href="#" data-user='${sUser}' class="botonIcono" type="button" onclick="openModal(this)">
				PUNTUAL
			</a>
		</td>
		<td>${user.usuarioAuto}</td>
		<td>${user.numeroTrans}</td>
		<td>${formatCurrency(user.valorDesde)}</td>
		<td>${formatCurrency(user.valorHasta)}</td>
		<td>${user.hora}</td>
		<td>${user.estado}</td>
		<td>${user.usuarioAudi}</td>
		<td>${user.fechaTrans}</td>
		<td>${user.ipUsuarioAudi}</td>
		</tr>
		`;                                                       
      return template;                              
	}
	
function buildTrClean(){
		return `<tr><td colspan="11">No se encontraron registros</td></tr>`;
} 

function Validacion(){
		var TotalCuentas = '';
		var Cantidad = 0;
		$('td > input').each(function(){
			if($(this).prop("checked")) {
				alert("Total cuentas"+TotalCuentas);
				TotalCuentas += $(this).attr('name')+'&';
				Cantidad++;
			}
		});
		if(Cantidad == 0){
			$('#Error').fadeIn('slow');
			$('#myModal').modal('hide');
		}
	}

function ajaxRequest(urlRequest, form, functioExecute) {
    $.ajax({
        url: urlRequest,
        type: 'post',
        dataType: "html",
        contentType: 'application/x-www-form-urlencoded',
        data: form,
        beforeSend: function () {
           driverAjax("IN_SENT");
        },
        complete: function () {
             driverAjax("SENT");
        },
        success: function (data, textStatus, jQxhr) {
	        driverAjax("SENT");
	        const obj = JSON.parse(data);
            functioExecute(obj);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.error(errorThrown, textStatus);
            driverAjax("SENT");
        }
    });
}

function pushMessage(msg, type){
	let pestana=$("#pagosPse").hasClass("active")?1:2;
	mensaje = `<li>${msg}</li>`;
	if(pestana==1){
		if(type=='OK'){
		$("#messageBoxULOk").empty().append(mensaje);
		$("#messageBox").removeClass('ocultar');
		setTimeout(function() { 
		$("#messageBox").addClass('ocultar');
		}, 5000);
	} else{
		$("#messageBoxULNOK").empty().append(mensaje);
		$("#messageBoxNOK").removeClass('ocultar');
	  }
	}else{
		//mensaje de panatalla 2
	}
	
}

function pushMessageElimi(msg, type){
	mensaje = `<li>${msg}</li>`;
	if(type=='OK'){
		$("#messageBoxULOk").empty().append(mensaje);
		$("#messageBox2").removeClass('ocultar');
		setTimeout(function() { 
		$("#messageBox2").addClass('ocultar');
		}, 5000);
	} else{
		$("#messageBoxULNOK").empty().append(mensaje);
		$("#messageBoxNOK").removeClass('ocultar');
	}
}

function ajustar(){
	try {
 		let iframe= window.parent.document.getElementById('kyop-central-load-area');
		let alto= $('body').height();
		iframe.style.height=alto+'px';
	}
	catch(err) {
 		console.log("Error al encontrar funcion KYOP");
	}
			
}

