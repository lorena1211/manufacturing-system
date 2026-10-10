CREATE OR REPLACE PROCEDURE register_invoice (
    p_client_id        IN  NUMBER,
    p_client_active    IN  VARCHAR2,   
    p_subtotal         IN  NUMBER,
    p_total_taxes      IN  NUMBER,
    p_total_discounts  IN  NUMBER,
    p_total            IN  NUMBER,
    p_invoice_id       OUT NUMBER      
)
IS
    v_client_invalid EXCEPTION;
BEGIN
    -- 1. Validate that the customer is marked as active
    IF p_client_active IS NULL OR UPPER(p_client_active) != 'S' THEN
        RAISE v_client_invalid;
    END IF;

    -- 2. Insert the invoice
    INSERT INTO invoices (client_id, subtotal, total_taxes, total_discounts, total, status)
    VALUES (p_client_id, p_subtotal, p_total_taxes, p_total_discounts, p_total, 'ISSUED')
    RETURNING id INTO p_invoice_id;

    COMMIT;

EXCEPTION
    WHEN v_client_invalid THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'The client' || p_client_id || ' does not exists or is not active');
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20099, 'Error registering the invoice: ' || SQLERRM);
END register_invoice;
/