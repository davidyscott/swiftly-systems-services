package com.useswiftly.dscott;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Thin executable wrapper / command-line entrypoint
 */
public class Main {
  public static void main(String[] args) throws IOException {
    try (var sc = new BufferedReader(new FileReader(args[0]))) {
      var inputLines = sc.lines();
      var productRecords = new InputParser(inputLines).parse();
      productRecords.forEach(System.out::println);
    }
  }
}
