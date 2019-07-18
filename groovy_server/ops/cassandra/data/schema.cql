CREATE KEYSPACE autoshowroom
  WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1
  };

USE autoshowroom;

CREATE TYPE car (
    model varchar,
    make varchar,
    options frozen <list<varchar>>
);

CREATE TABLE orders (id timeuuid, car car, client varchar, status varchar, PRIMARY KEY (id));
CREATE TABLE clients (id bigint, name varchar, PRIMARY KEY (id));
CREATE TABLE makes (id bigint, name varchar, PRIMARY KEY (id));
CREATE TABLE models (id bigint, name varchar, price bigint, PRIMARY KEY (id));
CREATE TABLE options (id bigint, name varchar, price bigint, PRIMARY KEY (id));