package com.epam.tr.security;


import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;

public class JWTSecretHolder {
    public static final SecretKey JWT_SECRET = MacProvider.generateKey();
}
