#!/bin/bash

hive -e "

use ali;
create table if not exists expo_detail_table
(
pvid string comment'唯一标识一次请求',
user_id string comment'用户id',
item_id string comment'商品id'
);

create table if not exists click_detail_table
(
pvid string comment'唯一标识一次请求',
user_id string comment'用户id',
item_id string comment'商品id'
);

insert into table expo_detail_table values('p001','user001','item001');
insert into table expo_detail_table values('p002','user001','item001');
insert into table expo_detail_table values('p003','user001','item002');
insert into table expo_detail_table values('p004','user002','item002');
insert into table expo_detail_table values('p005','user002','item003');
insert into table expo_detail_table values('p006','user003','item004');

insert into table click_detail_table values('c001','user001','item001');
insert into table click_detail_table values('c002','user001','item001');
insert into table click_detail_table values('c003','user002','item002');
————————————————
";
