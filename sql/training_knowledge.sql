-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema training
-- -----------------------------------------------------
-- Training online system

-- -----------------------------------------------------
-- Schema training
--
-- Training online system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `training` DEFAULT CHARACTER SET utf8 ;
USE `training` ;

-- -----------------------------------------------------
-- Table `training`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`subject` (
  `subject_id` VARCHAR(20) NOT NULL,
  `subject_name` VARCHAR(45) NOT NULL,
  `subject_link` VARCHAR(100) NULL,
  `block_flag` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`subject_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `training`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`course` (
  `course_id` VARCHAR(20) NOT NULL,
  `subject_id` VARCHAR(20) NOT NULL,
  `block_flag` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `fk_course_subject2_idx` (`subject_id` ASC),
  CONSTRAINT `fk_course_subject2`
    FOREIGN KEY (`subject_id`)
    REFERENCES `training`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `training`.`choice_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`choice_question` (
  `question_id` VARCHAR(20) NOT NULL,
  `question_title` LONGTEXT NOT NULL,
  `question_option` LONGTEXT NOT NULL,
  `block_flag` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`question_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `training`.`course_question_link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`course_question_link` (
  `course_id` VARCHAR(20) NOT NULL,
  `question_id` VARCHAR(20) NOT NULL,
  `block_flag` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  INDEX `fk_course_question_link_course1_idx` (`course_id` ASC),
  INDEX `fk_course_question_link_choice_question1_idx` (`question_id` ASC),
  CONSTRAINT `fk_course_question_link_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `training`.`course` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_question_link_choice_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `training`.`choice_question` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
