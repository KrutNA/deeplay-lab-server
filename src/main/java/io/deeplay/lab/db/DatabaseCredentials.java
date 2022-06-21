package io.deeplay.lab.db;

public record DatabaseCredentials(
        String url,
        String user,
        String password
) {
}
