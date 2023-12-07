show databases;
drop database if exists base1;
create database if not exists base1;
use base1;

create table if not exists Address (
    address_id int auto_increment not null,
    city varchar(50) not null,
    state varchar(50) not null,
    pin_code int not null,
    primary key (address_id)
);

drop table if exists Student;

create table if not exists Student (
    id int unique auto_increment not null,
    name varchar(50) not null,
    age int not null,
    email varchar(20) not null,
    phone varchar(13) not null,
    address_id int not null,
    course_id int,
    primary key (id),
    foreign key (address_id) references Address(address_id),
    foreign key (course_id) references course(course_id)
);

create table if not exists course (
    course_id int auto_increment not null,
    course_name varchar(50) not null,
    course_code varchar(50) not null,
    primary key (course_id)
);

drop table if exists Enrollment;

create table if not exists Enrollment (
    enrollment_id int auto_increment not null,
    student_id int,
    course_id int,
    enrollment_date DATE,
    primary key (enrollment_id),
    foreign key (student_id) references Student(id),
    foreign key (course_id) references course(course_id)
);

show tables;
describe address;
describe course;
describe student;
describe enrollment;
select * from student;
select * from address;
select * from course;

select * from student s inner join course c on s.course_id = c.course_id where s.id = 1;

SELECT * FROM Enrollment WHERE student_id=1;

insert into course (course_name, course_code)
values ('B.Tech', 'BTEC1709'),
       ('Mechanical', 'BTME2109');

insert into student (name, age, email, phone, address_id, course_id)
values ('Nikhil', 22, 'nikhil@gmail.com', '9719768185', 1, 1),
       ('Sanjana', 23, 'sanjana@gmail.com', '9719776883', 1, 2),
       ('Shakti', 22, 'shakti@gmail.com', '123456789', 2, 1),
       ('Suraj', 23, 'suraj@gmail.com', 987654321, 1, 2);

insert into student (name, age, email, phone, address_id, course_id)
values ('Nikhil', 22, 'nikhil@gmail.com', '9719768185', 1, 2);

insert into address (address_id, city, state, pin_code)
values (1, 'Muzaffarnagar', 'UP', 251306),
       (2, 'Meerut', 'UP', 12000);

insert into enrollment (student_id, course_id, enrollment_date)
values (1, 1, '2023-12-06'),
       (2, 1, '2023-12-06'),
       (3, 2, '2023-12-06');

insert into enrollment (student_id, course_id, enrollment_date)
values (1, 2, '2023-12-06'),
       (1, 1, '2023-12-06');

select * from Enrollment as e
inner join student as s
on e.student_id = s.id;

SELECT S.id, S.name, S.age, S.email, S.phone, C.course_name, C.course_code, A.city, A.state, A.pin_code from Enrollment as E INNER JOIN Student As S ON E.student_id = S.id INNER JOIN Course As C ON E.course_id = C.course_id INNER JOIN Address As A ON S.address_id = A.address_id WHERE E.enrollment_id = 1;




SELECT S.name, S.age, S.email, S.phone, A.city, A.state, A.pin_code, C.course_name, C.course_code FROM Student AS S INNER JOIN Address AS A ON S.address_id = A.address_id INNER JOIN course AS C ON S.course_id = C.course_id WHERE S.id=1;
