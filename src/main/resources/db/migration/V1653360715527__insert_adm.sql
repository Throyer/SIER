INSERT INTO `role`
    (`name`)
VALUES
	('ADMINISTRADOR'),
	('PROFESSOR'),
	('ALUNO');

INSERT INTO `user`
    (`name`, nick_name, email, `password`, role_id)
VALUES
    ('admin', 'admin', 'admin@email.com', '$2a$10$niWZuIvIHvqXnlTFv/7v1O0.LaBuUqjy/pWqP7rbE7.hMzLdLq1aC', 1);
