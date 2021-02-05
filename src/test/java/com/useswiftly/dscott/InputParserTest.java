package com.useswiftly.dscott;

import com.google.common.collect.ImmutableList;
import com.useswiftly.dscott.model.ProductRecord;
import com.useswiftly.dscott.model.Unit;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class InputParserTest {
  @Test
  void simpleHappyPathWorks() throws URISyntaxException, IOException {
    var expected = ImmutableList.of(
        ProductRecord.builder()
                     .productId(80000001)
                     .productDescription("Kimchi-flavored white rice")
                     .productSize("18oz")
                     .taxRate(BigDecimal.ZERO)
                     .unit(Unit.EACH)
                     .regularCalculatorPrice(new BigDecimal("5.67"))
                     .regularDisplayPrice("$5.67")
                     .saleCalculatorPrice(null)
                     .saleDisplayPrice(null)
                     .build(),
        ProductRecord.builder()
                     .productId(14963801)
                     .productDescription("Generic Soda 12-pack")
                     .productSize("12x12oz")
                     .taxRate(new BigDecimal("0.07775"))
                     .unit(Unit.EACH)
                     .regularCalculatorPrice(new BigDecimal("6.5000"))
                     .regularDisplayPrice("2 for $13.00")
                     .saleCalculatorPrice(new BigDecimal("5.49"))
                     .saleDisplayPrice("$5.49")
                     .build(),
        ProductRecord.builder()
                     .productId(40123401)
                     .productDescription("Marlboro Cigarettes")
                     .productSize("") // TODO: is there a more correct output here?
                     .taxRate(BigDecimal.ZERO)
                     .unit(Unit.EACH)
                     .regularCalculatorPrice(new BigDecimal("10.00"))
                     .regularDisplayPrice("$10.00")
                     .saleCalculatorPrice(new BigDecimal("5.49"))
                     .saleDisplayPrice("$5.49")
                     .build(),
        ProductRecord.builder()
                     .productId(50133333)
                     .productDescription("Fuji Apples (Organic)")
                     .productSize("lb")
                     .taxRate(BigDecimal.ZERO)
                     .unit(Unit.POUND)
                     .regularCalculatorPrice(new BigDecimal("3.49"))
                     .regularDisplayPrice("$3.49")
                     .saleCalculatorPrice(null)
                     .saleDisplayPrice(null)
                     .build()
    );

    List<Matcher<? super ProductRecord>> expectedMatchers =
        expected.stream()
                .map(Matchers::equalTo)
                .collect(Collectors.toList());

    var inputFile = getClass().getResource("/input-sample.txt");
    try (var reader = new BufferedReader(new FileReader(new File(inputFile.toURI())))) {
      var actual = new InputParser(reader.lines()).parse();

      assertThat(actual, containsInAnyOrder(expectedMatchers));
    }

  }

}