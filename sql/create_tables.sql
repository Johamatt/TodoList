CREATE TABLE Task(
id int auto_increment not null,
description varchar(300) not null,
checked int not null,
date date not null,
primary key(id)
)engine=InnoDB;