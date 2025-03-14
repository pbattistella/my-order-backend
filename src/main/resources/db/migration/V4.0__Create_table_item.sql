CREATE TABLE item (
    id UUID DEFAULT gen_random_uuid(),
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),

    CONSTRAINT item_pk PRIMARY KEY (id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES order_tb(id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);