create dataBase project;

use project;

create table user (
    name varchar(100) primary key,
    password varchar(100) not null,
    gender varchar(10),
    age int,
    email varchar(50) not null,
    school varchar(50)
);

create table problem (
    q_id int primary key auto_increment,
    title varchar(50),
    level varchar(50),
    description varchar(4096),
    templateCode varchar(4096),
    testCode varchar(4096),
    organization varchar(100),
    owner varchar(200)
);

create table record (
    u_name varchar(50) not null,
    q_id int not null,
    result varchar(20) not null,
    time varchar(100) not null
);


-- select * from problem where title like "%加%";