package com.cosmotech.workflow;

import com.cosmotech.connector.adt.impl.AzureDigitalTwinsConnector;
import com.cosmotech.connector.adx.impl.AzureDataExplorerConnector;
import com.cosmotech.connector.commons.pojo.CsvData;
import com.cosmotech.connector.storage.impl.AzureStorageConnector;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    AzureDigitalTwinsConnector azureDigitalTwinsConnector = new AzureDigitalTwinsConnector();
    final List<CsvData> adtData = azureDigitalTwinsConnector.process();

    AzureStorageConnector azureStorageConnector = new AzureStorageConnector(adtData);
    final Map<String, CsvData> storageData = azureStorageConnector.process();

    final AzureDataExplorerConnector azureDataExplorerConnector = new AzureDataExplorerConnector(storageData);
    azureDataExplorerConnector.process();

  }
}
