package stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SteamFunctionTest {
    final List<Person> persons = new ArrayList<Person>(
            Arrays.asList(
                    new Person("귀여운쵸파", 18),
                    new Person("루피", 20),
                    new Person("조로", 22),
                    new Person("상디", 24),
                    new Person("우솝", 26),
                    new Person("나미", 28),
                    new Person("변신한프랑키", 30)
            )
    );

    @Test
    public void 스트림함수_filter를_사용해_이름이_4자_이하인_사람들만_선택해_리스트를_만든다() {
        // given
        List<Person> expected = new ArrayList<Person>(Arrays.asList(
                new Person("루피", 20),
                new Person("조로", 22),
                new Person("상디", 24),
                new Person("우솝", 26),
                new Person("나미", 28)
        ));

        // when
        List<Person> actual = persons.stream()
                .filter(person -> person.getName().length() <= 4)
                .toList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void 스트림함수_filter를_사용해_나이가_25세_이하인_사람들만_선택해_리스트를_만든다() {
        // given
        List<Person> expected = new ArrayList<Person>(Arrays.asList(
                new Person("귀여운쵸파", 18),
                new Person("루피", 20),
                new Person("조로", 22),
                new Person("상디", 24)
        ));

        // when
        List<Person> actual = persons.stream()
                .filter(person -> person.getAge() <= 25)
                .toList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void 스트림함수_sorted를_이용하여_사람들을_나이를_기준으로_내림차순으로_정렬한다() {
        // given
        List<Person> expected = new ArrayList<Person>(Arrays.asList(
                new Person("변신한프랑키", 30),
                new Person("나미", 28),
                new Person("우솝", 26),
                new Person("상디", 24),
                new Person("조로", 22),
                new Person("루피", 20),
                new Person("귀여운쵸파", 18)
        ));

        // when
        List<Person> actual = persons.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .toList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void 스트림_함수_map을_사용해_이름만_담긴_리스트를_만든다() {
        // given
        List<String> expected = new ArrayList<String>(Arrays.asList(
                "귀여운쵸파",
                "루피",
                "조로",
                "상디",
                "우솝",
                "나미",
                "변신한프랑키"
        ));

        // when
        List<String> actual = persons.stream().map(Person::getName).toList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void 스트림함수를_사용해_이름이_4자_이하이고_나이가_25세_이하인_사람들을_나이_내림차순으로_정렬하여_이름만_담긴_리스트를_만든다() {
        // given
        List<String> expected = new ArrayList<String>(Arrays.asList("상디", "조로", "루피"));

        // when
        List<String> actual = persons.parallelStream()
                .filter(person -> person.getName().length() <= 4)
                .filter(person -> person.getAge() <= 25)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .map(Person::getName)
                .toList();

        // then
        Assertions.assertEquals(expected, actual);
    }
}
