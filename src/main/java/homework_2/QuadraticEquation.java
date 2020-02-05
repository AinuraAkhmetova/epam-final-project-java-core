package homework_2;

import java.util.Arrays;

public class QuadraticEquation {
    private float a, b, c;
    private float discriminant;
    private Float[] solutions;

    public QuadraticEquation(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;

        discriminant = calculateDiscriminant(a, b, c);
        solutions = calculateSolutions(a, b, discriminant);
    }


    public static void main(String[] args) {

        QuadraticEquation equation1 = new QuadraticEquation(3, -14, -5);
        System.out.println(equation1);

    }


    private static Float[] calculateSolutions(float a, float b, float discriminant) {

        Float[] solutions;

        if (discriminant > 0) {
            solutions = new Float[2];

            solutions[0] = (-b + (float) Math.sqrt(discriminant)) / (2 * a);
            solutions[1] = (-b - (float) Math.sqrt(discriminant)) / (2 * a);

        } else if (discriminant == 0) {
            solutions = new Float[1];

            solutions[0] = (-b + (float) Math.sqrt(discriminant)) / (2 * a);

        } else {

            solutions = null;

        }
        return solutions;

    }


    private static float calculateDiscriminant(float a, float b, float c) {

        return b * b - 4 * a * c;

    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "QuadraticEquation{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", discriminant=" + discriminant +
                ", solutions=" + Arrays.toString(solutions) +
                '}';
    }
}
