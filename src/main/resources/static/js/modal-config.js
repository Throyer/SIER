/**
 * 
 */
$('#delete-edificio').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});

$('#delete-aluno').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});

/**
 * 
 */
$('#show-aluno').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  
  var nome = button.data('nome');
  var sobrenome = button.data('sobrenome');
  var email = button.data('email');
  var turma = button.data('turma');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});

/**
 * 
 */
$('#show-cep').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});





