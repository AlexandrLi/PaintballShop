#db init
CREATE SCHEMA `pbshopdb`
  DEFAULT CHARACTER SET utf8;

#create products table
CREATE TABLE `pbshopdb`.`products` (
  `id`          INT           NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255)  NOT NULL,
  `price`       DECIMAL(8, 0) NOT NULL,
  `type`        VARCHAR(45)   NOT NULL,
  `description` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

# add first product in products table
INSERT INTO products (name, price, type, description) VALUES ('Proto Reflex Rail 2013', 450, 'Paintball Marker',
                                                              'The Reflex Rail is great for any intermediate to advanced paintball player looking for a high end gun without the $1000+ price tag. The Reflex Rail is a top performer and designed after the popular Dye DM series guns. Jump on the Reflex today if you want a great looking gun that performs at the highest level!')