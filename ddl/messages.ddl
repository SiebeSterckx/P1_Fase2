CREATE SEQUENCE groep_10.message_id_seq;
create table groep_10.messages
(
    messageid  integer default nextval('groep_10.message_id_seq'::regclass) not null,
    materialid integer,
    fromid     integer,
    toid       integer,
    message    varchar,
    datetime   date
);