package ru.practice.javacore.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStringBuilderTest {
    @Test
    public void append_undo_test () {
      MyStringBuilder stringBuilder = new MyStringBuilder();
        MyStringBuilder result = stringBuilder.append("Hello ")
                .append("World");
        assertEquals("Hello World", result.toString());
        stringBuilder.clear();
        MyStringBuilder resultToUndo = stringBuilder.append("Hello ")
                .append("New ")
                .append("World");
        stringBuilder.undo();
        assertEquals("Hello New ", resultToUndo.toString());
    }
}
