package io.deeplay.lab.data;

public enum Team {
    Our(0),
    Opponent(1);

    final short data;
    Team(int data) {
        this.data = (short)data;
    }

    public short toInner() {
        return this.data;
    }
}
