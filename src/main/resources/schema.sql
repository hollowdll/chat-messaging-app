/* Create test database schema */
/* Make sure to create database first in DBMS */
/* Delete tables on program startup */
/* Deletion order matters to prevent errors */

DROP TABLE IF EXISTS public.message_room_members;
DROP TABLE IF EXISTS public.messages;
DROP TABLE IF EXISTS public.message_rooms;
DROP TABLE IF EXISTS public.users;

/* users */

CREATE TABLE IF NOT EXISTS public.users
(
    user_id serial NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    created timestamp with time zone NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT username_unique UNIQUE (username)
);

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

/* message_rooms */

CREATE TABLE IF NOT EXISTS public.message_rooms
(
    message_room_id serial NOT NULL,
    name text NOT NULL,
    user_id integer NOT NULL,
    password text NOT NULL,
    created timestamp with time zone NOT NULL,
    PRIMARY KEY (message_room_id)
);

ALTER TABLE IF EXISTS public.message_rooms
    OWNER to postgres;

ALTER TABLE IF EXISTS public.message_rooms
    ADD CONSTRAINT fk_message_rooms_users FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
    NOT VALID;

/* messages */

CREATE TABLE IF NOT EXISTS public.messages
(
    message_id serial NOT NULL,
    text text NOT NULL,
    user_id integer NOT NULL,
    message_room_id integer NOT NULL,
    created timestamp with time zone NOT NULL,
    PRIMARY KEY (message_id)
);

ALTER TABLE IF EXISTS public.messages
    OWNER to postgres;

ALTER TABLE IF EXISTS public.messages
    ADD CONSTRAINT fk_messages_users FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.messages
    ADD CONSTRAINT fk_messages_message_rooms FOREIGN KEY (message_room_id)
    REFERENCES public.message_rooms (message_room_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
    NOT VALID;

/* message_room_members */

CREATE TABLE IF NOT EXISTS public.message_room_members
(
    message_room_id integer NOT NULL,
    user_id integer NOT NULL,
    joined timestamp with time zone NOT NULL,
    PRIMARY KEY (message_room_id, user_id)
);

ALTER TABLE IF EXISTS public.message_room_members
    OWNER to postgres;

ALTER TABLE IF EXISTS public.message_room_members
    ADD CONSTRAINT fk_message_room_members_message_rooms FOREIGN KEY (message_room_id)
    REFERENCES public.message_rooms (message_room_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.message_room_members
    ADD CONSTRAINT fk_message_room_members_users FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
    NOT VALID;
