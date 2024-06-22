DROP TABLE if exists groep_10.user;
DROP SEQUENCE if exists groep_10.user_id_seq;

CREATE SEQUENCE groep_10.user_id_seq;
CREATE TABLE groep_10.user(id integer NOT NULL DEFAULT nextval('groep_10.user_id_seq'::regclass),
                           email character varying COLLATE pg_catalog."default" NOT NULL,
                           password character varying COLLATE pg_catalog."default" NOT NULL,
                           role character varying COLLATE pg_catalog."default" NOT NULL,
                           name character varying COLLATE pg_catalog."default" NOT NULL,
                           CONSTRAINT user_pkey PRIMARY KEY (id, email));

INSERT INTO groep_10.user ("email","password", "role") values ('aanbieder@mbl.be','t', 'AANBIEDER');
INSERT INTO groep_10.user ("email","password", "role") values ('verdeler@mbl.be','t', 'VERDELER');
INSERT INTO groep_10.user ("email","password", "role") values ('moderator@mbl.be','t', 'MODERATOR');