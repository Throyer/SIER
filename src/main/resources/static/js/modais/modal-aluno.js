$('#modal-deletar-aluno').on('shown.bs.modal', function (evento) {
    const button = $(evento.relatedTarget);

    let nome = button.data('nome');
    let id = button.data('id');

    let modal = $(this);

    modal.find('#nome-aluno')
        .text(nome);

    modal.find('#form-deletar-aluno')
        .attr('action', `/alunos/deletar/${id}`);
})
