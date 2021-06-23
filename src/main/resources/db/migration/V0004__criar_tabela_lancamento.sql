create table lancamento(
codigo bigint primary key auto_increment,
descricao varchar(200),
data_vencimento date not null,
data_pagamento date,
valor decimal(10,2) not null,
observacao varchar(200),
tipo varchar(20) not null,
codigo_categoria bigint not null,
codigo_pessoa bigint not null,
foreign key (codigo_categoria) references categoria (codigo),
foreign key (codigo_pessoa) references pessoa (codigo)
)engine=innodb default charset=utf8;