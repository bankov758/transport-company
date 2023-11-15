-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema logistic_company
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema logistic_company
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `logistic_company` DEFAULT CHARACTER SET utf8mb3 ;
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `logistic_company` ;

-- -----------------------------------------------------
-- Table `logistic_company`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`company` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`employee` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `family_name` VARCHAR(45) NULL DEFAULT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Employee_Company1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `logistic_company`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`employee_has_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`employee_has_skill` (
  `employee_id` INT NOT NULL,
  `skilll_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`, `skilll_id`),
  INDEX `fk_Employee_has_skill_skill1_idx` (`skilll_id` ASC) VISIBLE,
  INDEX `fk_Employee_has_skill_Employee1_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_has_skill_Employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `logistic_company`.`employee` (`id`),
  CONSTRAINT `fk_Employee_has_skill_skill1`
    FOREIGN KEY (`skilll_id`)
    REFERENCES `logistic_company`.`skill` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`payload`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`payload` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `unit` VARCHAR(45) NULL,
  `unit_value` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`vehicle` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `capacity_unit` VARCHAR(45) NOT NULL,
  `capacity` FLOAT NOT NULL,
  `company_id` INT NOT NULL,
  INDEX `fk_Vehicles_Company1_idx` (`company_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_Vehicles_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `logistic_company`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`order` (
  `id` INT NOT NULL,
  `start_time` DATETIME NULL DEFAULT NULL,
  `end_time` DATETIME NULL DEFAULT NULL,
  `arrival_point` VARCHAR(45) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `departure_point` VARCHAR(45) NULL DEFAULT NULL,
  `payload_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `company_id` INT NOT NULL,
  `vehicles_id` INT NOT NULL,
  `is_human_delivery` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_payload1_idx` (`payload_id` ASC) VISIBLE,
  INDEX `fk_order_Employee1_idx` (`employee_id` ASC) VISIBLE,
  INDEX `fk_order_Company1_idx` (`company_id` ASC) VISIBLE,
  INDEX `fk_order_Vehicles1_idx` (`vehicles_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `logistic_company`.`company` (`id`),
  CONSTRAINT `fk_order_Employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `logistic_company`.`employee` (`id`),
  CONSTRAINT `fk_order_payload1`
    FOREIGN KEY (`payload_id`)
    REFERENCES `logistic_company`.`payload` (`id`),
  CONSTRAINT `fk_order_Vehicles1`
    FOREIGN KEY (`vehicles_id`)
    REFERENCES `logistic_company`.`vehicle` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`client` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `family_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `logistic_company`.`order_has_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`order_has_client` (
  `order_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `client_id`),
  INDEX `fk_order_has_client_client1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_order_has_client_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_client_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `logistic_company`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_client_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `logistic_company`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`company` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`employee` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `family_name` VARCHAR(45) NULL DEFAULT NULL,
  `company_id` INT NOT NULL,
  `salary` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Employee_Company1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `mydb`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vehicles` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` FLOAT NULL DEFAULT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Vehicles_Company1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_Vehicles_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `mydb`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`payload`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`payload` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `weight` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order` (
  `id` INT NOT NULL,
  `start_time` DATETIME NULL DEFAULT NULL,
  `snd_time` DATETIME NULL DEFAULT NULL,
  `arrival_point` VARCHAR(45) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `departure_point` VARCHAR(45) NULL DEFAULT NULL,
  `Employee_id` INT NOT NULL,
  `Company_id` INT NOT NULL,
  `Vehicles_id` INT NOT NULL,
  `payload_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_Employee1_idx` (`Employee_id` ASC) VISIBLE,
  INDEX `fk_order_Company1_idx` (`Company_id` ASC) VISIBLE,
  INDEX `fk_order_Vehicles1_idx` (`Vehicles_id` ASC) VISIBLE,
  INDEX `fk_order_payload1_idx` (`payload_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_Company1`
    FOREIGN KEY (`Company_id`)
    REFERENCES `mydb`.`company` (`id`),
  CONSTRAINT `fk_order_Employee1`
    FOREIGN KEY (`Employee_id`)
    REFERENCES `mydb`.`employee` (`id`),
  CONSTRAINT `fk_order_Vehicles1`
    FOREIGN KEY (`Vehicles_id`)
    REFERENCES `mydb`.`vehicles` (`id`),
  CONSTRAINT `fk_order_payload1`
    FOREIGN KEY (`payload_id`)
    REFERENCES `mydb`.`payload` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `logistic_company`.`receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistic_company`.`receipt` (
  `id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receit_client1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_receipt_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_receit_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `logistic_company`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`employee_has_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`employee_has_skill` (
  `Employee_ID` INT NOT NULL,
  `skilll_id` INT NOT NULL,
  PRIMARY KEY (`Employee_ID`, `skilll_id`),
  INDEX `fk_Employee_has_skill_skill1_idx` (`skilll_id` ASC) VISIBLE,
  INDEX `fk_Employee_has_skill_Employee1_idx` (`Employee_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_has_skill_Employee1`
    FOREIGN KEY (`Employee_ID`)
    REFERENCES `mydb`.`employee` (`id`),
  CONSTRAINT `fk_Employee_has_skill_skill1`
    FOREIGN KEY (`skilll_id`)
    REFERENCES `mydb`.`skill` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`company_has_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`company_has_client` (
  `company_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`company_id`, `client_id`),
  INDEX `fk_company_has_client_client1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_company_has_client_company1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_company_has_client_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `mydb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_has_client_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `logistic_company`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`order_has_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order_has_client` (
  `order_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `client_id`),
  INDEX `fk_order_has_client_client1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_order_has_client_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_client_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_client_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `logistic_company`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
