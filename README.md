<h1>FIAP Fase 1 : Capítulo 5 </h1>

<h3>Início</h3>

No <a href="https://github.com/Amorim-cyber/fiap2_fase1_cap4">projeto do capítulo 4</a> montei o modelo lógico de um sistema que facilite o encontro de moradores de condomínios com prestadores de serviços. O sistema apresenta também um controle de registro de serviço caso o morador queira contratar o prestador.  A relação ficou da seguinte forma: 

<img src="assets/tabelas.PNG">

* <b>tb_condominio:</b> Tabela que vai armazenar dados de condomínio. Contém o nome e endereço do condomínio.
* <b>tb_morador:</b> Tabela que vai armazenar dados de morador. Contém nome e número da sua morada. 
* <b style="color:grey">Relação condominio_morador:</b> Um condomínio pode ser habitado por um ou mais moradores contudo um morador deve habitar um condomínio (vamos supor por enquanto que um morador tem o direito de ter um imóvel em apenas um condomínio).
* <b>tb_servico:</b> Tabela que vai armazenar dados do serviço. Contém o nome do serviço.
* <b>tb_prestador:</b> Tabela que vai armazenar dados do prestador de serviço. Contém o nome, número de telefone do prestador.
* <b>tb_registro_servico:</b> Tabela que vai armazenar dados do registro de serviço. Contém a data de inicio e a data de fim de serviço.
* <b style="color:grey">Relação servico_registro:</b> Um tipo de serviço pode ser registrado, contudo um registro deve conter um tipo de serviço.
* <b style="color:grey">Relação prestador_registro:</b> Um prestador de serviço pode ser registrado, contudo um registro deve conter um prestador de serviço.
* <b style="color:grey">Relação morador_registro:</b> Um morador pode ser registrado, contudo um registro deve conter um morador.

Conforme solicitado, este projeto tem como objetivo montar o script de criação de tabelas, incluir as relações entre entidades no programa e implementar as operações de CRUD.

<HR> 

<h3>Montar o script</h3>