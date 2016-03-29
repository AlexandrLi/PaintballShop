# MySQL server variables are temporarily set to enable faster SQL import by the server.
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

# Table gender
CREATE TABLE IF NOT EXISTS gender (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC)
)
  ENGINE = InnoDB;

# Table user
CREATE TABLE IF NOT EXISTS user (
  id           INT          NOT NULL AUTO_INCREMENT,
  email        VARCHAR(80)  NOT NULL,
  password     VARCHAR(45)  NOT NULL,
  first_name   VARCHAR(100) NULL,
  last_name    VARCHAR(100) NULL,
  phone_number VARCHAR(45)  NULL,
  gender_id    INT          NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX email_UNIQUE (email ASC),
  INDEX fk_user_gender_idx (gender_id ASC),
  CONSTRAINT fk_user_gender
  FOREIGN KEY (gender_id)
  REFERENCES gender (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table address
CREATE TABLE IF NOT EXISTS address (
  id               INT         NOT NULL AUTO_INCREMENT,
  country          VARCHAR(45) NOT NULL,
  city             VARCHAR(45) NOT NULL,
  street           VARCHAR(45) NOT NULL,
  building_number  VARCHAR(45) NOT NULL,
  apartment_number VARCHAR(45) NOT NULL,
  user_id          INT         NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_address_user_idx (user_id ASC),
  CONSTRAINT fk_address_user
  FOREIGN KEY (user_id)
  REFERENCES user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table product_type
CREATE TABLE IF NOT EXISTS product_type (
  id   INT          NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

# Table characteristic
CREATE TABLE IF NOT EXISTS characteristic (
  id              INT          NOT NULL AUTO_INCREMENT,
  name            VARCHAR(255) NOT NULL,
  product_type_id INT          NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_characteristic_product_type_idx (product_type_id ASC),
  CONSTRAINT fk_characteristic_product_type
  FOREIGN KEY (product_type_id)
  REFERENCES product_type (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table order
CREATE TABLE IF NOT EXISTS `order` (
  id      INT      NOT NULL AUTO_INCREMENT,
  date    DATETIME NOT NULL,
  user_id INT      NOT NULL,
  PRIMARY KEY (id, user_id),
  INDEX fk_order_user_idx (user_id ASC),
  CONSTRAINT fk_order_user
  FOREIGN KEY (user_id)
  REFERENCES user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table product
CREATE TABLE IF NOT EXISTS product (
  id              INT          NOT NULL AUTO_INCREMENT,
  name            VARCHAR(254) NOT NULL,
  price           DECIMAL(8)   NOT NULL,
  description     VARCHAR(254) NULL,
  product_type_id INT          NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_product_product_type_idx (product_type_id ASC),
  CONSTRAINT fk_products_product_type
  FOREIGN KEY (product_type_id)
  REFERENCES product_type (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table order_item
CREATE TABLE IF NOT EXISTS order_item (
  id         INT NOT NULL AUTO_INCREMENT,
  amount     INT NOT NULL,
  order_id   INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (id, order_id, product_id),
  INDEX fk_order_item_order_idx (order_id ASC),
  INDEX fk_order_item_product_idx (product_id ASC),
  CONSTRAINT fk_order_item_order
  FOREIGN KEY (order_id)
  REFERENCES `order` (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_item_product
  FOREIGN KEY (product_id)
  REFERENCES product (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table storage
CREATE TABLE IF NOT EXISTS storage (
  id          INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(45)  NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

# Table storage_item
CREATE TABLE IF NOT EXISTS storage_item (
  amount     INT NOT NULL,
  storage_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (storage_id),
  INDEX fk_storage_item_storage_idx (storage_id ASC),
  INDEX fk_storage_item_product_idx (product_id ASC),
  CONSTRAINT fk_storage_item_storage
  FOREIGN KEY (storage_id)
  REFERENCES storage (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_storage_item_product
  FOREIGN KEY (product_id)
  REFERENCES product (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table image
CREATE TABLE IF NOT EXISTS image (
  id         INT         NOT NULL AUTO_INCREMENT,
  name       VARCHAR(45) NOT NULL,
  content    BLOB        NOT NULL,
  product_id INT         NOT NULL,
  PRIMARY KEY (id, product_id),
  INDEX fk_image_product_idx (product_id ASC),
  CONSTRAINT fk_image_product
  FOREIGN KEY (product_id)
  REFERENCES product (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Server variables are reset at the end of the script
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
