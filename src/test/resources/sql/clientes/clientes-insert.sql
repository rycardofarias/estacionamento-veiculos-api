insert into USUARIOS (id, username, password, role) values (100, 'admin@email.com', '$2a$12$yHyEFmRcQQFPDo4dmEPqSu1f8ANyzg2U/YNkn.wfbVNFA5opcbiEG', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role) values (101, 'ricardo@email.com', '$2a$12$yHyEFmRcQQFPDo4dmEPqSu1f8ANyzg2U/YNkn.wfbVNFA5opcbiEG', 'ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (102, 'bob@email.com', '$2a$12$yHyEFmRcQQFPDo4dmEPqSu1f8ANyzg2U/YNkn.wfbVNFA5opcbiEG', 'ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (103, 'toby@email.com', '$2a$12$yHyEFmRcQQFPDo4dmEPqSu1f8ANyzg2U/YNkn.wfbVNFA5opcbiEG', 'ROLE_CLIENTE');

insert into CLIENTES (id, nome, cpf, id_usuario) values (10, 'Ricardo Farias', '47851634024', 101);
insert into CLIENTES (id, nome, cpf, id_usuario) values (11, 'Bob Sousa', '16615177064', 102);