package io.deeplay.lab.parser;

import io.deeplay.lab.data.Round;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public interface Parser<T> {
    Stream<T> process(InputStream is) throws IOException ;
}
