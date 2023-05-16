create database agenda;

use agenda;

create table contato(
id int not null auto_increment primary key,
nome varchar(40),
idade int,
dataCadastro date)