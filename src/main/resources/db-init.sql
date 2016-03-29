# MySQL server variables are temporarily set to enable faster SQL import by the server.
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

# Schema pbshopdb
CREATE SCHEMA IF NOT EXISTS pbshopdb
  DEFAULT CHARACTER SET utf8;
USE pbshopdb;

# Table `pbshopmodel`.`gender`
CREATE TABLE IF NOT EXISTS pbshopdb.`gender` (
  `id`   INT         NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`user`
CREATE TABLE IF NOT EXISTS pbshopdb.`user` (
  `id`           INT          NOT NULL AUTO_INCREMENT,
  `email`        VARCHAR(80)  NOT NULL,
  `password`     VARCHAR(45)  NOT NULL,
  `first_name`   VARCHAR(100) NULL,
  `last_name`    VARCHAR(100) NULL,
  `phone_number` VARCHAR(45)  NULL,
  `gender_id`    INT          NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX fk_user_gender_idx (`gender_id` ASC),
  CONSTRAINT fk_user_gender
  FOREIGN KEY (`gender_id`)
  REFERENCES pbshopdb.`gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`address`
CREATE TABLE IF NOT EXISTS pbshopdb.`address` (
  `id`               INT         NOT NULL AUTO_INCREMENT,
  `country`          VARCHAR(45) NOT NULL,
  `city`             VARCHAR(45) NOT NULL,
  `street`           VARCHAR(45) NOT NULL,
  `building_number`  VARCHAR(45) NOT NULL,
  `apartment_number` VARCHAR(45) NOT NULL,
  `user_id`          INT         NOT NULL,
  PRIMARY KEY (`id`),
  INDEX fk_address_user_idx (`user_id` ASC),
  CONSTRAINT fk_address_user
  FOREIGN KEY (`user_id`)
  REFERENCES pbshopdb.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`product_type`
CREATE TABLE IF NOT EXISTS pbshopdb.`product_type` (
  `id`   INT          NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`characteristic`
CREATE TABLE IF NOT EXISTS pbshopdb.`characteristic` (
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(255) NOT NULL,
  `product_type_id` INT          NOT NULL,
  PRIMARY KEY (`id`),
  INDEX fk_characteristic_product_type_idx (`product_type_id` ASC),
  CONSTRAINT fk_characteristic_product_type
  FOREIGN KEY (`product_type_id`)
  REFERENCES pbshopdb.`product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`order`
CREATE TABLE IF NOT EXISTS pbshopdb.`order` (
  `id`      INT      NOT NULL AUTO_INCREMENT,
  `date`    DATETIME NOT NULL,
  `user_id` INT      NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX fk_order_user_idx (`user_id` ASC),
  CONSTRAINT fk_order_user
  FOREIGN KEY (`user_id`)
  REFERENCES pbshopdb.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`product`
CREATE TABLE IF NOT EXISTS pbshopdb.`product` (
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(254) NOT NULL,
  `price`           DECIMAL(8)   NOT NULL,
  `description`     VARCHAR(254) NULL,
  `product_type_id` INT          NOT NULL,
  PRIMARY KEY (`id`),
  INDEX fk_product_product_type_idx (`product_type_id` ASC),
  CONSTRAINT fk_products_product_type
  FOREIGN KEY (`product_type_id`)
  REFERENCES pbshopdb.`product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`order_item`
CREATE TABLE IF NOT EXISTS pbshopdb.`order_item` (
  `id`         INT NOT NULL AUTO_INCREMENT,
  `amount`     INT NOT NULL,
  `order_id`   INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`, `order_id`, `product_id`),
  INDEX fk_order_item_order_idx (`order_id` ASC),
  INDEX fk_order_item_product_idx (`product_id` ASC),
  CONSTRAINT fk_order_item_order
  FOREIGN KEY (`order_id`)
  REFERENCES pbshopdb.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_item_product
  FOREIGN KEY (`product_id`)
  REFERENCES pbshopdb.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`storage`
CREATE TABLE IF NOT EXISTS pbshopdb.`storage` (
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45)  NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`storage_item`
CREATE TABLE IF NOT EXISTS pbshopdb.`storage_item` (
  `amount`     INT NOT NULL,
  `storage_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`storage_id`),
  INDEX fk_storage_item_storage_idx (`storage_id` ASC),
  INDEX fk_storage_item_product_idx (`product_id` ASC),
  CONSTRAINT fk_storage_item_storage
  FOREIGN KEY (`storage_id`)
  REFERENCES pbshopdb.`storage` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_storage_item_product
  FOREIGN KEY (`product_id`)
  REFERENCES pbshopdb.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `pbshopmodel`.`image`
CREATE TABLE IF NOT EXISTS pbshopdb.`image` (
  `id`         INT         NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45) NOT NULL,
  `content`    BLOB        NOT NULL,
  `product_id` INT         NOT NULL,
  PRIMARY KEY (`id`, `product_id`),
  INDEX fk_image_product_idx (`product_id` ASC),
  CONSTRAINT fk_image_product
  FOREIGN KEY (`product_id`)
  REFERENCES pbshopdb.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Server variables are reset at the end of the script
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
