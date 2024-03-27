create table task_groups(
    id int primary key auto_increment,
    description varchar(100) not null,
    done boolean
);
ALTER TABLE tasks ADD COLUMN task_group_id int null;
ALTER TABLE tasks ADD FOREIGN KEY (task_group_id) references task_groups(id);