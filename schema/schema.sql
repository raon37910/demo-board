CREATE TABLE `User` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `email` varchar(45),
                        `password` varchar(45),
                        `createTime` datetime,
                        `role` varchar(45)
);

CREATE TABLE `Post` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `title` varchar(45),
                        `contents` varchar(500),
                        `createTime` datetime,
                        `views` int,
                        `categoryId` int,
                        `userId` int
);

CREATE TABLE `Category` (
                            `id` int PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(45)
);

CREATE TABLE `PostTag` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `postId` int,
                           `tagId` int
);

CREATE TABLE `Comment` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `contents` varchar(100),
                           `parentCommentId` int,
                           `userId` int
);

CREATE TABLE `Tag` (
                       `id` int PRIMARY KEY AUTO_INCREMENT,
                       `name` varchar(45)
);

ALTER TABLE `Post` ADD FOREIGN KEY (`categoryId`) REFERENCES `Category` (`id`);

ALTER TABLE `Post` ADD FOREIGN KEY (`userId`) REFERENCES `User` (`id`);

ALTER TABLE `PostTag` ADD FOREIGN KEY (`postId`) REFERENCES `Post` (`id`);

ALTER TABLE `PostTag` ADD FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`);

ALTER TABLE `Comment` ADD FOREIGN KEY (`parentCommentId`) REFERENCES `Comment` (`id`);

ALTER TABLE `Comment` ADD FOREIGN KEY (`userId`) REFERENCES `User` (`id`);
