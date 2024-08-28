package group

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GroupingByTest {
    val persons = listOf(
        Person("카이사", 20),
        Person("이즈리얼", 20),
        Person("진", 21),
        Person("케이틀린", 21),
        Person("애쉬", 22),
    )

    // aggregate는 각 key에 해당하는 value들을 loop돌며 누산하여 하나의 결과물을 생성할 수 있다.
    @Test
    fun `persons를 groupingBy와 aggregate를 사용하여 key-나이, value-그 나이의 사람들을 나열한 String인 Map으로 변환한다`() {
        // given
        val expected: Map<Int, String> = mapOf(
            20 to "20: 카이사, 이즈리얼",
            21 to "21: 진, 케이틀린",
            22 to "22: 애쉬",
        )

        // when
        val grouping: Grouping<Person, Int> = persons.groupingBy { it.age }
        val actual: Map<Int, String> =
            grouping.aggregate { key: Int, accumulator: String?, element: Person, first: Boolean ->
                if (first) {
                    "$key: ${element.name}"
                } else {
                    accumulator + ", ${element.name}"
                }
            }

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    // eachCount는 각 key에 해당하는 value들의 개수를 value로 하는 Map을 만들 수 있다..
    @Test
    fun `persons를 groupingBy와 eachCount를 사용하여 key-나이, value-해당 나이인 value의 개수인 Map으로 변환한다`() {
        // given
        val expected: Map<Int, Int> = mapOf(
            20 to 2,
            21 to 2,
            22 to 1,
        )

        // when
        val grouping: Grouping<Person, Int> = persons.groupingBy { it.age }
        val actual: Map<Int, Int> = grouping.eachCount()

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }
}