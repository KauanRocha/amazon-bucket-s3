# Amazon S3 File Management

![Java](https://img.shields.io/badge/Java-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen) ![AWS SDK for Java](https://img.shields.io/badge/AWS%20SDK%20for%20Java-blue) ![License](https://img.shields.io/badge/License-MIT-yellow)

## Descrição

Este projeto é uma aplicação Java/Spring Boot para gerenciamento de arquivos no Amazon S3. Ele inclui funcionalidades básicas de upload, download e exclusão de arquivos no Amazon S3 utilizando a AWS SDK for Java e o Spring Framework.

## Configuração

Antes de executar a aplicação, é necessário configurar suas credenciais da AWS e o nome do bucket no arquivo `application.yaml`. Certifique-se de ter as permissões adequadas para acessar e gerenciar objetos no Amazon S3.

```yaml
aws:
  region:
    static: ${AWS-REGION:us-east-2}
  credentials:
    access-key: ${YOUR_ACCESS_KEY:test}
    secret-key: ${YOUR_SECRET_KEY:test}
  bucket:
    name: ${BUCKET_NAME:amazon-bucket-test}
```

## Funcionalidades

### Publicar Arquivo

Endpoint: `POST /api/files/upload`

Este endpoint permite enviar um arquivo para o Amazon S3. O arquivo será salvo no bucket configurado com o nome especificado. Para isso, você precisa enviar uma requisição POST com o arquivo a ser enviado no corpo da requisição e o nome do arquivo como parâmetro.

### Baixar Arquivo

Endpoint: `GET /api/files/download/{objectKeyName}`

Este endpoint permite baixar um arquivo do Amazon S3. Você precisa especificar o nome da chave (object key) do arquivo que deseja baixar como parte da URL. O arquivo será retornado como um download para o usuário.

### Deletar Arquivo

Endpoint: `DELETE /api/files/delete/{objectKey}`

Este endpoint permite excluir um arquivo do Amazon S3. Você precisa especificar o nome da chave (object key) do arquivo que deseja excluir como parte da URL. O arquivo será removido do bucket configurado.
