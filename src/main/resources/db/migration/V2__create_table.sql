DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(50) NOT NULL,
    `detail` VARCHAR(255),
    `status` BOOLEAN,
    PRIMARY KEY (`id`)
)DEFAULT CHARSET=utf8;

