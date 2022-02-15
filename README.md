<h1>FIAP Fase 1 : Capítulo 5 </h1>

<h3>Início</h3>

No <a href="https://github.com/Amorim-cyber/fiap2_fase1_cap4">projeto do capítulo 4</a> montei o modelo lógico de um sistema que facilite o encontro de moradores de condomínios com prestadores de serviços. O sistema apresenta também um controle de registro de serviço caso o morador queira contratar o prestador.  A relação ficou da seguinte forma: 

<img src="assets/tabelas.PNG">

* <b>tb_condominio:</b> Tabela que vai armazenar dados de condomínio. Contém o nome e endereço do condomínio.
* <b>tb_morada:</b> Tabela que vai armazenar dados de morada. Contém o numero da morada e seu tipo (CASA ou APARTAMENTO).
* <b style="color:grey">Relação morada_condominio:</b> Uma morada deve pertencer à um condomínio. Um condomínio pode possuir uma ou mais de uma morada.
* <b>tb_morador:</b> Tabela que vai armazenar dados de morador. Contém nome do morador. 
* <b style="color:grey">Relação morada_morador:</b> Uma morada deve ser habitada por um ou mais moradores e um morador deve morar em pelo menos uma morada.
* <b>tb_servico:</b> Tabela que vai armazenar dados do serviço. Contém o nome do serviço.
* <b>tb_prestador:</b> Tabela que vai armazenar dados do prestador de serviço. Contém o nome, número de telefone do prestador.
* <b>tb_registro_servico:</b> Tabela que vai armazenar dados do registro de serviço. Contém a data de inicio, a data de fim de serviço e o status do registro.
* <b style="color:grey">Relação servico_registro:</b> Um tipo de serviço deve ser registrado e um registro deve conter pelo menos um tipo de serviço.
* <b style="color:grey">Relação prestador_registro:</b> Um prestador de serviço pode ser registrado, contudo um registro deve conter pelo menos um prestador de serviço.
* <b style="color:grey">Relação morador_registro:</b> Um morador pode ser registrado, contudo um registro deve conter pelo menos um morador.

Conforme solicitado, este projeto tem como objetivo montar o script de criação de tabelas, incluir as relações entre entidades no programa e implementar as operações de CRUD.

<HR> 

<h3>Montar o script</h3>

O script que efetua o `create` das tabelas, sequências e chaves estrangeiras foi desenvolvido da seguinte forma:

`````sql
-- DELETAR HISTORICO (COMPUTE CASO AS ESTRURAS JA EXISTAM NO BANCO) --

drop table tb_condominio cascade constraints;
drop table tb_morada cascade constraints;
drop table tb_morador cascade constraints;
drop table tb_prestador cascade constraints;
drop table tb_registro_morada cascade constraints;
drop table tb_registro_servico cascade constraints;
drop table tb_servico cascade constraints;
drop sequence sq_tb_condominio;
drop sequence sq_tb_morada;
drop sequence sq_tb_morador;
drop sequence sq_tb_prestador;
drop sequence sq_tb_registro;
drop sequence sq_tb_servico;


-- CRIAÇÃO DE SEQUENCES --

create sequence sq_tb_condominio start with 1 increment by 1;
create sequence sq_tb_morada start with 1 increment by 1;
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

CREATE TABLE tb_morada (
    id_morada                    NUMBER(10) NOT NULL,
    nr_morada                    NUMBER(10) NOT NULL,
    tipo_morada                  VARCHAR2(20) NOT NULL,
    tb_condominio_id_condominio  NUMBER(10) NOT NULL
);

ALTER TABLE tb_morada ADD CONSTRAINT tb_morada_pk PRIMARY KEY ( id_morada );

CREATE TABLE tb_morador (
    id_morador  NUMBER(10) NOT NULL,
    nm_morador  VARCHAR2(100) NOT NULL
);

ALTER TABLE tb_morador ADD CONSTRAINT tb_morador_pk PRIMARY KEY ( id_morador );

CREATE TABLE tb_prestador (
    id_prestador  NUMBER(10) NOT NULL,
    nm_prestador  VARCHAR2(100) NOT NULL,
    telefone      NUMBER(19) NOT NULL
);

ALTER TABLE tb_prestador ADD CONSTRAINT tb_prestador_pk PRIMARY KEY ( id_prestador );

CREATE TABLE tb_registro_morada (
    tb_morador_id_morador  NUMBER(10) NOT NULL,
    tb_morada_id_morada    NUMBER(10) NOT NULL
);

CREATE TABLE tb_registro_servico (
    id_registro                NUMBER(10) NOT NULL,
    dt_inicio                  TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    dt_termino                 TIMESTAMP WITH LOCAL TIME ZONE NOT NULL,
    tb_prestador_id_prestador  NUMBER(10) NOT NULL,
    tb_morador_id_morador      NUMBER(10) NOT NULL,
    tb_servico_id_servico      NUMBER(10) NOT NULL,
    status                     VARCHAR2(15) NOT NULL
);

ALTER TABLE tb_registro_servico ADD CONSTRAINT tb_registro_servico_pk PRIMARY KEY ( id_registro );

CREATE TABLE tb_servico (
    id_servico  NUMBER(10) NOT NULL,
    nm_servico  VARCHAR2(255) NOT NULL
);

ALTER TABLE tb_servico ADD CONSTRAINT tb_servico_pk PRIMARY KEY ( id_servico );

--  CHAVES ESTRANGEIRAS  --

ALTER TABLE tb_morada
    ADD CONSTRAINT mor_condo_fk FOREIGN KEY ( tb_condominio_id_condominio )
        REFERENCES tb_condominio ( id_condominio );

ALTER TABLE tb_registro_morada
    ADD CONSTRAINT regis_mor_fk FOREIGN KEY ( tb_morada_id_morada )
        REFERENCES tb_morada ( id_morada );
        
ALTER TABLE tb_registro_morada
    ADD CONSTRAINT regis_mora_fk FOREIGN KEY ( tb_morador_id_morador )
        REFERENCES tb_morador ( id_morador );

ALTER TABLE tb_registro_servico
    ADD CONSTRAINT regis_serv_mora_fk FOREIGN KEY ( tb_morador_id_morador )
        REFERENCES tb_morador ( id_morador );

ALTER TABLE tb_registro_servico
    ADD CONSTRAINT regis_serv_pres_fk FOREIGN KEY ( tb_prestador_id_prestador )
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

  As entidades `Morador` e `morada` possuem relação N x N. Adicionamos as seguintes linhas de código nos seguintes arquivos:

  <b>Morador.java:</b>

  ````java
  @ManyToMany(cascade=CascadeType.PERSIST)
  	@JoinTable(joinColumns = @JoinColumn(name="id_morador"), 
  	inverseJoinColumns = @JoinColumn(name="id_morada"), name = "tb_registro_morada")
  	private List<Morada> moradas;
  ````

   <b>Morada.java:</b>

  ````java
  @ManyToMany(mappedBy="moradas")
  	private List<Morador> moradores;
  ````

* <b>Relação Muitos para Um:</b>

  A entidade `Registro Serviço` possui uma relação de muitos para um com as entidades `Serviço`, `Morador` e `Prestador`, da mesma forma que `Morada` tem sobre `condominio`. Adicionamos as seguintes linhas de código nos seguintes arquivos:

  <b>Registro.java</b>:

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

   <b>Morada.java:</b>

  ````java
  @JoinColumn(name = "id_condominio")
  	@ManyToOne(cascade=CascadeType.PERSIST)
  	private Condominio condominio;
  ````

  

* <b>Relação Um para Muitos:</b>

  As entidades  `Serviço`, `Morador` e `Prestador` possuem uma relação de um para muitos em relação a `Registro Serviço`, da mesma forma que `condominio` tem sobre `Morada`. Adicionamos as seguintes linhas de código nos seguintes arquivos:

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
  
  <b>Condominio.java</b>
  
  ````java
  @OneToMany(mappedBy = "condominio")
  	private List<Morada> moradas;
  ````
  
  

Pronto! Relações formalizadas!

<hr>

<h3> Implementar o CRUD</h3>





