CREATE TABLE goods (
                       ID serial PRIMARY KEY,
                       NAME VARCHAR(50) NOT NULL,
                       PRIORITY FLOAT
);

CREATE TABLE sales (
                       ID serial PRIMARY KEY ,
                       GOOD_ID INT,
                       GOOD_COUNT INT,
                       CREATE_DATE DATE NOT NULL,
                       FOREIGN KEY (GOOD_ID) REFERENCES goods (ID)
);

CREATE TABLE warehouse1 (
                            ID serial PRIMARY KEY,
                            GOOD_ID INT,
                            GOOD_COUNT INT,
                            FOREIGN KEY (GOOD_ID) REFERENCES goods (ID)
);

CREATE TABLE warehouse2 (
                            ID serial PRIMARY KEY ,
                            GOOD_ID INT,
                            GOOD_COUNT INT,
                            FOREIGN KEY (GOOD_ID) REFERENCES goods (ID)
);

CREATE TABLE user_role (
                           ID serial PRIMARY KEY,
                           ROLE_NAME VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE "user" (
                      ID serial PRIMARY KEY ,
                      USERNAME VARCHAR(255) NOT NULL UNIQUE,
                      PASSWORD VARCHAR(255) NOT NULL,
                      USER_ROLE_ID INT,
                      FOREIGN KEY (USER_ROLE_ID) REFERENCES user_role (id)
);