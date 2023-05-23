package com.gaggle.challenge

import org.gaggle.challenge.MeanMedianModeCalculator
import spock.lang.Specification

class MeanMedianModeCalculatorSpecification extends Specification {

    def 'feature [#iterationIndex]: Data: #array Mean=#ExpectedMean Median=#ExpectedMedian Mode=#ExpectedMode'() {
        given:
        def app = new MeanMedianModeCalculator()
        Integer mean = 0
        Double median = 0.0
        Integer mode = 0

        when:
        mean = app.calculateMean(array)
        median = app.calculateMedian(array)
        mode = app.calculateMode(array)

        then:
        mean == ExpectedMean
        median == ExpectedMedian
        mode == ExpectedMode

        where:              //(avg)         | sort then middle # |  most common
        array               || ExpectedMean | ExpectedMedian     | ExpectedMode
        [7, 6, 3, 8, 1, -2] || 3            | 4.5                | 8
        [1, 3, 5]           || 3            | 3                  | 5
        [1]                 || 1            | 1                  | 1
        [0, 0, 0]           || 0            | 0                  | 0
        [-1, -1, -1]        || -1           | -1                 | -1
[11, 77, 32, 45, 48, 77, 32, 77]| 49        | 46.5               | 77
    }
}