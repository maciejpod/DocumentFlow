INSERT INTO t_user VALUES(2, 'Krzysztof', 'awopeq@gmail.com', 'Iksinski', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick2');
INSERT INTO t_user VALUES(3, 'Ala', 'rampampam@o2.pl', 'Igrekowa', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick3');
INSERT INTO t_user VALUES(4, 'Katarzyna', 'iksde@g2a.com', 'Kasztanowa', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick4');
INSERT INTO t_user VALUES(5, 'Barbara', 'deiksop@onet.pl', 'Nienullowa', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick5');
INSERT INTO t_user VALUES(6, 'Franciszek', 'taratrata@o2.pl', 'Integerowy', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick6');
INSERT INTO t_user VALUES(7, 'Zbigniew', 'okcamopo@gmail.com', 'Flara', '$2a$10$oTHPx3Dt6PLGmJ5n.5xV5.OetVtLni3fVj0sAAG52cxrXm4yzfoFu', 'nick7');

INSERT INTO t_status VALUES(0, 'CANCELLED');
INSERT INTO t_status VALUES(1, 'ACCEPTED');
INSERT INTO t_status VALUES(2, 'DENIED');
INSERT INTO t_status VALUES(3, 'MODIFY');
INSERT INTO t_status VALUES(4, 'PROCESSING');

INSERT INTO t_role VALUES(1, 'Student', 1);
INSERT INTO t_role VALUES(2, 'Employee', 2);
INSERT INTO t_role VALUES(3, 'Supervisor', 3);
INSERT INTO t_role VALUES(4, 'Manager', 4);
INSERT INTO t_role VALUES(5, 'Dean', 5);
INSERT INTO t_role VALUES(6, 'Rector', 6);

INSERT INTO t_transaction VALUES(1, 'Node 1', 1);
INSERT INTO t_transaction VALUES(2, 'Node 2', 2);
INSERT INTO t_transaction VALUES(3, 'Node 3', 3);

INSERT INTO public."T_DOCTYPE" VALUES(1, 'Doctype 1', 1);

INSERT INTO t_connection VALUES(1, 2, 1);
INSERT INTO t_connection VALUES(2, 3, 1);
INSERT INTO t_connection VALUES(3, 1, 1);

INSERT INTO t_department VALUES(2, null, 'Department 1');
INSERT INTO t_department VALUES(3, null, 'Department 2');
--user role department nick2 - id 2 department 1 - id 2 role 2id - employee
INSERT INTO t_userrole VALUES(2, 2, 2);
INSERT INTO t_userrole VALUES(3, 1, 2);
INSERT INTO t_userrole VALUES(4, 3, 2);
INSERT INTO t_userrole VALUES(5, 4, 2);
INSERT INTO t_userrole VALUES(6, 5, 2);
INSERT INTO t_userrole VALUES(2, 5, 3);
INSERT INTO t_userrole VALUES(6, 4, 3);
INSERT INTO t_userrole VALUES(3, 3, 3);
INSERT INTO t_userrole VALUES(7, 2, 3);