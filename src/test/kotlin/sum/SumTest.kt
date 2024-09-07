package sum

import group.Person
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SumTest {
    val persons = listOf(
        Person("카이사", 20),
        Person("이즈리얼", 20),
        Person("진", 21),
        Person("케이틀린", 21),
        Person("애쉬", 22),
    )

    @Test
    fun `sumOf로 persons의 나이의 합을 구한다`() {
        // given
        val expected: Int = 104

        // when
        val actual: Int = persons.sumOf { it.age }

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `fold를 통해 persons의 나이의 합을 구한다`() {
        // given
        val expected: Int = 104

        // when
        val actual = persons.fold(0) { acc: Int, person: Person ->
            acc + person.age
        }

        // then
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }
}