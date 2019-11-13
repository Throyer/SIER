$('#modal-deletar-edificio').on('shown.bs.modal', function (evento) {
    const button = $(evento.relatedTarget);

    let nome = button.data('nome');
    let id = button.data('id');

    let modal = $(this);

    modal.find('#nome-edificio')
        .text(nome);

    modal.find('#form-deletar-edificio')
        .attr('action', `/edificios/deletar/${id}`);
})
