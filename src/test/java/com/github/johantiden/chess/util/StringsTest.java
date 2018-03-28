package com.github.johantiden.chess.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StringsTest {

    @Test
    public void testExtractNameWithoutThe() throws Exception {

        assertThat(Strings.extractNameWithoutThe().apply("The hej"), is("hej"));
        assertThat(Strings.extractNameWithoutThe().apply("hej hej"), is("hej hej"));

    }
}