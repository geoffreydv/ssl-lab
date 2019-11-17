# SSL playground

This project contains all kinds of correctly and incorrectly set up servers with SSL enabled to learn how to debug SSL issues.

## Generating a CA certificate

### Create a private key

```shell script
openssl genrsa -out ca.private.key 4096
openssl req -new -x509 -days 1826 -subj "/CN=SSL Essentials CA" -key ca.private.key -out ca.crt
```

### Valid Certificate

```shell script
openssl genrsa -out valid.key 4096
openssl req -new -key valid.key -out valid.csr -config <( cat valid.conf )
openssl x509 -req -CA ../ca/ca.crt -CAkey ../ca/ca.private.key -CAcreateserial -days 1826 -in valid.csr -extfile v3.ext -out valid.crt
openssl pkcs12 -export -clcerts -in valid.crt -inkey valid.key -out valid.p12 -name ssl_keypair -noiter -nomaciter
```