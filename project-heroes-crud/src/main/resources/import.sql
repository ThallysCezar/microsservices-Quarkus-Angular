CREATE SEQUENCE hibernate_sequence START 4;


INSERT INTO hero(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Link', 'Hero Of The Wild', 'https://www.zeldadungeon.net/wiki/images/b/b4/Link_Champion_Tunic_-_HWAoC.png', 'Master swordsman', 70);
INSERT INTO hero(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Link', 'Child', 'https://s1.zerochan.net/Young.Link.600.2796271.jpg', 'Boomerang', 30);
INSERT INTO hero(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Daruk', '', 'https://static.zerochan.net/Daruk.%28Breath.of.the.Wild%29.1024.2068326.webp
', 'Daruks Protection', 40);