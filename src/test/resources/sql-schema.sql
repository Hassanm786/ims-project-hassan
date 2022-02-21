DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS`ims`.`items` (
  `id` INT NOT NULL  AUTO_INCREMENT,
  `itemName` VARCHAR(45) NOT NULL,
  `itemQuantity` INT NOT NULL,
  `itemPrice` INT NOT NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `order_items`;

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
  `orderId` INT NOT NULL,
  `itemId` INT NULL,
  INDEX `orderId_idx` (`orderId` ASC) VISIBLE,
  CONSTRAINT `orderId`
    FOREIGN KEY (`orderId`)
    REFERENCES `ims`.`orders` (`id`));



