DROP TABLE if exists groep_10.awaitinguser;
DROP SEQUENCE if exists groep_10.awaitinguser_id_seq;

CREATE SEQUENCE groep_10.awaitinguser_id_seq;
CREATE TABLE groep_10.awaitinguser(id integer NOT NULL DEFAULT nextval('groep_10.awaitinguser_id_seq'::regclass),
                           email character varying COLLATE pg_catalog."default" NOT NULL,
                           password character varying COLLATE pg_catalog."default" NOT NULL,
                           role character varying COLLATE pg_catalog."default" NOT NULL,
                           name character varying COLLATE pg_catalog."default" NOT NULL,
                           CONSTRAINT awaitinguser_pkey PRIMARY KEY (id, email));

INSERT INTO groep_10.awaitinguser ("email","password", "role","name") values ('aanbieder@mbl.be','t', 'AANBIEDER', 'AANBIEDER');
INSERT INTO groep_10.awaitinguser ("email","password", "role","name") values ('aanbieder2@mbl.be','t', 'AANBIEDER', 'AANBIEDER');