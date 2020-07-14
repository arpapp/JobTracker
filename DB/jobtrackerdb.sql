-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jobtrackerjb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jobtrackerjb` ;

-- -----------------------------------------------------
-- Schema jobtrackerjb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jobtrackerjb` DEFAULT CHARACTER SET utf8 ;
USE `jobtrackerjb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_applied` DATE NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `cover_letter_submitted` TINYINT NOT NULL DEFAULT 1,
  `contact_person` VARCHAR(45) NULL,
  `notes` TEXT NULL,
  `status` ENUM('PENDING', 'REJECTED', 'OFFERED') NULL DEFAULT 'PENDING',
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_job_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS jobuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'jobuser'@'localhost' IDENTIFIED BY 'jobuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'jobuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerjb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `enabled`) VALUES (1, 'tpapp', 'admin', 'Toni', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerjb`;
INSERT INTO `job` (`id`, `date_applied`, `title`, `company`, `location`, `cover_letter_submitted`, `contact_person`, `notes`, `status`, `user_id`) VALUES (1, '2020-07-13', 'Software Developer', 'Woodridge Software', 'Golden, CO', 1, 'Kaj Gronholm', NULL, 'PENDING', 1);

COMMIT;

