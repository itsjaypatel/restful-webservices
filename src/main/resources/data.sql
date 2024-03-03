insert into user_detail(id,name,birth_date)
values (1001,'Jay',current_date()),
(1002,'Raju','2001-02-01'),
(1003,'Dev',current_date()),
(1004,'Kishan',current_date()),
(1005,'Prem',current_date());

insert into post(id,description,user_id) values
(1001,'Learn guitar',1001),
(1002,'Learn Spring Boot',1001),
(1003,'Learn Painting',1002),
(1004,'Learn Piano',1003),
(1005,'Learn AWS',1004)