CREATE TABLE pizza
(
    pizza_id   INT AUTO_INCREMENT PRIMARY KEY,
    order_id   INT NOT NULL,
    size       VARCHAR(20) NOT NULL,
    topping1   VARCHAR(50),
    topping2   VARCHAR(50),
    topping3   VARCHAR(50),
    topping4   VARCHAR(50),
    topping5   VARCHAR(50),
    price      FLOAT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
) CHARACTER SET utf8mb4;