drop table Permissions_Role if exists;

drop table Permissions if exists;

drop table Role if exists;
 
CREATE TABLE Permissions ( id BIGINT NOT NULL, action VARCHAR (255), userName VARCHAR (255), PRIMARY KEY (id));

CREATE TABLE Permissions_Role ( Permissions_id BIGINT NOT NULL, roles_id BIGINT NOT NULL );

CREATE TABLE Role ( id BIGINT NOT NULL, NAME VARCHAR (255), PRIMARY KEY (id));

ALTER TABLE Permissions_Role ADD CONSTRAINT FK3A8B2F91185AD954 FOREIGN KEY (roles_id) REFERENCES Role;

ALTER TABLE Permissions_Role ADD CONSTRAINT FK3A8B2F9145947519 FOREIGN KEY (Permissions_id) REFERENCES Permissions;

INSERT INTO Role(id,NAME) values (1, 'Batch Process');

INSERT INTO Role(id,NAME) values ( 2, 'Service Credit Reviewer' );

INSERT INTO Role(id,NAME) values (3, 'Receivables Supervisor');

INSERT INTO Role(id,NAME) values ( 4, 'Service Credit Specialist' );

INSERT INTO Role(id,NAME) values (5, 'Receivables Technician');

INSERT INTO Role(id,NAME) values ( 6, 'Service Credit Data Entry Technician' );

INSERT INTO Role(id,NAME) values (7, 'Financial Technician');

INSERT INTO Role(id,NAME) values (8, 'Financial Supervisor');

INSERT INTO Role(id,NAME) values (9, 'Information Technician');

INSERT INTO Role(id,NAME) values (10, 'System Administrator');

INSERT INTO Role(id,NAME) values (11, 'FACES User');

INSERT INTO Role(id,NAME) values (12, 'RIO User');

INSERT INTO Role(id,NAME) values (13, 'View Only User');

INSERT INTO Permissions(id,action,userName) values (1, '/', 'aduser@TCTEST.COM');

INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 1);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 2);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 3);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 4);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 5);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 6);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 7);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 8);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 9);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 10);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 11);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 12);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (1, 13);

INSERT INTO Permissions(id,action,userName) values (2, '/index.jsp', 'aduser@TCTEST.COM');
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,1);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,2);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,3);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,4);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,5);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,6);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,7);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,8);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,9);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,10);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,11);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,12);
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (2,13);

INSERT INTO Permissions(id,action,userName) values (3, 'tab1', 'aduser@TCTEST.COM');
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (3,1);

INSERT INTO Permissions(id,action,userName) values (4, 'tab2', 'aduser@TCTEST.COM');
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (4,2);

INSERT INTO Permissions(id,action,userName) values (5, 'tab3', 'aduser@TCTEST.COM');
INSERT INTO Permissions_Role(Permissions_id,roles_id) values (5,3);

