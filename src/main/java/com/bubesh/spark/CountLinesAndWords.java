package com.bubesh.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class CountLinesAndWords {

    private void countLinesAndWords() {
        // Set the master to local[2], you can set it to local
        // Note : For setting master, you can also use SparkSession.builder().master()
        // I preferred using config, which provides us more flexibility
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        // Create the session using the config above
        // getOrCreate : Gets an existing SparkSession or, if there is no existing one,
        // creates a new one based on the options set in this builder.
        SparkSession session = SparkSession.builder().config("spark.master", "local").getOrCreate();
        System.out.println("Session created");
        // Read the input file, and create a RDD from it

        JavaRDD<Row> lines = session.read().text(getClass().getResource("/input.txt").toString()).javaRDD();
        // Split the lines into words
        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.getString(0).split(" ")).iterator());
        // Print lines
        System.out.println(lines.count());
        // Print words
        System.out.println(words.count());
    }

    public static void main(String[] args) {
        CountLinesAndWords countLinesAndWords = new CountLinesAndWords();
        countLinesAndWords.countLinesAndWords();
    }

}
