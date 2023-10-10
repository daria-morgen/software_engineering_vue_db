CREATE TABLE ProductType
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE Department
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE Client
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    tel  numeric
);

CREATE TABLE Product
(
    id            BIGSERIAL PRIMARY KEY NOT NULL,
    name          VARCHAR(255),
    color         VARCHAR(255),
    size          VARCHAR(255),
    price         NUMERIC(5, 3),
    productTypeId INT,
    departmentId  INT,
    CONSTRAINT FK_product_productType FOREIGN KEY (productTypeId)
        REFERENCES producttype (id),
    CONSTRAINT FK_product_department FOREIGN KEY (departmentId)
        REFERENCES department (id)
)

CREATE TABLE Purchase
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    clientsId INT,
    productId INT,
    CONSTRAINT FK_product_productType FOREIGN KEY (clientsId)
        REFERENCES client (id),
    CONSTRAINT FK_product_department FOREIGN KEY (productId)
        REFERENCES product (id)
)
