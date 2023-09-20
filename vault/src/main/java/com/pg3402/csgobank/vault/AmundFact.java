package com.pg3402.csgobank.vault;

/*@Data
@Value*/
public class AmundFact {
    public String getFact() {
        return fact;
    }

    @Override
    public String toString() {
        return "AmundFact{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    String fact;
    int length;
}
