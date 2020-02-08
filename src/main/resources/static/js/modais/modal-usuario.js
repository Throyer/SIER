$('#modal-alternar-status-usuario').on('shown.bs.modal', function (evento) {
    const button = $(evento.relatedTarget);

    const nome = button.data('nome');
    const id = button.data('id');
    const status = button.data('status');

    let modal = $(this);

    modal.find('#acao')
        .text(status ? 'Bloquear' : 'Desbloquear');

    modal.find('.confirmacao')
        .text(status ? 'bloquear' : 'desbloquear');

    modal.find(".confirmacao")
        .removeClass(status ? "btn-success" : "btn-danger")

    modal.find(".confirmacao")
        .addClass(status ? "btn-danger" : "btn-success")

    modal.find('#nome-usuario')
        .text(nome);

    modal.find('#form-alternar-status-usuario')
        .attr('action', `/usuarios/alternarAtivoOuInativo/${id}`);
});

$('#modal-deletar-usuario').on('shown.bs.modal', function (evento) {
    const button = $(evento.relatedTarget);

    const nome = button.data('nome');
    const id = button.data('id');

    let modal = $(this);

    modal.find('#usuario-nome')
        .text(nome);

    modal.find('#form-deletar-usuario')
        .attr('action', `/usuarios/deletar/${id}`);
})
