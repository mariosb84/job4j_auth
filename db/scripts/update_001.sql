create table person (
    person_id serial primary key not null,
    person_login varchar(2000),
    person_password varchar(2000)
);

insert into person (person_login, person_password) values ('parsentev', '123');
insert into person (person_login, person_password) values ('ban', '123');
insert into person (person_login, person_password) values ('ivan', '123');