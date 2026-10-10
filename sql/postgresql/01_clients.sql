CREATE TABLE clients (
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(150) NOT NULL,
    nit                 VARCHAR(30) UNIQUE NOT NULL,
    email               VARCHAR(150) NOT NULL,
    tel                 VARCHAR(30),
    address             VARCHAR(255),
    active              BOOLEAN NOT NULL DEFAULT TRUE,
    creation_date       TIMESTAMP NOT NULL DEFAULT now(),
    update_date         TIMESTAMP
);

CREATE INDEX idx_clients_active ON clients(active);