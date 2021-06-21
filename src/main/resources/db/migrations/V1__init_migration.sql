CREATE TABLE event (
    id bigserial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE person (
    id bigserial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE transaction (
    id bigserial PRIMARY KEY,
    name varchar(50),
    event_id bigint,
    owner_id bigint,
    CONSTRAINT event_fk
        FOREIGN KEY(event_id)
        REFERENCES event(id),
    CONSTRAINT owner_fk
        FOREIGN KEY(owner_id)
            REFERENCES person(id)
);

CREATE TABLE chat (
    id bigserial unique,
    client_name varchar(50) unique,
    PRIMARY KEY(id, client_name)
);

CREATE TABLE person_client (
    id bigserial PRIMARY KEY,
    client_name varchar(50),
    person_id bigint,
    CONSTRAINT person_fk
        FOREIGN KEY(person_id)
            REFERENCES person(id)
);

CREATE TABLE person_transaction_jt (
    id bigserial PRIMARY KEY,
    person_id bigint,
    transaction_id bigint,
    CONSTRAINT person_fk
        FOREIGN KEY(person_id)
            REFERENCES person(id),
    CONSTRAINT transaction_fk
        FOREIGN KEY(transaction_id)
            REFERENCES transaction(id)
);

CREATE TABLE chat_event_jt (
    id bigserial PRIMARY KEY,
    event_id bigint,
    chat_id bigint,
    chat_client_name varchar(50),
    CONSTRAINT event_fk
        FOREIGN KEY(event_id)
            REFERENCES event(id),
    CONSTRAINT chat_fk
        FOREIGN KEY(chat_id)
            REFERENCES chat(id),
    CONSTRAINT chat_client_name_fk
        FOREIGN KEY(chat_client_name)
            REFERENCES chat(client_name)
);