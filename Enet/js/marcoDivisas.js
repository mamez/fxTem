var host = window.location.host;
console.info("El host encontrado es: " + host);
if (host.indexOf("bbvanetcash.com") != -1) {
	netJS = 'https://www.bbvanetcash.com.co/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js';
} else if (host.indexOf("150.250.251.65:843") != -1) {
	netJS = 'https://150.250.251.65:843/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js';
}//INI Global NetCash 08/01/2019 - Niv
else if (host.indexOf("ei-bbvaglobalnetcash.igrupobbva") != -1) {
	netJS = 'https://ei-bbvaglobalnetcash.igrupobbva/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js'; //Calidad
} else if (host.indexOf("bbvaglobalnetcash.com") != -1) {
	netJS = 'https://www.bbvaglobalnetcash.com/SCOKYOP/kyop_mult_web_pub/external/utilPortalKyop.js'; //Produccion
} //FIN Global NetCash 08/01/2019 - Niv
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

function ajustar() {
	try {
		let iframe = window.parent.document.getElementById('kyop-central-load-area');
		let alto = $('body').height();
		iframe.style.height = alto + 'px';
	}
	catch (err) {
		console.log("Error al encontrar funcion KYOP");
	}

}
