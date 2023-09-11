create sequence if not exists public.id_sequence;

create table if not exists public.tbl_users
(
    id       int8         not null,
    username varchar(255) not null,
    password varchar(255) not null,
    aktif    boolean      not null,
    version  int4,
    primary key (id)
);

create unique index if not exists tbl_users_username_uindex
    on tbl_users (username);


create table if not exists public.tbl_upload_file
(
    id        int8         not null,
    aktif     boolean      not null,
    version   int4,
    file_name varchar(255) not null,
    data      bytea,
    user_id   int8         not null,
    primary key (id)
);

alter table if exists public.tbl_upload_file
    add constraint tbl_users_fk foreign key (user_id) references public.tbl_users;