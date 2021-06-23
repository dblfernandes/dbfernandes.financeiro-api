create table pessoa(
	codigo BIGINT primary key auto_increment,
	nome varchar(200) not null,
	ativo boolean not null,
	logradouro varchar(200),
	numero varchar(10),
	complemento varchar(200),
	bairro varchar (50),
	cep varchar (50),
	cidade varchar(50),
	estado varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;