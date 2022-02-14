-- DELETAR HISTORICO --

drop table tb_condominio cascade constraints;
drop table tb_morador cascade constraints;
drop table tb_prestador cascade constraints;
drop table tb_registro_condominio cascade constraints;
drop table tb_registro_servico cascade constraints;
drop table tb_servico cascade constraints;
drop sequence sq_tb_condominio;
drop sequence sq_tb_morador;
drop sequence sq_tb_prestador;
drop sequence sq_tb_registro;
drop sequence sq_tb_servico;


-- CRIAÇÃO DE SEQUENCES --

create sequence sq_tb_condominio start with 1 increment by 1;
create sequence sq_tb_morador start with 1 increment by 1;
create sequence sq_tb_prestador start with 1 increment by 1;
create sequence sq_tb_registro start with 1 increment by 1;
create sequence sq_tb_servico start with 1 increment by 1;


-- CRIAÇÃO DE TABELAS --

CREATE TABLE tb_condominio (
    id_condominio  NUMBER(10) NOT NULL,
    nm_condominio  VARCHAR2(100) NOT NULL,
    endereco       VARCHAR2(200) NOT NULL
);

ALTER TABLE tb_condominio ADD CONSTRAINT tb_condominio_pk PRIMARY KEY ( id_condominio );

CREATE TABLE tb_morador (
    id_morador  NUMBER(10) NOT NULL,
    nm_morador  VARCHAR2(100) NOT NULL,
    nr_morador  NUMBER(19) NOT NULL
);

ALTER TABLE tb_morador ADD CONSTRAINT tb_morador_pk PRIMARY KEY ( id_morador );

CREATE TABLE tb_prestador (
    id_prestador  NUMBER(10) NOT NULL,
    nm_prestador  VARCHAR2(100) NOT NULL,
    telefone      NUMBER(19) NOT NULL
);

ALTER TABLE tb_prestador ADD CONSTRAINT tb_prestador_pk PRIMARY KEY ( id_prestador );

CREATE TABLE tb_registro_condominio (
    tb_condominio_id_condominio  NUMBER(10) NOT NULL,
    tb_morador_id_morador        NUMBER(10) NOT NULL
);

CREATE TABLE tb_registro_servico (
    id_registro                NUMBER(10) NOT NULL,
    dt_inicio                  TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    dt_termino                 TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    tb_prestador_id_prestador  NUMBER(10) NOT NULL,
    tb_morador_id_morador      NUMBER(10) NOT NULL,
    tb_servico_id_servico      NUMBER(10) NOT NULL
);

ALTER TABLE tb_registro_servico ADD CONSTRAINT tb_registro_servico_pk PRIMARY KEY ( id_registro );

CREATE TABLE tb_servico (
    id_servico  NUMBER(10) NOT NULL,
    nm_servico  VARCHAR2(255) NOT NULL
);

ALTER TABLE tb_servico ADD CONSTRAINT tb_servico_pk PRIMARY KEY ( id_servico );

--  CHAVES ESTRANGEIRAS  --

ALTER TABLE tb_registro_condominio
    ADD CONSTRAINT tb_condominio_fk FOREIGN KEY ( tb_condominio_id_condominio )
        REFERENCES tb_condominio ( id_condominio );

ALTER TABLE tb_registro_condominio
    ADD CONSTRAINT regis_condo_mor_fk FOREIGN KEY ( tb_morador_id_morador )
        REFERENCES tb_morador ( id_morador );

ALTER TABLE tb_registro_servico
    ADD CONSTRAINT regis_serv_mor_fk FOREIGN KEY ( tb_morador_id_morador )
        REFERENCES tb_morador ( id_morador );

ALTER TABLE tb_registro_servico
    ADD CONSTRAINT regis_serv_prest_fk FOREIGN KEY ( tb_prestador_id_prestador )
        REFERENCES tb_prestador ( id_prestador );

ALTER TABLE tb_registro_servico
    ADD CONSTRAINT regis_serv_serv_fk FOREIGN KEY ( tb_servico_id_servico )
        REFERENCES tb_servico ( id_servico );