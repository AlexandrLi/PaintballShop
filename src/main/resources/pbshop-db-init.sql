# MySQL server variables are temporarily set to enable faster SQL import by the server.
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

# Table `gender`
CREATE TABLE IF NOT EXISTS `gender` (
  `id`      INT         NOT NULL AUTO_INCREMENT,
  `name_ru` VARCHAR(45) NOT NULL,
  `name_en` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1)  NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name_ru` ASC),
  UNIQUE INDEX `name_en_UNIQUE` (`name_en` ASC)
)
  ENGINE = InnoDB;

# Table `user`
CREATE TABLE IF NOT EXISTS `user` (
  `id`           INT         NOT NULL AUTO_INCREMENT,
  `email`        VARCHAR(80) NOT NULL,
  `password`     VARCHAR(45) NOT NULL,
  `role`         VARCHAR(45) NOT NULL,
  `first_name`   VARCHAR(45) NULL,
  `last_name`    VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `gender_id`    INT         NOT NULL,
  `deleted`      TINYINT(1)  NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_gender_idx` (`gender_id` ASC),
  CONSTRAINT `fk_user_gender`
  FOREIGN KEY (`gender_id`)
  REFERENCES `gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `address`
CREATE TABLE IF NOT EXISTS `address` (
  `id`               INT         NOT NULL AUTO_INCREMENT,
  `country`          VARCHAR(45) NOT NULL,
  `city`             VARCHAR(45) NOT NULL,
  `street`           VARCHAR(45) NOT NULL,
  `building_number`  VARCHAR(45) NOT NULL,
  `apartment_number` VARCHAR(45) NOT NULL,
  `user_id`          INT         NOT NULL,
  `deleted`          TINYINT(1)  NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_address_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_address_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `product_type`
CREATE TABLE IF NOT EXISTS `product_type` (
  `id`      INT          NOT NULL AUTO_INCREMENT,
  `name_ru` VARCHAR(255) NOT NULL,
  `name_en` VARCHAR(255) NOT NULL,
  `deleted` TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

# Table `characteristic`
CREATE TABLE IF NOT EXISTS `characteristic` (
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name_ru`         VARCHAR(255) NOT NULL,
  `name_en`         VARCHAR(255) NOT NULL,
  `product_type_id` INT          NOT NULL,
  `deleted`         TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `product_type_id`),
  INDEX `fk_characteristics_product_type_idx` (`product_type_id` ASC),
  CONSTRAINT `fk_characteristics_product_type`
  FOREIGN KEY (`product_type_id`)
  REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `order`
CREATE TABLE IF NOT EXISTS `order` (
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `created`     DATETIME     NOT NULL,
  `user_id`     INT          NOT NULL,
  `description` VARCHAR(255) NULL,
  `deleted`     TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_orders_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_orders_users`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `product`
CREATE TABLE IF NOT EXISTS `product` (
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(45)  NOT NULL,
  `price`           DECIMAL(12)  NOT NULL,
  `description_ru`  VARCHAR(255) NULL,
  `description_en`  VARCHAR(255) NULL,
  `product_type_id` INT          NOT NULL,
  `deleted`         TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_product_product_type_idx` (`product_type_id` ASC),
  CONSTRAINT `fk_product_product_type`
  FOREIGN KEY (`product_type_id`)
  REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `order_item`
CREATE TABLE IF NOT EXISTS `order_item` (
  `id`         INT        NOT NULL AUTO_INCREMENT,
  `amount`     INT        NOT NULL,
  `order_id`   INT        NOT NULL,
  `product_id` INT        NOT NULL,
  `deleted`    TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `order_id`, `product_id`),
  INDEX `fk_order_item_orders_idx` (`order_id` ASC),
  INDEX `fk_order_item_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_item_orders`
  FOREIGN KEY (`order_id`)
  REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `storage`
CREATE TABLE IF NOT EXISTS `storage` (
  `id`             INT          NOT NULL AUTO_INCREMENT,
  `name`           VARCHAR(45)  NOT NULL,
  `description_ru` VARCHAR(255) NULL,
  `description_en` VARCHAR(255) NULL,
  `deleted`        TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

# Table `storage_item`
CREATE TABLE IF NOT EXISTS `storage_item` (
  `id`         INT        NOT NULL AUTO_INCREMENT,
  `amount`     INT        NOT NULL,
  `storage_id` INT        NOT NULL,
  `product_id` INT        NOT NULL,
  `deleted`    TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `storage_id`, `product_id`),
  INDEX `fk_storage_items_storages_idx` (`storage_id` ASC),
  INDEX `fk_storage_item_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_storage_items_storages`
  FOREIGN KEY (`storage_id`)
  REFERENCES `storage` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_storage_item_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `image`
CREATE TABLE IF NOT EXISTS `image` (
  `id`           INT         NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(45) NOT NULL,
  `content`      BLOB        NOT NULL,
  `product_id`   INT         NOT NULL,
  `modified`     DATETIME    NOT NULL,
  `content_type` VARCHAR(45) NOT NULL,
  `deleted`      TINYINT(1)  NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `product_id`),
  INDEX `fk_image_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_image_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Table `characteristic_item`
CREATE TABLE IF NOT EXISTS `characteristic_item` (
  `id`                INT          NOT NULL AUTO_INCREMENT,
  `value`             VARCHAR(255) NULL,
  `characteristic_id` INT          NOT NULL,
  `product_id`        INT          NOT NULL,
  `deleted`           TINYINT(1)   NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `characteristic_id`, `product_id`),
  INDEX `fk_characteristic_item_characteristic_idx` (`characteristic_id` ASC),
  INDEX `fk_characteristic_item_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_characteristic_item_characteristic`
  FOREIGN KEY (`characteristic_id`)
  REFERENCES `characteristic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_characteristic_item_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

# Server variables are reset at the end of the script
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

# Init default data
INSERT INTO gender (name_ru, name_en) VALUES ('Не указано', 'None');
INSERT INTO gender (name_ru, name_en) VALUES ('Мужчина', 'Male');
INSERT INTO gender (name_ru, name_en) VALUES ('Женщина', 'Female');

INSERT INTO user (email, password, role, first_name, last_name, phone_number, gender_id)
VALUES ('muzikant1990@gmail.com', 'qwerty', 'admin', 'Alexandr', 'Li', '+77051794745', 2);
INSERT INTO user (email, password, role, first_name, last_name, phone_number, gender_id)
VALUES ('muzikant_1990@mail.ru', '123456', 'user', 'Александр', 'Ли', '+77001794745', 2);

INSERT INTO address (country, city, street, building_number, apartment_number, user_id)
VALUES ('Kazakhstan', 'Karaganda', 'Alihanov', '34/2', '37', 1);
INSERT INTO address (country, city, street, building_number, apartment_number, user_id)
VALUES ('Kazakhstan', 'Karaganda', 'Ermekov', '81', '47', 1);
INSERT INTO address (country, city, street, building_number, apartment_number, user_id)
VALUES ('Kazakhstan', 'Almaty', 'Abai', '53', '11', 2);

INSERT INTO product_type (name_ru, name_en) VALUES ('Маркеры', 'Markers');
INSERT INTO product_type (name_ru, name_en) VALUES ('Фидеры', 'Hoppers');
INSERT INTO product_type (name_ru, name_en) VALUES ('Маски', 'Goggles');

INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Тип', 'Type', 1);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Цвет', 'Color', 1);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Ствол', 'Barrels', 1);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Тип', 'Type', 2);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Цвет', 'Color', 2);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Вместимость', 'Ball capacity', 2);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Модель', 'Model', 3);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Цвет', 'Color', 3);
INSERT INTO characteristic (name_ru, name_en, product_type_id) VALUES ('Линза', 'Lens', 3);

INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('M2 RASTA', 490000,
                                                                                           'Компания Dye имеет 12-летний опыт в изготовлении инновационных маркеров, и новый Dye M2 ― воплощение продвинутых технологий и красоты.',
                                                                                           'Refined over 12 years of paintball marker manufacturing, innovation, and experience, DYE’s M2 marker is the new benchmark for performance and beauty.',
                                                                                           1);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('M2 TIGER', 490000,
                                                                                           'Компания Dye имеет 12-летний опыт в изготовлении инновационных маркеров, и новый Dye M2 ― воплощение продвинутых технологий и красоты.',
                                                                                           'Refined over 12 years of paintball marker manufacturing, innovation, and experience, DYE’s M2 marker is the new benchmark for performance and beauty.',
                                                                                           1);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('M2 WOODY', 490000,
                                                                                           'Компания Dye имеет 12-летний опыт в изготовлении инновационных маркеров, и новый Dye M2 ― воплощение продвинутых технологий и красоты.',
                                                                                           'Refined over 12 years of paintball marker manufacturing, innovation, and experience, DYE’s M2 marker is the new benchmark for performance and beauty.',
                                                                                           1);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('R2 RASTA', 79000,
                                                                                           'R2™ сочетает в себе простоту вместе с взрывной скоростью подачи, надёжностью и регулируемой ёмкостью.',
                                                                                           'The R2™ fuses simplicity with mind-blowing feed rates, reliability and capacity flexibility.',
                                                                                           2);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('R2 TIGER', 79000,
                                                                                           'R2™ сочетает в себе простоту вместе с взрывной скоростью подачи, надёжностью и регулируемой ёмкостью.',
                                                                                           'The R2™ fuses simplicity with mind-blowing feed rates, reliability and capacity flexibility.',
                                                                                           2);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('R2 WOODY', 79000,
                                                                                           'R2™ сочетает в себе простоту вместе с взрывной скоростью подачи, надёжностью и регулируемой ёмкостью.',
                                                                                           'The R2™ fuses simplicity with mind-blowing feed rates, reliability and capacity flexibility.',
                                                                                           2);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('i4 RASTA', 63500,
                                                                                           'Одна из самых легких масок с широчайшим углом обзора и низким профилем.',
                                                                                           'One of the smallest profile, lightest, and best field of vision goggle system currently available.',
                                                                                           3);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('i4 TIGER', 63500,
                                                                                           'Одна из самых легких масок с широчайшим углом обзора и низким профилем.',
                                                                                           'One of the smallest profile, lightest, and best field of vision goggle system currently available.',
                                                                                           3);
INSERT INTO product (name, price, description_ru, description_en, product_type_id) VALUES ('i4 WOODY', 63500,
                                                                                           'Одна из самых легких масок с широчайшим углом обзора и низким профилем.',
                                                                                           'One of the smallest profile, lightest, and best field of vision goggle system currently available.',
                                                                                           3);

INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Electronic', 1, 1);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Electronic', 1, 2);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Electronic', 1, 3);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Rasta', 2, 1);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Tiger', 2, 2);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Woody', 2, 3);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Two piece', 3, 1);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Two piece', 3, 2);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Two piece', 3, 3);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Agitating', 4, 4);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Agitating', 4, 5);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Agitating', 4, 6);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Rasta', 5, 4);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Tiger', 5, 5);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Woody', 5, 6);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('200/260', 6, 4);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('200/260', 6, 5);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('200/260', 6, 6);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('i4', 7, 7);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('i4', 7, 8);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('i4', 7, 9);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Rasta', 8, 7);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Tiger', 8, 8);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Woody', 8, 9);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Thermal', 9, 7);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Thermal', 9, 8);
INSERT INTO characteristic_value (value, characteristic_id, product_id) VALUES ('Thermal', 9, 9);

INSERT INTO storage (name, description_ru, description_en)
VALUES ('Central', 'Центральный склад на Каскадной площади', 'Central storage on cascade square');

INSERT INTO storage_item (amount, storage_id, product_id) VALUES (2, 1, 1);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (3, 1, 2);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (1, 1, 3);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (5, 1, 4);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (0, 1, 5);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (4, 1, 6);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (11, 1, 7);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (0, 1, 8);
INSERT INTO storage_item (amount, storage_id, product_id) VALUES (9, 1, 9);

INSERT INTO `order` (created, user_id, description) VALUES (NOW(), 2, 'I want to buy this!');
INSERT INTO `order` (created, user_id, description) VALUES (NOW(), 2, 'This is exactly what I am searching for!');

INSERT INTO order_item (amount, order_id, product_id) VALUES (1, 1, 1);
INSERT INTO order_item (amount, order_id, product_id) VALUES (1, 1, 4);
INSERT INTO order_item (amount, order_id, product_id) VALUES (1, 1, 7);
INSERT INTO order_item (amount, order_id, product_id) VALUES (2, 2, 2);
INSERT INTO order_item (amount, order_id, product_id) VALUES (2, 2, 5);
INSERT INTO order_item (amount, order_id, product_id) VALUES (2, 2, 8);