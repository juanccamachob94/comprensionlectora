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
