/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.shiro;

import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.HashFormatFactory;
import org.apache.shiro.crypto.hash.format.ParsableHashFormat;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * shiro密码服务自定义扩展
 *
 * @author gugia
 */
@Service
public class UserPasswordService implements HashingPasswordService {

    private final Logger logger = LoggerFactory.getLogger(UserPasswordService.class);

    public static final String ALGORITHM = "SHA-256";
    public static final int ITERATIONS = 10000;

    private HashService hashService;
    private HashFormat hashFormat;
    private HashFormatFactory hashFormatFactory;
    private volatile boolean hashFormatWarned; //used to avoid excessive log noise

    public UserPasswordService() {
        this.hashFormatWarned = false;
        DefaultHashService defaultHashService = new DefaultHashService();
        defaultHashService.setHashAlgorithmName(ALGORITHM);
        defaultHashService.setHashIterations(ITERATIONS);
        defaultHashService.setGeneratePublicSalt(true); //always want generated salts for user passwords to be most secure
        this.hashService = defaultHashService;
//        this.hashFormat = new SiotCryptFormat();
        this.hashFormat = new Shiro1CryptFormat();
        this.hashFormatFactory = new DefaultHashFormatFactory();
    }

    @Override
    public Hash hashPassword(Object plaintext) throws IllegalArgumentException {
        ByteSource plaintextBytes = createByteSource(plaintext);
        if (plaintextBytes == null || plaintextBytes.isEmpty()) {
            return null;
        }
        HashRequest request = createHashRequest(plaintextBytes);
        return hashService.computeHash(request);
    }

    @Override
    public boolean passwordsMatch(Object plaintext, Hash savedPassword) {
        ByteSource plaintextBytes = createByteSource(plaintext);
        if (savedPassword == null || savedPassword.isEmpty()) {
            return plaintextBytes == null || plaintextBytes.isEmpty();
        } else if (plaintextBytes == null || plaintextBytes.isEmpty()) {
            return false;
        }
        HashRequest request = buildHashRequest(plaintextBytes, savedPassword);
        Hash computed = this.hashService.computeHash(request);
        return savedPassword.equals(computed);
    }

    @Override
    public String encryptPassword(Object plaintext) throws IllegalArgumentException {
        Hash hash = hashPassword(plaintext);
        checkHashFormatDurability();
        return this.hashFormat.format(hash);
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        ByteSource plaintextBytes = createByteSource(submittedPlaintext);
        if (encrypted == null || encrypted.length() == 0) {
            return plaintextBytes == null || plaintextBytes.isEmpty();
        } else if (plaintextBytes == null || plaintextBytes.isEmpty()) {
            return false;
        }
        HashFormat discoveredFormat = this.hashFormatFactory.getInstance(encrypted);
        if (discoveredFormat != null && discoveredFormat instanceof ParsableHashFormat) {
            ParsableHashFormat parsableHashFormat = (ParsableHashFormat) discoveredFormat;
            Hash savedHash = parsableHashFormat.parse(encrypted);
            return passwordsMatch(submittedPlaintext, savedHash);
        }
        HashRequest request = createHashRequest(plaintextBytes);
        Hash computed = this.hashService.computeHash(request);
        String formatted = this.hashFormat.format(computed);
        return encrypted.equals(formatted);
    }

    protected void checkHashFormatDurability() {
        if (!this.hashFormatWarned) {
            HashFormat format = this.hashFormat;
            if (!(format instanceof ParsableHashFormat) && logger.isWarnEnabled()) {
                String msg = "The configured hashFormat instance [" + format.getClass().getName() + "] is not a "
                        + ParsableHashFormat.class.getName() + " implementation.  This is "
                        + "required if you wish to support backwards compatibility for saved password checking (almost "
                        + "always desirable).  Without a " + ParsableHashFormat.class.getSimpleName() + " instance, "
                        + "any hashService configuration changes will break previously hashed/saved passwords.";
                logger.warn(msg);
                this.hashFormatWarned = true;
            }
        }
    }

    protected HashRequest buildHashRequest(ByteSource plaintext, Hash saved) {
        //keep everything from the saved hash except for the source:
        return new HashRequest.Builder().setSource(plaintext)
                .setAlgorithmName(saved.getAlgorithmName())
                .setSalt(saved.getSalt())
                .setIterations(saved.getIterations())
                .build();
    }

    protected HashRequest createHashRequest(ByteSource plaintext) {
        return new HashRequest.Builder().setSource(plaintext).build();
    }

    protected ByteSource createByteSource(Object o) {
        return ByteSource.Util.bytes(o);
    }

    public HashService getHashService() {
        return hashService;
    }

    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    public HashFormat getHashFormat() {
        return hashFormat;
    }

    public void setHashFormat(HashFormat hashFormat) {
        this.hashFormat = hashFormat;
    }

    public HashFormatFactory getHashFormatFactory() {
        return hashFormatFactory;
    }

    public void setHashFormatFactory(HashFormatFactory hashFormatFactory) {
        this.hashFormatFactory = hashFormatFactory;
    }
}
