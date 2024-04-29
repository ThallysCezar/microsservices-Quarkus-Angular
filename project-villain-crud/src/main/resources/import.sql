CREATE SEQUENCE hibernate_sequence START 4;


INSERT INTO villain(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Majora', 'Skull Kid', 'https://s1.zerochan.net/Zelda.no.Densetsu%3A.Mujura.no.Kamen.600.2270437.jpg', 'Dark magic', 45);
INSERT INTO villain(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Ganondorf', 'Dragmire', 'https://www.superherodb.com/gallery/075/20/2096.webp', 'Electrokinesis', 55);
INSERT INTO villain(id, name, otherName, picture, powers, level)
VALUES (nextval('hibernate_sequence'), 'Vaati', '', 'https://s1.zerochan.net/Vaati.600.175537.jpg
', 'Swordsmanship', 35);