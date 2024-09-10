package eu.chrost

import eu.chrost.Shooter.Ship
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import strikt.api.expectThat
import strikt.assertions.isEqualTo

@DisplayNameGeneration(ReplaceUnderscores::class)
internal class ShooterTest {
    @Test
    fun A_board_without_ships_returns_finished_state_on_first_shot() {
        //given
        val shooter = Shooter(BOARD_WITH_NO_SHIPS)

        //when
        val result = shooter.shoot(FIELD_WITHOUT_SHIP)

        //then
        expectThat(result).isEqualTo(FINISHED)
    }

    @Nested
    internal inner class A_board_with_single_two_fields_ship {
        //given
        var shooter: Shooter = Shooter(BOARD_WITH_SINGLE_TWO_FIELDS_SHIP)

        @Test
        fun returns_missed_status_on_first_shot_on_field_without_ship() {
            //when
            val result = shooter.shoot(FIELD_WITHOUT_SHIP)

            //then
            expectThat(result).isEqualTo(MISSED)
        }

        @Test
        fun returns_hit_and_then_sunk_results_on_shot_on_all_ship_fields() {
            //when
            var result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD)

            //then
            expectThat(result).isEqualTo(HIT)

            //when
            result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD)

            //then
            expectThat(result).isEqualTo(FINISHED)
        }
    }

    @Test
    fun A_board_with_multiple_ships_returns_proper_statuses_on_all_shots() {
        //given
        val shooter = Shooter(BOARD_WITH_MULTIPLE_SHIPS)

        //when
        var result = shooter.shoot(FIELD_WITHOUT_SHIP)

        //then
        expectThat(result).isEqualTo(MISSED)

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD)

        //then
        expectThat(result).isEqualTo(HIT)

        //when
        result = shooter.shoot(ONE_FIELD_SHIP_FIELD)

        //then
        expectThat(result).isEqualTo(SUNK)

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD)

        //then
        expectThat(result).isEqualTo(HIT)

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD)

        //then
        expectThat(result).isEqualTo(SUNK)

        //when
        result = shooter.shoot(VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD)

        //then
        expectThat(result).isEqualTo(SUNK)

        //when
        result = shooter.shoot(ANOTHER_ONE_FIELD_SHIP_FIELD)

        //then
        expectThat(result).isEqualTo(SUNK)

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD)

        //then
        expectThat(result).isEqualTo(HIT)

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD)

        //then
        expectThat(result).isEqualTo(FINISHED)

        //when
        result = shooter.shoot(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD)

        //then
        expectThat(result).isEqualTo(FINISHED)

        //when
        result = shooter.shoot(ANOTHER_FIELD_WITHOUT_SHIP)

        //then
        expectThat(result).isEqualTo(FINISHED)
    }

    companion object {
        private val VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD: Shooter.Point = Shooter.point(3, 4)
        private val VERTICAL_TWO_FIELDS_SHIP_SECOND_FIELD: Shooter.Point = Shooter.point(3, 5)

        private val ONE_FIELD_SHIP_FIELD: Shooter.Point = Shooter.point(7, 2)

        private val ANOTHER_ONE_FIELD_SHIP_FIELD: Shooter.Point = Shooter.point(6, 5)

        private val HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD: Shooter.Point = Shooter.point(1, 1)
        private val HORIZONTAL_TWO_FIELDS_SHIP_SECOND_FIELD: Shooter.Point = Shooter.point(2, 1)

        private val FIELD_WITHOUT_SHIP: Shooter.Point = Shooter.point(1, 2)
        private val ANOTHER_FIELD_WITHOUT_SHIP: Shooter.Point = Shooter.point(4, 4)

        private const val VERTICAL = true
        private const val HORIZONTAL = false

        private const val ONE_FIELD_SHIP_SIZE = 1
        private const val TWO_FIELDS_SHIP_SIZE = 2

        private val VERTICAL_TWO_FIELDS_SHIP: Ship =
            Ship.of(VERTICAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, VERTICAL)
        private val ONE_FIELD_SHIP: Ship = Ship.of(ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, HORIZONTAL)
        private val ANOTHER_ONE_FIELD_SHIP: Ship = Ship.of(ANOTHER_ONE_FIELD_SHIP_FIELD, ONE_FIELD_SHIP_SIZE, VERTICAL)
        private val HORIZONTAL_TWO_FIELDS_SHIP: Ship =
            Ship.of(HORIZONTAL_TWO_FIELDS_SHIP_FIRST_FIELD, TWO_FIELDS_SHIP_SIZE, HORIZONTAL)

        private val BOARD_WITH_NO_SHIPS = listOf<Ship>()
        private val BOARD_WITH_SINGLE_TWO_FIELDS_SHIP = listOf(VERTICAL_TWO_FIELDS_SHIP)
        private val BOARD_WITH_MULTIPLE_SHIPS = listOf(
            VERTICAL_TWO_FIELDS_SHIP,
            ONE_FIELD_SHIP,
            ANOTHER_ONE_FIELD_SHIP,
            HORIZONTAL_TWO_FIELDS_SHIP
        )

        private const val MISSED = 0
        private const val HIT = 1
        private const val SUNK = 2
        private const val FINISHED = 3
    }
}

