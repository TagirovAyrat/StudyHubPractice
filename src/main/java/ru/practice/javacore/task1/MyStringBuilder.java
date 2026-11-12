package ru.practice.javacore.task1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Класс реализация собственного StringBuilder с 3 методами append undo и clear.
 */
public class MyStringBuilder {

    private char[] data = new char[16];
    private int length = 0;
    private final Deque<String> operationQueue = new ArrayDeque<>();

    private void createSnapshot() {
        operationQueue.push(new String(data, 0, length)); //создает новую строку, а не копируем старую.
    }

    public MyStringBuilder append(String s) {
        createSnapshot();
        ensureCapacity(length + s.length());
        s.getChars(0, s.length(), data, length);
        length += s.length();
        return this;
    }

    public void undo() {
        if (operationQueue.isEmpty()) {
            return;
        }
        String snapshot = operationQueue.pop();
        this.data = snapshot.toCharArray();
        this.length = data.length;
    }

    public void clear() {
        operationQueue.clear();
        Arrays.fill(data, (char) 0);
        length = 0;
    }

    @Override
    public String toString() {
        return new String(data, 0, length);
    }

    private void ensureCapacity(int sum) {
        if(data.length < sum) {
            data = Arrays.copyOf(data, Math.min(sum, data.length * 2));
        }
    }

}