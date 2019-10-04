$('#delete-edificio').on('show.bs.modal', (evento) => {
  
  let button = $(evento.relatedTarget);
  
  let nome = button.data('nome');
  let id = button.data('id');
  
  let modal = $(this);

  modal.find('#nome').text(nome);

  modal.find('#excluir').attr('value', id);
});





