-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`contingent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`contingent` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`military_affiliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`military_affiliations` (
                                                              `id` INT NOT NULL AUTO_INCREMENT,
                                                              `military_rank` ENUM("рядовой", "ефрейтор", "младший сержант", "сержант", "старший сержант", "старшина", "прапорщик", "старший прапорщик", "младший лейтенант", "лейтенант", "старший лейтенант", "капитан", "майор", "подполковник", "полковник", "генерал-майор", "генерал-лейтенант", "генерал-полковник") NOT NULL,
    `serviceman_category` ENUM("офицеры", "прапорщики", "контрактники", "срочники", "курсанты") NOT NULL,
    `military_comissariat` VARCHAR(255) NOT NULL,
    `conscription_date` TIMESTAMP(4) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`life_anamnesis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`life_anamnesis` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `value` MEDIUMTEXT NULL,
                                                       PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `last_name` VARCHAR(128) NOT NULL,
    `fisrt_name` VARCHAR(128) NOT NULL,
    `patronymic` VARCHAR(128) NOT NULL,
    `birth_date` TIMESTAMP(4) NOT NULL,
    `gender` VARCHAR(128) NOT NULL,
    `citizenship` VARCHAR(128) NOT NULL,
    `job` VARCHAR(255) NULL,
    `polyclinic` VARCHAR(128) NOT NULL,
    `pensioner_id` VARCHAR(128) NULL,
    `outpatient_card_number` VARCHAR(255) NULL,
    `additional_info` TEXT(1024) NULL,
    `contingent_id` INT NOT NULL,
    `anamnesis_of_life_id` INT NOT NULL,
    `military_affiliations_id` INT NULL,
    `military_affiliations_id1` INT NULL,
    `contingent_id1` INT NOT NULL,
    `life_anamnesis_id` INT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_patients_contingent1_idx` (`contingent_id1` ASC),
    INDEX `fk_patients_military_affiliations1_idx` (`military_affiliations_id1` ASC),
    INDEX `fk_patients_life_anamnesis1_idx` (`life_anamnesis_id` ASC),
    CONSTRAINT `fk_patients_contingent1`
    FOREIGN KEY (`contingent_id1`)
    REFERENCES `mydb`.`contingent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_patients_military_affiliations1`
    FOREIGN KEY (`military_affiliations_id1`)
    REFERENCES `mydb`.`military_affiliations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_patients_life_anamnesis1`
    FOREIGN KEY (`life_anamnesis_id`)
    REFERENCES `mydb`.`life_anamnesis` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`street_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`street_types` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`locality_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`locality_type` (
                                                      `id` INT NOT NULL,
                                                      `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`placement_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`placement_types` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`addresses` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `region` VARCHAR(128) NOT NULL,
    `district` VARCHAR(128) NOT NULL,
    `locality` VARCHAR(128) NOT NULL,
    `street_adress` VARCHAR(128) NOT NULL,
    `house_number` VARCHAR(64) NOT NULL,
    `placement_number` VARCHAR(64) NOT NULL,
    `phone_number` VARCHAR(128) NOT NULL,
    `home_phone_number` VARCHAR(128) NULL,
    `street_types_id` INT NOT NULL,
    `locality_type_id` INT NOT NULL,
    `placement_types_id` INT NOT NULL,
    `patients_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_addresses_street_types1_idx` (`street_types_id` ASC),
    INDEX `fk_addresses_locality_type1_idx` (`locality_type_id` ASC),
    INDEX `fk_addresses_placement_types1_idx` (`placement_types_id` ASC),
    INDEX `fk_addresses_patients1_idx` (`patients_id` ASC),
    CONSTRAINT `fk_addresses_street_types`
    FOREIGN KEY (`street_types_id`)
    REFERENCES `mydb`.`street_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_addresses_locality_type`
    FOREIGN KEY (`locality_type_id`)
    REFERENCES `mydb`.`locality_type` (`id`)
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
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`disability_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`disability_type` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`privileges` (
                                                   `id` INT NOT NULL,
                                                   `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`patients_disability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients_disability` (
                                                            `start_date` TIME NULL,
                                                            `patients_id` INT(11) NOT NULL,
    `disability_type_id` INT NOT NULL,
    INDEX `fk_patients_disability_patients1_idx` (`patients_id` ASC),
    INDEX `fk_patients_disability_disability_type1_idx` (`disability_type_id` ASC),
    CONSTRAINT `fk_patients_disability_patients1`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_patients_disability_disability_type1`
    FOREIGN KEY (`disability_type_id`)
    REFERENCES `mydb`.`disability_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`patients_privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`patients_privileges` (
    `patients_id1` INT(11) NOT NULL,
    `privileges_id1` INT NOT NULL,
    INDEX `fk_patients_privileges_patients1_idx` (`patients_id1` ASC),
    INDEX `fk_patients_privileges_privileges1_idx` (`privileges_id1` ASC),
    CONSTRAINT `fk_patients_privileges_patients1`
    FOREIGN KEY (`patients_id1`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_patients_privileges_privileges1`
    FOREIGN KEY (`privileges_id1`)
    REFERENCES `mydb`.`privileges` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`medical_board_requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medical_board_requests` (
                                                               `id` INT NOT NULL AUTO_INCREMENT,
                                                               `board_name` VARCHAR(128) NOT NULL,
    `result` TEXT(1024) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`request_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request_types` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `name` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`treatment_referrals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`treatment_referrals` (
                                                            `id` INT NOT NULL AUTO_INCREMENT,
                                                            `destination` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paid_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paid_services` (
    `id` BIGINT(14) NOT NULL,
    `contract_number` VARCHAR(128) NOT NULL,
    `insurance_number` VARCHAR(128) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`medical_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medical_request` (
    `id` BIGINT(14) NOT NULL,
    `request_date` TIMESTAMP(4) NOT NULL,
    `home_visit` SMALLINT(4) NOT NULL,
    `planned_hospitalization` SMALLINT(4) NULL,
    `patients_id` INT(11) NOT NULL,
    `request_types_id` INT NOT NULL,
    `treatment_referrals_id` INT NULL,
    `medical_boards_id` INT NULL,
    `paid_services_id` BIGINT(14) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_medical_request_medical_boards1_idx` (`medical_boards_id` ASC),
    INDEX `fk_medical_request_request_types1_idx` (`request_types_id` ASC),
    INDEX `fk_medical_request_treatment_referrals1_idx` (`treatment_referrals_id` ASC),
    INDEX `fk_medical_request_patients1_idx` (`patients_id` ASC),
    INDEX `fk_medical_request_paid_services1_idx` (`paid_services_id` ASC),
    CONSTRAINT `fk_medical_request_medical_boards`
    FOREIGN KEY (`medical_boards_id`)
    REFERENCES `mydb`.`medical_board_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_medical_request_request_types`
    FOREIGN KEY (`request_types_id`)
    REFERENCES `mydb`.`request_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_medical_request_treatment_referrals`
    FOREIGN KEY (`treatment_referrals_id`)
    REFERENCES `mydb`.`treatment_referrals` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_medical_request_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_medical_request_paid_services`
    FOREIGN KEY (`paid_services_id`)
    REFERENCES `mydb`.`paid_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`completed_procedures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`completed_procedures` (
                                                             `id` INT NOT NULL AUTO_INCREMENT,
                                                             `name` TEXT(1024) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`manipulations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`manipulations` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `name` TEXT(1024) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`diseases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`diseases` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `mkb10_code` VARCHAR(255) NOT NULL,
    `main_diagnosis` TEXT(16384) NOT NULL,
    `co_diagnosis` TEXT(4096) NULL,
    `complications` TEXT(4096) NULL,
    `anamnesis` TEXT(65535) NULL,
    `complaints` TEXT(4096) NULL,
    `lab_examination_interpretation` TEXT(8192) NULL,
    `instrumental_examination_interpretation` TEXT(8192) NULL,
    `extra` TEXT(8192) NULL,
    `recommendations` TEXT(8192) NULL,
    `procedures_id` INT NULL,
    `manipulations_id` INT NULL,
    `medical_request_id` BIGINT(14) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_diseases_procedures1_idx` (`procedures_id` ASC),
    INDEX `fk_diseases_manipulations1_idx` (`manipulations_id` ASC),
    INDEX `fk_diseases_medical_request1_idx` (`medical_request_id` ASC),
    CONSTRAINT `fk_diseases_procedures1`
    FOREIGN KEY (`procedures_id`)
    REFERENCES `mydb`.`completed_procedures` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_diseases_manipulations1`
    FOREIGN KEY (`manipulations_id`)
    REFERENCES `mydb`.`manipulations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_diseases_medical_request1`
    FOREIGN KEY (`medical_request_id`)
    REFERENCES `mydb`.`medical_request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`objective_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`objective_statuses` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `temperature` FLOAT NULL,
                                                           `body_mass_index` FLOAT NULL,
                                                           `consciousness` VARCHAR(255) NULL,
    `skin` TEXT(1024) NULL,
    `visible_mucous_membranes` VARCHAR(255) NULL,
    `palatine_tonsils` VARCHAR(255) NULL,
    `lymph_nodes` VARCHAR(255) NULL,
    `thyroid` VARCHAR(255) NULL,
    `osteoarticular_system` TEXT(1024) NULL,
    `muscular_system` TEXT(1024) NULL,
    `breathing_rate` VARCHAR(255) NULL,
    `percussion_sound` VARCHAR(255) NULL,
    `breathing_sound` VARCHAR(255) NULL,
    `pulse_rate` VARCHAR(128) NULL,
    `pulse_deficit` VARCHAR(128) NULL,
    `pulse_filling` VARCHAR(128) NULL,
    `pulse_info` VARCHAR(255) NULL,
    `arterial_pressure` VARCHAR(128) NULL,
    `heart_info` TEXT(1024) NULL,
    `tongue` VARCHAR(128) NULL,
    `stomach` VARCHAR(255) NULL,
    `liver` VARCHAR(255) NULL,
    `spleen` VARCHAR(255) NULL,
    `kidneys` VARCHAR(255) NULL,
    `tapping_symptom` VARCHAR(128) NULL,
    `urination` VARCHAR(255) NULL,
    `bowel_status` VARCHAR(128) NULL,
    `swelling` VARCHAR(255) NULL,
    `medical_request_id` BIGINT(14) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_objective_statuses_medical_request1_idx` (`medical_request_id` ASC),
    CONSTRAINT `fk_objective_statuses_medical_request`
    FOREIGN KEY (`medical_request_id`)
    REFERENCES `mydb`.`medical_request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`hospital_personel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hospital_personel` (
                                                          `id` INT NOT NULL AUTO_INCREMENT,
                                                          `first_name` VARCHAR(128) NOT NULL,
    `last_name` VARCHAR(128) NOT NULL,
    `patronymic` VARCHAR(128) NOT NULL,
    `login` VARCHAR(128) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `possition` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


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
    `purpose` VARCHAR(255) NULL,
    `note` VARCHAR(255) NULL,
    `patients_id` INT(11) NOT NULL,
    `hospital_personel_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `fk_hospital_certificates_patients1_idx` (`patients_id` ASC),
    INDEX `fk_hospital_certificates_hospital_personel1_idx` (`hospital_personel_id` ASC),
    CONSTRAINT `fk_hospital_certificates_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `mydb`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_hospital_certificates_hospital_personel`
    FOREIGN KEY (`hospital_personel_id`)
    REFERENCES `mydb`.`hospital_personel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
                                              `id` INT NOT NULL,
                                              `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`permissions` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`hospital_personel_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hospital_personel_roles` (
                                                                `hospital_personel_id` INT NOT NULL,
                                                                `roles_id` INT NOT NULL,
                                                                INDEX `fk_hospital_personel_roles_hospital_personel1_idx` (`hospital_personel_id` ASC),
    INDEX `fk_hospital_personel_roles_roles1_idx` (`roles_id` ASC),
    CONSTRAINT `fk_hospital_personel_roles_hospital_personel1`
    FOREIGN KEY (`hospital_personel_id`)
    REFERENCES `mydb`.`hospital_personel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_hospital_personel_roles_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`role_permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role_permissions` (
                                                         `permissions_id` INT NOT NULL,
                                                         `roles_id` INT NOT NULL,
                                                         INDEX `fk_role_permissions_permissions1_idx` (`permissions_id` ASC),
    INDEX `fk_role_permissions_roles1_idx` (`roles_id` ASC),
    CONSTRAINT `fk_role_permissions_permissions1`
    FOREIGN KEY (`permissions_id`)
    REFERENCES `mydb`.`permissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_role_permissions_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`request_personel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request_personel` (
    `medical_request_id` BIGINT(14) NOT NULL,
    `hospital_personel_id` INT NOT NULL,
    `action` VARCHAR(255) NULL,
    INDEX `fk_request_personel_medical_request1_idx` (`medical_request_id` ASC),
    INDEX `fk_request_personel_hospital_personel1_idx` (`hospital_personel_id` ASC),
    CONSTRAINT `fk_request_personel_medical_request1`
    FOREIGN KEY (`medical_request_id`)
    REFERENCES `mydb`.`medical_request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_request_personel_hospital_personel1`
    FOREIGN KEY (`hospital_personel_id`)
    REFERENCES `mydb`.`hospital_personel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
