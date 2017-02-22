CREATE TABLE t_user(
 user_id int auto_increment NOT NULL,
 name Varchar
        CHECK (name ~'^[a-zA-Z]*$'),
 email Varchar NOT NULL
        CHECK (email ~'^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
 lastname Varchar
        CHECK (lastname ~'^[a-zA-Z]*$'),
 password Varchar(100) NOT NULL,
 username Varchar NOT NULL
        CHECK (username ~ '^[a-zA-Z0-9_.-]{4,}$')
)
;

-- Add keys for table T_USER

ALTER TABLE t_user ADD CONSTRAINT pk_t_user PRIMARY KEY (user_id)
;

ALTER TABLE t_user ADD CONSTRAINT email UNIQUE (email)
;

ALTER TABLE t_user ADD CONSTRAINT username UNIQUE (username)
;

-- Table T_STATUS

CREATE TABLE t_status(
 status_id int auto_increment NOT NULL,
 name Varchar(20) NOT NULL
)
;

-- Add keys for table T_STATUS

ALTER TABLE t_status ADD CONSTRAINT pk_t_status PRIMARY KEY (status_id)
;

-- Table T_ROLE

CREATE TABLE t_role(
 role_id int auto_increment NOT NULL,
 name Varchar(20) NOT NULL,
 priority Integer
)
;

-- Add keys for table T_ROLE

ALTER TABLE t_role ADD CONSTRAINT pk_t_role PRIMARY KEY (role_id)
;

-- Table T_DEPARTMENT

CREATE TABLE t_department(
 department_id int auto_increment NOT NULL,
 fk_department_id Integer,
 name Varchar(20) NOT NULL
)
;

-- Create indexes for table T_DEPARTMENT

CREATE INDEX department_parent_idx ON t_department (fk_department_id)
;

-- Add keys for table T_DEPARTMENT

ALTER TABLE t_department ADD CONSTRAINT pk_t_department PRIMARY KEY (department_id)
;

-- Table T_USERROLE

CREATE TABLE t_userrole(
 user_id Integer NOT NULL,
 role_id Integer NOT NULL,
 department_id Integer NOT NULL
)
;

-- Add keys for table T_USERROLE

ALTER TABLE t_userrole ADD CONSTRAINT pk_t_userrole PRIMARY KEY (user_id,role_id,department_id)
;

-- Table T_TRANSACTION

CREATE TABLE t_transaction(
 transaction_id int auto_increment NOT NULL,
 name Varchar(20) NOT NULL,
 role_id Integer
)
;

-- Create indexes for table T_TRANSACTION

CREATE INDEX role_index ON t_transaction (role_id)
;

-- Add keys for table T_TRANSACTION

ALTER TABLE t_transaction ADD CONSTRAINT pk_t_transaction PRIMARY KEY (transaction_id)
;

-- Table T_DOCTYPE

CREATE TABLE t_doctype(
 doctype_id int auto_increment NOT NULL,
 name Varchar(20) NOT NULL,
 transaction_id Integer
)
;

-- Create indexes for table T_DOCTYPE

CREATE INDEX begin_transaction_idx ON t_doctype (transaction_id)
;

-- Add keys for table T_DOCTYPE

ALTER TABLE t_doctype ADD CONSTRAINT pk_t_doctype PRIMARY KEY (doctype_id)
;

-- Table T_CONNECTION

CREATE TABLE t_connection(
 transaction_id Integer NOT NULL,
 ref_transaction_id Integer NOT NULL,
 doctype_id Integer NOT NULL
)
;

-- Add keys for table T_CONNECTION

ALTER TABLE t_connection ADD CONSTRAINT pk_t_connection PRIMARY KEY (transaction_id,ref_transaction_id,doctype_id)
;

-- Table T_REQUEST

CREATE TABLE t_request(
 request_id int auto_increment NOT NULL,
 user_id Integer,
 doctype_id Integer,
 status_id Integer,
 department_id Integer,
 modified_flag Boolean DEFAULT FALSE NOT NULL,
 feedback Varchar(200),
 content Varchar(1000) NOT NULL
)
;

-- Create indexes for table T_REQUEST

CREATE INDEX user_idx ON t_request (user_id)
;

CREATE INDEX status_idx ON t_request (status_id)
;

CREATE INDEX doctype_idx ON t_request (doctype_id)
;

CREATE INDEX department_idx ON t_request (department_id)
;

-- Add keys for table T_REQUEST

ALTER TABLE t_request ADD CONSTRAINT pk_t_request PRIMARY KEY (request_id)
;

-- Table T_CURRENT_STATE

CREATE TABLE t_current_state(
 currentstate_id int auto_increment NOT NULL,
 request_id Integer NOT NULL,
 transaction_id Integer NOT NULL,
 status_id Integer,
 department_id Integer
)
;

-- Create indexes for table T_CURRENT_STATE

CREATE INDEX status1_idx ON t_current_state (status_id)
;

CREATE INDEX department1_idx ON t_current_state (department_id)
;

-- Add keys for table T_CURRENT_STATE

ALTER TABLE t_current_state ADD CONSTRAINT pkt_T_current_state PRIMARY KEY (currentstate_id,request_id,transaction_id)
;

-- Table T_HISTORY

CREATE TABLE t_history(
 history_id int auto_increment NOT NULL,
 request_id Integer NOT NULL,
 department_name Varchar(20) NOT NULL,
 transaction_name Varchar(20) NOT NULL,
 status_name Varchar(20) NOT NULL,
 user Varchar(50) NOT NULL,
 role_name Varchar(20) NOT NULL
)
;

-- Create indexes for table T_HISTORY

CREATE INDEX request_idx ON t_history (request_id)
;

-- Add keys for table T_HISTORY

ALTER TABLE t_history ADD CONSTRAINT key4 PRIMARY KEY (history_id)
;


-- Create relationships section -------------------------------------------------

ALTER TABLE t_department ADD CONSTRAINT subordinate_to FOREIGN KEY (fk_department_id) REFERENCES t_department (department_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_userrole ADD CONSTRAINT work_for FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_userrole ADD CONSTRAINT has FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_userrole ADD CONSTRAINT hire FOREIGN KEY (department_id) REFERENCES t_department (department_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_transaction ADD CONSTRAINT administrate FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_doctype ADD CONSTRAINT begin FOREIGN KEY (transaction_id) REFERENCES t_transaction (transaction_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_connection ADD CONSTRAINT belong_to FOREIGN KEY (transaction_id) REFERENCES t_transaction (transaction_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_connection ADD CONSTRAINT fallowed_by FOREIGN KEY (ref_transaction_id) REFERENCES t_transaction (transaction_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_connection ADD CONSTRAINT describe FOREIGN KEY (doctype_id) REFERENCES t_doctype (doctype_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_request ADD CONSTRAINT report FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_request ADD CONSTRAINT r FOREIGN KEY (status_id) REFERENCES t_status (status_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_request ADD CONSTRAINT assigned_to FOREIGN KEY (doctype_id) REFERENCES t_doctype (doctype_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_request ADD CONSTRAINT recive FOREIGN KEY (department_id) REFERENCES t_department (department_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_current_state ADD CONSTRAINT has_state FOREIGN KEY (request_id) REFERENCES t_request (request_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_current_state ADD CONSTRAINT perforned FOREIGN KEY (transaction_id) REFERENCES t_transaction (transaction_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_current_state ADD CONSTRAINT r1 FOREIGN KEY (status_id) REFERENCES t_status (status_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_current_state ADD CONSTRAINT "process" FOREIGN KEY (department_id) REFERENCES t_department (department_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE t_history ADD CONSTRAINT "is_stored" FOREIGN KEY (request_id) REFERENCES t_request (request_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;