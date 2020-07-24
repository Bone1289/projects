INSERT INTO regions(id, name) value (1, 'South America');
INSERT INTO regions(id, name) value (2, 'Central America');
INSERT INTO regions(id, name) value (3, 'North America');
INSERT INTO regions(id, name) value (4, 'Europa');
INSERT INTO regions(id, name) value (5, 'Asia');
INSERT INTO regions(id, name) value (6, 'Africa');
INSERT INTO regions(id, name) value (7, 'Oceania');
INSERT INTO regions(id, name) value (8, 'Antartica');


INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (1,now(), 'first.name@email.com', 'First', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (3,now(), 'second.last@emai.com', 'Second', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (4,now(), 'third.name@email.com', 'Third', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (5,now(), 'fourth.last@emai.com', 'Fourth', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (6,now(), 'primera.name@email.com', 'Primera', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (2,now(), 'segundo.last@emai.com', 'Segundo', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (1,now(), 'tercero.name@email.com', 'Tercero', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (5,now(), 'cuarto.last@emai.com', 'Second', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (6,now(), 'segundo.last1@emai.com', 'Segundo', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (2,now(), 'tercero.name1@email.com', 'Tercero', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (1,now(), 'cuarto.last1@emai.com', 'Second', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (4,now(), 'first.name1@email.com', 'First', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (5,now(), 'second.last1@emai.com', 'Second', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (6,now(), 'third.name1@email.com', 'Third', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (7,now(), 'fourth.last1@emai.com', 'Fourth', 'Last');
INSERT INTO `client` (`region_id`, `create_at`, `email`, `first_name`, `last_name`) VALUES (1,now(), 'primera.name1@email.com', 'Primera', 'Last');

INSERT INTO `users` (`enabled`, `password`, `username`,`first_name`,`last_name`, `email`) VALUES (1, '$2a$10$RmSrqCooaFKrXCIqktv5v.z4tBXgbu9my0Q4IKjsIp.fGIqE/.YCm', 'admin','admin', 'admin', 'admin@admin.com');
INSERT INTO `users` (`enabled`, `password`, `username`,`first_name`,`last_name`, `email`) VALUES (1, '$2a$10$J0/NaFkJrMTisw6Xp/SIwey285sZNugrzzzHXeUtwuDvnxpUKYAHa', 'bogdan', 'Spring', 'One', 'spring@one.com');

INSERT INTO `roles` (`name`) VALUES ('ROLE_USER');
INSERT INTO `roles` (`name`) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('2', '1');

INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'IPhone', '100');
INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'MacBook Pro', '2000');
INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'Bike', '10');
INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'Samisug', '200');
INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'Playstation', '100');
INSERT INTO `spring-spain`.`products` (`create_at`, `name`, `price`) VALUES (NOW(), 'XBox', '100');

INSERT INTO `spring-spain`.`invoices` (`create_at`, `description`, `observation`, `client_id`) VALUES (NOW(), 'Console', 'Console Mishto', '1');
INSERT INTO `spring-spain`.`invoice_item` (`quantity`, `product_id`, `invoice_id`) VALUES ('3', '5', '1');
INSERT INTO `spring-spain`.`invoice_item` (`quantity`, `product_id`, `invoice_id`) VALUES ('1', '6', '1');

INSERT INTO `spring-spain`.`invoices` (`create_at`, `description`, `observation`, `client_id`) VALUES (NOW(), 'Mobile', 'Mobile', '2');
INSERT INTO `spring-spain`.`invoice_item` (`quantity`, `product_id`, `invoice_id`) VALUES ('1', '1', '2');
INSERT INTO `spring-spain`.`invoice_item` (`quantity`, `product_id`, `invoice_id`) VALUES ('2', '4', '2');



