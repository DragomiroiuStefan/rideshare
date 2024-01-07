drop table if exists ride_rating;
drop table if exists booking_connection;
drop table if exists ride_connection;
drop table if exists location;
drop table if exists ride;
drop type if exists ride_status;
drop table if exists vehicle;
drop table if exists booking;
drop table if exists "user";


create table "user"
(
    user_id         bigint generated always as identity primary key,
    email           varchar(255) unique not null,
    password        varchar(50)         not null,
    first_name      varchar(255)        not null,
    last_name       varchar(255)        not null,
    phone_number    varchar(15),
    birth_date      date                not null,
    created_on      timestamp default now(),
    last_login      timestamp,
    profile_picture varchar(64)
);

create table vehicle
(
    plate_number      varchar(20) primary key,
    brand             varchar(50) not null,
    model             varchar(50) not null,
    color             varchar(50) not null,
    registration_year integer     not null,
    seats             int         not null,
    -- picture
    owner             bigint references "user" (user_id)
);

create table location
(
    location_id bigint generated always as identity primary key,
    city        varchar(255) not null,
    county      varchar(255) not null
);

create type ride_status as enum ('ACTIVE', 'FINISHED', 'CANCELED');

create table ride
(
    ride_id            bigint generated always as identity primary key,
    driver             bigint references "user" (user_id)            not null,
    departure_date     date                                          not null,
    seats              int                                           not null,
    additional_comment varchar(255),
    vehicle            varchar(20) references vehicle (plate_number) not null,
    status             ride_status,
    posted_at          timestamp default now()
);

create table ride_connection
(
    connection_id      bigint generated always as identity primary key,
    departure_location bigint references location (location_id),
    arrival_location   bigint references location (location_id),
    departure_time     timestamp not null,
    arrival_time       timestamp not null,
    departure_address  varchar(255),
    arrival_address    varchar(255),
    price              int       not null,
    ride_id            bigint references ride (ride_id)
    -- ride_id , departure UNIQUE
    -- ride_id , arrival UNIQUE
);

create table ride_rating
(
    ride_id   bigint references ride (ride_id),
    user_id   bigint references "user" (user_id),
    rating    int not null,
    comment   varchar(255),
    posted_at timestamp default now(),
    primary key (ride_id, user_id)
);

create table booking
(
    booking_id bigint generated always as identity primary key,
    user_id    bigint references "user" (user_id),
    adults     int not null,
    children   int not null,
    confirmed  boolean,
    booked_at  timestamp default now()
);

create table booking_connection
(
    booking_id    bigint references booking (booking_id),
    connection_id bigint references ride_connection (connection_id),
    primary key (booking_id, connection_id)
);
