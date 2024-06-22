CREATE SEQUENCE groep_10.material_id_seq;
CREATE TABLE groep_10.material(id integer NOT NULL DEFAULT nextval('groep_10.material_id_seq'::regclass),
                               providerId int NOT NULL,
                               distributorId int NOT NULL,
                               type varchar NOT NULL,
                               lastPickupDate date NOT NULL,
                               location varchar NOT NULL,
                               status varchar NOT NULL,
                               imageid int NOT NULL,
                               rejectmessage varchar,
                               CONSTRAINT image_id_material_id FOREIGN KEY (imageid) REFERENCES groep_10.image(id),
                               CONSTRAINT material_pkey PRIMARY KEY (id));