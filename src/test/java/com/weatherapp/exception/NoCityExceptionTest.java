package com.weatherapp.exception;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NoCityExceptionTest {

    @Test
    public void should_return_message() {
        NoCityException noCityException = new NoCityException("emptyCity");
        MatcherAssert.assertThat(noCityException.getMessage(), Matchers.is("emptyCity"));
        MatcherAssert.assertThat(noCityException.toString(), Matchers.is("NoCityException{message='emptyCity'}"));
    }

}
