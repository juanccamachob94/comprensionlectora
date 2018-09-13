/**
 * Para mostrar la ventana de cargando (la animación que aparece al iniciar sesión en el sistema)
 */
function mostrarCargando() {
    window['progress'] = setInterval(function() {
        var pbClient = PF('msgCargando'),
        oldValue = pbClient.getValue(),
        newValue = oldValue + 10;
        pbClient.setValue(pbClient.getValue() + 10);
        if(newValue === 100) clearInterval(window['progress']);
    }, 300);
    for (var i = 0; i < arguments.length; i++) PF(arguments[i]).show();
}

/**
 * Para ocultar la ventana de cargando (la animación que aparece al iniciar sesión en el sistema)
 */
function ocultarCargando() {
	for (var i = 0; i < arguments.length; i++) PF(arguments[i]).hide();
    clearInterval(window['progress']);
    PF('msgCargando').setValue(0);
}

function posicionarFijo() {
	$('body').css('position','fixed');
	try {
		setTimeout(function() {
			$('.ui-message-dialog').css('top', $('#primefacesmessagedlg_modal').height()/2-parseInt(($('.ui-message-dialog').css('height')).replace('px',''))/2 + 'px');
			$('.ui-message-dialog').css('left', $('#primefacesmessagedlg_modal').width()/2-parseInt(($('.ui-message-dialog').css('width')).replace('px',''))/2 + 'px');
		},10);
	} catch(ex){}
}


/**
 * Para mostrar un item de Primefaces-JSF
 * Se adicionó el cambio de posición del cuerpo del documento
 * para que el modal se adapte adecuadamente a la pantalla
 * y no al tamaño real del contenido de la página, lo que estaba
 * provocando que se posicionara más abajo de lo que debía
 */
function mostrar(p) {
	posicionarFijo();
	PF(p).show();
	$('body').css('position','inherit');
}

/**
 *Para ocultar un item de Primefaces-JSF
 */
function ocultar(p) {
	PF(p).hide();
}


$( document ).ready(function() {
	('#left img').css('max-height',('#left img').width());
});


$(window).resize(function() {
	$('#left img').height($('#left img').width());
	});