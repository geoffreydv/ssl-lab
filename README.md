# SSL playground

This project contains all kinds of correctly and incorrectly set up servers with SSL enabled to learn how to debug SSL issues.

## Create a CA certificate

In CA dir

Create the CA root certificate
```shell script
openssl req -new -x509 -extensions v3_ca -keyout cakey.key -out cacert.pem -days 3650 -config ./self_signed.conf
```

## Final Commands to sign

In client's certificate dir

```shell script
openssl req -new -nodes -out server-req.csr -keyout server.key -days 3650 -config csr.conf
```

Sign it in the ca dir:

Prepare some signing directories / files

```shell script
echo '100001' > serial
touch certindex.txt
```
Sign the server's CSR
```shell script
openssl ca -out server-cert.pem -days 3650 -config ./self_signed.conf -infiles ../create_csr/server-req.csr
```

Send back the files to the server and create a keystore

```shell script
openssl pkcs12 -export -clcerts -in server-cert.pem -inkey server.key -out valid.p12 -name ssl_keypair -noiter -nomaciter
```