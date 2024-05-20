-- -----------------------------------------------------
-- schema e-comm-business
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `e-comm-business`;

CREATE SCHEMA `e-comm-business`;
USE `e-comm-business`;

-- -----------------------------------------------------
-- table `e-comm-business`.`tbl_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-comm-business`.`tbl_category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category_title` VARCHAR(50) NOT NULL,
    `active` ENUM('yes', 'no') DEFAULT 'yes' NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`category_title`)
)  ENGINE=INNODB AUTO_INCREMENT=1;

select * from tbl_category;

-- -----------------------------------------------------
-- table `e-comm-business`.`tbl_brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-comm-business`.`tbl_brand` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `brand_title` VARCHAR(50) NOT NULL,
    `active` ENUM('yes', 'no') DEFAULT 'yes' NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`brand_title`)
)  ENGINE=INNODB AUTO_INCREMENT=1;

select * from tbl_brand;

-- -----------------------------------------------------
-- table `e-comm-business`.`tbl_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-comm-business`.`tbl_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_title` VARCHAR(255) NOT NULL,
    `active` ENUM('yes', 'no') DEFAULT 'yes' NOT NULL,
    `category_id` BIGINT NOT NULL,
    `brand_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`product_title`),
    FOREIGN KEY (`category_id`)
        REFERENCES `tbl_category` (`id`),
    FOREIGN KEY (`brand_id`)
        REFERENCES `tbl_brand` (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1;

select * from tbl_product;

SELECT 
    p.id,
    p.active,
    p.product_title,
    c.category_title,
    b.brand_title
FROM
    (tbl_product p
    LEFT JOIN tbl_category c ON c.id = p.category_id)
        LEFT JOIN
    tbl_brand b ON b.id = p.brand_id;
    
select count(*) as total_row from product_tbl;

