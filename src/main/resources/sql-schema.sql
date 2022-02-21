drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `ims`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  PRIMARY KEY (`id`));


  CREATE TABLE `ims`.`items` (
  `id` INT NOT NULL  AUTO_INCREMENT,
  `itemName` VARCHAR(45) NOT NULL,
  `itemQuantity` INT NOT NULL,
  `itemPrice` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ims`.`order_items` (
  `orderId` INT NOT NULL,
  `itemId` INT NULL,
  INDEX `orderId_idx` (`orderId` ASC) VISIBLE,
  CONSTRAINT `orderId`
    FOREIGN KEY (`orderId`)
    REFERENCES `ims`.`orders` (`id`));



