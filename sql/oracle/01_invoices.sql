CREATE TABLE invoices (
    id               NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    client_id        NUMBER NOT NULL,
    subtotal         NUMBER(12,2) NOT NULL,
    total_taxes      NUMBER(12,2) NOT NULL,
    total_discounts  NUMBER(12,2) NOT NULL,
    total            NUMBER(12,2) NOT NULL,
    creation_date    TIMESTAMP DEFAULT SYSTIMESTAMP,
    status           VARCHAR2(20) DEFAULT 'ISSUED'
);

CREATE TABLE items_invoice (
    id              NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    invoice_id      NUMBER NOT NULL REFERENCES invoices(id),
    product         VARCHAR2(150) NOT NULL,
    unit_price      NUMBER(12,2) NOT NULL,
    amount          NUMBER(10) NOT NULL,
    subtotal_item   NUMBER(12,2) NOT NULL
);

CREATE INDEX idx_invoices_client ON invoices(client_id);