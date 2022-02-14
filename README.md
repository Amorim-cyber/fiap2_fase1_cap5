<h1>FIAP Fase 1 : Capítulo 5 </h1>

<h3>Início</h3>

No <a href="https://github.com/Amorim-cyber/fiap2_fase1_cap4">projeto do capítulo 4</a> montei o modelo lógico de um sistema que facilite o encontro de moradores de condomínios com prestadores de serviços. O sistema apresenta também um controle de registro de serviço caso o morador queira contratar o prestador.  A relação ficou da seguinte forma: 

<img src="assets/tabelas.PNG">

* <b>tb_condominio:</b> Tabela que vai armazenar dados de condomínio. Contém o nome e endereço do condomínio.
* <b>tb_morador:</b> Tabela que vai armazenar dados de morador. Contém nome e número da sua morada. 
* <b style="color:grey">Relação condominio_morador:</b> Um condomínio pode ser habitado por um ou mais moradores contudo um morador deve pelo menos um condomínio.
* <b>tb_condominio:</b> Um condomínio pode conter n moradores, assim como um morador pode ter posses em n condomínios. Tabela auxiliar `tb_condominio` serve para normalizar essa relação.
* <b>tb_servico:</b> Tabela que vai armazenar dados do serviço. Contém o nome do serviço.
* <b>tb_prestador:</b> Tabela que vai armazenar dados do prestador de serviço. Contém o nome, número de telefone do prestador.
* <b>tb_registro_servico:</b> Tabela que vai armazenar dados do registro de serviço. Contém a data de inicio e a data de fim de serviço.
* <b style="color:grey">Relação servico_registro:</b> Um tipo de serviço pode ser registrado, contudo um registro deve conter um tipo de serviço.
* <b style="color:grey">Relação prestador_registro:</b> Um prestador de serviço pode ser registrado, contudo um registro deve conter um prestador de serviço.
* <b style="color:grey">Relação morador_registro:</b> Um morador pode ser registrado, contudo um registro deve conter um morador.

Conforme solicitado, este projeto tem como objetivo montar o script de criação de tabelas, incluir as relações entre entidades no programa e implementar as operações de CRUD.

<HR> 

<h3>Montar o script</h3>

O script que efetua o `create` das tabelas, sequências e chaves estrangeiras foi desenvolvido da seguinte forma:

`````sql
-- DELETAR HISTORICO (COMPUTE CASO AS ESTRURAS JA EXISTAM NO BANCO) --

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
`````

<b>OBS:</b> O programa em `java` efetua automaticamente todo o processo acima com o `hibernate`. Contudo caso haja algum problema, você pode utiliza-ló para criar manualmente as estruturas no seu banco. 

<hr>

<h3>Montar relação entre entidades</h3>

Vamos formalizar as relações em nosso programa.

* <b>Relação Muitos para Muitos:</b> 

  As entidades `Morador` e `Condomínio` possuem relação N x N. Adicionamos as seguintes linhas de código nos seguintes arquivos:

  <b>Morador.java</b>

  ````java
  @ManyToMany
  	@JoinTable(joinColumns = @JoinColumn(name="id_morador"), 
  	inverseJoinColumns = @JoinColumn(name="id_condominio"), name = "tb_registro_condominio")
  	private List<Condominio> condominios;
  ````

   <b>Condominio.java</b>

  ````java
  @ManyToMany(mappedBy="condominios")
  	private List<Morador> moradores;
  ````

* <b>Relação Muitos para Um:</b>

  A entidade `Registro Serviço` possui uma relação de muitos para um com as entidades `Serviço`, `Morador` e `Prestador`. Adicionamos as seguintes linhas de código no arquivo  <b>Registro.java</b>:

  ````java
  @JoinColumn(name = "id_servico")
  	@ManyToOne
  	private Servico tipoServico;
  	
  	@JoinColumn(name = "id_morador")
  	@ManyToOne
  	private Morador morador;
  	
  	@JoinColumn(name = "id_prestador")
  	@ManyToOne
  	private Morador prestador;
  ````

* <b>Relação Um para Muitos:</b>

  As entidades  `Serviço`, `Morador` e `Prestador` possuem uma relação de um para muitos em relação a `Registro Serviço`. Adicionamos as seguintes linhas de código nos seguintes arquivos:

  <b>Morador.java</b>

  ````java
  @OneToMany(mappedBy = "morador")
  	private List<Registro> registros;
  ````

  <b>Prestador.java</b>

  ````java
  @OneToMany(mappedBy = "prestador")
  	private List<Registro> registros;
  ````

  <b>Servico.java</b>

  ````java
  @OneToMany(mappedBy = "tipoServico")
  	private List<Registro> registros;
  ````

Pronto! Relações formalizadas!

<hr>

<h3> Implementar o CRUD</h3>





