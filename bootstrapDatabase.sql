DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int NOT NULL,
  `recipient` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`sender`) REFERENCES users(id),
  FOREIGN KEY (`recipient`) REFERENCES users(id)
);

INSERT INTO `users`
(`username`, `password`, `first_name`,`last_name`)
VALUES
('dali546', 'fishbrain', 'Muhammed', 'Ali'),
('feadoor', 'peabrain', 'Sam', 'L Jackson'),
('xXx_squidward_xXX', 'damnyouspongebob', 'Squidward', 'Tentacles'),
('thevoice', 'ofgod', 'Morgan', 'Freeman'),
('capnJack', 'drinkupmehearties', 'Captain. Jack', 'Sparrow');

INSERT INTO `posts`
(`sender`, `recipient`, `content`)
VALUES
('1', '2', 'Hi Sam, do you like my new social network?'),
('2', '1', 'Hey Mo, Yes its amazing. you deserve an award'),
('5', '4', 'Master Gibbs, what happened to the rum?'),
('4', '5', 'Jack, I drank it all. How do you think i get my voice this deep.'),
('3', '1', 'I hate all of you.');
