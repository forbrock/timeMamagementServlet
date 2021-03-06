create database db_time_management;

create table if not exists users
(
    id         int primary key                       not null auto_increment,
    first_name varchar(30)                           not null,
    last_name  varchar(30)                           not null,
    email      varchar(50)                           not null,
    password   varchar(100)                          not null,
    enabled    boolean                default 1      not null,
    role       enum ('USER', 'ADMIN') default 'USER' not null,
    unique (email)
);

create table if not exists categories
(
    id   int primary key not null auto_increment,
    name varchar(30)     not null,
    unique (name)
);

create table if not exists activities
(
    id          int primary key not null auto_increment,
    name        varchar(30)     not null,
    category_id int             not null,
    unique (name),
    constraint activities_categories_id_fk
        foreign key (category_id) references categories (id)
);

create table if not exists users_activities
(
    id          int primary key         not null auto_increment,
    user_id     int                     not null,
    activity_id int                     not null,
    state       enum (
        'ASSIGNED',
        'ACCEPTED',
        'REJECTED',
        'COMPLETED',
        'UNSIGNED',
        'REQUESTED') default 'UNSIGNED' not null,
    constraint fk_users_activities_users
        foreign key (user_id) references users (id)
            on delete restrict,
    constraint fk_users_activities_activities
        foreign key (activity_id) references activities (id)
            on delete restrict
);

create table if not exists time_log
(
    id               int primary key        not null auto_increment,
    duration         double                 not null,
    user_activity_id int                    not null,
    start_date       datetime default now() not null,
    constraint time_log_users_activities_fk
        foreign key (user_activity_id) references users_activities (id)
);

insert into categories (name)
values ('Projects'),
       ('Vacations'),
       ('Other');
	   
insert into activities (category_id, name)
values (1, 'Development'),
       (1, 'Refactoring'),
       (1, 'Testing'),
       (2, 'Vacation'),
       (2, 'Illness'),
       (2, 'Business trip'),
       (3, 'Other');
	   
insert into users (first_name, last_name, email, password, role)
values ('admin', 'admin', 'admin@mail.com', '$2a$12$derrv5ZsI/D4OH6wPPe5QejMab0zZMldG9.P/FZhl/mmphBIDH.tO', 'ADMIN'),
       ('user1', 'user1', 'user1@mail.com', '$2a$12$7oBIQ6X27bQ10AvY5T./9uBelzd0j1Nie6USYBXLXKn1ElpqKZ2cm', 'USER'),
       ('user2', 'user2', 'user2@mail.com', '$2a$12$7dBd/i.SAQv/6jK7QIgduugMZmUCkmabc/QLImNk/Npo4ivOrKOFi', 'USER');
	   
insert into users_activities (user_id, activity_id, state)
values (2, 1, 'COMPLETED'),
       (2, 6, 'COMPLETED'),
       (2, 1, 'ACCEPTED'),
       (2, 2, 'ACCEPTED'),
       (3, 3, 'COMPLETED'),
       (3, 7, 'COMPLETED'),
       (3, 3, 'ACCEPTED'),
       (3, 7, 'REQUESTED');

insert into time_log (duration, user_activity_id, start_date)
values (5, 1, '2021-02-27 13:07:03'),
       (10, 2, '2021-02-27 13:07:10'),
       (12, 2, '2021-02-27 13:07:13'),
       (8, 2, '2021-02-27 13:07:20'),
       (12, 2, '2021-02-27 13:07:30'),
       (4, 2, '2021-02-27 13:07:34'),
       (0.5, 2, '2021-02-27 13:07:38'),
       (0.5, 2, '2021-02-27 13:07:40'),
       (9, 1, '2021-02-27 13:08:08'),
       (3, 3, '2021-02-27 13:08:33'),
       (0.5, 4, '2021-02-27 13:08:38'),
       (3, 5, '2021-02-27 13:10:18'),
       (0.5, 5, '2021-02-27 13:10:19'),
       (0.5, 6, '2021-02-27 13:10:19'),
       (1, 7, '2021-02-27 13:10:50'),
       (3.5, 8, '2021-02-27 13:11:42');