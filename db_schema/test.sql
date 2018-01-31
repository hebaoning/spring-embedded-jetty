DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `age` int(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

INSERT INTO `users` (`name`, `age`) VALUES ('pailie', '1');
