# SSL playground

This project contains all kinds of correctly and incorrectly set up servers with SSL enabled to learn how to debug SSL issues.

## Generating a CA certificate

### Create a private key

```shell script
openssl req -new -x509 -extensions v3_ca -keyout private/cakey.pem -out cacert.pem -days 3650 -config ./self_signed.conf
```

### Valid Certificate

```shell script

openssl req -new -nodes -out server-req.csr -keyout private/server-key.pem -days 3650 -config ./self_signed.conf
openssl ca -out server-cert.pem -days 3650 -config ./self_signed.conf -copy_extensions -infiles server-req.csr
 

# Sign with CA
openssl ca -out server-cert.pem -days 3650 -config ./self_signed.conf -infiles server-req.csr 
openssl genrsa -out valid.key 4096
openssl req -new -key valid.key -out valid.csr -config <( cat valid.conf )
openssl x509 -req -CA ../ca/ca.crt -CAkey ../ca/ca.private.key -CAcreateserial -days 1826 -in valid.csr -extfile v3.ext -out valid.crt
openssl pkcs12 -export -clcerts -in valid.crt -inkey valid.key -out valid.p12 -name ssl_keypair -noiter -nomaciter
```

### Creating a trust store from a CA

OpenSSL can't create a p12 file with only a certificate in it, it fails if no private key is provided.

```shell script
keytool -importcert -storetype PKCS12 -keystore truststore.p12 -storepass changeit -alias self_signed_ca -file ../../servers/ca/ca.crt -noprompt
```

### Intermediate CA
```

```