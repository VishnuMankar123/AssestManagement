CREATE TABLE clients
(
    id            INT AUTO_INCREMENT
        PRIMARY KEY,
    client_name   VARCHAR(100) NOT NULL,
    client_id     VARCHAR(100) NOT NULL,
    client_secret VARCHAR(255) NOT NULL,
    CONSTRAINT unique_client_id
        UNIQUE (client_id),
    CONSTRAINT unique_clients_name
        UNIQUE (client_name)
);

CREATE TABLE authentication_methods
(
    id          INT AUTO_INCREMENT
        PRIMARY KEY,
    method_name VARCHAR(100) NOT NULL
);

CREATE TABLE grant_types
(
    id              INT AUTO_INCREMENT
        PRIMARY KEY,
    grant_type_name VARCHAR(150) NOT NULL
);

CREATE TABLE redirect_uris
(
    id  INT AUTO_INCREMENT
        PRIMARY KEY,
    url VARCHAR(500) NOT NULL
);

CREATE TABLE scopes
(
    id         INT AUTO_INCREMENT
        PRIMARY KEY,
    scope_name VARCHAR(100) NOT NULL
);

CREATE TABLE token_settings
(
    id                    INT AUTO_INCREMENT
        PRIMARY KEY,
    time_to_live_in_hours INT         NOT NULL,
    format                VARCHAR(50) NOT NULL,
    client_id             INT         NOT NULL,
    CONSTRAINT token_settings_clients_id_fk
        FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE client_authentication_method_mapping
(
    id                       INT AUTO_INCREMENT
        PRIMARY KEY,
    client_id                INT NOT NULL,
    authentication_method_id INT NOT NULL,
    CONSTRAINT pairwise_unique_client_id_auth_method_id
        UNIQUE (client_id, authentication_method_id),
    CONSTRAINT authentication_methods_id_fk
        FOREIGN KEY (authentication_method_id) REFERENCES authentication_methods (id),
    CONSTRAINT clients_id_auth_method_fk
        FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE client_grant_type_mapping
(
    id            INT AUTO_INCREMENT
        PRIMARY KEY,
    client_id     INT NOT NULL,
    grant_type_id INT NOT NULL,
    CONSTRAINT pairwise_unique_client_id_grant_type
        UNIQUE (client_id, grant_type_id),
    CONSTRAINT clients_id_grant_type_fk
        FOREIGN KEY (client_id) REFERENCES clients (id),
    CONSTRAINT grant_types_id_fk
        FOREIGN KEY (grant_type_id) REFERENCES grant_types (id)
);

CREATE TABLE client_redirect_uri_map
(
    id              INT AUTO_INCREMENT
        PRIMARY KEY,
    client_id       INT NOT NULL,
    redirect_uri_id INT NOT NULL,
    CONSTRAINT pairwise_unique_client_id_redirect_uri_id
        UNIQUE (client_id, redirect_uri_id),
    CONSTRAINT client_redirect_uri_map_redirect_uris_id_fk
        FOREIGN KEY (redirect_uri_id) REFERENCES redirect_uris (id),
    CONSTRAINT clients_id_redirect_uri_fk
        FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE client_scope_map
(
    id        INT AUTO_INCREMENT
        PRIMARY KEY,
    client_id INT NOT NULL,
    scope_id  INT NOT NULL,
    CONSTRAINT pairwise_unique_client_id_scope_id
        UNIQUE (client_id, scope_id),
    CONSTRAINT client_scope_map_scopes_id_fk
        FOREIGN KEY (scope_id) REFERENCES scopes (id),
    CONSTRAINT clients_id_scope_fk
        FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE users
(
    id       INT AUTO_INCREMENT
        PRIMARY KEY,
    username VARCHAR(200) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT unique_username
        UNIQUE (username)
);

CREATE TABLE roles
(
    id        INT AUTO_INCREMENT
        PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_role_name
        UNIQUE (role_name)
);

CREATE TABLE user_role_map
(
    id      INT AUTO_INCREMENT
        PRIMARY KEY,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT pairwise_unique_user_id_role_id
        UNIQUE (user_id, role_id),
    CONSTRAINT user_role_map_roles_id_fk
        FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT user_role_map_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
);
