CREATE SEQUENCE groep_10.notificatie_id_seq;
CREATE TABLE groep_10.notification (
                                       notificationid integer NOT NULL DEFAULT nextval('groep_10.notification_id_seq'::regclass),
                                       userid INTEGER NOT NULL,
                                       datetime DATE,
                                       message TEXT NOT NULL
);