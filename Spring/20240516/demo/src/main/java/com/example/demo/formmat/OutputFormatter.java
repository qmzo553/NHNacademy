package com.example.demo.formmat;

import com.example.demo.domain.Price;

public interface OutputFormatter {

    String format(Price price, int usage);
}
