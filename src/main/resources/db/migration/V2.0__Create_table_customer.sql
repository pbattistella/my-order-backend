CREATE TABLE customer (
    id UUID DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),

    CONSTRAINT customer_pk PRIMARY KEY (id)
);