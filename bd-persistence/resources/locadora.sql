CREATE DATABASE locadora;

CREATE TABLE pessoas (
	id BIGSERIAL PRIMARY KEY,
	nome TEXT NOT NULL,
	cpf CHAR(11) UNIQUE
);

CREATE TABLE enderecos (
	id_pessoa BIGSERIAL REFERENCES pessoas ON DELETE CASCADE,
	logradouro TEXT NOT NULL,
	numero TEXT NOT NULL,
	complemento TEXT,
	referencia TEXT,
	bairro TEXT NOT NULL,
	cidade TEXT NOT NULL,
	estado CHAR(2) NOT NULL,
	cep CHAR(8) NOT NULL
);

CREATE TABLE emails (
	id_pessoa BIGSERIAL REFERENCES pessoas ON DELETE CASCADE,
	email TEXT UNIQUE NOT NULL
);

CREATE TABLE telefones (
	id_pessoa BIGSERIAL REFERENCES pessoas ON DELETE CASCADE,
	ddd CHAR(2) NOT NULL,
	telefone VARCHAR(9) NOT NULL
);

CREATE TABLE clientes (
	id BIGSERIAL PRIMARY KEY,
	id_pessoa BIGSERIAL REFERENCES pessoas ON DELETE CASCADE
);

CREATE TABLE usuarios (
	id BIGSERIAL PRIMARY KEY,
	id_pessoa BIGSERIAL REFERENCES pessoas ON DELETE CASCADE,
	login TEXT UNIQUE NOT NULL,
	senha TEXT NOT NULL
);

CREATE TABLE gerentes (
	id BIGSERIAL PRIMARY KEY,
	id_usuario BIGSERIAL REFERENCES usuarios ON DELETE CASCADE
);

CREATE TABLE funcionarios (
	id BIGSERIAL PRIMARY KEY,
	id_usuario BIGSERIAL REFERENCES usuarios ON DELETE CASCADE
);

CREATE FUNCTION not_future(ano INTEGER) RETURNS BOOLEAN AS $$
	BEGIN
		RETURN ano <= (SELECT EXTRACT(YEAR FROM CURRENT_DATE));
	END;
$$ LANGUAGE plpgsql;

CREATE TABLE emprestaveis (
	id BIGSERIAL PRIMARY KEY,
	titulo TEXT NOT NULL,
	numero_exemplares INTEGER CHECK (numero_exemplares >= 0) DEFAULT 0,
	numero_emprestimos INTEGER CHECK (numero_emprestimos >= 0) DEFAULT 0,
	numero_dias_alugado INTEGER CHECK (numero_dias_alugado >= 0) DEFAULT 0,
	ano_lancamento INTEGER CHECK (not_future(ano_lancamento)) DEFAULT 0,
	valor_aluguel REAL CHECK (valor_aluguel >= 0) DEFAULT 0
);

CREATE FUNCTION quantidade_emprestavel(id_emprestavel BIGINT) RETURNS INTEGER AS $$
	BEGIN
		RETURN (SELECT numero_exemplares FROM emprestaveis WHERE id = id_emprestavel);
	END;
$$ LANGUAGE plpgsql;

CREATE TABLE livros (
	id BIGSERIAL PRIMARY KEY,
	id_emprestavel BIGSERIAL REFERENCES emprestaveis ON DELETE CASCADE,
	genero TEXT NOT NULL,
	numero_paginas INTEGER CHECK (numero_paginas > 0)
);

CREATE VIEW parametros_principais_livros AS SELECT titulo, genero, numero_paginas, valor_aluguel FROM livros
	LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id);

CREATE TABLE discos (
	id BIGSERIAL PRIMARY KEY,
	id_emprestavel BIGSERIAL REFERENCES emprestaveis ON DELETE CASCADE,
	banda TEXT NOT NULL,
	estilo TEXT NOT NULL
);

CREATE VIEW parametros_principais_discos AS SELECT titulo, banda, estilo, valor_aluguel FROM discos
	LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id);

CREATE TABLE emprestimos (
	id BIGSERIAL PRIMARY KEY,
	id_cliente BIGSERIAL REFERENCES clientes ON DELETE CASCADE,
	data_emprestimo DATE NOT NULL,
	faturamento REAL CHECK (faturamento >= 0)
);

CREATE FUNCTION after(data DATE) RETURNS BOOLEAN AS $$
	BEGIN
		RETURN data > (SELECT CURRENT_DATE);
	END;
$$ LANGUAGE plpgsql;

CREATE TABLE objetos_emprestados (
	id BIGSERIAL PRIMARY KEY,
	id_emprestimo BIGSERIAL REFERENCES emprestimos ON DELETE CASCADE,
	id_emprestavel BIGSERIAL REFERENCES emprestaveis ON DELETE CASCADE,
	data_devolucao DATE CHECK (after(data_devolucao)),
	quantidade INTEGER CHECK (quantidade > 0)
);

'''
CREATE FUNCTION atualizar_quantidade_emprestavel(quantidade Integer) RETURNS TRIGGER AS $$
	BEGIN
		UPDATE emprestaveis SET numero_exemplares = numero_exemplares WHERE id = LASTVAL();
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER atualiza_quantidade_emprestavel
AFTER INSERT ON objetos_emprestados
	FOR EACH ROW EXECUTE PROCEDURE atualizar_quantidade_emprestavel();
'''
