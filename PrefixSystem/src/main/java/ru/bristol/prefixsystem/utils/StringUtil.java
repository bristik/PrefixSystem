package ru.bristol.prefixsystem.utils;

public class StringUtil {

    public static String build(String[] args, int startWith) {
        StringBuilder builder = new StringBuilder();
        for(int i = startWith; i < args.length; i++) {
            if(i != startWith) builder.append(" " + args[i]);
            else builder.append(args[i]);
        }
        return builder.toString();
    }

}
