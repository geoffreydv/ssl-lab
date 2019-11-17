# SSL playground

This project contains all kinds of correctly and incorrectly set up servers with SSL enabled to learn how to debug SSL issues.

## Generating a CA certificate

### Create a private key

```shell script
# Generate a private key
openssl genrsa -out ca.private.key 4096

# Generate a public key
openssl req -new -x509 -days 1826 -subj "/CN=SSL Essentials Lab CA" -key ca.private.key -out ca.crt
```