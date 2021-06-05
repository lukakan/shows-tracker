INSERT INTO person (first_name, last_name, birth_date, country, gender, height)
VALUES ('Clint', 'Eastwood', '1930-05-31', 'USA', 'MALE', 193),
       ('Johnny', 'Deep', '1963-06-09', 'USA', 'MALE', 178),
       ('John Carroll', 'Lynch', '1963-08-01', 'USA', 'MALE', 193),
       ('Helena Bonham', 'Carter', '1966-05-26', 'Anglia', 'FEMALE', 153),
       ('Tim', 'Burton', '1958-08-25', 'USA', 'MALE', 182);

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

INSERT INTO role(name, person_id, movie_id, function_type)
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

INSERT INTO user(id, user_name, email, password, first_name, last_name)
VALUES
(1, 'roman','example@gmail.com' , '{noop}qwerty', 'Romuald', 'Polański'),
(2, 'admin','example@gmail.com' , '{noop}qwerty', 'Edek', 'Polański'),
(3, 'someuser','example@gmail.com' , '{noop}qwerty', 'Grzegorz', 'Nowak'),
(4, 'superuser','adi@gmail.com' , '{noop}qwerty', 'Adrian', 'Kowalski');

INSERT INTO user_role(id, user_id, role)
VALUES
(1, 1, 'ROLE_USER'),
(2, 3, 'ROLE_USER'),
(3, 2, 'ROLE_ADMIN'),
(4, 4, 'ROLE_ADMIN'),
(5, 4, 'ROLE_USER');