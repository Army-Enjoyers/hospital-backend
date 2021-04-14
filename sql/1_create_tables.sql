DROP SCHEMA IF EXISTS `mydb`;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`locality_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`locality_types` (
  `id` INT NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`street_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`street_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`placement_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`placement_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`contingents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`contingents` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`life_anamnesis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`life_anamnesis` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`military_affiliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`military_affiliations` (
  `id` INT NOT NULL AUTO_INCREMENT,
--   `military_rank` ENUM('рядовой', 'ефрейтор', 'младший сержант', 'сержант', 'старший сержант', 'старшина', 'прапорщик', 'старший прапорщик', 'младший лейтенант', 'лейтенант', 'старший лейтенант', 'капитан', 'майор', 'подполковник', 'полковник', 'генерал-майор', 'генерал-лейтенант', 'генерал-полковник') NOT NULL,
--   `serviceman_category` ENUM('офицеры', 'прапорщики', 'контрактники', 'срочники', 'курсанты') NOT NULL,
  `military_commissariat` VARCHAR(255) NOT NULL,
  `conscription_date` TIMESTAMP(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(128) NOT NULL,
  `first_name` VARCHAR(128) NOT NULL,
  `patronymic` VARCHAR(128) NOT NULL,
  `birth_date` TIMESTAMP(4) NOT NULL,
  `gender` VARCHAR(128) NOT NULL,
  `citizenship` VARCHAR(128) NOT NULL,
  `job` VARCHAR(255) NULL DEFAULT NULL,
  `polyclinic` VARCHAR(128) NOT NULL,
  `pensioner_id` VARCHAR(128) NULL DEFAULT NULL,
  `outpatient_card_number` VARCHAR(255) NULL DEFAULT NULL,
  `additional_info` TEXT NULL DEFAULT NULL,
  `contingents_id` INT NOT NULL,
  `life_anamnesis_id` INT NULL,
  `military_affiliations_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_patients_contingents_idx` (`contingents_id` ASC) VISIBLE,
  INDEX `fk_patients_life_anamnesis_idx` (`life_anamnesis_id` ASC) VISIBLE,
  INDEX `fk_patients_military_affiliations_idx` (`military_affiliations_id` ASC) VISIBLE,
  CONSTRAINT `fk_patients_contingents`
    FOREIGN KEY (`contingents_id`)
    REFERENCES `mydb`.`contingents` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_life_anamnesis`
    FOREIGN KEY (`life_anamnesis_id`)
    REFERENCES `mydb`.`life_anamnesis` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_military_affiliations`
    FOREIGN KEY (`military_affiliations_id`)
    REFERENCES `mydb`.`military_affiliations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(128) NOT NULL,
  `district` VARCHAR(128) NOT NULL,
  `locality` VARCHAR(128) NOT NULL,
  `street_address` VARCHAR(128) NOT NULL,
  `house_number` VARCHAR(64) NOT NULL,
  `placement_number` VARCHAR(64) NOT NULL,
  `phone_number` VARCHAR(128) NOT NULL,
  `home_phone_number` VARCHAR(128) NULL DEFAULT NULL,
  `locality_types_id` INT NOT NULL,
  `street_types_id` INT NOT NULL,
  `placement_types_id` INT NOT NULL,
  `patients_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_addresses_locality_types_idx` (`locality_types_id` ASC) VISIBLE,
  INDEX `fk_addresses_street_types_idx` (`street_types_id` ASC) VISIBLE,
  INDEX `fk_addresses_placement_types_idx` (`placement_types_id` ASC) VISIBLE,
  INDEX `fk_addresses_patients_idx` (`patients_id` ASC) VISIBLE,
  CONSTRAINT `fk_addresses_locality_types`
    FOREIGN KEY (`locality_types_id`)
    REFERENCES `mydb`.`locality_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_addresses_street_types`
    FOREIGN KEY (`street_types_id`)
    REFERENCES `mydb`.`street_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_addresses_placement_types`
    FOREIGN KEY (`placement_types_id`)
    REFERENCES `mydb`.`placement_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_addresses_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`completed_procedures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`completed_procedures` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`disability_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`disability_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`manipulations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`manipulations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`treatment_referrals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`treatment_referrals` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `destination` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`paid_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paid_services` (
  `id` BIGINT NOT NULL,
  `contract_number` VARCHAR(128) NOT NULL,
  `insurance_number` VARCHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`request_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`medical_board_requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medical_board_requests` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(128) NOT NULL,
  `result` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`medical_requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medical_requests` (
  `id` BIGINT NOT NULL,
  `request_date` TIMESTAMP(4) NOT NULL,
  `home_visit` SMALLINT NOT NULL,
  `planned_hospitalization` SMALLINT NULL DEFAULT NULL,
  `patients_id` INT NOT NULL,
  `treatment_referrals_id` INT NULL,
  `paid_services_id` BIGINT NULL,
  `request_types_id` INT NOT NULL,
  `medical_board_requests_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_medical_requests_patients_idx` (`patients_id` ASC) VISIBLE,
  INDEX `fk_medical_requests_treatment_referrals_idx` (`treatment_referrals_id` ASC) VISIBLE,
  INDEX `fk_medical_requests_paid_services_idx` (`paid_services_id` ASC) VISIBLE,
  INDEX `fk_medical_requests_request_types_idx` (`request_types_id` ASC) VISIBLE,
  INDEX `fk_medical_requests_medical_board_requests_idx` (`medical_board_requests_id` ASC) VISIBLE,
  CONSTRAINT `fk_medical_requests_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_requests_treatment_referrals`
    FOREIGN KEY (`treatment_referrals_id`)
    REFERENCES `mydb`.`treatment_referrals` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_requests_paid_services`
    FOREIGN KEY (`paid_services_id`)
    REFERENCES `mydb`.`paid_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_requests_request_types`
    FOREIGN KEY (`request_types_id`)
    REFERENCES `mydb`.`request_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_requests_medical_board_requests`
    FOREIGN KEY (`medical_board_requests_id`)
    REFERENCES `mydb`.`medical_board_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`diseases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`diseases` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mkb10_code` VARCHAR(255) NOT NULL,
  `main_diagnosis` TEXT NOT NULL,
  `co_diagnosis` TEXT NULL DEFAULT NULL,
  `complications` TEXT NULL DEFAULT NULL,
  `anamnesis` MEDIUMTEXT NULL DEFAULT NULL,
  `complaints` TEXT NULL DEFAULT NULL,
  `lab_examination_interpretation` TEXT NULL DEFAULT NULL,
  `instrumental_examination_interpretation` TEXT NULL DEFAULT NULL,
  `extra` TEXT NULL DEFAULT NULL,
  `recommendations` TEXT NULL DEFAULT NULL,
  `completed_procedures_id` INT NULL,
  `manipulations_id` INT NULL,
  `medical_requests_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_diseases_completed_procedures_idx` (`completed_procedures_id` ASC) VISIBLE,
  INDEX `fk_diseases_manipulations_idx` (`manipulations_id` ASC) VISIBLE,
  INDEX `fk_diseases_medical_requests_idx` (`medical_requests_id` ASC) VISIBLE,
  CONSTRAINT `fk_diseases_completed_procedures`
    FOREIGN KEY (`completed_procedures_id`)
    REFERENCES `mydb`.`completed_procedures` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_diseases_manipulations`
    FOREIGN KEY (`manipulations_id`)
    REFERENCES `mydb`.`manipulations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_diseases_medical_requests`
    FOREIGN KEY (`medical_requests_id`)
    REFERENCES `mydb`.`medical_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`hospital_personnel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hospital_personnel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(128) NOT NULL,
  `last_name` VARCHAR(128) NOT NULL,
  `patronymic` VARCHAR(128) NOT NULL,
  `login` VARCHAR(128) NOT NULL UNIQUE,
  `password` VARCHAR(128) NOT NULL,
  `position` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`hospital_certificates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hospital_certificates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(128) NOT NULL,
  `issued_at` TIMESTAMP(4) NOT NULL,
  `case_start_date` TIMESTAMP(4) NOT NULL,
  `release_start` TIMESTAMP(4) NOT NULL,
  `release_end` TIMESTAMP(4) NOT NULL,
  `cause_code` INT NOT NULL,
  `purpose` VARCHAR(255) NULL DEFAULT NULL,
  `note` VARCHAR(255) NULL DEFAULT NULL,
  `patients_id` INT NOT NULL,
  `hospital_personnel_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_hospital_certificates_patients_idx` (`patients_id` ASC) VISIBLE,
  INDEX `fk_hospital_certificates_hospital_personnel_idx` (`hospital_personnel_id` ASC) VISIBLE,
  CONSTRAINT `fk_hospital_certificates_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hospital_certificates_hospital_personnel`
    FOREIGN KEY (`hospital_personnel_id`)
    REFERENCES `mydb`.`hospital_personnel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `id` INT NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`hospital_personnel_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hospital_personnel_roles` (
  `hospital_personnel_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  INDEX `fk_hospital_personnel_roles_hospital_personnel_idx` (`hospital_personnel_id` ASC) VISIBLE,
  INDEX `fk_hospital_personnel_roles_roles_idx` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `fk_hospital_personnel_roles_hospital_personnel`
    FOREIGN KEY (`hospital_personnel_id`)
    REFERENCES `mydb`.`hospital_personnel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hospital_personnel_roles_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`objective_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`objective_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `temperature` FLOAT NULL DEFAULT NULL,
  `body_mass_index` FLOAT NULL DEFAULT NULL,
  `consciousness` VARCHAR(255) NULL DEFAULT NULL,
  `skin` TEXT NULL DEFAULT NULL,
  `visible_mucous_membranes` VARCHAR(255) NULL DEFAULT NULL,
  `palatine_tonsils` VARCHAR(255) NULL DEFAULT NULL,
  `lymph_nodes` VARCHAR(255) NULL DEFAULT NULL,
  `thyroid` VARCHAR(255) NULL DEFAULT NULL,
  `osteoarticular_system` TEXT NULL DEFAULT NULL,
  `muscular_system` TEXT NULL DEFAULT NULL,
  `breathing_rate` VARCHAR(255) NULL DEFAULT NULL,
  `percussion_sound` VARCHAR(255) NULL DEFAULT NULL,
  `breathing_sound` VARCHAR(255) NULL DEFAULT NULL,
  `pulse_rate` VARCHAR(128) NULL DEFAULT NULL,
  `pulse_deficit` VARCHAR(128) NULL DEFAULT NULL,
  `pulse_filling` VARCHAR(128) NULL DEFAULT NULL,
  `pulse_info` VARCHAR(255) NULL DEFAULT NULL,
  `arterial_pressure` VARCHAR(128) NULL DEFAULT NULL,
  `heart_info` TEXT NULL DEFAULT NULL,
  `tongue` VARCHAR(128) NULL DEFAULT NULL,
  `stomach` VARCHAR(255) NULL DEFAULT NULL,
  `liver` VARCHAR(255) NULL DEFAULT NULL,
  `spleen` VARCHAR(255) NULL DEFAULT NULL,
  `kidneys` VARCHAR(255) NULL DEFAULT NULL,
  `tapping_symptom` VARCHAR(128) NULL DEFAULT NULL,
  `urination` VARCHAR(255) NULL DEFAULT NULL,
  `bowel_status` VARCHAR(128) NULL DEFAULT NULL,
  `swelling` VARCHAR(255) NULL DEFAULT NULL,
  `medical_requests_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_objective_statuses_medical_requests_idx` (`medical_requests_id` ASC) VISIBLE,
  CONSTRAINT `fk_objective_statuses_medical_requests`
    FOREIGN KEY (`medical_requests_id`)
    REFERENCES `mydb`.`medical_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`patients_disabilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients_disabilities` (
  `start_date` TIME NULL DEFAULT NULL,
  `disability_types_id` INT NOT NULL,
  `patients_id` INT NOT NULL,
  INDEX `fk_patients_disabilities_disability_types_idx` (`disability_types_id` ASC) VISIBLE,
  INDEX `fk_patients_disabilities_patients_idx` (`patients_id` ASC) VISIBLE,
  CONSTRAINT `fk_patients_disabilities_disability_types`
    FOREIGN KEY (`disability_types_id`)
    REFERENCES `mydb`.`disability_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_disabilities_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`privileges` (
  `id` INT NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`patients_privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients_privileges` (
  `privileges_id` INT NOT NULL,
  `patients_id` INT NOT NULL,
  INDEX `fk_patients_privileges_privileges_idx` (`privileges_id` ASC) VISIBLE,
  INDEX `fk_patients_privileges_patients_idx` (`patients_id` ASC) VISIBLE,
  CONSTRAINT `fk_patients_privileges_privileges`
    FOREIGN KEY (`privileges_id`)
    REFERENCES `mydb`.`privileges` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patients_privileges_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`permissions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`request_personnel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request_personnel` (
  `action` VARCHAR(255) NULL DEFAULT NULL,
  `medical_requests_id` BIGINT NOT NULL,
  `hospital_personnel_id` INT NOT NULL,
  INDEX `fk_request_personnel_medical_requests_idx` (`medical_requests_id` ASC) VISIBLE,
  INDEX `fk_request_personnel_hospital_personnel_idx` (`hospital_personnel_id` ASC) VISIBLE,
  CONSTRAINT `fk_request_personnel_medical_requests`
    FOREIGN KEY (`medical_requests_id`)
    REFERENCES `mydb`.`medical_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_personnel_hospital_personnel`
    FOREIGN KEY (`hospital_personnel_id`)
    REFERENCES `mydb`.`hospital_personnel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`role_permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role_permissions` (
  `roles_id` INT NOT NULL,
  `permissions_id` INT NOT NULL,
  INDEX `fk_role_permissions_roles_idx` (`roles_id` ASC) VISIBLE,
  INDEX `fk_role_permissions_permissions_idx` (`permissions_id` ASC) VISIBLE,
  CONSTRAINT `fk_role_permissions_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_permissions_permissions`
    FOREIGN KEY (`permissions_id`)
    REFERENCES `mydb`.`permissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tokens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tokens` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` TEXT(4096) NOT NULL,
  `hospital_personnel_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_tokens_hospital_personnel_idx` (`hospital_personnel_id` ASC) VISIBLE,
  CONSTRAINT `fk_tokens_hospital_personnel`
    FOREIGN KEY (`hospital_personnel_id`)
    REFERENCES `mydb`.`hospital_personnel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

