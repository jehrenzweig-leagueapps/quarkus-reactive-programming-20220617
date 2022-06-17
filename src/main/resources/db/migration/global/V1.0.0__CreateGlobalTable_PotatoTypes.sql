create table if not exists PotatoTypes
(
    ID                   char(36)    not null comment 'Unique 36-character GUID of the potato type.',
    NAME                 varchar(50) not null comment 'Name of the potato type.',
    COUNTRY_OF_ORIGIN    char(3)     null comment 'ISO-3166 alpha-3 code describing the potato type''s country of origin.',
    YEAR_OF_INTRODUCTION smallint    null comment 'Calendar year in which the potato type was first introduced.',
    primary key PK_PotatoTypes (ID),
    constraint UQ_PotatoTypes_ID unique (ID),
    constraint UQ_PotatoTypes_NAME unique (NAME)
);

insert ignore into PotatoTypes (ID, NAME, COUNTRY_OF_ORIGIN, YEAR_OF_INTRODUCTION)
values
    ('1a0f40de-24e6-40da-9d33-c1d48b6317f7', 'King Edward', 'GBR', 1902),
    ('c40b9303-a8d0-482a-899b-6b4258bf93a6', 'Adirondack Blue', 'USA', 2003),
    ('7dec219e-b240-40b0-84b7-2d48ac6a9717', 'Adirondack Red', 'USA', 2003)
;
