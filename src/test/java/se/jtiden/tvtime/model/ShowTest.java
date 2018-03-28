package se.jtiden.tvtime.model;

import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShowTest {

    @Test(expected = NullPointerException.class)
    public void compareOfNullable2() {
        Comparator<Optional<String>> comparator = Comparator
                .nullsLast(
                        Comparator.comparing(
                                o -> o.orElse(null)));

        Optional<String> a = Optional.of("hej");
        Optional<String> b = Optional.ofNullable(null);

        comparator.compare(a, b);
    }

    @Test
    public void testName() throws Exception {

        assertThat(Optional.ofNullable(null), notNullValue());
        assertThat(Optional.ofNullable(null), is(Optional.empty()));

    }
}