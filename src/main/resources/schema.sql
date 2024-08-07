-- Drop tables if they exist
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `CartItem`;
DROP TABLE IF EXISTS `Cart`;
DROP TABLE IF EXISTS `Product`;
DROP TABLE IF EXISTS `Category`;
DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS `ROLE`;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `ROLE` (
                        `roleId` INT NOT NULL AUTO_INCREMENT,
                        `auth` VARCHAR(10) NULL,
                        PRIMARY KEY (`roleId`)
);

CREATE TABLE `MEMBER` (
                          `memberId` INT NOT NULL AUTO_INCREMENT,
                          `roleId` INT NOT NULL,
                          `email` VARCHAR(40) NULL,
                          `name` VARCHAR(20) NULL,
                          `password` VARCHAR(20) NULL,
                          `birth` DATE NULL,
                          `point` INT NULL,
                          `createAt` DATE NULL,
                          PRIMARY KEY (`memberId`),
                          FOREIGN KEY (`roleId`) REFERENCES `ROLE`(`roleId`) ON DELETE CASCADE
);

CREATE TABLE `Category` (
                            `categoryId` INT NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(20) NULL,
                            PRIMARY KEY (`categoryId`)
);

CREATE TABLE `Product` (
                           `productId` INT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(40) NULL,
                           `price` INT NULL,
                           `description` VARCHAR(1000) NULL,
                           `image` VARCHAR(1000) NULL,
                           `categoryId` INT,
                           PRIMARY KEY (`productId`),
                           FOREIGN KEY (`categoryId`) REFERENCES `Category`(`categoryId`) ON DELETE SET NULL
);

CREATE TABLE `Cart` (
                        `cartId` INT NOT NULL AUTO_INCREMENT,
                        `memberId` INT NOT NULL,
                        `createAt` DATE NULL,
                        PRIMARY KEY (`cartId`),
                        FOREIGN KEY (`memberId`) REFERENCES `MEMBER`(`memberId`) ON DELETE CASCADE
);

CREATE TABLE `CartItem` (
                            `cartItemId` INT NOT NULL AUTO_INCREMENT,
                            `cartId` INT NOT NULL,
                            `productId` INT NOT NULL,
                            `quantity` INT NOT NULL,
                            PRIMARY KEY (`cartItemId`),
                            FOREIGN KEY (`cartId`) REFERENCES `Cart`(`cartId`) ON DELETE CASCADE,
                            FOREIGN KEY (`productId`) REFERENCES `Product`(`productId`) ON DELETE CASCADE
);

-- Insert role for user
INSERT INTO ROLE (auth) VALUES ('USER');

-- Insert role for admin
INSERT INTO ROLE (auth) VALUES ('ADMIN');

INSERT INTO `Category` (`name`) VALUES ('smartphone');
INSERT INTO `Category` (`name`) VALUES ('tablet');
INSERT INTO `Category` (`name`) VALUES ('notebook');
INSERT INTO `Category` (`name`) VALUES ('watch');
INSERT INTO `Category` (`name`) VALUES ('buds');

INSERT INTO `Product` (`productId`, `categoryId`, `name`, `image`, `price`, `description`) VALUES
                                                                                               (1, 1, '갤럭시 Z 플립5 레트로', '1.webp', 1599400, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (2, 1, '갤럭시 Z 플립5 자급제', '2.webp', 1263000, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (3, 1, '갤럭시 S23 FE 자급제', '3.webp', 1599400, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (4, 1, '갤럭시 Z 플립5 메종', '4.webp', 1906000, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (5, 1, '갤럭시 A24 자급제', '5.webp', 1599400, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (6, 1, '갤럭시 점프3 사업자향', '6.webp', 1263000, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (7, 1, '갤럭시 A34 5G 자급제', '7.webp', 1599400, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (8, 1, '마르지엘라 에디션', '8.webp', 1906000, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (9, 1, '갤럭시 Z 폴드5 톰브라운', '9.webp', 1906000, '2003년과 2023년의 만남2003년에 출시된 SGH-E700에는 삼성의 혁신적인 생각이 담겨있습니다.내장된 안테나, 전면 카메라, 매끈한 클램셸 디자인 등을 통해 삼성은 트렌드를 선도하는 새로운 기준이 되었습니다'),
                                                                                               (10, 2, '갤럭시 탭 A9', '10.webp', 1263000, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (11, 2, '갤럭시 탭 S9', '11.webp', 1599400, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (12, 2, '갤럭시 탭 S9 Ultra', '12.webp', 1906000, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (13, 2, '갤럭시 탭 S7 FE', '13.webp', 1599400, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (14, 2, '갤럭시 탭 S8 FE', '14.webp', 1263000, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (15, 2, '갤럭시 탭 S6 Lite', '15.webp', 1599400, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (16, 2, '갤럭시 탭 S8 울트라', '16.webp', 1906000, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (17, 2, '갤럭시 탭 S7+', '17.webp', 1599400, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (18, 2, '갤럭시 탭 S9+', '18.webp', 1263000, '그리고 갤럭시 탭 S9 Ultra.세 가지 탭 모두 다이나믹 아몰레드 2X 디스플레이를 탑재했습니다.선명하고 깨끗한 색상과 형태를 구현하면서, 블루라이트를 줄여줘보다 편안하고 만족스러운 시청 경험을 선사하죠.'),
                                                                                               (19, 3, '갤럭시북 2 프로 360', '19.webp', 1906000, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (20, 3, '갤럭시북 3 프로', '20.webp', 1263000, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (21, 3, '갤럭시북 프로 360', '21.webp', 1599400, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (22, 3, '갤럭시북 이온', '22.webp', 1599400, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (23, 3, '갤럭시북 2', '23.webp', 1906000, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (24, 3, '갤럭시북 3 프로 360', '24.webp', 1263000, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (25, 3, '갤럭시북 플렉스', '25.webp', 1906000, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (26, 3, '갤럭시북 3 울트라', '26.webp', 1599400, '역대 가장 강력한 갤럭시북 역대 갤럭시북 중 가장 강력한 성능을 갖춘 갤럭시북3 울트라.그 비결은 인텔의 가장 강력한 프로세서와 엔비디아의 초고속 그래픽카드입니다.두 가지가 조화를 이뤄갤럭시북의 혁신적 성능이 완성되었죠.'),
                                                                                               (27, 4, '갤럭시 워치6 클래식', '27.webp', 1263000, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (28, 4, '갤럭시 워치6', '28.webp', 1599400, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (29, 4, '갤럭시 워치5 프로 골프 에디션', '29.webp', 1906000, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (30, 4, '갤럭시 워치5', '30.webp', 1599400, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (31, 4, '갤럭시 워치5 프로', '31.webp', 1263000, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (32, 4, '갤럭시 워치4 클래식', '32.webp', 1599400, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (33, 4, '갤럭시 워치4 골프 에디션', '33.webp', 1906000, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (34, 4, '갤럭시 워치4 LTE 알루미늄', '34.webp', 1599400, '갤럭시 워치 시리즈에 대한 설명입니다.'),
                                                                                               (35, 5, '갤럭시 버즈2 Pro', '35.webp', 1263000, '갤럭시 버즈 시리즈에 대한 설명입니다.');