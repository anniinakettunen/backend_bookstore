
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS UserTable;

CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publication_year INT CHECK (publication_year BETWEEN 1000 AND 2100),
    isbn VARCHAR(50) NOT NULL,
    price DECIMAL CHECK (price >= 0),
    category_id BIGINT NOT NULL REFERENCES category(id)
);


CREATE TABLE UserTable (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    role VARCHAR(100) NOT NULL
);


INSERT INTO category (name) VALUES 
('Fantasy'), 
('Science Fiction');


INSERT INTO book (title, author, publication_year, isbn, price, category_id) VALUES 
('The Hobbit', 'J.R.R. Tolkien', 1937, 'ISBN123456789', 19.99, 1),
('Foundation', 'Isaac Asimov', 1951, 'ISBN987654321', 14.99, 2);


INSERT INTO UserTable (username, password, email, role) VALUES 
('user', 'user', 'user@example.com', 'USER'),
('admin', 'admin', 'admin@example.com', 'ADMIN');