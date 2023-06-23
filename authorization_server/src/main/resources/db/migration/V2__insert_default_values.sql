INSERT INTO `authentication_methods` VALUES (1,'client_secret_basic'),(2,'client_secret_post'),(3,'client_secret_jwt'),(4,'private_key_jwt'),(5,'none');
INSERT INTO `token_settings` VALUES (1,1,'self-contained');
INSERT INTO `clients` VALUES (1,'default','default','$2a$12$8cd9TlAZEk7lXhwj61oPwOzThYCyutz0/rN7PH6v5YjHaYgTu5R0O',1);
INSERT INTO `grant_types` VALUES (1,'authorization_code'),(2,'refresh_token'),(3,'client_credentials'),(4,'urn:ietf:params:oauth:grant-type:jwt-bearer'),(5,'urn:ietf:params:oauth:grant-type:device_code');
INSERT INTO `redirect_uris` VALUES (1,'https://example.com/auth');
INSERT INTO `scopes` VALUES (1,'openid'),(2,'profile'),(3,'email'),(4,'address'),(5,'phone');
INSERT INTO `client_authentication_method_mapping` VALUES (1,1,1);
INSERT INTO `client_grant_type_mapping` VALUES (1,1,1),(2,1,2);
INSERT INTO `client_redirect_uri_map` VALUES (1,1,1);
INSERT INTO `client_scope_map` VALUES (1,1,1);

INSERT INTO `users` VALUES (1,'admin','$2a$12$/exa0unUgiDbM3sXxeBAdusdKh8hj3T24UZ.fdSDJZFMnZh9teD8m');
INSERT INTO `roles` VALUES (1,'admin');
INSERT INTO `user_role_map` VALUES (1,1,1);