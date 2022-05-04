CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(30),
    number VARCHAR(30),
    additional VARCHAR(30),
    district VARCHAR(30),
    postal_code VARCHAR(30),
    city VARCHAR(30),
    state VARCHAR(30),
    activated BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;