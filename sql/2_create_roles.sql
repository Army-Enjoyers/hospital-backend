INSERT INTO `mydb`.`roles`(`id`, `name`) VALUES(1, 'ADMIN'), (2, 'USER');

INSERT INTO `mydb`.`permissions`(`id`, `name`) VALUES(1, 'admin:*'), (2, 'user:get:*'), (3, 'user:create:*');
INSERT INTO `mydb`.`role_permissions`(`roles_id`, `permissions_id`) VALUES(1, 1), (2, 2), (2,3);