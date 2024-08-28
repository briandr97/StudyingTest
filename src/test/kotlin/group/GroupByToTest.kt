package group

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GroupByToTest {
    val persons = listOf(
        Person("카이사", 20),
        Person("이즈리얼", 20),
        Person("진", 21),
        Person("케이틀린", 21),
        Person("애쉬", 22),
    )

    @Test
    fun `persons를 groupByTo를 사용하여 key-나이, value-Person으로 하는 Map으로 만들어 비어있는 actual(destination)에 추가한다`() {
        // given
        val expected: Map<Int, List<Person>> = mapOf(
            20 to listOf(Person("카이사", 20), Person("이즈리얼", 20)),
            21 to listOf(Person("진", 21), Person("케이틀린", 21)),
            22 to listOf(Person("애쉬", 22)),
        )

        // when
        val actual = mutableMapOf<Int, MutableList<Person>>()
        persons.groupByTo(
            destination = actual,
            keySelector = { it.age }
        )

        // then
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `persons를 groupByTo를 사용하여 나이를 key-나이, value-Person으로 하는 Map으로 만들어 비어있지 않은 actual(destination)에 추가한다`() {
        // given
        val expected: Map<Int, List<Person>> = mapOf(
            20 to listOf(Person("코그모", 20), Person("카이사", 20), Person("이즈리얼", 20)),
            21 to listOf(Person("진", 21), Person("케이틀린", 21)),
            22 to listOf(Person("애쉬", 22)),
        )

        // when
        val actual = mutableMapOf<Int, MutableList<Person>>(20 to mutableListOf(Person("코그모", 20)))
        persons.groupByTo(
            destination = actual,
            keySelector = { it.age }
        )

        // then
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `persons를 groupByTo를 사용하여 key-나이, value-이름으로 하는 Map으로 만들어 비어있는 actual(destination)에 추가한다`() {
        // given
        val expected: Map<Int, List<String>> = mapOf(
            20 to listOf("카이사", "이즈리얼"),
            21 to listOf("진", "케이틀린"),
            22 to listOf("애쉬"),
        )

        // when
        val actual = mutableMapOf<Int, MutableList<String>>()
        persons.groupByTo(
            destination = actual,
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
    fun `persons를 groupByTo를 사용하여 key-나이, value-이름으로 하는 Map으로 만들어 비어있는 않은 actual(destination)에 추가한다`() {
        // given
        val expected: Map<Int, List<String>> = mapOf(
            20 to listOf("코그모", "카이사", "이즈리얼"),
            21 to listOf("진", "케이틀린"),
            22 to listOf("애쉬"),
        )

        // when
        val actual = mutableMapOf<Int, MutableList<String>>(20 to mutableListOf("코그모"))
        persons.groupByTo(
            destination = actual,
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
    fun `이름 리스트를 groupByTo의 destination, 반환값, 그리고 expected를 비교한다`() {
        // given
        val expected: Map<Int, List<Person>> = mapOf(
            20 to listOf(Person("카이사", 20), Person("이즈리얼", 20)),
            21 to listOf(Person("진", 21), Person("케이틀린", 21)),
            22 to listOf(Person("애쉬", 22)),
        )

        // when
        val destination = mutableMapOf<Int, MutableList<Person>>()
        val actual: Map<Int, MutableList<Person>> = persons.groupByTo(
            destination = destination,
            keySelector = { it.age }
        )

        assertAll(
            { assertEquals(expected = expected, actual = destination) },
            { assertEquals(expected = expected, actual = actual) },
            { assertEquals(expected = destination, actual = actual) },
            { assertFalse { expected === destination } },
            { assertFalse { expected === actual } },
            { assertTrue { destination === actual } }
        )
    }
}