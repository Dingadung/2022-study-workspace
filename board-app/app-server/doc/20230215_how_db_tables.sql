drop table how_file;

drop table how_product;

drop table how_category;

drop table how_member;


create table how_category
(
    cat_no   int auto_increment
        primary key,
    cat_name varchar(50) null
);

create table how_member
(
    ID     varchar(100)                         not null comment '아이디'
        primary key,
    PWD    varchar(100)                         not null comment '비밀번호',
    NNAME  varchar(50)                          not null comment '닉네임',
    SIDATE datetime default current_timestamp() not null comment '생성일',
    NAME   varchar(40)                          not null comment '이름',
    EMAIL  varchar(40)                          not null comment '이메일',
    PNUM   varchar(30)                          not null comment '휴대폰 번호',
    BIRTH  date                                 null comment '생년월일',
    GENDER char                                 not null comment '성별'
);

create table how_product
(
    prod_no   int auto_increment
        primary key,
    prod_name varchar(50)  not null,
    price     int          not null,
    cont      varchar(255) not null,
    id        varchar(50)  not null,
    cat_no    int          not null,
    constraint key_name
        unique (id),
    constraint key_name2
        unique (cat_no),
    constraint foreign_key_name
        foreign key (id) references how_member (ID),
    constraint foreign_key_name2
        foreign key (cat_no) references how_category (cat_no)
);

create table how_file
(
    fno     int auto_increment
        primary key,
    path    varchar(255) not null,
    prod_no int          not null,
    constraint key_name
        unique (prod_no),
    constraint foreign_key_name3
        foreign key (prod_no) references how_product (prod_no)
);

