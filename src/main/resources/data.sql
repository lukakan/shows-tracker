INSERT INTO person (first_name, last_name, birth_date, country, gender)
VALUES ('Clint', 'Eastwood', '1930-05-31', 'USA', 'MALE'),
       ('Johnny', 'Deep', '1963-06-09', 'USA', 'MALE'),
       ('John Carroll', 'Lynch', '1963-08-01', 'USA', 'MALE'),
       ('Helena Bonham', 'Carter', '1966-05-26', 'Anglia', 'FEMALE'),
       ('Tim', 'Burton', '1958-08-25', 'USA', 'MALE');

INSERT INTO function (name)
VALUES ('Director'),
       ('Actor');

INSERT INTO genre (name)
VALUES ('COMEDY'),
       ('ACTION'),
       ('HORROR'),
       ('DRAMA'),
       ('THRILLER'),
       ('ANIMATION'),
       ('SCI-FI'),
       ('MUSICAL'),
       ('WESTERN');

INSERT INTO movie(title, premiere_date, description)
VALUES ('Gran Torino', '2008-12-09',
        'Walt Kowalski to emerytowany weteran żyjący we własnym poukładanym świecie. Jego spokój zostaje zburzony przez nowych sąsiadów z Azji, których syn spróbuje ukraść mu ulubione auto.'),
       ('SWEENEY TODD', '2007-12-03',
        'Golibroda wraca do wiktoriańskiego Londynu, by zemścić się na porywaczu żony i córki. We współpracy z sąsiadką otwiera zakład fryzjerski, dzięki któremu restauracja wspólniczki zaopatrywana jest w nadzienie do pasztecików.'),
       ('Nowiny ze świata', '2020-12-25',
        'Jego akcja toczy się krótko po zakończeniu wojny secesyjnej. Głównym bohaterem historii jest weteran, kapitan Jeffrey Kidd (niezawodny Tom Hanks), który jeździ od miasta do miasta i za niewielką opłatą czyta mieszkańcom miasteczek wiadomości.');

INSERT INTO role(name, person_id, movie_id, function_id)
VALUES ('Walt Kowalski', 1, 1, 2),
       ('', 1, 1, 1),
       ('Hair Cutter', 3, 1, 2),
       ('Sweeney Todd', 2, 2, 2),
       ('Ms. Lovett', 4, 2, 2),
       ('', 5, 2, 1);

INSERT INTO genres_movies(genre_id, movie_id)
VALUES (4, 1),
       (5, 2),
       (8, 2),
       (9, 3),
       (4, 3);