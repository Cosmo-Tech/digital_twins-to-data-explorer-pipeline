# Azure Digital Twins to Azure Data Explorer Pipeline
The aim of this project is to :
- read/extract data from an ADT
- Store data in Azure Storage (CSV files)
- create tables and ingest data into it

## Properties to overwrite :
Here is the list of properties that should be changed (in ```META-INF/microprofile-config.properties``` file):
- azure.client.id
- azure.tenant.id
- azure.client.secret
- azure.digital.twins.url
- export.csv.file.absolute.path
- azure.storage.connection.string
- azure.storage.blob.container.name
- azure.data.explorer.resource.uri
- azure.data.explorer.database.name
- azure.storage.account.name
- azure.storage.account.key
- azure.storage.shared.access.signature.duration
- azure.storage.shared.access.signature.permissions
- azure.storage.shared.access.signature.services
- azure.storage.shared.access.signature.resource.types


If you want to overwrite these properties, you can write your own property values in the ```META-INF/microprofile-config.properties``` file, or set a property's system, or an environment variable named :
- AZURE_CLIENT_ID : the Azure client id (can be found under the App registration screen)
- AZURE_TENANT_ID : the Azure Tenant id (can be found under the App registration screen)
- AZURE_CLIENT_SECRET : the app client secret (can be found under the App registration/certificates and secrets screen)
- AZURE_DIGITAL_TWINS_URL : the url of the ADT targeted (can be found in the specific resource screen)
- EXPORT_CSV_FILE_ABSOLUTE_PATH : the absolute path to export all csv files (don't forget the / at the end)
- AZURE_STORAGE_CONNECTION_STRING
- AZURE_STORAGE_BLOB_CONTAINER_NAME
- AZURE_DATA_EXPLORER_RESOURCE_URI : the ADX cluster path (URI info can be found into ADX cluster page)
- AZURE_DATA_EXPLORER_DATABASE_NAME : the targeted database name
- AZURE_STORAGE_ACCOUNT_NAME : the Azure storage account name (can be found under the Storage account screen)
- AZURE_STORAGE_ACCOUNT_KEY : the Azure storage account key (can be found under the specific Storage account screen, under Access keys section)
- AZURE_STORAGE_SHARED_ACCESS_SIGNATURE_DURATION : the SAS Token valid duration (obviously, should be positive and be as small as possible )
- AZURE_STORAGE_SHARED_ACCESS_SIGNATURE_PERMISSIONS : the SAS Token permissions (should be the most restrictive possible)
- AZURE_STORAGE_SHARED_ACCESS_SIGNATURE_SERVICES : the SAS Token services (should be the most restrictive possible)
- AZURE_STORAGE_SHARED_ACCESS_SIGNATURE_RESOURCE_TYPES :the SAS Token resource types (should be the most restrictive possible)

## Application insights
The connector comes with a javaagent for adding connector's outputs to an application insights.

Create an application insight through portal.azure and set the connection string into the file ```/src/main/jib/applicationinsights.json```

![Application Insights](README/ApplicationInsights.png)


##Change the default container registry

```
  <to>
    <image>${your_container_registry}/digital-twins-data-explorer-pipeline</image>
  </to>
```
See [Jib project Configuration]("https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#configuration") to set correctly your container registry (GCR, ECR, ACR, Docker Hub Registry)

Build your container image with:

```shell
mvn compile jib:build
```

Subsequent builds are much faster than the initial build.

#### Build to Docker daemon

Jib can also build your image directly to a Docker daemon. This uses the `docker` command line tool and requires that you have `docker` available on your `PATH`.

```shell
mvn compile jib:dockerBuild
```

For more information, see [Jib project Build]("https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#build-your-image")

#### How to run your image locally

```
docker run \ 
-v <<local_export_dir_path>>:/tmp \ 
-e EXPORT_CSV_FILE_ABSOLUTE_PATH=/tmp/ \ 
<your_container_registry>/digital-twins-data-explorer-pipeline
```

You can find all export files under the directory "local_export_dir_path" specified above
