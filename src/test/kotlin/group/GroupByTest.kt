package group

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GroupByTest {
    val persons = listOf(
        Person("카이사", 20),
        Person("이즈리얼", 20),
        Person("진", 21),
        Person("케이틀린", 21),
        Person("애쉬", 22),
    )

    @Test
    fun `persons를 groupBy를 사용하여 key-나이, value-Person으로 하는 Map으로 변환한다`() {
        // given
        val expected: Map<Int, List<Person>> = mapOf(
            20 to listOf(Person("카이사", 20), Person("이즈리얼", 20)),
            21 to listOf(Person("진", 21), Person("케이틀린", 21)),
            22 to listOf(Person("애쉬", 22)),
        )

        // when
        val actual: Map<Int, List<Person>> = persons.groupBy { it.age }

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `persons를 groupBy를 사용하여 key-나이, value-이름으로 하는 Map으로 변환한다`() {
        // given
        val expected: Map<Int, List<String>> = mapOf(
            20 to listOf("카이사", "이즈리얼"),
            21 to listOf("진", "케이틀린"),
            22 to listOf("애쉬"),
        )

        // when
        val actual = persons.groupBy(
            keySelector = { it.age },
            valueTransform = { it.name }
        )

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `persons를 groupBy를 사용하여 이름이 나온 횟수를 값으로 갖는 Map으로 만든다`() {
        // given
        val names = listOf("Havertz", "Havertz", "Odegaard", "Saka", "Rice", "Rice")

        // when
        val actual = names.groupBy { it }
    }
}