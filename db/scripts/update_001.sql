create table person (
    person_id serial primary key NOT NULL,
    person_login varchar(2000) NOT NULL unique,
    person_password varchar(2000) NOT NULL
);

insert into person (person_login, person_password) values ('parsentev', '123');
insert into person (person_login, person_password) values ('ban', '123');
insert into person (person_login, person_password) values ('ivan', '123');