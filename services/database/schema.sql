create database blueandpurpleproducts;
create table products(
	codigo serial not null primary key,
	nome varchar(100) not null,
	valor_unitario decimal(16,2) not null
);

create database blueandpurpleclients;
create table clients(
	codigo serial not null primary key,
	name varchar(150) not null,
	cpf char(11) not null,
	address varchar(100),
	cellphonenumber varchar(10),
	email varchar(150)

);

create database blueandpurpleorders;
create table orders(
	codigo serial not null primary key,
	codigo_client bigint not null,
	data_orders timestamp not null default now(),
	payment_key text,
	observacoes text,
	status varchar(20) check (
		status in ('REALIZED', 'PAID', 'INVOICED', 'SENT', 'PAYMENT_ERROR', 'PREPARING_FOR_SHIPPING')
	),
	total decimal (16, 2) not null,
	codigo_rastreio varchar(255),
	url_nf text
);

create table item_order(
	codigo serial not null primary key,
	codigo_order bigint not null references orders (codigo),
	codigo_products bigint not null,
	quantity int not null,
	valor_unitario decimal (16,2) not null

);