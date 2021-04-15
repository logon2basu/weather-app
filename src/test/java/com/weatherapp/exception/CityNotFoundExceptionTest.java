package com.weatherapp.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
public class CityNotFoundExceptionTest {


    @Test
    public void should_Return_the_message() {
        CityNotFoundException cityNotFoundException = new CityNotFoundException("bb");
        assertThat(cityNotFoundException.getMessage(), is("bb"));
        assertThat(cityNotFoundException.toString(), is("CityNotFoundExcpetion{message='bb'}"));
    }
}
