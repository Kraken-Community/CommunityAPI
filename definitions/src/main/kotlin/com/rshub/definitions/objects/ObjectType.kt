package com.rshub.definitions.objects

enum class ObjectType(val id: Int, val slot: Int) {
    WALL_STRAIGHT(0, 0), WALL_DIAGONAL_CORNER(1, 0), WALL_WHOLE_CORNER(2, 0), WALL_STRAIGHT_CORNER(
        3,
        0
    ),
    STRAIGHT_INSIDE_WALL_DEC(4, 1), STRAIGHT_OUSIDE_WALL_DEC(5, 1), DIAGONAL_OUTSIDE_WALL_DEC(
        6,
        1
    ),
    DIAGONAL_INSIDE_WALL_DEC(7, 1), DIAGONAL_INWALL_DEC(8, 1), WALL_INTERACT(9, 2), SCENERY_INTERACT(
        10,
        2
    ),
    GROUND_INTERACT(11, 2), STRAIGHT_SLOPE_ROOF(12, 2), DIAGONAL_SLOPE_ROOF(13, 2), DIAGONAL_SLOPE_CONNECT_ROOF(
        14,
        2
    ),
    STRAIGHT_SLOPE_CORNER_CONNECT_ROOF(15, 2), STRAIGHT_SLOPE_CORNER_ROOF(16, 2), STRAIGHT_FLAT_ROOF(
        17,
        2
    ),
    STRAIGHT_BOTTOM_EDGE_ROOF(18, 2), DIAGONAL_BOTTOM_EDGE_CONNECT_ROOF(19, 2), STRAIGHT_BOTTOM_EDGE_CONNECT_ROOF(
        20,
        2
    ),
    STRAIGHT_BOTTOM_EDGE_CONNECT_CORNER_ROOF(21, 2), GROUND_DECORATION(22, 3);

    val isWall: Boolean
        get() = id >= WALL_STRAIGHT.id && id <= WALL_STRAIGHT_CORNER.id || id == WALL_INTERACT.id;

    companion object {
        private val MAP = HashMap<Int, ObjectType>()

        init {
            for (t in values()) MAP[t.id] = t
        }

        fun forId(type: Int): ObjectType? {
            return MAP[type]
        }
    }
}