CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS drawings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content CLOB,
    username VARCHAR(255),
    CONSTRAINT fk_drawings_user FOREIGN KEY(username) REFERENCES users(username)
);
