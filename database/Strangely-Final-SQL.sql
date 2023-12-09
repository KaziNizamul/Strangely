-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema CSCI5308_14_DEVINT
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CSCI5308_14_DEVINT
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CSCI5308_14_DEVINT` DEFAULT CHARACTER SET utf8mb4 ;
USE `CSCI5308_14_DEVINT` ;

-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`area` (
  `area_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `latitude` DECIMAL(11,8) NOT NULL,
  `longitude` DECIMAL(11,8) NOT NULL,
  PRIMARY KEY (`area_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`user` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `phone_number` CHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `joining_date` DATE NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `area_id` INT(11) NOT NULL,
  `reset_token` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userID`, `username`),
  INDEX `fk_client` (`area_id` ASC) VISIBLE,
  INDEX `username_index` (`username` ASC) VISIBLE,
  INDEX `username_indexx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_client`
    FOREIGN KEY (`area_id`)
    REFERENCES `CSCI5308_14_DEVINT`.`area` (`area_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`group` (
  `groupId` INT(11) NOT NULL,
  `groupName` VARCHAR(45) NULL DEFAULT NULL,
  `groupDescription` VARCHAR(45) NULL DEFAULT NULL,
  `groupAdmin` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  INDEX `groupAdmin_idx` (`groupAdmin` ASC) VISIBLE,
  CONSTRAINT `group_ibfk_1`
    FOREIGN KEY (`groupAdmin`)
    REFERENCES `CSCI5308_14_DEVINT`.`user` (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`images` (
  `image_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `image` LONGBLOB NULL DEFAULT NULL,
  `post_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`image_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`message` (
  `message_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL DEFAULT NULL,
  `sender` VARCHAR(100) NULL DEFAULT NULL,
  `receiver` VARCHAR(100) NULL DEFAULT NULL,
  `timestamp` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`postcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`postcategory` (
  `post_category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `post_category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`post_category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`post` (
  `post_id` INT(11) NOT NULL AUTO_INCREMENT,
  `post_category_id` INT(11) NULL DEFAULT NULL,
  `area_id` INT(11) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `post_date` BIGINT(17) NULL DEFAULT NULL,
  `post_dislike` INT(11) NULL DEFAULT NULL,
  `post_like_reaction` INT(11) NULL DEFAULT NULL,
  `post_love_reaction` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `area_id` (`area_id` ASC) VISIBLE,
  INDEX `post_category_id` (`post_category_id` ASC) VISIBLE,
  CONSTRAINT `post_ibfk_1`
    FOREIGN KEY (`area_id`)
    REFERENCES `CSCI5308_14_DEVINT`.`area` (`area_id`),
  CONSTRAINT `post_ibfk_2`
    FOREIGN KEY (`post_category_id`)
    REFERENCES `CSCI5308_14_DEVINT`.`postcategory` (`post_category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `CSCI5308_14_DEVINT`.`usergroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_14_DEVINT`.`usergroup` (
  `userId` INT(11) NOT NULL,
  `groupId` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `usergroup_ibfk_1`
    FOREIGN KEY (`userId`)
    REFERENCES `CSCI5308_14_DEVINT`.`user` (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
