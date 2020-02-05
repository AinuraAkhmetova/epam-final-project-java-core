package lesson.a_3_converter;

@FunctionalInterface
public interface Converter<FROM, TO> {
    TO convert(FROM from);
}
