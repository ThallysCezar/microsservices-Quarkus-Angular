CREATE SEQUENCE hibernate_sequence START 4;

INSERT INTO fight(id, fightDate, winnerName, winnerLevel, winnerPicture, loserName, loserLevel, loserPicture, winnerTeam, loserTeam)
VALUES (nextval('hibernate_sequence'), current_timestamp, 'Link', 70, 'https://www.zeldadungeon.net/wiki/images/b/b4/Link_Champion_Tunic_-_HWAoC.png', 'Ganondorf', 55, 'https://www.superherodb.com/gallery/075/20/2096.webp', 'heroes', 'villains');
INSERT INTO fight(id, fightDate, winnerName, winnerLevel, winnerPicture, loserName, loserLevel, loserPicture, winnerTeam ,loserTeam)
VALUES (nextval('hibernate_sequence'), current_timestamp, 'Daruk', 40, 'https://static.zerochan.net/Daruk.%28Breath.of.the.Wild%29.1024.2068326.webp', 'Vaati', 35, 'https://s1.zerochan.net/Vaati.600.175537.jpg', 'heroes', 'villains');
INSERT INTO fight(id, fightDate, winnerName, winnerLevel, winnerPicture, loserName, loserLevel, loserPicture, winnerTeam ,loserTeam)
VALUES (nextval('hibernate_sequence'), current_timestamp, 'Majora', 45, 'https://s1.zerochan.net/Zelda.no.Densetsu%3A.Mujura.no.Kamen.600.2270437.jpg', 'Link', 30, 'https://s1.zerochan.net/Young.Link.600.2796271.jpg', 'villains', 'heroes');
INSERT INTO fight(id, fightDate, winnerName, winnerLevel, winnerPicture, loserName, loserLevel, loserPicture, winnerTeam ,loserTeam)
VALUES (nextval('hibernate_sequence'), current_timestamp, 'Ganondorf', 55, 'https://www.superherodb.com/gallery/075/20/2096.webp', 'Link', 70, 'https://www.zeldadungeon.net/wiki/images/b/b4/Link_Champion_Tunic_-_HWAoC.png', 'villains', 'heroes');