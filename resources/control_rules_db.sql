-- DROP DATABASE control_rules_db;

CREATE DATABASE control_rules_db
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Polish_Poland.1250'
       LC_CTYPE = 'Polish_Poland.1250'
       CONNECTION LIMIT = -1;

CREATE TABLE control_rules (
 rule_id BIGSERIAL PRIMARY KEY,
 rule_name VARCHAR(255),
 type VARCHAR(255)
);

CREATE TABLE control_conditions (
 condition_id BIGSERIAL PRIMARY KEY,
 fk_control_rule INT REFERENCES control_rules(rule_id),
 condition_name VARCHAR(255),
 negation BOOLEAN,
 action BOOLEAN,
 reference_condition_action_id INT
);

CREATE TABLE control_arguments (
 arg_id BIGSERIAL PRIMARY KEY,
 fk_condition INT REFERENCES control_conditions(condition_id),
 variable VARCHAR(255),
 constant VARCHAR(255)
);