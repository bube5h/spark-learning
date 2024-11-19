package com.bubesh.spark;

import org.apache.spark.SparkFiles;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MnmStatistics {

    public static void main(String[] args) {

        SparkSession session = SparkSession.builder()
                                           .appName("mnm-statistics")
                                           .config("spark.master", "local")
                                           .getOrCreate();
        session.sparkContext()
               .addFile(
                       "https://raw.githubusercontent.com/databricks/LearningSparkV2/master/chapter2/py/src/data/mnm_dataset.csv");

        Dataset<Row> dataset = session.read()
                                      .option("header", true)
                                      .option("inferSchema", true)
                                      .csv("file://" + SparkFiles.get("mnm_dataset.csv"));

        dataset.show();

    }

}
