INSERT INTO person (first_name, last_name, birth_date, country, gender)
VALUES ('Clint', 'Eastwood', '1930-05-31', 'USA', 'MALE'),
       ('Johnny', 'Deep', '1963-06-09', 'USA', 'MALE'),
       ('John Carroll', 'Lynch', '1963-08-01', 'USA', 'MALE'),
       ('Helena Bonham', 'Carter', '1966-05-26', 'Anglia', 'FEMALE'),
       ('Tim', 'Burton', '1958-08-25', 'USA', 'MALE');

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

INSERT INTO movie(title, premiere_date, description, poster_image_file_path)
VALUES ('Gran Torino', '2008-12-09',
        'Walt Kowalski to emerytowany weteran żyjący we własnym poukładanym świecie. ' ||
        'Jego spokój zostaje zburzony przez nowych sąsiadów z Azji, których syn spróbuje ukraść mu ulubione auto.',
        'poster/1/1_Gran_Torino_2021-05-01.jpg'),
       ('SWEENEY TODD', '2007-12-03',
        'Golibroda wraca do wiktoriańskiego Londynu, by zemścić się na porywaczu żony i córki. We współpracy z sąsiadką otwiera zakład fryzjerski, dzięki któremu restauracja wspólniczki zaopatrywana jest w nadzienie do pasztecików.',
        'poster/2/2_Sweeney Tod_2021-05-02.png'),
       ('Nowiny ze świata', '2020-12-25',
        'Jego akcja toczy się krótko po zakończeniu wojny secesyjnej. Głównym bohaterem historii jest weteran, kapitan Jeffrey Kidd (niezawodny Tom Hanks), który jeździ od miasta do miasta i za niewielką opłatą czyta mieszkańcom miasteczek wiadomości.',
        'poster/3/3_Nowiny ze świata_21-05-02.png');

INSERT INTO role(name, person_id, movie_id, function)
VALUES ('Walt Kowalski', 1, 1, 'ACTOR'),
       ('', 1, 1, 'DIRECTOR'),
       ('Hair Cutter', 3, 1, 'ACTOR'),
       ('Sweeney Todd', 2, 2, 'ACTOR'),
       ('Ms. Lovett', 4, 2, 'ACTOR'),
       ('', 5, 2, 'DIRECTOR');

INSERT INTO movies_genres(movie_id, genre_id)
VALUES (1, 4),
       (2, 5),
       (2, 8),
       (3, 9),
       (3, 4);