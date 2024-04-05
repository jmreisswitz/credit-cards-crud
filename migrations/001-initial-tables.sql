CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE credit_card (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    number VARCHAR(255) NOT NULL,
    expire_date_month VARCHAR(2),
    expire_date_year VARCHAR(4),
    cvv VARCHAR(255),
    last_four_digits VARCHAR(4),
    holder VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE credit_card_batch (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE credit_card_batch_line (
    credit_card_batch_id BIGINT UNSIGNED NOT NULL,
    identifier VARCHAR(255) NOT NULL primary key,
    credit_card_number VARCHAR(255) NOT NULL,
    last_four_digits VARCHAR(4) NOT NULL,
    status VARCHAR(125) NOT NULL,
    FOREIGN KEY (credit_card_batch_id) REFERENCES credit_card_batch(id)
)

