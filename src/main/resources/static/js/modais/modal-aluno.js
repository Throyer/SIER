$('#modal-alternar-status-aluno').on('shown.bs.modal', function (evento) {
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
        .addClass(status ? "btn-danger" : "btn-success")

    modal.find('#nome-aluno')
        .text(nome);

    modal.find('#form-alternar-status-aluno')
        .attr('action', `/alunos/alternarAtivoOuInativo/${id}`);
})
