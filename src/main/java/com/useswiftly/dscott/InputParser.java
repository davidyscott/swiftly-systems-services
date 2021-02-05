package com.useswiftly.dscott;

import com.useswiftly.dscott.model.ProductRecord;
import com.useswiftly.dscott.model.input.FieldDefinition;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Encapsulates only the highest level of input processing.  Should the method of receiving input
 * data change, replacing this class should be a fairly straightforward task.
 */
public class InputParser {
  private final Stream<String> inputLines;

  /**
   * @param inputLines A Stream of the lines from the input file (each element of the Stream is a
   *                   line of input)
   */
  public InputParser(Stream<String> inputLines) {
    this.inputLines = inputLines;
  }

  /**
   * Take in a line, split it into the fields that comprise it
   *
   * @param inputLine
   * @return
   */
  public static Map<FieldDefinition, String> extractRawFields(String inputLine) {
    return Stream.of(FieldDefinition.values())
                 .collect(Collectors.toMap(
                     Function.identity(),
                     fieldDefinition -> extractSingleField(fieldDefinition, inputLine)));
  }

  public static String extractSingleField(FieldDefinition fieldDefinition, String inputLine) {
    // This substring method is exclusive of the endIndex, but we need to include the endIndex
    return inputLine.substring(fieldDefinition.getStartIndex(), fieldDefinition.getEndIndex() + 1);
  }

  public List<ProductRecord> parse() {
    return inputLines.map(InputParser::extractRawFields)
                     .map(rawFields -> new ProductRecordCreator(rawFields).create())
                     .collect(Collectors.toList());
  }

}
