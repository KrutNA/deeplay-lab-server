package io.deeplay.lab.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public interface Parser<T> {
    Stream<T> process(InputStream is) throws IOException;
}
