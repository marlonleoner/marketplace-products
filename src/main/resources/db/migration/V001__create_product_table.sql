CREATE TABLE product (
	id				BIGINT NOT NULL,
	name			VARCHAR(255),
	price			DOUBLE,
	type 			VARCHAR(255),
	amount			INTEGER,
	created_at 		DATETIME,
	updated_at 		DATETIME,
	PRIMARY KEY 	(id)
);

CREATE TABLE generator_table (
	PRODUCT_KEY			VARCHAR(255),
	PRODUCT_KEY_NEXT	BIGINT
);