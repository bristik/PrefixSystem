package ru.bristol.prefixsystem.files.config;

public interface ConfigRepository {

    void init();

    boolean isBlockedPrefix(String path);

}
