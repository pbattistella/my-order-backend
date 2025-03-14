CREATE TABLE order_tb (
    id UUID DEFAULT gen_random_uuid(),
    customer_id UUID NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),

    CONSTRAINT order_pk PRIMARY KEY (id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);