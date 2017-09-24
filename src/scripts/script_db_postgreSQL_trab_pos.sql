-- Database: trabalho-pos
CREATE DATABASE "trabalho-pos"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = 10;

COMMENT ON DATABASE "trabalho-pos"
    IS 'Banco utilizado para o trabalho de JAVA da pós';

-- Table: public.cliente
CREATE TABLE public.cliente
(
    id serial NOT NULL,
    cpf character varying(15) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(30) COLLATE pg_catalog."default" NOT NULL,
    sobrenome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT unique_cpf UNIQUE (cpf)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cliente
    OWNER to postgres;

-- Table: public.pedido
CREATE TABLE public.pedido
(
    id serial NOT NULL DEFAULT,
    data timestamp without time zone NOT NULL,
    id_cliente integer NOT NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id),
    CONSTRAINT fkey_cliente_pedido FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pedido
    OWNER to postgres;

-- Table: public.produto
CREATE TABLE public.produto
(
    id serial NOT NULL DEFAULT,
    descricao character varying(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.produto
    OWNER to postgres;
	
-- Table: public.item_pedido
CREATE TABLE public.item_pedido
(
    id_pedido bigint NOT NULL,
    id_produto integer NOT NULL,
    quantidade integer NOT NULL,
    CONSTRAINT fkey_item_pedido_pedido FOREIGN KEY (id_pedido)
        REFERENCES public.pedido (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkey_item_pedido_produto FOREIGN KEY (id_produto)
        REFERENCES public.produto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.item_pedido
    OWNER to postgres;